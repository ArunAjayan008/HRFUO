package com.polymorfuz.hrfuo.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit postinstance;
    private static Retrofit getinstance;

    public static Retrofit getPostInstance(){
        if(postinstance==null)
            postinstance=new Retrofit.Builder()
                    .baseUrl("http://192.168.0.8:3000/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return  postinstance;
    }
    public static Retrofit getInstance(){
        if(getinstance==null)
            getinstance=new Retrofit.Builder()
                    .baseUrl("http://192.168.0.8:3000/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return  getinstance;
    }
}
