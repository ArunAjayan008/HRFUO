package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.IMyService;
import com.polymorfuz.hrfuo.Retrofit.RetrofitClient;

import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SignUp_Activity extends AppCompatActivity {
    EditText name, mobno, password;
    LinearLayout login;
    Button signup;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iservice;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppLocale("ml");
        setContentView(R.layout.activity_sign_up);
        Retrofit retrofitclient = RetrofitClient.getInstance();
        iservice = retrofitclient.create(IMyService.class);
        name = findViewById(R.id.username);
        mobno = findViewById(R.id.mobno);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.btn_sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(name.getText().toString(),
                        mobno.getText().toString(),
                        password.getText().toString());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
            }
        });
    }

    private void setAppLocale(String localcode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localcode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    private void registerUser(String name, String mob, String pwd) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(mob)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        compositeDisposable.add(iservice.registerUser(name, mob, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(SignUp_Activity.this, "" + response, Toast.LENGTH_LONG).show();
                    }
                }));
    }
}