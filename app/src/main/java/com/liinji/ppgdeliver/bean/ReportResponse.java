package com.liinji.ppgdeliver.bean;

import android.view.View;

/**
 * Created by Administrator on 2017/4/7.
 */
public class ReportResponse {
    private View mView;

    public ReportResponse(View view) {
        mView = view;
    }

    public void finish(){
        mView.setVisibility(View.INVISIBLE);
    }
}
