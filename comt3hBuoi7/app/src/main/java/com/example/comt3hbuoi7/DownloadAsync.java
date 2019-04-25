package com.example.comt3hbuoi7;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAsync extends AsyncTask<String,Integer,String> {
    private downloadCallBack callBack;

    public DownloadAsync(downloadCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {                                                                    //String... strings: truyền tham số kiểu param, thẻ hiện số phần tử
        String link = strings[0];
        String path = strings[1];
//        String extention = strings[2];                                                                                      //kiểu file
        try {
            File f = new File(path);                                                                                            //file để lưu
            f.getParentFile().mkdir();
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            byte[] b = new byte[1024];
            int count  = in.read(b);
            while (count != 1){
                out.write(b,0,count);
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
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBack.downloadFinish(s);
    }

    public interface downloadCallBack{
        void updatePercent(int percent);
        void downloadFinish(String path);
    }


    //ba Void tương đương với kiểu dữ liệu truyền vào tương ứng với ba hàm
//    @Override
//    protected Void doInBackground(Void... voids) {                                                                                  // tương đương run của thread,chạy trên thread mới
//        publishProgress();                                                                                                      //gọi onProgressUpdate
//        return null;
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {                                                                               //cập nhật giao diện trong khi thread thực hiện,chạy trên main thread
//        super.onProgressUpdate(values);
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {                                                                                      //khi kết thúc thread sẽ gọi tới hàm này, chạy trên main thread
//        super.onPostExecute(aVoid);
//    }
}
