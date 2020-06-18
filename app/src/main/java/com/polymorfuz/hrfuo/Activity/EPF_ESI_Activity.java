package com.polymorfuz.hrfuo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.polymorfuz.hrfuo.R;

public class EPF_ESI_Activity extends AppCompatActivity {
    TextView esino, esi_empno, epfacno, uan, epf_pwd;
    Button pfbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epf_esi);
        esino=findViewById(R.id.esi_no);
        esi_empno=findViewById(R.id.esi_empcode);
        epfacno=findViewById(R.id.epfacno);
        uan=findViewById(R.id.uan);
        epf_pwd=findViewById(R.id.epf_paswd);
        pfbook=findViewById(R.id.buttonsubmit);
    }
}