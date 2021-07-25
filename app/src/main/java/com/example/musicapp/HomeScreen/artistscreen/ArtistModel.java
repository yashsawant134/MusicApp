package com.example.musicapp.HomeScreen.artistscreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistModel {
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    private List<ArtistModel> list;

    public List<ArtistModel> getList() {
        return list;
    }

    public void setList(List<ArtistModel> list) {
        this.list = list;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }
}
