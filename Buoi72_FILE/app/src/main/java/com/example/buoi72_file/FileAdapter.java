package com.example.buoi72_file;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;


   import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileHolder> {
    private ItemFileClick fileClick;

    private LayoutInflater inflater;
    private List<File> data;

    public FileAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<File> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setFileClick(ItemFileClick fileClick) {
        this.fileClick = fileClick;
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_file,viewGroup,false);
        return new FileHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FileHolder fileHolder, int i) {
        final File item = data.get(i);
        fileHolder.binData(item);

        if (fileClick != null){
            fileHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fileClick.onItemFileClick(item);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 :data.size();
    }

    public class FileHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDate;


        public FileHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        public void binData(File item) {
            tvName.setText(item.getName());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            tvDate.setText(format.format(item.lastModified()));
        }
    }


    public interface ItemFileClick{
        void onItemFileClick(File f);
    }
}
