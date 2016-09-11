package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    ClientThread clientThread;
    Handler handler;
    TextView show;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--
        show = (TextView) this.findViewById(R.id.show);
        input = (EditText) this.findViewById(R.id.msg);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                if (msg.what == 0x123) {
                    show.append(msg.obj.toString());
                }
            }
        };
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();

        //--
        bindOnClickListener(this, R.id.play, R.id.stop);
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.play:
                try {
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = input.getText();
                    clientThread.revHandler.sendMessage(msg);
                    input.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.stop:
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
