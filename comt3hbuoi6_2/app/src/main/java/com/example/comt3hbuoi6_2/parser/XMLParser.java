package com.example.comt3hbuoi6_2.parser;



import com.example.comt3hbuoi6_2.model.News;
import com.example.comt3hbuoi6_2.utils.Constances;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLParser extends DefaultHandler{
    private ArrayList<News> arrNews = new ArrayList<>();
    private StringBuilder builder;
    private News item = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        builder = new StringBuilder();
        if (qName.equals(Constances.ITEM)){
            item = new News();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (item == null) return;
        switch (qName){
            case Constances.TITLE:
                item.setTitle(builder.toString());
                break;
            case Constances.DESC:
                String s = "title='";
                String value = builder.toString();
                int index = value.indexOf(s) + s.length();
                value = value.substring(index);
                String desc = value.substring(0, value.indexOf("'"));
                s = "src='";
                index = value.indexOf(s)+ s.length();
                value = value.substring(index);
                String image = value.substring(0, value.indexOf("'"));
                item.setDesc(desc);
                item.setImage(image);
                break;
            case Constances.PUB_DATE:
                item.setPubDate(builder.toString());
                break;
            case Constances.LINK:
                item.setLink(builder.toString());
                break;
            case Constances.ITEM:
                arrNews.add(item);
                break;
        }
    }

    public ArrayList<News> getArrNews() {
        return arrNews;
    }
}
