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
	//����㲥
	//private NetworkChangeReceiver networkChangeReceiver;
	//���ع㲥
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendBbroadcast = (Button) findViewById(R.id.send_broadcast);
		sendBbroadcast.setOnClickListener(this);
		//���ع㲥ʵ����
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		//��̬��������
		/*intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver = new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);*/
		// ע�᱾�ع㲥������
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
			//����㲥
			//OrderedBroadcast(intent, null);
			//��׼�㲥
			//sendBroadcast(intent);
			//���ͱ��ع㲥
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
		//ע����̬����㲥
		//unregisterReceiver(networkChangeReceiver);
		//ע�����ع㲥
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
