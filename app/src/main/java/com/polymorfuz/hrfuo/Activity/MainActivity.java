package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.polymorfuz.hrfuo.R;

public class MainActivity extends AppCompatActivity {
CardView profile_card,services_card,salary_card,leave_card,notify_card,other_card;
View logid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_card=findViewById(R.id.profile_btn_ma);
        services_card=findViewById(R.id.service_btn_ma);
        salary_card=findViewById(R.id.salary_btn_ma);
        leave_card=findViewById(R.id.leave_btn_ma);
        notify_card=findViewById(R.id.notify_btn_ma);
        other_card=findViewById(R.id.others_btn_ma);
        logid=findViewById(R.id.toolbar).findViewById(R.id.logid);

        profile_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileViewActivity.class));
            }
        });
        services_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ServiceDetailsActivity.class));

            }
        });
        salary_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SalaryActivity.class));
            }
        });
        leave_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LeaveActivity.class));

            }
        });
        notify_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NotificationListActivity.class));

            }
        });
        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PFActivity.class));

            }
        });
        logid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Logged User",Toast.LENGTH_LONG).show();
            }
        });
    }
}
