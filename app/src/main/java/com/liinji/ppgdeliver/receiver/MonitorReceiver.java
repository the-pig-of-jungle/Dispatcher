package com.liinji.ppgdeliver.receiver;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.HomeActivity;
import com.liinji.ppgdeliver.activity.IBaseActivity;
import com.liinji.ppgdeliver.activity.LoginActivity;
import com.liinji.ppgdeliver.activity.MessageDetailActivity;
import com.liinji.ppgdeliver.activity.OnlinePayActivity;
import com.liinji.ppgdeliver.activity.ProblemOrderDetailActivity;
import com.liinji.ppgdeliver.bean.GlobalMsg;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.manager.ActivityManager;
import com.liinji.ppgdeliver.tools.AlertTools;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Administrator on 2017/4/18.
 */
public class MonitorReceiver extends BroadcastReceiver {

    @Override
   public void onReceive(Context context, final Intent intent) {
        if (intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)) {
            Logger.d("收到自定义消息");
            Bundle bundle = intent.getExtras();
            String orderStr = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Logger.json(orderStr);

            GlobalMsg globalMsg = JsonUtils.json2obj(orderStr, GlobalMsg.class);

            if (globalMsg != null){
                if (!TextUtils.isEmpty(globalMsg.getWaybillNo())){
                    Activity activity = ActivityManager.getSingleManager().currentActivity();
                    if (activity != null && activity.getClass() == OnlinePayActivity.class){
                        OnlinePayActivity onlinePayActivity = (OnlinePayActivity) activity;
                        if (onlinePayActivity.getOrderStr().equals(globalMsg.getWaybillNo())){
                            onlinePayActivity.getOrderNumber().setText(globalMsg.getWaybillNo());
                            onlinePayActivity.getViewFlipper().showNext();
                            HttpTools.updateSignRemark(onlinePayActivity,onlinePayActivity,onlinePayActivity.getOrderId(),onlinePayActivity.getSignRemarks());
                        }
                    }
                }else if (!TextUtils.isEmpty(globalMsg.getIsForced()) && globalMsg.getIsForced().equals("1")){
                    final Activity activity = ActivityManager.getSingleManager().currentActivity();
                    if (activity != null && !(activity instanceof LoginActivity)){
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity)

                                .setTitleText("下线通知")

                                .setContentText("您的账号信息已修改，请尝试重新登录！\n")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        IntentUtils.startActivity(activity, LoginActivity.class);
                                        ActivityManager.getSingleManager().popAllActivityExceptOne(LoginActivity.class);
                                    }
                                }).setConfirmText("确定");
                        Utils.processDialog(sweetAlertDialog);
                        sweetAlertDialog.setCanceledOnTouchOutside(false);
                        sweetAlertDialog.setCancelable(false);
                        sweetAlertDialog.show();
                    }
                }
            }




        }else if(intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){

            Logger.d("收到通知...");
            Bundle b = intent.getExtras();
            String title = b.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = b.getString(JPushInterface.EXTRA_ALERT);
            String mIdStr = b.getString(JPushInterface.EXTRA_EXTRA);

            String mId = "";
            String problemResponse = "";
            String problemId = "";
            try {
                JSONObject jsonObject = new JSONObject(mIdStr);
                mId = jsonObject.getString("mId");
                problemResponse = jsonObject.getString("ProblemResponse");
                problemId = jsonObject.getString("ProblemId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int notificationId = b.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            final IBaseActivity activity = (IBaseActivity) ActivityManager.getSingleManager().currentActivity();

            if (activity != null && activity.isShowing()){
                Logger.d("在应用中");
                if (activity instanceof HomeActivity){
                    ((HomeActivity) activity).getmBubble().setImageResource(R.drawable.personal_info);
                }
                NotificationManager manager = (NotificationManager) MyApplication.sContext.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notificationId);
                final String finalMId = mId;
                Logger.d("消息id" + mId);
                Logger.d("我爱你" + problemResponse + "/" + problemId);
                if ("1".equals(problemResponse)){
                    final String finalProblemId = problemId;
                    if (!AlertTools.isShowing()){
                        AlertTools.showAlert(((Activity) activity), title, content, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertTools.hide();
                                Intent in = new Intent(((Context) activity),ProblemOrderDetailActivity.class);
                                in.putExtra("problem_id", finalProblemId);
                                ((Context) activity).startActivity(in);
                            }
                        });
                    }

                }else {

                    if (!AlertTools.isShowing()){
                        AlertTools.showAlert(((Activity) activity), title, content, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent in = new Intent(((Context) activity),MessageDetailActivity.class);
                                in.putExtra("msg_id", Integer.parseInt(finalMId));
                                ((Context) activity).startActivity(in);
                            }
                        });
                    }

                }

            }

        }else if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            Bundle bb = intent.getExtras();
            String jsonStr = bb.getString(JPushInterface.EXTRA_EXTRA);
            String mid = "";
            String problemRespone = "";
            String problemId = "";
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                mid = jsonObject.getString("mId");
                problemRespone = jsonObject.getString("ProblemResponse");
                problemId = jsonObject.getString("ProblemId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if ("1".equals(problemRespone)){
                Intent iii = new Intent(context,ProblemOrderDetailActivity.class);
                iii.putExtra("problem_id",problemId);
                iii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(iii);
            }else {
                Intent myIntent = new Intent(context,MessageDetailActivity.class);
                myIntent.putExtra("msg_id", Integer.parseInt(mid));
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }

        }
    }
}