package com.hbdworld.test26;

import android.app.TabActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.SearchView;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int[] imageIds = new int[]
            {
                    R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7
                    , R.drawable.bomb8, R.drawable.bomb9, R.drawable.bomb10
                    , R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13
                    , R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16
            };


    String[] strs = new String[]
            {
                    "疯狂Java讲义",
                    "轻量级Java EE企业应用实战",
                    "疯狂Android讲义",
                    "疯狂Ajax讲义"
            };

    SearchView sv;
    ListView lv;
    private final String[] mStrings = { "aaaaa", "bbbbbb", "cccccc" };



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        Button simple = this.getObject(Button.class, R.id.simple);
        Button simpleList = this.getObject(Button.class, R.id.simpleList);
        Button singleChoice = this.getObject(Button.class, R.id.singleChoice);
        Button multiChoice = this.getButton(R.id.multiChoice);
        Button customList = this.getButton(R.id.customList);
        Button customView = this.getButton(R.id.customView);

        simple.setOnClickListener(this);
        simpleList.setOnClickListener(this);
        singleChoice.setOnClickListener(this);
        multiChoice.setOnClickListener(this);
        customList.setOnClickListener(this);
        customView.setOnClickListener(this);

    }


    public Button getButton( int id){
        return this.getObject(Button.class,id);
    }

    public <T> T getObject(Class<T> t, int id){
        return (T)this.findViewById(id);
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private List<Map<String, Object>> getMapList() {
        List<Map<String,Object>> items = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("image",imageIds[i]);
            items.add(item);
        }
        return items;
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder dialog = null;
        Button btn = (Button)view;
        switch (view.getId()){

            case R.id.simple:

                dialog = new AlertDialog.Builder(this)
                        .setTitle(btn.getText())
                        .setCancelable(true)
                        .setIcon(R.drawable.tools)
                        .setMessage("对话框的测试内容\n第二行内容")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("取消");
                            }
                        });
                dialog.create()
                        .show();

                break;

            case R.id.simpleList:

                dialog = new AlertDialog.Builder(this)
                        .setTitle(btn.getText())
                        .setCancelable(true)
                        .setIcon(R.drawable.tools)
                        //.setMessage("对话框的测试内容\n第二行内容")
                        .setItems(strs, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("你选中了" + strs[i]);
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("取消");
                            }
                        });
                dialog.create()
                        .show();

                break;

            case R.id.singleChoice:

                dialog = new AlertDialog.Builder(this)
                        .setTitle(btn.getText())
                        .setCancelable(true)
                        .setIcon(R.drawable.tools)
                        //.setMessage("对话框的测试内容\n第二行内容")
                        .setSingleChoiceItems(strs,1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("你选中了" + strs[i]);
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("取消");
                            }
                        });
                dialog.create()
                        .show();

                break;

            case R.id.multiChoice:

                dialog = new AlertDialog.Builder(this)
                        .setTitle(btn.getText())
                        .setCancelable(true)
                        .setIcon(R.drawable.tools)
                        //.setMessage("对话框的测试内容\n第二行内容")
                        .setMultiChoiceItems(strs, new boolean[]{false, true, true, false}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                showToast("你选中了" + strs[i] + "value" + String.valueOf(b));
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("取消");
                            }
                        });
                dialog.create()
                        .show();
                break;

            case R.id.customList:

                dialog = new AlertDialog.Builder(this)
                        .setTitle(btn.getText())
                        .setCancelable(true)
                        .setIcon(R.drawable.tools)
                        //.setMessage("对话框的测试内容\n第二行内容")
                        .setAdapter(new ArrayAdapter<String>(MainActivity.this,R.layout.array_item,strs),null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("取消");
                            }
                        });
                dialog.create()
                        .show();

                break;

            case R.id.customView:

                dialog = new AlertDialog.Builder(this)
                        .setTitle(btn.getText())
                        .setCancelable(true)
                        .setIcon(R.drawable.tools)
                        //.setMessage("对话框的测试内容\n第二行内容")
                        .setView(R.layout.activity_other)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("确定");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("取消");
                            }
                        });
                dialog.create()
                        .show();

                break;

            default:

                break;
        }

    }
}
