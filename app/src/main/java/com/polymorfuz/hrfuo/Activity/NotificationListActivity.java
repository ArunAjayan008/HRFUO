package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.polymorfuz.hrfuo.Adapter.NotificationAdapter;
import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.model.Notifications;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationListActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    NotificationAdapter adapter;
    RecyclerView notifycycler;
    List<Notifications> notify;
    String id;
    NetworkChangeReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        receiver = new NetworkChangeReceiver(this);
        notifycycler = findViewById(R.id.notify_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationListActivity.this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        notifycycler.setLayoutManager(layoutManager);
    }

    private void fetchdata() {
        id = new SharedPrefManager(getApplicationContext()).readString("accesstoken", null);
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

        Call<List<Notifications>> call = api.getnotify();
        call.enqueue(new Callback<List<Notifications>>() {
            @Override
            public void onResponse(Call<List<Notifications>> call, Response<List<Notifications>> response) {
                notify = response.body();
                adapter = new NotificationAdapter(getApplicationContext(), notify);
                notifycycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Notifications>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchdata();
    }

    @Override
    public void onNetworkConnected(boolean isConnected) {

    }
}