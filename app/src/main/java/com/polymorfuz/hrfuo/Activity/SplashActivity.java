package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String id=new SharedPrefManager(getApplicationContext()).readString("authtoken",null);
        if(id!=null){
            startActivity(new Intent(getApplicationContext(),OnStartConfigActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(getApplicationContext(),SignUp_Activity.class));
            finish();
        }
    }
}
