package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {

    public BatteryReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        int current = bundle.getInt("level");
        // 获取总电量
        int total = bundle.getInt("scale");
        Toast.makeText(context, String.format("----BatteryReceiver--level:%s  scale:%s--", current, total), Toast.LENGTH_LONG).show();
    }
}
