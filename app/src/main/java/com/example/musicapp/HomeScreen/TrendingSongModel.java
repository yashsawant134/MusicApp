package com.example.musicapp.HomeScreen;

public class TrendingSongModel {
    int image;
    String song_name,artistname,numberofplays;

    public TrendingSongModel(int image, String song_name, String artistname, String numberofplays) {
        this.image = image;
        this.song_name = song_name;
        this.artistname = artistname;
        this.numberofplays = numberofplays;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getNumberofplays() {
        return numberofplays;
    }

    public void setNumberofplays(String numberofplays) {
        this.numberofplays = numberofplays;
    }
}
