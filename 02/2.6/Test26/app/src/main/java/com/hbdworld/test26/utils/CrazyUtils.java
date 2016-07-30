package com.hbdworld.test26.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hbd on 16/7/28.
 */
public class CrazyUtils {

    public static void showToast(Context context, CharSequence text)
    {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
