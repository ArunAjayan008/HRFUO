package com.polymorfuz.hrfuo.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilityMethods {

    public boolean isconnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        return connected;
    }

    public void set_snackbar(View view, String msg, Context context, String msgtype) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        View snackbarview = snackbar.getView();
        if (msgtype.equals("error")) {
            snackbarview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed));
        } else if (msgtype.equals("warning")) {
            snackbarview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorOrange));
        } else if (msgtype.equals("success")) {
            snackbarview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen));
        }
        snackbar.show();
    }

    public void getAccesstoken(Context context) {
        String id = new SharedPrefManager(context).readString("authtoken", null);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + id);
                return chain.proceed(newRequest.build());
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<String> call = api.getaccess();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                new SharedPrefManager(context).saveString("accesstoken", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Error........", Toast.LENGTH_LONG).show();
            }
        });
    }

}
