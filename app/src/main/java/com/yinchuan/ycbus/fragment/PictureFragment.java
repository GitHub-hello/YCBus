package com.yinchuan.ycbus.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.activity.GalleryPicActivity;
import com.yinchuan.ycbus.adapter.GirlRecycleAdapter;
import com.yinchuan.ycbus.adapter.PictureRecycleAdapter;
import com.yinchuan.ycbus.entity.BeautyPicResult;
import com.yinchuan.ycbus.entity.BeautyPicture;
import com.yinchuan.ycbus.util.AsyTaskHttpGet;
import com.yinchuan.ycbus.util.CommonUtil;
import com.yinchuan.ycbus.util.ConstantsUrl;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/6/30.
 */
public class PictureFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private RecyclerView recycler;
    private SwipeRefreshLayout swipe;
    private String t1, path;
    private ArrayList<BeautyPicture> pictures = new ArrayList<>();
     private int currentPage = 1;
    private PictureRecycleAdapter adapter;
    private int lastCount = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_girl_recycle, container, false);
        initView();
        // asyTask();
        getHtml();
        return view;
    }

    private void initView() {
        Bundle bundle = getArguments();
        t1 = bundle.getString("t1");
        path = bundle.getString("url");
        recycler = (RecyclerView) view.findViewById(R.id.recycle);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//设置RecyclerView布局管理器为2列垂直排布
        swipe = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        swipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipe.setOnRefreshListener(this);
    }

    private void getHtml() {
        HashMap<String, String> map = new HashMap<>();
        map.put("ch", "beauty");
        map.put("listtype", "hot");
        map.put("t1", t1);
        map.put("sn", String.valueOf(currentPage * 30));
        //new AsyTaskHttpGet(handler, ConstantsUrl.GET_360_PICTURE_BY_HTML, null).execute(map);//通过解析HTML中的数据
        new AsyTaskHttpGet(jsonHandler, ConstantsUrl.GET_360_PICTURE_BY_JSON, null).execute(map);//通过直接解析JSON数据
    }

    Handler jsonHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String response = (String) msg.obj;
                BeautyPicResult result = JSON.parseObject(response, BeautyPicResult.class);
                for (BeautyPicture mo : result.getList()) {
                    pictures.add(mo);
                }
                if(result.getList().size() > 0){
                    setAdapter();
                }else{
                    CommonUtil.showMessage(getActivity(), "没有更多数据了！");
                    if(swipe.isRefreshing()){
                        swipe.setRefreshing(false);
                    }
                }

            }

    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String response = (String) msg.obj;
            Document doc = Jsoup.parse(response);
            if (doc != null) {
                Elements es = doc.getElementsByTag("script");
                for (Element element : es) {
                    Log.v("|||1------", element.toString());
                    String text = element.html();
                    Log.v("|||2------", text);
                    if (text.contains("window.initData =")) {
                        String jsonData = text.replace("window.initData =", "").trim();
                        Log.v("|||jsonData------", jsonData);
                        try {
                            JSONObject object = new JSONObject(jsonData);
                            BeautyPicResult result = JSON.parseObject(object.getString("data"), BeautyPicResult.class);
                            for (BeautyPicture mo : result.getList()) {
                                Log.v("|||3----------------", mo.toString());
                                pictures.add(mo);
                            }
                            setAdapter();
                            Log.v("|||4----------------", pictures.size() + "");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    };
        ;

        private void asyTask() {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    getData();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    setAdapter();
                }
            }.execute(new Void[]{});
        }

        private void getData() {
            String url_part = "";
            double x = Math.random() * 10;
       /*if( x > 5){
            url_part = "?ch=beauty&listtype=hot&t1=595";
       }else{
           url_part = "?ch=beauty&listtype=hot&t1=600";

       }*/

            // String url = ConstantsUrl.GET_360_PICTURE.concat(url_part);

            String url = "";
            if (path == null || path.equals("")) {
                url = "http://m.image.so.com/z?ch=beauty";
            } else {
                url = path;
            }
            Log.v("|||||", url);
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
                Log.v("|||0------", doc.toString());
                if (doc != null) {
                    Elements es = doc.getElementsByTag("script");
                    for (Element element : es) {
                        Log.v("|||1------", element.toString());
                        String text = element.html();
                        Log.v("|||2------", text);
                        if (text.contains("window.initData =")) {
                            String jsonData = text.replace("window.initData =", "").trim();
                            Log.v("|||jsonData------", jsonData);
                            try {
                                JSONObject object = new JSONObject(jsonData);
                                BeautyPicResult result = JSON.parseObject(object.getString("data"), BeautyPicResult.class);
                                for (BeautyPicture mo : result.getList()) {
                                    Log.v("|||3----------------", mo.toString());
                                    pictures.add(mo);
                                }
                                Log.v("|||4----------------", pictures.size() + "");
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

        private void setAdapter() {
            if(adapter == null){
                adapter = new PictureRecycleAdapter(getActivity(), pictures);
                recycler.setAdapter(adapter);
            }else{
                adapter.notifyItemRangeInserted(lastCount+1, pictures.size() - lastCount);
            }
             lastCount = pictures.size();
            if(swipe.isRefreshing()){
                swipe.setRefreshing(false);
            }
            adapter.setOnClickListener(new PictureRecycleAdapter.OnItemClickListener() {
                @Override
                public void ItemClickListener(View view, int postion) {
                    Intent intent = new Intent(getActivity(), GalleryPicActivity.class);
                    intent.putExtra("id", pictures.get(postion).getId());
                    startActivity(intent);
                }

                @Override
                public void ItemLongClickListener(View view, int postion) {

                }
            });
        }

    @Override
    public void onRefresh() {
      currentPage++;
        getHtml();
    }
}

