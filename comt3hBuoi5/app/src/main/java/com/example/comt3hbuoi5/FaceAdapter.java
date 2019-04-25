package com.example.comt3hbuoi5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceHolder>{          //
    private LayoutInflater inflater;                                                    //Ánh xạ một layout chuyển nó thành một view
    private ArrayList<face> data;                                                     //mảng data
    private FaceItemListener listener;

    public void setListener(FaceItemListener listener) {
        this.listener = listener;
    }

    public FaceAdapter(Context context, ArrayList<face> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {             //Thể hiên của layout itemFace(View), nắm giữ thông tin của view, mỗi position là một view, load view, layout của view
        View v = inflater.inflate(R.layout.item_face,viewGroup,false);              //ánh xạ layout, biến nó trở thành view
        FaceHolder holder = new FaceHolder(v);                                                  //đưa vào holder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FaceHolder faceHolder, final int position) {                   //đổ dữ liệu lên phương thức trên,load dữ liệu của view
        face f = data.get(position);
        faceHolder.binData(f);
        faceHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });

        faceHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener != null){
                    listener.onLongClick(position);
                }
                return true;                                                            //true để ngắt click
            }
        });
    }

    @Override
    public int getItemCount() {                                                             //lấy số lương phần tử để sinh ra item view
        return data.size();
    }

    public class FaceHolder extends RecyclerView.ViewHolder {           //đúng cấu trúc, có Adapter sẽ tao class này, Cấu hình cho View Holder
        private ImageView imFace;
        private TextView tvname;

        public FaceHolder(@NonNull View itemView) {
            super(itemView);
            imFace = itemView.findViewById(R.id.im_face);
            tvname = itemView.findViewById(R.id.tv_name);
        }

        public void binData(face face){
            imFace.setImageResource(face.getFace());
            tvname.setText(face.getName());
        }

    }


    //xử lý sự kiện, tạo một bô sự kiện cho recycleView
    public interface FaceItemListener{
        void onClick(int position);
        void onLongClick(int position);
    }

}
