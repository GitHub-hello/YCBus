package com.yinchuan.ycbus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.BusLine;
import com.yinchuan.ycbus.entity.StationItem;

import java.util.List;

public class BusLineByStationAdapter extends CommonAdapter<StationItem> {

	public BusLineByStationAdapter(Context context, List<StationItem> datas, int layoutID) {
		super(context, datas, layoutID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void convert(ViewHoider holder, StationItem base, int position) {
		// TODO Auto-generated method stub
		Log.v("TAG---", base.getStationName());
		Log.v("TAG---", base.getStartStation() + "|||" + base.getEndStation());
		holder.setText(R.id.line_name, base.getBusLineName());
		holder.setText(R.id.start, base.getStartStation());
		holder.setText(R.id.end, base.getEndStation());
		String zhan = base.getStations();
		Log.v("zhan----", zhan);
		if(Integer.parseInt(zhan) > 0){
			holder.setText(R.id.stations, "剩余 "+zhan+" 站");
		}else{
			holder.setText(R.id.stations, "即将进站");
		}
		int time = Integer.parseInt(base.getDura());
		if(time > 0){
			int m = time/60;
			int s = time-m*60;
			holder.setText(R.id.time, m+"分"+s+"秒");
		}else if(time < 0){
          holder.setText(R.id.time, "本时段暂无运行车辆");
			holder.setText(R.id.stations, "提示：");
		}else{
			holder.setText(R.id.time, "00分00秒");
		}

	}

	

}
