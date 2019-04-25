package com.example.buoi92.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buoi92.R;
import com.example.buoi92.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_student,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Student item = data.get(i);
        viewHolder.binData(item);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvClass;
        private TextView tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvClass = itemView.findViewById(R.id.tv_class);
            tvScore = itemView.findViewById(R.id.tv_score);
        }

        public void binData(Student item) {
            tvName.setText(item.getName());
            tvClass.setText(item.getClassRoom());
            tvScore.setText(item.getScore()+ "");
        }
    }
}
