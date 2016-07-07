package com.yinchuan.ycbus.activity;

import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.util.AsyTaskHttpGet;
import com.yinchuan.ycbus.util.AsyncTastHttp;
import com.yinchuan.ycbus.util.CommonUtil;
import com.yinchuan.ycbus.util.ConstantsUrl;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;



public class MainActivity extends Activity implements OnClickListener {
	private TextView search;
	private MyCountDownTimer timer;
	private static final int TAKE_PHOTO = 0x000002;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		search = (TextView)findViewById(R.id.search);
		search.setOnClickListener(this);
		/*timer = new MyCountDownTimer(30000, 1000);
		timer.start();*/
		HashMap<String, String>map = new HashMap<String, String>();
		String apikey = CommonUtil.APIS_API_KEY;
		new AsyTaskHttpGet(hanlder, ConstantsUrl.GET_NEWS_CATEGORY, apikey).execute(map);
}

	Handler hanlder = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String response = (String)msg.obj;
			Log.v("++++++++++++++++", response);
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.search:
			Intent intent = new Intent(this, SearchListActivity.class);
			startActivity(intent);

			/*Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType("image*//*");//相片类型
			startActivityForResult(intent, TAKE_PHOTO);*/
			break;
		  /*	timer.cancel();
		      timer = new MyCountDownTimer(30000, 1000);
			timer.start();
			break;*/


		default:
			break;
		}
	}
	class MyCountDownTimer extends CountDownTimer {
		/**
		 * @param millisInFuture    The number of millis in the future from the call
		 *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
		 *                          is called.
		 * @param countDownInterval The interval along the way to receive
		 *                          {@link #onTick(long)} callbacks.
		 */
		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			search.setText(millisUntilFinished / 1000+"");
		}

		@Override
		public void onFinish() {
			search.setText("done");
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			/*case TAKE_PICTURE:
				if (resultCode == RESULT_OK) {
					path = FileUtils.SDPATH + photoName;
					if (!StringUtil.isEmpty(path)) {
						ImageFactory imageFactory = new ImageFactory();
						Bitmap bm = imageFactory.getBitmap(path);
						Log.v("path---", path);
						upLoadFile(bm);
					}
				}
				break;*/
			/*case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					Uri uri = data.getData();
					String path = changeUriToPath(uri);
					if (null != path) {
						File file = new File(path);
						//构造上传请求，类似web表单
						RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM)
								.addPart(Headers.of("Content-Disposition", "form-data; name=\"callbackurl\""), RequestBody.create(null, "/idcard/"))
								.addPart(Headers.of("Content-Disposition", "form-data; name=\"action\""), RequestBody.create(null, "idcard"))
								.addPart(Headers.of("Content-Disposition", "form-data; name=\"img\"; filename="+file.getName()), RequestBody.create(MediaType.parse("image/jpeg"), file))
								.build();
						//进行包装，使其支持进度回调
						final Request request = new Request.Builder()
								.header("Host", "ocr.ccyunmai.com")
								.header("Origin", "http://ocr.ccyunmai.com")
								.header("Referer", "http://ocr.ccyunmai.com/idcard/")
								.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2398.0 Safari/537.36")
								.url("http://ocr.ccyunmai.com/UploadImg.action")
								.post(requestBody)
								.build();
						//开始请求
						mOkHttpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
							@Override
							public void onFailure(Request request, IOException e) {
								Log.e("TAG", "error");
							}

							@Override
							public void onResponse(Response response) throws IOException {
								String result = response.body().string();
								Document parse = Jsoup.parse(result);
								Elements select = parse.select("div.left fieldset");
								Log.e("TAG", select.text());
								Document parse1 = Jsoup.parse(select.text());
								StringBuilder builder = new StringBuilder();
								String name = parse1.select("name").text();
								String cardno = parse1.select("cardno").text();
								String sex = parse1.select("sex").text();
								String folk = parse1.select("folk").text();
								String birthday = parse1.select("birthday").text();
								String address = parse1.select("address").text();
								String issue_authority = parse1.select("issue_authority").text();
								String valid_period = parse1.select("valid_period").text();
								builder.append("name:" + name)
										.append("\n")
										.append("cardno:" + cardno)
										.append("\n")
										.append("sex:" + sex)
										.append("\n")
										.append("folk:" + folk)
										.append("\n")
										.append("birthday:" + birthday)
										.append("\n")
										.append("address:" + address)
										.append("\n")
										.append("issue_authority:" + issue_authority)
										.append("\n")
										.append("valid_period:" + valid_period)
										.append("\n");
								Log.e("TAG", "name:" + name);
								Log.e("TAG", "cardno:" + cardno);
								Log.e("TAG", "sex:" + sex);
								Log.e("TAG", "folk:" + folk);
								Log.e("TAG", "birthday:" + birthday);
								Log.e("TAG", "address:" + address);
								Log.e("TAG", "issue_authority:" + issue_authority);
								Log.e("TAG", "valid_period:" + valid_period);
								*//*Message obtain = Message.obtain();
								obtain.what = UPDATE_TEXTVIEW;
								obtain.obj = builder.toString();
								mHandler.sendMessage(obtain);*//*
							}
						});
					}
				}
				break;*/
		}
	}

	// 将URI转换为真实路径
	private String changeUriToPath(Uri uri) {
		String currentImagePath = "";
		String[] proj = {MediaStore.Images.Media.DATA};
		Cursor actualImageCursor = managedQuery(uri, proj, null, null, null);
		int actual_image_column_index = actualImageCursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		actualImageCursor.moveToFirst();
		currentImagePath = actualImageCursor
				.getString(actual_image_column_index);
		return currentImagePath;
	}
}
