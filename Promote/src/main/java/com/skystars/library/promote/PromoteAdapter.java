package com.skystars.library.promote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 文 件 名: PromoteAdapter
 * 创 建 人: TOO15
 * 创建日期: 2016/7/1 13:32
 * 修改时间：
 * 修改备注：
 */
public class PromoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<PromoteBean> mList;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public PromoteAdapter(Context context, List<PromoteBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        PromoteBean ci = mList.get(i);
        ((PromoteViewHolder)holder).itemView.setTag(i);
        ((PromoteViewHolder)holder).vTitle.setText(ci.getTitle());
        ((PromoteViewHolder)holder).vDesc.setText(ci.getDesc());
        //((PromoteViewHolder)holder).ivIcon.set.setText(ci.getUrl());

        Glide.with(context)
                .load(ci.getIcon())
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .centerCrop()
                .into(((PromoteViewHolder)holder).ivIcon);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.promote_item, viewGroup, false);
        itemView.setOnClickListener(this);
        return new PromoteViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public static class PromoteViewHolder extends RecyclerView.ViewHolder {

        public TextView vTitle;
        public TextView vDesc;
        public ImageView ivIcon;

        public PromoteViewHolder(View v) {
            super(v);

            vTitle =  (TextView) v.findViewById(R.id.txtTitle);
            vDesc = (TextView)  v.findViewById(R.id.txtDesc);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
        }
    }
}