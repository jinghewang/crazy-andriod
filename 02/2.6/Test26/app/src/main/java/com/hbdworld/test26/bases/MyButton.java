package com.hbdworld.test26.bases;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.hbdworld.test26.utils.CrazyUtils;

/**
 * Created by hbd on 16/7/30.
 */
public class MyButton extends Button {


    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("-MyButton-", "the onKeyDown in MyButton");
        super.onKeyDown(keyCode, event);
        return false;
    }
}
