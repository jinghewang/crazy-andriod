package com.hbdworld.gridviewtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    GridView grid;
    ImageView imageView;
    int[] imageIds = new int[]
            {
                    R.drawable.bomb5 , R.drawable.bomb6 , R.drawable.bomb7
                    , R.drawable.bomb8 , R.drawable.bomb9 , R.drawable.bomb10
                    , R.drawable.bomb11 , R.drawable.bomb12	, R.drawable.bomb13
                    , R.drawable.bomb14 , R.drawable.bomb15 , R.drawable.bomb16
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        grid = (GridView)this.findViewById(R.id.grid01);
        imageView = (ImageView)this.findViewById(R.id.imageView);

        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String,Object > listitem = new HashMap<>();
            listitem.put("image",imageIds[i]);
            list.add(listitem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.cell,new String[]{"image"},new int[]{R.id.image1});
        grid.setAdapter(simpleAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageView.setImageResource(imageIds[i]);
            }
        });

        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageView.setImageResource(imageIds[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
