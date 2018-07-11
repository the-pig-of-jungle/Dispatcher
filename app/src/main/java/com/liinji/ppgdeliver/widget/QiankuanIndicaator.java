package com.liinji.ppgdeliver.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.liinji.ppgdeliver.R;

/**
 * Created by 朱志强 on 2017/6/29.
 */

public class QiankuanIndicaator extends LinearLayout {
    private RadioButton mQiankuan;
    private RadioButton mJijieQiankuan;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    public QiankuanIndicaator(Context context) {
        this(context,null);
    }

    public QiankuanIndicaator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QiankuanIndicaator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.qiankuan_indicator,this,true);
        mQiankuan = (RadioButton) findViewById(R.id.qiankuan);
        mJijieQiankuan = (RadioButton) findViewById(R.id.jijie_qiankuan);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setTextColor(Color.parseColor("#0dc25f"));
                }else {
                    buttonView.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        };

        mQiankuan.setOnCheckedChangeListener(listener);
        mJijieQiankuan.setOnCheckedChangeListener(listener);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.qiankuan:
                        if (mViewPager != null){
                            mViewPager.setCurrentItem(0);
                        }
                        break;
                    case R.id.jijie_qiankuan:
                        if (mViewPager != null){
                            mViewPager.setCurrentItem(1);
                        }
                        break;
                }
            }
        });
    }

    public void setViewPager(ViewPager viewPager){
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRadioGroup.check(R.id.qiankuan);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.jijie_qiankuan);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }





}
