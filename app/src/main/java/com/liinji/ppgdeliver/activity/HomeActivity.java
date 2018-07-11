package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.coder.zzq.waybillscannerlib.WaybillScanner;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.liinji.ppgdeliver.BuildConfig;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.SearchedOrderAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.HomeOrderNumber;
import com.liinji.ppgdeliver.bean.MsgOutline;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.SearchedOrder;
import com.liinji.ppgdeliver.bean.UserInfo;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.ApkDownloadTool;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * Created by Administrator on 2017/3/15.
 */
public class HomeActivity extends BaseDarkStatusBarActivity {
    public static final int REQ_CODE_TO_SCAN = 0x001;
    private static final int REQ_TO_PERSONAL_PANE = 0x002;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.edt_search)
    AutoCompleteTextView mEdtSearch;
    @BindView(R.id.btn_scan)
    ImageButton mBtnScan;
    @BindView(R.id.banner)
    SliderLayout mBanner;
    @BindView(R.id.bubble)
    ImageView mBubble;
    @BindView(R.id.branch)
    TextView branch;
    @BindView(R.id.waited_item)
    LinearLayout mWaitedItem;
    @BindView(R.id.problem_item)
    LinearLayout mProblemItem;
    @BindView(R.id.payback_item)
    LinearLayout mPaybackItem;
    @BindView(R.id.clear_item)
    LinearLayout mClearItem;
    @BindView(R.id.complete_item)
    LinearLayout mCompleteItem;
    @BindView(R.id.waited_badge)
    TextView mWaitedBadge;
    @BindView(R.id.payback_badge)
    TextView mPaybackBadge;
    @BindView(R.id.pos)
    TextView mPos;
    @BindView(R.id.searched_list)
    ListView mSearchedList;
    @BindView(R.id.clear)
    ImageView mClear;

    @Override
    protected int returnContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initToolbarStyle() {
        JPushInterface.resumePush(MyApplication.sContext);
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        toolbar.setNavigationIcon(null);
        mPos.setText(userInfo.getBranchName());
        toolbar_title.setText("配送员");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        UserInfo userInfo = SharePrefUtils.getUserInfo();
        JPushInterface.setAlias(this, userInfo.getUserId(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Logger.d("状态码" + i);
            }
        });
        int[] banners = {
                R.drawable.banner_01,
                R.drawable.banner_02,
                R.drawable.banner_03
        };
        for (int index = 0; index < banners.length; index++) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            textSliderView
                    .image(banners[index]);

            mBanner.addSlider(textSliderView);
        }
        mBanner.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));


        mBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivityForResult(HomeActivity.this, PersonalActivity.class, REQ_TO_PERSONAL_PANE);
            }
        });

        mSearchedList.setAdapter(new SearchedOrderAdapter());
        mSearchedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEdtSearch.setText(((SearchedOrderAdapter) parent.getAdapter()).getItem(position).getWaybillNo());
                mSearchedList.setVisibility(View.GONE);
                MyApplication.sCancelNetTip = true;
                mOrderStr = mEdtSearch.getText().toString().trim();
                HttpTools.isMyOrder(HomeActivity.this, mOrderStr, HomeActivity.this);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        HttpTools.setWaitDebitMark(this, this);
        if (!BuildConfig.IS_PRE_RELEASE){

            HttpTools.versionUpdate(this, this);

        }

        HttpTools.hasUnreadMsg(this, this);
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        switch (type) {
            case HttpRequestType.EEBIT_MARK:
                HomeOrderNumber homeOrderNumber = (HomeOrderNumber) bean.getReturnData();
                if (homeOrderNumber.getSendLoadNumber() == 0) {
                    mPaybackBadge.setVisibility(View.INVISIBLE);
                } else {
                    mPaybackBadge.setVisibility(View.VISIBLE);
                    mPaybackBadge.setText(homeOrderNumber.getSendLoadNumber() + "");
                }

                if (homeOrderNumber.getWaitForNumber() == 0) {
                    mWaitedBadge.setVisibility(View.INVISIBLE);
                } else {
                    mWaitedBadge.setVisibility(View.VISIBLE);

                    mWaitedBadge.setText(homeOrderNumber.getWaitForNumber() + "");
                }

                if (homeOrderNumber.getIsForced() == 1){
                    finish();
                    IntentUtils.startActivity(this,LoginActivity.class);
                }

                break;
            case HttpRequestType.IS_MY_ORDER:
                MyApplication.sCancelNetTip = false;
                HttpTools.orderDetail(this, mOrderStr, this);
                break;
            case HttpRequestType.ORDER_DETAIL:
                int orderStatus = ((OrderDetail) bean.getReturnData()).getWaybillInfo().getCorWaybillStatus();
                Logger.d(orderStatus);
                Class clazz = null;
                switch (orderStatus) {
                    case 1:
                        if (((OrderDetail) bean.getReturnData()).getWaybillInfo().getIsReturn() == 1) {
                            clazz = CompleteDetailActivity.class;
                        } else {
                            clazz = WaitSendDetailActivity.class;
                        }

                        break;
                    case 2:
                        clazz = DaishouDebitDetailActivity.class;
                        break;
                    case 3:
                        clazz = CompleteDetailActivity.class;
                        break;
                }
                Intent intent = new Intent(this, clazz);
                intent.putExtra("order", mOrderStr);
                intent.putExtra("order_id", ((OrderDetail) bean.getReturnData()).getWaybillInfo().getWaybillId());

                intent.putExtra(WaitToSentActivity.EXTRA_IS_SEND_AND_LOAD, ((OrderDetail) bean.getReturnData()).getWaybillInfo().getIsSendAndLoad());
                startActivity(intent);
                break;
            case HttpRequestType.VERSION_UPDATE:
                ApkDownloadTool.getInstance().initCheckUpdate(this, bean);
                break;
            case HttpRequestType.HAS_UNREAD_MSG:
                int drawableRes = (((List<MsgOutline>) bean.getReturnData()).isEmpty() || ((List<MsgOutline>) bean.getReturnData()).get(0).getUnReadCount() == 0) ? R.drawable.personal : R.drawable.personal_info;
                mBubble.setImageResource(drawableRes);
                break;

            case HttpRequestType.GET_SEARCHED_LIST:
                List<SearchedOrder> searchedOrders = (List<SearchedOrder>) bean.getReturnData();
                if (!searchedOrders.isEmpty() && !searchedOrders.get(0).getWaybillNo().endsWith(mEdtSearch.getText().toString().trim())) {
                    return;
                }

                ((SearchedOrderAdapter) mSearchedList.getAdapter()).resetData(searchedOrders);
                MyApplication.sCancelNetTip = false;
                break;

        }
    }


    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        MyApplication.sCancelNetTip = false;
        switch (type) {
            case HttpRequestType.IS_MY_ORDER:
                Intent intent = new Intent(this, OrderTraceActivity.class);
                intent.putExtra("order", mOrderStr);
                startActivityForResult(intent, 0x852);
                break;
            case HttpRequestType.HAS_UNREAD_MSG:
                mBubble.setImageResource(R.drawable.message_no);
                break;

        }
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mSearchedList.setVisibility(View.VISIBLE);
                ((SearchedOrderAdapter) mSearchedList.getAdapter()).clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mClear.setVisibility(View.INVISIBLE);
                } else {
                    mClear.setVisibility(View.VISIBLE);
                }
                final String text = s.toString();

                if (s.length() >= 6) {
                    toolbar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MyApplication.sCancelNetTip = true;
                            HttpTools.getSearchedList(HomeActivity.this, HomeActivity.this, text);
                        }
                    }, 600);

                } else {
                    ((SearchedOrderAdapter) mSearchedList.getAdapter()).clear();
                }
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtSearch.setText("");
            }
        });
    }


    private String mKeywords = "";


    private long mLastBackTime;



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mLastBackTime > 2000 || mLastBackTime == 0) {
                SmartToast.show("再按一次退出程序");
                mLastBackTime = System.currentTimeMillis();
                return true;
            }
        }
        SmartToast.dismiss();
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.btn_scan, R.id.btn_search})
    public void onClick(View btn) {
        switch (btn.getId()) {
            case R.id.btn_scan:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, REQ_CODE_TO_SCAN);
                break;
            case R.id.btn_search:
                if (TextUtils.isEmpty(mEdtSearch.getText().toString().trim())){
                    SmartToast.showAtTop("请输入运单信息！");
                    return;
                }
                MyApplication.sCancelNetTip = true;
                mOrderStr = mEdtSearch.getText().toString().trim();
                HttpTools.isMyOrder(this, mOrderStr, this);
                break;
        }

    }

    String mOrderStr;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQ_CODE_TO_SCAN && data != null) {
            Bundle bundle = data.getExtras();
            switch (bundle.getInt(CodeUtils.RESULT_TYPE)) {
                case CodeUtils.RESULT_SUCCESS:
                    String orderStr = Utils.trimOrder(bundle.getString(CodeUtils.RESULT_STRING));
                    mOrderStr = orderStr;
                    mEdtSearch.setText(orderStr);
                    HttpTools.isMyOrder(this, orderStr, this);
                    break;
                case CodeUtils.RESULT_FAILED:
                    SmartSnackbar.get(this).show("扫面失败");
                    break;
            }
        }

        if (resultCode == RESULT_OK && requestCode == REQ_TO_PERSONAL_PANE && data != null) {

            int unread = data.getIntExtra("unread_num", 0);
            Logger.d("unread" + unread);
            mBubble.setImageResource(unread == 0 ? R.drawable.personal : R.drawable.personal_info);
        }

        if (resultCode == RESULT_OK && requestCode == 0x852) {
            mEdtSearch.setText("");
        }
    }

    public ImageView getmBubble() {
        return mBubble;
    }


    @OnClick(R.id.waited_item)
    public void onMWaitedItemClicked() {
        IntentUtils.startActivity(this, WaitToSentActivity.class);
    }

    @OnClick(R.id.problem_item)
    public void onMProblemItemClicked() {
        IntentUtils.startActivity(this, ProblemCopyActivty.class);
    }

    @OnClick(R.id.payback_item)
    public void onMPaybackItemClicked() {
        IntentUtils.startActivity(this, PaybackActivity.class);
    }

    @OnClick(R.id.clear_item)
    public void onMClearItemClicked() {
        IntentUtils.startActivity(this, SettleActivity.class);
    }

    @OnClick(R.id.complete_item)
    public void onMCompleteItemClicked() {
        WaybillScanner.get(this)
                .runningEnvironment(1)
                .deviceType(WaybillScanner.DEVICE_PHONE)
                .operateType(WaybillScanner.OPT_SCAN_LOAD)
                .userInfo(com.coder.zzq.waybillscannerlib.bean.UserInfo.create().userId("58").branchCode("2001").companyCode("002"))
                .toScan();
    }

}
