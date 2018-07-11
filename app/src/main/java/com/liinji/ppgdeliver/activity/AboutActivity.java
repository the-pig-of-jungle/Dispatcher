package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.utils.Utils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/22.
 */
public class AboutActivity extends BaseDarkStatusBarActivity {
    @BindView(R.id.version)
    TextView mVersion;

    @Override
    protected int returnContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();

        toolbar_title.setText("关于我们");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mVersion.setText(Utils.appVersionName());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

}
