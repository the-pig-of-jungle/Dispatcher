package com.liinji.ppgdeliver.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.fragment.JijieQiankuanFragment;
import com.liinji.ppgdeliver.fragment.QiankuanFragment;
import com.liinji.ppgdeliver.widget.QiankuanIndicaator;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/4/19.
 */
public class PaybackActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.indicator)
    QiankuanIndicaator mIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    @Override
    protected int returnContentView() {
        return R.layout.activity_pay_back;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mIndicator.setViewPager(mViewPager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new QiankuanFragment();
                    case 1:
                        return new JijieQiankuanFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


}
