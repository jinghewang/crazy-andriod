package com.hbdworld.test10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {


    MediaPlayer alarmMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alarmMusic = MediaPlayer.create(this, R.raw.earth);
        alarmMusic.setLooping(true);
        alarmMusic.start();

        new AlertDialog.Builder(AlarmActivity.this).setTitle("")
                .setMessage("闹钟响了")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarmMusic.stop();
                        AlarmActivity.this.finish();
                    }
                })
                .show();
    }
}
