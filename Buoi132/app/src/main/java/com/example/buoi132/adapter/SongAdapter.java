package com.example.buoi132.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buoi132.Model.Song;
import com.example.buoi132.databinding.ItemSongBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> data;
    private LayoutInflater inflater;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binData(data.get(i));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemSongBinding binding;

        public ViewHolder(ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void binData(Song song) {
            binding.tvTitle.setText(song.getTitle());
            binding.tvAlbum.setText(song.getAlbum());
            binding.tvArtist.setText(song.getArtist());

            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            String duration = format.format(song.getDruation());
            binding.tvDuration.setText(duration);


        }
    }
}
