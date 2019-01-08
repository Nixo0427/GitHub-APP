//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.download;

import android.os.Debug;
import com.download.FileInfo.FileItem;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NixoTaskCreatorThread extends Thread {
    public static final String TAG = "NixoTaskCreatorThread";
    private NixoFileDownloader mdownLoader;
    private TaskList mTaskList;
    private String folder;
    private String extend;
    private ExecutorService executor;

    public NixoTaskCreatorThread(NixoFileDownloader downloader, TaskList taskList) {
        this.mTaskList = taskList;
        this.mdownLoader = downloader;
        this.executor = Executors.newFixedThreadPool(NixoFileDownloader.THREAD_COUNT);
    }

    public void run() {
        while(this.mdownLoader.isRunning()) {
            String url = this.mTaskList.removeReturnRul();
            if (url == null) {
                try {
                    TaskList var2 = this.mTaskList;
                    synchronized(this.mTaskList) {
                        this.mTaskList.wait();
                    }
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            } else {
                this.create(url);
            }
        }

        this.stopExcutor();
    }

    private void create(String url) {
        Debug.startMethodTracing("callc");
        FileInfo fileInfo = this.mTaskList.getFileInfoByUrl(url);
        if (fileInfo == null) {
            String apkFilePath = this.folder + MD5Util.getUpperMD5Str(url) + this.extend;
            LogUtil.d("NixoTaskCreatorThread", "file:" + apkFilePath);
            fileInfo = new FileInfo(url, 0, 0, apkFilePath);
            fileInfo._id = System.currentTimeMillis();
        }

        fileInfo.fileSize = this.getFileSize(fileInfo);
        LogUtil.d("NixoTaskCreatorThread", "fileSize:" + fileInfo.fileSize);
        if (fileInfo.fileSize <= 0) {
            fileInfo.state = 7;
            this.mdownLoader.error(url, 102);
        } else {
            boolean isSuccess = this.createApkFile(fileInfo);
            if (!isSuccess) {
                fileInfo.state = 7;
                this.mdownLoader.error(url, 103);
            } else {
                this.createSubsection(fileInfo);
                fileInfo.state = 8;
                this.mdownLoader.update(url, fileInfo.filePath, 0);
                this.mTaskList.add(fileInfo);
                Debug.stopMethodTracing();
            }
        }
    }

    private void createSubsection(FileInfo fileInfo) {
        if (fileInfo.fileSize > 0) {
            LogUtil.d("NixoTaskCreatorThread", "(4)fileInfo._id > 0 && fileInfo.fileSize > 0");
            fileInfo.fileItemList = new ArrayList();
            int itemtLen = this.getSuitableSize();
            int count = fileInfo.fileSize / itemtLen;
            if (count < 1) {
                FileItem fileItem = new FileItem();
                fileItem.info = fileInfo;
                fileItem.infoId = fileInfo._id;
                fileItem.startPos = 0;
                fileItem._id = 0L;
                fileItem.endPos = fileItem.startPos + fileInfo.fileSize;
                fileInfo.fileItemList.add(fileItem);
//                this.executor.submit(new DownloadWorker(this.mdownLoader, fileItem));
            } else {
                FileItem fileItem;
                int length;
                for(length = 0; length < count; ++length) {
                    fileItem = new FileItem();
                    fileItem.info = fileInfo;
                    fileItem.infoId = fileInfo._id;
                    fileItem.startPos = length * itemtLen;
                    fileItem._id = (long)length;
                    fileItem.endPos = fileItem.startPos + itemtLen - 1;
                    fileInfo.fileItemList.add(fileItem);
//                    this.executor.submit(new DownloadWorker(this.mdownLoader, fileItem));
                }

                length = fileInfo.fileSize % itemtLen;
                if (length > 0) {
                    fileItem = new FileItem();
                    fileItem.info = fileInfo;
                    fileItem.infoId = fileInfo._id;
                    fileItem.startPos = count * itemtLen;
                    fileItem.endPos = fileItem.startPos + length - 1;
                    fileInfo.fileItemList.add(fileItem);
//                    this.executor.submit(new DownloadWorker(this.mdownLoader, fileItem));
                }
            }
        }

    }

    private int getFileSize(FileInfo fileInfo) {
        HttpURLConnection connection = null;
        boolean var3 = true;

        int size;
        try {
            URL url = new URL(fileInfo.fileUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
//            connection.setRequestProperty("authorizations",github.nixo.com.MVP.Model.);
            size = connection.getContentLength();
        } catch (Exception var8) {
            var8.printStackTrace();
            size = 0;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

        return size;
    }

    private boolean createApkFile(FileInfo fileInfo) {
        boolean result = true;
        RandomAccessFile accessFile = null;

        try {
            if (fileInfo.fileSize > 0) {
                File file = new File(fileInfo.filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }

                accessFile = new RandomAccessFile(file, "rwd");
                accessFile.setLength((long)fileInfo.fileSize);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            result = false;
        } finally {
            if (accessFile != null) {
                try {
                    accessFile.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

        return result;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public int getSuitableSize() {
        return 1048576;
    }

    public void stopExcutor() {
        if (!this.executor.isShutdown()) {
            this.executor.shutdown();
        }

    }
}
