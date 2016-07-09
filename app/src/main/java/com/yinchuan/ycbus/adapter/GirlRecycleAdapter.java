package com.yinchuan.ycbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yinchuan.ycbus.MyApplication;
import com.yinchuan.ycbus.R;
import com.yinchuan.ycbus.entity.Girl;

import java.util.ArrayList;

/**
 * Created by zhouwei on 2016/7/7.
 */
public class GirlRecycleAdapter extends RecyclerView.Adapter<GirlViewHolder> {

    private ArrayList<Girl> lists;
    private Context ctx;
    private ArrayList<Integer> heights;

    private OnItemClickListener mListener;
    public GirlRecycleAdapter(Context ctx, ArrayList<Girl> lists) {
        this.ctx = ctx;
        this.lists = lists;
        getRandomHeight(this.lists);
    }

   public void getRandomHeight(ArrayList<Girl> list){
       heights = new ArrayList<>();
       for(int i = 0; i < list.size(); i++){
           heights.add((int)(600+Math.random()*200));
       }
   }

    public interface OnItemClickListener{

        void ItemClickListener(View view,int postion);

        void ItemLongClickListener(View view,int postion);

    }
    public void setOnClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_image, parent, false);
        GirlViewHolder viewHolder = new GirlViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, int position) {

        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = heights.get(position);
        holder.itemView.setLayoutParams(params);
        holder.title.setText(lists.get(position).getTitle());
        ImageLoader.getInstance().displayImage(lists.get(position).getPic_url(), holder.img, MyApplication.setOptions());
        Log.v("TAG-------", lists.get(position).getTags().toString());
       if(mListener != null){
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
       });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}

class GirlViewHolder extends RecyclerView.ViewHolder{

   ImageView img;
    TextView title;
    public GirlViewHolder(View itemView) {
        super(itemView);
        img = (ImageView)itemView.findViewById(R.id.item_img);
        title = (TextView)itemView.findViewById(R.id.item_title);
    }
}
