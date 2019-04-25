package com.example.buoi13;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.buoi13.Model.Song;
import com.example.buoi13.adapter.SongAdapter;
import com.example.buoi13.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongAdapter.itemClickListener {

    private final String[] PERMISSION_LIST = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private SystemData systemData;
    private ArrayList<Song> arrSong;
    private ActivityMainBinding binding;
    private SongAdapter adapter;

    private MediaManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
//        systemData = new SystemData(this);
//        arrSong = systemData.getData();

        adapter = new SongAdapter(this);
        adapter.setListener(this);
//        adapter.setData(arrSong);
        binding.lvSong.setAdapter(adapter);



        //đọc data từ bộ nhớ ngoài
        if (checkPermission() == true){
            readData();
        }
    }


    //đọc data từ bộ nhớ ngoài
    private void readData(){
        systemData = new SystemData(this);
        arrSong = systemData.getData();
        adapter.setData(arrSong);

        manager = new MediaManager(arrSong, this);
    }


    //đọc data từ bộ nhớ ngoài
    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSION_LIST) {
                if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSION_LIST, 0);
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //nếu trả về đúng dữ liệu thì read data, nếu không thì finish();
        if (checkPermission()){
            readData();
        }else {
            finish();
        }
    }

    @Override
    public void onItemClicked(int possition) {
        manager.create(possition);
    }
}
