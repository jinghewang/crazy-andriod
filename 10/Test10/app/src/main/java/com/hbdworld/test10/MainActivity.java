package com.hbdworld.test10;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<String> blockList = new ArrayList<>();


    // 判断某个电话号码是否在黑名单之内
    public boolean isBlock(String phone) {
        for (String s1 : blockList) {
            if (s1.equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.bindOnClickListener(this, R.id.read, R.id.write);
    }

    @Override
    public void onClick(View view) {
        //showToast("----onClick:" + view.getId());
        Button btn = (Button) view;
        TextView textView = (TextView)this.findViewById(R.id.msg);
        String fileName = getFilesDir() + "hbd.txt";
        Intent intent = null;
        String content = "";
        switch (btn.getId()) {
            case R.id.read:
                try {
                    InputStream instream = new FileInputStream("hbd.txt");
                    if (instream != null)
                    {
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line;
                        //分行读取
                        while (( line = buffreader.readLine()) != null) {
                            content += line + "\n";
                        }
                        instream.close();
                    }
                    textView.setText(content);
                    showToast("读取成功");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.write:
                try {
                    FileOutputStream fos = openFileOutput("hbd.txt", MODE_APPEND);
                    PrintStream ps = new PrintStream(fos);
                    ps.println(String.valueOf(System.currentTimeMillis()));
                    ps.close();
                    showToast("写入成功");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
