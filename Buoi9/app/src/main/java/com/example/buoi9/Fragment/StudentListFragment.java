package com.example.buoi9.Fragment;

import android.arch.persistence.room.Database;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.buoi9.Adapter.StudentAdapter;
import com.example.buoi9.AppDatabase;
import com.example.buoi9.R;
import com.example.buoi9.Student;

import java.util.List;

public class StudentListFragment extends FragmentBase implements View.OnClickListener {

    private RecyclerView lvStudent;
    private List<Student> data;
    private StudentAdapter adapter;

    private FloatingActionButton btnAdd;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_student;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvStudent = findViewById(R.id.lvStudent);
        adapter = new StudentAdapter(getContext());
        lvStudent.setAdapter(adapter);
        getData();

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }

    public void getData(){
        data = AppDatabase.getInstance(getContext()).getStudentDao().getAll();
        adapter.setData(data);
    }

    @Override
    public void onClick(View v) {
        showEdit(null);
    }

    private void showEdit(Student student){
        getParent().getFmEditStudent().setStudent(student);
        getParent().showFragment(getParent()
                .getFmEditStudent());
    }
}
