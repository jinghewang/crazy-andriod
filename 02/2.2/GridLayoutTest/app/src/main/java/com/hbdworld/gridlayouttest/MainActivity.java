package com.hbdworld.gridlayouttest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    GridLayout gridLayout;
    TextView result;

    String[] chars = new String[]{
            "7" , "8" , "9" , "÷",
            "4" , "5" , "6" , "×",
            "1" , "2" , "3" , "-",
            "." , "0" , "=" , "+"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--
        gridLayout = (GridLayout)this.findViewById(R.id.root);
        result = (TextView)this.findViewById(R.id.result);
        for (int i=0;i<chars.length;i++){
            Button btn = new Button(this);
            btn.setText(chars[i]);
            btn.setTextSize(40);
            btn.setBackgroundColor(Color.BLUE);
            btn.setPadding(5 , 35 , 5 , 35);

            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec colSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,colSpec);
            params.setMargins(1,1,1,1);
            params.setGravity(Gravity.FILL);
            btn.setLayoutParams(params);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tv = (TextView) view;
                    result.setText(result.getText().toString() + tv.getText());
                }
            });

            gridLayout.addView(btn);

        }
    }
}
