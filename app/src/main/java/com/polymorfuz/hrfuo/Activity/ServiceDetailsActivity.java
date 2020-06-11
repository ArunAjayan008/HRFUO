package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.model.Profile;
import com.polymorfuz.hrfuo.model.ServiceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceDetailsActivity extends AppCompatActivity {
    TextView doj_txt, desig_txt, curnt_desig_txt, dor_txt, last_promotxt, next_promotxt;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevice_details);
        id = new SharedPrefManager(getApplicationContext()).readString("id", null);
        desig_txt = findViewById(R.id.desig_on_join_sda_txt);
        doj_txt = findViewById(R.id.doj_sda_txt);
        curnt_desig_txt = findViewById(R.id.curnt_desig_sda_txt);
        dor_txt = findViewById(R.id.dor_sda_txt);
        last_promotxt = findViewById(R.id.dolast_promo_sda_txt);
        next_promotxt = findViewById(R.id.date_next_promo_sda_txt);
        fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<ServiceModel>> call = api.getservice(id);

        call.enqueue(new Callback<List<ServiceModel>>() {
            @Override
            public void onResponse(Call<List<ServiceModel>> call, Response<List<ServiceModel>> response) {
                List<ServiceModel> adslist = response.body();
                desig_txt.setText(adslist.get(0).getDesig_on_join());
                doj_txt.setText(adslist.get(0).getDoj());
                curnt_desig_txt.setText(adslist.get(0).getCurrent_desig());
                dor_txt.setText(adslist.get(0).getDor());
                last_promotxt.setText(adslist.get(0).getDate_of_lastprom());
                next_promotxt.setText(adslist.get(0).getNext_promotion());
            }

            @Override
            public void onFailure(Call<List<ServiceModel>> call, Throwable t) {
                Toast.makeText(ServiceDetailsActivity.this, "" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}