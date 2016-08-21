package com.hbdworld.test4;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String ACTION_SECOND = "com.hbdworld.test4.intent.action.SECOND_ACTION";
    private static final String CATEGORY_SECOND = "com.hbdworld.test4.intent.category.SECOND_CATEGORY";
    private static final String ACTION_HELLO = "helloWorld";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--
        //Button btn = this.getView(R.id.bn);
        //Button btn2 = this.getView2(Button.class,R.id.bn);
        this.bindOnClickListener(this, R.id.start, R.id.start2, R.id.start3, R.id.bn, R.id.call, R.id.edit,R.id.component);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.start:
                intent = new Intent(MainActivity.ACTION_SECOND);
                startActivity(intent);
                break;

            case R.id.start2:
                intent = new Intent(MainActivity.ACTION_HELLO);
                startActivity(intent);
                break;

            case R.id.start3:
                intent = new Intent(MainActivity.ACTION_SECOND);
                intent.addCategory(CATEGORY_SECOND);
                startActivity(intent);
                break;

            case R.id.bn:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;

            case R.id.call:
                intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:13800138000"));
                startActivity(intent);
                break;

            case R.id.edit:

                break;

            case R.id.component:

                intent = new Intent();
                ComponentName componentName = new ComponentName(MainActivity.this,SecondActivity.class);
                intent.setComponent(componentName);
                startActivity(intent);

                break;

            default:
                break;

        }
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
