package com.hbdworld.t9_firstprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by hbd on 16/8/31.
 */
public class FirstProvider extends ContentProvider {


    @Override
    public boolean onCreate() {
        System.out.println("=====onCreate方法被调用=====");
        return true;
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

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] whereArgs, String sortOrder) {
        System.out.println(uri + "===query方法被调用===");
        System.out.println("where参数为：" + where);
        return null;
    }

    protected Cursor getCursor() {
        //Cursor c = new cur
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        System.out.println(uri + "===insert方法被调用===");
        System.out.println("values参数为：" + values);
        return null;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        System.out.println(uri + "===delete方法被调用===");
        System.out.println("where参数为：" + where);
        return 10;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        System.out.println(uri + "===update方法被调用===");
        System.out.println("where参数为：" + where + ",values参数为：" + values);
        return 20;
    }
}
