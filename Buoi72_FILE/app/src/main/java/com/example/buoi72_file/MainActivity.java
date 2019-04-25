package com.example.buoi72_file;

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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FileAdapter.ItemFileClick, View.OnClickListener, DownloadAsync.DownloadCallBack {

    private FileAdapter fileAdapter;
    private RecyclerView lvFile;
    private FileManager manager;
    private List<File> arr;
    private String currentPath;
    private EditText edtLink;
    private ProgressBar pbDownload;
    private Button btnDownload;

    private final String[] PERMISSTION_LIST ={                                                      //các permission quan trọng
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            //khởi tạo các giá trị
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new FileManager();
        initView();

        if (checkPermission()){
            readFile(manager.path);
        }else {
            requestPermissions(PERMISSTION_LIST,0);
        }

    }

    private boolean checkPermission(){                                                              //hàm kiểm tra vesion của máy chạy
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        for (String p : PERMISSTION_LIST ) {
            int accept = checkSelfPermission(p);
            if(accept == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()){
            readFile(manager.path);
        }else{
            finish();
        }
    }

    private void initView() {
        fileAdapter = new FileAdapter(this);
        lvFile = findViewById(R.id.lv_View);
        lvFile.setAdapter(fileAdapter);
        fileAdapter.setFileClick(this);


        edtLink = findViewById(R.id.edt_link);
        pbDownload = findViewById(R.id.pb_download);
        btnDownload = findViewById(R.id.btn_Download);
        btnDownload.setOnClickListener(this);
    }

//hiển thị các file có trong điện thoại

    private void readFile(String path) {                                                            //truyền vào path để đưa đến địa chỉ các file trong máy tính
        currentPath = path;                                                                         //gán biến current bằng đường dẫn path
        arr = manager.getFile(path);                                                                //truyền giá trị path vào hàm getFile để đưa vào mảng một list các File
        fileAdapter.setData(arr);                                                                   //đưa vào adapter để đưa lên màn hình
    }

    @Override
    public void onItemFileClick(File f) {                                                           //xử lý sự kiện khi click vào file: gọi hàm hiển thị file lên màn hình
        if (f.isDirectory()){
            readFile(f.getPath());
        }
    }




//download một file nhạc trên mạng về
    @Override
    public void onClick(View v) {                                                                   //xử lý sự kiện dowmlload khi click vào nút download
        String link = edtLink.getText().toString();
        if (link.isEmpty()){
            Toast.makeText(this,"Link input empty",Toast.LENGTH_LONG).show();
        }

        String path = currentPath + "/" + System.currentTimeMillis() + ".jpg";
        DownloadAsync download = new DownloadAsync(this);
        download.execute(link,path);
    }

    @Override
    public void updatePercent(int percent) {                                                        //hiển thị progessBar download
        pbDownload.setProgress(percent);
    }

    @Override
    public void downloadFinish(String path) {                                                       //download kết thúc, gọi hàm đọc file
        readFile(currentPath);                                                                      //chạy mở file currentPath
    }


//cài đặt nút quay lại trên điện thoại

    @Override
    public void onBackPressed() {
        if(currentPath.equals(manager.path)) {                                                      //nếu path hiện tại là path gốc thì thoát chương trình
            super.onBackPressed();
        }else{
            File f = new File(currentPath);                                                         //nếu không, tạo file mới, với đường dẫn path hiện hành
            String parent = f.getParent();                                                          //lấy đường dẫn của file cha của file hiện hành
            readFile(parent);                                                                       //mở file cha của file hiện hành đến khi nào hết file, đến path gốc
        }
    }
}
