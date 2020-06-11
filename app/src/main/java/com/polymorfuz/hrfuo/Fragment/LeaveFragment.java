package com.polymorfuz.hrfuo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polymorfuz.hrfuo.Adapter.HolidayAdapter;
import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.Retrofit.Api;
import com.polymorfuz.hrfuo.Utilities.SharedPrefManager;
import com.polymorfuz.hrfuo.model.HolidayModel;
import com.polymorfuz.hrfuo.model.Leave;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaveFragment extends Fragment {
    TextView cltaken,eltaken,hpltaken,esitaken,absent,totalleave,bal_cl,bal_el,bal_hpl,tot_bal;
    String id;
    public LeaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_leave, container, false);
        id=new SharedPrefManager(getContext()).readString("mobno",null );
        cltaken=view.findViewById(R.id.cl_taken_leavfrag);
        eltaken=view.findViewById(R.id.el_taken_leavfrag);
        hpltaken=view.findViewById(R.id.hpl_taken_leavfrag);
        esitaken=view.findViewById(R.id.esi_taken_leavfrag);
        absent=view.findViewById(R.id.absent_leavfrag);
        totalleave=view.findViewById(R.id.total_taken);
        bal_cl=view.findViewById(R.id.cl_bal);
        bal_el=view.findViewById(R.id.el_bal);
        bal_hpl=view.findViewById(R.id.hpl_bal);
        tot_bal=view.findViewById(R.id.balance_leav);
        return view;
    }


    private void fetchdata(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);

        Call<List<Leave>> call=api.getleave(id);
        call.enqueue(new Callback<List<Leave>>() {
            @Override
            public void onResponse(Call<List<Leave>> call, Response<List<Leave>> response) {
                List<Leave>leaves=response.body();
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
            public void onFailure(Call<List<Leave>> call, Throwable t) {

            }
        });
    }
}
