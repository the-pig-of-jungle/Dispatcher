package com.liinji.ppgdeliver.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.http.HttpNetUtils;
import com.liinji.ppgdeliver.http.HttpRequestListener;

import butterknife.ButterKnife;


/**
 * Created by YUEYINGSK on 2016/8/15.
 */
public abstract class BaseFragment extends Fragment implements HttpRequestListener {

    protected View mRootView;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (returnContentView() != 0) {
            mRootView = inflater.inflate(returnContentView(), null);
        }

        ButterKnife.bind(this, mRootView);
        init();
        initData(savedInstanceState);
        initView(savedInstanceState);
        return mRootView;
    }

    private void init() {
        mContext = getActivity();
    }

    /**
     * 返回layout res id 相当于调用setCotentView
     *
     * @return
     */
    protected abstract int returnContentView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    protected void myToast(String string) {
        SmartSnackbar.get(getActivity()).show(string);
    }



    @Override
    public void onSuccess(int type, BaseBean info) {

    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpNetUtils.cancelNetWork(mContext);
    }
}
