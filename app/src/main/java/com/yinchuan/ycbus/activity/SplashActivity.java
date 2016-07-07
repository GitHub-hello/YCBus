package com.yinchuan.ycbus.activity;

import com.yinchuan.ycbus.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class SplashActivity extends Activity {
private TextView welcome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		welcome = (TextView)findViewById(R.id.welcome);
		Animation alAni = new AlphaAnimation(0.0f, 1.0f);
		alAni.setDuration(2000);
		welcome.setAnimation(alAni);
		nextStep();
	}
	private void nextStep(){
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
				startActivity(intent);
			}
		}, 3000);
	}

	
}
