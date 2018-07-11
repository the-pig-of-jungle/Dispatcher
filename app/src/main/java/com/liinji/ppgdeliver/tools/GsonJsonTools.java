package com.liinji.ppgdeliver.tools;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by YUEYINGSK on 2016/6/21.
 */
public class GsonJsonTools {
    /**
     * 对象转为json
     *
     * @param obj
     * @return
     */
    public static String object2Json(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * 解析json数据为对象
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T json2Obj(String json, Class<T> tClass) {
        return new Gson().fromJson(json, tClass);
    }

    /**
     * 解析json数据为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T json2Obj(String json, Type type) {
        return new Gson().fromJson(json, type);
    }


}
