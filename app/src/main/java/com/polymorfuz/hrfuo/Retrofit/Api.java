package com.polymorfuz.hrfuo.Retrofit;

import com.polymorfuz.hrfuo.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://http://192.168.0.4:3000/";

    @GET("/profile")
    Call<List<Profile>> getstatus();

}

