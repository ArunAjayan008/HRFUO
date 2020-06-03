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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Login_Activity extends AppCompatActivity {
    Button login;
    EditText mobile, passwd;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iservice;
    ConstraintLayout mainlayout;

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
                imm.hideSoftInputFromWindow(mainlayout.getWindowToken(), 0);
                loginUser(mobile.getText().toString(),
                        passwd.getText().toString(),v);
            }
        });
    }

    private void loginUser(String mobno, String password,View view) {
        if (TextUtils.isEmpty(mobno)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        compositeDisposable.add(iservice.loginUser(mobno, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        response = response.substring(1, response.length() - 1);
                    if (!(response.equals("contract")||response.equals("apprentice")||response.equals("permanent"))) {
                            Snackbar snackbar = Snackbar.make(view, response, Snackbar.LENGTH_SHORT);
                            snackbar.setActionTextColor(getResources().getColor(R.color.colorRed));
                            snackbar.show();
                        } else {
//                        Toast.makeText(Login_Activity.this, "" + response, Toast.LENGTH_LONG).show();
                            redirect(response);
                        }
                    }
                }));
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