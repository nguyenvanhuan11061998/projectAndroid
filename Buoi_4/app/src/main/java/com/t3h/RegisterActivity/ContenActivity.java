package com.t3h.RegisterActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buoi_4.R;

import java.io.FileNotFoundException;

public class ContenActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PICK_IMAGE = 1;
    private TextView tvContent;
    private EditText edtBrowser;
    private Button btnCall;
    private Button btnBrowser;
    private Button btnPick;
    private ImageView imgPick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenr);
        initView();
    }

    private void initView() {
        edtBrowser = findViewById(R.id.edt_browser);
        btnCall = findViewById(R.id.btn_call);
        btnBrowser = findViewById(R.id.btn_browser);
        btnPick = findViewById(R.id.btn_pick);
        imgPick = findViewById(R.id.im_pick);
        btnPick.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnCall.setOnClickListener(this);


        tvContent = findViewById(R.id.tv_infor);
        Intent intent = getIntent();
        String username = intent.getStringExtra(RegisterActivity.EXTRA_USERNAME);
        String password = intent.getStringExtra(RegisterActivity.EXTRA_PASSWORD);
        tvContent.setText("Hi "+username+" , "+password);
    }

    @Override
    public void onClick(View v) {
        String content = edtBrowser.getText().toString();
        switch (v.getId()){
            case R.id.btn_browser:
                Intent browser = new Intent(Intent.ACTION_VIEW);
                browser.setData(Uri.parse(content));
                startActivity(browser);
                break;
            case R.id.btn_call:
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:"+content));            // tel: tiền tố mặc định
                startActivity(call);
                break;
            case R.id.btn_pick:
                Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");                   //lọc hình ảnh trong hệ thống để lấy ra
                startActivityForResult(pick,REQUEST_PICK_IMAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PICK_IMAGE && requestCode == RESULT_OK){
            Uri uri = data.getData();                                                   //lấy link hình ảnh
            try {
                Bitmap b = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));          //đọc bộ nhớ ngoài điện thoại để lấy ảnh
                imgPick.setImageBitmap(b);                                                                  //lấy hình ảnh
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
