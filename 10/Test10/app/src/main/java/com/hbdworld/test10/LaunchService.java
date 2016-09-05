package com.hbdworld.test10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LaunchService extends Service {

    public LaunchService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(LaunchService.this, new Date().toString(), Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }, 0, 2000);

    }
}
