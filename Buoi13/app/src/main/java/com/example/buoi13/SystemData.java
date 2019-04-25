package com.example.buoi13;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.buoi13.Model.Song;

import java.util.ArrayList;

public class SystemData {

    private ContentResolver resolver;

    public SystemData(Context context) {
        resolver = context.getContentResolver();
    }

    public ArrayList<Song> getData(){                                                                          //có một danh sách , con trỏ sẽ trỏ vào từng vị trí data rồi đọc data của từng vị trí ra,đến hết thì thôi

        ArrayList<Song> arr = new ArrayList<>();

        //contact : ContactsContract.Data.CONTENT_URI
        //callog : Calllog.CONTENT_URI
        //sms: Telephone.Sms.CONTENT_URI ...
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        ,null,null,null,null);                                                               //tương đương với câu lệnh select(đượng dẫn, các cột muốn lấy(null = lấy hết), where, order by)

        cursor.moveToFirst();                                                                       //đưa con trỏ vào vị trí đầu tiên

        int indexData = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);                                                     //tra về 0 hoặc 1
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
        int indexDuration = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
        int indexSize = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE);
        int indexAlbum = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);

        while (cursor.isAfterLast() == false){
//            for (int i = 0; i < cursor.getColumnCount(); i++) {
//                Log.e(cursor.getColumnName(i),cursor.getString(i)+"");
//            }
//            Log.e("----------","========================");
            String data = cursor.getString(indexData);
            String title = cursor.getString(indexTitle);
            long duration = cursor.getLong(indexDuration);
            long size = cursor.getLong(indexSize);
            String album = cursor.getString(indexAlbum);
            String artist = cursor.getString(indexArtist);

            Song song = new Song(data,duration,size,title,album,artist);
            arr.add(song);

            cursor.moveToNext();                                                                    //đọc dòng tiếp theo
        }
        return  arr;

    }

}
