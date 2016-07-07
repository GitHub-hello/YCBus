package com.yinchuan.ycbus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinchuan.ycbus.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class TabPicFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_bus, container, false);

        return view;
    }
}
