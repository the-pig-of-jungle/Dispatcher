package com.liinji.ppgdeliver.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.liinji.ppgdeliver.bean.Authority;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.BillOrder;
import com.liinji.ppgdeliver.bean.BillRecord;
import com.liinji.ppgdeliver.bean.CashPayInfo;
import com.liinji.ppgdeliver.bean.CompanyBranchInfo;
import com.liinji.ppgdeliver.bean.CompleteOrder;
import com.liinji.ppgdeliver.bean.CompleteOutline;
import com.liinji.ppgdeliver.bean.DaishouDetail;
import com.liinji.ppgdeliver.bean.DebitConfig;
import com.liinji.ppgdeliver.bean.DebitOrder;
import com.liinji.ppgdeliver.bean.DeletePicParams;
import com.liinji.ppgdeliver.bean.HomeOrderNumber;
import com.liinji.ppgdeliver.bean.LoginParams;
import com.liinji.ppgdeliver.bean.Message;
import com.liinji.ppgdeliver.bean.MessageDetail;
import com.liinji.ppgdeliver.bean.MsgOutline;
import com.liinji.ppgdeliver.bean.NetBuddy;
import com.liinji.ppgdeliver.bean.NewWaitedOrderData;
import com.liinji.ppgdeliver.bean.OfflineBean;
import com.liinji.ppgdeliver.bean.Order;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.OrderPics;
import com.liinji.ppgdeliver.bean.PrintCompanyInfo;
import com.liinji.ppgdeliver.bean.PrintOrders;
import com.liinji.ppgdeliver.bean.ProblemOrder;
import com.liinji.ppgdeliver.bean.ProblemOrderItem;
import com.liinji.ppgdeliver.bean.ProblemOrderState;
import com.liinji.ppgdeliver.bean.ProcessDetail;
import com.liinji.ppgdeliver.bean.Qiankuan;
import com.liinji.ppgdeliver.bean.QuickDispatchResult;
import com.liinji.ppgdeliver.bean.ReDispatchOrder;
import com.liinji.ppgdeliver.bean.ReportItem;
import com.liinji.ppgdeliver.bean.SearchedOrder;
import com.liinji.ppgdeliver.bean.SendReportResult;
import com.liinji.ppgdeliver.bean.TableInfo;
import com.liinji.ppgdeliver.bean.ToDispatcher;
import com.liinji.ppgdeliver.bean.TraceInfo;
import com.liinji.ppgdeliver.bean.TripNo;
import com.liinji.ppgdeliver.bean.TripNos;
import com.liinji.ppgdeliver.bean.UploadImgResult;
import com.liinji.ppgdeliver.bean.UploadSignPicParams;
import com.liinji.ppgdeliver.bean.UserInfo;
import com.liinji.ppgdeliver.bean.VersionInfo;
import com.liinji.ppgdeliver.bean.WaitedOrder;
import com.liinji.ppgdeliver.configure.AppConstants;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.tools.AESEncryptUtil;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


public class HttpTools {
    public static Gson sGson = new Gson();


