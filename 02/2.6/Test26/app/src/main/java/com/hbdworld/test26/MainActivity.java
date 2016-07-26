package com.hbdworld.test26;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int[] imageIds = new int[]
            {
                    R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7
                    , R.drawable.bomb8, R.drawable.bomb9, R.drawable.bomb10
                    , R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13
                    , R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16
            };


    String[] strs = new String[]
            {
                    "疯狂Java讲义",
                    "轻量级Java EE企业应用实战",
                    "疯狂Android讲义",
                    "疯狂Ajax讲义"
            };

    NotificationManager nm;
    static final int NOTIFICATION_ID = 0x123;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //user
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Button send = (Button)this.findViewById(R.id.send);
        Button cancel = (Button)this.findViewById(R.id.cancel);
        send.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        Intent intent = new Intent(MainActivity.this,OtherActivity.class);
        PendingIntent pi =  PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        switch (btn.getId()){
            case R.id.send:
                Notification notify = new Notification.Builder(this)
                        .setAutoCancel(true)
                        .setTicker("你有新的消息123")
                        .setSmallIcon(R.drawable.notify)
                        .setContentTitle("一条新通知")
                        .setContentText("恭喜你，您加薪了，工资增加20%!")
                        .setSound(Uri.parse("android.resource://com.hbdworld.test26/" + R.raw.msg))
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pi)
                        .build();

                nm.notify(NOTIFICATION_ID,notify);
                break;

            case R.id.cancel:
                nm.cancel(NOTIFICATION_ID);
                break;

            default:
                break;
        }
    }


    @NonNull
    private List<Map<String, Object>> getMapList() {
        List<Map<String,Object>> items = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("image",imageIds[i]);
            items.add(item);
        }
        return items;
    }
}
