package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/23.
 */
public class BillSubmitResultActivity extends BaseDarkStatusBarActivity {
    @BindView(R.id.amount)
    TextView mAmount;
    @BindView(R.id.complete_btn)
    Button mCompleteBtn;

    @Override
    protected int returnContentView() {
        return R.layout.activity_bill_submit_result;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("申请交账");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAmount.setText(getIntent().getStringExtra("amount"));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick(R.id.complete_btn)
    public void onClick() {
        finish();
    }
}
