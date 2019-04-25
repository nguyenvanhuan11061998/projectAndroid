package com.example.buoi92;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Student.class},version = 1)               //entities: truyền các bảng vào cơ sở dữ liệu
public abstract class AppDatabase extends RoomDatabase {        //cơ sở dữ liệu của app
    private static AppDatabase instance = null;                         //tạo cơ sở dữ liệu tên instance

    public static AppDatabase getInstance(Context context){                                         //tạo một phiên bản, ánh xạ của cơ sở dữ liệu để thao tác
        if (instance == null){
            instance = Room.databaseBuilder(context                                                 //xây dựng ánh xạ tên instance
            ,AppDatabase.class,"student_manager")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StudentDao getStudentDao();                                                     //phương thức gọi đến đối tượng StudentDao để gọi câu lệnh truy vấn với CSDL

}
