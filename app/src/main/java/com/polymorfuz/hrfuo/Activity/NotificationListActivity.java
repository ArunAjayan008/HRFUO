package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.polymorfuz.hrfuo.Adapter.NotificationAdapter;
import com.polymorfuz.hrfuo.R;

public class NotificationListActivity extends AppCompatActivity {
    NotificationAdapter adapter;
    RecyclerView notifycycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        notifycycler=findViewById(R.id.notify_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationListActivity.this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        notifycycler.setLayoutManager(layoutManager);
        adapter=new NotificationAdapter(getApplicationContext());
        notifycycler.setAdapter(adapter);
    }
}