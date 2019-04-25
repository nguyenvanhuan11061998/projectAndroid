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

    private CountDownTimer w;

    private boolean check;
    private int checkBT, t =0;
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
    private long time;

    public void initView(){
        time =10;
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
        checkBT = 0;
        txScore = findViewById(R.id.tx1);
        txScore.setText(String.valueOf(score));
        txTime = findViewById(R.id.time1);

        w = new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished/1000;
                txTime.setText(""+time);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Hết thời gian",Toast.LENGTH_SHORT).show();
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
                    w.cancel();
                    initView();
                }
                break;
            }
            case R.id.btnFalse: {
                if(checkKQ == true){
                    Toast.makeText(this," Không chính xác",Toast.LENGTH_SHORT).show();
                    w.cancel();
                    initView();
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
