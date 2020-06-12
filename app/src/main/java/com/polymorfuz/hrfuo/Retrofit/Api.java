package com.polymorfuz.hrfuo.Retrofit;

import com.polymorfuz.hrfuo.Utilities.Config;
import com.polymorfuz.hrfuo.model.Deduct_Model;
import com.polymorfuz.hrfuo.model.EPFModel;
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

    //    String BASE_URL = "http://192.168.1.6:3000";13.233.128.2
    String BASE_URL = Config.SERVER_IP;

    @GET("/getprofile")
    Call<List<Profile>> getprofile(
            @Query("id") String id);

    @GET("/getservice")
    Call<List<ServiceModel>> getservice(@Query("id") String id);

    @GET("/getleave")
    Call<List<Leave>> getleave(@Query("id") String id);

    @GET("/getnotify")
    Call<List<Notifications>> getnotify(@Query("id") String id);

    @GET("/getholiday")
    Call<List<HolidayModel>> getholidays(@Query("id") String id);

    @GET("/getearning")
    Call<List<EarningModel>> getearning(
            @Query("id") String id,
            @Query("mn") String mn,
            @Query("yr") String yr
    );

    @GET("/getdeduct")
    Call<List<Deduct_Model>> getdeduct(
            @Query("id") String id,
            @Query("mn") String mn,
            @Query("yr") String yr
    );

    @GET("/getepf")
    Call<List<EPFModel>> getPF(@Query("id") String id);

    @GET("/getid")
    Call<String> getID(@Query("id") String id);
}

