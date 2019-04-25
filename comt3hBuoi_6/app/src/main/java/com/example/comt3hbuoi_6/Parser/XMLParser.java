package com.example.comt3hbuoi_6.Parser;

import com.example.comt3hbuoi_6.Model.News;
import com.example.comt3hbuoi_6.util.Constances;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLParser extends DefaultHandler {                                 //DefaultHandler:  pars xml lần lượt các phần tử từ đầu đến cuối
    private ArrayList<News> arrNews = new ArrayList<>();
    private StringBuilder builder;                                                                          //chứa data của thẻ hiện tại
    private News item = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {                       //thẻ mở
        super.startElement(uri, localName, qName, attributes);
        builder = new StringBuilder();
        if(qName.equals(Constances.ITEM)){                                                                              //Khi vào thẻ mở bất kì, khởi tạo builder mới,
            item = new News();                                                                                          //khởi tạo mới item
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {                                              //nội dung trong thẻ
        super.characters(ch, start, length);
        builder.append(ch,start,length);                                                                             //lấy nội dung đưa vào builder đưa về kiểu String
    }

    public ArrayList<News> getArrNews() {
        return arrNews;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {                                     //thẻ đóng
        super.endElement(uri, localName, qName);
        if(item == null) return;
        switch (qName){
            case Constances.TITLE:
                item.setTitle(builder.toString());
                break;
            case Constances.DESC:
                break;
            case  Constances.PUB_DATE:
                item.setPubDate(builder.toString());
                break;
            case  Constances.LINK:
                item.setLink(builder.toString());
                break;
            case Constances.ITEM:
                arrNews.add(item);
                break;
        }

    }
}
