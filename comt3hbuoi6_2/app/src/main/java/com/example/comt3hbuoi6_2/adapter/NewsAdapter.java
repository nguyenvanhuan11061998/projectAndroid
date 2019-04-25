package com.example.comt3hbuoi6_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comt3hbuoi6_2.R;
import com.example.comt3hbuoi6_2.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private ArrayList<News> data;
    private LayoutInflater inflater;

    public NewsAdapter(Context context, ArrayList<News> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_news, viewGroup, false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
        News item = data.get(i);
        newsHolder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        private ImageView imNews;
        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvPubDate;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            imNews = itemView.findViewById(R.id.im_news);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPubDate = itemView.findViewById(R.id.tv_pub_date);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }

        public void bindData(final News item){
            tvTitle.setText(item.getTitle());
            tvPubDate.setText(item.getPubDate());
            tvDesc.setText(item.getDesc());

            Glide.with(imNews)
                    .load(item.getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imNews);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(item.getLink()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }
}
