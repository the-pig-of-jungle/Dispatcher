package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.CompleteOrderAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.CompleteOrder;
import com.liinji.ppgdeliver.bean.CompleteOutline;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.PrintCompanyInfo;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.DateTool;
import com.liinji.ppgdeliver.utils.DatePickerUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.liinji.ppgdeliver.activity.HomeActivity.REQ_CODE_TO_SCAN;

/**
 * Created by Administrator on 2017/3/16.
 */
public class OrderCompleteActivity extends BaseRecycleviewActivity implements View.OnClickListener {

    public static final String EXTRA_ORDER_DETAIL_JSON = "order";
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    @BindView(R.id.filter_mark)
    ImageView mFilterMark;
    @BindView(R.id.home_et)
    EditText mHomeEt;
    @BindView(R.id.search_order)
    Button mSearchOrder;
    @BindView(R.id.btn_scan)
    ImageButton mBtnScan;
    @BindView(R.id.clear)
    ImageView mClear;
    @BindView(R.id.empty_mark)
    TextView mEmptyMark;

    private boolean mNotScroll;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNotScroll) {
                mToTopFb.setVisibility(View.GONE);
            }
        }
    };


    private String mKeywords = "";


    @Override
    protected void initRestData() {
        mHomeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mClear.setVisibility(TextUtils.isEmpty(s.toString()) ? View.INVISIBLE : View.VISIBLE);
            }
        });
    }


    @Override
    protected void initRestView() {
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.outline_header, null);
        ((TextView) mHeaderView.findViewById(R.id.date)).setText("汇总报表" + DateTool.getDateStr(new Date(), "（yyyy.MM.dd）"));
        ((CompleteOrderAdapter) recyclerView.getAdapter()).addHeaderView(mHeaderView);
        HttpTools.getCompleteOutline(this, this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yyyy-MM-dd"), "");
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

        mBtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderCompleteActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQ_CODE_TO_SCAN);
            }
        });
    }

    private View mHeaderView;

    @Override
    protected void onBaseItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        super.onBaseItemChildClick(baseQuickAdapter, view, i);
        if (view.getId() == R.id.see_detail) {
            Intent intent = new Intent(this, CompleteDetailActivity.class);
            intent.putExtra(EXTRA_ORDER_DETAIL_JSON, ((CompleteOrderAdapter) baseQuickAdapter).getItem(i).getWaybillNo());
            intent.putExtra(WaitToSentActivity.EXTRA_IS_SEND_AND_LOAD, ((CompleteOrderAdapter) baseQuickAdapter).getItem(i).getIsSendAndLoad());
            startActivity(intent);
        }
    }


    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("已完成运单");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order_complete;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new CompleteOrderAdapter(new ArrayList<CompleteOrder.ReturnDataBean>());
    }


    @Override
    protected void getRecyclerViewNetData() {
        mKeywords = mHomeEt.getText().toString().trim();
        HttpTools.setOrderCompleteList(this, pageIndex + "", pagesize + "", this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yyyy-MM-dd"), mKeywords);
    }

    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.COMPLETE_ORDER_LIST;
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.COMPLETE_ORDER_LIST:
                if (recyclerView.getAdapter().getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }

                if (bean.getReturnTotalRecords().equals("0")){
                    mEmptyMark.setVisibility(View.VISIBLE);
                }else {
                    mEmptyMark.setVisibility(View.GONE);
                }

                break;
            case HttpRequestType.GET_COMPLETER_OUTLINE:
                CompleteOutline outline = (CompleteOutline) bean.getReturnData();
                ((TextView) mHeaderView.findViewById(R.id.gather_total)).setText("收款总计：" + outline.getTotalAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.cash_gather_total)).setText("现金合计：" + outline.getOfflineTotalAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.cash_gather_freight)).setText("收运费：" + outline.getOfflineFreightAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.cash_gather_collect)).setText("收代收：" + outline.getOfflinePaymentAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.online_gather_total)).setText("线上合计：" + outline.getOnlineTotalAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.online_gather_freight)).setText("收运费：" + outline.getOnlineFreightAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.online_gather_collect)).setText("收代收：" + outline.getOnlinePaymentAmount() + "元");

                ((TextView) mHeaderView.findViewById(R.id.debit_total)).setText("欠款总计：" + outline.getOweTotalAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.cash_debit_total)).setText("现金合计：" + outline.getOweOfflineTotalAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.cash_debit_freight)).setText("欠运费：" + outline.getOweOfflineFreightAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.cash_debit_collect)).setText("欠代收：" + outline.getOweOfflinePaymentAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.online_debit_total)).setText("线上合计：" + outline.getOweOnlineTotalAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.online_debit_freight)).setText("欠运费：" + outline.getOweOnlineFreightAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.online_debit_collect)).setText("欠代收：" + outline.getOweOnlinePaymentAmount() + "元");
                ((TextView) mHeaderView.findViewById(R.id.total_order)).setText("总票数：" + outline.getTotalNumber() + "票");
                ((TextView) mHeaderView.findViewById(R.id.return_order)).setText("退货票数：" + outline.getReturnNumber() + "票");
                if (mBeginDate.getTime() == mEndDate.getTime()) {
                    ((TextView) mHeaderView.findViewById(R.id.date)).setText("汇总报表" + DateTool.getDateStr(mBeginDate, "(yyyy.MM.dd)"));
                } else {
                    ((TextView) mHeaderView.findViewById(R.id.date)).setText("汇总报表" + DateTool.getDateStr(mBeginDate, "(yyyy.MM.dd-") + DateTool.getDateStr(mEndDate, "yyyy.MM.dd)"));
                }

                break;

            case HttpRequestType.GET_PRINT_COMPANY_INFO:
                PrintCompanyInfo printCompanyInfo = (PrintCompanyInfo) bean.getReturnData();
                SharePrefUtils.setCompanyTel(printCompanyInfo.getCompanyTel());
                SharePrefUtils.setCompanyName(printCompanyInfo.getCompanyName());
                break;
            case HttpRequestType.IS_MY_ORDER:
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

        }

    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type) {
            case HttpRequestType.IS_MY_ORDER:
                Logger.d("my失败！");
                Intent intent = new Intent(this, OrderTraceActivity.class);
                intent.putExtra("order", mOrderStr);
                startActivityForResult(intent, 0x852);
                break;
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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.filter_mark)
    public void onViewClicked() {
        getMoreMark().showAsDropDown(toolbar, 0, (int) Utils.dp2px(MyApplication.sContext, -6), Gravity.RIGHT);
    }

    private PopupWindow mMoreMark;

    public PopupWindow getMoreMark() {
        if (mMoreMark == null) {
            View content = LayoutInflater.from(this).inflate(R.layout.order_filter, null);
            mMoreMark = new PopupWindow(content, (int) Utils.dp2px(this, 200), Utils.dp2px(220));
            mMoreMark.setOutsideTouchable(true);
            content.findViewById(R.id.today).setOnClickListener(this);
            content.findViewById(R.id.three_days).setOnClickListener(this);
            content.findViewById(R.id.custom).setOnClickListener(this);
            content.findViewById(R.id.week).setOnClickListener(this);
            content.findViewById(R.id.month).setOnClickListener(this);
        }

        return mMoreMark;
    }

    private AlertDialog mDateSelector;
    private ViewFlipper mViewFlipper;

    private Date mBeginDate = new Date();
    private Date mEndDate = new Date();

    public AlertDialog getDateSelector() {

        mViewFlipper = new ViewFlipper(this);
        final View beginDate = LayoutInflater.from(this).inflate(R.layout.date_seletor, null);
        final DatePicker beginDatePicker = (DatePicker) beginDate.findViewById(R.id.date_picker);
        beginDatePicker.setMaxDate(new Date().getTime());
        DateTool.CALENDAR.setTime(new Date());
        DateTool.CALENDAR.add(Calendar.DAY_OF_MONTH, -365);
        beginDatePicker.setMinDate(DateTool.CALENDAR.getTime().getTime());
        View beginConfirm = beginDate.findViewById(R.id.confirm_btn);
        View beginCancel = beginDate.findViewById(R.id.cancel_btn);

        View endDate = LayoutInflater.from(this).inflate(R.layout.date_seletor, null);
        final DatePicker endDatePicker = (DatePicker) endDate.findViewById(R.id.date_picker);
        View endConfirm = endDate.findViewById(R.id.confirm_btn);
        View endCancel = endDate.findViewById(R.id.cancel_btn);
        endDatePicker.setMaxDate(new Date().getTime());
        ((TextView) endDate.findViewById(R.id.title)).setText("请选择结束日期");
        endConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTool.CALENDAR.set(endDatePicker.getYear(), endDatePicker.getMonth(), endDatePicker.getDayOfMonth());
                mEndDate = DateTool.CALENDAR.getTime();
                mDateSelector.dismiss();
                onRefresh();
            }
        });

        endCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDatePicker.setMinDate(1);
                Utils.showPrevious(mViewFlipper);
            }
        });
        beginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateTool.CALENDAR.set(beginDatePicker.getYear(), beginDatePicker.getMonth(), beginDatePicker.getDayOfMonth());
                mBeginDate = DateTool.CALENDAR.getTime();
                endDatePicker.setMinDate(mBeginDate.getTime());
                Utils.showNext(mViewFlipper);
            }
        });

        beginCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDateSelector.dismiss();
            }
        });


        mViewFlipper.addView(beginDate);
        mViewFlipper.addView(endDate);
        mViewFlipper.setInAnimation(this, R.anim.slide_in_right);
        mViewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        mDateSelector = new AlertDialog.Builder(this)
                .setView(mViewFlipper)
                .create();
        mDateSelector.setCanceledOnTouchOutside(false);
        NumberPicker numberPicker = new NumberPicker(this);

        DatePickerUtils.setDatePickerDividerColor(beginDatePicker);
        DatePickerUtils.setDatePickerDividerColor(endDatePicker);
        beginDatePicker.invalidate();
        endDatePicker.invalidate();
        return mDateSelector;
    }


    @Override
    public void onClick(View v) {
        getMoreMark().dismiss();

        switch (v.getId()) {
            case R.id.today:
                mBeginDate = new Date();
                mEndDate = new Date();
                onRefresh();
                break;
            case R.id.three_days:
                DateTool.CALENDAR.setTime(new Date());
                DateTool.CALENDAR.add(Calendar.DAY_OF_MONTH, -2);
                mBeginDate = DateTool.CALENDAR.getTime();
                mEndDate = new Date();
                onRefresh();
                break;
            case R.id.week:
                DateTool.CALENDAR.setTime(new Date());
                DateTool.CALENDAR.add(Calendar.DAY_OF_MONTH, -6);
                mBeginDate = DateTool.CALENDAR.getTime();
                mEndDate = new Date();
                onRefresh();
                break;
            case R.id.month:
                DateTool.CALENDAR.setTime(new Date());
                DateTool.CALENDAR.add(Calendar.DAY_OF_MONTH, -29);
                mBeginDate = DateTool.CALENDAR.getTime();
                mEndDate = new Date();
                onRefresh();
                break;
            case R.id.custom:
                getDateSelector().show();
                break;
        }
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        mKeywords = mHomeEt.getText().toString().trim();
        HttpTools.getCompleteOutline(this, this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yy-MM-dd"), mKeywords);
    }


    @Override
    public void onBackPressed() {
        if (getMoreMark().isShowing()) {
            getMoreMark().dismiss();
        } else {
            super.onBackPressed();
        }

    }


    @OnClick({R.id.search_order, R.id.btn_scan, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_order:
                onRefresh();
                break;
            case R.id.btn_scan:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, REQ_CODE_TO_SCAN);
                break;
            case R.id.clear:
                mHomeEt.setText("");
                break;
        }
    }

    private String mOrderStr;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
