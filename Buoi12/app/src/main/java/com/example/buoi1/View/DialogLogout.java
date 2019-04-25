package com.example.buoi1.View;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.example.buoi1.R;
import com.example.buoi1.databinding.UiDialogBinding;

public class DialogLogout extends Dialog implements View.OnClickListener {
    private CallbackLogout callback;

    public void setCallback(CallbackLogout callback) {
        this.callback = callback;
    }

    private UiDialogBinding binding;

    public DialogLogout(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.ui_dialog,null,false);
        setContentView(binding.getRoot());

        binding.btnYes.setOnClickListener(this);
        binding.btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_No:
                dismiss();
                break;

            case R.id.btn_Yes:
                dismiss();
                if (callback != null){
                    callback.onClickOK();
                }
                break;
        }
    }

    public interface CallbackLogout{
        void onClickOK();
    }
}
