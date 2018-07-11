package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.BillRecordAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.BillRecord;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.widget.CusSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/3/17.
 */
public class BillRecordActivity extends BaseRecycleviewActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.cusswipeRefreshlayout)
    CusSwipeRefreshLayout mCusswipeRefreshlayout;

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar_title.setText("记录");
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }

    }


    @Override
    protected void initRestData() {

    }

    @Override
    protected void initRestView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_bill_record;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new BillRecordAdapter(new ArrayList<BillRecord>());

    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);

        if (bean.getReturnTotalRecords().equals("0")){
            mEmptyView.setVisibility(View.VISIBLE);
        }else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onBaseItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        super.onBaseItemClick(baseQuickAdapter, view, i);
        BillRecord billRecord = (BillRecord) baseQuickAdapter.getData().get(i);
        int id = billRecord.getID();
        Intent intent = new Intent(this,JiaozhangListActivity.class);
        intent.putExtra("bill_id",id);
        startActivity(intent);
    }


    @Override
    protected void getRecyclerViewNetData() {
        HttpTools.billRecord(this,pageIndex + "",pagesize + "",this);
    }


    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.BILL_RECORD;
    }


}
