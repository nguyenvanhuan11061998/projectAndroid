package com.example.buoi10.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.buoi10.Fragment.BaseFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private BaseFragment[] arrFragment;

    public PageAdapter(FragmentManager fm,BaseFragment...arrFragments) {
        super(fm);
        this.arrFragment = arrFragments;
    }

    @Override
    public Fragment getItem(int i) {                                                                //trả về Fragment, i: vị trí của item hiện tại.Mỗi page = 1 fragment
        return arrFragment[i];
    }

    @Override
    public int getCount() {                                                                         //số lượng page mà nó sẽ tạo ra
        return  arrFragment.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {                                                //Trả về các title hiện lên màn hình, position: vị trí title
        return arrFragment[position].getTitle();
    }
}
