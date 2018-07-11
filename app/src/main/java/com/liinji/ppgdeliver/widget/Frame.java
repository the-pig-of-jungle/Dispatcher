package com.liinji.ppgdeliver.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;

/**
 * Created by 朱志强 on 2017/6/8.
 */

public class Frame extends FrameLayout {
    private View mDiappearMark;
    private TextView mContent;
    private int mImg;
    private OnCloseClickListener mOnCloseClickListener;

    public Frame(@NonNull Context context) {
        super(context);
        init(context);
    }

    public Frame(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Frame(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.frame,this,true);
        mDiappearMark = findViewById(R.id.disappear);
        mDiappearMark.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnCloseClickListener != null){
                    mOnCloseClickListener.onCloseClick(Frame.this);
                }
            }
        });
        mContent = (TextView) findViewById(R.id.content);
    }

    public void setContent(String content){
        mContent.setText(content);
    }



    public void setOnCloseClickListener(OnCloseClickListener listener){
        mOnCloseClickListener = listener;
    }



    public interface OnCloseClickListener{
        void onCloseClick(Frame frame);
    }


}
