package com.example.comt3hbuoi7.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.comt3hbuoi7.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileHolder> {
    private ItemFileClick itemFileClick;
    private List<File> data;
    private LayoutInflater inflater;

    public void setItemFileClick(ItemFileClick itemFileClick) {
        this.itemFileClick = itemFileClick;
    }

    public FileAdapter( Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<File> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_file,viewGroup,false);
        return new FileHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FileHolder fileHolder, int i) {
        final File f = data.get(i);
        fileHolder.binData(f);
        if(itemFileClick != null){
            fileHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemFileClick.onItemFileClick(f);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();                                                              //nếu data = null trả về 0, neetus không trả về sizze
    }

    public class FileHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDate;
        public FileHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDate = itemView.findViewById(R.id.tv_date);

        }

        public void binData(File f){
            tvName.setText(f.getName());
            SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
            tvDate.setText(format.format(f.lastModified()));
        }
    }

    public interface ItemFileClick{
        void onItemFileClick(File f);
    }
}
