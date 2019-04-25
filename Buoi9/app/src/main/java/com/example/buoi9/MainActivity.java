package com.example.buoi9;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.buoi9.Fragment.EditStudentFragment;
import com.example.buoi9.Fragment.FragmentBase;
import com.example.buoi9.Fragment.StudentListFragment;

public class MainActivity extends AppCompatActivity {

    private StudentListFragment fmStudent = new StudentListFragment();
    private EditStudentFragment fmEditStudent = new EditStudentFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        showFragment(fmStudent);
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel,fmStudent);
        transaction.add(R.id.panel,fmEditStudent);
        transaction.commitAllowingStateLoss();
    }

    public void showFragment(FragmentBase fm){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        transaction.hide(fmStudent);
        transaction.hide(fmEditStudent);
        transaction.show(fm);
        transaction.commitAllowingStateLoss();

    }

    public StudentListFragment getFmStudent() {
        return fmStudent;
    }

    public EditStudentFragment getFmEditStudent() {
        return fmEditStudent;
    }
}
