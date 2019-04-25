package com.example.comt3hbtvn_buoi5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class  EditActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RESULT_EDIT = 2;
    private EditText edtMaSV;
    private EditText edtHoTen;
    private EditText edtLop;
    private EditText edtDiem;
    private Button btnCapNhat;
    private Button btnHuy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        initView();
    }

    private void initView() {
        edtMaSV = findViewById(R.id.edt_MaSV);
        edtHoTen = findViewById(R.id.edt_HoTen);
        edtLop = findViewById(R.id.edt_Lop);
        edtDiem = findViewById(R.id.edt_Diem);
        btnCapNhat = findViewById(R.id.btn_capNhatEdit);
        btnHuy = findViewById(R.id.btn_huyEdit);

        Intent intent = getIntent();

        String maSV = intent.getStringExtra(MainActivity.EXTRA_MASV);
        String hoTen = intent.getStringExtra(MainActivity.EXTRA_HOTEN);
        String lop = intent.getStringExtra(MainActivity.EXTRA_LOP);
        String diem = intent.getStringExtra(MainActivity.EXTRA_DIEM);

        edtMaSV.setText(maSV);
        edtHoTen.setText(hoTen);
        edtLop.setText(lop);
        edtDiem.setText(diem);
        btnCapNhat.setOnClickListener(this);
        btnHuy.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_capNhatEdit:
                    String maSV = edtMaSV.getText().toString();
                    String hoTen = edtHoTen.getText().toString();
                    String lop = edtLop.getText().toString();
                    String diem = edtDiem.getText().toString();

                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.EXTRA_MASV,maSV);
                    intent.putExtra(MainActivity.EXTRA_HOTEN,hoTen);
                    intent.putExtra(MainActivity.EXTRA_LOP,lop);
                    intent.putExtra(MainActivity.EXTRA_DIEM,diem);

                    setResult(RESULT_EDIT,intent);                                      //hàm trả kết quả về hàm đã gọi đến nó
                    finish();                                                           //thoát class
                break;
            case R.id.btn_huyEdit:
                finish();
        }
    }
}
