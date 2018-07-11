package com.liinji.ppgdeliver.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.ReportItem;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.DateTool;
import com.liinji.ppgdeliver.utils.ReportUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */
public class TodayFragment extends BaseFragment {
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.explain_line)
    LinearLayout mExplainLine;
    @BindView(R.id.empty_tip)
    TextView mEmptyTip;
    @BindView(R.id.spin_kit)
    SpinKitView mSpinKit;
    @BindView(R.id.other_section)
    RelativeLayout mOtherSection;
    @BindView(R.id.data_section)
    RelativeLayout mDataSection;

    @Override
    protected int returnContentView() {
        return R.layout.fragment_report;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String dateFrom = DateTool.getDateStrBeforeNow(0);
        String dateTo = DateTool.getDateStrAfterNow(1);
        HttpTools.report(getActivity(), dateFrom, dateTo, this);

    }


    @Override
    public void onSuccess(int type, BaseBean info) {
        super.onSuccess(type, info);
        List<ReportItem> list = (List<ReportItem>) info.getReturnData();
        double offline = 0;
        double online = 0;
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).isOffline()) {
                offline = list.get(index).getAmount();
            } else {
                online = list.get(index).getAmount();
            }
        }
        if (offline == 0 && online == 0) {
            mExplainLine.setVisibility(View.INVISIBLE);
            mEmptyTip.setVisibility(View.VISIBLE);
            mSpinKit.setVisibility(View.INVISIBLE);
        } else {
            mEmptyTip.setVisibility(View.INVISIBLE);
            mExplainLine.setVisibility(View.VISIBLE);

        }
        ReportUtils.showReport(mWebView, offline, online);
        mWebView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mOtherSection.setVisibility(View.INVISIBLE);
                mDataSection.setVisibility(View.VISIBLE);
            }
        }, 800);
    }

}
