package com.hbdworld.test10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hbd on 16/9/3.
 */
public class FirstService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("--------onBind-------");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("--------onStartCommand-------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("-----onDestroy----------");
        super.onDestroy();
    }
}
