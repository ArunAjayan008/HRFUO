package com.polymorfuz.hrfuo.Retrofit;

import com.polymorfuz.hrfuo.model.Deduct_Model;
import com.polymorfuz.hrfuo.model.EarningModel;
import com.polymorfuz.hrfuo.model.HolidayModel;
import com.polymorfuz.hrfuo.model.Leave;
import com.polymorfuz.hrfuo.model.Notifications;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //    String BASE_URL = "http://192.168.1.6:3000";
    String BASE_URL = "http://192.168.0.12:3000";

    @GET("/getprofile")
    Call<List<Profile>> getprofile(
            @Query("id") String id);

    @GET("/getservice?id=44")
    Call<List<ServiceModel>> getservice();

    @GET("/getleave?id=44")
    Call<List<Leave>> getleave();

    @GET("/getnotify?id=44")
    Call<List<Notifications>> getnotify();

    @GET("/getholiday?id=44")
    Call<List<HolidayModel>> getholidays();

    @GET("/getearning?id=44")
    Call<List<EarningModel>> getearning(
            @Query("mn") String mn,
            @Query("yr") String yr
    );

    @GET("/getdeduct?id=44")
    Call<List<Deduct_Model>> getdeduct(
            @Query("mn") String mn,
            @Query("yr") String yr
    );

}

