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
import com.polymorfuz.hrfuo.BroadcastReceiver.NetworkChangeReceiver;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.model.HolidayModel;

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

public class HolidayFragment extends Fragment implements NetworkChangeReceiver.NetworkListener {
    RecyclerView holiday_recycler;
    HolidayAdapter adapter;
    List<HolidayModel>holidayModels;
    String id;
    NetworkChangeReceiver receiver;
    public HolidayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_holiday, container, false);
        holiday_recycler=view.findViewById(R.id.holiday_recycler);
        receiver = new NetworkChangeReceiver(this);
        LinearLayoutManager lmgr=new LinearLayoutManager(getContext());
        holiday_recycler.setLayoutManager(lmgr);
        return view;
    }

    private void fetchdata(){
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
        Retrofit retrofit=new Retrofit.Builder()
                .client(client.build())
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

    @Override
    public void onStart() {
        super.onStart();
        fetchdata();
    }

    @Override
    public void onNetworkConnected(boolean isConnected) {

    }
}
