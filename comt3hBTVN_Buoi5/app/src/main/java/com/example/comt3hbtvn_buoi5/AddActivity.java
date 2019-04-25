package com.example.comt3hbtvn_buoi5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RESULT_ADD = 3;
    private EditText edtMaSV;
    private EditText edtHoTen;
    private EditText edtLop;
    private EditText edtDiem;
    private Button btnThem;
    private Button btnHuy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        initView();
    }

    private void initView() {
        edtMaSV = findViewById(R.id.edt_MaSV1);
        edtHoTen = findViewById(R.id.edt_HoTen1);
        edtLop = findViewById(R.id.edt_Lop1);
        edtDiem = findViewById(R.id.edt_Diem1);
        btnThem = findViewById(R.id.btn_Them);
        btnHuy = findViewById(R.id.btn_huyThem);
        btnThem.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Them:
                String maSV = edtMaSV.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String lop = edtLop.getText().toString();
                String diem = edtDiem.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_MASV,maSV);
                intent.putExtra(MainActivity.EXTRA_HOTEN,hoTen);
                intent.putExtra(MainActivity.EXTRA_LOP,lop);
                intent.putExtra(MainActivity.EXTRA_DIEM,diem);

                setResult(RESULT_ADD,intent);
                finish();
                break;
            case R.id.btn_huyThem:
                finish();
        }
    }
}
