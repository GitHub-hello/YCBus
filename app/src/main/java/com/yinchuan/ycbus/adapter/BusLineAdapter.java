package com.yinchuan.ycbus.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.BusLine;

public class BusLineAdapter extends CommonAdapter<BusLine> {

	public BusLineAdapter(Context context, List<BusLine> datas, int layoutID) {
		super(context, datas, layoutID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHoider holder, BusLine base, int position) {
		// TODO Auto-generated method stub
		holder.setText(R.id.line_name, base.getBusLineName());
		holder.setImage(R.id.img, R.drawable.bus_blue);
	}

	

}
