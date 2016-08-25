package com.hbdworld.test8;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    TextView tvContent;
    TextView tvTitle;

    // 记录当前的父文件夹
    File currentParent;
    // 记录当前路径下的所有文件的文件数组
    File[] currentFiles;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--
        listView = (ListView) findViewById(R.id.show);
        tvTitle = (TextView) findViewById(R.id.title);
        tvContent = (TextView) findViewById(R.id.content);

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir() + "/mydb.db", null);
        this.ensureTable(db);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView tv = (TextView) view.findViewById(R.id.my_id);
                    String s = tv.getText().toString();
                    Cursor cursor = db.rawQuery("select * from news_inf where _id=?", new String[]{s});
                    int _id = cursor.getColumnIndex("_id");
                    int _title = cursor.getColumnIndex("news_title");
                    int _content = cursor.getColumnIndex("news_content");

                    if (cursor.moveToFirst()) {
                        tvTitle.setText(cursor.getString(_title));
                        tvContent.setText(cursor.getString(_content));
                    }
                    cursor.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        inflateListView(db);

        this.bindOnClickListener(this, R.id.ok);
    }

    private void ensureTable(SQLiteDatabase db) {

        try {
            // 执行DDL创建数据表
            db.execSQL("create table news_inf(_id integer"
                    + " primary key autoincrement,"
                    + " news_title varchar(50),"
                    + " news_content varchar(255))");
            // 执行insert语句插入数据
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inflateListView(SQLiteDatabase db2)  // ①
    {
        try {
            Cursor cursor = db.rawQuery("select * from news_inf", new String[]{});
            SimpleCursorAdapter list = new SimpleCursorAdapter(MainActivity.this, R.layout.line, cursor,
                    new String[]{"_id", "news_title", "news_content"},
                    new int[]{R.id.my_id, R.id.my_title, R.id.my_content});

            listView.setAdapter(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok:

                ContentValues values = new ContentValues();
                values.put("news_title", tvTitle.getText().toString());
                values.put("news_content", tvContent.getText().toString());
                db.insert("news_inf", null, values);

                tvTitle.setText("");
                tvContent.setText("");

                // 再次更新ListView
                inflateListView(db);
                showToast(Integer.valueOf(1).toString());
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null || db.isOpen()) {
            db.close();
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
