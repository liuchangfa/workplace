package com.yunnex.mycontentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private String newId;
	private Button addData;
	private Button queryDate;
	private Button updateData;
	private Button deleteDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addData = (Button) findViewById(R.id.add_data);
		queryDate = (Button) findViewById(R.id.query_data);
		updateData = (Button) findViewById(R.id.update_data);
		deleteDate =  (Button) findViewById(R.id.delete_data);
		
		addData.setOnClickListener(this);
		queryDate.setOnClickListener(this);
		updateData.setOnClickListener(this);
		deleteDate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_data:
			// 添加数据
			Uri uriadd = Uri.parse("content://com.yunnex.mydatasql.provider/book");
			ContentValues valuesadd = new ContentValues();
			valuesadd.put("name", "A Clash of Kings");
			valuesadd.put("author", "George Martin");
			valuesadd.put("pages", 1040);
			valuesadd.put("price", 22.85);
			Uri newUri = getContentResolver().insert(uriadd, valuesadd);
			newId = newUri.getPathSegments().get(1);
			break;
		case R.id.query_data:
			// 查询数据
			Uri uriquery = Uri.parse("content://com.yunnex.mydatasql.provider/book");
			Cursor cursor = getContentResolver().query(uriquery, null, null,null, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String author = cursor.getString(cursor.getColumnIndex("author"));
					int pages = cursor.getInt(cursor.getColumnIndex("pages"));
					double price = cursor.getDouble(cursor.getColumnIndex("price"));
					Log.d("MainActivity", "book name is " + name);
					Log.d("MainActivity", "book author is " + author);
					Log.d("MainActivity", "book pages is " + pages);
					Log.d("MainActivity", "book price is " + price);
				}
				cursor.close();
			}
			break;
		case R.id.update_data:
			// 更新数据
			Uri uriupdate = Uri.parse("content://com.yunnex.mydatasql.provider/book/" + newId);
			ContentValues valuesupdate = new ContentValues();
			valuesupdate.put("name", "A Storm of Swords");
			valuesupdate.put("pages", 1216);
			valuesupdate.put("price", 24.05);
			getContentResolver().update(uriupdate, valuesupdate, null, null);
			break;
		case R.id.delete_data:
			// 删除数据
			Uri uridelete = Uri.parse("content://com.yunnex.mydatasql.provider/book/" + newId);
			getContentResolver().delete(uridelete, null, null);
			break;
		default:
			break;
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
