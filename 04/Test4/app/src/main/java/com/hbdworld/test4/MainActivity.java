package com.hbdworld.test4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String ACTION_SECOND = "com.hbdworld.test4.intent.action.SECOND_ACTION";
    private static final String CATEGORY_SECOND = "com.hbdworld.test4.intent.category.SECOND_CATEGORY";
    private static final String ACTION_HELLO = "helloWorld";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--
        this.bindOnClickListener(this, this, new int[]{R.id.start, R.id.start2, R.id.start3, R.id.bn, R.id.call, R.id.edit});
    }

    public Button findButtonById(int view) {
        return (Button) this.findViewById(view);
    }


    public void bindOnClickListener(Activity activity, View.OnClickListener listener, int[] views) {
        for (int view : views) {
            View v = activity.findViewById(view);
            v.setOnClickListener(listener);
        }
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

                break;

            case R.id.call:

                break;

            case R.id.edit:

                break;

            default:
                break;

        }
    }
}
