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
import com.polymorfuz.hrfuo.model.MonthlyLeaveModel;

import java.util.List;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.Viewholder> {
    Context context;
    List<MonthlyLeaveModel> leave;

    public LeaveAdapter(Context context, List<MonthlyLeaveModel> leave) {
        this.context = context;
        this.leave = leave;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_monthly_leave, parent, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        MonthlyLeaveModel leaveModel=leave.get(position);
        holder.slno.setText(String.valueOf(position+1));
        holder.leavedate.setText(leaveModel.getDate());
        holder.type.setText(leaveModel.getLeave_type());
    }

    @Override
    public int getItemCount() {
        return leave.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView leavedate, type,slno;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            leavedate=itemView.findViewById(R.id.leave_date);
            type=itemView.findViewById(R.id.type);
            slno=itemView.findViewById(R.id.slno);
        }
    }
}
