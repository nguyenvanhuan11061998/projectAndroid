package com.example.miniproiect2.Async;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAsync extends AsyncTask<String,Void,String> {
    private DownloadCallBack callBack;

    public DownloadAsync(DownloadCallBack callBack) {
        this.callBack = callBack;
    }

    String path1;

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        String path = strings[1];

        try {
            File f = new File(path);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            byte[] b = new byte[1024];
            int count = in.read(b);
            while (count != -1){
                out.write(b,0, count);
                count = in.read(b);
            }
            in.close();
            out.close();
            return f.getPath();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBack.downloadFinish(s);
    }

    public interface DownloadCallBack{
        void downloadFinish(String path);
    }

    public String getPath1() {
        return path1;
    }
}
