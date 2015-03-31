package com.yunnex.fileopreations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("SdCardPath")
public class MainActivity extends Activity implements OnClickListener {
	
	private Button  Save;
	private Button  Modify;
	private Button  Delete;
	private EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Save = (Button) findViewById(R.id.save);
		Modify = (Button) findViewById(R.id.modify);
		Delete = (Button) findViewById(R.id.delete);
		edit = (EditText) findViewById(R.id.edit);
		
		Save.setOnClickListener(this);
		Modify.setOnClickListener(this);
		Delete.setOnClickListener(this);
		
		if(loadshow())
			Toast.makeText(this, "Restoring succeeded",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			//存储文件
			String inputText = edit.getText().toString();
			save(inputText);
			Toast.makeText(this, "Save succeeded",Toast.LENGTH_SHORT).show();
			break;		
		case R.id.modify:
			//修改文件
			modify("刘长发 ");
			if(loadshow())
				Toast.makeText(this, "Modify succeeded",Toast.LENGTH_SHORT).show();
			break;
		case R.id.delete:
			//删除文件
			if(deleteFile("/data/data/com.yunnex.fileopreations/files/data")){
				String putText = load();
				edit.setText(putText);
				Toast.makeText(this, "Delete succeeded",Toast.LENGTH_SHORT).show();
			}
			
			break;
		default:
			break;
		}
	}
	
	//存储文件内容
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
	//加载文件内容
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

	//修改文件内容
	public void  modify(String outputText){
		String inputText = load();
		inputText = outputText;
		save(inputText);
	}
	
	//删除文件
	public  boolean deleteFile(String fileName) {  
		 File file = new File(fileName);  
		 // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除  
		 if (file.exists() && file.isFile()) {  
			 if (file.delete()) {  
				 Log.d("MainActivity" ,"删除文件成功！");  
				 return true;  
			 } else {  
				 Log.d("MainActivity" ,"删除文件失败！");  
				 return false;  
			 }  
		 } else {  
			 Log.d("MainActivity" ,"删除文件失败，文件不存在！");   
			 return false;  
		 }  
	}
	
	//加载重现
	public  boolean  loadshow(){
		String inputText = load();
		if (!TextUtils.isEmpty(inputText)) {
			edit.setText(inputText);
			edit.setSelection(inputText.length());
			return true;
		} else {
			return false;
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

