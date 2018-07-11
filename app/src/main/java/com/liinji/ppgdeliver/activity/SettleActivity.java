package com.liinji.ppgdeliver.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.tools.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/16.
 */
public class SettleActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.apply)
    TextView mApply;
    @BindView(R.id.report)
    TextView mReport;

    @Override
    protected int returnContentView() {
        return R.layout.activity_settle;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("配送结算");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick({R.id.apply, R.id.report})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply:
                IntentUtils.startActivity(this,ApplyActivity.class);
                break;
            case R.id.report:
                IntentUtils.startActivity(this,ReportActivity.class);
                break;
        }
    }
}
