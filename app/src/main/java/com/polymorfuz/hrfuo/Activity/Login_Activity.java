package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.IMyService;
import com.polymorfuz.hrfuo.Retrofit.RetrofitClient;

import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Login_Activity extends AppCompatActivity {
    Button login;
    EditText mobile, passwd;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iservice;
    ConstraintLayout mainlayout;
    UtilityMethods utils = new UtilityMethods();
    ProgressBar bar;
    View view;
    String token;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gettoken();
        Retrofit retrofitclient = RetrofitClient.getPostInstance();
        view = getWindow().getDecorView().getRootView();
        iservice = retrofitclient.create(IMyService.class);
        mobile = findViewById(R.id.username);
        passwd = findViewById(R.id.password);
        login = findViewById(R.id.btnlogin);
        mainlayout = findViewById(R.id.mainlayout);
        bar = findViewById(R.id.progressBar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard before going to next activity, otherwise causes error
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(mainlayout.getWindowToken(), 0);
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                loginUser(mobile.getText().toString(),
                        passwd.getText().toString(), v);
            }
        });
    }

    private void loginUser(String mobno, String password, View view) {
        if (TextUtils.isEmpty(mobno)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if (utils.isconnected(getApplicationContext())) {
            bar.setVisibility(View.VISIBLE);
            compositeDisposable.add(iservice.loginUser(mobno, password,token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<String>() {
                        @Override
                        public void onNext(String response) {
                            bar.setVisibility(View.GONE);
                            response = response.substring(1, response.length() - 1);
                            if (!(response.equals("contract") || response.equals("apprentice") || response.equals("permanent"))) {
                                utils.set_snackbar(view, response, getApplicationContext(), "error");
                            } else {
                                new SharedPrefManager(getApplicationContext()).saveString("token", token);
                                new SharedPrefManager(getApplicationContext()).saveString("mobno", mobno);
                                new SharedPrefManager(getApplicationContext()).saveString("type", response);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
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
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}