package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.fragment.MonthFragment;
import com.liinji.ppgdeliver.fragment.TodayFragment;
import com.liinji.ppgdeliver.fragment.WeekFragment;
import com.liinji.ppgdeliver.tools.DateTool;
import com.liinji.ppgdeliver.widget.ViewPagerIndicator;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */
public class ReportActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.indicator)
    ViewPagerIndicator mIndicator;
    @BindView(R.id.duration)
    TextView mDuration;

    @Override
    protected int returnContentView() {
        return R.layout.activity_report;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("报表");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mDuration.setText(DateTool.getDateStrBeforeNow(0));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new TodayFragment();
                    case 1:
                        return new WeekFragment();
                    case 2:
                        return new MonthFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.selectItem(position);
                mDuration.setText(DateTool.getDate(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIndicator.init(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
    }

}
