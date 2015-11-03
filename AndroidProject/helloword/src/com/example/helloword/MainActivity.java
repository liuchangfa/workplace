package com.example.helloword;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("MainActivity","Task id is" + getTaskId());
		requestWindowFeature(Window.FEATURE_NO_TITLE);/*Òþ²Ø±êÌâÀ¸*/
		setContentView(R.layout.activity_main);
		Button buttonsend = (Button) findViewById(R.id.button_send);
		buttonsend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Toast.makeText(MainActivity.this,"you clicked button_send",
						Toast.LENGTH_SHORT).show();*/
				/*Intent intent = new Intent("com.example.helloword.ACTION_START");
				intent.addCategory("com.example.helloword.MY_CATEGORY");*/
			    /*Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));*/
				/*Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:10086"));*/
				/*String data = "Hello SecondActivity";
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra("extra_data", data);*/
				/*Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivityForResult(intent, 1);*/
				/*Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(intent);*/
				SecondActivity.actionStart(MainActivity.this, "data1", "data2");
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				String returnedData = data.getStringExtra("data_return");
				Log.d("FirstActivity", returnedData);
			}
			break;
		default:
		}
	}	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("MainActivity", "onDestroy");
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("MainActivity", "onRestart");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.add_item:
			Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
			break;
		case R.id.remove_item:
			Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "You action_settings", Toast.LENGTH_SHORT).show();
			break;
		default:
		}
		
		return true;
	}
	
}
