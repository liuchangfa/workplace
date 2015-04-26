package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;

public class MainActivity extends Activity {

	private int orientation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/**
		 * 判断竖屏和横屏，加载不同布局
		 * */
		orientation=getResources().getConfiguration().orientation;
		if(orientation == 1)
			setContentView(R.layout.activity_main);
		else
			setContentView(R.layout.layout_land);
	}

}
