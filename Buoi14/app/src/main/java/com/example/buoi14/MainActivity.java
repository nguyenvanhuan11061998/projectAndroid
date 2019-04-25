package com.example.buoi14;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.buoi14.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ServiceConnectionListener {

    private MyService service;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setListener(this);
    }

    private ServiceConnection connection = new ServiceConnection() {                                //đối tượng lắng nghe các trạng thái của activity và Service
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getClass().getName(),"onServiceConnected");

            MyService.MyBinder binder = (MyService.MyBinder) service;
            MainActivity.this.service = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getClass().getName(),"onServiceDisconnected");
        }
    };

    @Override
    public void onBindClicked() {
//        Intent intent = new Intent(this,MyService.class);
//        bindService(intent,connection,BIND_AUTO_CREATE);                                            //tương đương với startActivity,khởi chạy Service
        Intent intent = new Intent(this,MySecondService.class);
        startService(intent);
    }

    @Override
    public void onUnBindClicked() {
//        unbindService(connection);
        Intent intent = new Intent(this,MySecondService.class);
        stopService(intent);
    }

    @Override
    public void onStartNewAct() {
        Intent intent = new Intent(this, getClass());                                  //getClass(): tự bật chính bản thân class đó lên
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(connection);
    }
}
