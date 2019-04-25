package com.example.buoi1;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//các ActivityBuiding đều extend từ ViewDataBuiding
public abstract class BaseActivity <BD extends ViewDataBinding> extends AppCompatActivity {

    protected  BD buiding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buiding = DataBindingUtil.setContentView(this,getLayoutId());

        initActivity();
    }

    protected abstract void initActivity();

    protected abstract int getLayoutId();
}
