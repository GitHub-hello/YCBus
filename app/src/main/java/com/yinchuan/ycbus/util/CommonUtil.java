package com.yinchuan.ycbus.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/30.
 */
public class CommonUtil {
    //http://apis.baidu.com的apiKey
    public static final String APIS_API_KEY = "e2d6e24d460cff9d18136961266bd7b5";
    public static void showMessage(Context ctx, String msg){
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }
}
