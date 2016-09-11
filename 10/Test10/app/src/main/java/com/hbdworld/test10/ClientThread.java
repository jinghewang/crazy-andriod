package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutionException;

/**
 * Created by hbd on 16/9/11.
 */
public class ClientThread implements Runnable {

    private Handler handler;
    public Handler revHandler;
    Socket socket;
    BufferedReader br = null;
    OutputStream os = null;

    public ClientThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            socket = new Socket("192.168.1.100", 30000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = socket.getOutputStream();

            new Thread() {
                @Override
                public void run() {
                    String content = null;
                    try {
                        while ((content = br.readLine()) != null) {
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = content;
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            Looper.prepare();
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x345) {
                        try {
                            os.write((msg.obj.toString() + "\r\n").getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();

        } catch (SocketTimeoutException e1) {
            System.out.println("网络连接超时！！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
