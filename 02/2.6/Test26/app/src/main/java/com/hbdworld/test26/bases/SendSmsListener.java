package com.hbdworld.test26.bases;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import com.hbdworld.test26.utils.CrazyUtils;

/**
 * Created by hbd on 16/7/30.
 */
public class SendSmsListener implements View.OnLongClickListener {

    private Activity act;
    private EditText address;
    private EditText content;

    public SendSmsListener(Activity act, EditText address , EditText content) {
        this.act = act;
        this.address = address;
        this.content =  content;
        CrazyUtils.showToast(act.getApplicationContext(),"SendSmsListener");
    }

    @Override
    public boolean onLongClick(View view) {
        String addr2 = this.address.getText().toString();
        String context2 = this.content.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sent = PendingIntent.getBroadcast(act, 0, new Intent(), 0);
        smsManager.sendTextMessage(addr2, null, context2, sent, null);

        CrazyUtils.showToast(view.getContext(), "短信发送完成");
        return false;
    }
}
