package com.yinchuan.ycbus.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.adapter.MyFragmentStatePagerAdapter;
import com.yinchuan.ycbus.entity.BeautyCategory;
import com.yinchuan.ycbus.util.AsyTaskHttpGet;
import com.yinchuan.ycbus.util.CommonUtil;
import com.yinchuan.ycbus.util.ConstantsUrl;
import com.yinchuan.ycbus.view.ColumnHorizontalScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/30.
 */
public class TabPicFragment extends Fragment {
    private View mView;
    /** 自定义HorizontalScrollView */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    /** 新闻分类列表*/
    private ArrayList<BeautyCategory> categorys=new ArrayList<>();
    /** 当前选中的栏目*/
    private int columnSelectIndex = 0;
    /** 左阴影部分*/
    public ImageView shade_left;
    /** 右阴影部分 */
    public ImageView shade_right;
    /** 屏幕宽度 */
    private int mScreenWidth = 0;
    /** Item宽度 */
    private int mItemWidth = 0;

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_fragment_picture, container, false);
         mScreenWidth = getWindowsWidth(getActivity());
        mItemWidth = (mScreenWidth-40) / 5;
        initView();
        getCategory();
        return mView;
    }
    private void getCategory(){
        HashMap<String, String> map = new HashMap<>();
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
                initData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private void initView(){
        categorys.add(new BeautyCategory("", "全部", ""));
        mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)mView.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) mView.findViewById(R.id.mRadioGroup_content);
        rl_column = (RelativeLayout) mView.findViewById(R.id.rl_column);
        mViewPager = (ViewPager) mView.findViewById(R.id.mViewPager);
        shade_left = (ImageView) mView.findViewById(R.id.shade_left);
        shade_right = (ImageView) mView.findViewById(R.id.shade_right);
    }


    private void initData(){
        initColumnData();
        initTabColumn();
       initFragment();
    }

    private void initColumnData(){

    }
    private void initFragment(){
        for(int i = 0; i < categorys.size(); i++){
            Bundle bundle = new Bundle();
            Log.v("|||t1===", categorys.get(i).getT());
            bundle.putString("t1", categorys.get(i).getT());
            bundle.putString("url", categorys.get(i).getUrl());
            PictureFragment fragment = new PictureFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        MyFragmentStatePagerAdapter mAdapetr = new MyFragmentStatePagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(pageListener);
    }
    private void initTabColumn(){
        mRadioGroup_content.removeAllViews();
        int count = categorys.size();
        mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for(int i = 0; i < count; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i > 0){
                params.leftMargin = 30;
            }
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setLayoutParams(params);
            //ll.setBackgroundResource(R.drawable.top_category_scroll_text_view_bg);
             TextView localTextView = new TextView(getActivity());
            localTextView.setTextAppearance(getActivity(), R.style.top_category_scroll_view_item_text);
           // localTextView.setBackgroundResource(R.drawable.top_category_scroll_text_view_bg);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setPadding(10, 10, 10, 10);
            localTextView.setMaxLines(1);
            localTextView.setId(i);
            localTextView.setText(categorys.get(i).getTitle());
            localTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                }
            });
         /*   localTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CommonUtil.showMessage(getActivity(), "hahahhaha");
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                           mViewPager.setCurrentItem(i);
                        }
                    }
                    //Toast.makeText(getActivity(), newsClassify.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });*/
            ll.addView(localTextView);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5);
            if(categorys.get(i).getTitle().length() > 2){
                lp.setMargins(20, 15, 20, 10);
            }else{
                lp.setMargins(35, 15, 35, 10);
            }

            lp.gravity = Gravity.CENTER;
            ImageView line = new ImageView(getActivity());
            line.setLayoutParams(lp);
            line.setBackgroundResource(R.drawable.top_category_scroll_text_view_bg);
            ll.addView(line);
            mRadioGroup_content.addView(ll);
        }
            mRadioGroup_content.getChildAt(0).setSelected(true);

    }

    /**
     *  ViewPager切换监听方法
     * */
    public ViewPager.OnPageChangeListener pageListener= new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };
    /**
     *  选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
            // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
            // mItemWidth , 0);
        }
        //判断是否选中
        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }
    //获取屏幕宽度
    public  int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
