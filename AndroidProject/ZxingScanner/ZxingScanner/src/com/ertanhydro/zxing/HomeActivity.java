package com.ertanhydro.zxing;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	private EditText input=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
    }
    
    /**
	 * 初始化控件
	 */
	private void initView() {
		input=(EditText)findViewById(R.id.input);
		
		findViewById(R.id.make).setOnClickListener(this);

		findViewById(R.id.clear).setOnClickListener(this);
		
		findViewById(R.id.btn_scanner).setOnClickListener(this);
		
		findViewById(R.id.btn_more).setOnClickListener(this);

	}
	
	/**
	 * 按键处理
	 */
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.make:
			if(input.getText().toString().length()>0)
			{
			intent.putExtra("content", input.getText().toString());
			intent.setClass(HomeActivity.this, CreateActivity.class);
			startActivity(intent);
			}
			else
			{
				Toast toast = Toast.makeText(getApplicationContext(),"please input message", Toast.LENGTH_LONG);
			      	   toast.setGravity(Gravity.CENTER, 0, 0);
			      	   toast.show();
			}
			break;
		case R.id.clear:
			input.setText("");
			break;
		case R.id.btn_scanner:
			intent = intent.setClass(HomeActivity.this,CaptureActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_more:
			intent = intent.setClass(HomeActivity.this,AboutActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
}
