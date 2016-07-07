package com.yinchuan.ycbus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncTastHttp extends AsyncTask<HashMap<String, String>, Void ,String>{

	private Handler handler;
	private String url;
	
	public AsyncTastHttp(Handler handler,String url){
		this.handler = handler;
		this.url = url;
	}

	@Override
	protected String doInBackground(HashMap<String, String>... params) {
		HashMap<String,String> map = params[0];
		String resbody = getWebInfo1(url, map);

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
	


	public String getWebInfo1 (String path,HashMap<String, String> map){
		String resbody = "";
		HttpClient client = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true) );
		//链接超时
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);  
		//读取超时
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		PostMethod postMethod = new PostMethod(path);

		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry entry = (Entry) iter.next();
			Log.v("HTTP-PARAMETER--  "+ (String)entry.getKey(),(String)entry.getValue());
		postMethod.addParameter((String) entry.getKey(), (String) entry.getValue());
		}
		
		try {
			int status = client.executeMethod(postMethod);
			//resbody = postMethod.getResponseBodyAsString();//会出现  Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.
			BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while((str = reader.readLine())!=null){
				stringBuffer.append(str);
			}
			resbody = stringBuffer.toString();
			Log.v("HTTP-URL---", url);
			Log.v("HTTP-RESPONSE---", resbody);
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("HTTP-IOException", "e:"+e);
		} finally {
			postMethod.releaseConnection(); 
		}

		return resbody;
	}


}
