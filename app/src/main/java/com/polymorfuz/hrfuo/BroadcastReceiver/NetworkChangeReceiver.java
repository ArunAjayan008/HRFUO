package com.polymorfuz.hrfuo.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.polymorfuz.hrfuo.Utilities.UtilityMethods;

public class NetworkChangeReceiver extends BroadcastReceiver {
    UtilityMethods utils = new UtilityMethods();
    private NetworkListener listener;

    public NetworkChangeReceiver(NetworkListener listener) {
        this.listener=listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        listener.onNetworkConnected(utils.isconnected(context));
//        if (utils.isconnected(context)) {
//            Toast.makeText(context, "Netconnected", Toast.LENGTH_SHORT).show();
//        }
    }

    public interface NetworkListener {
        void onNetworkConnected(boolean isConnected);
    }
}
