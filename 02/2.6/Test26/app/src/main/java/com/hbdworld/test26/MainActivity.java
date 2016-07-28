package com.hbdworld.test26;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.PopupWindow;
import android.widget.SearchView;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
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

    final static int MAX_PROGRESS = 100;
    // 该程序模拟填充长度为100的数组
    private int[] data = new int[50];
    // 记录进度对话框的完成百分比
    int progressStatus = 0;
    int hasData = 0;
    ProgressDialog pd1,pd2;
    // 定义一个负责更新的进度的Handler
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            // 表明消息是由该程序发送的
            if (msg.what == 0x123)
            {
                pd2.setProgress(progressStatus);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void showSpinner(View source)
    {
        // 调用静态方法显示环形进度条
        ProgressDialog.show(this, "任务执行中"
                , "任务执行中，请等待", false, true); // ①
    }

    public void showIndeterminate(View source)
    {
        pd1 = new ProgressDialog(MainActivity.this);
        // 设置对话框的标题
        pd1.setTitle("任务正在执行中");
        // 设置对话框显示的内容
        pd1.setMessage("任务正在执行中，敬请等待...");
        // 设置对话框能用“取消”按钮关闭
        pd1.setCancelable(true);
        // 设置对话框的进度条风格
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置对话框的进度条是否显示进度
        pd1.setIndeterminate(true);
        pd1.show(); // ②
    }

    public void showProgress(View source)
    {
        // 将进度条的完成进度重设为0
        progressStatus = 0;
        // 重新开始填充数组
        hasData = 0;
        pd2 = new ProgressDialog(MainActivity.this);
        pd2.setMax(MAX_PROGRESS);
        // 设置对话框的标题
        pd2.setTitle("任务完成百分比");
        // 设置对话框显示的内容
        pd2.setMessage("耗时任务的完成百分比");
        // 设置对话框不能用“取消”按钮关闭
        pd2.setCancelable(false);
        // 设置对话框的进度条风格
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置对话框的进度条是否显示进度
        pd2.setIndeterminate(false);
        pd2.show(); // ③
        new Thread()
        {
            public void run()
            {
                while (progressStatus < MAX_PROGRESS)
                {
                    // 获取耗时操作的完成百分比
                    progressStatus = MAX_PROGRESS
                            * doWork() / data.length;
                    // 发送空消息到Handler
                    handler.sendEmptyMessage(0x123);
                }
                // 如果任务已经完成
                if (progressStatus >= MAX_PROGRESS)
                {
                    // 关闭对话框
                    pd2.dismiss();
                }
            }
        }.start();
    }
    // 模拟一个耗时的操作
    public int doWork()
    {
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

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        switch (view.getId()){
            case R.id.close:
                break;

            default:
                break;
        }
    }


    public Button getButton( int id){
        return this.getObject(Button.class,id);
    }

    public <T> T getObject(Class<T> t, int id){
        return (T)this.findViewById(id);
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
