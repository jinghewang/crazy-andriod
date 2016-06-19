package com.hbdworld.framelayouttest;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6
    };

    final int[] names = new int[]{
            R.id.text01,
            R.id.text02,
            R.id.text03,
            R.id.text04,
            R.id.text05,
            R.id.text06
    };

     int currentColor = 0;

    final TextView[] textViews = new TextView[names.length];

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                for (int i = 0; i < names.length; i++) {
                    //textViews[i].setText(i);

                    //Toast.makeText(this,"ddd",0).show();
                    //textViews[i].setBackgroundColor(colors[(i + currentColor) % names.length]);
                    //Log.println(1,"ddd","dds");
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-
        for (int i=0 ;i< names.length ;i++){
            textViews[i] = (TextView)this.findViewById(names[i]);
        }
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(0x123);
//            }
//        },200,1000);
    }
}
