package com.example.buoi9.Utils;

import android.widget.EditText;

public class ValidatorUtil {
    public static  boolean isInputEmpty(EditText... edts){
        boolean resutlt = false;
        for(EditText edt : edts){
            if(edt.getText().toString().isEmpty() ){
                resutlt = true;
                edt.setError("Input is empty");
            }
        }
        return resutlt;
    }
}
