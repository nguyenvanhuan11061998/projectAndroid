package com.example.buoi92.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.buoi92.AppDatabase;
import com.example.buoi92.R;
import com.example.buoi92.Student;
import com.example.buoi92.adapter.StudentAdapter;

import java.util.List;

public class StudentListFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView lvStudent;
    private StudentAdapter adapter;
    private List<Student> data;

    private FloatingActionButton btnAdd;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_student;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {                                        //phương thức = onCreated trên mainActivity
        super.onActivityCreated(savedInstanceState);
        lvStudent = findViewBtId(R.id.lv_student);
        adapter = new StudentAdapter(getContext());
        lvStudent.setAdapter(adapter);

        getData();

        btnAdd = findViewBtId(R.id.fm_add);
        btnAdd.setOnClickListener(this);
    }

    public void getData() {
        data = AppDatabase.getInstance(getContext()).getStudentDao().getAll();                              //lấy toàn bộ sinh viên trong cơ sở dữ liệu
        adapter.setData(data);                                                                              //đưa lên layout
    }

    @Override
    public void onClick(View v) {                                                                   //click vào sinh viên sẽ gọi đến EditStudent
        showEdit(null);
    }

    private void showEdit(Student student) {
        getParent().getFmEditStudent().setStudent(student);                                                 //truyền vào editSeudent một sinh viên
        getParent().showFragment(getParent().getFmEditStudent());                                           //hiển thị fragmentEdit
    }
}
