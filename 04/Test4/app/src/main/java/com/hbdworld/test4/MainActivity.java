package com.hbdworld.test4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final String ACTION_SECOND = "com.hbdworld.test4.intent.action.SECOND_ACTION";
    private static final String CATEGORY_SECOND = "com.hbdworld.test4.intent.category.SECOND_CATEGORY";
    private static final String ACTION_HELLO = "helloWorld";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--

        Button start = (Button)this.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.ACTION_SECOND);
                startActivity(intent);
            }
        });

        Button start2 = (Button)this.findViewById(R.id.start2);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.ACTION_HELLO);
                startActivity(intent);
            }
        });

        Button start3 = (Button)this.findViewById(R.id.start3);
        start3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.ACTION_SECOND);
                intent.addCategory(CATEGORY_SECOND);
                startActivity(intent);
            }
        });
    }
}
