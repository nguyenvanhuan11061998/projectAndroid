package com.example.miniproiect2.Utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {                                                               //tạo dialog loading chặn người dùng thao tác với màn hình
    private static Dialog progessDialog;

    public static void show(Context context){
        dismiss();
        progessDialog = new ProgressDialog
                .Builder(context)
                .setMessage("Loading ...")
                .setCancelable(false)
                .create();
        progessDialog.show();
    }

    public static void dismiss() {
        if(progessDialog != null && progessDialog.isShowing()){
            progessDialog.dismiss();
        }
    }

}
