package com.lunger.bidirtabledemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.CompleteDetailActivity;
import com.liinji.ppgdeliver.activity.DaishouDetailActivity;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.TableInfo;
import com.liinji.ppgdeliver.http.HttpRequestListener;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.tools.DateTool;
import com.liinji.ppgdeliver.tools.StatusBarTools;
import com.liinji.ppgdeliver.utils.Utils;
import com.lunger.bidirtabledemo.adapter.LvInfoAdapter;
import com.lunger.bidirtabledemo.adapter.LvNameAdapter;
import com.lunger.bidirtabledemo.view.LinkedHorizontalScrollView;
import com.lunger.bidirtabledemo.view.NoScrollHorizontalScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lunger on 2015/02/05 15:40
 */
public class MainActivity extends AppCompatActivity implements HttpRequestListener {

    @BindView(R.id.home_et)
    EditText mHomeEt;
    @BindView(R.id.search_order)
    Button mSearchOrder;
    @BindView(R.id.clear)
    ImageView mClearMark;
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
    private TextView mTotalYunFee;
    private TextView mTotalDaishou;
    private TextView mTotalPaiedDaishou;
    private TextView mTotalYunFeePaied;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mLeftCon = (TextView) findViewById(R.id.left_condition);
        mRightCon = (TextView) findViewById(R.id.right_condition);
        mLeftCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog();
            }
        });
        mRightCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayDialog();
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
        mTotalYunFeePaied = (TextView) findViewById(R.id.total_yun_fee_paied);
        mTotalYunFee = (TextView) findViewById(R.id.total_yun_fee);
        mTotalDaishou = (TextView) findViewById(R.id.total_daishou_fee);
        mTotalPaiedDaishou = (TextView) findViewById(R.id.total_paied_daishou_fee);


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

    private int mPayType = 0;
    private int mTimeType = 0;//0 今日 1 一周 2 一个月 3 三个月


    private AdapterView.OnItemClickListener mOnItemClickListener;

    private void initView() {
        mGoodsNameSV = (NoScrollHorizontalScrollView) findViewById(R.id.sv_title);
        mInfoContainer = (LinkedHorizontalScrollView) findViewById(R.id.sv_good_detail);
        mListViewName = (ListView) findViewById(R.id.lv_goods_name);
        mListViewInfo = (ListView) findViewById(R.id.lv_good_info);
        mOnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String order = mLvNormalNameAdapter.getItem(position).getWaybillNo();
                String orderId = mLvNormalNameAdapter.getItem(position).getWaybillId();
                Class activityClass = CompleteDetailActivity.class;
                float daishou = mLvNormalNameAdapter.getItem(position).getCollectionTradeCharges();
                float daishouPaied = mLvNormalNameAdapter.getItem(position).getCollectionTradeChargesPaid();
                float yunfee = mLvNormalInfoAdapter.getItem(position).getPickUpPayAmount();
                float yunfeePaid = mLvNormalInfoAdapter.getItem(position).getPickUpPayAmountPaid();
                String payType = mLvNormalNameAdapter.getItem(position).getPayType();

                if (payType.equals("现金支付")) {
                    if ((daishou != 0 && daishouPaied == 0) || (yunfee != 0 && yunfeePaid == 0)) {
                        activityClass = DaishouDetailActivity.class;
                    } else {

                        activityClass = CompleteDetailActivity.class;
                    }

                } else {

                    activityClass = CompleteDetailActivity.class;

                }

                Intent intent = new Intent(MainActivity.this, activityClass);

                intent.putExtra("order", order);
                intent.putExtra("order_id", orderId);
                startActivity(intent);
            }
        };

        mHomeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mClearMark.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });


        mHomeEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Utils.hideKeyboard(mToolbar);
                onRefresh(true);
                return true;
            }
        });


        mListViewInfo.setOnItemClickListener(mOnItemClickListener);
        mListViewName.setOnItemClickListener(mOnItemClickListener);
        //联动控件
        combination(mListViewName, mListViewInfo, mGoodsNameSV, mInfoContainer);

        onRefresh(true);
    }

    private void PayDialog() {
        mBuilderTime = new AlertDialog.Builder(this);
        final String[] items = new String[]{"全部", "余额付", "欠款付", "现金支付", "银行卡支付", "欠现金"};
        mBuilderTime.setSingleChoiceItems(items, mPayType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mClear = true;
                mPayType = which;
                mRightCon.setText(items[which]);
                onRefresh(true);
            }
        });
        mBuilderTime.show();


    }

    private void TimeDialog() {
        mBuilderTime = new AlertDialog.Builder(this);
        final String[] items = new String[]{"今日", "一周", "一个月", "三个月"};
        mBuilderTime.setSingleChoiceItems(items, mTimeType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mClear = true;
                mTimeType = which;
                mLeftCon.setText(items[which]);
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

    private String mSigner = "";

    public void onRefresh(boolean first) {

        String startDate = "";
        String endDate = "";

        if (mLeftCon.getText().toString().startsWith("今日")) {
            startDate = DateTool.getDateStrBeforeNow(0);
            endDate = DateTool.getDateStrAfterNow(1);
        } else if (mLeftCon.getText().toString().startsWith("一周")) {
            startDate = DateTool.getDateStrBeforeNow(6);
            endDate = DateTool.getDateStrAfterNow(1);
        } else if (mLeftCon.getText().toString().startsWith("一个月")) {
            startDate = DateTool.getDateStrBeforeNow(29);
            endDate = DateTool.getDateStrAfterNow(1);
        } else if (mLeftCon.getText().toString().startsWith("三个月")) {
            startDate = DateTool.getDateStrBeforeNow(89);
            endDate = DateTool.getDateStrAfterNow(1);
        }

        if (first) {
            mClear = true;
            mPageNo = 1;
        } else {
            mClear = false;
            mPageNo++;
        }

        mSigner = mHomeEt.getText().toString().trim();

        HttpTools.getReport(this, this, startDate, endDate, mPageNo, mPageSize, mPayType,mSigner);
    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {

        mEmptyView.setVisibility(View.VISIBLE);


    }

    private boolean mClear = false;

    private boolean mStop = false;

    @Override
    public void onSuccess(int type, BaseBean bean) {

        if (((TableInfo) bean.getReturnData()).getReturnData1().isEmpty()) {
            mStop = true;
        } else {
            mStop = false;
        }

        if (mClear) {
            mLvNormalInfoAdapter.clearAndAddData(((TableInfo) bean.getReturnData()).getReturnData1());
            mLvNormalNameAdapter.clearAndAddData(((TableInfo) bean.getReturnData()).getReturnData1());
        } else {
            mLvNormalInfoAdapter.addData(((TableInfo) bean.getReturnData()).getReturnData1());
            mLvNormalNameAdapter.addData(((TableInfo) bean.getReturnData()).getReturnData1());
        }
        TableInfo.ReturnData2Bean totalInfo = ((TableInfo) bean.getReturnData()).getReturnData2().get(0);
        mTotalOrderNum.setText("合计：" + totalInfo.getWaybillCount() + " (票)");
        mTotalYunFee.setText(PrintInfo.processFee(totalInfo.getPickUpPayAmountTotal()));
        mTotalDaishou.setText(PrintInfo.processFee(totalInfo.getCollectionTradeChargesTotal()));
        mTotalPaiedDaishou.setText(PrintInfo.processFee(totalInfo.getCollectionTradeChargesPaidTotal()));
        mTotalYunFeePaied.setText(PrintInfo.processFee(totalInfo.getPickUpPayAmountPaidTotal()));
        showEmptyView();
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

    @OnClick({R.id.search_order, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_order:
                Utils.hideKeyboard(view);
                onRefresh(true);
                break;
            case R.id.clear:
                mHomeEt.setText("");
                break;
        }
    }
}
