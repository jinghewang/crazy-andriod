package com.hbdworld.test4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //--
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(this);
        textView.setText(this.getIntent().getAction());
        layout.addView(textView);

        if (this.getIntent().getCategories() != null){
            textView = new TextView(this);
            textView.setText(this.getIntent().getCategories().toString());
            layout.addView(textView);
        }

        this.setContentView(layout);
    }
}
