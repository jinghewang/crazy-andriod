package com.hbdworld.test4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //--
        TextView textView = new TextView(this);
        textView.setText(this.getIntent().getAction());
        this.setContentView(textView);
    }
}
