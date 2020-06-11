package com.polymorfuz.hrfuo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.IMyService;
import com.polymorfuz.hrfuo.Retrofit.RetrofitClient;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SignUp_Activity extends AppCompatActivity {
    EditText name, mobno, password;
    LinearLayout login;
    Button signup;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iservice;
    ConstraintLayout mainlayout;
    UtilityMethods utils = new UtilityMethods();
    View view;
    String token;
    ProgressBar bar;

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
        view = getWindow().getDecorView().getRootView();
        Retrofit retrofitclient = RetrofitClient.getPostInstance();
        iservice = retrofitclient.create(IMyService.class);
        gettoken();
        name = findViewById(R.id.username);
        mobno = findViewById(R.id.mobno);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.btn_sign_up);
        mainlayout = findViewById(R.id.mainlayout);
        bar = findViewById(R.id.progressBar);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide keyboard before going to next activity, otherwise causes error
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(mainlayout.getWindowToken(), 0);
                registerUser(name.getText().toString(),
                        mobno.getText().toString(),
                        password.getText().toString(), token);
            }
        });
        login.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Login_Activity.class)));

    }

    private void setAppLocale(String localcode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localcode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    private void gettoken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.d("getInstanceId failed", task.getException().toString());
                        return;
                    }

                    // Get new Instance ID token
                    token = Objects.requireNonNull(task.getResult()).getToken();
                    new SharedPrefManager(getApplicationContext()).saveString("token", token);
//                        registerUser(name.getText().toString(),
//                                mobno.getText().toString(),
//                                password.getText().toString(), token);
                });
    }

    private boolean registerUser(String name, String mob, String pwd, String token) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(mob)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (utils.isconnected(getApplicationContext())) {
            bar.setVisibility(View.VISIBLE);
            compositeDisposable.add(iservice.registerUser(name, mob, pwd, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<String>() {
                        @Override
                        public void onNext(String response) {
                            bar.setVisibility(View.GONE);
                            response = response.substring(1, response.length() - 1);
                            if (response.equals("unregistered")) {
                                utils.set_snackbar(view, "Mobile number not registered", getApplicationContext(), "error");
                            } else {
                                new SharedPrefManager(getApplicationContext()).saveString("mobno", mob);
                                redirect(response);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            bar.setVisibility(View.GONE);
                            utils.set_snackbar(view, "Server connection failed", getApplicationContext(), "error");
                        }

                        @Override
                        public void onComplete() {

                        }
                    }));

        } else {
            utils.set_snackbar(view, "Please connect to the internet", getApplicationContext(), "warning");
        }
        return true;
    }

    private void redirect(String res) {
        if (res.equals("contract")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else if (res.equals("apprentice")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else if (res.equals("permanent")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        finish();
    }
}