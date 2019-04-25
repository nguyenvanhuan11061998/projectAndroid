package com.example.buoi72_file;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAsync extends AsyncTask<String,Integer,String> {                               //tải file trên mạng về

    private DownloadCallBack callBack;

    public DownloadAsync(DownloadCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];                                                               //truyền vào link và path để lưu file
        String path = strings[1];

        try {
            File f = new File(path);                                                        //khởi tạo một đối tượng file với path
            f.getParentFile().mkdir();                                                       //tạo folder để chứa file
            f.createNewFile();                                                              //tạo file
            FileOutputStream out = new FileOutputStream(f);                                 //đối tượng để ghi file
            URL url = new URL(link);                                                        //tạo kết nối tới internet với đường link truyền vào
            URLConnection connection = url.openConnection();                                //mở kết nối tới intenet
            InputStream in = connection.getInputStream();                                   //đọc các byte dữ liệu
            int total = connection.getContentLength();                                      //dung lượng file
            int totalSave = 0;                                                              //dung lượng đã download được
            byte[] b = new byte[1024];                                                      //mảng byte mỗi lần đọc
            int count = in.read(b);                                                         //tạo biến bằng dung lượng mỗi lần đọc
            while (count != -1){
                totalSave += count;                                                         //cộng dung lượng với biến sau mỗi lần tải được
                float percent = (float) totalSave /total;                                   //chuyển thành phần trăm để hiển thị lên progessBar
                publishProgress((int) (percent*100));                                       //chạy progessBar
                out.write(b,0,count);                                                   //ghi dữ liệu từ mảng b vào file, từ vị trí số 0 tới hết mảng b
                count = in.read(b);
            }
            in.close();                                                                     //đóng stream khi download xong
            out.close();

            return f.getPath();                                                         //trả về đường dẫn tới file
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {                                 //phương thức hiển thị tiến trình của quá trình load file
        super.onProgressUpdate(values);
        callBack.updatePercent(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {                                             //kết thúc phương thức trên sẽ trả về kết quả vào đây(gọi một hàm download finish ra)
        super.onPostExecute(s);
        callBack.downloadFinish(s);
    }

    public interface DownloadCallBack{
        void updatePercent(int percent);
        void downloadFinish(String path);
    }
}
