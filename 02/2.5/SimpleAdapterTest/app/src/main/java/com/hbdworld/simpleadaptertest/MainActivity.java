package com.hbdworld.simpleadaptertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    ListView mylist ;

    private String[] names = new String[]
            { "虎头", "弄玉", "李清照", "李白"};
    private String[] descs = new String[]
            { "可爱的小孩", "一个擅长音乐的女孩"
                    , "一个擅长文学的女性", "浪漫主义诗人"};
    private int[] imageIds = new int[]
            { R.drawable.tiger , R.drawable.nongyu
                    , R.drawable.qingzhao , R.drawable.libai};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylist = (ListView)this.findViewById(R.id.mylist);

        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("header",imageIds[i]);
            item.put("personName",names[i]);
            item.put("desc",descs[i]);
            list.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.simple_item,new String[]{"header","personName","desc"}, new int[]{R.id.header,R.id.name,R.id.desc});
        mylist.setAdapter(simpleAdapter);
    }
}
