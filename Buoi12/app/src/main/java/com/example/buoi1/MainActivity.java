package com.example.buoi1;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.buoi1.View.DialogLogout;
import com.example.buoi1.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener, DialogLogout.CallbackLogout {
    private DialogLogout dialog;

    private ShareHelper helper;

    @Override
    protected void initActivity() {

        helper = new ShareHelper(this);

        String userName = helper.get(ShareHelper.Keys.USERNAME,"");
        String passWord = helper.get(ShareHelper.Keys.PASSWORD,"");
        buiding.tvInfor.setText(userName + ", " + passWord);
        buiding.btnLogout.setOnClickListener(this);

        dialog = new DialogLogout(this);
        dialog.setCallback(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        dialog.show();
    }

    @Override
    public void onClickOK() {
        helper.remote(ShareHelper.Keys.USERNAME,ShareHelper.Keys.PASSWORD);
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
