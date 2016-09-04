package com.hbdworld.test10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        String action = intent.getAction();
        Bundle first = getResultExtras(true);
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, String.format("MyReceiver2  -- action:%s msg:%s first:%s", action, msg,first.getString("first")), Toast.LENGTH_SHORT).show();

//        Bundle bundle = new Bundle();
//        bundle.putString("first","this is first msg");
//        setResultExtras(bundle);

    }
}
