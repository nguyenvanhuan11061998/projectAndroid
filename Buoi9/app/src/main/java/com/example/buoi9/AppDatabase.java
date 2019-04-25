package com.example.buoi9;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Student.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //cấu hình Database
    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "Student Manager").
                    allowMainThreadQueries().
                    build();
        }
        return instance;
    }

    public abstract StudentDao getStudentDao();


}
