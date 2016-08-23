package com.hbdworld.test8;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    TextView textView;
    // 记录当前的父文件夹
    File currentParent;
    // 记录当前路径下的所有文件的文件数组
    File[] currentFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--
        listView = (ListView) findViewById(R.id.list);
        textView = (TextView) findViewById(R.id.path);
        // 获取系统的SD卡的目录
        File root = new File("/mnt/sdcard/");
        if (root.exists()) {
            currentParent = root;
            currentFiles = root.listFiles();
            this.inflateListView(currentFiles);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = currentFiles[position];
                if (file.isFile()){
                    showToast("当前是文件");
                    return;
                }
                File[] tmp = file.listFiles();
                if (tmp == null || tmp.length == 0) {
                    Toast.makeText(MainActivity.this, "当前路径不可访问或该路径下没有文件", Toast.LENGTH_SHORT).show();
                } else {
                    currentParent = file;
                    currentFiles = tmp;
                    inflateListView(currentFiles);
                }
            }
        });

        this.bindOnClickListener(this, R.id.parent);
    }

    private void inflateListView(File[] files)  // ①
    {
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (File file : files) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("icon", file.isDirectory() ? R.drawable.folder : R.drawable.file);
            item.put("fileName", file.getName());
            listItems.add(item);
        }
        ListAdapter list = new SimpleAdapter(this, listItems, R.layout.line, new String[]{"icon", "fileName"}, new int[]{R.id.icon, R.id.file_name});
        listView.setAdapter(list);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.parent:
                try {
                    if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")) {
                        // 获取上一级目录
                        currentParent = currentParent.getParentFile();
                        // 列出当前目录下所有文件
                        currentFiles = currentParent.listFiles();
                        // 再次更新ListView
                        inflateListView(currentFiles);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showToast(Integer.valueOf(1).toString());
                break;

            default:
                break;
        }
    }


    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public Button findButtonById(int view) {
        return (Button) this.findViewById(view);
    }

    public TextView findTextViewById(int view) {
        return (TextView) this.findViewById(view);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        try {
            return (T) this.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView2(Class<T> view, int id) {
        return (T) this.findViewById(id);
    }

    public void bindOnClickListener(View.OnClickListener listener, int... views) {
        for (int view : views) {
            View v = this.findViewById(view);
            v.setOnClickListener(listener);
        }
    }
}
