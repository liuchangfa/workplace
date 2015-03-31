package com.yunnex.mydatasql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private MyDatabaseHelper dbHelper;
	private Button createDatabase;
	private Button addData;
	private Button updateData;
	private Button deleteDate;
	private Button queryDate;
	private Button replaceDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
		//�������
		createDatabase = (Button) findViewById(R.id.create_database);
		addData = (Button) findViewById(R.id.add_data);
		updateData = (Button) findViewById(R.id.update_data);
		deleteDate =  (Button) findViewById(R.id.delete_data);
		queryDate = (Button) findViewById(R.id.query_data);
		replaceDate = (Button) findViewById(R.id.replace_data);
		
		createDatabase.setOnClickListener(this);
		addData.setOnClickListener(this);
		updateData.setOnClickListener(this);
		deleteDate.setOnClickListener(this);
		queryDate.setOnClickListener(this);
		replaceDate.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.create_database:
			Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:10086"));
			dbHelper.getWritableDatabase();
			break;	
			
		case R.id.add_data:
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			//��һ�ַ���������SQL
			db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
					new String[] { "The Da Vinci Code", "Dan Brown", "454", "16.96" });
			db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
					new String[] { "The Lost Symbol", "Dan Brown", "510", "19.95" });
			//�ڶ��ַ��������ð�׿�ṩ��API
			/*/ ��ʼ��װ��һ������
			ContentValues values = new ContentValues();
			values.put("name", "The Da Vinci Code");
			values.put("author", "Dan Brown");
			values.put("pages", 454);
			values.put("price", 16.96);
			db.insert("Book", null, values); // �����һ������
			values.clear();*/
	
			/*/ ��ʼ��װ�ڶ�������
			values.put("name", "The Lost Symbol");
			values.put("author", "Dan Brown");
			values.put("pages", 510);
			values.put("price", 19.95);
			db.insert("Book", null, values);// ����ڶ�������*/
			 
			Toast.makeText(this, "Add succeeded", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.update_data:
			SQLiteDatabase dbup = dbHelper.getWritableDatabase();
			//��һ�ַ���������SQL
			//dbup.execSQL("update Book set price = ? where name = ?",
			//		new String[] { "10.99","The Da Vinci Code" });
			//�ڶ��ַ��������ð�׿�ṩ��API
			ContentValues valuesup = new ContentValues();
			valuesup.put("price", 10.99);
			dbup.update("Book", valuesup, "name = ?", new String[] { "The DaVinci Code" });
			Toast.makeText(this, "Update succeeded", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.delete_data:
			SQLiteDatabase dbdelete = dbHelper.getWritableDatabase();
			//��һ�ַ���������SQL
			//dbdelete.execSQL("delete from Book where pages > ?", new String[] { "500" });
			//�ڶ��ַ��������ð�׿�ṩ��API
			dbdelete.delete("Book", "pages > ?", new String[] { "500" });
			Toast.makeText(this, "Delete succeeded", Toast.LENGTH_SHORT).show();
			
		case R.id.query_data:
			SQLiteDatabase dbquery = dbHelper.getWritableDatabase();
			// ��ѯBook�������е�����
			//��һ�ַ���������SQL
			//dbquery.rawQuery("select * from Book", null);
			//�ڶ��ַ��������ð�׿�ṩ��API
			Cursor cursor = dbquery.query("Book", null, null, null, null, null, null);
			if (cursor.moveToFirst()) {
				do {
					// ����Cursor����ȡ�����ݲ���ӡ
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String author = cursor.getString(cursor.getColumnIndex("author"));
					int pages = cursor.getInt(cursor.getColumnIndex("pages"));
					double price = cursor.getDouble(cursor.getColumnIndex("price"));
					Log.d("MainActivity", "book name is " + name);
					Log.d("MainActivity", "book author is " + author);
					Log.d("MainActivity", "book pages is " + pages);
					Log.d("MainActivity", "book price is " + price);
				} while (cursor.moveToNext());
			}
			cursor.close();
			Toast.makeText(this, "Query succeeded", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.replace_data:
			SQLiteDatabase dbreplace = dbHelper.getWritableDatabase();
			dbreplace.beginTransaction(); // ��������
			try {
				dbreplace.delete("Book", null, null);
				//����ͷ������ݲ��ᱻ�滻������ԭʼ���ݣ���ֹ���ݶ�ʧ
				//if (true) {
					// �������ֶ��׳�һ���쳣��������ʧ��
				//	throw new NullPointerException();
				//}
				ContentValues valuesre = new ContentValues();
				valuesre.put("name", "Game of Thrones");
				valuesre.put("author", "George Martin");
				valuesre.put("pages", 720);
				valuesre.put("price", 20.85);
				dbreplace.insert("Book", null, valuesre);
				dbreplace.setTransactionSuccessful(); // �����Ѿ�ִ�гɹ�
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbreplace.endTransaction(); // ��������
			}
			Toast.makeText(this, "Replace succeeded", Toast.LENGTH_SHORT).show();
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
