package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;

import java.util.Objects;

public class OnStartConfigActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    ProgressBar bar;
    LinearLayout dynamiclayout;
    UtilityMethods utility = new UtilityMethods();
    NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_start_config);
        receiver = new NetworkChangeReceiver(this);
        bar = findViewById(R.id.progressBar);
        dynamiclayout = findViewById(R.id.dynamic_content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
        if (utility.isconnected(getApplicationContext())) {
            utility.getAccesstoken(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            setNoNetwork();
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
            dynamiclayout.setVisibility(View.GONE);
            utility.getAccesstoken(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            setNoNetwork();
        }
    }
    private void setNoNetwork(){
        bar.setVisibility(View.GONE);
        View wizardView = getLayoutInflater()
                .inflate(R.layout.no_network, dynamiclayout, false);
        dynamiclayout.addView(wizardView);
    }
}