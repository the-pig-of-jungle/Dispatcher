package com.liinji.ppgdeliver.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.LoginParams;
import com.liinji.ppgdeliver.bean.UserInfo;
import com.liinji.ppgdeliver.configure.AppConstants;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.AESEncryptUtil;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.tools.StringTools;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.orhanobut.logger.Logger;

import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/3/15.
 */
public class LoginActivity extends BaseLightStatusBarActivity {

    @BindView(R.id.name_clear)
    ImageView mNameClear;
    @BindView(R.id.pwd_clear)
    ImageView mPwdClear;
    private Runnable mSetAliasRunnable;

    @BindView(R.id.edt_name)
    EditText mEdtName;
    @BindView(R.id.edt_pwd)
    EditText mEdtPwd;
    @BindView(R.id.error_tip)
    TextView mErrorTip;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected int returnContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar.setVisibility(View.GONE);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
       mEdtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!hasFocus){
                   mNameClear.setVisibility(View.GONE);
               }else if (!TextUtils.isEmpty(mEdtName.getText())){
                   mNameClear.setVisibility(View.VISIBLE);
               }
           }
       });
        mEdtPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    mPwdClear.setVisibility(View.GONE);
                }else if (!TextUtils.isEmpty(mEdtPwd.getText())){
                    mPwdClear.setVisibility(View.VISIBLE);
                }
            }
        });
        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNameClear.setVisibility(TextUtils.isEmpty(s) ? View.GONE : View.VISIBLE);
            }
        });

        mEdtPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPwdClear.setVisibility(TextUtils.isEmpty(s) ? View.GONE : View.VISIBLE);
            }
        });
        mSetAliasRunnable = new Runnable() {
            @Override
            public void run() {
                final UserInfo userInfo = SharePrefUtils.getUserInfo();
                JPushInterface.setAlias(MyApplication.sContext, userInfo.getUserId(), new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        switch (i) {
                            case 0:
                                SharePrefUtils.storeJPushClientAlias(userInfo.getUserId());
                                break;
                            case 6002:
                                mErrorTip.postDelayed(mSetAliasRunnable, 60 * 1000);
                                break;
                        }

                    }
                });
            }
        };
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        Logger.json(HttpTools.sGson.toJson(bean));
        switch (type) {
            case HttpRequestType.LOGIN_REQUEST:
                UserInfo userInfo = (UserInfo) bean.getReturnData();
                SharePrefUtils.storeUserInfo(userInfo);
                SharePrefUtils.storeJPushClientAlias(userInfo.getUserId());
                JPushInterface.setAlias(this, userInfo.getUserId(), new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {

                    }
                });
                Logger.t("配送员个人信息");
                Logger.json(JsonUtils.jsonStr(SharePrefUtils.getUserInfo()));
                SmartSnackbar.get(this).show("登录成功");
                IntentUtils.startActivityDelayAndFinish(this, HomeActivity.class, 1000);
        }
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
        mErrorTip.setText("");
        if (MyApplication.sDebugMode) {
            IntentUtils.startActivity(this, HomeActivity.class);
        } else {
            if (inputCheck(StringTools.getEdittextString(mEdtName), StringTools.getEdittextString(mEdtPwd))) {
                LoginParams loginParams = new LoginParams();
                loginParams.setLoginAccount(StringTools.getEdittextString(mEdtName));
                loginParams.setPassword(AESEncryptUtil.getInstance(AppConstants.SECRET_KEY).encrypt(StringTools.getEdittextString(mEdtPwd)));
                loginParams.setUserType("3");
                HttpTools.login(this, loginParams, this);
            }
        }


    }

    private boolean inputCheck(String name, String pwd) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            mErrorTip.setText("账号或者密码不可为空！");
            return false;
        }
        return true;
    }


    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }



    @OnClick({R.id.name_clear, R.id.pwd_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name_clear:
                mEdtName.setText("");
                break;
            case R.id.pwd_clear:
                mEdtPwd.setText("");
                break;
        }
    }
}
