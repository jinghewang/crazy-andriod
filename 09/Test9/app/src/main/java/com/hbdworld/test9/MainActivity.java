package com.hbdworld.test9;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ContentResolver contentResolver = null;
    ListView show;
    TextView msg;

    private static final String TAG = "HBD-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.msg = (TextView)this.findViewById(R.id.msg);
        //this.bindOnClickListener(this, R.id.add, R.id.view);
        getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, new SmsObserver(new Handler()));
    }

    private class SmsObserver extends ContentObserver {

        public SmsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            //super.onChange(selfChange);
            Cursor c = getContentResolver().query(Uri.parse("content://sms/outbox"), null, null, null, null);
            while (c.moveToNext()) {
                String addr = c.getString(c.getColumnIndex("address"));
                String subject = c.getString(c.getColumnIndex("subject"));
                String body = c.getString(c.getColumnIndex("body"));
                Long date = c.getLong(c.getColumnIndex("date"));
                StringBuilder sb = new StringBuilder();
                sb.append("address=" + addr);
                sb.append("subject=" + subject);
                sb.append("body=" + body);
                sb.append("date=" + date.toString());

                System.out.println(sb.toString());
                showToast(sb.toString());
                msg.setText(msg.getText() + sb.toString());
            }
        }
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {

            default:
                showToast("----default----");
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
