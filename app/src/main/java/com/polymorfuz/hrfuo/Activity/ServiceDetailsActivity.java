package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

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

public class ServiceDetailsActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    TextView doj_txt, desig_txt, curnt_desig_txt, dor_txt, last_promotxt, next_promotxt;
    String id;
    NetworkChangeReceiver receiver;
    UtilityMethods utils = new UtilityMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevice_details);
        receiver = new NetworkChangeReceiver(this);
        desig_txt = findViewById(R.id.desig_on_join_sda_txt);
        doj_txt = findViewById(R.id.doj_sda_txt);
        curnt_desig_txt = findViewById(R.id.curnt_desig_sda_txt);
        dor_txt = findViewById(R.id.dor_sda_txt);
        last_promotxt = findViewById(R.id.dolast_promo_sda_txt);
        next_promotxt = findViewById(R.id.date_next_promo_sda_txt);

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

        Call<List<ServiceModel>> call = api.getservice();

        call.enqueue(new Callback<List<ServiceModel>>() {
            @Override
            public void onResponse(Call<List<ServiceModel>> call, Response<List<ServiceModel>> response) {
                List<ServiceModel> adslist = response.body();
                desig_txt.setText(adslist.get(0).getDesig_on_join());
                doj_txt.setText(adslist.get(0).getDoj());
                curnt_desig_txt.setText(adslist.get(0).getCurrent_desig());
                dor_txt.setText(adslist.get(0).getDor());
                last_promotxt.setText(adslist.get(0).getDate_of_lastprom());
                next_promotxt.setText(adslist.get(0).getNext_promotion());
            }

            @Override
            public void onFailure(Call<List<ServiceModel>> call, Throwable t) {
                Toast.makeText(ServiceDetailsActivity.this, "" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
        if (utils.isconnected(getApplicationContext())) {
//            no_network.setVisibility(View.GONE);
            fetchData();
        } else {
//            utils.set_snackbar(view, "Please connect to the internet", getApplicationContext(), "warning");
//            no_network.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    public void onNetworkConnected(boolean isConnected) {
        if (isConnected) {
//            no_network.setVisibility(View.GONE);
            fetchData();
        } else {
//            no_network.setVisibility(View.VISIBLE);
        }
    }
}