package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, String.format("action:%s msg:%s", action, msg), Toast.LENGTH_SHORT).show();
    }

}
