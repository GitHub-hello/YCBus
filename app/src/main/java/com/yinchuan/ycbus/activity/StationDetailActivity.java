package com.yinchuan.ycbus.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yinchuan.ycbus.R;

public class StationDetailActivity extends Activity implements OnClickListener {
	private TextView search;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		search = (TextView)findViewById(R.id.search);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			Intent intent = new Intent(this, SearchListActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
}
