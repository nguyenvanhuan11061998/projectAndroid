package com.example.buoi82.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.buoi82.R;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;

    public void setData(String userName, String Password){
        this.edtUserName.setText(userName);
        this.edtPassword.setText(Password);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        edtUserName = findViewById(R.id.edt_UserName_Login);
        edtPassword = findViewById(R.id.edt_Password_Login);
        btnLogin = findViewById(R.id.btn_Login);
        btnRegister = findViewById(R.id.btn_Register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Login:
                break;
            case R.id.btn_Register:
                getParent().showFragment(getParent().getFmRegister());
        }
    }
}
