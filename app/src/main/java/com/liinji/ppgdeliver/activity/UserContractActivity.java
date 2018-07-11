package com.liinji.ppgdeliver.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.http.HttpUrl;

import butterknife.BindView;

public class UserContractActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    protected int returnContentView() {
        return R.layout.activity_user_contract;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("服务协议");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(HttpUrl.USER_CONTRACT);
    }


}
