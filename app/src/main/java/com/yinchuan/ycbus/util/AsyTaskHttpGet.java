package com.yinchuan.ycbus.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Administrator on 2016/6/29.
 */
public class AsyTaskHttpGet extends AsyncTask<HashMap<String, String>, Void ,String> {

    private Handler handler;
    private String url;
    private String apiKey;
    public AsyTaskHttpGet(Handler handler,String url, String apiKey){
        this.handler = handler;
        this.url = url;
        this.apiKey = apiKey;
    }

    @Override
    protected String doInBackground(HashMap<String, String>... params) {
        HashMap<String,String> map = params[0];
        String resbody = getResponse(url, apiKey, map);
        return resbody;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//		  ShareEntity entity = JSON.parseObject(result, ShareEntity.class);

        if(null != result && !"".equals(result)){
            Message msg = new Message();
            msg.obj = result;
            handler.sendMessage(msg);
        }
    }
    public String getResponse(String url, String apiKey, HashMap<String,String> map) {
        String result = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url).append("?");
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()){
                Entry entry = (Entry)it.next();
                Log.v("HTTP-PARAMETER--  " + (String) entry.getKey(), (String) entry.getValue());
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode((String)entry.getValue(), "utf-8"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.toString().length()-1);
            HttpClient httpclient = new DefaultHttpClient();
            String uri = sb.toString();
            HttpGet get = new HttpGet(uri);
            //添加http头信息
            if(apiKey != null){
                get.addHeader("apiKey", apiKey);
            }

            HttpResponse response;
            response = httpclient.execute(get);
            int code = response.getStatusLine().getStatusCode();
            //检验状态码，如果成功接收数据
            if (code == 200) {
                //返回json格式： {"id": "27JpL~j4vsL0LX00E00005","version": "abc"}
                String rev = EntityUtils.toString(response.getEntity());
                result = rev;
            }
        } catch (Exception e) {
        }finally {
            return result;
        }
    }
}
