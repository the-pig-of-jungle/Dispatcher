package com.liinji.ppgdeliver.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.activity.DaishouDebitDetailActivity;
import com.liinji.ppgdeliver.adapter.QiankuanAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.Qiankuan;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


/**
 * Created by 朱志强 on 2017/6/29.
 */

public class QiankuanFragment extends BaseRecycleviewFragment {


    public static final String EXTRA_ORDER_ID = "order_id";
    public static final int REQ_QIANKUAN_DETAIL = 0X123;
    private static final String EXTRA_ITEM_POS = "item_pos";

    @BindView(R.id.empty_view)
    RelativeLayout mEmptyView;
    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.edt_search)
    EditText mEdtSearch;
    @BindView(R.id.clear)
    ImageView mClear;
    Unbinder unbinder;

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
    }

    private View mHeaderView;
    @Override
    protected void initRestView() {
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.offline_debit_header,null);
        ((QiankuanAdapter) recyclerView.getAdapter()).addHeaderView(mHeaderView);
        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mClear.setVisibility(View.GONE);
                } else {
                    mClear.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.see_detail) {

                    Intent intent = new Intent(getActivity(), DaishouDebitDetailActivity.class);
                    intent.putExtra(EXTRA_ORDER_ID, ((Qiankuan) baseQuickAdapter.getData().get(i)).getWaybillId());
                    intent.putExtra("order",((Qiankuan) baseQuickAdapter.getData().get(i)).getWaybillNo());
                    intent.putExtra(EXTRA_ITEM_POS, i);
                    startActivityForResult(intent, REQ_QIANKUAN_DETAIL);
                }

                if (view.getId() == R.id.call_phone) {
                    Utils.dialSomeone(getActivity(), ((Qiankuan) baseQuickAdapter.getData().get(i)).getReceiveTel());
                }
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_qiankuan;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new QiankuanAdapter(new ArrayList<Qiankuan>());
    }


    private String mKeywords = "";

    @Override
    protected void getRecyclerViewNetData() {
        HttpTools.getNormalQiankuan(getActivity(), this, pageIndex, pagesize, mKeywords);
    }

    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.GET_NORMAL_QIANKUAN;
    }

    private int mTotalOrders;
    private int mOver30;
    private int mOver15;
    private int mOver5;


    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);

        recyclerView.getAdapter().notifyDataSetChanged();

        if (TextUtils.isEmpty(bean.getmTotalOrders())){
            mTotalOrders = 0;
        }else {
            mTotalOrders = Integer.parseInt(bean.getmTotalOrders());
        }

        mOver30 = bean.getmOverdue30();
        mOver15 = bean.getmOverdue15();
        mOver5 = bean.getmOverdue5();



        if (mTotalOrders == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
        setOutline();

    }

    private void setOutline() {
        ((TextView) mHeaderView.findViewById(R.id.total_orders)).setText( Utils.getInserBoldText(6,"欠款总票数：" + mTotalOrders + " 票"));

        ((TextView) mHeaderView.findViewById(R.id.overdue_30)).setText(Utils.getInserBoldText(0, mOver30 + "票"));
        ((TextView) mHeaderView.findViewById(R.id.overdue_15)).setText(Utils.getInserBoldText(0,mOver15 + "票"));
        ((TextView) mHeaderView.findViewById(R.id.overdue_5)).setText(Utils.getInserBoldText(0,mOver5 + "票"));
    }


    @OnClick(R.id.to_top_fb)
    public void onClick() {
        recyclerView.scrollToPosition(0);
    }

    public FloatingActionButton getToTopFb() {
        return mToTopFb;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mToTopFb.setVisibility(View.GONE);
        if (resultCode == RESULT_OK && requestCode == REQ_QIANKUAN_DETAIL && data != null) {
            boolean paySucc = data.getBooleanExtra("pay_succ", false);
            int itemPos = data.getIntExtra("item_pos", -1);
            if (paySucc && itemPos != -1) {

                Qiankuan qiankuan = ((QiankuanAdapter) recyclerView.getAdapter()).getData().remove(itemPos);

                recyclerView.getAdapter().notifyDataSetChanged();
                mTotalOrders--;
                int overDays = qiankuan.getOweDay();
                if (overDays > 30){
                    mOver30--;
                }else if (overDays > 15){
                    mOver15--;
                }else if (overDays > 5){
                    mOver5--;
                }
                setOutline();
                if (((QiankuanAdapter) recyclerView.getAdapter()).getData().size() < 3) {
                    onLoadMoreRequested();
                }

                if (recyclerView.getAdapter().getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }

            }
        }

    }


    @OnClick({R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                mKeywords = mEdtSearch.getText().toString().trim();
                onRefresh();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEdtSearch.getWindowToken(), 0);
                break;
        }
    }




    @OnClick(R.id.clear)
    public void onViewClicked() {
        mEdtSearch.setText("");
    }
}
