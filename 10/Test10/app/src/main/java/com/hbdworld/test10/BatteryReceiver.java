package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {

    public BatteryReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"----BatteryReceiver----",Toast.LENGTH_SHORT).show();
    }
}
