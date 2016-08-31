package com.hbdworld.t9_firstprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by hbd on 16/8/31.
 */
public class DictProvider extends ContentProvider {

    private static UriMatcher matcher
            = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private MyDatabaseHelper dbOpenHelper;

    static{
        matcher.addURI(Words.AUTHORITY,"words",WORDS);
        matcher.addURI(Words.AUTHORITY,"word/#",WORD);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyDatabaseHelper(this.getContext(),"myDict.db3",1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case WORDS:
                return "vnd.android.cursor.dir/org.crazyit.dict";

            case WORD:
                return "vnd.android.cursor.item/org.crazyit.dict";

            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)){
            case WORDS:
                long rowId = db.insert("dict",Words.Word._ID,contentValues);
                if (rowId>0){
                    Uri wordUri = ContentUris.withAppendedId(uri,rowId);
                    this.getContext().getContentResolver().notifyChange(wordUri,null);
                    return wordUri;
                }
                break;

            default:
                throw new IllegalArgumentException("未知Uri:" + uri);

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int num = 0;
        // 对uri进行匹配
        switch (matcher.match(uri))
        {
            // 如果Uri参数代表操作全部数据项
            case WORDS:
                num = db.delete("dict", where, whereArgs);
                break;
            // 如果Uri参数代表操作指定数据项
            case WORD:
                // 解析出所需要删除的记录ID
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                // 如果原来的where子句存在，拼接where子句
                if (where != null && !where.equals(""))
                {
                    whereClause = whereClause + " and " + where;
                }
                num = db.delete("dict", whereClause, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        // 通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        // 记录所修改的记录数
        int num = 0;
        switch (matcher.match(uri))
        {
            // 如果Uri参数代表操作全部数据项
            case WORDS:
                num = db.update("dict", values, where, whereArgs);
                break;
            // 如果Uri参数代表操作指定数据项
            case WORD:
                // 解析出想修改的记录ID
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                // 如果原来的where子句存在，拼接where子句
                if (where != null && !where.equals(""))
                {
                    whereClause = whereClause + " and " + where;
                }
                num = db.update("dict", values, whereClause, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        // 通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }
}
