package com.yinchuan.ycbus.util;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.adapter.CircleImageAdapter;
import com.yinchuan.ycbus.entity.Adv;
import com.yinchuan.ycbus.widget.CircleFlowIndicator;
import com.yinchuan.ycbus.widget.ViewFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CircleImage {
	/**
	 * 普通图片组初始化
	 * @param viewFlow
	 * @param indic
	 * @param ctx
	 */
	public static void initCommunityImage(ArrayList<Adv> imgs,ViewFlow viewFlow,CircleFlowIndicator indic, final Context ctx, ImageLoader imageLoader){
		viewFlow.stopAutoFlowTimer();
		if(imgs!=null && imgs.size() > 0){
			int advCount = imgs.size();
			viewFlow.setmSideBuffer(advCount); // 实际图片张数
			viewFlow.setFlowIndicator(indic);
			viewFlow.setTimeSpan(3000);
			viewFlow.setSelection(0); // 设置初始位置
			viewFlow.startAutoFlowTimer(); // 启动自动播放
			final ArrayList<Map<String,String>> mData= new ArrayList<Map<String,String>>();
			for(int i =0; i<advCount; i++) {
				Map<String,String> item = new HashMap<String,String>();
				item.put("image_url", imgs.get(i).getPic_url());
				mData.add(item);
			}
			CircleImageAdapter adapter = new CircleImageAdapter(ctx, mData);
			viewFlow.setAdapter(adapter);
		}
	}

}