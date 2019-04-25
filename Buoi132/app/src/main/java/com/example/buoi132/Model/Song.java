package com.example.buoi132.Model;

public class Song {
    private String data;
    private long druation;
    private long size;
    private String title;
    private String album;
    private String artist;

    public Song(String data, long druation, long size, String title, String album, String artist) {
        this.data = data;
        this.druation = druation;
        this.size = size;
        this.title = title;
        this.album = album;
        this.artist = artist;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getDruation() {
        return druation;
    }

    public void setDruation(long druation) {
        this.druation = druation;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
