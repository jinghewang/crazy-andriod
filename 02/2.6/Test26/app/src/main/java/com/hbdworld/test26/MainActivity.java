package com.hbdworld.test26;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    // 该程序模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    // 记录ProgressBar的完成进度
    int status = 0;
    ProgressBar bar , bar2;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x111)
                bar.setProgress(status);
            //super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        bar = (ProgressBar)this.findViewById(R.id.progressBar);
        new Thread(){
            @Override

            public void run() {
                //super.run();

                while (status < 100){
                    status = doWork();
                    handler.sendEmptyMessage(0x111);
                }
            }
        }.start();
    }

    public int doWork(){
        // 为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return hasData;
    }
}
