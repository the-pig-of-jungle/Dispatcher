package com.liinji.ppgdeliver.http;


import com.liinji.ppgdeliver.configure.AppConstants;

/**
 * Created by Lee on 2015/9/8.
 */
public class HttpUrlEnvironment {

    /**
     * 测试地址
     */
    public static final String TEST_HOST = "http://10.10.1.4:";
    public static final String TEST_PORT = "24005/api/";


    /**
     * 正式(正式服务器两个服务器，所以分两个地址)
     */
    public static final String RELEASE_HOST = "http://";
    public static final String RELEASE_PORT_20 = "us.56wmm.com";
    public static final String RELEASE_PORT_02 = "splus.56wmm.com";


    /**
     * 获取Port(根据版本环境返回不同的Port)
     *
     * @return
     */
    public static String getPort() {
        //正式地址
        if (AppConstants.VersionEnvironment.NOW_ENVIROMENT.equals(AppConstants.VersionEnvironment.RELEASE)) {
            return RELEASE_PORT_20;
        }
        //测试地址
        else if (AppConstants.VersionEnvironment.NOW_ENVIROMENT.equals(AppConstants.VersionEnvironment.TEST)) {
            return TEST_PORT;
        }
        return TEST_PORT;
    }

    /**
     * 获取Host(根据版本环境返回不同的HOST)
     *
     * @return
     */
    public static String getHost() {
        //正式地址
        if (AppConstants.VersionEnvironment.NOW_ENVIROMENT.equals(AppConstants.VersionEnvironment.RELEASE)) {
            return RELEASE_HOST;
        }
        //测试地址
        else if (AppConstants.VersionEnvironment.NOW_ENVIROMENT.equals(AppConstants.VersionEnvironment.TEST)) {
            return TEST_HOST;
        }
        return TEST_HOST;
    }
}
