
package com.grumoon.volleydemo.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.grumoon.volleydemo.R;
import com.grumoon.volleydemo.custom.GsonRequest;
import com.grumoon.volleydemo.util.Constants;
import com.grumoon.volleydemo.util.LogUtil;
import com.grumoon.volleydemo.util.StringUtil;
import com.grumoon.volleydemo.util.ToastUtil;
import com.grumoon.volleydemo.util.VolleyUtil;
import com.grumoon.volleydemo.util.Weather;
import com.grumoon.volleydemo.util.WeatherInfo;


public class GsonRequestFragment extends Fragment {
	public static final int INDEX = 32;

	private ListView lvWeather;
	private static final int[] ids = { R.id.gs_weather_city, R.id.gs_weather_temp, R.id.gs_weather_time};
	private static final String[] keys = { "city", "temp", "time"};
	private List<Map<String, String>> weatherDataList;
	
	private SimpleAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fr_gson_request, container, false);

		weatherDataList = new ArrayList<Map<String,String>>();

		lvWeather = (ListView) view.findViewById(R.id.gs_weather);
		adapter = new SimpleAdapter(getActivity(), weatherDataList, R.layout.fr_gson_request_list_item, keys, ids);
		lvWeather.setAdapter(adapter);

		// xml数据请求
		GsonRequest<Weather> request = new GsonRequest<Weather>(StringUtil.preUrl(Constants.DEFAULT_GSON_REQUEST_URL), Weather.class,
				new Response.Listener<Weather>() {
					@Override
					public void onResponse(Weather weather) {
						weatherDataList.clear();
						
						Map<String, String> weatherMap = new HashMap<String, String>();
						WeatherInfo weatherInfo = weather.getWeatherinfo();
			
						LogUtil.d("TAG", "city is " + weatherInfo.getCity());
						LogUtil.d("TAG", "temp is " + weatherInfo.getTemp());
						LogUtil.d("TAG", "time is " + weatherInfo.getTime());
						
						weatherMap.put("city", weatherInfo.getCity());
						weatherMap.put("temp", weatherInfo.getTemp());
						weatherMap.put("time", weatherInfo.getTime());
					
						weatherDataList.add(weatherMap);
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						ToastUtil.showToast(getActivity(), getResources().getString(R.string.request_fail_text));
					}
				});

		//将请求都绑定到执行的Activity上
		request.setTag(this);

		VolleyUtil.getQueue(getActivity()).add(request);

		return view;
	}

	@Override
	public void onDestroyView() {
		VolleyUtil.getQueue(getActivity()).cancelAll(this);
		super.onDestroyView();
	}

}

