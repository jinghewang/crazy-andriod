package com.hbdworld.test10;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.rtp.AudioStream;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    Calendar calendar = Calendar.getInstance();
    AlarmManager alarmManager;

    boolean isMute = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.earth);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        mediaPlayer.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
            @Override
            public void onTimedText(MediaPlayer mediaPlayer, TimedText timedText) {
                showToast("" + timedText);
            }
        });


        ToggleButton toggleButton = (ToggleButton) this.findViewById(R.id.mute);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, checked);
            }
        });

        this.bindOnClickListener(this, R.id.play, R.id.stop, R.id.up, R.id.down, R.id.mute, R.id.vibrator, R.id.setTime);
    }


    @Override
    public void onClick(View view) {
        //showToast("----onClick:" + view.getId());
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.play:
                mediaPlayer.start();
                break;

            case R.id.stop:
                mediaPlayer.stop();
                break;

            case R.id.up:
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                break;

            case R.id.down:
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                break;

            case R.id.vibrator:
                showToast("振动2秒");
                vibrator.vibrate(2000);
                break;


            case R.id.setTime:
                final Calendar currentTime = Calendar.getInstance();
                new TimePickerDialog(MainActivity.this, 0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showToast(String.format("hour:%d  minute:%d",hourOfDay,minute));
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        Intent intent = new Intent(MainActivity.this,AlarmActivity.class) ;
                        PendingIntent pendingIntent =  PendingIntent.getActivity(MainActivity.this,1,intent,0);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                        showToast("设置闹钟成功");
                    }
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), false).show();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        showToast("手机振动");
        vibrator.vibrate(1000);
        return super.onTouchEvent(event);
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
