package com.herman.summary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.herman.summary.action.method.nvram.NVRAM;
import com.herman.summary.common.method.formatprocess.FormatConversion;

public class MainActivity extends AppCompatActivity
{

	public static final String TAG                               = "herman";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		NVRAM.formatSecretKeys();
//		NVRAM.deleteSecretKeys(2);
		byte[] bytes = NVRAM.KEY_PAY1.getBytes();
		Log.e(TAG, "KEY_PAY1 = " + NVRAM.KEY_PAY1);
		Log.e(TAG, "key1_hex = " + FormatConversion.byteTohex(bytes));
		boolean flag = NVRAM.saveSecretKeys(22,NVRAM.KEY_PAY1);
		Log.e(TAG, "是否写入成功" + flag);
		String keyStr= NVRAM.readSecretKeys(22, null);
		Log.e(TAG, "读取秘钥-->:" + keyStr);
	}
}
