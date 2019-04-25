package com.example.buoi8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private String userName ="";
    private String password = "";

    @Nullable
    @Override
    //đính layout vào đây, chuyển đổi thành dạng view
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        Log.e(getClass().getName(),"onCreateView");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(getClass().getName(),"onActivityCreated");

        edtUserName = getActivity().findViewById(R.id.edt_userName);
        edtPassword = getActivity().findViewById(R.id.edt_Password);
        btnLogin = getActivity().findViewById(R.id.btn_Login);
        btnRegister = getActivity().findViewById(R.id.btn_Register);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        edtUserName.setText(userName);
        edtPassword.setText(password);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName(),"onDestroy");
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Login:
                break;
            case R.id.btn_Register:
                MainActivity act = (MainActivity)getActivity();
                act.showFragment(act.getfmRegister());
                break;
        }
    }

    public void setData(String userName, String Password){
//        edtUserName.setText(userName);
//        edtPassword.setText(Password);
    }
}
