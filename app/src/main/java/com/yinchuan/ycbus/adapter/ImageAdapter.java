
package com.yinchuan.ycbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.R;

/*
 * 数据装载类
 */
public class ImageAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	//private static final int[] ids = {R.drawable.test1, R.drawable.test2, R.drawable.test3 };
	private static final String[] ids = {"http://101.200.88.207/api/uploads/event_pic/sw_563332773472b.jpg", "http://101.200.88.207/api/uploads/event_pic/sw_5633329529f17.jpg", "http://pic20.nipic.com/20120502/9783151_111254241000_2.jpg" };
	public ImageAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;   //返回很大的值使得getView中的position不断增大来实现循环。
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_circle_image, null);
		}
		//((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(ids[position%ids.length]);
		ImageLoader.getInstance().displayImage(ids[position%ids.length], (ImageView) convertView.findViewById(R.id.imgView));
		return convertView;
	}

}
