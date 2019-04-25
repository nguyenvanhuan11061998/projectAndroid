package com.example.buoi9;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity     //thêm vào , tự động nhận biết đây là một bảng
public class Student {

    @PrimaryKey(autoGenerate = true)                                                             //Sử dụng id làm khóa chính, tự động tăng id
    private int id;

    @ColumnInfo(name = "HoTen")
    private String name;

    @ColumnInfo(name = "Lop")
    private String classRoom;

    @ColumnInfo(name = "Diem")
    private float score;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public float getScore() {
        return score;
    }
}