    public static void login(Context context, LoginParams params, final HttpRequestListener listener) {

        RequestParams requestParams = new RequestParams();
        requestParams.put(HttpParams.LOGIN_ACCOUNT, params.getLoginAccount());
        requestParams.put(HttpParams.PASSWORD, params.getPassword());
        requestParams.put(HttpParams.USER_TYPE, params.getUserType());

        HttpNetUtils.postNetData(context, HttpUrl.LOGIN_URL, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<UserInfo>>() {
            @Override
            public void onSucces(BaseBean<UserInfo> userInfoBaseBean) {
                listener.onSuccess(HttpRequestType.LOGIN_REQUEST, userInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<UserInfo> userInfoBaseBean) {
                listener.onFailed(HttpRequestType.LOGIN_REQUEST, e, userInfoBaseBean);
            }
        });
    }

    public static void loginTest(Context context, LoginParams params, final HttpRequestListener listener) {

        RequestParams requestParams = new RequestParams();
        requestParams.put(HttpParams.LOGIN_ACCOUNT, params.getLoginAccount());
        requestParams.put(HttpParams.PASSWORD, params.getPassword());
        requestParams.put(HttpParams.USER_TYPE, params.getUserType());

        HttpNetUtils.postNetData(context, HttpUrl.LOGIN_URL, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<UserInfo>>() {
            @Override
            public void onSucces(BaseBean<UserInfo> userInfoBaseBean) {
                listener.onSuccess(HttpRequestType.LOGIN_REQUEST, userInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<UserInfo> userInfoBaseBean) {
                listener.onFailed(HttpRequestType.LOGIN_REQUEST, e, userInfoBaseBean);
            }
        });
    }


    public static void setWaitDebitMark(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.WAIT_DEBIT_COUNT, HttpParams.setDebiyParams(""), new HttpNetUtils.BaseRequestListener<BaseBean<HomeOrderNumber>>() {
            @Override
            public void onSucces(BaseBean<HomeOrderNumber> listBaseBean) {
                listener.onSuccess(HttpRequestType.EEBIT_MARK, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<HomeOrderNumber> listBaseBean) {
                listener.onFailed(HttpRequestType.EEBIT_MARK, e, listBaseBean);
            }
        });
    }

    public static void setOrderCompleteList(Context context, String pageNo, String pageSize, final HttpRequestListener listener, String beginDate, String endDate, String keywords) {

        RequestParams requestParams = HttpParams.setCompleteOrderParams(pageNo, pageSize, beginDate, endDate,keywords);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.ORDER_COMPLETE, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<CompleteOrder>>() {
            @Override
            public void onSucces(BaseBean<CompleteOrder> listBaseBean) {
                BaseBean<List<CompleteOrder.ReturnDataBean>> b = getCompleteOrderListBaseBean(listBaseBean);
                listener.onSuccess(HttpRequestType.COMPLETE_ORDER_LIST, b);
            }

            @Override
            public void onFailure(Exception e, BaseBean<CompleteOrder> listBaseBean) {
                if (e != null){
                    Logger.d(e.toString());
                }
                listener.onFailed(HttpRequestType.COMPLETE_ORDER_LIST, e, null);
            }
        });
    }


    public static void setWaitSendedList(Context context, String tripNo, String keywords, String pageNo, String pageSize, final HttpRequestListener listener, String startDate, String endDate, String sequence) {
        RequestParams requestParams = HttpParams.setWaitSendedParams(keywords, pageNo, pageSize, startDate, endDate, tripNo, sequence);
        Logger.d(requestParams.getParams() + "爱你");
        HttpNetUtils.getNetData(context, HttpUrl.WAIT_SENDED_LIST, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<List<Order>>>() {
            @Override
            public void onSucces(BaseBean<List<Order>> listBaseBean) {
                listener.onSuccess(HttpRequestType.WAIT_SENDED_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<Order>> listBaseBean) {
                listener.onFailed(HttpRequestType.WAIT_SENDED_LIST, e, listBaseBean);
                Logger.d(e);
            }
        });

    }


    @NonNull
    private static BaseBean<List<WaitedOrder.ReturnDataBean>> getWaitedOrderListBaseBean(BaseBean<WaitedOrder> bean) {
        List<WaitedOrder.ReturnDataBean> beans = bean.getReturnData().getReturnData();

        BaseBean<List<WaitedOrder.ReturnDataBean>> list = new BaseBean<>();
        list.setReturnCode(bean.getReturnCode());
        list.setReturnData(beans);
        list.setReturnMsg(bean.getReturnMsg());
        list.setReturnTotalRecords(bean.getReturnTotalRecords());
        list.setSumPieces(bean.getReturnData().getTotalMessage().getSumPieces());
        list.setSumRecords(bean.getReturnData().getTotalMessage().getSumRecords());
        return list;
    }


    @NonNull
    private static BaseBean<List<CompleteOrder.ReturnDataBean>> getCompleteOrderListBaseBean(BaseBean<CompleteOrder> bean) {
        List<CompleteOrder.ReturnDataBean> beans = bean.getReturnData().getReturnData();

        BaseBean<List<CompleteOrder.ReturnDataBean>> list = new BaseBean<>();
        list.setReturnCode(bean.getReturnCode());
        list.setReturnData(beans);
        list.setReturnMsg(bean.getReturnMsg());
        list.setReturnTotalRecords(bean.getReturnTotalRecords());
        list.setSumPieces(bean.getReturnData().getTotalMessage().getSumPieces());
        list.setSumRecords(bean.getReturnData().getTotalMessage().getSumRecords());
        return list;
    }


    public static void applySubmitBill(Context context, String amount, final HttpRequestListener listener) {
        Logger.d(HttpParams.setApplyBillParams(amount).getParams());
        HttpNetUtils.getNetData(context, HttpUrl.APPLY_SUBMIT_BILL, HttpParams.setApplyBillParams(amount), new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {

            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.APPLY_SUBMIT_BILL, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.APPLY_SUBMIT_BILL, e, objectBaseBean);
            }
        });

    }


    public static void getDebitList(Context context, final HttpRequestListener listener, String keywords) {
        RequestParams requestParams = HttpParams.setDebiyParams(keywords);
        HttpNetUtils.getNetData(context, HttpUrl.DEBIT_LIST, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<DebitOrder>>() {
            @Override
            public void onSucces(BaseBean<DebitOrder> listBaseBean) {

                listener.onSuccess(HttpRequestType.DEBIT_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<DebitOrder> listBaseBean) {

                listener.onFailed(HttpRequestType.DEBIT_LIST, e, listBaseBean);
            }
        });
    }

    public static void getOrderTraceInfo(Context context, String order, final HttpRequestListener listener) {
        Logger.t("监测order");
        Logger.d(order);
        HttpNetUtils.getNetData(context, HttpUrl.ORDER_TRACE_INFO, HttpParams.setTraceInfoParams(order), new HttpNetUtils.BaseRequestListener<BaseBean<TraceInfo>>() {
            @Override
            public void onSucces(BaseBean<TraceInfo> traceInfoBaseBean) {
                Logger.d("执行成功");
                listener.onSuccess(HttpRequestType.ORDER_TRACE_INFO, traceInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<TraceInfo> traceInfoBaseBean) {
                Logger.d("执行失败");
                listener.onFailed(HttpRequestType.ORDER_TRACE_INFO, e, traceInfoBaseBean);
            }
        });
    }


    public static void changePassword(Context context, String oldPwd, String newPwd, final HttpRequestListener listener) {
        HttpNetUtils.postNetData(context, HttpUrl.CHANGE_PASSWORD, HttpParams.setChangePwdParams(AESEncryptUtil.getInstance(AppConstants.SECRET_KEY).encrypt(oldPwd), AESEncryptUtil.getInstance(AppConstants.SECRET_KEY).encrypt(newPwd)), new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {


            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.CHANGE_PASSWORD, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.CHANGE_PASSWORD, e, objectBaseBean);

            }
        });
    }


    public static void realCashPay(Context context, String order, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.REAL_CASH_PAY_AMOUNT, HttpParams.setRealCashPayParams(order), new HttpNetUtils.BaseRequestListener<BaseBean<CashPayInfo>>() {

            @Override
            public void onSucces(BaseBean<CashPayInfo> cashPayInfoBaseBean) {
                listener.onSuccess(HttpRequestType.REAL_CASH_PAY_AMOUNT, cashPayInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<CashPayInfo> cashPayInfoBaseBean) {
                listener.onFailed(HttpRequestType.REAL_CASH_PAY_AMOUNT, e, cashPayInfoBaseBean);

            }
        });
    }

