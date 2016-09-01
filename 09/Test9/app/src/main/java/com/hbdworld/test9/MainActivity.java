package com.hbdworld.test9;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ContentResolver contentResolver = null;
    private static final String TAG = "HBD-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.w(TAG, "--create--");

        this.contentResolver = this.getContentResolver();
        this.bindOnClickListener(this, R.id.add, R.id.search);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.search:
                // 获取用户输入
                // 执行查询
                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                ArrayList<Map<String, String>> data2 = converCursorToList(cursor);
                // 创建一个Bundle对象
                Bundle data = new Bundle();
                data.putSerializable("data", data2);
                // 创建一个Intent
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(data);
                // 启动Activity
                startActivity(intent);
                break;

            case R.id.add:
                // 获取程序界面中的三个文本框的内容
                String name = ((EditText) findViewById(R.id.name))
                        .getText().toString();
                String phone = ((EditText) findViewById(R.id.phone))
                        .getText().toString();
                String email = ((EditText) findViewById(R.id.email))
                        .getText().toString();
                // 创建一个空的ContentValues
                ContentValues values = new ContentValues();
                // 向RawContacts.CONTENT_URI执行一个空值插入
                // 目的是获取系统返回的rawContactId
                Uri rawContactUri = getContentResolver().insert(
                        ContactsContract.RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(rawContactUri);
                values.clear();
                values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId);
                // 设置内容类型
                values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                // 设置联系人名字
                values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
                // 向联系人URI添加联系人名字
                getContentResolver().insert(android.provider.ContactsContract
                        .Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                // 设置联系人的电话号码
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
                // 设置电话类型
                values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                // 向联系人电话号码URI添加电话号码
                getContentResolver().insert(android.provider.ContactsContract
                        .Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
                // 设置联系人的E-mail地址
                values.put(ContactsContract.CommonDataKinds.Email.DATA, email);
                // 设置该电子邮件的类型
                values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
                // 向联系人E-mail URI添加E-mail数据
                getContentResolver().insert(android.provider.ContactsContract
                        .Data.CONTENT_URI, values);
                Toast.makeText(MainActivity.this, "联系人数据添加成功",
                        Toast.LENGTH_SHORT).show();
                break;

            default:
                showToast("----default----");
                break;
        }

    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        // 遍历Cursor结果集
        while (cursor.moveToNext()) {
            // 将结果集中的数据存入ArrayList中
            Map<String, String> map = new HashMap<>();
            // 取出查询记录中第2列、第3列的值
            map.put(Words.Word.WORD, cursor.getString(1));
            map.put(Words.Word.DETAIL, cursor.getString(2));
            result.add(map);
        }
        return result;
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
