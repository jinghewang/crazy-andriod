package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchReceiver extends BroadcastReceiver {
    public LaunchReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent tintent = new Intent(context,LaunchService.class);
        context.startService(tintent);
    }
}
