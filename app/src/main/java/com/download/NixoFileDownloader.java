//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.download;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

public class NixoFileDownloader {
    private static final String TAG = NixoFileDownloader.class.getSimpleName();
    static int THREAD_COUNT = 10;
    private TaskCreatorThread mFileAdder;
    private DownloadListener mObserver;
    private Application context;
    private String mFolder = Environment.getExternalStorageDirectory() + "/fileDownload/";
    private String mExtend = ".apk";
    private TaskList mTaskList;
    private boolean running;
    private static NixoFileDownloader NixoFileDownloader;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (NixoFileDownloader.this.netWorkAble()) {
                LogUtil.d("Thread", "mBroadcastReceiver 说：终于有网络了。");
                NixoFileDownloader.NixoFileDownloader.onNetWorkResume();
            }

        }
    };

    private NixoFileDownloader(Application context) {
        this.context = context;
        this.init();
        this.registerNetStateReceiver();
    }

    public boolean isRunning() {
        return this.running;
    }

    public void registerNetStateReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.context.registerReceiver(this.mBroadcastReceiver, filter);
    }

    public void unregisterNetStateReceiver() {
        if (null != this.mBroadcastReceiver) {
            this.context.unregisterReceiver(this.mBroadcastReceiver);
        }

    }

    public void setFilePath(String path) {
        this.mFolder = path;
        this.mFileAdder.setFolder(path);
        this.createFolder();
    }

    public void setExtend(String extend) {
        this.mExtend = extend;
        this.mFileAdder.setExtend(extend);
    }

    private void onNetWorkResume() {
        this.resume();
    }

    private void resume() {
        TaskList var1 = this.mTaskList;
        synchronized(this.mTaskList) {
            this.mTaskList.notifyAll();
        }
    }

    public static NixoFileDownloader getInstance(Application context) {
        if (NixoFileDownloader == null) {
            NixoFileDownloader = new NixoFileDownloader(context);
        }

        return NixoFileDownloader;
    }

    private void init() {
        this.running = true;
        this.mTaskList = new TaskList();
        this.createFolder();
//        this.mFileAdder = new NixoTaskCreatorThread(this, this.mTaskList);
        this.mFileAdder.setFolder(this.mFolder);
        this.mFileAdder.setExtend(this.mExtend);
        this.mFileAdder.start();
    }

    private void createFolder() {
        File file = new File(this.mFolder);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    public synchronized void setDownloadObserver(DownloadListener observer) {
        this.mObserver = observer;
    }

    synchronized void update(String fileUrl, String file, int arg1) {
        FileInfo info = this.mTaskList.getFileInfoByUrl(fileUrl);
        LogUtil.d(TAG, "info = " + info);
        if (info != null) {
            info.completeSize += arg1;
            if (this.mObserver != null && info.completeSize > 0) {
                Message msg = Message.obtain();
                msg.what = 1;
                msg.arg1 = info.completeSize;
                msg.arg2 = info.fileSize;
                msg.obj = info.fileUrl + "," + file;
                this.mObserver.sendMessage(msg);
            }

            if (info.fileSize == info.completeSize) {
                info.state = 5;
            }
        } else {
            LogUtil.e(TAG, "has delete");
        }

    }

    synchronized void error(String fileUrl, int state) {
        FileInfo info = this.mTaskList.getFileInfoByUrl(fileUrl);
        if (info != null) {
            switch(state) {
                case 100:
                case 101:
                case 102:
                case 103:
                    info.state = 7;
                    this.delete(info);
                    break;
                case 104:
                    info.state = 7;
            }
        }

        if (this.mObserver != null) {
            Message msg = Message.obtain();
            msg.what = state;
            msg.arg1 = state;
            msg.obj = fileUrl;
            this.mObserver.sendMessage(msg);
        }

        this.running = false;
        this.mFileAdder.stopExcutor();
    }

    public synchronized void delete(FileInfo info) {
        if (info != null) {
            this.mTaskList.delete(info);
        }
    }

    public void addFile(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (!this.netWorkAble()) {
                this.error(url, 104);
            } else {
                Log.d(TAG, "(0)apkMap.containsKey(fileUrl);");
                if (!this.mTaskList.hasContain(url)) {
                    this.mTaskList.addUrl(url);
                    this.running = true;
                    this.resume();
                }
            }
        }
    }

    public void release() {
        NixoFileDownloader.unregisterNetStateReceiver();
        this.running = false;
        this.context = null;
        NixoFileDownloader = null;
    }

    public boolean netWorkAble() {
//        ConnectivityManager connectMgr = (ConnectivityManager)this.context.getSystemService("connectivity");
//        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(0);
//        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(1);
//        return mobNetInfo.isConnected() || wifiNetInfo.isConnected();
        return true;
    }
}
