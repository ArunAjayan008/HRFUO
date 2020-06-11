package com.polymorfuz.hrfuo.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.polymorfuz.hrfuo.R;

public class UtilityMethods {
    public boolean isconnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        return connected;
    }

    public void set_snackbar(View view, String msg, Context context, String msgtype) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        View snackbarview = snackbar.getView();
        if (msgtype.equals("error")) {
            snackbarview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed));
        } else if (msgtype.equals("warning")) {
            snackbarview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorOrange));
        } else if (msgtype.equals("success")) {
            snackbarview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen));
        }
        snackbar.show();
    }
}
