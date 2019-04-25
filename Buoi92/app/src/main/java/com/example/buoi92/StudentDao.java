package com.example.buoi92;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StudentDao {                                                   //thực hiện các truy vấn dữ liệu trên bảng student, mỗi bảng có một Dao
    @Query("SELECT * FROM Student")
    List<Student> getAll();                                                     //trả về một list dữ sinh viên

    @Query("SELECT * FROM Student WHERE Score >= :score")
    List<Student> getByScore(int score);                                        //trả về một list sinh viên

    @Insert
    void insert(Student...students);

    @Update
    void update(Student...students);

    @Delete
    void delete(Student...students);
}
