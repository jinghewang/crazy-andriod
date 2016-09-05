package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 获取界面中显示歌曲标题、作者文本框
    TextView title, author;
    // 播放/暂停、停止按钮
    ImageButton play, stop;
    ActivityReceiver activityReceiver;
    public static final String CTL_ACTION = "org.crazyit.action.CTL_ACTION";
    public static final String UPDATE_ACTION = "org.crazyit.action.UPDATE_ACTION";
    // 定义音乐的播放状态，0x11代表没有播放；0x12代表正在播放；0x13代表暂停
    int status = 0x11;
    String[] titleStrs = new String[]{"心愿", "约定", "美丽新世界"};
    String[] authorStrs = new String[]{"未知艺术家", "周蕙", "伍佰"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        title = (TextView) findViewById(R.id.title);
        author = (TextView) findViewById(R.id.author);

        //receiver
        activityReceiver = new ActivityReceiver();
        IntentFilter filter =  new IntentFilter();
        filter.addAction(UPDATE_ACTION);
        registerReceiver(activityReceiver,filter);

        //service
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        startService(intent);

        this.bindOnClickListener(this, R.id.play,R.id.stop);
    }


    public class ActivityReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        Intent intent = null;
        switch (btn.getId()) {
            case R.id.play:
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
