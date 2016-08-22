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
import android.widget.Toast;

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
        this.bindOnClickListener(
                this, R.id.scheme,
                R.id.schemeHostPath,
                R.id.schemeHostPort,
                R.id.schemeHostPortPath,
                R.id.schemeHostPortPathType,
                R.id.home,
                R.id.baidu
        );
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        Toast.makeText(MainActivity.this,"onClick",Toast.LENGTH_LONG).show();

        switch (view.getId()) {
            case R.id.scheme://匹配scheme的Intent
                intent = new Intent();
                intent.setData(Uri.parse("lee://"));
                startActivity(intent);
                break;

            case R.id.schemeHostPort://匹配scheme、host、port的Intent
                intent = new Intent();
                intent.setData(Uri.parse("lee://www.hbdworld.com.cn:8888"));
                startActivity(intent);
                break;

            case R.id.schemeHostPath://匹配scheme、host、path的Intent
                intent = new Intent();
                intent.setData(Uri.parse("lee://www.hbdworld.com.cn/mypath"));
                startActivity(intent);
                break;

            case R.id.schemeHostPortPath://匹配scheme、host、port、path的Intent
                intent = new Intent();
                intent.setData(Uri.parse("lee://www.hbdworld.com.cn:8888/mypath"));
                startActivity(intent);
                break;

            case R.id.schemeHostPortPathType://匹配scheme、host、port、path和type的Intent
                intent = new Intent();
                intent.setDataAndType(Uri.parse("lee://www.hbdworld.com.cn:8888/mypath"),"abc/xyz");
                startActivity(intent);
                break;

            case R.id.home://home
                intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                break;

            case R.id.baidu://baidu
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;

            default:
                Toast.makeText(MainActivity.this,"未绑定任何动作",Toast.LENGTH_LONG).show();
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
