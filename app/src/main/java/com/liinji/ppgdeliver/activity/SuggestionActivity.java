package com.liinji.ppgdeliver.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.http.HttpTools;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/22.
 */
public class SuggestionActivity extends BaseDarkStatusBarActivity {
    @BindView(R.id.submit_suggestion)
    Button mSubmitSuggestion;
    @BindView(R.id.suggest_message)
    EditText mSuggestMessage;

    @Override
    protected int returnContentView() {
        return R.layout.activity_suggestion;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("意见反馈");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.submit_suggestion)
    public void onClick() {
        HttpTools.suggestion(this, mSubmitSuggestion.getText().toString().trim(),this);
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        SmartToast.showInCenter("已提交");
        finish();
    }
}
