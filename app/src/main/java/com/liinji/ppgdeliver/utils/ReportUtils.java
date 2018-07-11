package com.liinji.ppgdeliver.utils;

import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liinji.ppgdeliver.bean.ReportData;


/**
 * Created by Administrator on 2017/3/18.
 */
public class ReportUtils {

    public static void showReport(final WebView webView, final double offline, final double online) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                ///  Logger.e("======!!!");
                ReportData reportData = new ReportData(offline,online);
                if (reportData.getTotal() != 0){
                    webView.loadUrl("javascript:refresh('" + JsonUtils.jsonStr(reportData) +"')");
                }else {

                }
            }

        });

        webView.loadUrl("file:////android_asset/chart.html");


    }
}
