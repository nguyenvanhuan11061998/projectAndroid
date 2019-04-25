package com.example.buoi1;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareHelper {                                                                          //lưu trữ dữ liệu đến khi xóa ứng dụng mới thôi

    enum Keys{                                                                                      //Keys chỉ nhận giá trị là USERNAME,PASSWORD
        USERNAME,
        PASSWORD
    }

    private SharedPreferences share;

    public ShareHelper(Context context) {
        share = context.getSharedPreferences("SharedPreferences"
                ,Context.MODE_PRIVATE);                                                             //lấy ra đối tượng SharePrefrences, name: tên File sẽ được tạo để lưu File, Mode Private: chỉ ứng dụng mới có quyền truy cập
    }

    public String get(Keys k, String defaultValues){                                                //
        return share.getString(k.toString(),defaultValues);
    }

    public void set(Keys k, String values){
        SharedPreferences.Editor editor = share.edit();
        editor.putString(k.toString(),values);
        editor.commit();                                                                            //lưu giữ liệu
    }

    public void remote(Keys ...keys){
        SharedPreferences.Editor editor = share.edit();
        for (Keys k:keys) {
            editor.remove(k.toString());
        }
        editor.commit();
    }
}
