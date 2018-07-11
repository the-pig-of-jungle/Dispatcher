package com.liinji.ppgdeliver.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.http.WebUrl;

/**
 * Created by 朱志强 on 2017/10/19.
 */

public class BranchRechargeActivity extends BaseDarkStatusBarActivity {
    private WebView mWebView;

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("网点充值");
    }

    @Override
    protected int returnContentView() {
        return R.layout.activity_branch_recharge;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mWebView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
            }

        });

        mWebView.loadUrl(WebUrl.BRANCH_RECHARGE);
    }
}
