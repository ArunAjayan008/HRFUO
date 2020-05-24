package com.polymorfuz.hrfuo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.model.HolidayModel;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.Viewholder> {
    Context context;
    List<HolidayModel> list;

    public HolidayAdapter(Context context, List<HolidayModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_adapter, parent, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayAdapter.Viewholder holder, int position) {
        HolidayModel holidays=list.get(position);
        holder.slno.setText(position+1);
        holder.date.setText(holidays.getDate());
        holder.desc.setText(holidays.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView slno,date, desc;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            slno=itemView.findViewById(R.id.slno);
            date=itemView.findViewById(R.id.date_txt);
            desc=itemView.findViewById(R.id.desc);
        }
    }
}
