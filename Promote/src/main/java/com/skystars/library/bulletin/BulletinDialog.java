package com.skystars.library.bulletin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skystars.library.promote.R;

/**
 * 文 件 名: BDialog
 * 创 建 人: TOO15
 * 创建日期: 2016/7/6 14:31
 * 修改时间：
 * 修改备注：
 */
public class BulletinDialog extends BaseDialogFragment{

    private String mMsg;

    public static BulletinDialog newInstance(Context context, String msg, int layout) {
        Bundle args = new Bundle();
        args.putInt(TAG_ARG, layout);
        BulletinDialog fragment = new BulletinDialog();
        fragment.setArguments(args);
        fragment.mMsg = msg;
        fragment.mLayout = layout;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mLayout = arguments.getInt(TAG_ARG, 0);
        }
    }

    @Override
    protected int getOkTextColorRes() {
        return  BaseDialogFragment.DEFAULT_COLOR;
    }

    @Override
    protected int getOkBgColorRes() {
        return BaseDialogFragment.DEFAULT_COLOR;
    }

    @Override
    protected int getCancelTextColorRes() {
        return BaseDialogFragment.DEFAULT_COLOR;
    }

    @Override
    protected int getCancelBgColorRes() {
        return BaseDialogFragment.DEFAULT_COLOR;
    }

    @Override
    protected String getOkText() {
        return getString(R.string.bulletin_ok);
    }

    @Override
    protected String getCancelText() {
        return getString(R.string.bulletin_cancel);
    }

    @Override
    protected String getContentText() {
        return mMsg;
    }
}