package com.example.buoi132;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class SystemData {

    private ContentResolver resolver;                                                               //đọc dữ liệu từ ứng dụng khác(Đọc từ bộ nhớ)

    public SystemData(Context context) {
        resolver = context.getContentResolver();
    }

    public void getData(){
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            for (int i = 0; i < cursor.getColumnCount(); i++) {
//                Log.e(cursor.getColumnName(i),cursor.getString(i)+"");
            }
        }

    }
}
