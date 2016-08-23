package com.hbdworld.test8;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences = null;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--
        preferences = getSharedPreferences("crazyit",MODE_PRIVATE);
        editor = preferences.edit();

        this.bindOnClickListener(this,R.id.read,R.id.write);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.read:
                String time = preferences.getString("time",null);
                String name = preferences.getString("name",null);
                int age = preferences.getInt("age",0);

                showToast(time);
                showToast(name);
                showToast(Integer.valueOf(age).toString());

                break;

            case R.id.write:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                editor.putString("time", dateFormat.format(new Date()));
                editor.putString("name", "wjh");
                editor.putInt("random", (int) (Math.random() * 100));
                editor.putInt("age", 15);
                editor.commit();
                break;

            default:
                break;
        }
    }


    public void showToast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
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
