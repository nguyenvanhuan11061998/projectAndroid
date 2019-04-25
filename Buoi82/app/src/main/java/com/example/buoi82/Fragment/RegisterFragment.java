package com.example.buoi82.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buoi82.R;

public class RegisterFragment extends BaseFragment implements View.OnClickListener {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnOk;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edt_UserName);
        edtPassword = findViewById(R.id.edt_Password);
        btnOk = findViewById(R.id.btn_OK);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_OK:
                if (edtUsername.getText().toString().isEmpty() && edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Input is Empty",Toast.LENGTH_LONG).show();
                }
                getParent().getFmLogin().setData(edtUsername.getText().toString(),edtPassword.getText().toString());
                getParent().showFragment(getParent().getFmLogin());
        }
    }
}
