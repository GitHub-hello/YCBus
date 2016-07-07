package com.yinchuan.ycbus.widget;

import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.adapter.CircleImageAdapter;
import com.yinchuan.ycbus.entity.Adv;

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
	public static void initCommunityImage(ArrayList<String> imgs,ViewFlow viewFlow,CircleFlowIndicator indic, Context ctx){
		//viewFlow.stopAutoFlowTimer();
		if(imgs!=null && imgs.size() > 0){
			int advCount = imgs.size();
			final ArrayList<Map<String,String>> mData= new ArrayList<Map<String,String>>();
			for(int i =0; i<advCount; i++) {
				Map<String,String> item = new HashMap<String,String>();
				item.put("image_url", imgs.get(i));
				mData.add(item);
			}
			CircleImageAdapter adapter = new CircleImageAdapter(ctx, mData);
			viewFlow.setAdapter(adapter);
			viewFlow.setmSideBuffer(advCount); // 实际图片张数
			viewFlow.setFlowIndicator(indic);
			viewFlow.setTimeSpan(3000);
			viewFlow.setSelection(0); // 设置初始位置
			viewFlow.startAutoFlowTimer(); // 启动自动播放

		}
	}

}