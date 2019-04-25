package com.example.comt3hbtvn_buoi5;

import android.support.annotation.DrawableRes;

public class Face_SinhVien {
    private String hoten;
    private String maSV;
    private String lop;
    private int diem;

    public Face_SinhVien( String hoten, String maSV, String lop, int diem) {
        this.hoten = hoten;
        this.maSV = maSV;
        this.lop = lop;
        this.diem = diem;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getHoten() {
        return hoten;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getLop() {
        return lop;
    }

    public int getDiem() {
        return diem;
    }
}
