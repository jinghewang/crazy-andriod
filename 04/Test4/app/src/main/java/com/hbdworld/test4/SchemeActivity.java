package com.hbdworld.test4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SchemeActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("仅指定Scheme匹配的Activity");
		tv.setTextSize(30);
		setContentView(tv);
	}
}