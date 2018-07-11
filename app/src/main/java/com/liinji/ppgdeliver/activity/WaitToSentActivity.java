package com.liinji.ppgdeliver.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.OrderToSendAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.DebitConfig;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.PayEvent;
import com.liinji.ppgdeliver.bean.PrintCompanyInfo;
import com.liinji.ppgdeliver.bean.QuickDispatchResult;
import com.liinji.ppgdeliver.bean.UserInfo;
import com.liinji.ppgdeliver.bean.WaitedOrder;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.liinji.ppgdeliver.activity.HomeActivity.REQ_CODE_TO_SCAN;
import static com.liinji.ppgdeliver.bean.PayEvent.PAY_CANCELED;
import static com.liinji.ppgdeliver.bean.PayEvent.PAY_SUCCESSFUL;

/**
 * Created by 朱志强 on 2017/8/22.
 */

public class WaitToSentActivity extends BaseRecycleviewActivity implements OfflinePay {

    public static final String EXTRA_WAIT_SEND_DETAIL_JSON = "order";
    public static final String EXTRA_REDUCE_AMOUNT = "reduce_amount";
    public static final String OP_MODE = "op_mode";
    public static final String RETURN_REDUCE = "return_reduce";
    public static final String EXTRA_IS_SEND_AND_LOAD = "is_send_and_load";
    private static final int REQ_CODE_CHOOSE = 0X456;

    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    @BindView(R.id.edit)
    TextView mEdit;
    @BindView(R.id.dispatch)
    Button mDispatch;
    @BindView(R.id.home_et)
    EditText mHomeEt;
    @BindView(R.id.search_order)
    Button mSearchOrder;
    @BindView(R.id.clear)
    ImageView mClear;
    @BindView(R.id.btn_scan)
    ImageButton mBtnScan;
    private boolean mNotScroll;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNotScroll) {
                mToTopFb.setVisibility(View.GONE);
            }
        }
    };


    @Override
    protected void initRestData() {
        HttpTools.judgeIsDefaultDispatcher(this, this);
    }

    private PopupWindow mMoreMark;

    public PopupWindow getMoreMark() {
        if (mMoreMark == null) {
            View content = LayoutInflater.from(this).inflate(R.layout.more_op, null);
            int height = mIsDefaultSender ? WindowManager.LayoutParams.WRAP_CONTENT : Utils.dp2px(64);
            mMoreMark = new PopupWindow(content, (int) Utils.dp2px(this, 200), height);
            mMoreMark.setOutsideTouchable(true);
            content.findViewById(R.id.item_print).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMoreMark.dismiss();
                    startActivity(new Intent(WaitToSentActivity.this, BatchPrintActivity.class));
                }
            });
            if (mIsDefaultSender) {
                content.findViewById(R.id.item_dispatch).setVisibility(View.VISIBLE);
                content.findViewById(R.id.item_quick_dispatch).setVisibility(View.VISIBLE);
                content.findViewById(R.id.first_separator).setVisibility(View.VISIBLE);
                content.findViewById(R.id.second_separator).setVisibility(View.VISIBLE);
            }
            content.findViewById(R.id.item_dispatch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMoreMark.dismiss();
                    startActivity(new Intent(WaitToSentActivity.this, DispatcherOrderListActivity.class));
                }
            });
            content.findViewById(R.id.item_quick_dispatch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMoreMark.dismiss();
                    HttpTools.quickDispatch(WaitToSentActivity.this, WaitToSentActivity.this, "");
                }
            });
        }

        return mMoreMark;
    }

    private View mHeadView;

    @Override
    protected void initRestView() {

        mHeadView = LayoutInflater.from(this).inflate(R.layout.sent_list_header, null);
        ((OrderToSendAdapter) recyclerView.getAdapter()).addHeaderView(mHeadView);

        mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mNotScroll = true;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    recyclerView.postDelayed(mRunnable, 1200);
                    return;
                }
                mNotScroll = false;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mToTopFb.setVisibility(View.VISIBLE);
            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                getMoreMark().showAsDropDown(toolbar, 0, (int) Utils.dp2px(MyApplication.sContext, -6), Gravity.RIGHT);
            }
        });

        mHomeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mClear.setVisibility(View.GONE);
                } else {
                    mClear.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    LinearLayoutManager mLinearLayoutManager;

    private DebitConfig mDebitConfig;

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        EventBus.getDefault().register(this);
        UserInfo userInfo = SharePrefUtils.getUserInfo();
        JPushInterface.setAlias(this, userInfo.getUserId(), new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Logger.d("状态码" + i);
            }
        });
        toolbar_title.setText("待送运单");
        HttpTools.getDebitConfig(this, this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_wait_to_sent;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new OrderToSendAdapter(new ArrayList<WaitedOrder.ReturnDataBean>());
    }

    private int mCurrentPos;
    public static final int OP_MODE_CHANGE = 5;
    public static final int OP_MODE_REMOVE = 6;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int mode = data.getIntExtra(OP_MODE, OP_MODE_REMOVE);
            float reduceAmount = data.getFloatExtra(RETURN_REDUCE, 0);
            switch (mode) {
                case OP_MODE_CHANGE:
                    float yunFeeReduce = data.getFloatExtra(WaitSendDetailActivity.EXTRA_YUNFEE_REDUCE_AMOUNT, 0);
                    float daishouReduce = data.getFloatExtra(WaitSendDetailActivity.EXTRA_DAISHOU_REDUCE_AMOUNT, 0);
                    boolean hasRegister = data.getBooleanExtra("has_register",false);
                    String signRemarks = data.getStringExtra(WaitSendDetailActivity.EXTRA_SIGN_REMARKS);
                    String orderNo = data.getStringExtra("order");
                    WaitedOrder.ReturnDataBean order = ((OrderToSendAdapter) recyclerView.getAdapter()).getItem(mCurrentPos);

                    Logger.d(order.getWaybillNo() + yunFeeReduce + daishouReduce);

                    if (yunFeeReduce != 0 && order.getWaybillNo().equals(orderNo)) {
                        Logger.d(order.getWaybillNo() + "/" + yunFeeReduce + "/" + daishouReduce);
                        order.setPickUpPayAmount(order.getPickUpPayAmount() - yunFeeReduce);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    if (daishouReduce != 0 && order.getWaybillNo().equals(orderNo)) {
                        order.setCollectionTradeCharges(order.getCollectionTradeCharges() - daishouReduce);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    if (hasRegister && order.getWaybillNo().equals(orderNo)){
                        order.setIsRegisteTransfer(1);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    break;
            }
        }

        if (requestCode == REQ_CODE_CHOOSE && resultCode == RESULT_OK && data != null) {
            boolean needRefresh = data.getBooleanExtra("need_refresh", false);
            if (needRefresh) {
                onRefresh();
            }
        }


        if (resultCode == RESULT_OK && requestCode == REQ_CODE_TO_SCAN && data != null) {
            Bundle bundle = data.getExtras();
            switch (bundle.getInt(CodeUtils.RESULT_TYPE)) {
                case CodeUtils.RESULT_SUCCESS:
                    String orderStr = Utils.trimOrder(bundle.getString(CodeUtils.RESULT_STRING));
                    mOrderStr = orderStr;
                    HttpTools.isMyOrder(this, orderStr, this);
                    break;
                case CodeUtils.RESULT_FAILED:
                    SmartSnackbar.get(this).show("扫面失败");
                    break;
            }
        }
    }

    private String mOrderStr = "";


    private String mKeywords = "";

    @Override
    protected void getRecyclerViewNetData() {
        cusswiperefreshlayout.setRefreshing(true);

        HttpTools.getSentOrderList(this, this, mKeywords, pageIndex, pagesize);

    }

    @Override
    protected void onBaseItemChildClick(BaseQuickAdapter baseQuickAdapter, final View view, int i) {
        super.onBaseItemChildClick(baseQuickAdapter, view, i);

        switch (view.getId()) {
            case R.id.see_detail:
                mCurrentPos = i;
                Intent intent = new Intent(this, WaitSendDetailActivity.class);
                intent.putExtra(EXTRA_WAIT_SEND_DETAIL_JSON, ((OrderToSendAdapter) baseQuickAdapter).getItem(i).getWaybillNo());
                startActivityForResult(intent, 1);
                break;
            case R.id.offline_pay:

                if (mDebitConfig == null) {
                    final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
                    sweetAlertDialog.setTitleText("提示")
                            .setConfirmText("确定")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }
                            })

                            .setContentText("获取该公司线下欠款模式失败，请刷新重试！");
                    sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                        }
                    });
                    sweetAlertDialog.show();
                    return;
                }


                mOrder = (WaitedOrder.ReturnDataBean) baseQuickAdapter.getItem(i);

                mCashPayPos = i;

                if (mOrder.getIsToEnd() == 1 && mOrder.getIsRegisteTransfer() == 0){
                    getTransferDialog().show();
                    return;
                }


                if (((TextView) view).getText().equals("线下支付")) {
                    getDialog().show();
                } else {
                    final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
                    sweetAlertDialog.setTitleText("提示")
                            .setConfirmText("确定")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    HttpTools.cashPay(WaitToSentActivity.this, mOrder.getWaybillNo(), mOrder.getReceiver(), "", 0, 0, WaitToSentActivity.this);
                                }
                            })
                            .setCancelText("取消")
                            .setContentText("无需支付费用，确定签收？");
                    sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                        }
                    });

                    sweetAlertDialog.show();
                }
                break;

        }
    }

    private WaitedOrder.ReturnDataBean mOrder;
    private AlertDialog mAlertDialog;
    private View mContent;
    private AlertDialog mSetTransferFeeDialog;
    private View mTransferFeeDialogContent;

    public AlertDialog getTransferDialog(){
        if (mSetTransferFeeDialog == null){
            mTransferFeeDialogContent = LayoutInflater.from(this).inflate(R.layout.set_transfer_fee,null);

            final float max = mOrder.getRebateWay() != 1 ? (mOrder.getBasicFee() - mOrder.getRebate()) : mOrder.getBasicFee();
            ((TextView) mTransferFeeDialogContent.findViewById(R.id.operation_tip)).setText("*该票中转费不得超过" + PrintInfo.sDecimalFormat.format(max) + "元");
            ((TextView) mTransferFeeDialogContent.findViewById(R.id.title)).setText("该单需先输入中转费");
            ((EditText) mTransferFeeDialogContent.findViewById(R.id.input_amount)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString().trim())){
                        mTransferFeeDialogContent.findViewById(R.id.confirmBtn).setEnabled(false);
                        return;
                    }

                    mTransferFeeDialogContent.findViewById(R.id.confirmBtn).setEnabled(Float.parseFloat(s.toString().trim()) <= max);
                }
            });

            mTransferFeeDialogContent.findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mNewTransferCompany = ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_company)).getText().toString().trim();
                    mNewTransferNo = ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_no)).getText().toString().trim();

                    if (mNewTransferCompany.length() > 50){
                        mNewTransferCompany = mNewTransferCompany.substring(0,50);
                    }

                    if (mNewTransferNo.length() > 50){
                        mNewTransferNo = mNewTransferNo.substring(0,50);
                    }

                    if (TextUtils.isEmpty(mNewTransferCompany) || TextUtils.isEmpty(mNewTransferNo)){
                        SmartToast.showAtTop("中转公司或中转运单号不可为空！");
                        return;
                    }

                    mSetTransferFeeDialog.dismiss();
                    mNewTransferCharge = ((EditText) mTransferFeeDialogContent.findViewById(R.id.input_amount)).getText().toString().trim();


                    HttpTools.updateTransferFee(WaitToSentActivity.this,WaitToSentActivity.this,mOrder.getWaybillNo(),mNewTransferCharge,mNewTransferCompany,mNewTransferNo);
                }
            });


            ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_company)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 50){
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.company_error)).setErrorEnabled(true);
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.company_error)).setError("公司名最多输入50字！");

                    }else {
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.company_error)).setErrorEnabled(false);
                    }

                }
            });


            ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_no)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 50){
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.transfer_no_error)).setErrorEnabled(true);
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.transfer_no_error)).setError("中转运单号最多输入50个数字！");
                    }else {
                        ((TextInputLayout) mTransferFeeDialogContent.findViewById(R.id.transfer_no_error)).setErrorEnabled(false);
                    }

                }
            });





            mSetTransferFeeDialog = new AlertDialog.Builder(this)
                    .setView(mTransferFeeDialogContent)
                    .create();
        }

        ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_company)).setText(mOrder.getTransferCompanyName());
        ((EditText) mTransferFeeDialogContent.findViewById(R.id.transfer_no)).setText(mOrder.getTransferNo());
        ((EditText) mTransferFeeDialogContent.findViewById(R.id.input_amount)).setText(PrintInfo.processFee(mOrder.getTransferFee()));
        return mSetTransferFeeDialog;
    }



    public AlertDialog getDialog() {
        if (mAlertDialog == null) {

            mContent = LayoutInflater.from(this).inflate(R.layout.cash_pay_dilog, null);
            mContent.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAlertDialog.dismiss();
                }
            });

            mAlertDialog = new AlertDialog.Builder(this)
                    .setView(mContent)
                    .create();
            mAlertDialog.setCanceledOnTouchOutside(false);

        }

        ((TextView) mContent.findViewById(R.id.deliver_fee)).setText(Utils.getCurrencyStr(mOrder.getPickUpPayAmount()));
        ((TextView) mContent.findViewById(R.id.daishou_fee)).setText(Utils.getCurrencyStr(mOrder.getCollectionTradeCharges()));
        ((TextView) mContent.findViewById(R.id.order_number)).setText(mOrder.getWaybillNo());
        final CheckBox daishou = (CheckBox) mContent.findViewById(R.id.daishou_fee_icon);
        final CheckBox yunfee = (CheckBox) mContent.findViewById(R.id.deliver_fee_icon);
        final TextView totalFee = (TextView) mContent.findViewById(R.id.total_fee);
        final View yunFeeLine = mContent.findViewById(R.id.yunfei_line);
        final View daishouLine = mContent.findViewById(R.id.daishou_line);
        DebitConfig debitConfig = mDebitConfig;
        totalFee.setText("合计：" + Utils.getCurrencyStr(mOrder.getPickUpPayAmount() + mOrder.getCollectionTradeCharges()));

        if (mOrder.getIsSendAndLoad() == 1) {
            process(daishou, yunfee, totalFee, yunFeeLine, daishouLine, debitConfig.getJJMode());
        } else {
            process(daishou, yunfee, totalFee, yunFeeLine, daishouLine, debitConfig.getNormalMode());
        }


        mContent.findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float all = Utils.currencyStrToNumber(((TextView) mContent.findViewById(R.id.total_fee)).getText().toString().trim().substring(3));
                Logger.d("总金额：" + all);
                int payWay = 0;
                if (mOrder.getIsSendAndLoad() == 1) {
                    switch (mDebitConfig.getJJMode()) {
                        case DebitConfig.MODE_0_0_0:
                            payWay = 0;
                            break;
                        case DebitConfig.MODE_0_0_1:
                            if (daishouLine.getVisibility() == View.VISIBLE) {
                                payWay = daishou.isChecked() ? 0 : 1;
                            } else {
                                payWay = 0;
                            }

                            break;
                        case DebitConfig.MODE_0_1_0:
                            if (yunFeeLine.getVisibility() == View.VISIBLE) {
                                payWay = yunfee.isChecked() ? 0 : 2;
                            } else {
                                payWay = 0;
                            }

                            break;
                        case DebitConfig.MODE_0_1_1:
                            if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE) {
                                if (yunfee.isChecked() && daishou.isChecked()) {
                                    payWay = 0;
                                }
                                ;
                                if (!yunfee.isChecked() && !daishou.isChecked()) {
                                    payWay = 3;
                                }

                                if (!yunfee.isChecked() && daishou.isChecked()) {
                                    payWay = 2;
                                }
                                if (yunfee.isChecked() && !daishou.isChecked()) {
                                    payWay = 1;
                                }
                            } else if (yunFeeLine.getVisibility() == View.GONE) {
                                if (daishou.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            } else {
                                if (yunfee.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            }

                            break;
                        case DebitConfig.MODE_1_1_1:
                            if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE) {
                                if (yunfee.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            } else if (yunFeeLine.getVisibility() == View.GONE) {
                                if (daishou.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            } else {
                                if (yunfee.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            }

                            break;
                    }
                } else {
                    switch (mDebitConfig.getNormalMode()) {
                        case DebitConfig.MODE_0_0_0:
                            payWay = 0;
                            break;
                        case DebitConfig.MODE_0_0_1:
                            if (daishouLine.getVisibility() == View.VISIBLE) {
                                payWay = daishou.isChecked() ? 0 : 1;
                            } else {
                                payWay = 0;
                            }

                            break;
                        case DebitConfig.MODE_0_1_0:
                            if (yunFeeLine.getVisibility() == View.VISIBLE) {
                                payWay = yunfee.isChecked() ? 0 : 2;
                            } else {
                                payWay = 0;
                            }

                            break;
                        case DebitConfig.MODE_0_1_1:
                            if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE) {
                                if (yunfee.isChecked() && daishou.isChecked()) {
                                    payWay = 0;
                                }
                                ;
                                if (!yunfee.isChecked() && !daishou.isChecked()) {
                                    payWay = 3;
                                }

                                if (!yunfee.isChecked() && daishou.isChecked()) {
                                    payWay = 2;
                                }
                                if (yunfee.isChecked() && !daishou.isChecked()) {
                                    payWay = 1;
                                }
                            } else if (yunFeeLine.getVisibility() == View.GONE) {
                                if (daishou.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            } else {
                                if (yunfee.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            }

                            break;
                        case DebitConfig.MODE_1_1_1:
                            if (yunFeeLine.getVisibility() == View.VISIBLE && daishouLine.getVisibility() == View.VISIBLE) {
                                if (yunfee.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            } else if (yunFeeLine.getVisibility() == View.GONE) {
                                if (daishou.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            } else {
                                if (yunfee.isChecked()) {
                                    payWay = 0;
                                } else {
                                    payWay = 3;
                                }
                            }

                            break;
                    }
                }
                HttpTools.cashPay(WaitToSentActivity.this, mOrder.getWaybillNo(), mOrder.getReceiver(), "", all, payWay, WaitToSentActivity.this);
                mAlertDialog.dismiss();
            }
        });
        daishou.setChecked(true);
        yunfee.setChecked(true);
        return mAlertDialog;
    }

    private void process(final CheckBox daishou, final CheckBox yunfee, final TextView totalFee, View yunFeeLine, View daishouLine, String mode) {
        yunFeeLine.setVisibility(mOrder.getPickUpPayAmount() == 0 ? View.GONE : View.VISIBLE);
        daishouLine.setVisibility(mOrder.getCollectionTradeCharges() == 0 ? View.GONE : View.VISIBLE);
        switch (mode) {
            case DebitConfig.MODE_1_1_1:
                yunfee.setEnabled(true);
                daishou.setEnabled(true);
                yunfee.setButtonDrawable(R.drawable.pay_select);
                daishou.setButtonDrawable(R.drawable.pay_select);
                yunfee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        daishou.setChecked(isChecked);
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? (mOrder.getCollectionTradeCharges() + mOrder.getPickUpPayAmount()) : 0));
                    }
                });
                daishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        yunfee.setChecked(isChecked);
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? (mOrder.getCollectionTradeCharges() + mOrder.getPickUpPayAmount()) : 0));
                    }
                });
                break;
            case DebitConfig.MODE_0_0_0:
                yunfee.setButtonDrawable(R.drawable.dsydxq_cancel);
                yunfee.setEnabled(false);
                daishou.setButtonDrawable(R.drawable.dsydxq_cancel);
                daishou.setEnabled(false);
                totalFee.setText("合计：" + Utils.getCurrencyStr(mOrder.getCollectionTradeCharges() + mOrder.getPickUpPayAmount()));
                break;
            case DebitConfig.MODE_0_0_1:
                yunfee.setButtonDrawable(R.drawable.dsydxq_cancel);
                yunfee.setEnabled(false);
                daishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? mOrder.getCollectionTradeCharges() + mOrder.getPickUpPayAmount() : mOrder.getPickUpPayAmount()));
                    }
                });
                daishou.setButtonDrawable(R.drawable.pay_select);
                daishou.setEnabled(true);
                break;
            case DebitConfig.MODE_0_1_0:
                yunfee.setEnabled(true);
                daishou.setEnabled(false);
                daishou.setButtonDrawable(R.drawable.dsydxq_cancel);
                yunfee.setButtonDrawable(R.drawable.pay_select);
                yunfee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        totalFee.setText("合计：" + Utils.getCurrencyStr(isChecked ? (mOrder.getCollectionTradeCharges() + mOrder.getPickUpPayAmount()) : mOrder.getCollectionTradeCharges()));
                    }
                });
                break;
            case DebitConfig.MODE_0_1_1:
                yunfee.setEnabled(true);
                daishou.setEnabled(true);
                yunfee.setButtonDrawable(R.drawable.pay_select);
                daishou.setButtonDrawable(R.drawable.pay_select);
                yunfee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            totalFee.setText("合计：" + Utils.getCurrencyStr(mOrder.getPickUpPayAmount() + (daishou.isChecked() ? mOrder.getCollectionTradeCharges() : 0)));
                        } else {
                            totalFee.setText("合计：" + Utils.getCurrencyStr(daishou.isChecked() ? mOrder.getCollectionTradeCharges() : 0));
                        }
                    }
                });

                daishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            totalFee.setText("合计：" + Utils.getCurrencyStr(mOrder.getCollectionTradeCharges() + (yunfee.isChecked() ? mOrder.getPickUpPayAmount() : 0)));
                        } else {
                            totalFee.setText("合计：" + Utils.getCurrencyStr(yunfee.isChecked() ? mOrder.getPickUpPayAmount() : 0));
                        }
                    }
                });
                break;
        }
    }


    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.GET_WAITED_ORDER_LIST;
    }


    private boolean mIsDefaultSender;

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);

        switch (type) {
            case HttpRequestType.GET_WAITED_ORDER_LIST:

                if (recyclerView.getAdapter().getItemCount() == 1) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }

                if (TextUtils.isEmpty(bean.getSumRecords())) {
                    mTotalRecords = 0;
                } else {
                    mTotalRecords = Integer.parseInt(bean.getSumRecords());
                }


                if (TextUtils.isEmpty(bean.getSumPieces().trim())) {
                    mTotalPieces = 0;
                } else {
                    mTotalPieces = Integer.parseInt(bean.getSumPieces().trim());
                }

                setOutline();
                cusswiperefreshlayout.setRefreshing(false);
                break;
            case HttpRequestType.IS_DEFAULT_DISPATCHER:
                mEdit.setVisibility(View.VISIBLE);
                mIsDefaultSender = ((Integer) bean.getReturnData()) == 1;
                break;
            case HttpRequestType.GET_PRINT_COMPANY_INFO:
                PrintCompanyInfo printCompanyInfo = (PrintCompanyInfo) bean.getReturnData();
                SharePrefUtils.setCompanyName(printCompanyInfo.getCompanyName());
                SharePrefUtils.setCompanyTel(printCompanyInfo.getCompanyTel());
                break;
            case HttpRequestType.QUICK_DISPATCH:
                QuickDispatchResult result = (QuickDispatchResult) bean.getReturnData();

                final int totalSucceed = result.getTotalSucceed();
                int totalQty = result.getTotalQty();
                int totalFail = result.getTotalFailed();

                final SweetAlertDialog sdialog = new SweetAlertDialog(this);
                sdialog.setTitleText("提示")
                        .setContentText(totalQty + " 票运单快速分配完成!\n\n" + totalSucceed + " 票成功，" + totalFail + " 票失败。\n")
                        .setConfirmText("确定")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        });
                sdialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                    }
                });
                sdialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (totalSucceed != 0) {
                            onRefresh();
                        }

                    }
                });
                sdialog.setCanceledOnTouchOutside(true);
                sdialog.show();

                break;
            case HttpRequestType.DEBIT_CONFIG:
                mDebitConfig = (DebitConfig) bean.getReturnData();
                break;
            case HttpRequestType.CASH_PAY:
                List<WaitedOrder.ReturnDataBean> orders = ((OrderToSendAdapter) recyclerView.getAdapter()).getData();
                WaitedOrder.ReturnDataBean order = orders.remove(mCashPayPos);
                recyclerView.getAdapter().notifyDataSetChanged();
                if (orders.size() <= 2) {
                    onRefresh();
                }
                mTotalRecords--;
                mTotalPieces -= order.getTotalPieces();
                setOutline();
                SmartToast.showInCenter("签收成功！");
                break;

            case HttpRequestType.IS_MY_ORDER:
                Logger.d("my成功！");
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
            case HttpRequestType.UPDATE_TRANSFER_FEE:
                mOrder.setTransferFee(Float.parseFloat(mNewTransferCharge));
                mOrder.setTransferCompanyName(mNewTransferCompany);
                mOrder.setTransferNo(mNewTransferNo);
                mOrder.setIsRegisteTransfer(1);
                SmartToast.showInCenter("中转费录入成功！");
                break;
        }

    }


    private String mNewTransferCharge;
    private String mNewTransferCompany;
    private String mNewTransferNo;

    private void setOutline() {

        SpannableString totalOrders = new SpannableString("总票数：" + mTotalRecords + " 票");
        totalOrders.setSpan(new StyleSpan(Typeface.BOLD), 4, totalOrders.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) mHeadView.findViewById(R.id.total_orders)).setText(totalOrders);
        SpannableString totalPieces = new SpannableString("总件数：" + mTotalPieces + " 件");
        totalPieces.setSpan(new StyleSpan(Typeface.BOLD), 4, totalPieces.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) mHeadView.findViewById(R.id.total_goods_pieces)).setText(totalPieces);
    }


    private int mCashPayPos;

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type) {
            case HttpRequestType.GET_WAITED_ORDER_LIST:
                mEmptyView.setVisibility(View.VISIBLE);
                cusswiperefreshlayout.setRefreshing(false);
                break;
            case HttpRequestType.IS_MY_ORDER:
                Logger.d("my失败！");
                Intent intent = new Intent(this, OrderTraceActivity.class);
                intent.putExtra("order", mOrderStr);
                startActivityForResult(intent, 0x852);
                break;
            case HttpRequestType.IS_DEFAULT_DISPATCHER:
                mEdit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mToTopFb.setVisibility(View.GONE);
    }


    @OnClick(R.id.to_top_fb)
    public void onClick() {
        recyclerView.scrollToPosition(0);
    }


    @OnClick({R.id.dispatch, R.id.search_order, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.search_order:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
                String phone = mHomeEt.getText().toString().trim();
                mKeywords = phone;
                onRefresh();
                break;
            case R.id.clear:
                mHomeEt.setText("");
                break;
        }

    }


    @Override
    public void onBackPressed() {
        if (mMoreMark != null && mMoreMark.isShowing()) {
            mMoreMark.dismiss();
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private int mTotalRecords;

    private int mTotalPieces;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivePayEvent(PayEvent payEvent) {
        Logger.d("是否调用？");
        switch (payEvent.getPayResult()) {
            case PAY_SUCCESSFUL:
                List<WaitedOrder.ReturnDataBean> orders = ((OrderToSendAdapter) recyclerView.getAdapter()).getData();
                WaitedOrder.ReturnDataBean order = orders.remove(mCurrentPos);
                if (order != null) {
                    mTotalRecords--;
                    mTotalPieces -= order.getTotalPieces();
                    setOutline();
                }

                recyclerView.getAdapter().notifyDataSetChanged();

                if (orders.size() <= 2) {
                    onRefresh();
                }
                break;
            case PAY_CANCELED:

                break;
        }
    }

    public DebitConfig getDebitConfig() {
        return mDebitConfig;
    }


    @OnClick({R.id.btn_scan})
    public void onClick(View btn) {
        switch (btn.getId()) {
            case R.id.btn_scan:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, REQ_CODE_TO_SCAN);
                break;
        }

    }
}
