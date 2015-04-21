package com.yunnex.uiview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TitleView extends FrameLayout  implements OnClickListener{

	private Button leftButton;
	private TextView titleText;
	private ImageButton rightButton;


	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.activity_title, this);
		initView();
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		titleText = (TextView) findViewById(R.id.mtextview_title);
		
		leftButton = (Button) findViewById(R.id.button_left);
		leftButton.setOnClickListener(this);

		rightButton = (ImageButton) findViewById(R.id.button_right);
		rightButton.setOnClickListener(this);
	
	}
	
	/**
	 * 按键处理
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_left:
			((Activity) getContext()).finish(); 
		break;
		
		case R.id.button_right:
			Toast.makeText(getContext(), "Wecome",Toast.LENGTH_SHORT).show();
		break;
		
		default:
		break;
		}
		
	}
	public void setTitleText(String text) {
		titleText.setText(text);
	}

	public void setLeftButtonText(String text) {
		leftButton.setText(text);
	}

	public void setLeftButtonListener(OnClickListener l) {
		leftButton.setOnClickListener(l);
	}
	
	public void setRightButtonListener(OnClickListener l) {
		rightButton.setOnClickListener(l);
	}

}