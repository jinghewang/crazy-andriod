package com.hbdworld.test4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startActivity, addFragment, backFragment, replaceFragment, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--
        startActivity = (Button) findViewById(R.id.startActivity);
        addFragment = (Button) findViewById(R.id.addFragment);
        backFragment = (Button) findViewById(R.id.backFragment);
        replaceFragment = (Button) findViewById(R.id.replaceFragment);
        finish = (Button) findViewById(R.id.finish);

        //启动对话框风格的Activity
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        // 加载目标Fragment
        addFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {

                LifecycleFragment fragment = new LifecycleFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragment)
                        .commit();

            }
        });
        // 替换目标Fragment、并加入Back栈
        backFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                SecondFragment fragment = new SecondFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragment)
                        .addToBackStack("aa")
                        .commit();
            }
        });
        // 为replaceFragment按钮绑定事件监听器
        replaceFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment fragment = new SecondFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });
        // 为finish按钮绑定事件监听器
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                MainActivity.this.finish();;
            }
        });


    }
}
