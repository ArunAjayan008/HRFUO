package com.polymorfuz.hrfuo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileViewActivity extends AppCompatActivity {
TextView nametxt,agetxt,gendertxt,qualtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        nametxt=findViewById(R.id.profilename_pva_txt);
        agetxt=findViewById(R.id.age_pva_txt);
        gendertxt=findViewById(R.id.gender_pva_txt);
        qualtxt=findViewById(R.id.qual_pva_txt);
        fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Profile>> call = api.getstatus();

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                List<Profile> adslist = response.body();

                String name = adslist.get(0).getName();
                String age = adslist.get(0).getAge();
                String gender = adslist.get(0).getGender();
                String qualify = adslist.get(0).getQualify();

                nametxt.setText(name);
                agetxt.setText(age);
                gendertxt.setText(gender);
                qualtxt.setText(qualify);
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Toast.makeText(ProfileViewActivity.this, "" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
