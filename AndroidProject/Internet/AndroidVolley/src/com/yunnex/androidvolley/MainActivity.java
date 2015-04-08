package com.yunnex.androidvolley;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final String TAG = "MyTag";//����tag��־
	RequestQueue mQueue ; //����һ��RequestQueue����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final TextView mTextView = (TextView) findViewById(R.id.text);
		final ImageView mImageView = (ImageView) findViewById(R.id.myImage);
		final NetworkImageView networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);  

		mQueue = Volley.newRequestQueue(this);
		
		//����GET����
		String url ="http://www.baidu.com"; 
		StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						//mTextView.setText("Response is: "+ response.substring(0,500));
						Log.d("TAG", response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						//mTextView.setText("That didn't work!");
						Log.e("TAG", error.getMessage(), error);
					}
				});
		mQueue.add(stringRequest);//��ӵ�������У���Ҫ�˹��ܣ��ͷż���
		
		//����POST����,�����
				/*StringRequest stringRequest = new StringRequest(Request.Method.POST, url,Listener,errorListener) {
					@Override
					protected Map<String, String> getParams() throws AuthFailureError {
						Map<String, String> map = new HashMap<String, String>();
						map.put("params1", "value1");
						map.put("params2", "value2");
						return map;
					}
				};*/
		
		//����JSON����
		String urlJson = "http://m.weather.com.cn/data/101010100.html";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlJson, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						//mTextView.setText("Response is: "+ response.toString());
						Log.d("TAG", response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						//mTextView.setText("That didn't work!");
						Log.e("TAG", error.getMessage(), error);
					}
				});
		mQueue.add(jsonObjectRequest);//��ӵ�������У���Ҫ�˹��ܣ��ͷż���
		
		// ImageRequest��������ͼƬ���÷�
		String urlImage = "http://i.imgur.com/7spzG.png";
		ImageRequest imageRequest = new ImageRequest(urlImage,
				new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap response) {
						mImageView.setImageBitmap(response);
					}
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						mImageView.setImageResource(R.drawable.ic_launcher);
					}
				});
		//mQueue.add(imageRequest);//��ӵ�������У���Ҫ�˹��ܣ��ͷż���
		
		//ImageLoader��������ͼƬ���÷�
		String urlimage = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
		ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
		ImageListener listener = ImageLoader.getImageListener(mImageView,
				R.drawable.ic_launcher, R.drawable.myimage);
		//imageLoader.get(urlimage, listener);//����ͼƬ����Ҫ�˹��ܣ��ͷż���
		
		//NetworkImageView��������ͼƬ���÷�
		String urlnet = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
		ImageLoader imageLoaderNet = new ImageLoader(mQueue, new BitmapCache());
		networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
		networkImageView.setErrorImageResId(R.drawable.myimage);
		networkImageView.setImageUrl(urlnet,imageLoaderNet);//����ͼƬ����Ҫ�˹��ܣ��ͷż���
		
		//Ϊ�����һ��tag����
		stringRequest.setTag(TAG);
		jsonObjectRequest.setTag(TAG);
		imageRequest.setTag(TAG);
		
	}

	@Override
	protected void onStop () {
	    super.onStop();
	    //ȡ����������
		if (mQueue != null) {
	    	mQueue.cancelAll(TAG);
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
