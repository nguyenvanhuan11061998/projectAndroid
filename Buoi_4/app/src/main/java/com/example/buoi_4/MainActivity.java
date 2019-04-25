package com.example.buoi_4;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.t3h.RegisterActivity.ContenActivity;
import com.t3h.RegisterActivity.RegisterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_REGISTER = 1;
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;


    public void initView(){
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_ok);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {               //tự động nhận dữ liệu trả về từ activity khác
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_REGISTER){
            if(resultCode == RESULT_OK){
                String userName = data.getStringExtra(RegisterActivity.EXTRA_USERNAME);
                String password = data.getStringExtra(RegisterActivity.EXTRA_PASSWORD);
                edtUserName.setText(userName);
                edtPassword.setText(password);
            }else{
                Toast.makeText(this,R.string.register_cancelled,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:{
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if(userName.isEmpty() || password.isEmpty()){
                    Toast.makeText(this,R.string.data_empty,Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent login = new Intent(this, ContenActivity.class);
                login.putExtra(RegisterActivity.EXTRA_USERNAME,userName);
                login.putExtra(RegisterActivity.EXTRA_PASSWORD,password);
                startActivity(login);
                finish();

            }
                break;
            case R.id.btn_register:{
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent,REQUEST_REGISTER);           // mở activity hai chiều, requesCode: đánh dấu luồng dữ liêu trả về của Activity nào, yêu cầu kết quả trả về.
            }
                break;
        }
    }
}
