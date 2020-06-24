package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;

import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.Profile;

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

public class MainActivity extends AppCompatActivity{
    CardView profile_card, services_card, salary_card, leave_card, notify_card, other_card;
    View logid;
    String type;
    NetworkChangeReceiver receiver;
    UtilityMethods utils = new UtilityMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_card = findViewById(R.id.profile_btn_ma);
        services_card = findViewById(R.id.service_btn_ma);
        salary_card = findViewById(R.id.salary_btn_ma);
        leave_card = findViewById(R.id.leave_btn_ma);
        notify_card = findViewById(R.id.notify_btn_ma);
        other_card = findViewById(R.id.others_btn_ma);
        logid = findViewById(R.id.toolbar).findViewById(R.id.logid);
        type = new SharedPrefManager(getApplicationContext()).readString("type", null);
        profile_card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ProfileViewActivity.class)));
        services_card.setOnClickListener(v -> {
//            if (type.equals("casuals")) {
//                startActivity(new Intent(getApplicationContext(), Casual_ServiceActivity.class));
//            } else if (type.equals("permanent")) {
                startActivity(new Intent(getApplicationContext(), ServiceDetailsActivity.class));
//            }
        });
        salary_card.setOnClickListener(v -> {
//            if (type.equals("casuals")) {
//                startActivity(new Intent(getApplicationContext(), CasualSalaryActivity.class));
//            } else if (type.equals("permanent")) {
                startActivity(new Intent(getApplicationContext(), SalaryActivity.class));
//            }
        });
        leave_card.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), LeaveActivity.class));

        });
        notify_card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NotificationListActivity.class)));
        other_card.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), EPF_ESI_Activity.class)));
        logid.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Logged User", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}
