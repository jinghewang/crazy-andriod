package com.hbdworld.test26.bases;

import android.view.View;
import android.widget.Toast;

import com.hbdworld.test26.utils.CrazyUtils;

/**
 * Created by hbd on 16/7/30.
 */
public class MyClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        CrazyUtils.showToast(view.getContext(),"MyClickListener");
    }
}
