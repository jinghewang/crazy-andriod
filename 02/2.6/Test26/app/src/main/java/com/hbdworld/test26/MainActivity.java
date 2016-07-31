package com.hbdworld.test26;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbdworld.test26.bases.MyButton;
import com.hbdworld.test26.bases.MyClickListener;
import com.hbdworld.test26.bases.SendSmsListener;
import com.hbdworld.test26.utils.CrazyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int[] imageIds = new int[]
            {
                    R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7
                    , R.drawable.bomb8, R.drawable.bomb9, R.drawable.bomb10
                    , R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13
                    , R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16
            };


    static final String UPPER_NUM = "upper";
    int currentImageId = 0;
    private int what = 0x123;
    private TextView show;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--
        show = (TextView) findViewById(R.id.show);
    }


    // 重写该方法，为界面的按钮提供事件响应方法
    public void download(View source) throws MalformedURLException
    {
        DownTask task = new DownTask(this);
        task.execute(new URL("http://www.crazyit.org/ethos.php"));
    }

    class DownTask extends AsyncTask<URL,Integer,String>{

        // 可变长的输入参数，与AsyncTask.exucute()对应
        ProgressDialog pdialog;
        // 定义记录已经读取行的数量
        int hasRead = 0;
        Context mContext ;

        public DownTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected String doInBackground(URL... params) {

            StringBuilder sb = new StringBuilder();
            try{

                URLConnection connection = params[0].openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                    hasRead++;
                    publishProgress(hasRead);
                    //Thread.currentThread().wait(200);
                }
                return sb.toString();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // 返回HTML页面的内容
            show.setText(result);
            pdialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            pdialog = new ProgressDialog(mContext);
            // 设置对话框的标题
            pdialog.setTitle("任务正在执行中");
            // 设置对话框显示的内容
            pdialog.setMessage("任务正在执行中，敬请等待...");
            // 设置对话框不能用“取消”按钮关闭
            pdialog.setCancelable(false);
            // 设置该进度条的最大进度值
            pdialog.setMax(202);
            // 设置对话框的进度条风格
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // 设置对话框的进度条是否显示进度
            pdialog.setIndeterminate(false);
            pdialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 更新进度
            show.setText("已经读取了【" + values[0] + "】行！");
            pdialog.setProgress(values[0]);
        }
    }


    public Button getButton(int id){
        return this.getObject(Button.class,id);
    }

    public EditText getTextView(int id){
        return this.getObject(EditText.class,id);
    }

    public <T> T getObject(Class<T> t, int id){
        return (T)this.findViewById(id);
    }



    @NonNull
    private List<Map<String, Object>> getMapList() {
        List<Map<String,Object>> items = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            Map<String,Object> item = new HashMap<>();
            item.put("image",imageIds[i]);
            items.add(item);
        }
        return items;
    }
}
