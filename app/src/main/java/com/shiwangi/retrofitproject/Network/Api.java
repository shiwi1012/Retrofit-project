package com.shiwangi.retrofitproject.Network;

import com.shiwangi.retrofitproject.Model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String key = "34";
    @GET("list?page="+key)
    Call<List<Image>> getAllImages();
}
