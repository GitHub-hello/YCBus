package com.yinchuan.ycbus.adapter;

import android.support.v4.util.CircularArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.MyApplication;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.BeautyPicture;

import java.util.ArrayList;

/**
 * Created by zhouwei on 2016/7/31.
 */
public class RecycleLoadMoreAdapter extends BaseLoadingAdapter<BeautyPicture> {
    private CircularArray<BeautyPicture> mDesignItems;
    private ArrayList<Integer> heights;

    public RecycleLoadMoreAdapter(RecyclerView recyclerView, CircularArray ts) {
        super(recyclerView, ts);
        mDesignItems = ts;
        heights = new ArrayList<>();
    }

    //正常条目
    public class DesignViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public DesignViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_img);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_image, parent, false);
        return new DesignViewHolder(view);

    }

    @Override
    public void onBindNormalViewHolder(RecyclerView.ViewHolder holders, int position) {
        DesignViewHolder holder = (DesignViewHolder) holders;
        for (int i = 0; i < mDesignItems.size(); i++) {
            heights.add((int) (600 + Math.random() * 200));
        }
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = heights.get(position);
        holder.itemView.setLayoutParams(params);
        holder.title.setText(mDesignItems.get(position).getGroup_title());
        ImageLoader.getInstance().displayImage(mDesignItems.get(position).getQhimg_url(), holder.img, MyApplication.setOptions());
        Log.v("TAG-------", mDesignItems.get(position).getTag());
       /* if(mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mListener.ItemClickListener(holder.itemView, pos);
                }
            });
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                mListener.ItemLongClickListener(holder.itemView, pos);
                return true;
            }
        });*/
    }
}
