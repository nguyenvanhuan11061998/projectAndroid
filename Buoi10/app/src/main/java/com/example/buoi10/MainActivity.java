package com.example.buoi10;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.buoi10.Adapter.PageAdapter;
import com.example.buoi10.Fragment.FavoriteFragment;
import com.example.buoi10.Fragment.NewsFragment;
import com.example.buoi10.Fragment.SavedFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private NewsFragment fmNews = new NewsFragment();
    private SavedFragment fmSaved = new SavedFragment();
    private FavoriteFragment fmFavorite  = new FavoriteFragment();

    private ViewPager pager;
    private PageAdapter adapter;
    private TabLayout tabTitle;

    public NewsFragment getFmNews() {
        return fmNews;
    }

    public SavedFragment getFmSaved() {
        return fmSaved;
    }

    public FavoriteFragment getFmFavorite() {
        return fmFavorite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        pager = findViewById(R.id.pager);
        adapter = new PageAdapter(getSupportFragmentManager(),fmNews,fmSaved,fmFavorite);
        pager.setAdapter(adapter);
        tabTitle = findViewById(R.id.tab_title);
        tabTitle.setupWithViewPager(pager);                                                         //đồng bộ hóa PageView với TabLayout

//        pager.setCurrentItem(2);                                                                    //mặc định vị trí khởi tạo là ở Fragment nào

        pager.addOnPageChangeListener(this);                                                       //xử lý logic khi chuyển page.
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {                                                             //vị trí hiện tại đang select
        Log.e(getClass().getName(),"onPageScrolled "+i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
