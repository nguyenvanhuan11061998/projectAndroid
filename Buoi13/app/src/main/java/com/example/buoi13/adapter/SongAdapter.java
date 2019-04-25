package com.example.buoi13.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buoi13.Model.Song;
import com.example.buoi13.R;
import com.example.buoi13.databinding.ActivityMainBinding;
import com.example.buoi13.databinding.ItemSongBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private ArrayList<Song> data;
    private LayoutInflater inflater;

    private itemClickListener listener;

    public void setListener(itemClickListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<Song> data) {
        this.data = data;
        notifyDataSetChanged();

    }

    public SongAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemSongBinding binding = ItemSongBinding.inflate(inflater);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.binData(data.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClicked(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSongBinding binding;

        public ViewHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void binData(Song item){
            binding.tvTitle.setText(item.getTitle());
            binding.tvAlbum.setText(item.getAlbum());
            binding.tvArtist.setText(item.getArtist());

            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            String duration = format.format(item.getDuration());
            binding.tvDuration.setText(duration);

            binding.tvSize.setText(formatSize(item.getSize()));
        }

        public String formatSize(long bytes){
            if (bytes < 1024) return bytes + "B";
            int exp = (int) (Math.log(bytes) / Math.log(1024));
            String pre = "KMGTPE".charAt(exp-1)+"";
            return String.format("%.1f %sB",bytes/Math.pow(1024,exp),pre);
        }
    }

    public interface itemClickListener{
        void onItemClicked(int possition);
    }
}
