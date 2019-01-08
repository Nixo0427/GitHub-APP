package github.nixo.com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class DownLoadUtils {

    private String s;

    /**
     * 保存文件
     */
    public String saveFiles(ResponseBody responseBody, String path,String fileName) {

        InputStream inputStream = null;


        FileOutputStream fileOutputStream = null;

        byte[] buffer = new byte[2048];

        int len;

        File file = new File(path);

        if (!file.exists()) {

            file.mkdirs();
        }

        try {
            inputStream = responseBody.byteStream();
            s = new String(responseBody.bytes());
            fileOutputStream = new FileOutputStream(file+"/"+fileName);
            while ((len = inputStream.read(buffer)) != -1) {

                fileOutputStream.write(buffer, 0, len);
            }

            inputStream.close();

            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }




}
