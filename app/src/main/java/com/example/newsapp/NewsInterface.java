package com.example.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {
    @GET("top-headlines")
    Call<News> news(
            @Query("country") String country,
            @Query("apiKey") String Api_Key
    );
    @GET("top-headlines")
    Call<News> science(
            @Query("country") String country,
            @Query("apiKey") String Api_Key,
            @Query("category") String category
    );
}
