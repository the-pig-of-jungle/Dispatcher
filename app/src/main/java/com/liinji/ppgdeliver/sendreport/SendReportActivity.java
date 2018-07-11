package com.liinji.ppgdeliver.sendreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.NetBuddy;
import com.liinji.ppgdeliver.bean.SendReportResult;
import com.liinji.ppgdeliver.bean.UserInfo;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestListener;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.sendreport.adapter.LvInfoAdapter;
import com.liinji.ppgdeliver.sendreport.adapter.LvNameAdapter;
import com.liinji.ppgdeliver.sendreport.view.LinkedHorizontalScrollView;
import com.liinji.ppgdeliver.sendreport.view.NoScrollHorizontalScrollView;
import com.liinji.ppgdeliver.tools.DateTool;
import com.liinji.ppgdeliver.tools.StatusBarTools;
import com.liinji.ppgdeliver.utils.DatePickerUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lunger on 2015/02/05 15:40
 */
public class SendReportActivity extends AppCompatActivity implements HttpRequestListener {

    private NoScrollHorizontalScrollView mGoodsNameSV;//不可滑动的顶部右侧的ScrollView
    private LinkedHorizontalScrollView mInfoContainer;//底部右侧的ScrollView
    private ListView mListViewName;//底部左侧的ListView
    private ListView mListViewInfo;//底部右侧的ListView

    boolean isLeftListEnabled = false;
    boolean isRightListEnabled = false;

    private NoScrollHorizontalScrollView mNoScrollHorizontalScrollView;

    private LvNameAdapter mLvNormalNameAdapter;
    private LvInfoAdapter mLvNormalInfoAdapter;

    private View mBottomLine;

    private TextView mLeftCon;
    private TextView mRightCon;
    private AlertDialog.Builder mBuilderTime;

    private Toolbar mToolbar;
    private NoScrollHorizontalScrollView mBottomScroll;
    private TextView mEmptyView;
    private TextView mTotalOrderNum;
    private TextView mTotalPieces;
    private TextView mPiecesCount;
    private TextView mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
        ButterKnife.bind(this);
        mSenders = new ArrayList<>();
        mIsSigned = 2;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mUserInfo = SharePrefUtils.getUserInfo();
        mSearchedSenderId = 0;
        mLeftCon = (TextView) findViewById(R.id.left_condition);
        mRightCon = (TextView) findViewById(R.id.right_condition);
        mLeftCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsDefaultSender == 1 && !mSenders.isEmpty()) {
                    senderDilog();
                }
            }
        });
        mRightCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consignDilog();
            }
        });
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBottomScroll = (NoScrollHorizontalScrollView) findViewById(R.id.bottom_scroll);
        mEmptyView = (TextView) findViewById(R.id.empty_view);
        mBottomLine = findViewById(R.id.total_line);
        mTotalOrderNum = (TextView) findViewById(R.id.total);
        mTotalPieces = (TextView) findViewById(R.id.total_pieces);
        mPiecesCount = (TextView) findViewById(R.id.pieces_count);
        mDate = (TextView) findViewById(R.id.date);

        mStartDate = mEndDate = new Date();
        mDate.setText("发货日期：" + DateTool.getDateStr(mStartDate, "yyyy.MM.dd"));


