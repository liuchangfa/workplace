package com.yunnex.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button sendBbroadcast;
	
	private IntentFilter intentFilter;
	//网络广播
	//private NetworkChangeReceiver networkChangeReceiver;
	//本地广播
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendBbroadcast = (Button) findViewById(R.id.send_broadcast);
		sendBbroadcast.setOnClickListener(this);
		//本地广播实例化
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		//动态监听网络
		/*intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver = new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);*/
		// 注册本地广播监听器
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.yunnex.broadcasttest.LOCAL_BROADCAST");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver, intentFilter);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_broadcast:
			//Intent intent = new Intent("com.yunnex.broadcasttest.MY_BROADCAST");
			//有序广播
			//OrderedBroadcast(intent, null);
			//标准广播
			//sendBroadcast(intent);
			//发送本地广播
			Intent intent = new Intent("com.yunnex.broadcasttest.LOCAL_BROADCAST");
			localBroadcastManager.sendBroadcast(intent); 
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//注销动态网络广播
		//unregisterReceiver(networkChangeReceiver);
		//注销本地广播
		localBroadcastManager.unregisterReceiver(localReceiver);
	}
	
	class LocalReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "received local broadcast",Toast.LENGTH_SHORT).show();
		}
	}
	
	class NetworkChangeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				Toast.makeText(context, "network is available",Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "network is unavailable",Toast.LENGTH_SHORT).show();
			}
		}
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
