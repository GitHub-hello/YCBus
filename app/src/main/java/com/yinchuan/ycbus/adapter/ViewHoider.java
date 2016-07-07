package com.yinchuan.ycbus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewHoider {

	private SparseArray<View> mView;
	private int mPosition;
	private View mConvertView;
	private Context mContext;

	public ViewHoider(Context context, ViewGroup parent, int layoutId, int position) {
		// TODO Auto-generated constructor stub
		this.mPosition = position;
		this.mContext=context;
		this.mView = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);

		mConvertView.setTag(this);
	}

	public static ViewHoider get(Context context, View convertView, ViewGroup parent, int layoutID, int position) {
		if (convertView == null) {
			return new ViewHoider(context, parent, layoutID, position);
		} else {
			ViewHoider holder = (ViewHoider) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}

	}
	
	

	/**
	 * 通过viewId获取控件
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mView.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mView.put(viewId, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}

	public ViewHoider setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}
	
	public ViewHoider setText(int viewId, Spanned spanned) {
		TextView tv = getView(viewId);
		tv.setText(spanned);
		return this;
	}

	public ViewHoider setImage(int viewId,Bitmap bitmap) {
		ImageView img = getView(viewId);
		img.setImageBitmap(bitmap);
		return this;
	}
	
	public ViewHoider setImage(int viewId,int drawableId) {
		ImageView img = getView(viewId);
		img.setBackgroundDrawable(mContext.getResources().getDrawable(drawableId));
		return this;
	}
	
	
	public ViewHoider setRatingBar(int viewId,float b) {
		RatingBar rb = getView(viewId);
		rb.setRating(b);
		return this;
	}
	public ViewHoider setRadioButton(int viewId,boolean c) {
		RadioButton rb = getView(viewId);
		rb.setChecked(c);
		return this;
	}
	
	
	public RelativeLayout setRelativeLayout(int viewId) {
		RelativeLayout rb = getView(viewId);
		return rb;
	}
	
	
	

}
