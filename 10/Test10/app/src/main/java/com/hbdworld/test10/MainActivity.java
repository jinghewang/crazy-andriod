package com.hbdworld.test10;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BindService.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            System.out.println("--Service Connected--");
            binder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("--Service Disconnected--");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.bindOnClickListener(this,R.id.bind, R.id.unbind, R.id.getServiceStatus);
    }

    @Override
    public void onClick(View view) {
        showToast("----onClick:" + view.getId());
        Button btn = (Button) view;
        Intent intent = new Intent(MainActivity.this, BindService.class);
        switch (btn.getId()) {
            case R.id.bind:
                boolean result = bindService(intent, conn, Service.BIND_AUTO_CREATE);
                break;

            case R.id.unbind:
                unbindService(conn);
                break;

            case R.id.getServiceStatus:
                Toast.makeText(MainActivity.this, "Service的count值为：" + binder.getCount(), Toast.LENGTH_SHORT).show();  // ②
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
