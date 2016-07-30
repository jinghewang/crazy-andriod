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

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        CrazyUtils.showToast(this.getContext(),"OnClickListener");
    }
}
