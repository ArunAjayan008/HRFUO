package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.Config;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CardView profile_card, services_card, salary_card, leave_card, notify_card, other_card;
    View logid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_card = findViewById(R.id.profile_btn_ma);
        services_card = findViewById(R.id.service_btn_ma);
        salary_card = findViewById(R.id.salary_btn_ma);
        leave_card = findViewById(R.id.leave_btn_ma);
        notify_card = findViewById(R.id.notify_btn_ma);
        other_card = findViewById(R.id.others_btn_ma);
        logid = findViewById(R.id.toolbar).findViewById(R.id.logid);

        GetID getID = new GetID();
        getID.execute();

        profile_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileViewActivity.class));
            }
        });
        services_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ServiceDetailsActivity.class));

            }
        });
        salary_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SalaryActivity.class));
            }
        });
        leave_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LeaveActivity.class));

            }
        });
        notify_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NextActivity.class));

            }
        });
        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PFActivity.class));

            }
        });
        logid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logged User", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class GetID extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            fetchData();
            return null;
        }
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String mobno = new SharedPrefManager(getApplicationContext()).readString("mobno", null);
        Api api = retrofit.create(Api.class);

        Call<String> empid = api.getID(mobno);
        empid.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String id = response.body();
                new SharedPrefManager(getApplicationContext()).saveString("id", id);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
