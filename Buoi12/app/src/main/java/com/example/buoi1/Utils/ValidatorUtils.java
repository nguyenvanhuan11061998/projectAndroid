package com.example.buoi1.Utils;

import android.widget.EditText;

public class ValidatorUtils {
    public static boolean isEmpty(EditText ...edts){

        boolean isEmpty = false;

        for (EditText edt: edts) {
            if (edt.getText().toString().isEmpty()){
                isEmpty = true;
                edt.setError("input empty");
            }
        }
        return isEmpty;
    }
}
