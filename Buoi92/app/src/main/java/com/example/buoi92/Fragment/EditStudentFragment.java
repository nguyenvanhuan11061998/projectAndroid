package com.example.buoi92.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.buoi92.AppDatabase;
import com.example.buoi92.R;
import com.example.buoi92.Student;
import com.example.buoi92.utils.ValidatorUtils;

public class EditStudentFragment extends BaseFragment implements View.OnClickListener {
    private EditText edtName;
    private EditText edtClass;
    private EditText edtScore;
    private Button btnOk;

    private Student student;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit_student;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        edtName = findViewBtId(R.id.edt_name);
        edtClass = findViewBtId(R.id.edt_class);
        edtScore = findViewBtId(R.id.edt_Score);
        btnOk = findViewBtId(R.id.btn_ok);

        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(ValidatorUtils.isInputEmpty(edtName,edtClass,edtScore))                                  //nếu edt rỗng thì return luôn
            return;
        String name = edtName.getText().toString();
        String classRoom = edtClass.getText().toString();
        float Score = Float.parseFloat(edtScore.getText().toString());

        boolean isInsert = false;                                                                   //khai báo biến isInsert = false để kiểm tra sinh viên
        if (student == null){
            isInsert = true;                                                                        //nếu isInsert = true là biến sinh viên được tạo mới
            student = new Student();                                                                //tao mới một sinh viên
        }

        student.setName(name);
        student.setClassRoom(classRoom);
        student.setScore(Score);

        if (isInsert) {                                                                               //nếu isInsert = true là có sinh viên tồn tại
            AppDatabase.getInstance(getContext()).getStudentDao().insert(student);                           //gọi đến CSDL, gọi đến truy vấn thêm sinh viên
            }else {
            AppDatabase.getInstance(getContext()).getStudentDao().update(student);                      //nếu isInsert = false thì update dinh viên
        }
        getParent().showFragment(getParent().getFmlistStudent());                                       //thêm sinh viên xong hiển thị danh sách sinh viên
        getParent().getFmlistStudent().getData();                                                        //đưa data vào adapter để hiển thị lên màn hình
    }

    public void setStudent(Student student){
        this.student = student;
        if(student != null){
            edtName.setText(student.getName());
            edtClass.setText(student.getClassRoom());
            edtScore.setText(student.getScore()+"");
        }else {
            edtName.setText("");
            edtClass.setText("");
            edtScore.setText("");
        }
    }
}
