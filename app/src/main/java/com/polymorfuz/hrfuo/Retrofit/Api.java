package com.polymorfuz.hrfuo.Retrofit;

import com.polymorfuz.hrfuo.model.Leave;
import com.polymorfuz.hrfuo.model.Notifications;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.1.6:3000";

    @GET("/getprofile?id=44")
    Call<List<Profile>> getprofile();

    @GET("/getservice?id=44")
    Call<List<ServiceModel>> getservice();

    @GET("/getleave?id=44")
    Call<List<Leave>> getleave();

    @GET("/getnotify?id=44")
    Call<List<Notifications>> getnotify();

}

