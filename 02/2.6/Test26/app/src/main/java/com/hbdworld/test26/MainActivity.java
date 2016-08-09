package com.hbdworld.test26;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hbdworld.test26.bases.MyButton;
import com.hbdworld.test26.bases.MyClickListener;
import com.hbdworld.test26.bases.SendSmsListener;
import com.hbdworld.test26.pojo.Person;
import com.hbdworld.test26.utils.CrazyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends LauncherActivity {

    int[] imageIds = new int[]
            {
                    R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7
                    , R.drawable.bomb8, R.drawable.bomb9, R.drawable.bomb10
                    , R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13
                    , R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16
            };

    String[] names = new String[]{"设置程序设置", "查看星际兵种"};
    Class<?>[] classz = {PreferenceActivityTest.class, ExpandableListActivityTest.class};
    private String TAG = "---hbd---";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //--
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        this.setListAdapter(adapter);

        Log.v(TAG,"oncreate");
    }

    @Override
    protected Intent intentForPosition(int position) {
        //return super.intentForPosition(position);
        return new Intent(this,classz[position]);
    }

    public Button getButton(int id){
        return this.getObject(Button.class,id);
    }

    public EditText getTextView(int id){
        return this.getObject(EditText.class,id);
    }

    public <T> T getObject(Class<T> t, int id){
        return (T)this.findViewById(id);
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
}
