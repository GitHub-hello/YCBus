package com.yinchuan.ycbus.adapter;

import android.content.Context;

import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.BusLine;

import java.util.List;

public class BusStationAdapter extends CommonAdapter<BusLine> {

	public BusStationAdapter(Context context, List<BusLine> datas, int layoutID) {
		super(context, datas, layoutID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHoider holder, BusLine base, int position) {
		// TODO Auto-generated method stub
		holder.setText(R.id.line_name, base.getStationName());
		holder.setImage(R.id.img, R.drawable.bus_station);
	}

	

}
