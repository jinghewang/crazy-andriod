package com.hbdworld.buttontest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView show = null;
    Button start = null;
    Button stop = null;
    ToggleButton tb ;
    Switch sw ;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tb = (ToggleButton)this.findViewById(R.id.tb);
        sw = (Switch)this.findViewById(R.id.sw);
        layout = (LinearLayout)this.findViewById(R.id.layout);

        CompoundButton.OnCheckedChangeListener listener  = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tb.setChecked(b);
                sw.setChecked(b);
                if (b){
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                }
                else{
                    layout.setOrientation(LinearLayout.VERTICAL);
                }
            }
        };

        tb.setOnCheckedChangeListener(listener);
        sw.setOnCheckedChangeListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
