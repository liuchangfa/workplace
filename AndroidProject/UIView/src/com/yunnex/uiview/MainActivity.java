package com.yunnex.uiview;

import java.util.ArrayList;
import java.util.List;

import com.yunnex.uiview.MyListView.OnDeleteListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends Activity {
	
	 private MyListView myListView;  
	  
	 private MyListViewAdapter adapter;  
	  
	 private List<String> contentList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initList();  
        myListView = (MyListView) findViewById(R.id.my_list_view);  
        myListView.setOnDeleteListener(new OnDeleteListener() {  
            @Override  
            public void onDelete(int index) {  
                contentList.remove(index);  
                adapter.notifyDataSetChanged();  
            }  
        });  
        adapter = new MyListViewAdapter(this, 0, contentList);  
        myListView.setAdapter(adapter);  
	}

	 private void initList() {  
	        contentList.add("Content Item 1");  
	        contentList.add("Content Item 2");  
	        contentList.add("Content Item 3");  
	        contentList.add("Content Item 4");  
	        contentList.add("Content Item 5");  
	        contentList.add("Content Item 6");  
	        contentList.add("Content Item 7");  
	        contentList.add("Content Item 8");  
	        contentList.add("Content Item 9");  
	        contentList.add("Content Item 10");  
	        contentList.add("Content Item 11");  
	        contentList.add("Content Item 12");  
	        contentList.add("Content Item 13");  
	        contentList.add("Content Item 14");  
	        contentList.add("Content Item 15");  
	        contentList.add("Content Item 16");  
	        contentList.add("Content Item 17");  
	        contentList.add("Content Item 18");  
	        contentList.add("Content Item 19");  
	        contentList.add("Content Item 20");  
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
