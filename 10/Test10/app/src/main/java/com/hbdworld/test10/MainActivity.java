package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UTFDataFormatException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "HBD-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        registerReceiver(new BatteryReceiver(),new IntentFilter("android.intent.action.BATTERY_CHANGED"));

        this.bindOnClickListener(this, R.id.play, R.id.stop,R.id.battery);
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        Intent intent = new Intent(MainActivity.this,LaunchReceiver.class);
        switch (btn.getId()) {
            case R.id.play:
                intent.putExtra("control", "start");
                break;

            case R.id.stop:
                intent.putExtra("control", "stop");
                break;

            case R.id.battery:
                intent = new Intent("android.intent.action.BATTERY_CHANGED");
                intent.putExtra("control", "stop");
                break;
            default:
                break;
        }
        sendBroadcast(intent);
    }


    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public Button findButtonById(int view) {
        return (Button) this.findViewById(view);
    }

    public TextView findTextViewById(int view) {
        return (TextView) this.findViewById(view);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        try {
            return (T) this.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView2(Class<T> view, int id) {
        return (T) this.findViewById(id);
    }

    public void bindOnClickListener(View.OnClickListener listener, int... views) {
        for (int view : views) {
            View v = this.findViewById(view);
            v.setOnClickListener(listener);
        }
    }
}
