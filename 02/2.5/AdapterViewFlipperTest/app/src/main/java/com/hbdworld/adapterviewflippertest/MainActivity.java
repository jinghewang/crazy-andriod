package com.hbdworld.adapterviewflippertest;

import android.graphics.ImageFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int[] imageIds = new int[]
            {
                    R.drawable.shuangzi, R.drawable.shuangyu,
                    R.drawable.chunv, R.drawable.tiancheng, R.drawable.tianxie,
                    R.drawable.sheshou, R.drawable.juxie, R.drawable.shuiping,
                    R.drawable.shizi, R.drawable.baiyang, R.drawable.jinniu,
                    R.drawable.mojie };

    private AdapterViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        flipper = (AdapterViewFlipper)this.findViewById(R.id.flipper);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {

                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(imageIds[i]);
                return imageView;
            }
        };

        flipper.setAdapter(baseAdapter);

    }

    public void exec(View view) {

        Button btn = (Button)view;
        String text = btn.getText().toString();
        if (text.equals("下一个")){
            flipper.setAutoStart(false);
            flipper.showNext();
        }
        else if(text.equals("上一个")){
            flipper.setAutoStart(false);
            flipper.showPrevious();
        }
        else if(text.equals("自动")){
            flipper.setAutoStart(true);
        }
        else{

        }
    }

}
