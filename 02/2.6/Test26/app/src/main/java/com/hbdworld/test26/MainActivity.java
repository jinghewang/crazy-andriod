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
import android.widget.NumberPicker;
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
    NumberPicker np1, np2;
    // 定义最低价格、最高价格的初始值
    int minPrice = 25, maxPrice = 75;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        np1 = (NumberPicker) findViewById(R.id.numberPicker);
        // 设置np1的最小值和最大值
        np1.setMinValue(10);
        np1.setMaxValue(50);
        // 设置np1的当前值
        np1.setValue(minPrice);
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            // 当NumberPicker的值发生改变时，将会激发该方法
            @Override
            public void onValueChange(NumberPicker picker,
                                      int oldVal, int newVal)
            {
                minPrice = newVal;
                showToast(String.valueOf(oldVal));
            }
        });
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        switch (btn.getId()){
            case R.id.send:
                break;

            case R.id.cancel:
                break;

            default:
                break;
        }
    }


    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
                .show();
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
