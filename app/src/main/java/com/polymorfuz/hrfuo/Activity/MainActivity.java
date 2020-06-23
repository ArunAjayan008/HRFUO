package com.polymorfuz.hrfuo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.Config;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

import java.util.List;

import BroadcastReceiver.NetworkChangeReceiver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    CardView profile_card, services_card, salary_card, leave_card, notify_card, other_card;
    View logid;
    String id, type;
    NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver=new NetworkChangeReceiver(this);
        profile_card = findViewById(R.id.profile_btn_ma);
        services_card = findViewById(R.id.service_btn_ma);
        salary_card = findViewById(R.id.salary_btn_ma);
        leave_card = findViewById(R.id.leave_btn_ma);
        notify_card = findViewById(R.id.notify_btn_ma);
        other_card = findViewById(R.id.others_btn_ma);
        logid = findViewById(R.id.toolbar).findViewById(R.id.logid);
        id = new SharedPrefManager(getApplicationContext()).readString("jwt", "null");
        type = new SharedPrefManager(getApplicationContext()).readString("type", null);
        startfetch();

        profile_card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ProfileViewActivity.class)));
        services_card.setOnClickListener(v -> {
            if (type.equals("casuals")) {
                startActivity(new Intent(getApplicationContext(), Casual_ServiceActivity.class));
            } else if (type.equals("permanent")) {
                startActivity(new Intent(getApplicationContext(), ServiceDetailsActivity.class));
            }
        });
        salary_card.setOnClickListener(v -> {
            if (type.equals("casuals")) {
                startActivity(new Intent(getApplicationContext(), CasualSalaryActivity.class));
            } else if (type.equals("permanent")) {
                startActivity(new Intent(getApplicationContext(), SalaryActivity.class));
            }
        });
        leave_card.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), LeaveActivity.class));

        });
        notify_card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NotificationListActivity.class)));
        other_card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), EPF_ESI_Activity.class)));
        logid.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Logged User", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onNetworkConnected(boolean isConnected) {
            startfetch();
    }

    private class GetID extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            fetchData();
            return null;
        }
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String mobno = new SharedPrefManager(getApplicationContext()).readString("mobno", null);
        Api api = retrofit.create(Api.class);

        Call<String> empid = api.getID(mobno);
        empid.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String id = response.body();
                new SharedPrefManager(getApplicationContext()).saveString("id", id);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void subscribeTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("hrfuo")
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        //                    sendtoserver();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private void startfetch() {
        if (id.equals("null")) {
            GetID getID = new GetID();
            getID.execute();
            subscribeTopic();
        }
//        Toast.makeText(getApplicationContext(),"lenkjdfskdfb",Toast.LENGTH_SHORT).show();
    }
}
