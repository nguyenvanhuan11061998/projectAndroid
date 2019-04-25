package com.t3h.BTVN_Buoi3;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TimeView extends View implements Runnable{
    private Paint p;{
        p = new Paint();
        p.setAntiAlias(true);
    }

    public TimeView(Context context) {
        super(context);
        init(null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs){

    }

    @Override
    public void run() {

    }
}
