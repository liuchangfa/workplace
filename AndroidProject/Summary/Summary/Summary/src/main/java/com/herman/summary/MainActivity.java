package com.herman.summary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.herman.summary.action.method.nvram.NVRAM;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NVRAM.WriteData(NVRAM.NVRAM_FILE_LID);
		NVRAM.ReadData(NVRAM.NVRAM_FILE_LID);
	}
}
