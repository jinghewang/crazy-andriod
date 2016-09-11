package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UTFDataFormatException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "HBD-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--


        //--
        bindOnClickListener(this, R.id.play, R.id.stop, R.id.battery);
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.play:

                new Thread() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Socket socket = null;
                        try
                        {
                            String message = ((TextView)MainActivity.this.findViewById(R.id.msg)).getText().toString();
                            socket = new Socket("192.168.89.28", 30000);
                            //向服务器发送消息
                            PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
                            out.println(message);

                            //接收来自服务器的消息
                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String msg = br.readLine();
                            showToast(msg);
                            Log.e(TAG,msg);

                            out.close();
                            br.close();
                            socket.close();
                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, e.toString());
                        }
                        Looper.loop();
                    }
                }.start();

                break;

            case R.id.stop:
                break;

            case R.id.battery:
                break;
            default:
                break;
        }
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
