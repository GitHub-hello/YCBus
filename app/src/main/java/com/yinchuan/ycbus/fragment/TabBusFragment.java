package com.yinchuan.ycbus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.activity.SearchListActivity;
import com.yinchuan.ycbus.adapter.ImageAdapter;
import com.yinchuan.ycbus.entity.Adv;
import com.yinchuan.ycbus.widget.CircleFlowIndicator;
import com.yinchuan.ycbus.widget.CircleImage;
import com.yinchuan.ycbus.widget.ViewFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class TabBusFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private ViewFlow viewFlow;
    private CircleFlowIndicator flowIndicator;
    private ArrayList<String> mData;
    private ImageLoader imageLoader;
    private TextView search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.activity_fragment_bus, container, false);
        imageLoader = imageLoader.getInstance();
         initView();
         initAdv();
        //test();
        return mView;
    }
    private void initView(){
        viewFlow = (ViewFlow) mView.findViewById(R.id.viewFlow);
        flowIndicator = (CircleFlowIndicator) mView.findViewById(R.id.flowIndicator);
        search = (TextView)mView.findViewById(R.id.search);
        search.setOnClickListener(this);
    }
    private void initAdv(){
        mData = new ArrayList<>();
        mData.add("http://pic2.ooopic.com/01/18/43/20b1OOOPIC33.jpg");
        mData.add("http://photos.breadtrip.com/photo_2015_04_06_728086c4c693e9fb2e5fc225b06fa78e.jpg?imageView/2/w/960/q/85");
        mData.add("http://img0.imgtn.bdimg.com/it/u=3127500774,4220763422&fm=21&gp=0.jpg");
        mData.add("http://c11.eoemarket.com/app0/210/210768/icon/437326.png");
        mData.add("http://uploads.xuexila.com/allimg/1507/641-150G31I335.jpg");
        CircleImage.initCommunityImage(mData, viewFlow, flowIndicator, getActivity());
    }
    private void test(){
        viewFlow.setAdapter(new ImageAdapter(getActivity()));
        viewFlow.setmSideBuffer(3); // 实际图片张数， 我的ImageAdapter实际图片张数为3
        viewFlow.setFlowIndicator(flowIndicator);

        viewFlow.setSelection(3 * 1000); // 设置初始位置
        viewFlow.startAutoFlowTimer(); // 启动自动播放

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                Intent intent = new Intent(getActivity(), SearchListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
