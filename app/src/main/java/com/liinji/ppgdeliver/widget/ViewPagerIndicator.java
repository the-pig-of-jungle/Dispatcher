package com.liinji.ppgdeliver.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.liinji.ppgdeliver.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */
public class ViewPagerIndicator extends LinearLayout {
    @BindView(R.id.item_01)
    RadioButton mItem01;
    @BindView(R.id.item_02)
    RadioButton mItem02;
    @BindView(R.id.item_03)
    RadioButton mItem03;
    @BindView(R.id.item_group)
    RadioGroup mItemGroup;

    private ViewPager mViewPager;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_page_indicator, this);

    }

    public void init(ViewPager viewPager){
        mViewPager = viewPager;
        mItemGroup = (RadioGroup) findViewById(R.id.item_group);
        mItemGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mViewPager.setCurrentItem(id2index(checkedId));
            }
        });
    }
    private int id2index(int checkedId) {

        switch (checkedId){
            case R.id.item_01:
                return 0;
            case R.id.item_02:
                return 1;
            case R.id.item_03:
                return 2;
        }

        return 0;
    }

    public void selectItem(int index){
        ((RadioButton) mItemGroup.getChildAt(index)).setChecked(true);
    }

    public static interface OnItemChangedListener{
        public void onItemChanged(int lastIndex, int newIndex);
    }
}
