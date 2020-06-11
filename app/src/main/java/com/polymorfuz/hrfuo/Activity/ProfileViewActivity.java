package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Room.ProfileViewModel;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileViewActivity extends AppCompatActivity {
    TextView nametxt, agetxt, gendertxt, qualtxt, dobtxt, addrtxt, empidtxt, mobnotxt;
    ProfileViewModel viewModel;
    Button add;
    UtilityMethods utils=new UtilityMethods();
    View view;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        id=new SharedPrefManager(getApplicationContext()).readString("mobno",null );
        view=getWindow().getDecorView().getRootView();
        nametxt = findViewById(R.id.profilename_pva_txt);
        empidtxt = findViewById(R.id.empid_pva_txt);
        mobnotxt = findViewById(R.id.mobno_pva_txt);
        agetxt = findViewById(R.id.age_pva_txt);
        gendertxt = findViewById(R.id.gender_pva_txt);
        qualtxt = findViewById(R.id.qual_pva_txt);
        dobtxt = findViewById(R.id.dob_pva_txt);
        addrtxt = findViewById(R.id.addr_pva_txt);
        add = findViewById(R.id.add);
        add.setOnClickListener(v -> {
//            startActivity(new Intent(getApplicationContext(),NewActivity.class));
//            ProfileDB db = new ProfileDB("Soman", "15", "Male", "Mtech", "15-02-2017");
//            viewModel.insert(db);
        });
//        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
//        viewModel.getprofiledata().observe(this, new Observer<List<ProfileDB>>() {
//            @Override
//            public void onChanged(List<ProfileDB> profileDBS) {
//                setData(profileDBS);
//            }
//        });
        if (utils.isconnected(getApplicationContext())) {
        fetchData();}
        else {
            utils.set_snackbar(view,"Please connect to the internet", getApplicationContext(), "warning");
        }
    }

//    private void setData(List<ProfileDB> db) {
//        if (db.size() > 0) {
//            ProfileDB data = db.get(0);
//            nametxt.setText(data.getEmpname());
//            agetxt.setText(data.getAge());
//            gendertxt.setText(data.getGender());
//            qualtxt.setText(data.getQualification());
//            dobtxt.setText(data.getDob());
//        }
//    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Profile>> call = api.getprofile(id);

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                List<Profile> adslist = response.body();
                assert adslist != null;
                nametxt.setText(adslist.get(0).getDesig());
                empidtxt.setText(adslist.get(0).getUserid());
                mobnotxt.setText(adslist.get(0).getMobno());
                agetxt.setText(adslist.get(0).getAge());
                dobtxt.setText(adslist.get(0).getDob());
                gendertxt.setText(adslist.get(0).getGender());
                qualtxt.setText(adslist.get(0).getEdu_qual());
                addrtxt.setText(adslist.get(0).getAddress());
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                utils.set_snackbar(view,"Server connection failed", getApplicationContext(), "error");
            }
        });
    }
}
