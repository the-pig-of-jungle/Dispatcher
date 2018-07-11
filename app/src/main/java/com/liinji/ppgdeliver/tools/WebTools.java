package com.liinji.ppgdeliver.tools;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/**
 * Created by ShadowMoon on 2017/12/25.
 */

public class WebTools {
    /**
     * 清除cookies
     *
     * @param context
     */
    public static void clearWebViewCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
    }
}
