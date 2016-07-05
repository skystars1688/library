package com.skystars.library.promote;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecyclerViewExampleItem extends FrameLayout {
    Context context;
    TextView vTitle;
    TextView vDesc;
    ImageView ivIcon;

    public RecyclerViewExampleItem(Context context) {
        super(context);
        inflate(context, R.layout.promote_item, this);
        this.context = context;
        vTitle = ((TextView) findViewById(R.id.txtTitle));
        vDesc = ((TextView) findViewById(R.id.txtDesc));
        ivIcon = (ImageView) findViewById(R.id.ivIcon);
    }

    public void bind(PromoteBean ci, int position){
        setTag(position);
        vTitle.setText(ci.getTitle());
        vDesc.setText(ci.getDesc());

        Glide.with(context)
                .load(ci.getIcon())
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .centerCrop()
                .into(ivIcon);
    }
}
