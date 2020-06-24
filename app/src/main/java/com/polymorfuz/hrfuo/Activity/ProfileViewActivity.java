package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.Profile;

import java.io.IOException;
import java.util.List;

import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileViewActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    TextView nametxt, agetxt, gendertxt, qualtxt, dobtxt, addrtxt, empidtxt, mobnotxt;
    UtilityMethods utils = new UtilityMethods();
    View view;
    String id;
    NetworkChangeReceiver receiver;
    ConstraintLayout no_network;
    LinearLayout dynamiclayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        receiver = new NetworkChangeReceiver(this);
        view = getWindow().getDecorView().getRootView();
        no_network = findViewById(R.id.no_network);
        dynamiclayout = findViewById(R.id.dynamic_content);
        nametxt = findViewById(R.id.profilename_pva_txt);
        empidtxt = findViewById(R.id.empid_pva_txt);
        mobnotxt = findViewById(R.id.mobno_pva_txt);
        agetxt = findViewById(R.id.age_pva_txt);
        gendertxt = findViewById(R.id.gender_pva_txt);
        qualtxt = findViewById(R.id.qual_pva_txt);
        dobtxt = findViewById(R.id.dob_pva_txt);
        addrtxt = findViewById(R.id.addr_pva_txt);

    }

    private void fetchData() {
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

        Call<List<Profile>> call = api.getprofile();

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (response.message().equals("Forbidden")) {
                    utils.getAccesstoken(getApplicationContext());
                    setNoNetwork("Session Timed Out");
                } else {
                    List<Profile> adslist = response.body();
                    assert adslist != null;
                    nametxt.setText(adslist.get(0).getDesig());
                    empidtxt.setText(adslist.get(0).getUserid());
                    mobnotxt.setText(adslist.get(0).getMobno());
                    agetxt.setText(adslist.get(0).getAge());
                    dobtxt.setText(adslist.get(0).getDob());
                    gendertxt.setText(adslist.get(0).getGender());
                    qualtxt.setText(adslist.get(0).getEdu_qual());
                    addrtxt.setText(adslist.get(0).getAddress());
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                utils.set_snackbar(view, "Server connection failed", getApplicationContext(), "error");
            }
        });
    }

    @Override
    public void onNetworkConnected(boolean isConnected) {
        if (isConnected) {
            no_network.setVisibility(View.GONE);
            fetchData();
        } else {
            no_network.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
        if (utils.isconnected(getApplicationContext())) {
            no_network.setVisibility(View.GONE);
            fetchData();
        } else {
            utils.set_snackbar(view, "Please connect to the internet", getApplicationContext(), "warning");
            no_network.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private void setNoNetwork(String header) {
        Button btnview;
        TextView errorv;
        View wizardView = getLayoutInflater()
                .inflate(R.layout.server_error, dynamiclayout, false);
        dynamiclayout.addView(wizardView);
        errorv = findViewById(R.id.dynamic_content).findViewById(R.id.error_head);
        errorv.setText(header);
        btnview = findViewById(R.id.dynamic_content).findViewById(R.id.btntryagain);
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
                dynamiclayout.removeView(wizardView);
            }
        });
    }
}
