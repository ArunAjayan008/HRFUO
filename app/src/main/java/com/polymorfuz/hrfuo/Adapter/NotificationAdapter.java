package com.polymorfuz.hrfuo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polymorfuz.hrfuo.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotifyView> {
    Context context;

    public NotificationAdapter(Context context) {
        this.context=context;
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

    }


    @Override
    public int getItemCount() {
        return 2;
    }

    public class NotifyView extends RecyclerView.ViewHolder {
        public NotifyView(@NonNull View itemView) {
            super(itemView);
        }
    }


}
