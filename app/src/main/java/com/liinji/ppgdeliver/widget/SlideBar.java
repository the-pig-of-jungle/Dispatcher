package com.liinji.ppgdeliver.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.utils.Utils;


/**
 * Created by Administrator on 2017/5/27.
 */
public class SlideBar extends View {

    private static final String[] WORDS = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z",
    };

    // 垂直居中
    private int heightCenter;
    private Paint mPaint;
    private int mCellWidth;
    private int mCellHigh;

    private int chooseItem = -1;

    public SlideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(Utils.dp2px(getContext(), 11));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCellWidth = getMeasuredWidth();
        mCellHigh = getMeasuredHeight() / WORDS.length;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int index = (int) event.getY() / mCellHigh;
              //  Logger.e("字母位置：" + index);
                if (index >= 0 && index < WORDS.length) {
                    if (index != chooseItem) {
                        chooseItem = index;
                        if (onIndexChooseListener != null) {
                            onIndexChooseListener.onIndexChoose(WORDS[chooseItem]);
                        }
                    }
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int index1 = (int) event.getY() / mCellHigh;
             //   Logger.e("字母位置：" + index1);
                if (index1 >= 0 && index1 < WORDS.length) {
                    if (index1 != chooseItem) {
                        chooseItem = index1;
                        if (onIndexChooseListener != null) {
                            onIndexChooseListener.onIndexChoose(WORDS[chooseItem]);
                        }
                    }
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                chooseItem = -1;
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
      //  Logger.e("执行了~~~~~");
        for (int i = 0; i < WORDS.length; i++) {
            mPaint.setColor(getResources().getColor(R.color.grey_999999));
            String word = WORDS[i];
            Rect bound = new Rect();
            mPaint.getTextBounds(word, 0, word.length(), bound);
            if (i == chooseItem) {
                mPaint.setColor(Color.parseColor("#0dc25f"));
                mPaint.setFakeBoldText(true);
            }
            float x = mCellWidth / 2 + 0.5f;
            float y = (i * mCellHigh + (mCellHigh + bound.height()) / 2) + 0.5f;
            canvas.drawText(word, x, y, mPaint);
        }
        super.onDraw(canvas);
    }

    //设置监听
    public interface OnIndexChooseListener {
        void onIndexChoose(String word);
    }

    public void setOnIndexChooseListener(OnIndexChooseListener onIndexChooseListener) {
        this.onIndexChooseListener = onIndexChooseListener;
    }

    private OnIndexChooseListener onIndexChooseListener;
}
