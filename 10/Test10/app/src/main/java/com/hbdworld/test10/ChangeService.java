package com.hbdworld.test10;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class ChangeService extends Service {

    WallpaperManager wallpaperManager;
    int[] wallpapers = new int[]{
            R.drawable.lijiang,
            R.drawable.shuangta,
            R.drawable.qiao,
            R.drawable.shui
    };
    int current = 0;


    public ChangeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (current >= 4)
            current = 0;

        try {
            wallpaperManager.setResource(this.wallpapers[current++]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(ChangeService.this, "command", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        wallpaperManager = WallpaperManager.getInstance(this);
    }
}