    public static void cashPay(Context context, String order, String signer, String signRemarks, float amount, int payWay, final HttpRequestListener listener) {
        RequestParams params = HttpParams.setCashPayParams(order, signer, signRemarks, amount, payWay);
        Logger.d("监控数据" + params.getParams());
        HttpNetUtils.postNetData(context, HttpUrl.CASH_PAY, params, new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.CASH_PAY, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.CASH_PAY, e, objectBaseBean);
            }
        });
    }


    public static void isMyOrder(Context context, String order, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.IS_MY_ORDER, HttpParams.setIsMyOrderParams(Utils.trimOrder(order)), new HttpNetUtils.BaseRequestListener<BaseBean<Integer>>() {
            @Override
            public void onSucces(BaseBean<Integer> integerBaseBean) {
                listener.onSuccess(HttpRequestType.IS_MY_ORDER, integerBaseBean);
                Logger.d("is my order success");
            }

            @Override
            public void onFailure(Exception e, BaseBean<Integer> integerBaseBean) {
                listener.onFailed(HttpRequestType.IS_MY_ORDER, e, integerBaseBean);
                Logger.d("is my order fail");
                Logger.json(JsonUtils.jsonStr(integerBaseBean));
            }

            @Override
            public boolean notShowDefaultMsg() {
                return true;
            }
        });
    }

    public static void howManyToSubmit(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.HOW_MANY_TO_SUMIT, HttpParams.setHowManyToSubmitParams(), new HttpNetUtils.BaseRequestListener<BaseBean<Float>>() {

            @Override
            public void onSucces(BaseBean<Float> integerBaseBean) {
                listener.onSuccess(HttpRequestType.HOW_MANY_TO_SUBMIT, integerBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Float> integerBaseBean) {
                listener.onFailed(HttpRequestType.HOW_MANY_TO_SUBMIT, e, integerBaseBean);
            }
        });
    }


    public static void orderDetail(Context context, String order, final HttpRequestListener listener) {

        Logger.d("调用了！");
        HttpNetUtils.getNetData(context, HttpUrl.ORDER_DETAIL, HttpParams.setOrderDetail(order), new HttpNetUtils.BaseRequestListener<BaseBean<OrderDetail>>() {
            @Override
            public void onSucces(BaseBean<OrderDetail> orderDetailBaseBean) {

                Logger.d("成功！");

                if (orderDetailBaseBean != null) {
                    Logger.d(JsonUtils.jsonStr(orderDetailBaseBean.getReturnData()));
                }

                listener.onSuccess(HttpRequestType.ORDER_DETAIL, orderDetailBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<OrderDetail> orderDetailBaseBean) {

                Logger.d("失败！");
                if (e != null) {
                    Logger.d(e);
                }

                listener.onFailed(HttpRequestType.ORDER_DETAIL, e, orderDetailBaseBean);
            }
        });
    }


    public static void billRecord(Context context, String pageNo, String pageSize, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.BILL_RECORD, HttpParams.setBillRecordParams(pageNo, pageSize), new HttpNetUtils.BaseRequestListener<BaseBean<List<BillRecord>>>() {
            @Override
            public void onSucces(BaseBean<List<BillRecord>> listBaseBean) {
                Logger.t("记录");
                Logger.json(JsonUtils.jsonStr(listBaseBean));
                listener.onSuccess(HttpRequestType.BILL_RECORD, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<BillRecord>> listBaseBean) {
                if (e != null) {
                    Logger.d(e);
                }
                listener.onFailed(HttpRequestType.BILL_RECORD, e, listBaseBean);
            }
        });
    }

    public static void suggestion(Context context, String suggestion, final HttpRequestListener listener) {
        HttpNetUtils.postNetData(context, HttpUrl.SUGGEST_MESSAGE, HttpParams.setSuggestionParams(suggestion), new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.SUGGESTION_MESSAGE, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.SUGGESTION_MESSAGE, e, objectBaseBean);
            }
        });
    }

    public static void report(Context context, String dateFrom, String dateTo, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.REPORT, HttpParams.setReportParams(dateFrom, dateTo), new HttpNetUtils.BaseRequestListener<BaseBean<List<ReportItem>>>() {

            @Override
            public void onSucces(BaseBean<List<ReportItem>> listBaseBean) {
                listener.onSuccess(HttpRequestType.REPORT, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<ReportItem>> listBaseBean) {
                listener.onFailed(HttpRequestType.REPORT, e, listBaseBean);
            }
        });
    }


    public static void versionUpdate(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.VERSION_UPDATE, HttpParams.setVersionUpdateParams(), new HttpNetUtils.BaseRequestListener<BaseBean<VersionInfo>>() {
            @Override
            public void onSucces(BaseBean<VersionInfo> versionInfoBaseBean) {
                listener.onSuccess(HttpRequestType.VERSION_UPDATE, versionInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<VersionInfo> versionInfoBaseBean) {
                listener.onFailed(HttpRequestType.VERSION_UPDATE, e, versionInfoBaseBean);
            }
        });
    }


    public static void hasUnreadMsg(Context context, final HttpRequestListener listener) {

        HttpNetUtils.getNetData(context, HttpUrl.HAS_UNREAD_MSG, HttpParams.setHasUnreadMsgParams(), new HttpNetUtils.BaseRequestListener<BaseBean<List<MsgOutline>>>() {
            @Override
            public void onSucces(BaseBean<List<MsgOutline>> listBaseBean) {
                listener.onSuccess(HttpRequestType.HAS_UNREAD_MSG, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<MsgOutline>> listBaseBean) {
                listener.onFailed(HttpRequestType.HAS_UNREAD_MSG, e, listBaseBean);
            }
        });

    }

    public static void msgList(Context context, int pageNo, int pageNum, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.MSG_LIST, HttpParams.setMsgListParams(pageNo, pageNum), new HttpNetUtils.BaseRequestListener<BaseBean<List<Message>>>() {
            @Override
            public void onSucces(BaseBean<List<Message>> listBaseBean) {
                listener.onSuccess(HttpRequestType.MSG_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<Message>> listBaseBean) {
                listener.onFailed(HttpRequestType.MSG_LIST, e, listBaseBean);
            }
        });
    }


    public static void msgDetail(Context context, int msgId, final HttpRequestListener listener) {

        HttpNetUtils.getNetData(context, HttpUrl.MSG_DETAIL, HttpParams.setMsgDetailParams(msgId), new HttpNetUtils.BaseRequestListener<BaseBean<MessageDetail>>() {
            @Override
            public void onSucces(BaseBean<MessageDetail> messageDetailBaseBean) {
                listener.onSuccess(HttpRequestType.MSG_DETAIL, messageDetailBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<MessageDetail> messageDetailBaseBean) {
                listener.onFailed(HttpRequestType.MSG_DETAIL, e, messageDetailBaseBean);
            }
        });
    }

    public static void ignoreUnreadMsg(Context context, final HttpRequestListener listener) {
        HttpNetUtils.postNetData(context, HttpUrl.IGNORE_UNREAD_MSG, HttpParams.setIgnoreUnreadMsgParams(), new HttpNetUtils.BaseRequestListener<BaseBean<List<MsgOutline>>>() {
            @Override
            public void onSucces(BaseBean<List<MsgOutline>> listBaseBean) {
                listener.onSuccess(HttpRequestType.IGNORE_UNREAD_MSG, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<MsgOutline>> listBaseBean) {
                listener.onFailed(HttpRequestType.IGNORE_UNREAD_MSG, e, listBaseBean);
            }
        });
    }

    public static void getBranch(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_BRANCH, HttpParams.setGetBranchParams(), new HttpNetUtils.BaseRequestListener<BaseBean<List<CompanyBranchInfo>>>() {
            @Override
            public void onSucces(BaseBean<List<CompanyBranchInfo>> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_BRANCH, listBaseBean);

            }

            @Override
            public void onFailure(Exception e, BaseBean<List<CompanyBranchInfo>> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_BRANCH, e, listBaseBean);
            }
        });

    }

    public static void getState(Context context, final HttpRequestListener listener) {

        HttpNetUtils.getNetData(context, HttpUrl.GET_STATE, HttpParams.setGetStateParams(), new HttpNetUtils.BaseRequestListener<BaseBean<List<ProblemOrderState>>>() {

            @Override
            public void onSucces(BaseBean<List<ProblemOrderState>> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_STATE, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<ProblemOrderState>> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_STATE, e, listBaseBean);
            }

        });

    }

    public static void uploadProblemImg(Context context, String fileStream, String waybillno, final HttpRequestListener listener) {

        HttpNetUtils.postNetData(context, HttpUrl.UPLOAD_PROBLEM_IMG, HttpParams.setUploadProblemImgParams(fileStream, waybillno), new HttpNetUtils.BaseRequestListener<BaseBean<String>>() {
            @Override
            public void onSucces(BaseBean<String> stringBaseBean) {
                listener.onSuccess(HttpRequestType.UPLOAD_PROBLEM_IMG, stringBaseBean);

            }

            @Override
            public void onFailure(Exception e, BaseBean<String> stringBaseBean) {
                listener.onFailed(HttpRequestType.UPLOAD_PROBLEM_IMG, e, stringBaseBean);
            }
        });


    }


    public static void submitProblemInfo(Context context, final HttpRequestListener listener, final ProblemOrder problemOrder) {

        RequestParams requestParams = HttpParams.setSubmitProblemInfoParams(problemOrder);
        Logger.d(requestParams.getParams());
        HttpNetUtils.postNetData(context, HttpUrl.SUBMIT_PROBLEM_INFO, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.SUBMIT_PROBLEM_INFO, objectBaseBean);
                Logger.json(JsonUtils.jsonStr(problemOrder));
                Logger.d(objectBaseBean.getReturnData());
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.SUBMIT_PROBLEM_INFO, e, objectBaseBean);
            }
        });
    }


    public static void getProblemOrderList(Context context, final HttpRequestListener listener, int pageNo, int pageSize) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_PROBLEM_ORDER_LIST, HttpParams.setGetProblemOrderListParams(pageNo, pageSize), new HttpNetUtils.BaseRequestListener<BaseBean<List<ProblemOrderItem>>>() {
            @Override
            public void onSucces(BaseBean<List<ProblemOrderItem>> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_PROBLEM_ORDER_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<ProblemOrderItem>> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_PROBLEM_ORDER_LIST, e, listBaseBean);
            }
        });
    }


    public static void getProcessList(Context context, final HttpRequestListener listener, String problemId) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_PROCESS_LIST, HttpParams.setProcessListParams(problemId), new HttpNetUtils.BaseRequestListener<BaseBean<ProcessDetail>>() {
            @Override
            public void onSucces(BaseBean<ProcessDetail> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_PROCESS_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<ProcessDetail> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_PROCESS_LIST, e, listBaseBean);
            }
        });
    }


    public static void getNormalQiankuan(Context context, final HttpRequestListener listener, int pageNo, int pageSize, String keywords) {
        Logger.d(HttpParams.setNormalQiankuanParams(pageNo, pageSize, keywords).getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_NORMAL_QIANKUAN, HttpParams.setNormalQiankuanParams(pageNo, pageSize, keywords), new HttpNetUtils.BaseRequestListener<BaseBean<OfflineBean>>() {

            @Override
            public void onSucces(BaseBean<OfflineBean> offlineBeanBaseBean) {
                BaseBean<List<Qiankuan>> newBase = getListQBean(offlineBeanBaseBean);
                OfflineBean offlineBean = offlineBeanBaseBean.getReturnData();
                newBase.setmOverdue5(offlineBean.getTotalMessage().getTotalOver5());
                newBase.setmOverdue15(offlineBean.getTotalMessage().getTotalOver15());
                newBase.setmOverdue30(offlineBean.getTotalMessage().getTotalOver30());
                newBase.setmTotalOrders(offlineBean.getTotalMessage().getTotalRec());
                listener.onSuccess(HttpRequestType.GET_NORMAL_QIANKUAN, newBase);
            }

            @Override
            public void onFailure(Exception e, BaseBean<OfflineBean> offlineBeanBaseBean) {
                BaseBean<List<Qiankuan>> newBase = getListQBean(offlineBeanBaseBean);
                listener.onFailed(HttpRequestType.GET_NORMAL_QIANKUAN, e, newBase);
            }
        });
    }

    @NonNull
    private static BaseBean<List<Qiankuan>> getListQBean(BaseBean<OfflineBean> offlineBeanBaseBean) {
        List<OfflineBean.ReturnDataBean> src = offlineBeanBaseBean.getReturnData().getReturnData();
        List<Qiankuan> qiankuans = new ArrayList<>(src.size());
        for (int i = 0; i < src.size(); i++) {
            OfflineBean.ReturnDataBean srcItem = src.get(i);
            Qiankuan qiankuan = new Qiankuan();
            qiankuan.setCargoName(srcItem.getCargoName());
            qiankuan.setCollectAmount(srcItem.getCollectAmount());
            qiankuan.setCollectPayStatus(srcItem.getCollectPayStatus());
            qiankuan.setConsignDate(srcItem.getConsignDate());
            qiankuan.setFreightAmount(srcItem.getFreightAmount());
            qiankuan.setFreightStatus(srcItem.getFreightStatus());
            qiankuan.setNumber(srcItem.getNumber());
            qiankuan.setOweDay(srcItem.getOweDay());
            qiankuan.setPaperNumber(srcItem.getPaperNumber());
            qiankuan.setReceiveAddress(srcItem.getReceiveAddress());
            qiankuan.setReceiver(srcItem.getReceiver());
            qiankuan.setReceiveTel(srcItem.getReceiveTel());
            qiankuan.setSignDate(srcItem.getSignDate());
            qiankuan.setWaybillId(srcItem.getWaybillId());
            qiankuan.setWaybillNo(srcItem.getWaybillNo());
            qiankuan.setWaybillCargoNo(srcItem.getWaybillCargoNo());
            qiankuan.setSendBranchName(srcItem.getSendBranchName());
            qiankuans.add(qiankuan);
        }
        BaseBean<List<Qiankuan>> newBase = new BaseBean<>();
        newBase.setReturnData(qiankuans);
        newBase.setReturnMsg(offlineBeanBaseBean.getReturnMsg());
        newBase.setReturnCode(offlineBeanBaseBean.getReturnCode());
        newBase.setReturnTotalRecords(offlineBeanBaseBean.getReturnTotalRecords());
        return newBase;
    }

    public static void getDaishouDebitDetail(Context context, final HttpRequestListener listener, String orderId) {
        RequestParams requestParams = HttpParams.setDaishouDebitDetailParams(orderId);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.DAISHOU_DEBIT_DETAIL, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<DaishouDetail>>() {
            @Override
            public void onSucces(BaseBean<DaishouDetail> daishouDetail) {
                listener.onSuccess(HttpRequestType.DAISHOU_DEBIT_DETAIL, daishouDetail);
            }

            @Override
            public void onFailure(Exception e, BaseBean<DaishouDetail> daishouDetail) {

                listener.onFailed(HttpRequestType.DAISHOU_DEBIT_DETAIL, e, daishouDetail);
            }
        });
    }


    public static void daishouPay(Context context, final HttpRequestListener listener, String orderId, String collectAmount, String yunFeeAmount) {

        RequestParams httpParams = HttpParams.setDaishouPayParams(orderId, collectAmount, yunFeeAmount);
        Logger.d(httpParams.getParams());
        HttpNetUtils.postNetData(context, HttpUrl.DAISHOU_PAY, httpParams, new HttpNetUtils.BaseRequestListener<BaseBean<String>>() {
            @Override
            public void onSucces(BaseBean<String> stringBaseBean) {
                listener.onSuccess(HttpRequestType.DAISHOU_PAY, stringBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<String> stringBaseBean) {
                listener.onFailed(HttpRequestType.DAISHOU_PAY, e, stringBaseBean);
            }
        });
    }


    public static void getReport(Context context, final HttpRequestListener listener, String startDate, String endDate, int pageNo, int pageSize, int payType, String signer) {
        RequestParams requestParams = HttpParams.setReportParams(startDate, endDate, payType, pageNo, pageSize,signer);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_REPORT, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<TableInfo>>() {
            @Override
            public void onSucces(BaseBean<TableInfo> tableInfoBaseBean) {
                listener.onSuccess(HttpRequestType.GET_REPORT, tableInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<TableInfo> tableInfoBaseBean) {
                listener.onFailed(HttpRequestType.GET_REPORT, e, tableInfoBaseBean);
            }

        });

    }

    public static void getBillOrderList(Context context, final HttpRequestListener listener, int billId, int pageNo, int pageSize) {
        RequestParams requestParams = HttpParams.setBillOrderListParams(billId, pageNo, pageSize);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_BILL_ORDER_LIST, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<List<BillOrder>>>() {
            @Override
            public void onSucces(BaseBean<List<BillOrder>> listBaseBean) {
                listener.onSuccess(HttpRequestType.getBillOrderList, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<BillOrder>> listBaseBean) {
                listener.onFailed(HttpRequestType.getBillOrderList, e, listBaseBean);
            }
        });
    }


    public static void judgeIsDefaultDispatcher(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.IS_DEFAULT_DISPATCHER, HttpParams.setIsDefaultDispatcherParams(), new HttpNetUtils.BaseRequestListener<BaseBean<Integer>>() {
            @Override
            public void onSucces(BaseBean<Integer> integerBaseBean) {
                Logger.d("是否是默认配送员" + integerBaseBean.getReturnData());
                listener.onSuccess(HttpRequestType.IS_DEFAULT_DISPATCHER, integerBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Integer> integerBaseBean) {
                Logger.d("失败了" + e + integerBaseBean);
                listener.onFailed(HttpRequestType.IS_DEFAULT_DISPATCHER, e, integerBaseBean);
            }
        });
    }

    public static void getBuddy(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_BUDDY, HttpParams.setGetBuddyParam(), new HttpNetUtils.BaseRequestListener<BaseBean<List<NetBuddy>>>() {
            @Override
            public void onSucces(BaseBean<List<NetBuddy>> netBuddyBaseBean) {
                listener.onSuccess(HttpRequestType.GET_BUDDY, netBuddyBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<NetBuddy>> netBuddyBaseBean) {
                listener.onFailed(HttpRequestType.GET_BUDDY, e, netBuddyBaseBean);
            }
        });
    }


    public static void submitBuddy(Context context, final HttpRequestListener listener, ToDispatcher toDispatcher) {
        String orderList = "";
        for (int i = 0; i < toDispatcher.getListWaybillNo().size(); i++) {
            orderList = orderList + toDispatcher.getListWaybillNo().get(i);
            if (i != toDispatcher.getListWaybillNo().size() - 1) {
                orderList = orderList + ",";
            }
        }
        RequestParams params = new RequestParams();
        UserInfo info = SharePrefUtils.getUserInfo();
        params.put("BranchCode", toDispatcher.getBranchCode());
        params.put("CompanyCode", toDispatcher.getCompanyCode());
        params.put("DistributeUserId", toDispatcher.getDistributeUserId() + "");
        params.put("DistributeUserName", toDispatcher.getDistributeUserName());
        params.put("ListWaybillNo", orderList);
        params.put("UserId", toDispatcher.getUserId() + "");
        Logger.d(params.getParams());

        HttpNetUtils.postNetData(context, HttpUrl.TO_BUDDY, params, new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.TO_BUDDY, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.TO_BUDDY, e, objectBaseBean);
            }
        });
    }

    public static void getDebitConfig(Context context, final HttpRequestListener listener) {

        HttpNetUtils.getNetData(context, HttpUrl.DEBIT_CONFIG, HttpParams.setDebitConfigParam(), new HttpNetUtils.BaseRequestListener<BaseBean<DebitConfig>>() {
            @Override
            public void onSucces(BaseBean<DebitConfig> debitConfigBaseBean) {
                listener.onSuccess(HttpRequestType.DEBIT_CONFIG, debitConfigBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<DebitConfig> debitConfigBaseBean) {
                listener.onFailed(HttpRequestType.DEBIT_CONFIG, e, debitConfigBaseBean);
            }


        });
    }

    public static void getPrintCompanyInfo(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_PRINT_COMPANY_INFO, HttpParams.setPrintCompanyParam(), new HttpNetUtils.BaseRequestListener<BaseBean<PrintCompanyInfo>>() {
            @Override
            public void onSucces(BaseBean<PrintCompanyInfo> printCompanyInfoBaseBean) {
                listener.onSuccess(HttpRequestType.GET_PRINT_COMPANY_INFO, printCompanyInfoBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<PrintCompanyInfo> printCompanyInfoBaseBean) {
                listener.onFailed(HttpRequestType.GET_PRINT_COMPANY_INFO, e, printCompanyInfoBaseBean);
            }
        });
    }

    public static void getRedispatchOrderList(Context context, final HttpRequestListener listener, String keywords, String pageNo, String pageSize) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_REDISPATCH_ORDER_LIST, HttpParams.setRedispatchOrderListParams(keywords, pageNo, pageSize), new HttpNetUtils.BaseRequestListener<BaseBean<List<ReDispatchOrder>>>() {
            @Override
            public void onSucces(BaseBean<List<ReDispatchOrder>> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_REDISPATCH_ORDER_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<ReDispatchOrder>> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_REDISPATCH_ORDER_LIST, e, listBaseBean);
            }
        });
    }


    public static void getSearchedList(Context context, final HttpRequestListener listener, String keywords) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_SEARCHED_LIST, HttpParams.setSearchedListParams(keywords), new HttpNetUtils.BaseRequestListener<BaseBean<List<SearchedOrder>>>() {
            @Override
            public void onSucces(BaseBean<List<SearchedOrder>> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_SEARCHED_LIST, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<List<SearchedOrder>> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_SEARCHED_LIST, e, listBaseBean);
            }
        });
    }


    public static void getAuthority(Context context, final HttpRequestListener listener) {
        HttpNetUtils.getNetData(context, HttpUrl.GET_AUTHORITY, HttpParams.setAuthorityParams(), new HttpNetUtils.BaseRequestListener<BaseBean<Authority>>() {
            @Override
            public void onSucces(BaseBean<Authority> authorityBaseBean) {
                listener.onSuccess(HttpRequestType.GET_AUTHORITY, authorityBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Authority> authorityBaseBean) {
                listener.onFailed(HttpRequestType.GET_AUTHORITY, e, authorityBaseBean);
            }
        });
    }


    public static void reduceYunfee(Context context, final HttpRequestListener listener, String orderId, float amount) {
        HttpNetUtils.postNetData(context, HttpUrl.REDUCE_YUNFEE, HttpParams.setReduceYunfeeParams(orderId, amount), new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.REDUCE_YUNFEE, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.REDUCE_YUNFEE, e, objectBaseBean);
            }
        });
    }

    public static void reduceDaishou(Context context, final HttpRequestListener listener, String orderId, float amount) {
        HttpNetUtils.postNetData(context, HttpUrl.REDUCE_DAISHOU, HttpParams.setReduceDaishParams(orderId, amount), new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.REDUCE_DAISHOU, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.REDUCE_DAISHOU, e, objectBaseBean);
            }
        });
    }

    public static void getCompleteOutline(Context context, final HttpRequestListener listener, String beginDate, String endDate, String keywords) {
        RequestParams requestParam = HttpParams.setCompleteOutlineParams(beginDate, endDate,keywords);
        Logger.d(requestParam.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_COMPLETE_OUTLINE, requestParam, new HttpNetUtils.BaseRequestListener<BaseBean<CompleteOutline>>() {
            @Override
            public void onSucces(BaseBean<CompleteOutline> outlineBaseBean) {
                listener.onSuccess(HttpRequestType.GET_COMPLETER_OUTLINE, outlineBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<CompleteOutline> outlineBaseBean) {
                if (e != null) {
                    Logger.d(e);
                }
                listener.onFailed(HttpRequestType.GET_COMPLETER_OUTLINE, e, outlineBaseBean);
            }
        });
    }

    public static void getTripNo(Context context, final HttpRequestListener listener, String searchDate) {
        RequestParams requestParams = HttpParams.setGetTripNosList(searchDate);
        Logger.d(requestParams.getParams());

        HttpNetUtils.getNetData(context, HttpUrl.GET_TRIP_NO, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<TripNos>>() {
            @Override
            public void onSucces(BaseBean<TripNos> listBaseBean) {
                listener.onSuccess(HttpRequestType.GET_TRIP_NO, listBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<TripNos> listBaseBean) {
                listener.onFailed(HttpRequestType.GET_TRIP_NO, e, listBaseBean);
            }
        });
    }

    @NonNull
    private static BaseBean<List<TripNo>> getListTripNo(BaseBean<NewWaitedOrderData> listBaseBean) {
        List<NewWaitedOrderData.ReturnDataBean> beans = ((NewWaitedOrderData) listBaseBean.getReturnData()).getReturnData();
        List<TripNo> orders = new ArrayList<>(beans.size());
        for (int i = 0; i < beans.size(); i++) {
            TripNo tripNo = new TripNo();
            tripNo.setTripNo(beans.get(i).getTripNo());
            orders.add(tripNo);
        }

        BaseBean<List<TripNo>> list = new BaseBean<>();
        list.setReturnTotalRecords(listBaseBean.getReturnTotalRecords());
        list.setReturnMsg(listBaseBean.getReturnMsg());
        list.setReturnCode(listBaseBean.getReturnCode());
        list.setReturnData(orders);
        return list;
    }

    public static void updateSignRemark(Context context, final HttpRequestListener listener, String waybillId, String signRemarks) {
        MyApplication.sCancelNetTip = true;
        RequestParams requestParams = HttpParams.setUpdateSignRemarksParams(waybillId, signRemarks);

        Logger.d(requestParams);
        HttpNetUtils.postNetData(context, HttpUrl.UPDATE_SIGN_REMARKS, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.UPDATE_SIGN_REMARKS, objectBaseBean);
                MyApplication.sCancelNetTip = false;
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.UPDATE_SIGN_REMARKS, e, objectBaseBean);
                MyApplication.sCancelNetTip = false;
            }
        });
    }

    public static void quickDispatch(Context context, final HttpRequestListener listener, String orderList) {
        HttpNetUtils.postNetData(context, HttpUrl.QUICK_DISPATCH, HttpParams.setQuickDispatchParams(orderList), new HttpNetUtils.BaseRequestListener<BaseBean<QuickDispatchResult>>() {
            @Override
            public void onSucces(BaseBean<QuickDispatchResult> quickDispatchResultBaseBean) {
                listener.onSuccess(HttpRequestType.QUICK_DISPATCH, quickDispatchResultBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<QuickDispatchResult> quickDispatchResultBaseBean) {
                listener.onFailed(HttpRequestType.QUICK_DISPATCH, e, quickDispatchResultBaseBean);
            }
        });
    }


    public static void getSendReport(Context context, final HttpRequestListener listener, String startDate, String endDate, int pageNo, int pageSize, int senderId, int isDefaultSender, int ifConsign) {
        RequestParams requestParams = HttpParams.setGetSendReportParams(startDate, endDate, pageNo, pageSize, senderId, isDefaultSender, ifConsign);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_SEND_REPORT, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<SendReportResult>>() {
            @Override
            public void onSucces(BaseBean<SendReportResult> sendReportResultBaseBean) {
                listener.onSuccess(HttpRequestType.GET_SEND_REPORT, sendReportResultBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<SendReportResult> sendReportResultBaseBean) {
                if (e != null) {
                    Logger.d(e);
                }
                listener.onFailed(HttpRequestType.GET_SEND_REPORT, e, sendReportResultBaseBean);
            }
        });
    }


    public static void getSentOrderList(Context context, final HttpRequestListener listener, String keywords, int pageIndex, int pageSize) {
        RequestParams requestParams = HttpParams.setWaitedOrderListParams(keywords, pageIndex, pageSize);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_WAITED_ORDER_LIST, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<WaitedOrder>>() {
            @Override
            public void onSucces(BaseBean<WaitedOrder> waitedOrderBaseBean) {
                BaseBean<List<WaitedOrder.ReturnDataBean>> b = getWaitedOrderListBaseBean(waitedOrderBaseBean);
                listener.onSuccess(HttpRequestType.GET_WAITED_ORDER_LIST, b);
            }

            @Override
            public void onFailure(Exception e, BaseBean<WaitedOrder> waitedOrderBaseBean) {
                BaseBean<List<WaitedOrder.ReturnDataBean>> b = getWaitedOrderListBaseBean(waitedOrderBaseBean);
                listener.onFailed(HttpRequestType.GET_WAITED_ORDER_LIST, e, b);
            }
        });
    }


    public static void getPrintOrderList(Context context, final HttpRequestListener listener, String startDate, String endDate, String tripNo, String keywords, String orderBy, int pageIndex, int pageSize) {
        RequestParams requestParams = HttpParams.setPrintOrderListParams(startDate, endDate, tripNo, keywords, orderBy, pageIndex, pageSize);
        Logger.d(requestParams.getParams());
        HttpNetUtils.getNetData(context, HttpUrl.GET_PRINT_ORDER_LIST, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<PrintOrders>>() {
            @Override
            public void onSucces(BaseBean<PrintOrders> printOrdersBaseBean) {
                BaseBean<List<PrintOrders.ReturnDataBean>> b = new BaseBean<>();
                List<PrintOrders.ReturnDataBean> data = printOrdersBaseBean.getReturnData().getReturnData();
                b.setReturnCode(printOrdersBaseBean.getReturnCode());
                b.setReturnMsg(printOrdersBaseBean.getReturnMsg());
                b.setReturnData(data);
                b.setReturnTotalRecords(printOrdersBaseBean.getReturnTotalRecords());
                listener.onSuccess(HttpRequestType.GET_PRINT_ORDER_LIST, b);
            }

            @Override
            public void onFailure(Exception e, BaseBean<PrintOrders> printOrdersBaseBean) {
                BaseBean<List<PrintOrders.ReturnDataBean>> b = new BaseBean<>();
                List<PrintOrders.ReturnDataBean> data = printOrdersBaseBean.getReturnData().getReturnData();
                b.setReturnCode(printOrdersBaseBean.getReturnCode());
                b.setReturnMsg(printOrdersBaseBean.getReturnMsg());
                b.setReturnData(data);
                b.setReturnTotalRecords(printOrdersBaseBean.getReturnTotalRecords());
                listener.onFailed(HttpRequestType.GET_PRINT_ORDER_LIST, e, b);
            }
        });
    }

    public static void updateTransferFee(Context context, final HttpRequestListener listener, String order,
                                         String transferAmount, String transferCompany, String transferNo) {
        RequestParams requestParams = HttpParams.setUpdateTransferFeeParams(order, transferAmount, transferCompany, transferNo);
        Logger.d(requestParams.getParams());
        HttpNetUtils.postNetData(context, HttpUrl.UPDATE_TRANSFER_FEE, requestParams, new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {

            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.UPDATE_TRANSFER_FEE, objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.UPDATE_TRANSFER_FEE, e, objectBaseBean);
            }

        });
    }


    public static void uploadSignPic(Context context, final HttpRequestListener listener, String order, List<String> pics) {

        UploadSignPicParams uploadSignPicParams = new UploadSignPicParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        uploadSignPicParams.setUserId(userInfo.getUserId());
        uploadSignPicParams.setBranchCode(userInfo.getBranchCode());
        uploadSignPicParams.setCompanyCode(userInfo.getCompanyCode());
        uploadSignPicParams.setWaybillNo(order);
        uploadSignPicParams.setImages(pics);
        uploadSignPicParams.setSign(userInfo.getSign());
        uploadSignPicParams.setToken(userInfo.getToken());
        
        HttpNetUtils.postJsonStringNetData(context, HttpUrl.UPLOAD_SIGN_PIC, sGson.toJson(uploadSignPicParams), new HttpNetUtils.BaseRequestListener<BaseBean<UploadImgResult>>() {
            @Override
            public void onSucces(BaseBean<UploadImgResult> objectBaseBean) {
                listener.onSuccess(HttpRequestType.UPLOAD_SIGN_PIC,objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<UploadImgResult> objectBaseBean) {
               listener.onFailed(HttpRequestType.UPLOAD_SIGN_PIC,e,objectBaseBean);
            }
        });
    }

    public static void deleteSignPic(Context context, final HttpRequestListener listener, String order, List<String> pics){
        DeletePicParams deletePicParams = new DeletePicParams();
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        deletePicParams.setUserId(userInfo.getUserId());
        deletePicParams.setWaybillNo(order);
        deletePicParams.setCompanyCode(userInfo.getCompanyCode());
        deletePicParams.setImagesUrl(pics);
        deletePicParams.setSign(userInfo.getSign());
        deletePicParams.setToken(userInfo.getToken());
        HttpNetUtils.postJsonStringNetData(context, HttpUrl.DELETE_PIC, sGson.toJson(deletePicParams), new HttpNetUtils.BaseRequestListener<BaseBean<Object>>() {
            @Override
            public void onSucces(BaseBean<Object> objectBaseBean) {
                listener.onSuccess(HttpRequestType.DELETE_PIC,objectBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<Object> objectBaseBean) {
                listener.onFailed(HttpRequestType.DELETE_PIC,e,objectBaseBean);
            }
        });
    }


    public static void retrieveOrderPics(Context context, final HttpRequestListener listener, final String order){
        HttpNetUtils.getNetData(context, HttpUrl.RETRIEVE_ORDER_PIC, HttpParams.setRetrieveOrderPicParams(order), new HttpNetUtils.BaseRequestListener<BaseBean<OrderPics>>() {

            @Override
            public void onSucces(BaseBean<OrderPics> orderPicsBaseBean) {
                listener.onSuccess(HttpRequestType.GET_ORDER_PIC,orderPicsBaseBean);
            }

            @Override
            public void onFailure(Exception e, BaseBean<OrderPics> orderPicsBaseBean) {
                listener.onFailed(HttpRequestType.GET_ORDER_PIC,e,orderPicsBaseBean);
            }

        });
    }

}
