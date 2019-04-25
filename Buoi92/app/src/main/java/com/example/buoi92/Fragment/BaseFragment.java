package com.example.buoi92.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buoi92.MainActivity;
import com.example.buoi92.Student;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutRes(),container,false);                                                              //tạo View với class truyền vào
        return v;
    }

    protected <T extends View>T findViewBtId(@IdRes int id){                                                            //trả về một view với id được truyền vào
        return getActivity().findViewById(id);
    }

    protected MainActivity getParent(){                                                             //tạo một đối tượng MainActivity để thao tác với các Fragment
        return (MainActivity) getActivity();
    }

    protected abstract int getLayoutRes();

}
