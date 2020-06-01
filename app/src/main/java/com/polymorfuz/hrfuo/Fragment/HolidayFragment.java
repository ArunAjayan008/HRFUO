package com.polymorfuz.hrfuo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.polymorfuz.hrfuo.Adapter.HolidayAdapter;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.model.HolidayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HolidayFragment extends Fragment {
    RecyclerView holiday_recycler;
    HolidayAdapter adapter;
    List<HolidayModel>holidayModels;
    public HolidayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_holiday, container, false);
        holiday_recycler=view.findViewById(R.id.holiday_recycler);
        LinearLayoutManager lmgr=new LinearLayoutManager(getContext());
        holiday_recycler.setLayoutManager(lmgr);
        fetchdata();
        return view;
    }

    private void fetchdata(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);

        Call<List<HolidayModel>>call=api.getholidays();
        call.enqueue(new Callback<List<HolidayModel>>() {
            @Override
            public void onResponse(Call<List<HolidayModel>> call, Response<List<HolidayModel>> response) {
                holidayModels=response.body();
                adapter=new HolidayAdapter(getContext(),holidayModels);
                holiday_recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<HolidayModel>> call, Throwable t) {

            }
        });
    }
}
