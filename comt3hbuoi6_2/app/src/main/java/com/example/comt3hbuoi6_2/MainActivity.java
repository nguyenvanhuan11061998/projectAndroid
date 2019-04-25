package com.example.comt3hbuoi6_2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;


import com.example.comt3hbuoi6_2.adapter.NewsAdapter;
import com.example.comt3hbuoi6_2.model.News;
import com.example.comt3hbuoi6_2.parser.XMLParser;


import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements Runnable {
    private static final int WHAT_UPDATE_LIST = 1;
    private ArrayList<News> data = new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView lvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Thread t = new Thread(this);
        t.start();
    }

    private void initViews() {
        lvNews = findViewById(R.id.lv_news);
        adapter = new NewsAdapter(this, data);
        lvNews.setAdapter(adapter);
    }

    @Override
    public void run() {
        try {
            XMLParser p = new XMLParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            String api = "https://www.24h.com.vn/upload/rss/bongda.rss";
            parser.parse(api, p);
            // send data to main thread
            Message message = new Message();
            message.what = WHAT_UPDATE_LIST;
            message.obj = p.getArrNews();
            handler.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WHAT_UPDATE_LIST){
                data.clear();
                data.addAll((Collection<? extends News>) msg.obj);
                adapter.notifyDataSetChanged();
            }
        }
    };
}
