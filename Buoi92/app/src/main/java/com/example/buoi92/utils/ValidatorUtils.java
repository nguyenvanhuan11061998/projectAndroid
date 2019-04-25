package com.example.buoi92.utils;

import android.widget.EditText;

public class ValidatorUtils  {
    public static boolean isInputEmpty(EditText...edts){                                       //kiểm tra các edit text đưa vào có rỗng hay không
        boolean result = false;
        for (EditText edt : edts) {
            if (edt.getText().toString().isEmpty()){
                result = true;
                edt.setError("Input is Empty");                                                // text rỗng return true
            }
        }
        return result;
    }
}
