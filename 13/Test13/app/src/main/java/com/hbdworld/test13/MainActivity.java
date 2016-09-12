package com.hbdworld.test13;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener {


    ImageView show = null;
    Bitmap bitmap;
    TextView response;
    TextView textShow;
    HttpClient httpClient;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                response.append(msg.obj.toString() + "\n");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--
        response = (TextView) this.findViewById(R.id.text_show);
        httpClient = new DefaultHttpClient();
        //--
        bindOnClickListener(this, R.id.play, R.id.save, R.id.play2);
    }


    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.play:

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            HttpGet httpGet = new HttpGet("http://192.168.89.28/t2.php");
                            HttpResponse httpResponse = httpClient.execute(httpGet);
                            HttpEntity httpEntity = httpResponse.getEntity();
                            if (httpEntity!=null){
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                                String line = "";
                                while ((line =bufferedReader.readLine()) != null){
                                    Message message = new Message();
                                    message.what=0x123;
                                    message.obj = line;
                                    handler.sendMessage(message);
                                }
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                showToast("操作成功");

                break;

            case R.id.save:
                showToast("操作成功");
                break;

            case R.id.play2:
                showToast("操作成功2");
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
