package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.model.Deduct_Model;
import com.polymorfuz.hrfuo.model.EarningModel;
import com.polymorfuz.hrfuo.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SalaryActivity extends AppCompatActivity {
    Button buttonsubmit;
    Spinner month_spin, year_spin;
    TextView basic, da, hra, risk, travel, wash, earn_other_1, earn_other_2, earn_other_3, totalearn, pf, esi, f_adv, sifl, canteen, ded_other1, ded_other2, ded_other3, ded_other4, totalded, grandtotal;
    List<EarningModel> earnlist;
    List<Deduct_Model> deductlist;
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] year = {"2020", "2021"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        buttonsubmit = findViewById(R.id.buttonsubmit);
        month_spin = findViewById(R.id.spin_mnth);
        year_spin = findViewById(R.id.spin_year);
        ArrayAdapter<String> monthadapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
        month_spin.setAdapter(monthadapter);
        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, year);
        year_spin.setAdapter(year_adapter);
        da = findViewById(R.id.da_txt_salact);
        basic = findViewById(R.id.basic_txt_salact);
        hra = findViewById(R.id.hra_txt_salact);
        risk = findViewById(R.id.risk_txt_salact);
        travel = findViewById(R.id.travel_txt_salact);
        wash = findViewById(R.id.wash_txt_salact);
        earn_other_1 = findViewById(R.id.earn1_txt_salact);
        earn_other_2 = findViewById(R.id.earn2_txt_salact);
        earn_other_3 = findViewById(R.id.earn3_txt_salact);
        totalearn = findViewById(R.id.gross_txt_salact);
        pf = findViewById(R.id.epf_txt_sal_act);
        esi = findViewById(R.id.esi_txt_sal_act);
        f_adv = findViewById(R.id.festad_txt_sal_act);
        sifl = findViewById(R.id.sifl_txt_sal_act);
        canteen = findViewById(R.id.canteen_txt_sal_act);
        ded_other1 = findViewById(R.id.ded1_txt_sal_act);
        ded_other2 = findViewById(R.id.ded2_txt_sal_act);
        ded_other3 = findViewById(R.id.ded3_txt_sal_act);
        ded_other4 = findViewById(R.id.ded4_txt_sal_act);
        totalded = findViewById(R.id.totalded_txt_sal_act);
        grandtotal = findViewById(R.id.grandtotal_txt_salact);
        buttonsubmit.setOnClickListener(v -> {
            String month = month_spin.getSelectedItem().toString();
            String year = year_spin.getSelectedItem().toString();
            fetchData(month, year);
        });
    }

    private void fetchData(String month, String year) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<EarningModel>> call = api.getearning(month, year);
        call.enqueue(new Callback<List<EarningModel>>() {
            @Override
            public void onResponse(Call<List<EarningModel>> call, Response<List<EarningModel>> response) {
                earnlist = response.body();
            }

            @Override
            public void onFailure(Call<List<EarningModel>> call, Throwable t) {

            }
        });
        Call<List<Deduct_Model>> deductcall = api.getdeduct(month, year);
        deductcall.enqueue(new Callback<List<Deduct_Model>>() {
            @Override
            public void onResponse(Call<List<Deduct_Model>> call, Response<List<Deduct_Model>> response) {
                deductlist = response.body();
                setData(earnlist, deductlist);
            }

            @Override
            public void onFailure(Call<List<Deduct_Model>> call, Throwable t) {

            }
        });

    }

    private void setData(List<EarningModel> earnlist, List<Deduct_Model> deductlist) {
        basic.setText(earnlist.get(0).getBasicval());
        da.setText(earnlist.get(0).getDa());
        hra.setText(earnlist.get(0).getHra());
        risk.setText(earnlist.get(0).getRisk());
        travel.setText(earnlist.get(0).getRisk());
        wash.setText(earnlist.get(0).getWash());
        earn_other_1.setText(earnlist.get(0).getOther_1());
        earn_other_2.setText(earnlist.get(0).getOther_2());
        earn_other_3.setText(earnlist.get(0).getOther_3());
        totalearn.setText(earnlist.get(0).getTotal());
        pf.setText(deductlist.get(0).getPf());
        esi.setText(deductlist.get(0).getEsi());
        f_adv.setText(deductlist.get(0).getF_adv());
        sifl.setText(deductlist.get(0).getSifl());
        canteen.setText(deductlist.get(0).getCanteen());
        ded_other1.setText(deductlist.get(0).getOther1());
        ded_other2.setText(deductlist.get(0).getOther2());
        ded_other3.setText(deductlist.get(0).getOther3());
        ded_other4.setText(deductlist.get(0).getOther4());
        totalded.setText(deductlist.get(0).getTotal());
        int a=Integer.parseInt(totalearn.getText().toString());
        int b=Integer.parseInt(totalded.getText().toString());
        String total=String.valueOf(a-b);
        grandtotal.setText(total);
    }
}
