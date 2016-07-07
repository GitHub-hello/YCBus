package com.yinchuan.ycbus.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.fragment.TabBusFragment;
import com.yinchuan.ycbus.fragment.TabNewsFragment;
import com.yinchuan.ycbus.fragment.TabPicFragment;

import java.util.ArrayList;

public class HomeActivity extends FragmentActivity {
	private FrameLayout content;
	private ArrayList<View> views;
	private RadioGroup group;
	private FragmentTransaction transaction;
	private Fragment mContent;
	private RadioButton main_bus, main_news, main_picture;
	private TabBusFragment busFragment;
	private TabNewsFragment newsFragment;
	private TabPicFragment picFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
		initCurrentTab();
}
	private void initView(){
		group = (RadioGroup)findViewById(R.id.group);
		content = (FrameLayout)findViewById(R.id.content);
		main_bus = (RadioButton)findViewById(R.id.main_bus);
		main_news = (RadioButton)findViewById(R.id.main_news);
		main_picture = (RadioButton)findViewById(R.id.main_picture);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.main_bus:
						main_bus.setChecked(true);
						if (busFragment == null) {
							busFragment = new TabBusFragment();
						}
						switchContent(busFragment);
						break;
					case R.id.main_news:
						main_news.setChecked(true);
						if (newsFragment == null) {
							newsFragment = new TabNewsFragment();
						}
						switchContent(newsFragment);
						break;
					case R.id.main_picture:
						main_picture.setChecked(true);
						if (picFragment == null) {
							picFragment = new TabPicFragment();
						}
						switchContent(picFragment);
						break;
				}
			}
		});
	}
	private void switchContent(Fragment fragment) {
		if (mContent != null && mContent != fragment) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			if (!fragment.isAdded()) {
				transaction.hide(mContent).add(R.id.content, fragment).commitAllowingStateLoss();
			} else {
				transaction.hide(mContent).show(fragment).commitAllowingStateLoss();
			}
			mContent = fragment;
		}
	}
	private void initCurrentTab() {
		transaction = getSupportFragmentManager().beginTransaction();
		int index = getIntent().getIntExtra("index", R.id.main_bus);
		Log.v("index", index + "");
		switch (index) {
			case R.id.main_bus:
				main_bus.setChecked(true);
				busFragment = new TabBusFragment();
				mContent = busFragment;
				transaction.add(R.id.content, mContent).commit();
				break;
			case R.id.main_news:
				main_news.setChecked(true);
				newsFragment = new TabNewsFragment();
				mContent = newsFragment;
				transaction.add(R.id.content, mContent).commit();
				break;
			case R.id.main_picture:
				main_picture.setChecked(true);
				picFragment = new TabPicFragment();
				mContent = picFragment;
				transaction.add(R.id.content, mContent).commit();
				break;
		}
		group.check(index);
	}
}
