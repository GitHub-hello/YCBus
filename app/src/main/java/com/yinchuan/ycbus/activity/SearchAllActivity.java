package com.yinchuan.ycbus.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;


import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.adapter.BusLineAdapter;
import com.yinchuan.ycbus.adapter.BusStationAdapter;
import com.yinchuan.ycbus.entity.BusLine;
import com.yinchuan.ycbus.util.AsyncTastHttp;
import com.yinchuan.ycbus.util.ConstantsUrl;
import com.yinchuan.ycbus.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchAllActivity extends Activity{
	
	private List<BusLine> mData = new ArrayList<>();
	private MyListView list;
	private String key;
    private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_all);
		list = (MyListView)findViewById(R.id.list);
		key = getIntent().getStringExtra("key");
		 type = getIntent().getIntExtra("type", 0);
		switch (type){
			case 0:
				getBusLine();
				break;
			case 1:
				getBusStation();
				break;
		}
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(type == 0){
					Intent intent = new Intent(SearchAllActivity.this, BusLineDetailActivity.class);
					intent.putExtra("BUS_LINE_NAME", mData.get(position).getBusLineName());
					intent.putExtra("BUS_LINE_ID", mData.get(position).getBusLineId());
					startActivity(intent);
				}else if(type == 1){
					Intent intent = new Intent(SearchAllActivity.this, BusLineByStationActivity.class);
					intent.putExtra("STATION_NAME", mData.get(position).getStationName());
					startActivity(intent);
				}

			}
		});
	}

	private void getBusStation(){
		HashMap<String, String>map = new HashMap<>();
		map.put("STATION_NAME", key);
		AsyncTastHttp task = new AsyncTastHttp(handler, ConstantsUrl.GET_BUSSTATION_LIST);
		task.execute(map);
	}
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			String response = (String)msg.obj;
			try {
				JSONObject object = new JSONObject(response);
				if(object.getBoolean("success")){
					mData = JSON.parseArray(object.getString("stations"), BusLine.class);
					BusStationAdapter adapter = new BusStationAdapter(SearchAllActivity.this,  mData, R.layout.item_search_list);
					list.setAdapter(adapter);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};
	private void getBusLine(){
	HashMap<String, String>map = new HashMap<>();
	map.put("BUS_LINE_NAME", key);
	AsyncTastHttp task = new AsyncTastHttp(mHandler, ConstantsUrl.GET_BUSLINE_LIST);
	task.execute(map);
	}
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			String response = (String)msg.obj;
			try {
				JSONObject object = new JSONObject(response);
				if(object.getBoolean("success")){
					mData = JSON.parseArray(object.getString("busLines"), BusLine.class);
			       BusLineAdapter adapter = new BusLineAdapter(SearchAllActivity.this,  mData, R.layout.item_search_list);
			       list.setAdapter(adapter);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
}
