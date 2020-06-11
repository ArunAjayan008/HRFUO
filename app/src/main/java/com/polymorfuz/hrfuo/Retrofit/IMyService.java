package com.polymorfuz.hrfuo.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyService {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("name") String name,
                                    @Field("mobno") String mobno,
                                    @Field("password") String password,
                                    @Field("token") String token);

    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("mobno") String mobno,
                                 @Field("password") String password,
                                 @Field("password") String token);

}
