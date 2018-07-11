package com.liinji.ppgdeliver.http;


import com.liinji.ppgdeliver.bean.BaseBean;

public interface HttpRequestListener {
    public void onFailed(int type, Exception e, BaseBean bean);


    public void onSuccess(int type, BaseBean bean);
}
