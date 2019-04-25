package com.example.comt3hbuoi7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.comt3hbuoi7.adapter.FileAdapter;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FileAdapter.ItemFileClick, View.OnClickListener, DownloadAsync.downloadCallBack {
    private EditText edtLink;
    private Button btnDownload;
    private ProgressBar pdDownload;
    private RecyclerView lvFile;
    private FileAdapter adapter;
    private String currentPath;                                                             //path gốc


    private  final  String[] PERMISSION_LIST = {                                                        //xin permission RunTime
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private FileManager fileManager;
    private List<File> arr;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        fileManager = new FileManager();
        if(checkPermission()){
            readFile(fileManager.path);
        }else{
            requestPermissions(PERMISSION_LIST,0);                                                                   //xin permisssion(alt+enter : new api)
        }
    }

    private void initView() {
        edtLink = findViewById(R.id.edt_link);
        btnDownload = findViewById(R.id.btn_download);
        pdDownload = findViewById(R.id.pd_download);
        btnDownload.setOnClickListener(this);

        adapter = new FileAdapter(this);
        adapter.setItemFileClick(this);
        lvFile = findViewById(R.id.lv_file);
        lvFile.setAdapter(adapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {                   //
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(checkPermission()){
            readFile(fileManager.path);
        }else {
            finish();
        }
    }

    private boolean checkPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {                                         //lấy vesion máy điện thoại đang chạy, >23 mới phải xin permission
            return true;
        }

        for (String p: PERMISSION_LIST) {
            //PERMISSION_GIANTED = acccept
            //PERMISSION_DENIED = denied
            int accept = checkSelfPermission(p);
            if(accept == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    private void readFile(String path){                                                                 //phương thức đọc dữ liệu từ file
        currentPath = path;
        arr = fileManager.getFile(path);
        adapter.setData(arr);
    }



    @Override
    public void onItemFileClick(File f) {
        if(f.isDirectory()){
            readFile(f.getPath());
        }
    }

    @Override
    public void onBackPressed() {
        if(currentPath.equals(fileManager.path)){
            super.onBackPressed();
        }else {
            File f = new File(currentPath);
            String parent = f.getParent();                                                                  //lấy path cha
            readFile(parent);
        }

    }

    @Override
    public void onClick(View v) {
        String link = edtLink.getText().toString();
        if(link.isEmpty()){
            Toast.makeText(this,"link is empty",Toast.LENGTH_LONG).show();
            return;
        }

        String path = currentPath+"/"+System.currentTimeMillis()+".jpg";
        DownloadAsync download = new DownloadAsync(this);
        download.execute(link,path);
    }

    @Override
    public void updatePercent(int percent) {

    }

    @Override
    public void downloadFinish(String path) {
        readFile(currentPath);
    }
}
