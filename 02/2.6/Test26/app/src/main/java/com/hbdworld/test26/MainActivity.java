package com.hbdworld.test26;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hbdworld.test26.bases.MyButton;
import com.hbdworld.test26.bases.MyClickListener;
import com.hbdworld.test26.bases.SendSmsListener;
import com.hbdworld.test26.utils.CrazyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int[] imageIds = new int[]
            {
                    R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7
                    , R.drawable.bomb8, R.drawable.bomb9, R.drawable.bomb10
                    , R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13
                    , R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16
            };


    static final String UPPER_NUM = "upper";
    EditText etNum;
    CalThread calThread;



    class CalThread extends Thread{

        public Handler mHandler;

        @Override
        public void run() {
            //super.run();

            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    //super.handleMessage(msg);

                    CrazyUtils.showToast(MainActivity.this,"loop");

                    if (msg.what == 0x123){
                        int num = msg.getData().getInt(UPPER_NUM);
                        CrazyUtils.showToast(MainActivity.this,String.valueOf(num));
                    }
                }
            };

            Looper.loop();
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum = this.getObject(EditText.class,R.id.etNum);
        calThread = new CalThread();
        calThread.start();
    }

    public void cal(View view){
        Message message = new Message();
        message.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,Integer.parseInt(etNum.getText().toString()));
        message.setData(bundle);
        calThread.mHandler.sendMessage(message);

    }


    public Button getButton(int id){
        return this.getObject(Button.class,id);
    }

    public EditText getTextView(int id){
        return this.getObject(EditText.class,id);
    }

    public <T> T getObject(Class<T> t, int id){
        return (T)this.findViewById(id);
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
