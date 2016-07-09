package com.yinchuan.ycbus.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.MyApplication;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.PictureDetail;
import com.yinchuan.ycbus.entity.PictureDetailResult;
import com.yinchuan.ycbus.util.AsyTaskHttpGet;
import com.yinchuan.ycbus.util.ConstantsUrl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/13.
 */
public class GalleryPicActivity extends Activity {
    private Gallery gallery;
    private ImageLoader imageLoader;
    private ArrayList<PictureDetail> mData;
    private ImageButton back;
    private TextView title;
    private String id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageLoader = imageLoader.getInstance();
        initView();
        getData();
    }
    private void initView(){
        id = getIntent().getStringExtra("id");
        back = (ImageButton)findViewById(R.id.back);
        title = (TextView)findViewById(R.id.title);
        gallery = (Gallery)findViewById(R.id.img_gallery);
       gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               title.setText((position + 1) + "/" + mData.size());
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFinish();
            }
        });
    }
    private void getData(){

        HashMap<String, String> map = new HashMap<>();
        map.put("ch", "beauty");
        map.put("listtype", "hot");
        map.put("id", id);
        new AsyTaskHttpGet(handler, ConstantsUrl.GET_360_PICTURE_DETAIL, null).execute(map);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String response = (String) msg.obj;
            PictureDetailResult result = JSON.parseObject(response, PictureDetailResult.class);
            mData = result.getList();
            setData();
        }
    };

    private void setData(){
        GalleryAdapter adapter = new GalleryAdapter(GalleryPicActivity.this,mData);
        gallery.setAdapter(adapter);
        gallery.setSelection(getIntent().getIntExtra("posi", 0));
        WindowManager window = getWindowManager();
        int width = window.getDefaultDisplay().getWidth();
        Log.v("width===", width+"");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        doFinish();
    }

    private void doFinish(){
        finish();
    }

    class GalleryAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<PictureDetail> mData;

        public GalleryAdapter(Context context, ArrayList<PictureDetail> mData) {
            this.context = context;
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView img = new ImageView(context);
            img.setBackgroundColor(0x000000);
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Gallery.LayoutParams lp = new Gallery.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            img.setLayoutParams(lp);
            ImageLoader.getInstance().displayImage( mData.get(position).getQhimg_url(), img, MyApplication.setOptions());
            return img;
        }

    }
}
