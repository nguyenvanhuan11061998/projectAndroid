package com.example.buoi92;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.buoi92.Fragment.BaseFragment;
import com.example.buoi92.Fragment.EditStudentFragment;
import com.example.buoi92.Fragment.StudentListFragment;

public class MainActivity extends AppCompatActivity {
    private StudentListFragment fmlistStudent = new StudentListFragment();                              //tạo hai đói tượng fragment
    private EditStudentFragment fmEditStudent = new EditStudentFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();                                                                                 //thêm hai đối tượng fragment vào chương trình
        showFragment(fmlistStudent);                                                                    //hiển thị fmListStudent
    }

    public void showFragment(BaseFragment fm) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();               //một công cụ để thực hiện các thao tác thêm, thay thế or xóa các fragment
        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);       //có tác dụng giúp thao tác hiển thị không quá nhanh phù hợp với người dùng
        transaction.hide(fmEditStudent);
        transaction.hide(fmlistStudent);
        transaction.show(fm);
        transaction.commitAllowingStateLoss();

    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel,fmlistStudent);                                                        //thêm các fragment vào layout có id là panel
        transaction.add(R.id.panel,fmEditStudent);
        transaction.commitAllowingStateLoss();
    }

    public StudentListFragment getFmlistStudent() {                                                     //lấy ra fmListStudent
        return fmlistStudent;
    }

    public EditStudentFragment getFmEditStudent() {                                                     //lấy ra fmEditFragment
        return fmEditStudent;
    }
}
