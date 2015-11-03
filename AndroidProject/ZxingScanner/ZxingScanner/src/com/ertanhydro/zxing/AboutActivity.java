package com.ertanhydro.zxing;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;



public class AboutActivity extends Activity {

	private TextView tvAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.app_about);
	
		tvAbout = (TextView) findViewById(R.id.tv_about);
		tvAbout.setMovementMethod(LinkMovementMethod.getInstance());
		tvAbout.setText(Html.fromHtml((getResources().getString((R.string.about_text)))));
		
		Button backbutton=(Button)findViewById(R.id.button_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View v) {
				finish();
			}
		});
	}
}
