package com.example.comt3hbuoi_6;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.comt3hbuoi_6.Adapter.NewsAdapter;
import com.example.comt3hbuoi_6.Model.News;
import com.example.comt3hbuoi_6.Parser.XMLParser;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements Runnable{
    private static final int WHAT_UPDATE_LIST = 1;
    private ArrayList<News> data = new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView lvView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Thread t = new Thread(this);
        t.start();

    }

    private void initView() {
        lvView = findViewById(R.id.lv_News);
        adapter = new NewsAdapter(this,data);
        lvView.setAdapter(adapter);
    }

    @Override
    public void run() {
        try {

            XMLParser p = new XMLParser();                                                     //đối tượng vừa định nghĩa ra
            SAXParserFactory factory  = SAXParserFactory.newInstance();                       //cho tốc độ nhanh hơn
            SAXParser parser = factory.newSAXParser();
            String api = "https://www.24h.com.vn/upload/rss/trangchu24h.rss";
            parser.parse(api,p);

            Message message = new Message();
            message.what = WHAT_UPDATE_LIST;
            message.obj = p.getArrNews();                                                               //obj: chứa các kiểu dữ liệu
            handler.sendMessage(message);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == WHAT_UPDATE_LIST){
                data.clear();
                data.addAll((Collection<? extends News>) msg.obj);
                adapter.notifyDataSetChanged();
            }
        }
    };
}
