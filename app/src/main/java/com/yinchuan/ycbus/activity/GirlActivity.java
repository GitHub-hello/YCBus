package com.yinchuan.ycbus.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.adapter.GirlRecycleAdapter;
import com.yinchuan.ycbus.entity.BeautyCategory;
import com.yinchuan.ycbus.entity.BeautyPicResult;
import com.yinchuan.ycbus.entity.BeautyPicture;
import com.yinchuan.ycbus.entity.Girl;
import com.yinchuan.ycbus.entity.GirlResult;
import com.yinchuan.ycbus.util.AsyTaskHttpGet;
import com.yinchuan.ycbus.util.CommonUtil;
import com.yinchuan.ycbus.util.ConstantsUrl;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class GirlActivity extends AppCompatActivity {
	private RecyclerView recycler;
	private ArrayList<Girl> mData = new ArrayList<>();
	private ArrayList<BeautyCategory> categorys = new ArrayList<>();
	private ArrayList<BeautyPicture> pictures = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_girl_recycle);
		recycler = (RecyclerView)findViewById(R.id.recycle);
		recycler.setItemAnimator(new DefaultItemAnimator());
		recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//设置RecyclerView布局管理器为2列垂直排布
		getData();
		getCategoryData();
      new Thread(new Runnable() {
	@Override
	public void run() {
		testHtml();
	}
    }).start();
	}
	private void getData(){
		HashMap<String, String>map = new HashMap<String, String>();
        map.put("category", "美女");
		map.put("tag", "全部");
		map.put("start", "0");
		map.put("len", "50");
		new AsyTaskHttpGet(hanlder, ConstantsUrl.GET_PICTURE, null).execute(map);
	}

	Handler hanlder = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String response = (String)msg.obj;
			Log.v("++++++++++++++++", response);
			GirlResult result = JSON.parseObject(response, GirlResult.class);
			mData = result.getAll_items();
			Log.v("size===", mData.size()+"" );
			setAdapter();

		}
	};
	private void getCategoryData(){
		HashMap<String, String>map = new HashMap<>();
		map.put("callback","jQuery18305014866125749894_1467972855303");
		map.put("type", "beauty");
		map.put("_", "1467972855422");
		new AsyTaskHttpGet(categoryHandler, ConstantsUrl.GET_360_PICTURE_CATEGORY, null).execute(map);
	}
	Handler categoryHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String response = (String)msg.obj;
			int index = response.indexOf("(");
			response = response.substring(index+1, response.length()-1);
			JSONObject object = null;
			try {
				JSONObject data = new JSONObject(response);
				object = data.getJSONObject("data");
				for(Iterator it = object.keys(); it.hasNext();){
					String key = (String)it.next();
					String value = (String)object.getString(key);
					BeautyCategory model = JSON.parseObject(value, BeautyCategory.class);
					model.setTitle(key);
					categorys.add(model);
					Log.v("model-----", model.toString());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};
	private void setAdapter(){
		GirlRecycleAdapter adapter = new GirlRecycleAdapter(this, mData);
		recycler.setAdapter(adapter);
	}
	private void testHtml(){
		String url = "http://image.so.com/z?ch=beauty";
		//String url = "http://www.biquge.la/xuanhuanxiaoshuo/";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			if(doc != null){
				Elements es = doc.getElementsByTag("script");
				for(Element element :es){
					Log.v("element------", element.toString());
					String text = element.html();
					Log.v("element------",text);
					if(text.contains("window.initData =")){
						String jsonData = text.replace("window.initData =", "").trim();
						try {
							JSONObject object = new JSONObject(jsonData);
							BeautyPicResult result = JSON.parseObject(object.getString("data"), BeautyPicResult.class);
							for(BeautyPicture mo :result.getList() ){
                                pictures.add(mo);
                            }
							Log.v("----------------", pictures.size()+"");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
