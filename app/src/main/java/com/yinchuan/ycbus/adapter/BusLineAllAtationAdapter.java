package com.yinchuan.ycbus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.Stations;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class BusLineAllAtationAdapter extends CommonAdapter<Stations> {
    public BusLineAllAtationAdapter(Context context, List<Stations> datas, int layoutID) {
        super(context, datas, layoutID);
    }

    @Override
    public void convert(ViewHoider holder, Stations base, int position) {
      holder.setText(R.id.num, position+1+"");
        holder.setText(R.id.station_name, base.getStationName());
        Log.v("TAG", base.getStationName()+"---"+base.getStatus_run()+"||"+base.getStatus_stop());
        if(base.getStatus_run() != null && base.getCount_run() > 0){
                holder.getView(R.id.left).setVisibility(View.VISIBLE);
                holder.setText(R.id.run_count, "X" + base.getCount_run() + "开往");}
            else{
                holder.getView(R.id.left).setVisibility(View.GONE);
            }
        if(base.getStatus_stop() != null && base.getCount_stop() > 0) {
                holder.getView(R.id.right).setVisibility(View.VISIBLE);
                holder.setText(R.id.stop_count, "X" + base.getCount_stop() + "停靠");
            }else{
                holder.getView(R.id.right).setVisibility(View.GONE);
            }
    }
}
