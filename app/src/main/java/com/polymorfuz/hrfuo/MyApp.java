package com.polymorfuz.hrfuo;

import android.app.Application;

import com.flurry.android.FlurryAgent;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "8YYCK75CDBJ857M3RDSK");
    }
}
