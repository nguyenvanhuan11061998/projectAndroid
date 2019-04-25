package com.example.buoi14;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

//Bind Service
public class MyService extends Service {

    private final String  TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind");
        return new MyBinder(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    public class MyBinder extends Binder {                                                            //lấy kết nối giữa service với activity
        private  MyService service;

        public MyBinder(MyService service) {
            this.service = service;
        }

        public MyService getService() {
            return service;
        }
    }
}
