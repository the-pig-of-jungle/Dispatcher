package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.zzq.smartshow.snackbar.SmartSnackbar;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.ProcessAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.ProcessDetail;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class ProblemOrderDetailActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.problem_order)
    TextView mProblemOrder;
    @BindView(R.id.register_pos)
    TextView mRegisterPos;
    @BindView(R.id.receive_pos)
    TextView mReceivePos;
    @BindView(R.id.problem_state)
    TextView mProblemState;
    @BindView(R.id.problem_desc)
    TextView mProblemDesc;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    private String mProblemId;


    @Override
    protected int returnContentView() {
        return R.layout.activity_problem_order_detail;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("问题件处理详情");
        mProblemId = getIntent().getStringExtra("problem_id");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ProcessAdapter());
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                mPaint.setColor(Color.parseColor("#e5e5e5"));
                mPaint.setStrokeWidth(Utils.dp2px(MyApplication.sContext,2));
                c.drawLine(Utils.dp2px(MyApplication.sContext,18.5f), Utils.dp2px(MyApplication.sContext,21), Utils.dp2px(MyApplication.sContext,18.5f),parent.getHeight(),mPaint);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        HttpTools.getProcessList(this, this,mProblemId);
    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {
        HttpTools.isMyOrder(this,mProcessDetail.getWaybillNo(),this);
    }


    private ProcessDetail mProcessDetail;
    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type){
            case HttpRequestType.GET_PROCESS_LIST:

                mProcessDetail = (ProcessDetail) bean.getReturnData();

                mProblemOrder.setText("运单号：" + mProcessDetail.getWaybillNo());
                mRegisterPos.setText("登记网点：" + mProcessDetail.getRegisterBranchName());
                mReceivePos.setText("接收网点：" + mProcessDetail.getReceiveBranchName());
                mProblemState.setText("问题状态：" + mProcessDetail.getProblemStatus());
                mProblemDesc.setText("问题描述：" + mProcessDetail.getProblemDescribe());
                ((ProcessAdapter) mRecyclerView.getAdapter()).addData(mProcessDetail.getReplyList());

                if (mProcessDetail.getReplyList().size() == 0){
                    mEmptyView.setVisibility(View.VISIBLE);
                }else {
                    mEmptyView.setVisibility(View.GONE);
                }
                break;
            case HttpRequestType.ORDER_DETAIL:
                int orderStatus = ((OrderDetail) bean.getReturnData()).getWaybillInfo().getCorWaybillStatus();
                Logger.d(orderStatus);
                Class clazz = null;
                switch (orderStatus) {
                    case 1:
                        if (((OrderDetail) bean.getReturnData()).getWaybillInfo().getIsReturn() == 1){
                            clazz = CompleteDetailActivity.class;
                        }else {
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
                intent.putExtra("order", mProcessDetail.getWaybillNo());
                intent.putExtra("order_id",((OrderDetail) bean.getReturnData()).getWaybillInfo().getWaybillId());
                intent.putExtra(WaitToSentActivity.EXTRA_IS_SEND_AND_LOAD, ((OrderDetail) bean.getReturnData()).getWaybillInfo().getIsSendAndLoad());
                startActivity(intent);
                break;
            case HttpRequestType.IS_MY_ORDER:
                HttpTools.orderDetail(this,mProcessDetail.getWaybillNo(),this);
                break;
        }


    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {

        switch (type){
            case HttpRequestType.GET_PROCESS_LIST:
                super.onFailed(type, e, bean);
                mEmptyView.setVisibility(View.VISIBLE);
                break;
            case HttpRequestType.IS_MY_ORDER:

                SmartSnackbar.dismiss();
                Intent intent = new Intent(this, OrderTraceActivity.class);
                intent.putExtra("order", mProcessDetail.getWaybillNo());
                startActivity(intent);
                break;
        }

    }
}
