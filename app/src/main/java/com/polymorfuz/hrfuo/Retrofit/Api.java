package com.polymorfuz.hrfuo.Retrofit;

import com.polymorfuz.hrfuo.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.1.6:3000";

    @GET("/getprofile?id=1234")
    Call<List<Profile>> getstatus();

}

