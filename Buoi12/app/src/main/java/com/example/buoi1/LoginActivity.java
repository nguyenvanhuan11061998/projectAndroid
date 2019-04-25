package com.example.buoi1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.example.buoi1.Utils.ValidatorUtils;
import com.example.buoi1.databinding.ActivityLoginBinding;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {

    private ShareHelper helper;

    @Override
    protected void initActivity() {
        helper = new ShareHelper(this);
        String userName = helper.get(ShareHelper.Keys.USERNAME,null);
        String password = helper.get(ShareHelper.Keys.PASSWORD,null);

        if (!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(password)){
            startMainActivity();
            return;
        }
        buiding.btnOk.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        if (ValidatorUtils.isEmpty(buiding.edtUserName,buiding.edtPassword)){
            return;
        }
        helper.set(ShareHelper.Keys.USERNAME,buiding.edtUserName.getText().toString());
        helper.set(ShareHelper.Keys.PASSWORD,buiding.edtPassword.getText().toString());
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
