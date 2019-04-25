package com.t3h.Buoi3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.buoi_3.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClockView extends View implements Runnable{
    private Paint p;                    //định nghĩa các thông tin nét vẽ: màu, đường nét, ...
    {                                   // function nặc danh, tự động khởi tạo
        p= new Paint();
        p.setColor(Color.RED);
        p.setTextSize(30);
        p.setAntiAlias(true);               // làm mịn nét vẽ
    }
    private SimpleDateFormat format = new SimpleDateFormat("ss");
    private String time;

    public ClockView(Context context) {
        super(context);
        init(null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        if(attrs != null){
            TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.ClockView) ;             //đọc ra các thuộc tính được xét trên layout
            int color = arr.getColor(R.styleable.ClockView_color, Color.BLACK);              //default value: nếu trên lay
            int size = arr.getDimensionPixelSize(R.styleable.ClockView_size,30);
            arr.recycle();
            p.setColor(color);
            p.setTextSize(size);
        }

        Thread t = new Thread(this);
        t.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {          //Canvas: Ngòi bút để vẽ = graphic2D
        super.onDraw(canvas);
        canvas.drawText(time,100,100,p);
    }



    @Override
    public void run() {
        while(true){
            Calendar c = Calendar.getInstance();
            time = format.format(c.getTime());
            postInvalidate();
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
