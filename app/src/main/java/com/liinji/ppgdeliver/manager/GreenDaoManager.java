package com.liinji.ppgdeliver.manager;


import com.liinji.ppgdeliver.bean.DaoMaster;
import com.liinji.ppgdeliver.bean.DaoSession;
import com.liinji.ppgdeliver.configure.MyApplication;

/**
 * Created by Administrator on 2017/6/12.
 */
public class GreenDaoManager {

    private static GreenDaoManager sInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public GreenDaoManager() {
        if (null == sInstance) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.sContext, "Branch.db", null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (null == sInstance) {
            synchronized (GreenDaoManager.class) {
                if (null == sInstance) {
                    sInstance = new GreenDaoManager();
                }
            }
        }
        return sInstance;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
