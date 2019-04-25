package com.example.miniproiect2.Async;

import android.os.AsyncTask;

import com.example.miniproiect2.Model.News;
import com.example.miniproiect2.Parser.XMLParser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


//tạo thread để parser tin tức

public class XMLAsync extends AsyncTask<String,Void, List<News>> {                             //biến đầu (params) truyền vào từ khóa search, biến hai (progess) thực thi tiến trình không cần, biến 3 (result) trả về list danh sách tin tức

    private ParserXMLCallback callback;

    public XMLAsync(ParserXMLCallback callback) {
        this.callback = callback;
    }

    @Override
    //strings là kiểu truyền vào một list các biến kiểu String
    protected List<News> doInBackground(String... strings) {                                   //Hàm thực thi tiến trình, xử lý link truyền vào
        String api = "https://news.google.de/news/feeds?pz=1&cf=vi_vn&ned=vi_vn&hl=vi_vn&q=";
        String keySearch = strings[0];                                                              //Vị trí 0 trong strings truyền vào link
        api = api + keySearch;

        try {
            XMLParser xmlParser = new XMLParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(api,xmlParser);
            return xmlParser.getArr();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<News> news) {                                            //Hàm chạy đưa ra kết quả trả về sau khi hàm tiến trình trên kết thúc
        super.onPostExecute(news);
        callback.onParserFinish(news);

    }

    public interface ParserXMLCallback{
        void onParserFinish(List<News> arr);
    }
}
