package com.t3h.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buoi_4.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_USERNAME = "extra.user.name";
    public static final String EXTRA_PASSWORD = "extra.password";

    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
    }

    private void initView(){
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String userName = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        if(userName.isEmpty() || password.isEmpty()){
            Toast.makeText(this,R.string.data_empty,Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_USERNAME,userName);           //Truyền dữ liệu vào để đưa quay lại về main Activity, chỉ truyền được kiểu dữ liệu nguyên thủy
        intent.putExtra(EXTRA_PASSWORD,password);           //vế 1: key để truyền dữ liệu
        setResult(RESULT_OK,intent);                        //resultCode: trả về kết quả thực hiện khi nhấn register. OK: thành công
        finish();                                           // destroy activity hiện tại để bật activity cũ
    }
}
