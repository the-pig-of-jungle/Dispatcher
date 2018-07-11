package com.liinji.ppgdeliver.manager;

import com.liinji.ppgdeliver.bean.DaoMaster;
import com.liinji.ppgdeliver.bean.DaoSession;
import com.liinji.ppgdeliver.configure.MyApplication;

/**
 * Created by 朱志强 on 2017/7/25.
 */

public class BuddyDaoManager {

    private static BuddyDaoManager sInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public BuddyDaoManager() {
        if (null == sInstance) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.sContext, "Buddy.db", null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static BuddyDaoManager getInstance() {
        if (null == sInstance) {
            synchronized (GreenDaoManager.class) {
                if (null == sInstance) {
                    sInstance = new BuddyDaoManager();
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
