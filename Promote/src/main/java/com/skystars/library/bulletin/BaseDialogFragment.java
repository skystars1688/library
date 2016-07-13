package com.skystars.library.bulletin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.skystars.library.promote.R;

/**
 * 文 件 名: BaseDialogFragment
 * 创 建 人: TOO15
 * 创建日期: 2016/7/6 17:07
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment {
    protected int mLayout;
    protected static final String TAG_ARG = "layout";
    protected static final int DEFAULT_COLOR = -1; //default

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Step1 build Dialog
        if (mLayout == 0) {
            throw new RuntimeException("no correct layout found.");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(mLayout, null);
        TextView mContent = (TextView)view.findViewById(R.id.txtContent);
        mContent.setText(Html.fromHtml(getContentText()));

        builder.setView(view).setPositiveButton(getOkText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mOnBtnClickListener != null)
                    mOnBtnClickListener.onOkClick();
            }
        }).setNegativeButton(getCancelText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mOnBtnClickListener != null)
                    mOnBtnClickListener.onCancelClick();
            }
        }).setCancelable(false);
        AlertDialog alertDialog = builder.create();

        //Step2 custom Button
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                //只有在Show之后才能getButton!
                Button okButton = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
                Button cancelButton = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_NEGATIVE);
                if (getOkTextColorRes() != DEFAULT_COLOR) {
                    okButton.setTextColor(getResources().getColor(getOkTextColorRes()));
                }
                if (getCancelTextColorRes() != DEFAULT_COLOR) {
                    cancelButton.setTextColor(getResources().getColor(getCancelTextColorRes()));
                }
                if (getOkBgColorRes() != DEFAULT_COLOR) {
                    okButton.setBackgroundResource(getOkBgColorRes());
                }
                if (getCancelBgColorRes() != DEFAULT_COLOR) {
                    cancelButton.setBackgroundResource(getCancelBgColorRes());
                }
            }
        });

        //Step3 request feature
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface OnBtnClickListener {
        public void onOkClick();

        public void onCancelClick();
    }

    private OnBtnClickListener mOnBtnClickListener;

    /**
     * 添加Button的Click监听
     *
     * @param onBtnClickListener
     */
    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        mOnBtnClickListener = onBtnClickListener;
    }

    /**
     * 用于设定Positive Button的Text颜色
     * @return Color的Resource Id
     */
    protected abstract int getOkTextColorRes();// text 只能是color

    /**
     * 用于设定Positive Button的背景颜色
     * @return Color或者Drawable的Resource Id
     */
    protected abstract int getOkBgColorRes();

    /**
     * 用于设定Negative Button的Text颜色
     * @return Color的Resource Id
     */
    protected abstract int getCancelTextColorRes();

    /**
     * 用于设定Negative Button的背景颜色
     * @return Color或者Drawable的Resource Id
     */
    protected abstract int getCancelBgColorRes();

    protected abstract String getOkText();

    protected abstract String getCancelText();

    protected abstract String getContentText();
}