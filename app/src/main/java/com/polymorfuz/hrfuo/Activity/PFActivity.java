package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.EPFModel;
import com.polymorfuz.hrfuo.model.Profile;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PFActivity extends AppCompatActivity {
    WebView webView;
    View view;
    String id;
    UtilityMethods utils = new UtilityMethods();
    String uan, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_f);
        view = getWindow().getDecorView().getRootView();
        id = new SharedPrefManager(getApplicationContext()).readString("id", null);
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        if (utils.isconnected(getApplicationContext())) {
            fetchData();}
        else {
            utils.set_snackbar(view,"Please connect to the internet", getApplicationContext(), "warning");
        }
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<EPFModel>> call = api.getPF(id);

        call.enqueue(new Callback<List<EPFModel>>() {
            @Override
            public void onResponse(Call<List<EPFModel>> call, Response<List<EPFModel>> response) {
                List<EPFModel> pflist = response.body();
                assert pflist != null;
                uan = pflist.get(0).getUan();
                password = pflist.get(0).getPassword();
                if (uan != null && password != null) {
                    loadweb();
                }
            }

            @Override
            public void onFailure(Call<List<EPFModel>> call, Throwable t) {
                utils.set_snackbar(view, "Server connection failed", getApplicationContext(), "error");
            }
        });
    }

    private void loadweb() {
        webView.loadUrl("https://passbook.epfindia.gov.in/MemberPassBook/Login");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                final String js = "javascript:" +
                        "document.getElementById('password').value = '" + password + "';" +
                        "document.getElementById('username').value = '" + uan + "';";

                view.evaluateJavascript(js, s -> {

                });
            }
        });
    }
}
