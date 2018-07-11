package com.liinji.ppgdeliver.tools;

import android.app.Activity;
import android.view.View;

import com.liinji.ppgdeliver.R;
import com.tapadoo.alerter.Alerter;
import com.tapadoo.alerter.OnHideAlertListener;
import com.tapadoo.alerter.OnShowAlertListener;

/**
 * Created by ShadowMoon on 2017/4/24.
 */
public class AlertTools {



    private static Alerter getAlerter(Activity activity, String title, String text) {

        return Alerter.create(activity).setBackgroundColorRes(R.color.colorPrimary).setDuration(3000).enableSwipeToDismiss().enableProgress(true).setTitle(title).setText(text);
    }

    public static void showAlert(Activity activity, String title, String text) {
        getAlerter(activity, title, text).show();
    }

    public static void showAlert(Activity activity, String title, String text, OnShowAlertListener onShowAlertListener, OnHideAlertListener onHideAlertListener) {
        getAlerter(activity, title, text).setOnShowListener(onShowAlertListener).setOnHideListener(onHideAlertListener).show();
    }

    public static void showAlert(Activity activity, String title, String text, View.OnClickListener onClickListener) {
        getAlerter(activity, title, text).setOnClickListener(onClickListener).show();
    }

    public static void showAlert(Activity activity, String title, String text, long duration) {
        getAlerter(activity, title, text).setDuration(duration).show();
    }

    public static void showAlert(Activity activity, String text) {
        getAlerter(activity, null, text).show();
    }

    public static void hide() {
        Alerter.hide();
    }

    public static boolean isShowing() {
        return Alerter.isShowing();
    }
}
