package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.IMyService;
import com.polymorfuz.hrfuo.Retrofit.RetrofitClient;

import Utilities.CheckInternet;
import Utilities.UtilityMethods;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
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

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofitclient = RetrofitClient.getPostInstance();
        iservice = retrofitclient.create(IMyService.class);
        mobile = findViewById(R.id.username);
        passwd = findViewById(R.id.password);
        login = findViewById(R.id.btnlogin);
        mainlayout = findViewById(R.id.mainlayout);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard before going to next activity, otherwise causes error
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(mainlayout.getWindowToken(), 0);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                loginUser(mobile.getText().toString(),
//                        passwd.getText().toString(), v);
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

        if (utils.connectionStatus(getApplicationContext())) {
            compositeDisposable.add(iservice.loginUser(mobno, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<String>() {
                        @Override
                        public void onNext(String response) {
                            response = response.substring(1, response.length() - 1);
                            if (!(response.equals("contract") || response.equals("apprentice") || response.equals("permanent"))) {
                               utils.set_snackbar(view, response,getApplicationContext(),"error");
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
        } else {
            utils.set_snackbar(view, "Please connect to the internet", getApplicationContext(), "warning");
        }

//        if (utils.connectionStatus(getApplicationContext())) {
//            compositeDisposable.add(iservice.loginUser(mobno, password)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Consumer<String>() {
//                        @Override
//                        public void accept(String response) throws Exception {
//                            response = response.substring(1, response.length() - 1);
//                            if (!(response.equals("contract") || response.equals("apprentice") || response.equals("permanent"))) {
//                               utils.set_snackbar(view, response,getApplicationContext(),"error");
//                            } else {
//                                redirect(response);
//                            }
//                        }
//                    }));
//        } else {
//           utils.set_snackbar(view, "Please connect to the internet",getApplicationContext(),"warning");
//        }
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