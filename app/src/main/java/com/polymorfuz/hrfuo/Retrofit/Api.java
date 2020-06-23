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
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = Config.SERVER_IP;

    @GET("/getprofile")
    Call<List<Profile>> getprofile(
            @Header ("Authorization")
                    String authtoken);

    @GET("/getservice")
    Call<List<ServiceModel>> getservice(@Query("id") String id);

    @GET("/getleave")
    Call<List<LeaveSummaryModel>> getleave(@Query("id") String id);

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

    @GET("/getepfesi")
    Call<List<EPF_ESIModel>> getPF(@Query("id") String id);

    @GET("/getid")
    Call<String> getID(@Query("id") String id);

    @GET("/getleavedays")
    Call<List<MonthlyLeaveModel>> getleaves(@Query("id") String id,
                                            @Query("mn") String mn,
                                            @Query("yr") String yr);

}

