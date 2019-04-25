package com.example.comt3hbtvn_buoi5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.SinhVien> {                     //Truyền vào các đối tượng là sinh viên, lưu vào một mảng.

    private ArrayList<Face_SinhVien> list_Sv;
    private LayoutInflater inflater_SV;                                                         //Mỗi inflater_Sv là một đối tượng sinh viên
    private FaceItemListener itemListener;

    public void setItemListener(FaceItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public FaceAdapter(Context context, ArrayList<Face_SinhVien> list_Sv) {
        this.list_Sv = list_Sv;
        this.inflater_SV = LayoutInflater.from(context);                                        //Context: đối tượng được tạo ra để lấy dữ liệu, trong ngữ cảnh này nó là một sinh viên
    }

    @NonNull
    @Override
    public SinhVien onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {                   //ViewGroup: đối tượng kiểm soát các view hiển thị ra màn hình
        View v =inflater_SV.inflate(R.layout.item_view_sv,viewGroup,false);         //Tạo ra một view để đại diện cho một layout
        SinhVien sv = new SinhVien(v);                                                          //Tạo một biến sinh viên để chứa một view
        return sv;
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVien faceHolder, final int i) {
    Face_SinhVien sv = list_Sv.get(i);                                                   //tạo một sinh viên trong list
    faceHolder.Data(sv);                                                                    // lấy dữ liệu cho sinh viên
    faceHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(itemListener != null){
                itemListener.onClick(i);
            }
        }
    });
    faceHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if(itemListener != null){
                itemListener.onLongClick(i);
            }
            return true;                                                                //true  để ngắt click
        }
    });

    }

    @Override
    public int getItemCount() {
        return list_Sv.size();
    }

    public class SinhVien extends RecyclerView.ViewHolder{                        //Mỗi SinhVien là một đối tượng sinh viên hiển thị trên layout item_view_sv, mỗi layout là một sinh viên

        private TextView tvhoTen;
        private TextView tvmaSV;
        private TextView tvlop;
        private TextView diem;


        public SinhVien(@NonNull View itemView) {
            super(itemView);
            tvhoTen = itemView.findViewById(R.id.tv_hoTen);
            tvlop = itemView.findViewById(R.id.tv_Lop);
            tvmaSV = itemView.findViewById(R.id.tv_maSV);
            diem = itemView.findViewById(R.id.tv_diem);
        }

        public void Data(Face_SinhVien sv){
            tvmaSV.setText(sv.getMaSV());
            tvhoTen.setText(sv.getHoten());
            tvlop.setText(sv.getLop());
            diem.setText(""+sv.getDiem());
        }
    }

    public interface FaceItemListener{
        void onClick(int i);
        void onLongClick(int i);
    }
}
