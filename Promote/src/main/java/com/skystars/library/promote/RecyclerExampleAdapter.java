package com.skystars.library.promote;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.skystars.library.admobadapter.RecyclerViewAdapterBase;
import com.skystars.library.admobadapter.ViewWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FILM on 01.02.2016.
 */
public class RecyclerExampleAdapter extends RecyclerViewAdapterBase<PromoteBean, RecyclerViewExampleItem> implements View.OnClickListener{

    private List<PromoteBean> items = new ArrayList<PromoteBean>();

    private Context mContext;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public RecyclerExampleAdapter(Context context){
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<RecyclerViewExampleItem> viewHolder, int position) {
        RecyclerViewExampleItem rvei = viewHolder.getView();
        PromoteBean str = getItem(position);
        rvei.bind(str, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    protected RecyclerViewExampleItem onCreateItemView(ViewGroup parent, int viewType) {
        RecyclerViewExampleItem rvei = new RecyclerViewExampleItem(mContext);

        rvei.setOnClickListener(this);
        return rvei;
    }

    @Override
    public PromoteBean getItem(int position) {
        return items.get(position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void addAll(List<PromoteBean> lst){
        items.addAll(lst);
    }
}
