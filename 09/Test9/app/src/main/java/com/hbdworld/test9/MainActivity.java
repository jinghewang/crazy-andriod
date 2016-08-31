package com.hbdworld.test9;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ContentResolver contentResolver = null;
    Uri uri = Uri.parse("content://com.hbdworld.t9_firstprovider.FirstProvider/");
    private static final String TAG = "HBD-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.w(TAG,"--create--");

        this.contentResolver = this.getContentResolver();
        this.bindOnClickListener(this,R.id.query,R.id.insert,R.id.update,R.id.delete);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        switch (btn.getId()){
            case R.id.query:
                Cursor c =  this.contentResolver.query(uri,null,"query_where",new String[]{"123","456"},null);
                showToast("远程ContentProvide-query返回的Cursor为：" + c);
                break;

            case R.id.insert:
                ContentValues values = new ContentValues();
                values.put("name","wjh");
                values.put("age",18);
                Uri newUri =  this.contentResolver.insert(uri,values);
                showToast("远程ContentProvide-insert返回的Cursor为：" + newUri);
                break;

            case R.id.update:
                ContentValues values2 = new ContentValues();
                values2.put("name", "wjh");
                values2.put("age", 18);
                int result = this.contentResolver.update(uri, values2, "update_where", null);
                showToast("远程ContentProvide-update返回的Cursor为：" + result);
                break;

            case R.id.delete:
                int result2 = this.contentResolver.delete(uri, "update_where", null);
                showToast("远程ContentProvide-delete返回的Cursor为：" + result2);
                break;

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
