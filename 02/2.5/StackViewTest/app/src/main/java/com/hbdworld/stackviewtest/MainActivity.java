package com.hbdworld.stackviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    StackView stackView;
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

        stackView = (StackView)this.findViewById(R.id.sv);
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageIds.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.cell,new String[]{"image"},new int[]{R.id.image1});
        stackView.setAdapter(simpleAdapter);

        Button btn1 = (Button)this.findViewById(R.id.btnNext);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackView.showNext();
            }
        });

        Button btn2 = (Button)this.findViewById(R.id.btnPrevew);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackView.showPrevious();
            }
        });
    }
}
