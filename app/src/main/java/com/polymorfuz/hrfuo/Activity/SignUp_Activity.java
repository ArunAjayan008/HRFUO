package com.polymorfuz.hrfuo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.IMyService;
import com.polymorfuz.hrfuo.Retrofit.RetrofitClient;

import java.util.Locale;
import java.util.Objects;

import com.polymorfuz.hrfuo.Utilities.Config;
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
        Retrofit retrofitclient = RetrofitClient.getPostInstance();
        iservice = retrofitclient.create(IMyService.class);
        name = findViewById(R.id.username);
        mobno = findViewById(R.id.mobno);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.btn_sign_up);
        mainlayout = findViewById(R.id.mainlayout);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide keyboard before going to next activity, otherwise causes error
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(mainlayout.getWindowToken(), 0);
                registerUser(name.getText().toString(),
                        mobno.getText().toString(),
                        password.getText().toString(), view);
            }
        });
        login.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Login_Activity.class)));
        gettoken();
    }

    private void setAppLocale(String localcode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localcode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    private void registerUser(String name, String mob, String pwd, View view) {
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
        if (utils.connectionStatus(getApplicationContext())) {
            compositeDisposable.add(iservice.registerUser(name, mob, pwd)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<String>() {

                        @Override
                        public void onNext(String response) {
                            response = response.substring(1, response.length() - 1);
                            if (response.equals("unregistered")) {
                                utils.set_snackbar(view, "Mobile number not registered", getApplicationContext(), "error");
                            } else {
                                redirect(response);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            utils.set_snackbar(view, "Server connection failed",getApplicationContext(),"error");
                        }

                        @Override
                        public void onComplete() {

                        }
                    }));

        }else {
            utils.set_snackbar(view, "Please connect to the internet",getApplicationContext(),"warning");
        }
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


    private void gettoken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("getInstanceId failed", task.getException().toString());
                            return;
                        }

                        // Get new Instance ID token
                        String token = Objects.requireNonNull(task.getResult()).getToken();
                        storeRegIdInPref(token);
                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("tokennnnnnnnnnn",token);
                        Toast.makeText(SignUp_Activity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }
}