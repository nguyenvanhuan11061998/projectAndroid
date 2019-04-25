package com.example.buoi9;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

//thực hiện các truy vấn trên bảng dữ liệu, mội class có một Dao để truy vấn

@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE Diem >= :score")
    List<Student> getbyScore(int score);

    @Insert
    void insert(Student...students);

    @Update
    void update(Student...students);

    @Delete
    void delete(Student...students);


}
