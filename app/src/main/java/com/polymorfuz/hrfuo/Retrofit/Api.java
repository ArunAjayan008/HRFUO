package com.polymorfuz.hrfuo.Retrofit;

import com.polymorfuz.hrfuo.Utilities.Config;
import com.polymorfuz.hrfuo.model.Deduct_Model;
import com.polymorfuz.hrfuo.model.EPF_ESIModel;
import com.polymorfuz.hrfuo.model.EarningModel;
import com.polymorfuz.hrfuo.model.HolidayModel;
import com.polymorfuz.hrfuo.model.LeaveSummaryModel;
import com.polymorfuz.hrfuo.model.MonthlyLeaveModel;
import com.polymorfuz.hrfuo.model.Notifications;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = Config.SERVER_IP;


    @GET("/getaccess")
    Call<String>getaccess();

    @GET("/getprofile")
    Call<List<Profile>> getprofile();

    @GET("/getservice")
    Call<List<ServiceModel>> getservice();

    @GET("/getleave")
    Call<List<LeaveSummaryModel>> getleave(@Query("id") String id);

    @GET("/getnotify")
    Call<List<Notifications>> getnotify();

    @GET("/getholiday")
    Call<List<HolidayModel>> getholidays();

    @GET("/getearning")
    Call<List<EarningModel>> getearning(
            @Query("mn") String mn,
            @Query("yr") String yr
    );

    @GET("/getdeduct")
    Call<List<Deduct_Model>> getdeduct(
            @Query("id") String id,
            @Query("mn") String mn,
            @Query("yr") String yr
    );

    @GET("/getepfesi")
    Call<List<EPF_ESIModel>> getPF();

    @GET("/getid")
    Call<String> getID(@Query("id") String id);

    @GET("/getleavedays")
    Call<List<MonthlyLeaveModel>> getleaves(@Query("mn") String mn,
                                            @Query("yr") String yr);

}

