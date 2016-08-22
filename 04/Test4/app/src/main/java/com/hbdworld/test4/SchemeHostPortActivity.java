package com.hbdworld.test4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SchemeHostPortActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("指定Scheme、Host、Port匹配的Activity");
		tv.setTextSize(30);
		setContentView(tv);
	}
}