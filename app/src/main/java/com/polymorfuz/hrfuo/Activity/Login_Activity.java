package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView forgot_pwd, sign_up;
    EditText mobile, passwd;
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
        setContentView(R.layout.activity_login);
        Retrofit retrofitclient = RetrofitClient.getPostInstance();
        iservice = retrofitclient.create(IMyService.class);
        mobile = findViewById(R.id.username);
        passwd = findViewById(R.id.password);
        login = findViewById(R.id.btnlogin);
        login.setOnClickListener(v -> {
            loginUser(mobile.getText().toString(),
                    passwd.getText().toString());
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
        });
    }

    private void loginUser(String mobno, String pwd) {
        if (TextUtils.isEmpty(mobno)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        compositeDisposable.add(iservice.loginUser(mobno, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(Login_Activity.this, "" + response, Toast.LENGTH_LONG).show();
                    }
                }));
    }
}