package com.yunnex.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button  Save;
	private Button  Delete;
	private EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Save = (Button) findViewById(R.id.save);
		Delete = (Button) findViewById(R.id.delete);
		edit = (EditText) findViewById(R.id.edit);
		String inputText = load();
		
		Save.setOnClickListener(this);
		Delete.setOnClickListener(this);
		
		if (!TextUtils.isEmpty(inputText)) {
			edit.setText(inputText);
			edit.setSelection(inputText.length());
			Toast.makeText(this, "Restoring succeeded",Toast.LENGTH_SHORT).show();
		}
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			String inputText = edit.getText().toString();
			save(inputText);
			break;
		case R.id.delete:
			//Î±É¾³ý
			String outputText = edit.getText().toString();
			outputText = "";
			save(outputText);
			edit.setText(outputText);
			Toast.makeText(this, "Delete succeeded",Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	//@Override
	/*protected void onDestroy() {
		super.onDestroy();
		String inputText = edit.getText().toString();
		save(inputText);
	}*/
	
	public String load() {
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try {
			in = openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}
	
	public void save(String inputText) {
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = openFileOutput("data", Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(inputText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
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
