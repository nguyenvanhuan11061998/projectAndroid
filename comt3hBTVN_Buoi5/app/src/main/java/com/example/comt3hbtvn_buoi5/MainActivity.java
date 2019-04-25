package com.example.comt3hbtvn_buoi5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FaceAdapter.FaceItemListener,View.OnClickListener{

    public static final String EXTRA_MASV = "extra.Ma.SV";
    public static final String EXTRA_HOTEN = "extra.ho.ten";
    public static final String EXTRA_LOP = "extra.lop";
    public static final String EXTRA_DIEM = "extra.diem";
    private static final int REQUEST_MAIN = 1;
    private ArrayList<Face_SinhVien> list_SV;
    private FaceAdapter faceAdapter;
    private RecyclerView lvSinhVien;
    private int viTri;
    private FloatingActionButton fabAddSV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initView1();
    }

    private void initView1() {
        fabAddSV=findViewById(R.id.fab_Add);
        fabAddSV.setOnClickListener(this);
    }

    private void initData() {
        list_SV = new ArrayList<>();
        list_SV.add(new Face_SinhVien("Nguyễn Văn Huân","1651060685","58TH4",10));
        list_SV.add(new Face_SinhVien("Nguyễn Anh Tú","1651546654","58TH4",10));
        list_SV.add(new Face_SinhVien("Vũ Bá Ngọc Minh","1651066547","58TH1",10));
        list_SV.add(new Face_SinhVien("Nguyễn Tuấn Đạt","1651035345","58TH2",6));
        list_SV.add(new Face_SinhVien("Hoàng Ngọc Hòa","1651064566","58TH3",7));
        list_SV.add(new Face_SinhVien("Nguyễn Văn Sơn","1651045654","58TH1",8));
    }

    private void initView() {
        lvSinhVien = findViewById(R.id.lv_SV);
        faceAdapter = new FaceAdapter(this,list_SV);
        lvSinhVien.setAdapter(faceAdapter);
        faceAdapter.setItemListener(this);
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {           //Hàm chuyên nhận kết quả từ các liên kết hai chiều
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_MAIN){
            if(resultCode == EditActivity.RESULT_EDIT){
                String maSV = data.getStringExtra(MainActivity.EXTRA_MASV);
                String hoTen = data.getStringExtra(MainActivity.EXTRA_HOTEN);
                String lop = data.getStringExtra(MainActivity.EXTRA_LOP);
                String diem = data.getStringExtra(MainActivity.EXTRA_DIEM);

                list_SV.remove(viTri);

                list_SV.add(new Face_SinhVien(hoTen,maSV,lop,Integer.parseInt(diem)));
                faceAdapter.notifyDataSetChanged();
            }else if(resultCode == AddActivity.RESULT_ADD){
                String maSV = data.getStringExtra(MainActivity.EXTRA_MASV);
                String hoTen = data.getStringExtra(MainActivity.EXTRA_HOTEN);
                String lop = data.getStringExtra(MainActivity.EXTRA_LOP);
                String diem = data.getStringExtra(MainActivity.EXTRA_DIEM);

                list_SV.add(new Face_SinhVien(hoTen,maSV,lop,Integer.parseInt(diem)));
                faceAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(int i) {
        String maSV = list_SV.get(i).getMaSV();
        String hoTen = list_SV.get(i).getHoten();
        String lop = list_SV.get(i).getLop();
        int diem = list_SV.get(i).getDiem();

        viTri = i;

        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra(EXTRA_MASV,maSV);
        intent.putExtra(EXTRA_HOTEN,hoTen);
        intent.putExtra(EXTRA_LOP,lop);
        intent.putExtra(EXTRA_DIEM,""+diem);

        startActivityForResult(intent,REQUEST_MAIN);                                    //hàm mở liên kết hai chiều, sau đó nhận kết quả trở lại class này( REQUEST_MAIN : kí hiệu của class này, trả kết quả về class này )
    }

    @Override
    public void onLongClick(final int i) {
        new AlertDialog.Builder(this).setTitle("Delete").setMessage("Do you want to delete this ??")
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        list_SV.remove(i);
                        faceAdapter.notifyDataSetChanged();
                    }})
                .setCancelable(true).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab_Add){
            Intent intent = new Intent(this,AddActivity.class);
            startActivityForResult(intent,REQUEST_MAIN);                                            //mở liên kết hai chiều, sau đó nhận kết quả trở lại class này( REQUEST_MAIN : kí hiệu của class này, trả kết quả về class này )
        }
    }
}
