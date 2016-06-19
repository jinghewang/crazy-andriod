package com.hbdworld.mixview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    int[] images = new int[]{
            R.drawable.ajax,
            R.drawable.html,
            R.drawable.java,
            R.drawable.javaee,
            R.drawable.swift
    };
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout main = (LinearLayout) this.findViewById(R.id.root);
        Button button = new Button(this);
        button.setText(R.string.image_click);
        main.addView(button);

        final ImageView imageView = new ImageView(this);
        imageView.setImageResource(images[0]);
        main.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(images[++currentImage % images.length]);
            }
        });

    }
}
