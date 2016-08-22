package com.hbdworld.test4;

import android.app.Activity;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity {


    private static final String ACTION_SECOND = "com.hbdworld.test4.intent.action.SECOND_ACTION";
    private static final String CATEGORY_SECOND = "com.hbdworld.test4.intent.category.SECOND_CATEGORY";
    private static final String ACTION_HELLO = "helloWorld";
    final int PICK_CONTACT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        //--
        Intent intent = new Intent(this,SecondActivity.class);
        intent.setData(Uri.parse("tab://1"));
        TabHost tabHost = this.getTabHost();
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("已接电话")
                .setContent(intent));

        intent = new Intent(this,SecondActivity.class);
        intent.setData(Uri.parse("tab://2"));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("未接电话")
                .setContent(intent));

        intent = new Intent(this,SecondActivity.class);
        intent.setData(Uri.parse("tab://3"));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("无接电话")
                .setContent(intent));

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
