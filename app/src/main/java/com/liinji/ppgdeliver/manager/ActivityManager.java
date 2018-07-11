package com.liinji.ppgdeliver.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * activity管理器
 *
 * @author Yueyingsk
 */
public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getSingleManager() {
        if (instance == null) {
            instance = new ActivityManager();
            activityStack = new Stack<>();
        }
        return instance;
    }

    /**
     * 退出栈顶Activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            // 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activity.finish();
            if (activityStack.contains(activity)) {
                activityStack.remove(activity);
            }
            activity = null;
        }
    }

    /**
     * 获得当前栈顶Activity
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack == null){
            return null;
        }
        if (activityStack!= null && !activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {

        activityStack.add(activity);
    }

    /**
     * 退出栈中所有Activity并保留cls
     *
     * @param cls
     */
    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 退出所有activity
     */
    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 获取activity队列
     *
     * @return
     */
    public Stack<Activity> getActivityStack() {
        return activityStack;
    }
}
