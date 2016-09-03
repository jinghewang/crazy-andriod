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
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<String> blockList = new ArrayList<>();
    ListView showView;
    // 声明代表状态名的数组
    String[] statusNames;
    TelephonyManager tManager;
    ArrayList<String> statusValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.showView = (ListView) this.findViewById(R.id.show);
        this.bindOnClickListener(this, R.id.read, R.id.write);

        tManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        statusNames = this.getResources().getStringArray(R.array.statusNames);
        // 获取代表SIM卡状态的数组
        String[] simState = getResources().getStringArray(R.array.simState);
        // 获取代表电话网络类型的数组
        String[] phoneType = getResources().getStringArray(R.array.phoneType);

        // 获取设备编号
        statusValues.add(tManager.getDeviceId());
        // 获取系统平台的版本
        statusValues.add(tManager.getDeviceSoftwareVersion()
                != null ? tManager.getDeviceSoftwareVersion() : "未知");
        // 获取网络运营商代号
        statusValues.add(tManager.getNetworkOperator());
        // 获取网络运营商名称
        statusValues.add(tManager.getNetworkOperatorName());
        // 获取手机网络类型
        statusValues.add(phoneType[tManager.getPhoneType()]);
        // 获取设备所在位置
        statusValues.add(tManager.getCellLocation() != null ? tManager
                .getCellLocation().toString() : "未知位置");
        // 获取SIM卡的国别
        statusValues.add(tManager.getSimCountryIso());
        // 获取SIM卡序列号
        statusValues.add(tManager.getSimSerialNumber());
        // 获取SIM卡状态
        statusValues.add(simState[tManager.getSimState()]);

        ArrayList<Map<String, String>> status = new ArrayList<>();
        // 遍历statusValues集合，将statusNames、statusValues
        // 的数据封装到List<Map<String , String>>集合中
        for (int i = 0; i < statusValues.size(); i++)
        {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", statusNames[i]);
            map.put("value", statusValues.get(i));
            status.add(map);
        }
        // 使用SimpleAdapter封装List数据
        SimpleAdapter adapter = new SimpleAdapter(this, status,
                R.layout.line, new String[] { "name", "value" }
                , new int[] { R.id.name, R.id.value });
        // 为ListView设置Adapter
        showView.setAdapter(adapter);


    }


    @Override
    public void onClick(View view) {
        //showToast("----onClick:" + view.getId());
        Button btn = (Button) view;
        TextView textView = (TextView) this.findViewById(R.id.msg);
        String fileName = getFilesDir() + "hbd.txt";
        Intent intent = null;
        String content = "";
        switch (btn.getId()) {
            case R.id.read:
                break;

            case R.id.write:
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
