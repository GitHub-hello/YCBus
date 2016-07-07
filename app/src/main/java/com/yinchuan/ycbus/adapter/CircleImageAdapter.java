package com.yinchuan.ycbus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.MyApplication;
import com.yinchuan.ycbus.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/25.
 */
public class CircleImageAdapter extends BaseAdapter {
    private ImageLoader imageLoader;
    private Context context;
    private ArrayList<Map<String, String>> mData;
    private LayoutInflater mInflater;
    public CircleImageAdapter( Context context, ArrayList<Map<String, String>> mData) {
        this.imageLoader = imageLoader;
        this.context = context;
        this.mData = mData;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_circle_image, null);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imgView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.imageView.setTag(position);
        viewHolder.imageView.setOnClickListener(new ClickListener(position));
        ImageLoader.getInstance().displayImage(mData.get(position % (mData.size())).get("image_url"), viewHolder.imageView);
        return convertView;
    }
    class ClickListener implements View.OnClickListener{
        int pos;
        public ClickListener(int pos){
            this.pos = pos;
        }
        @Override
        public void onClick(View v) {

        }
    }
    class ViewHolder{
        ImageView imageView;
    }
}