//        根据安卓版本改变状态栏图标颜色

        int result = StatusBarTools.StatusBarLightMode(this);

        if (result == 0) {
            StatusBarTools.setStatusBarColor(this, R.color.color_statusbar);
        } else {
            StatusBarTools.setStatusBarColor(this, R.color.white);
        }

        initView();
        initAdapter();
    }


    private AlertDialog mDateSelector;
    private ViewFlipper mViewFlipper;


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
                if (mStartDate.equals(mEndDate)) {
                    mDate.setText("发货日期：" + DateTool.getDateStr(mStartDate, "yyyy.MM.dd"));
                } else {
                    mDate.setText("发货日期：" + DateTool.getDateStr(mStartDate, "yyyy.MM.dd - ") + DateTool.getDateStr(mEndDate, "yyyy.MM.dd"));
                }
                onRefresh(true);
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
                mStartDate = DateTool.CALENDAR.getTime();
                endDatePicker.setMinDate(mStartDate.getTime());
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


    private void initView() {
        mGoodsNameSV = (NoScrollHorizontalScrollView) findViewById(R.id.sv_title);
        mInfoContainer = (LinkedHorizontalScrollView) findViewById(R.id.sv_good_detail);
        mListViewName = (ListView) findViewById(R.id.lv_goods_name);
        mListViewInfo = (ListView) findViewById(R.id.lv_good_info);
        //联动控件
        combination(mListViewName, mListViewInfo, mGoodsNameSV, mInfoContainer);
        MyApplication.sCancelNetTip = true;
        HttpTools.judgeIsDefaultDispatcher(this, this);
    }

    private int mLastSelected;
    private void consignDilog() {
        mBuilderTime = new AlertDialog.Builder(this);
        final String[] items = new String[]{"全部","未签收", "已签收"};
        mBuilderTime.setSingleChoiceItems(items, mLastSelected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mClear = true;
                mLastSelected = which;
                mIsSigned = getSign(which);
                mRightCon.setText(items[which] + " ▼");
                onRefresh(true);
            }
        });
        mBuilderTime.show();
    }

    private int getSign(int which) {
        switch (which){
            case 0:
                return 2;
            case 1:
                return 0;
            case 2:
                return 1;
        }
        return 2;
    }

    private List<NetBuddy> mSenders;

    private int curNameIndex;

    private void senderDilog() {
        mBuilderTime = new AlertDialog.Builder(this);
        final String[] items = new String[mSenders.size() + 2];
        items[0] = "全部";
        items[1] = mUserInfo.getUserName();
        for (int i = 2; i < items.length; i++) {
            items[i] = mSenders.get(i - 2).getName();
        }
        mBuilderTime.setSingleChoiceItems(items, curNameIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mClear = true;
                curNameIndex = which;
                mSearchedSenderId = searchedUserId(which);
                mLeftCon.setText(items[which] + " ▼");
                onRefresh(true);
            }
        });
        mBuilderTime.show();
    }


    private void initAdapter() {
        mLvNormalNameAdapter = new LvNameAdapter(this);
        mLvNormalInfoAdapter = new LvInfoAdapter(this);
        mListViewName.setAdapter(mLvNormalNameAdapter);
        mListViewInfo.setAdapter(mLvNormalInfoAdapter);


    }

    private void combination(final ListView lvName, final ListView lvDetail, final HorizontalScrollView title, LinkedHorizontalScrollView content) {
        /**
         * 左右滑动同步
         */
        content.setMyScrollChangeListener(new LinkedHorizontalScrollView.LinkScrollChangeListener() {
            @Override
            public void onscroll(LinkedHorizontalScrollView view, int x, int y, int oldx, int oldy) {
                title.scrollTo(x, y);
                mBottomScroll.scrollTo(x, y);
            }
        });

        /**
         * 上下滑动同步
         */
        // 禁止快速滑动
        lvName.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        lvDetail.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        //左侧ListView滚动时，控制右侧ListView滚动
        lvName.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mScrollState = SCROLL_STATE_IDLE;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                //这两个enable标志位是为了避免死循环
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isRightListEnabled = false;
                    isLeftListEnabled = true;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isRightListEnabled = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View child = view.getChildAt(0);
                if (child != null && isLeftListEnabled) {
                    lvDetail.setSelectionFromTop(firstVisibleItem, child.getTop());
                }

                if ((firstVisibleItem + visibleItemCount == totalItemCount) && firstVisibleItem != 0) {
                    if (!mStop) {
                        onRefresh(false);
                    }

                }
            }
        });

        //右侧ListView滚动时，控制左侧ListView滚动
        lvDetail.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    isLeftListEnabled = false;
                    isRightListEnabled = true;
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    isLeftListEnabled = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View c = view.getChildAt(0);
                if (c != null && isRightListEnabled) {
                    lvName.setSelectionFromTop(firstVisibleItem, c.getTop());
                }
            }
        });

    }

    private int mPageNo = 1;
    private int mPageSize = 40;

    private Date mStartDate;
    private Date mEndDate;

    private int mSearchedSenderId;

    private int mIsDefaultSender = 0;

    private int mIsSigned;

    public void onRefresh(boolean first) {

        if (first) {
            mClear = true;
            mPageNo = 1;
        } else {
            mClear = false;
            mPageNo++;
        }

        HttpTools.getSendReport(this, this, DateTool.getDateStr(mStartDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yyyy-MM-dd"), mPageNo, mPageSize, mSearchedSenderId, mIsDefaultSender, mIsSigned);
    }

    private int searchedUserId(int i){
        switch (i){
            case 0:
                return 0;
            case 1:
                return Integer.parseInt(mUserInfo.getUserId());
            default:
                return mSenders.get(i - 2).getId();
        }
    }
    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        switch (type) {
            case HttpRequestType.IS_DEFAULT_DISPATCHER:
                MyApplication.sCancelNetTip = false;
                mLeftCon.setText(mUserInfo.getUserName());
                mRightCon.setEnabled(true);
                mDate.setEnabled(true);
                onRefresh(true);
                break;
            case HttpRequestType.GET_BUDDY:
                mLeftCon.setText(mUserInfo.getUserName());
                mRightCon.setEnabled(true);
                mDate.setEnabled(true);
                MyApplication.sCancelNetTip = false;
                onRefresh(true);
                break;
            case HttpRequestType.GET_SEND_REPORT:

                break;
        }
        mEmptyView.setVisibility(View.VISIBLE);
    }

    private boolean mClear = false;

    private boolean mStop = false;
    private UserInfo mUserInfo;

    @Override
    public void onSuccess(int type, BaseBean bean) {

        switch (type) {
            case HttpRequestType.IS_DEFAULT_DISPATCHER:
                mIsDefaultSender = ((Integer) bean.getReturnData());
                if (mIsDefaultSender == 1) {
                    HttpTools.getBuddy(this, this);
                } else {
                    MyApplication.sCancelNetTip = false;
                    mLeftCon.setText(mUserInfo.getUserName());
                    mRightCon.setEnabled(true);
                    mDate.setEnabled(true);
                    onRefresh(true);
                }
                break;
            case HttpRequestType.GET_BUDDY:
                mSenders = (List<NetBuddy>) bean.getReturnData();
                if (!mSenders.isEmpty()) {
                    mLeftCon.setEnabled(true);
                }else {
                    mLeftCon.setText(mUserInfo.getUserName());
                    mSearchedSenderId = Integer.parseInt(mUserInfo.getUserId());
                }
                mRightCon.setEnabled(true);
                mDate.setEnabled(true);
                MyApplication.sCancelNetTip = false;
                onRefresh(true);
                break;
            case HttpRequestType.GET_SEND_REPORT:

                if (((SendReportResult) bean.getReturnData()).getTotalCount() == 0) {
                    mStop = true;
                } else {
                    mStop = false;
                }

                if (mClear) {
                    mLvNormalInfoAdapter.clearAndAddData(((SendReportResult) bean.getReturnData()).getListWaybill());
                    mLvNormalNameAdapter.clearAndAddData(((SendReportResult) bean.getReturnData()).getListWaybill());
                } else {
                    mLvNormalInfoAdapter.addData(((SendReportResult) bean.getReturnData()).getListWaybill());
                    mLvNormalNameAdapter.addData(((SendReportResult) bean.getReturnData()).getListWaybill());
                }

                SendReportResult totalInfo = ((SendReportResult) bean.getReturnData());
                mTotalOrderNum.setText("合计：" + totalInfo.getTotalCount() + " (票)");
                mPiecesCount.setText(totalInfo.getTotalNumber() + " (件)");

                showEmptyView();

                break;
        }


    }


    private void showEmptyView() {

        if (mListViewName.getAdapter().getCount() == 0) {

            mEmptyView.setVisibility(View.VISIBLE);
            mBottomLine.setVisibility(View.GONE);

        } else {
            mEmptyView.setVisibility(View.GONE);
            mBottomLine.setVisibility(View.VISIBLE);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.date_mark)
    public void onViewClicked() {
        getDateSelector().show();
    }
}
