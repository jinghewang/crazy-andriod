package com.hbdworld.test10;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hbd on 16/9/3.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("---xx---");
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("-----endTime------" + endTime);
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("---耗时任务执行完成---");
        //return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
