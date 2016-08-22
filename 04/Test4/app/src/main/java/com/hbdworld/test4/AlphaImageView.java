package com.hbdworld.test4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hbd on 16/8/22.
 */
public class AlphaImageView extends ImageView {

    private static final int WHAT = 0x124;

    // 图像透明度每次改变的大小
    private int alphaDelta = 0;
    // 记录图片当前的透明度。
    private int curAlpha = 0;
    // 每隔多少毫秒透明度改变一次
    private final int SPEED = 300;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if (msg.what == WHAT){
                curAlpha += alphaDelta;
                if (curAlpha>255)
                    curAlpha = 255;

                AlphaImageView.this.setAlpha(curAlpha);
            }
        }
    };

    public AlphaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        alphaDelta = 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.setImageAlpha(curAlpha);
        super.onDraw(canvas);

//        final Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Message msg = new Message();
//                msg.what = WHAT;
//                handler.sendMessage(msg);
//                if (curAlpha >= 255) {
//                    timer.cancel();
//                } else {
//                    handler.sendMessage(msg);
//                }
//            }
//        }, 0, SPEED);

    }
}
