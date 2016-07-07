package com.yinchuan.ycbus.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.adapter.BusLineAdapter;
import com.yinchuan.ycbus.adapter.BusLineAllAtationAdapter;
import com.yinchuan.ycbus.adapter.BusStationAdapter;
import com.yinchuan.ycbus.entity.BusLine;
import com.yinchuan.ycbus.entity.RunningBus;
import com.yinchuan.ycbus.entity.Stations;
import com.yinchuan.ycbus.util.AsyncTastHttp;
import com.yinchuan.ycbus.util.ConstantsUrl;
import com.yinchuan.ycbus.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusLineDetailActivity extends Activity implements View.OnClickListener {
	
	private List<Stations> mData = new ArrayList<>();
	private MyListView list;
	private String key;
	private TextView title;
	private ImageButton back;
	private TextView start_at, end_at, run_time;
    private String  BUS_LINE_NAME, BUS_LINE_ID;
	private String FLAG = "1";
	private BusLineAllAtationAdapter adapter;
	private ImageView switch_line, refresh, collect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_line);
		if(getIntent().hasExtra("FLAG")){
			FLAG = getIntent().getStringExtra("FLAG");
		}
		list = (MyListView)findViewById(R.id.list);
		back = (ImageButton)findViewById(R.id.back);
		start_at = (TextView)findViewById(R.id.start_at);
		end_at = (TextView)findViewById(R.id.end_at);
		run_time = (TextView)findViewById(R.id.run_time);
		switch_line = (ImageView)findViewById(R.id.switch_line);
		refresh = (ImageView)findViewById(R.id.refresh);
		collect = (ImageView)findViewById(R.id.collect);
		switch_line.setOnClickListener(this);
		refresh.setOnClickListener(this);
		collect.setOnClickListener(this);
		back.setOnClickListener(this);
		title = (TextView)findViewById(R.id.title);
		BUS_LINE_NAME = getIntent().getStringExtra("BUS_LINE_NAME");
		BUS_LINE_ID = getIntent().getStringExtra("BUS_LINE_ID");
		title.setText(BUS_LINE_NAME);
		getBusLine();
		getRunningBus();
		timerHandler.postDelayed(runnable, 10*1000);
	}
	Handler timerHandler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			getRunningBus();
			timerHandler.postDelayed(runnable, 10*1000);
		}
	};
	private void getBusLine(){
	HashMap<String, String>map = new HashMap<>();
	map.put("BUS_LINE_NAME", BUS_LINE_NAME);
		map.put("BUS_LINE_ID", BUS_LINE_ID);
		map.put("FLAG", FLAG);
	AsyncTastHttp task = new AsyncTastHttp(mHandler, ConstantsUrl.GET_BUSLINE_ALLSTATION);
	task.execute(map);
	}

	private void getRunningBus(){
		HashMap<String, String>map = new HashMap<>();
		map.put("BUS_LINE_NAME", BUS_LINE_NAME);
		map.put("BUS_LINE_ID", BUS_LINE_ID);
		map.put("FLAG", FLAG);
		AsyncTastHttp task = new AsyncTastHttp(runHandler, ConstantsUrl.GET_BUS_RUNNING);
		task.execute(map);
	}
	Handler runHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String response = (String)msg.obj;
			JSONObject object = null;
			try {
				object = new JSONObject(response);
				List<RunningBus> runningBus = JSON.parseArray(object.getString("vehiclePos"), RunningBus.class);
				Log.v("------", runningBus.size()+"");
				for(Stations station : mData){
					int count_run = 0;
					int count_stop = 0;
                for(RunningBus bus : runningBus){
                     if(station.getStrank().equals(bus.getStrank())){
						 if(bus.getStatus().equals("2")){
							station.setStatus_stop("2");
							 count_stop++;
						 }else{
							 station.setStatus_run("1");
							 count_run++;
						 }
					 }
				 }
					station.setCount_run(count_run);
					station.setCount_stop(count_stop);
				}
				setData();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			String response = (String)msg.obj;
			try {
				JSONObject object = new JSONObject(response);
				if(object.getBoolean("success")){
					BusLine busLine = JSON.parseObject(object.getString("busLine"), BusLine.class);
					start_at.setText(busLine.getStartStation());
					end_at.setText(busLine.getEndStation());
					run_time.setText(busLine.getStartTime() + "  --  " + busLine.getEndTime());
					mData = busLine.getStations();
					setData();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
private void setData(){
	if(adapter == null){
		adapter = new BusLineAllAtationAdapter(BusLineDetailActivity.this,  mData, R.layout.item_bus_status);
		list.setAdapter(adapter);
	}else{
		adapter.notifyDataSetChanged();
	}

}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		timerHandler.removeCallbacks(runnable);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.back:
				finish();
				break;
			case R.id.collect:
				Toast.makeText(this, "敬请期待！", Toast.LENGTH_SHORT).show();
				break;
			case R.id.refresh:
				//mData.clear();
				getBusLine();
				getRunningBus();
				break;
			case R.id.switch_line:
				adapter = null;
				if(FLAG.equals("1")){
					FLAG = "2";
				}else if(FLAG.equals("2")){
					FLAG = "1";
				}
				getBusLine();
				getRunningBus();
				break;
		}
	}
}
