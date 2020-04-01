package com.polymorfuz.hrfuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.polymorfuz.hrfuo.Adapter.NotificationAdapter;

public class NotificationListActivity extends AppCompatActivity {
    NotificationAdapter adapter;
    RecyclerView notifycycler;
    LinearLayout recyler_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        recyler_layout=findViewById(R.id.notify_recycler);
        notifycycler=findViewById(R.id.notify_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationListActivity.this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        RecyclerView.LayoutManager recylmgr=layoutManager;
        notifycycler.setLayoutManager(recylmgr);
        adapter=new NotificationAdapter(getApplicationContext());
        notifycycler.setAdapter(adapter);
    }
}