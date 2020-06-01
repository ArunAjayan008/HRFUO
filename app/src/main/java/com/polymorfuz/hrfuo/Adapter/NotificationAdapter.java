package com.polymorfuz.hrfuo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polymorfuz.hrfuo.R;
import com.polymorfuz.hrfuo.model.Notifications;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotifyView> {
    Context context;
    List<Notifications>notifylist;

    public NotificationAdapter(Context context,List<Notifications>list) {
        this.context=context;
        this.notifylist=list;
    }

    @NonNull
    @Override
    public NotifyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notify, parent, false);
        NotifyView holder = new NotifyView(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyView holder, int position) {
        Notifications list=notifylist.get(position);
        holder.title.setText(list.getTitle());
        holder.desc.setText(list.getDesc());
        holder.date_time.setText(list.getDate_time());
    }


    @Override
    public int getItemCount() {
       return notifylist.size();
    }

    public class NotifyView extends RecyclerView.ViewHolder {
        TextView title,desc,date_time;
        public NotifyView(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.desc);
            date_time=itemView.findViewById(R.id.date);
        }
    }

}
