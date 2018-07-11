package com.liinji.ppgdeliver.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import com.freedom.land.dispatcher.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.widget.CusSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 普通的基础recyclerview样式类，只适用于普通样式带有recyclerview的访问网络获取列表数据的类
 * Created by YUEYINGSK on 2016/10/17.
 */
public abstract class BaseRecycleviewFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.cusswipeRefreshlayout)
    CusSwipeRefreshLayout cusswiperefreshlayout;
    protected int pagesize = 10;
    private int TOTAL_COUNTER;//获取的网络数据的总数目
    protected int pageIndex = 1;

    private int mCurrentCounter;//当前数据数目
    private BaseQuickAdapter adapter;
    private List<BaseBean> listData;

    @Override

    protected int returnContentView() {
        return getContentView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        listData = new ArrayList<>();
        adapter = getAdapter();
        getRecyclerViewNetData();
        getRestInter1NetData();
        initRestData();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                onBaseItemClick(baseQuickAdapter, view, i);
            }
        });
        adapter.openLoadMore(pagesize);
        adapter.setOnLoadMoreListener(this);
        cusswiperefreshlayout.setOnRefreshListener(this);
        initRestView();
    }

    /**
     * 剩余数据的初始化
     */
    protected abstract void initRestData();

    /**
     * 剩余布局初始化
     */
    protected abstract void initRestView();

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getContentView();

    /**
     * 获取适配器
     *
     * @return
     */
    protected abstract BaseQuickAdapter getAdapter();

    /**
     * 访问接口获取列表数据
     */
    protected abstract void getRecyclerViewNetData();

    /**
     * 列表接口的type识别
     *
     * @return
     */
    protected abstract int getRecyclerViewNetType();

    /**
     * 条目短暂点击
     *
     * @param baseQuickAdapter
     * @param view
     * @param i
     */
    protected void onBaseItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    /**
     * 其他接口1类型
     *
     * @return
     */
    protected int getRestInter1Type() {
        return 0;
    }

    /**
     * 其他接口1获取网络数据
     */
    protected void getRestInter1NetData() {
    }

    /**
     * 其他接口1网络数据的处理
     *
     * @param bean
     */
    protected void DealWithRestInter1Data(BaseBean bean) {
    }

    @Override
    public void onSuccess(int type, BaseBean bean) {
        if (type == getRecyclerViewNetType()) {
            cusswiperefreshlayout.setRefreshing(false);
            TOTAL_COUNTER = Integer.valueOf(bean.getReturnTotalRecords());
            listData = (List<BaseBean>) bean.getReturnData();
            if (listData != null) {
                if (pageIndex == 1) {
                    adapter.setNewData(listData);
                } else {
                    adapter.addData(listData);
                }
                mCurrentCounter = adapter.getData().size();
            }
        } else if (type == getRestInter1Type()) {
            DealWithRestInter1Data(bean);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        // 一定要在mRecyclerView.post里面更新数据。
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    // 数据全部加载完毕就调用 loadComplete
                    adapter.loadComplete();
                    myToast(getString(R.string.recyclerview_nodata));
                } else {
                    // 如果有下一页则调用addData，不需要把下一页数据add到list里面，直接新的数据给adapter即可。
                    pageIndex++;
                    getRecyclerViewNetData();
                }
            }

        });
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        getRecyclerViewNetData();
        getRestInter1NetData();
    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        cusswiperefreshlayout.setRefreshing(false);
    }
}
