package com.liinji.ppgdeliver.http;

import java.util.HashMap;
import java.util.Map;

/**
 * 所传参数集合
 * Created by YUEYINGSK on 2016/10/17.
 */
public class RequestParams {

    private Map<String, String> params;

    public RequestParams() {
        params = new HashMap<>();
    }

    public void put(String key, String value) {
        params.put(key, value);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> map) {
        params = map;
    }

    public void removeParams(String key) {
        params.remove(key);
    }

    @Override
    public String toString() {
        return params.toString();
    }
}
