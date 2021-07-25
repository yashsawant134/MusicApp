package com.example.musicapp.api;

import com.example.musicapp.HomeScreen.artistscreen.ArtistModel;
import com.example.musicapp.ItuneApiPojo.ItuneApiPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ItuneInterface {

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ItuneApiPOJO> getRecords(@Query("term") String query);

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<ArtistModel> getRecordss(@Query("term") String query);

}
