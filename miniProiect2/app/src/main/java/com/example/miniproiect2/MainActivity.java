package com.example.miniproiect2;


import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.miniproiect2.Adapter.NewAdapter;
import com.example.miniproiect2.Async.DownloadAsync;
import com.example.miniproiect2.Async.XMLAsync;
import com.example.miniproiect2.Model.News;
import com.example.miniproiect2.Utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, XMLAsync.ParserXMLCallback, NewAdapter.NewItemListener, View.OnClickListener, DownloadAsync.DownloadCallBack {
    public static final int REQUEST_MAIN = 3;
    public static final String EXTRA_LINK = "extra_link";
    private RecyclerView lvView;
    private NewAdapter adapter;
    private List<News> arrNew;

    private TextView tvHienThi;

    private Button btnTinTuc;
    private Button btnYeuThich;
    private Button btnDaLuu;

    String returnPath;
    private String currentPath = Environment.getExternalStorageDirectory()
            .getPath();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }



//khởi tạo các giá trị trên MainActivity
    private void initView() {
        arrNew = new ArrayList<>();
        lvView = findViewById(R.id.lv_news);
        adapter = new NewAdapter(this);
        lvView.setAdapter(adapter);
        adapter.setItemListener(this);

        tvHienThi = findViewById(R.id.tv_HienThi);

        btnDaLuu = findViewById(R.id.btn_DaLuu);
        btnYeuThich = findViewById(R.id.btn_YeuThich);
        btnTinTuc = findViewById(R.id.btn_TinTuc);

        btnTinTuc.setOnClickListener(this);
        btnYeuThich.setOnClickListener(this);
        btnDaLuu.setOnClickListener(this);

    }



//phương thức tạo search menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                                                     //tạo option menu(Search action)
        getMenuInflater().inflate(R.menu.main,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener( this);

        return super.onCreateOptionsMenu(menu);
    }



//phương thức đưa từ khóa vào thread để đưa lên mạng search
    @Override
    public boolean onQueryTextSubmit(String query) {                                                //tạo thread truyền vào từ khóa search
        if (query.isEmpty()){
            return false;
        }

        DialogUtil.show(this);
        XMLAsync async = new XMLAsync(this);
        async.execute(query);                                                                       //
        return false;
    }


//phương thức thay dổi từ khóa search trên search menu
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override


//kết thúc thread load tin tức trên mạng
    public void onParserFinish(List<News> arr) {                                               //thread kết thúc trả về mảng arr các phần tử New
        DialogUtil.dismiss();
        adapter.setData(arr);
        arrNew = new ArrayList<>();
        arrNew = arr;
        tvHienThi.setText("");
    }



//click vào các item trên lvNews
    @Override
    public void onClick(int i) {
        DialogUtil.show(this);
    String link = arrNew.get(i).getLink();
    String pathFile = arrNew.get(i).getPath();
    Toast.makeText(this,pathFile,Toast.LENGTH_LONG).show();

    if (pathFile==null) {
        Intent intent = new Intent(this, WebNew.class);
        intent.putExtra(EXTRA_LINK, link);
        startActivityForResult(intent, REQUEST_MAIN);
    }else{
        Intent intent = new Intent(this, WebNew.class);
        intent.putExtra(EXTRA_LINK, pathFile);
        startActivityForResult(intent, REQUEST_MAIN);
    }
    }


//long click vào các item new trên lvNews
    @Override
    public void onLongClick(int i) {
        News news = arrNew.get(i);
        String linkDownload = news.getLink();

        DownloadAsync downloadAsync = new DownloadAsync(this);
        String path = currentPath +"/"+System.currentTimeMillis();

        downloadAsync.execute(linkDownload,path);

        news.setPath(returnPath);
        returnPath = downloadAsync.getPath1();
        Toast.makeText(this,"Đã lưu thành công , path: "+returnPath,Toast.LENGTH_LONG).show();
        AppDatabase.getInstance(this).getNewsDao().insert(news);

        returnPath = null;
    }



//click vào các nút trên mainActivity
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_DaLuu:
                List<News> arrNew2;
                arrNew2 = AppDatabase.getInstance(this).getNewsDao().getAllNews();
                adapter.setData(arrNew2);
                if (arrNew2.size()>0){
                    tvHienThi.setText("");
                }
                break;

            case R.id.btn_TinTuc:
                adapter.setData(arrNew);
                break;
        }
    }

    @Override
    public void downloadFinish(String path) {
        returnPath = currentPath+path;
    }
}
