package com.liinji.ppgdeliver.http;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.view.View;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.google.gson.reflect.TypeToken;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.tools.CustomDialogTool;
import com.liinji.ppgdeliver.tools.GsonJsonTools;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.tools.StringTools;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by YUEYINGSK on 2016/8/16.
 */
public class HttpNetUtils {
    /**
     * get请求
     *
     * @param url
     * @param params
     * @param listener
     */
    public static <T> void getNetData(Context context, String url, RequestParams params, BaseRequestListener<T> listener) {
        if (!initNet(context)) {
            return;
        }
        OkHttpUtils.get().url(url).params(params.getParams()).tag(context).build().execute(new BaseCallback<T>(listener, context) {
        });
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param listener
     */
    public static <T> void postNetData(Context context, String url, RequestParams params, BaseRequestListener<T> listener) {

        if (!initNet(context)) {
            return;
        }
        OkHttpUtils.post().url(url).params(params.getParams()).tag(context).build().execute(new BaseCallback<T>(listener, context) {
        });
    }

    /**
     * post请求传递json
     *
     * @param url
     * @param json
     * @param listener
     */
    public static <T> void postJsonStringNetData(Context context, String url, String json, BaseRequestListener<T> listener) {
        if (!initNet(context)) {
            return;
        }
        Logger.json(json);
        OkHttpUtils.postString().url(url).content(json).tag(context).mediaType(MediaType.parse("application/json; charset=utf-8")).build().execute(new BaseCallback<T>(listener, context) {
        });
    }

    /**
     * 上传文件
     *
     * @param url
     * @param file
     * @param listener
     */
    public static <T> void postFile(Context context, String url, File file, BaseRequestListener<T> listener) {
        if (!initNet(context)) {
            return;
        }
        OkHttpUtils.postFile().url(url).file(file).tag(context).build().execute(new BaseCallback<T>(listener, context) {
        });
    }

    /**
     * 下载文件
     *
     * @param context
     * @param url
     * @param fileCallBack
     */
    public static void downloadFile(Context context, String url, FileCallBack fileCallBack) {
        if (!initNet(context)) {
            return;
        }
        OkHttpUtils.get().url(url).tag(context).build().execute(fileCallBack);
    }

    public static abstract class BaseCallback<T> extends Callback<T> {
        private static BaseRequestListener mListener;
        private Context mContext;
        /**
         * 中间的方法是在另外线程中进行，不储存listener而使用mListener在多网络同时访问时候数据会错乱
         */
        private BaseRequestListener nowListener;


        public BaseCallback(BaseRequestListener listener, Context context) {
            mListener = listener;
            mContext = context;
        }

        @Override
        public void onBefore(Request request, int id) {
            if (!MyApplication.sCancelNetTip){
                CustomDialogTool.showProgressDialog(mContext);
            }
            MyApplication.shortsaveDataMap.put(request.url().toString(), mListener);
        }

        @Override
        public T parseNetworkResponse(Response response, int id) throws Exception {
            String string = response.body().string();
            Logger.json(string);
            nowListener = (BaseRequestListener) MyApplication.shortsaveDataMap.get(response.request().url().toString());

            //根据项目已有固定数据格式进行判断，仅适用于此项目并不通用
            BaseBean bean = GsonJsonTools.json2Obj(string, BaseBean.class);
            if (isGetDataSuccessfully(bean)) {
                return GsonJsonTools.json2Obj(string, nowListener.getType());
            } else {
                return (T) bean;
            }
            //通用数据解析
//            return GsonJsonTools.json2Obj(string, nowListener.getType());
        }

        @Override
        public void onResponse(T response, int id) {
            CustomDialogTool.dissProgressDialog();
            if (nowListener != null) {
                if (response instanceof BaseBean) {
                    if (isGetDataSuccessfully((BaseBean) response)) {
                        nowListener.onSucces(response);
                    } else {
                        if (!nowListener.notShowDefaultMsg()) {
                            SmartSnackbar.get((Activity) mContext).show(((BaseBean) response).getReturnMsg());
                        }
                        nowListener.onFailure(null, response);
                    }
                } else {
                    nowListener.onSucces(response);
                }
            }
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            CustomDialogTool.dissProgressDialog();

            if (nowListener != null) {

                nowListener.onFailure(e, null);


                if (!nowListener.notShowDefaultMsg()) {

                    SmartSnackbar.get((Activity) mContext).show("服务器连接超时，请稍候重试");

                }
            }

        }



    }

    /**
     * 入口
     *
     * @param <T>
     */
    public static abstract class BaseRequestListener<T> extends TypeToken<T> implements NetWorkListner<T> {
        /**
         * 不显示默认错误提示信息
         *
         * @return
         */
        public boolean notShowDefaultMsg() {
            return false;
        }
    }

    /**
     * 返回数据
     *
     * @param <T>
     */
    interface NetWorkListner<T> {
        void onSucces(T t);

        void onFailure(Exception e, T t);

    }

    /**
     * 网络状态是否不可用
     */
    private static boolean isNetStateUnavailable(final Context context) {

        if (!NetWorkCon.isConnectInternet(context)) {
            SmartSnackbar.get((Activity) context).showIndefinite(context.getString(R.string.netconnected_failed), "设置", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivityAction(context, Settings.ACTION_SETTINGS);
                }
            });
            return true;
        }
        return false;
    }

    /**
     * 是否初始化完成
     *
     * @param context
     * @return
     */
    private static boolean initNet(Context context) {
        if (isNetStateUnavailable(context)) {
            return false;
        }
        return true;
    }

    /**
     * 取消网络任务
     *
     * @param tag
     */
    public static void cancelNetWork(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
    }

    /**
     * 是否成功地获取到期望的数据
     *
     * @param baseBean 服务器返回的bean
     * @return true:代表成功
     */
    private static boolean isGetDataSuccessfully(BaseBean baseBean) {

        return StringTools.equals(HttpRequestType.SUCCESS_CODE, baseBean.getReturnCode());
    }



}
