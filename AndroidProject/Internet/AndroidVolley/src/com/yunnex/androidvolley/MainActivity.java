package com.yunnex.androidvolley;

import java.io.IOException;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

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

	public static final String TAG = "MyTag";//定义tag标志
	// Get a RequestQueue
	RequestQueue mQueue ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final TextView mTextView = (TextView) findViewById(R.id.text);
		final ImageView mImageView = (ImageView) findViewById(R.id.myImage);
		final NetworkImageView networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);  

		// Get a RequestQueue
		mQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
		//发送GET请求
		String url ="http://www.baidu.com"; //百度网页
		StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						//mTextView.setText("Response is: "+ response.substring(0,500));
						LogUtil.d("TAG", response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						//mTextView.setText("That didn't work!");
						Log.e("TAG", error.getMessage(), error);
					}
				});
		//为请求绑定一个tag对象
		stringRequest.setTag(TAG);
		//添加到请求队列，需要此功能，释放即可
		MySingleton.getInstance(this).addToRequestQueue(stringRequest);

		//利用JsonObject请求JSON数据
		String urlJson = "http://m.weather.com.cn/data/101010100.html";//天气数据
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlJson, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						//mTextView.setText("Response is: "+ response.toString());
						LogUtil.d("TAG", response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						//mTextView.setText("That didn't work!");
						Log.e("TAG", error.getMessage(), error);
					}
				});
		//为请求绑定一个tag对象
		jsonObjectRequest.setTag(TAG);
		//添加到请求队列，需要此功能，释放即可
		MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
		
		//利用Json请求JSON数据
		String urljson = "http://www.weather.com.cn/data/sk/101010100.html";
		GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(urljson, Weather.class,
				new Response.Listener<Weather>() {
					@Override
					public void onResponse(Weather weather) {
						WeatherInfo weatherInfo = weather.getWeatherinfo();
						LogUtil.d("TAG", "city is " + weatherInfo.getCity());
						LogUtil.d("TAG", "temp is " + weatherInfo.getTemp());
						LogUtil.d("TAG", "time is " + weatherInfo.getTime());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		//为请求绑定一个tag对象
		gsonRequest.setTag(TAG);
		//添加到请求队列，需要此功能，释放即可
		MySingleton.getInstance(this).addToRequestQueue(gsonRequest);
		
		//请求XML数据
		String urlXML = "http://flash.weather.com.cn/wmaps/xml/china.xml";
		XMLRequest xmlRequest = new XMLRequest(urlXML,
				new Response.Listener<XmlPullParser>() {
					@Override
					public void onResponse(XmlPullParser response) {
						try {
							int eventType = response.getEventType();
							while (eventType != XmlPullParser.END_DOCUMENT) {
								switch (eventType) {
								case XmlPullParser.START_TAG:
									String nodeName = response.getName();
									if ("city".equals(nodeName)) {
										String pName = response.getAttributeValue(0);
										LogUtil.d("TAG", "pName is " + pName);
									}
									break;
								}
								eventType = response.next();
							}
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		//为请求绑定一个tag对象
		xmlRequest.setTag(TAG);
		//添加到请求队列，需要此功能，释放即可
		MySingleton.getInstance(this).addToRequestQueue(xmlRequest);
		
		// ImageRequest加载网络图片的用法
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
		//为请求绑定一个tag对象
		imageRequest.setTag(TAG);
		//添加到请求队列，需要此功能，释放即可
		//MySingleton.getInstance(this).addToRequestQueue(imageRequest);
		
		//ImageLoader加载网络图片的用法
		String urlimage = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
		ImageLoader imageLoader = MySingleton.getInstance(this).getImageLoader();
		ImageListener listener = ImageLoader.getImageListener(mImageView,
				R.drawable.ic_launcher, R.drawable.myimage);
		//imageLoader.get(urlimage, listener);//加载图片，需要此功能，释放即可
		
		//NetworkImageView加载网络图片的用法
		String urlnet = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
		ImageLoader imageLoaderNet = MySingleton.getInstance(this).getImageLoader();
		networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
		networkImageView.setErrorImageResId(R.drawable.myimage);
		networkImageView.setImageUrl(urlnet,imageLoaderNet);//加载图片，需要此功能，释放即可

	}

	@Override
	protected void onStop () {
	    super.onStop();
	    //取消网络请求
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
