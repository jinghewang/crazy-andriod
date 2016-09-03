package com.hbdworld.test9;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ContentResolver contentResolver = null;
    ListView show;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> descs = new ArrayList<>();
    ArrayList<String> fileNames = new ArrayList<>();
    private static final String TAG = "HBD-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.w(TAG, "--create--");
        this.show = (ListView)this.findViewById(R.id.show);
        this.contentResolver = this.getContentResolver();
        this.bindOnClickListener(this, R.id.add, R.id.view);

        this.show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View viewDialog = getLayoutInflater().inflate(R.layout.view,null);
                ImageView imageView = (ImageView) viewDialog.findViewById(R.id.image);
                String filename = fileNames.get(i);
                imageView.setImageBitmap(BitmapFactory.decodeFile(filename));
                new AlertDialog.Builder(MainActivity.this)
                        .setView(viewDialog)
                        .setPositiveButton("确定", null)
                        .setTitle("图片查看")
                        .show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.add:
                // 创建ContentValues对象，准备插入数据
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME, "mm");
                values.put(MediaStore.Images.Media.DESCRIPTION, "金塔");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                // 插入数据，返回所插入数据对应的Uri
                Uri uri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                // 加载应用程序下的jinta图片
                Bitmap bitmap = BitmapFactory.decodeResource(
                        MainActivity.this.getResources(),
                        R.drawable.mm);
                System.out.println("======");
                OutputStream os = null;
                try
                {
                    // 获取刚插入的数据的Uri对应的输出流
                    os = getContentResolver().openOutputStream(uri); // ①
                    // 将bitmap图片保存到Uri对应的数据节点中
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.close();

                    this.showToast("加入成功");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;

            case R.id.view:
                Cursor c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
                while (c.moveToNext()){
                    String name = c.getString( c.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    String desc = c.getString( c.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                    byte[] data = c.getBlob( c.getColumnIndex(MediaStore.Images.Media.DATA));

                    this.names.add(name);
                    this.descs.add(desc);
                    this.fileNames.add(new String(data,0,data.length-1));
                }

                //--
                List<Map<String, Object>> itemList = new ArrayList<>();
                for (int i = 0; i < this.names.size(); i++) {
                    Map<String,Object> item = new HashMap<>();
                    item.put("name",this.names.get(i));
                    item.put("desc",this.descs.get(i));
                    itemList.add(item);
                }
                ListAdapter listAdapter = new SimpleAdapter(this, itemList, R.layout.line, new String[]{"name", "desc"}, new int[]{R.id.name, R.id.desc});
                this.show.setAdapter(listAdapter);

                break;

            default:
                showToast("----default----");
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
