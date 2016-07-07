package com.yinchuan.ycbus.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.adapter.BusLineAdapter;
import com.yinchuan.ycbus.adapter.BusStationAdapter;
import com.yinchuan.ycbus.entity.BusLine;
import com.yinchuan.ycbus.util.AsyncTastHttp;
import com.yinchuan.ycbus.util.ConstantsUrl;
import com.yinchuan.ycbus.view.MyListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchListActivity extends Activity implements View.OnClickListener {
	
	private List<BusLine> mData = new ArrayList<>();
	private List<BusLine> sData = new ArrayList<>();
	private MyListView list_line, list_station;
	private TextView more_line, more_station;
	private EditText key_word;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search_list);
		list_line = (MyListView)findViewById(R.id.list_line);
		list_station = (MyListView)findViewById(R.id.list_station);
		more_line = (TextView)findViewById(R.id.more_line);
		more_station = (TextView)findViewById(R.id.more_station);
		more_station.setOnClickListener(this);
		more_line.setOnClickListener(this);
		list_line.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SearchListActivity.this, BusLineDetailActivity.class);
				intent.putExtra("BUS_LINE_NAME", mData.get(position).getBusLineName());
				intent.putExtra("BUS_LINE_ID", mData.get(position).getBusLineId());
				startActivity(intent);
			}
		});
		list_station.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SearchListActivity.this, BusLineByStationActivity.class);
				intent.putExtra("STATION_NAME", sData.get(position).getStationName());
				startActivity(intent);
			}
		});
		key_word = (EditText)findViewById(R.id.key_word);
		key_word.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				//Toast.makeText(SearchListActivity.this, key_word.getText().toString().trim(), 0).show();
				//getBusLine(key_word.getText().toString().trim());
				getBusLine();
				getBusStation();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void getBusStation(){
		HashMap<String, String>map = new HashMap<>();
		map.put("STATION_NAME", key_word.getText().toString().trim());
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
					sData = JSON.parseArray(object.getString("stations"), BusLine.class);
					List<BusLine> data = new ArrayList<>();
					int count = sData.size()>5?5:sData.size();
						for(int i = 0; i < count; i++){
							data.add(sData.get(i));
					}
					BusStationAdapter adapter = new BusStationAdapter(SearchListActivity.this,  data, R.layout.item_search_list);
					list_station.setAdapter(adapter);
				}else{
					List<BusLine> data = new ArrayList<>();
					BusStationAdapter adapter = new BusStationAdapter(SearchListActivity.this,  data, R.layout.item_search_list);
					list_station.setAdapter(adapter);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};
	private void getBusLine(){
	HashMap<String, String>map = new HashMap<>();
	map.put("BUS_LINE_NAME", key_word.getText().toString().trim());
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
					List<BusLine> data = new ArrayList<>();
					int count = mData.size()>5?5:mData.size();
					for(int i = 0; i < count; i++){
						data.add(mData.get(i));
					}
			       BusLineAdapter adapter = new BusLineAdapter(SearchListActivity.this,  data, R.layout.item_search_list);
			       list_line.setAdapter(adapter);
				}else{
					List<BusLine> data = new ArrayList<>();
					BusStationAdapter adapter = new BusStationAdapter(SearchListActivity.this,  data, R.layout.item_search_list);
					list_line.setAdapter(adapter);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};

	@Override
	public void onClick(View v) {
	switch (v.getId()){
		case R.id.more_line:
			Intent intent = new Intent(SearchListActivity.this, SearchAllActivity.class);
			intent.putExtra("key", key_word.getText().toString().trim());
			intent.putExtra("type", 0);
			startActivity(intent);
			break;
		case R.id.more_station:
			Intent mIntent = new Intent(SearchListActivity.this, SearchAllActivity.class);
			mIntent.putExtra("key", key_word.getText().toString().trim());
			mIntent.putExtra("type", 1);
			startActivity(mIntent);
			break;
	}
	}
}
