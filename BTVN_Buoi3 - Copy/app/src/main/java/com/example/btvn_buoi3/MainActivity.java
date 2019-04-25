package com.example.btvn_buoi3;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.BTVN_Buoi3.TinhToan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{
    private final String TAG = "MainActivity";

    private CountDownTimer w;
    TinhToan tinhToan;
    private int soThuNhat;
    private int soThuHai;
    private int Tong;
    private Button btnTrue;
    private Button btnFalse;
    private boolean checkKQ;
    private TextView txBieuThuc;
    private int score = 0;
    private TextView txScore;
    private TextView txTime;
    private long time = 0;

    public void initView(){
        tinhToan = new TinhToan();
        soThuNhat = tinhToan.TaoSo();
        soThuHai = tinhToan.TaoSo();
        Tong = tinhToan.TongRandom(soThuNhat,soThuHai);
        checkKQ = tinhToan.KiemTra(soThuNhat,soThuHai,Tong);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        txBieuThuc = findViewById(R.id.text1);
        btnTrue.setOnLongClickListener(this);
        btnFalse.setOnLongClickListener(this);
        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        txBieuThuc.setText(soThuNhat+"+"+soThuHai+"="+Tong);
        txScore = findViewById(R.id.tx1);
        txScore.setText(String.valueOf(score));


        txTime = findViewById(R.id.time1);
        w = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished/1000;
                txTime.setText(""+time);
//                if(time == 0){
//                    w.cancel();
//                    return;
//                }
            }

            @Override
            public void onFinish() {
                return;
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG,"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTrue:{
                if(checkKQ == true){
                    score = score + 1;
                    Toast.makeText(this,"Chính xác",Toast.LENGTH_SHORT).show();
                    w.cancel();
                    initView();
                }else{
                    Toast.makeText(this,"Không chính xác",Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            }
            case R.id.btnFalse: {
                if(checkKQ == true){
                    Toast.makeText(this," Không chính xác",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    score = score + 1;
                    Toast.makeText(this,"Chính xác",Toast.LENGTH_SHORT).show();
                    w.cancel();
                    initView();
                }
                break;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
