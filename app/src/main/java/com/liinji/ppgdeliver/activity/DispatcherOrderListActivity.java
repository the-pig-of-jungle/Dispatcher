package com.liinji.ppgdeliver.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.DispatchOrderAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.ReDispatchOrder;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.manager.BuddyDaoManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 朱志强 on 2017/7/31.
 */

public class DispatcherOrderListActivity extends BaseRecycleviewActivity {

    public static final String EXTRA_WAIT_SEND_DETAIL_JSON = "order";
    public static final String EXTRA_REDUCE_AMOUNT = "reduce_amount";
    public static final String OP_MODE = "op_mode";
    public static final String RETURN_REDUCE = "return_reduce";
    public static final String EXTRA_IS_SEND_AND_LOAD = "is_send_and_load";
    private static final int REQ_CODE_CHOOSE = 0X456;


    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    @BindView(R.id.edit)
    CheckBox mEdit;
    @BindView(R.id.dispatch)
    Button mDispatch;
    @BindView(R.id.home_et)
    EditText mHomeEt;
    @BindView(R.id.search_order)
    Button mSearchOrder;
    @BindView(R.id.clear)
    ImageView mClear;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    private boolean mNotScroll;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNotScroll) {
                mToTopFb.setVisibility(View.GONE);
            }
        }
    };

    private boolean mIsTotalSelect = false;

    @Override
    protected void initRestData() {

    }

    @Override
    protected void initRestView() {
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

        mEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEdit.setText(isChecked ? "取消" : "全选");
                mIsTotalSelect = isChecked;
                DispatchOrderAdapter adapter = (DispatchOrderAdapter) recyclerView.getAdapter();
                List<ReDispatchOrder> data = adapter.getData();
                int size = data.size();
                ReDispatchOrder.setDefaultChecked(isChecked);
                for (int i = 0; i < size; i++) {
                    data.get(i).setChecked(isChecked);

                }

                adapter.notifyDataSetChanged();

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
                    mClear.setVisibility(View.INVISIBLE);
                } else {
                    mClear.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("待送运单");
        ReDispatchOrder.setDefaultChecked(false);
        pagesize = 1000;
    }

    @Override
    protected int getContentView() {
        return R.layout.dispatch_order_list;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new DispatchOrderAdapter(new ArrayList<ReDispatchOrder>());
    }

    private int mCurrentPos;
    public static final int OP_MODE_CHANGE = 5;
    public static final int OP_MODE_REMOVE = 6;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQ_CODE_CHOOSE && resultCode == RESULT_OK && data != null) {
            boolean needRefresh = data.getBooleanExtra("need_refresh", false);
            if (needRefresh) {
                mEdit.setChecked(false);
                onRefresh();
                setResult(RESULT_OK, data);
            }
        }
    }

    private String mKeywords = "";

    @Override
    protected void getRecyclerViewNetData() {
        HttpTools.getRedispatchOrderList(this, this, mKeywords, pageIndex + "", pagesize + "");
    }


    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.GET_REDISPATCH_ORDER_LIST;
    }


    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);

        switch (type) {

            case HttpRequestType.GET_REDISPATCH_ORDER_LIST:
                if (recyclerView.getAdapter().getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }
                break;
        }

    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type) {
            case HttpRequestType.GET_REDISPATCH_ORDER_LIST:
                mEmptyView.setVisibility(View.VISIBLE);
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


    @OnClick({R.id.dispatch, R.id.search_order, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dispatch:
                ArrayList<String> dispatch = new ArrayList<>();
                List<ReDispatchOrder> src = ((DispatchOrderAdapter) recyclerView.getAdapter()).getData();
                if (src.isEmpty()) {
                    SmartToast.showInCenter("无订单可选！");
                    return;
                }

                for (ReDispatchOrder order : src) {
                    if (order.isChecked()) {
                        dispatch.add(order.getWaybillNo());
                    }
                }

                if (dispatch.isEmpty()) {
                    SmartToast.showInCenter("尚未选择要指派的运单");
                    return;
                }
                BuddyDaoManager.getInstance().getDaoSession().getDispatcherDao().deleteAll();
                Intent intent = new Intent(this, ChooseBuddyActivity.class);
                intent.putStringArrayListExtra("order_list", dispatch);
                startActivityForResult(intent, REQ_CODE_CHOOSE);
                break;
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
    protected void onBaseItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        switch (view.getId()) {
            case R.id.root:
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_mark);
                checkBox.setChecked(!checkBox.isChecked());
                ((ReDispatchOrder) baseQuickAdapter.getData().get(i)).setChecked(checkBox.isChecked());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
