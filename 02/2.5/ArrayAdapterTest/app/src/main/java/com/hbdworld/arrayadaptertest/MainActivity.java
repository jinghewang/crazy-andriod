package com.hbdworld.arrayadaptertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        ListView list1 = (ListView) this.findViewById(R.id.list1);
        ListView list2 = (ListView) this.findViewById(R.id.list2);
        String[] arr1 = {"孙悟空", "猪八戒", "牛魔王"};
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.array_item, arr1);
        list1.setAdapter(adapter);

        String[] arr2 = { "Java", "Hibernate", "Spring" , "Android" };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.checked_item,arr2);
        list2.setAdapter(adapter2);


    }
}
