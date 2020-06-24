package com.polymorfuz.hrfuo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.polymorfuz.hrfuo.Adapter.HolidayAdapter;
import com.polymorfuz.hrfuo.Adapter.LeaveAdapter;
import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.Utilities.UtilityMethods;
import com.polymorfuz.hrfuo.model.MonthlyLeaveModel;
import com.polymorfuz.hrfuo.model.LeaveSummaryModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaveFragment extends Fragment implements NetworkChangeReceiver.NetworkListener {
    NetworkChangeReceiver receiver;
    TextView cltaken, eltaken, hpltaken, esitaken, absent, totalleave, bal_cl, bal_el, bal_hpl;
    String id;
    RecyclerView leavecycler;
    Spinner year, month;
    Button submit;
    LeaveAdapter adapter;
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String[] years = {"2020", "2021"};
    List<MonthlyLeaveModel> leaveModels;
    UtilityMethods utils=new UtilityMethods();
    LinearLayout leavelayout;

    public LeaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        leavecycler = view.findViewById(R.id.leave_recycler);
        receiver = new NetworkChangeReceiver(this);
        LinearLayoutManager lmgr = new LinearLayoutManager(getContext());
        leavecycler.setLayoutManager(lmgr);
        leavelayout = view.findViewById(R.id.leave_show_layout);
        cltaken = view.findViewById(R.id.cl_taken_leavfrag);
        eltaken = view.findViewById(R.id.el_taken_leavfrag);
        hpltaken = view.findViewById(R.id.hpl_taken_leavfrag);
        esitaken = view.findViewById(R.id.esi_taken_leavfrag);
        absent = view.findViewById(R.id.absent_leavfrag);
        totalleave = view.findViewById(R.id.total_taken);
        bal_cl = view.findViewById(R.id.cl_bal);
        bal_el = view.findViewById(R.id.el_bal);
        bal_hpl = view.findViewById(R.id.hpl_bal);
        year = view.findViewById(R.id.spin_year);
        month = view.findViewById(R.id.spin_mnth);
        submit = view.findViewById(R.id.buttonsubmit);
        ArrayAdapter<String> monthadapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, months);
        month.setAdapter(monthadapter);
        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, years);
        year.setAdapter(year_adapter);
        submit.setOnClickListener(v -> {
            String monthval = month.getSelectedItem().toString();
            String yearval = year.getSelectedItem().toString();
            fetchLeave(monthval, yearval,view);
        });
        return view;
    }


    private void fetchdata() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<LeaveSummaryModel>> call = api.getleave(id);
        call.enqueue(new Callback<List<LeaveSummaryModel>>() {
            @Override
            public void onResponse(Call<List<LeaveSummaryModel>> call, Response<List<LeaveSummaryModel>> response) {
                List<LeaveSummaryModel> leaves = response.body();
                cltaken.setText(leaves.get(0).getCl());
                eltaken.setText(leaves.get(0).getEl());
                hpltaken.setText(leaves.get(0).getHpl());
                esitaken.setText(leaves.get(0).getEsi());
                absent.setText(leaves.get(0).getAbsent());
//                totalleave.setText(leaves.get(0).get());
//                bal_cl.setText(leaves.get(0).getl());
//                cltaken.setText(leaves.get(0).getCl());
//                cltaken.setText(leaves.get(0).getCl());
//                cltaken.setText(leaves.get(0).getCl());
            }

            @Override
            public void onFailure(Call<List<LeaveSummaryModel>> call, Throwable t) {

            }
        });
    }


    private void fetchLeave(String mnth, String year,View v) {
        id = new SharedPrefManager(getContext()).readString("accesstoken", null);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + id);
                return chain.proceed(newRequest.build());
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<MonthlyLeaveModel>> call = api.getleaves(mnth, year);
        if (utils.isconnected(getContext())) {
            call.enqueue(new Callback<List<MonthlyLeaveModel>>() {
                @Override
                public void onResponse(Call<List<MonthlyLeaveModel>> call, Response<List<MonthlyLeaveModel>> response) {
                    leavelayout.setVisibility(View.VISIBLE);
                    leaveModels = response.body();
                    adapter = new LeaveAdapter(getContext(), leaveModels);
                    leavecycler.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<MonthlyLeaveModel>> call, Throwable t) {

                }
            });
        } else {
            utils.set_snackbar(v, "Please connect to the internet", getContext(), "warning");
        }
    }

    @Override
    public void onNetworkConnected(boolean isConnected) {

    }
}
