package com.hbdworld.test26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        Button btn = (Button) this.findViewById(R.id.select);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                intent.putExtra("name","wjh");
                SelectCityActivity.this.setResult(100,intent);
                SelectCityActivity.this.finish();
                //return false;
            }
        });
    }
}
