package com.example.buoi9.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buoi9.R;
import com.example.buoi9.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private LayoutInflater inflater;
    private List<Student> data;

    public StudentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Student> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_view,viewGroup,false);
        return new StudentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder studentHolder, int i) {
    Student item = data.get(i);
    studentHolder.binData(item);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class StudentHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvClass;
        private TextView tvScore;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_Name);
            tvClass = itemView.findViewById(R.id.tv_Class);
            tvScore = itemView.findViewById(R.id.tv_Score);
        }

        public void binData(Student item) {
            tvName.setText(item.getName());
            tvClass.setText(item.getClassRoom());
            tvScore.setText(""+item.getScore());
        }
    }
}
