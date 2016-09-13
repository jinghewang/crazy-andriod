package com.hbdworld.test13;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        bindOnClickListener(this, R.id.play, R.id.login, R.id.play2);
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
                            HttpGet httpGet = new HttpGet("http://192.168.1.100/t2.php");
                            HttpResponse httpResponse = httpClient.execute(httpGet);
                            HttpEntity httpEntity = httpResponse.getEntity();
                            if (httpEntity != null) {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                                String line = "";
                                while ((line = bufferedReader.readLine()) != null) {
                                    Message message = new Message();
                                    message.what = 0x123;
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

            case R.id.login:

                final View alertDialog = getLayoutInflater().inflate(R.layout.login, null);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("")
                        .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                final String name = ((TextView) alertDialog.findViewById(R.id.name)).getText().toString();
                                final String pass = ((TextView) alertDialog.findViewById(R.id.pass)).getText().toString();


                                new Thread() {
                                    @Override
                                    public void run() {
                                        //super.run();
                                        try {
                                            HttpPost post = new HttpPost("http://192.168.1.100/t2.php");
                                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                                            params.add(new BasicNameValuePair("name", name));
                                            params.add(new BasicNameValuePair("pass", pass));
                                            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                            HttpResponse response = httpClient.execute(post);
                                            if (response.getStatusLine().getStatusCode() == 200) {
                                                String msg = EntityUtils.toString(response.getEntity());
                                                Looper.prepare();
                                                showToast(msg);
                                                Looper.loop();
                                            }
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        } catch (ClientProtocolException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }.start();


                            }
                        })
                        .setView(alertDialog)
                        .setNegativeButton("取消", null)
                        .show();


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
