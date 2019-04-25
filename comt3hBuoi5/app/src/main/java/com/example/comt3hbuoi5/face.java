package com.example.comt3hbuoi5;

import android.support.annotation.DrawableRes;

public class face {
    private @DrawableRes int face;                  //@drawbleRes :  kiểu dữ liệu của ảnh khi gợi lên là kiểu int, cho thêm drawbleRes sẽ ràng buộc làm giá trị chặt chẽ, tránh ngoại lệ người dùng đưa vào
    private String name;

    public face(@DrawableRes int face, String name) {
        this.face = face;
        this.name = name;
    }

    public int getFace() {
        return face;
    }

    public String getName() {
        return name;
    }
}
