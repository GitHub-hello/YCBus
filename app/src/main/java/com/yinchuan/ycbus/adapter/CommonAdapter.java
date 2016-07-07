package com.yinchuan.ycbus.adapter;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mList;
	protected LayoutInflater mInflater;
	private DisplayImageOptions options;
	int layoutID;
	public CommonAdapter(Context context,List<T> datas,int layoutID) {
		// TODO Auto-generated constructor stub
		this.mContext=context;
		this.mList=datas;
		this.layoutID=layoutID;
		mInflater=LayoutInflater.from(context);
	}
	
	/** 清除 */
	public void clear() {
		if (mList != null) {
			mList.clear();
		}
		notifyDataSetChanged();
	}

	/** 数据追加 */
	public void add(List<T> mList) {
		if (mList == null) {
			mList = new ArrayList<T>();
		}
		this.mList.addAll(mList);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHoider holder = ViewHoider.get(mContext, convertView, parent, layoutID,position);
		

		convert(holder,getItem(position), position);
		return holder.getConvertView();
	}
	
	public abstract void convert(ViewHoider holder,T base,int position);
	
}
