package com.example.miniproiect2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.miniproiect2.Model.News;
import com.example.miniproiect2.R;

import java.util.ArrayList;
import java.util.List;


//đối tượng phân tích tin tức đưa lên layout
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewHoldel> {
    private LayoutInflater inflater;
    private List<News> data;
    private NewItemListener itemListener;

    public void setItemListener(NewItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public NewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {                                                     //truyền vào một mảng data để xử lý
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewHoldel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_new,viewGroup,false);
        return new NewHoldel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHoldel newHoldel, final int i) {
        final News item = data.get(i);
        newHoldel.binData(item);

        newHoldel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemListener != null){
                    itemListener.onClick(i);
                }
            }
        });

        newHoldel.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(itemListener != null){
                    itemListener.onLongClick(i);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class NewHoldel extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvPubDate;
        private ImageView imNew;
        public NewHoldel(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvPubDate = itemView.findViewById(R.id.tv_pubDate);
            imNew = itemView.findViewById(R.id.im_new);


        }

        public void binData(News item) {
            tvTitle.setText(item.getTitle());
            tvDesc.setText(item.getDesc());
            tvPubDate.setText(item.getPubDate());

            Glide.with(imNew).load(item.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher_round)
                    .into(imNew);
        }

    }
    public interface NewItemListener{
        void onClick(int i);
        void onLongClick(int i);
    }
}
