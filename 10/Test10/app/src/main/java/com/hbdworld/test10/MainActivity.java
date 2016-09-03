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

import com.android.internal.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TelephonyManager tManager;
    CustomPhoneCallListener cpListener;
    ArrayList<String> blockList = new ArrayList<>();

    public class CustomPhoneCallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String number) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;

                case TelephonyManager.CALL_STATE_RINGING:
                    if (isBlock(number)) {
                        try {
                            Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
                            IBinder binder = (IBinder) method.invoke(null, new Object[]{TELEPHONY_SERVICE});
                            //ITelephony telephony = ITelephony.Stub.asInterface(binder);
                            //telephony.endCall();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            super.onCallStateChanged(state, number);
        }
    }

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
        //--
        this.tManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        this.cpListener = new CustomPhoneCallListener();
        tManager.listen(cpListener,PhoneStateListener.LISTEN_CALL_STATE);
        this.bindOnClickListener(this, R.id.managerBlock);
    }

    @Override
    public void onClick(View view) {
        //showToast("----onClick:" + view.getId());
        Button btn = (Button) view;
        Intent intent = null;
        switch (btn.getId()) {
            case R.id.managerBlock:
                intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
                showToast(btn.getText().toString());
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
