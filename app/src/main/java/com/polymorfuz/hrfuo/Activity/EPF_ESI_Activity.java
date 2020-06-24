package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.EPF_ESIModel;

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

public class EPF_ESI_Activity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    TextView esino, esi_empno, epfacno, uan, epf_pwd;
    Button pfbook;
    String uanval, pswd, id;
    UtilityMethods utils = new UtilityMethods();
    View view;
    NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epf_esi);
        receiver = new NetworkChangeReceiver(this);
        view = getWindow().getDecorView().getRootView();
        esino = findViewById(R.id.esi_no);
        esi_empno = findViewById(R.id.esi_empcode);
        epfacno = findViewById(R.id.epfacno);
        uan = findViewById(R.id.uan);
        epf_pwd = findViewById(R.id.epf_paswd);
        pfbook = findViewById(R.id.buttonsubmit);
        pfbook.setOnClickListener(v -> {
            if (uanval != null && pswd != null) {
                Intent intent = new Intent(getApplicationContext(), PFActivity.class);
                intent.putExtra("uan", uanval);
                intent.putExtra("password", pswd);
                startActivity(intent);
            }
        });
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
        if (utils.isconnected(getApplicationContext())) {

            Api api = retrofit.create(Api.class);

            Call<List<EPF_ESIModel>> call = api.getPF();

            call.enqueue(new Callback<List<EPF_ESIModel>>() {
                @Override
                public void onResponse(Call<List<EPF_ESIModel>> call, Response<List<EPF_ESIModel>> response) {
                    List<EPF_ESIModel> pflist = response.body();
                    assert pflist != null;
                    uanval = pflist.get(0).getUan();
                    pswd = pflist.get(0).getEpf_passwd();
                    setdata(pflist);
                }

                @Override
                public void onFailure(Call<List<EPF_ESIModel>> call, Throwable t) {
                    utils.set_snackbar(view, "Server connection failed", getApplicationContext(), "error");
                }
            });
        } else {
            utils.set_snackbar(view, "Please connect to the internet", getApplicationContext(), "warning");
        }
    }

    private void setdata(List<EPF_ESIModel> list) {
        esino.setText(list.get(0).getEsi_no());
        esi_empno.setText(list.get(0).getEsi_empno());
        epfacno.setText(list.get(0).getEpfacno());
        uan.setText(list.get(0).getUan());
        epf_pwd.setText(list.get(0).getEpf_passwd());
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
            utils.set_snackbar(view, "Please connect to the internet", getApplicationContext(), "warning");
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

    }

//    private void setNoNetwork() {
//        View wizardView = getLayoutInflater()
//                .inflate(R.layout.server_error, dynamiclayout, false);
//        dynamiclayout.addView(wizardView);
//        errorv = findViewById(R.id.dynamic_content).findViewById(R.id.error_head);
//        errorv.setText(header);
//        btnview = findViewById(R.id.dynamic_content).findViewById(R.id.btntryagain);
//        btnview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fetchData();
//                dynamiclayout.removeView(wizardView);
//            }
//        });
//    }
}