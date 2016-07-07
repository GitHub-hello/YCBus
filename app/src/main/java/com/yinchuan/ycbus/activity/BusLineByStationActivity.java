package com.yinchuan.ycbus.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.adapter.BusLineByStationAdapter;
import com.yinchuan.ycbus.entity.StationItem;
import com.yinchuan.ycbus.util.AsyncTastHttp;
import com.yinchuan.ycbus.util.ConstantsUrl;
import com.yinchuan.ycbus.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BusLineByStationActivity extends Activity implements OnClickListener {
	private TextView title;
    private ImageButton back;
	private MyListView list;
	private ArrayList<StationItem> mData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_busline_by_station);
		title = (TextView)findViewById(R.id.title);
		title.setText(getIntent().getStringExtra("STATION_NAME"));
		list = (MyListView)findViewById(R.id.list_view);
		back = (ImageButton)findViewById(R.id.back);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(BusLineByStationActivity.this, BusLineDetailActivity.class);
				intent.putExtra("BUS_LINE_NAME", mData.get(position).getBusLineName());
				intent.putExtra("BUS_LINE_ID", mData.get(position).getBusLineId());
				intent.putExtra("FLAG", mData.get(position).getFlag());
				startActivity(intent);
			}
		});
		back.setOnClickListener(this);
		getData();
	}

	private void getData(){
		HashMap<String, String>map = new HashMap<>();
		map.put("STATION_NAME", getIntent().getStringExtra("STATION_NAME"));
        new AsyncTastHttp(handler, ConstantsUrl.GET_BUS_STATION_DETAIL).execute(map);
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String response = (String)msg.obj;
			try {
				JSONObject object = new JSONObject(response);
				mData = (ArrayList)JSON.parseArray(object.getString("stations"), StationItem.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Log.v("size------", mData.size()+"");
			BusLineByStationAdapter adapter = new BusLineByStationAdapter(BusLineByStationActivity.this, mData, R.layout.item_bus_detail_item);
			list.setAdapter(adapter);
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.back:
				finish();
			break;

		default:
			break;
		}
	}
	
}
