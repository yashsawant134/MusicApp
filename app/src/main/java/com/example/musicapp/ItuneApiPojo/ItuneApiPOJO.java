package com.example.musicapp.ItuneApiPojo;

import androidx.room.ColumnInfo;

import com.example.musicapp.HomeScreen.artistscreen.ArtistModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ItuneApiPOJO implements Serializable {
    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("collectionArtistId")
    @Expose
    private Integer collectionArtistId;
    @SerializedName("collectionArtistName")
    @Expose
    private String collectionArtistName;
    @SerializedName("collectionArtistViewUrl")
    @Expose
    private String collectionArtistViewUrl;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    //    @SerializedName("artworkUrl30")
//    @Expose
//    @ColumnInfo(name = "artworkUrl30")
//    private String artworkUrl30;
//    @SerializedName("artworkUrl60")
//    @Expose
//    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("discNumber")
    @Expose
    private Integer discNumber;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("trackNumber")
    @Expose
    private Integer trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;

    @SerializedName("results")
    @Expose
    private List<ItuneApiPOJO> tracks;


    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public Integer getCollectionArtistId() {
        return collectionArtistId;
    }

    public void setCollectionArtistId(Integer collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }

    public String getCollectionArtistName() {
        return collectionArtistName;
    }

    public void setCollectionArtistName(String collectionArtistName) {
        this.collectionArtistName = collectionArtistName;
    }

    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    public void setCollectionArtistViewUrl(String collectionArtistViewUrl) {
        this.collectionArtistViewUrl = collectionArtistViewUrl;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }



    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }


    public List<ItuneApiPOJO> getTracks() {
        return tracks;
    }

    public void setTracks(List<ItuneApiPOJO> tracks) {
        this.tracks = tracks;
    }


}
