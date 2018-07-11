package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.StringTools;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ShadowMoon on 2017/2/15.
 */
public class ModifyPswActivity extends BaseLightStatusBarActivity {
    @BindView(R.id.oldPwd)
    EditText oldPwd;
    @BindView(R.id.newPwd)
    EditText newPwd;
    @BindView(R.id.change)
    Button change;
    @BindView(R.id.newTwoPwd)
    EditText mNewTwoPwd;
    @BindView(R.id.newPwdInput)
    TextInputLayout mNewPwdInput;
    @BindView(R.id.newTwoPwdInput)
    TextInputLayout mNewTwoPwdInput;
    private String mOldPwd;
    private String mNewPwd;

    @Override
    protected int returnContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        return R.layout.activity_modifypsw;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    protected void initToolbarStyle() {
        toolbar_title.setText("修改密码");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
    }


    @OnClick(R.id.change)
    public void onClick() {


        String twoPwd = StringTools.getEdittextString(mNewTwoPwd);
        String onePwd = StringTools.getEdittextString(newPwd);
        String mOldPwd = StringTools.getEdittextString(oldPwd);

        if (TextUtils.isEmpty(mOldPwd)) {
          SmartSnackbar.get(this).show("旧密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(onePwd)) {
            SmartSnackbar.get(this).show("新密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(twoPwd)) {
            SmartSnackbar.get(this).show("请在输一遍新密码！");
            return;
        }

        if ((onePwd.length() < 6 || onePwd.length() > 15) || (twoPwd.length() < 6 || twoPwd.length() > 15)) {
            SmartSnackbar.get(this).show("密码长度错误，6~15位之间");
            return;
        }

        if (!StringTools.checkPwd(StringTools.getEdittextString(newPwd))) {
            SmartSnackbar.get(this).show("密码格式错误");
            return;
        }

        if (!TextUtils.equals(onePwd, twoPwd)) {
            SmartSnackbar.get(this).show("两次输入的密码不一致，请重新输入！");
            newPwd.setText("");
            mNewTwoPwd.setText("");
            return;
        }

        changePassword(newPwd.getText().toString().trim(), oldPwd.getText().toString().trim());
    }

    private void changePassword(String newPwd, String oldPwd) {
        //隐藏键盘
        hideKeyBoard();
        HttpTools.changePassword(this, oldPwd, newPwd, this);
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {

        if (type == HttpRequestType.CHANGE_PASSWORD) {
            //密码修改成功
            SmartSnackbar.get(this).show("密码修改成功！");


            //延迟一秒跳转到主页
            CountDownTimer timer = new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    NavUtils.navigateUpTo(ModifyPswActivity.this, new Intent(ModifyPswActivity.this, LoginActivity.class));
                }
            };

            timer.start();
        }

    }


}
