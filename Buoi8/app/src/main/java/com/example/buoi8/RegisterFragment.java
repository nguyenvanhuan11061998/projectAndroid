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
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLDisplay;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnOK;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register,container,false);
        Log.e(getClass().getName(),"onCreateView");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(getClass().getName(),"onActivityCreated");

        edtUserName = getActivity().findViewById(R.id.edt_register_userName);
        edtPassword = getActivity().findViewById(R.id.edt_register_Password);
        btnOK = getActivity().findViewById(R.id.btn_Ok);

        btnOK.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName(),"onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Ok:
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if(userName.isEmpty() || password.isEmpty()){
                    Toast.makeText(getContext(),"Please input info",Toast.LENGTH_LONG).show();
                    return;
                }

                MainActivity act = (MainActivity) getActivity();
                act.showFragment(act.getFmLogin());
                act.getFmLogin().setData(userName,password);
                break;
        }
    }
}
