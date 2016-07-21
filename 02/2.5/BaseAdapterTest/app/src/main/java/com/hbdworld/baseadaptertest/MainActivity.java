package com.hbdworld.baseadaptertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView list1 ;
    String[] books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        books = new String[20];
        for (int i =0;i<20;i++){
            books[i] = "books-" + String.valueOf(i);
        }

        //
        list1 = (ListView)this.findViewById(R.id.list1);

        ListAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return books.length;
            }

            @Override
            public Object getItem(int i) {
                return books[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.ok);
                TextView textView = new TextView(MainActivity.this);
                textView.setText(books[i]);
                textView.setTextSize(20);
                textView.setPadding(5,10,5,10);
                linearLayout.addView(imageView);
                linearLayout.addView(textView);
                return linearLayout;
            }
        };

        list1.setAdapter(adapter);

    }
}
