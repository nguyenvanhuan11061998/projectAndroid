package com.example.buoi11;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private Button btnClick;
    private Button btnAlpha;
    private Button btnRotate;
    private Button btnTranslate;
    private Button btnScale;
    private Button btnSet;
    private ImageView imAnimation;
    private AnimationDrawable animation;
    private Animation alpha;
    private Animation rotate;
    private Animation scale;
    private Animation translate;
    private Animation set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnClick = findViewById(R.id.btn_click);
        btnAlpha = findViewById(R.id.btn_Alpha);
        btnRotate = findViewById(R.id.btn_Rotate);
        btnScale = findViewById(R.id.btn_Scale);
        btnTranslate = findViewById(R.id.btn_Translate);
        btnSet = findViewById(R.id.btn_Set);
        imAnimation = findViewById(R.id.im_animation);
        animation = (AnimationDrawable) imAnimation.getDrawable();                                                       //lấy hình ảnh đang xét cho imageViews đó

        btnClick.setOnClickListener(this);
        btnAlpha.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        btnSet.setOnClickListener(this);

        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        scale = AnimationUtils.loadAnimation(this, R.anim.scale_ani);
        translate = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        set = AnimationUtils.loadAnimation(this,R.anim.set_anim);

        set.setAnimationListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_click:
                if (animation.isRunning()){                                                                                     //isRuning: đang chạy
                    animation.stop();                                                                                           //dừng animation
                }else {
                    animation.start();                                                                                          //chạy animation
                }
                break;
            case R.id.btn_Alpha:
                btnAlpha.startAnimation(alpha);
                break;
            case R.id.btn_Rotate:
                btnRotate.startAnimation(rotate);
                break;
            case R.id.btn_Scale:
                btnScale.startAnimation(scale);
                break;
            case R.id.btn_Translate:
                btnTranslate.startAnimation(translate);
                break;
            case R.id.btn_Set:
                btnSet.startAnimation(set);
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation.equals(alpha)){
            btnAlpha.setVisibility(View.GONE);                                                                              //ẩn animation đi
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
