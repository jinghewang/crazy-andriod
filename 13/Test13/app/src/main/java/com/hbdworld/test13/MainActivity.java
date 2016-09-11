package com.hbdworld.test13;

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView show = null;
    Bitmap bitmap;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                show.setImageBitmap(bitmap);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--
        show = (ImageView) this.findViewById(R.id.show);


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
                            URL url = new URL("http://f.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db12e7bfe7a0b23dd54564e7453.jpg");
                            InputStream is = url.openStream();
                            bitmap = BitmapFactory.decodeStream(is);
                            handler.sendEmptyMessage(0x123);
                            is.close();
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

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://f.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db12e7bfe7a0b23dd54564e7453.jpg");
                            InputStream inputStream = url.openStream();
                            OutputStream fos = openFileOutput("crazyit.png", MODE_APPEND);
                            int hasRead = 0;
                            byte[] buff = new byte[1024];
                            while ((hasRead = inputStream.read(buff)) > 0) {
                                fos.write(buff, 0, hasRead);
                            }
                            fos.close();
                            inputStream.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                showToast("操作成功");

                break;

            case R.id.play2:

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = openFileInput("crazyit.png");
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            inputStream.close();
                            handler.sendEmptyMessage(0x123);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
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
