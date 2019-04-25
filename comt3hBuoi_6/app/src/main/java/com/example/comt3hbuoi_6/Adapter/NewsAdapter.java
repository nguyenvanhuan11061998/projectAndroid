package com.example.comt3hbuoi_6.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comt3hbuoi_6.Model.News;
import com.example.comt3hbuoi_6.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewHolder> {

    private ArrayList<News> data;
    private LayoutInflater inflater;


    public NewsAdapter(Context context,ArrayList<News> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_new,viewGroup,false);
        return new NewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder newHolder, int i) {
        News item = data.get(i);
        newHolder.binData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class NewHolder extends RecyclerView.ViewHolder {
        private ImageView imNews;
        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvpubDate;

        public NewHolder(View itemView) {
            super(itemView);
            imNews = itemView.findViewById(R.id.im_news);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvpubDate = itemView.findViewById(R.id.tv_pubDate);
        }

        public void binData(News item){
            tvTitle.setText(item.getTitle());
            tvpubDate.setText(item.getPubDate());
        }
    }
}
