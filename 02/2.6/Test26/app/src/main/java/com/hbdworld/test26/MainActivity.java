package com.hbdworld.test26;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);  //①
        setContentView(R.layout.activity_main);

        //--
        Button bn1 = (Button)findViewById(R.id.bn1);
        Button bn2 = (Button)findViewById(R.id.bn2);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProgressBarIndeterminate(true);
                setProgressBarVisibility(true);
                setProgress(4500);
            }
        });

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProgressBarIndeterminate(false);
                setProgressBarVisibility(false);
                //setProgress(4500);
            }
        });
    }
}
