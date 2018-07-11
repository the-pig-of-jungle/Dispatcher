package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.BillOrderAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.BillOrder;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.liinji.ppgdeliver.activity.OrderCompleteActivity.EXTRA_ORDER_DETAIL_JSON;
import static com.liinji.ppgdeliver.fragment.QiankuanFragment.EXTRA_ORDER_ID;
import static com.liinji.ppgdeliver.fragment.QiankuanFragment.REQ_QIANKUAN_DETAIL;


public class JiaozhangListActivity extends BaseRecycleviewActivity {
    private static final String EXTRA_ITEM_POS = "item_pos";
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;

    private int mBillId;

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        mBillId = getIntent().getIntExtra("bill_id", 0);
    }

    @Override
    protected void initRestData() {

    }

    @Override
    protected void initRestView() {
        toolbar_title.setText("交账运单");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_jiaozhang_list;
    }


    @Override
    protected BaseQuickAdapter getAdapter() {
        return new BillOrderAdapter(new ArrayList<BillOrder>());
    }

    @Override
    protected void onBaseItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        switch (view.getId()) {
            case R.id.see_detail:
                int type = baseQuickAdapter.getItemViewType(i);
                if (type == BillOrder.ITEM_TYPE_COMPLETE) {
                    Intent intent = new Intent(this, CompleteDetailActivity.class);
                    intent.putExtra(EXTRA_ORDER_DETAIL_JSON, ((BillOrder) baseQuickAdapter.getData().get(i)).getWaybillNo());
                    intent.putExtra(WaitToSentActivity.EXTRA_IS_SEND_AND_LOAD, ((BillOrder) baseQuickAdapter.getData().get(i)).getIsSendAndLoad());
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(this, DaishouDetailActivity.class);
                    intent.putExtra(EXTRA_ORDER_ID, ((BillOrder) baseQuickAdapter.getData().get(i)).getWaybillId());
                    intent.putExtra("order",((BillOrder) baseQuickAdapter.getData().get(i)).getWaybillNo());
                    intent.putExtra(EXTRA_ITEM_POS, i);
                    startActivityForResult(intent, REQ_QIANKUAN_DETAIL);
                }
                break;
            case R.id.call_phone:
                Utils.dialSomeone(this, ((BillOrder) baseQuickAdapter.getData().get(i)).getReceiveTel());
                break;
        }
    }

    @Override
    protected void getRecyclerViewNetData() {
        HttpTools.getBillOrderList(this, this, mBillId, pageIndex, pagesize);
    }

    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.getBillOrderList;
    }


    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        if (recyclerView.getAdapter().getItemCount() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        }else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
