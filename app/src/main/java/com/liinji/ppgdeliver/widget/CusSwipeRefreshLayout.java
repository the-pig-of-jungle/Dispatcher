package com.liinji.ppgdeliver.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.ViewConfiguration;

import com.liinji.ppgdeliver.R;


/**
 * Created by YUEYINGSK on 2016/6/23.
 */
public class CusSwipeRefreshLayout extends SwipeRefreshLayout {
    private int mTouchSlop;
    private float mPrevX;

    public CusSwipeRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//        setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener):设置手势滑动监听器。
//        setProgressBackgroundColor( int colorRes):设置进度圈的背景色。
//
//        setColorSchemeResources(int…colorResIds):设置进度动画的颜色。
//
//        setRefreshing(Boolean refreshing):设置组件的刷洗状态。
//
//        setSize( int size):设置进度圈的大小，只有两个值：DEFAULT、LARGE
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public CusSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }
    //只保持纵向，解决横向滑动问题
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mPrevX = MotionEvent.obtain(event).getX();
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                final float eventX = event.getX();
//                float xDiff = Math.abs(eventX - mPrevX);
//
//                if (xDiff > mTouchSlop) {
//                    return false;
//                }
//        }
//
//        return super.onInterceptTouchEvent(event);
//    }
}
