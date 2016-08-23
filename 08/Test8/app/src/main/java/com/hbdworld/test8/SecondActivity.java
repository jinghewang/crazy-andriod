package com.hbdworld.test8;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences = null;
    SharedPreferences.Editor editor = null;
    final String FILE_NAME = "crazyit.bin";
    EditText edit1;
    EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        //--
        edit1 = (EditText) findViewById(R.id.file_edit1);
        edit2 = (EditText) findViewById(R.id.file_edit2);
        this.bindOnClickListener(this, R.id.file_read, R.id.file_write);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.file_read:
                edit2.setText(read());
                showToast("file_read");
                break;

            case R.id.file_write:
                this.write(edit1.getText().toString());
                edit1.setText("");
                showToast("file_write");
                break;

            default:
                break;
        }
    }


    public String read(){
        try{
            InputStream fis = this.openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead =fis.read(buff))>0){
                sb.append(new String(buff,0,hasRead));
            }
            fis.close();
            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void write(String content){

        try{
            OutputStream stream = this.openFileOutput(FILE_NAME,MODE_APPEND);
            PrintStream ps = new PrintStream(stream);
            ps.println(content);
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void showToast(String msg){
        Toast.makeText(SecondActivity.this,msg,Toast.LENGTH_SHORT).show();
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
