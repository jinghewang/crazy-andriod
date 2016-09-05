package com.hbdworld.test10;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.BindException;

public class MusicService extends Service {

    MyReceiver serviceReceiver;
    AssetManager am;
    String[] musics = new String[]{"wish.mp3", "promise.mp3", "beautiful.mp3"};
    MediaPlayer mPlayer;
    // 当前的状态，0x11代表没有播放；0x12代表正在播放；0x13代表暂停
    int status = 0x11;
    // 记录当前正在播放的音乐
    int current = 0;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //create
        serviceReceiver = new MyReceiver();
        registerReceiver(serviceReceiver, new IntentFilter(MainActivity.CTL_ACTION));
        am = getAssets();


        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.e(MainActivity.TAG, "MusicService-onCompletion");
                current++;
                if (current >= 3) {
                    current = 0;
                }

                //intent
                Intent intent = new Intent(MainActivity.UPDATE_ACTION);
                intent.putExtra("current", current);
                sendBroadcast(intent);
                Log.e(MainActivity.TAG, "MusicService-sendBroadcast");

                //play
                prepareAndPlay(musics[current]);
            }
        });

    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra("control", -1);
            Log.e(MainActivity.TAG, "MyReceiver-onReceive");
            Log.e(MainActivity.TAG, "control:" + control);
            switch (control) {
                case 1:
                    if (status == 0x11) {
                        prepareAndPlay(musics[current]);
                        status = 0x12;
                    } else if (status == 0x12) {
                        mPlayer.pause();
                        status = 0x13;
                    } else if (status == 0x13) {
                        mPlayer.start();
                        status = 0x12;
                    }
                    break;

                case 2:
                    if (status == 0x12 || status == 0x13) {
                        mPlayer.stop();
                        status = 0x11;
                    }
                    break;

                case 3://next
                    current++;
                    if (current >= 3)
                        current = 0;
                    prepareAndPlay(musics[current]);
                    status = 0x12;
                    break;

                case 4://pre
                    current--;
                    if (current <= 0)
                        current = 0;
                    prepareAndPlay(musics[current]);
                    status = 0x12;
                    break;

                default:
                    break;
            }

            Intent sendIntent = new Intent(MainActivity.UPDATE_ACTION);
            sendIntent.putExtra("update", status);
            sendIntent.putExtra("current", current);
            sendBroadcast(sendIntent);
            Log.e(MainActivity.TAG, "MyReceiver-sendBroadcast");
        }
    }


    private void prepareAndPlay(String music) {
        try {
            AssetFileDescriptor afd = am.openFd(music);
            mPlayer.reset();
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
