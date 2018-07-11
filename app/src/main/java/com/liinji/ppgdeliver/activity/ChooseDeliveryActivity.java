package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.RecyclerAdapter;
import com.liinji.ppgdeliver.adapter.ViewHolder;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.BranchInfo;
import com.liinji.ppgdeliver.bean.BranchInfoDao;
import com.liinji.ppgdeliver.bean.CompanyBranchInfo;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.manager.GreenDaoManager;
import com.liinji.ppgdeliver.manager.SharedPreManager;
import com.liinji.ppgdeliver.tools.CustomDialogTool;
import com.liinji.ppgdeliver.tools.StringTools;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.PinYinUtil;
import com.liinji.ppgdeliver.widget.SlideBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseDeliveryActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.home_et)
    EditText mHomeEt;
    @BindView(R.id.home_et_ll)
    LinearLayout mHomeEtLl;
    @BindView(R.id.home_sure)
    Button mHomeSure;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.slideBar)
    SlideBar mSlideBar;
    @BindView(R.id.emptyImage)
    ImageView mEmptyImage;
    @BindView(R.id.hintInfo)
    TextView mHintInfo;
    @BindView(R.id.empty)
    RelativeLayout mEmpty;
    private List<CompanyBranchInfo> mShowItems;
    private RecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean move;
    private int index;
    private Timer mTimer;
    private TimerTask mTimerTask;




    @Override
    protected int returnContentView() {
        return R.layout.activity_choose_delivery;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mShowItems = new ArrayList<>();

        //
        queryHasNetData();
    }

    //先去数据库查询数据
    private void queryHasNetData() {
        //每隔三个小时去网络加载一次数据刷新数据
        if (System.currentTimeMillis() - SharedPreManager.getLong("time", 0) >= 10800000) {
            Logger.e("前去网络下载");
            getNetDatas();
        } else if (getBranchInfoDao().loadAll().isEmpty()) {
            Logger.e("前去网络下载");
            getNetDatas();
        } else {
            Logger.e("获取本地数据");
            List<BranchInfo> infos = getBranchInfoDao().loadAll();
            setQueryData(infos);
        }
    }

    private void getNetDatas() {
        HttpTools.getBranch(this, this);
        CustomDialogTool.dissProgressDialog();
        SharedPreManager.putLong("time", System.currentTimeMillis());
    }

    private void setQueryData(List<BranchInfo> infos) {
        mShowItems.clear();
        if (infos != null && !infos.isEmpty()) {
            for (int i = 0; i < infos.size(); i++) {
                BranchInfo info1 = infos.get(i);
                CompanyBranchInfo cbInfo = new CompanyBranchInfo();
                cbInfo.setInitial(info1.getBranchLetter());
                cbInfo.setBranchCode(info1.getBranchCode());
                cbInfo.setBranchName(info1.getBranchName());
                mShowItems.add(cbInfo);
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mHomeEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideKeyBoard();
                    //String userPhone = StringTools.getEdittextString(mHomeEt);
                    getBranchInfo(StringTools.getEdittextString(mHomeEt));
                }
                return false;
            }
        });
        watcher();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter<CompanyBranchInfo>(this, mShowItems, R.layout.item_companybranch) {
            @Override
            public void convert(ViewHolder ViewHolder, final CompanyBranchInfo bean, int position) {
                ViewHolder.setText(R.id.tv_index, bean.getInitial().substring(0, 1));
                ViewHolder.setText(R.id.tv_branchname, bean.getBranchName());
                View index = ViewHolder.getView(R.id.tv_index);
                String info = getSectionForPosition(position);
                if (position == getPositionForSection(info)) {
                    index.setVisibility(View.VISIBLE);
                } else {
                    index.setVisibility(View.GONE);
                }
                ViewHolder.setOnIntemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent data = new Intent();
                        data.putExtra("branch", JsonUtils.jsonStr(bean));
                        setResult(RESULT_OK,data);
                        finish();
                    }
                });
            }
        };

        mRecyclerView.setAdapter(mAdapter);
        mSlideBar.setOnIndexChooseListener(new SlideBar.OnIndexChooseListener() {
            @Override
            public void onIndexChoose(String word) {

                index = getPositionForSection(word);

                mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                if (index != -1) {
                    moveToPosition(index);
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (move) {
                    move = false;
                    if (index != -1) {
                        int n = index - mLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < mRecyclerView.getChildCount()) {
                            int top = mRecyclerView.getChildAt(n).getTop();
                            mRecyclerView.scrollBy(0, top);
                        }
                    }
                }
            }
        });
    }

    private void watcher() {
        mHomeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    List<BranchInfo> infos = getBranchInfoDao().loadAll();
                    setQueryData(infos);
                    mAdapter.addDatas(mShowItems);
                } else {
                    //延时搜索
                    StopTime();
                    StrarTime();
                }
            }
        });

    }

    private void StopTime() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    private void StrarTime() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if (!TextUtils.isEmpty(StringTools.getEdittextString(mHomeEt))) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Logger.e(StringTools.getEdittextString(mHomeEt));
                                getBranchInfo(StringTools.getEdittextString(mHomeEt));
                            }
                        });
                    }
                }
            };
        }
        mTimer.schedule(mTimerTask, 500);
    }

    private void moveToPosition(int position) {
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        if (position <= firstVisibleItemPosition) {
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastVisibleItemPosition) {
            int top = mRecyclerView.getChildAt(position - firstVisibleItemPosition).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(position);
            move = true;
        }
    }


    private String getSectionForPosition(int position) {
        if (mShowItems != null) {
            return mShowItems.get(position).getInitial();
        }
        return "";
    }


    /**
     * 根据首字母获取该字母在RecyclerView第一次出现的位置
     */
    private int getPositionForSection(String initial) {

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            char c = mShowItems.get(i).getInitial().charAt(0);
            if (c == initial.charAt(0)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    protected void initToolbarStyle() {
        toolbar_title.setText("派件点");

    }

    @OnClick(R.id.home_sure)
    public void onClick(View view) {
        //根据搜索的进行查找
        switch (view.getId()) {
            case R.id.home_sure:
                StopTime();
                String info = StringTools.getEdittextString(mHomeEt);
                //   Logger.e("点击了" + info);
                if (!TextUtils.isEmpty(info)) {
                    getBranchInfo(info);
                }
                break;
        }
    }

    private void getBranchInfo(String info) {
        Logger.e("准备查找：" + info);
        if (StringTools.checkIsLetter(info)) {
            // 根据BranchLetter查找
            Logger.e("正在查找：" + info.toUpperCase());
            if (info.length() == 1) {
                List<BranchInfo> infos = getBranchInfoDao().queryBuilder().where(BranchInfoDao.Properties.BranchLetter.like(info.toUpperCase() + "%")).build().list();
                setQueryDatas(infos);
            } else {
                List<BranchInfo> infos = getBranchInfoDao().queryBuilder().where(BranchInfoDao.Properties.BranchLetter.like("%" + info.toUpperCase() + "%")).build().list();
                setQueryDatas(infos);
            }
        } else if (StringTools.checkIsCode(info)) {
            // 根据BranchCode查找
            List<BranchInfo> infos = getBranchInfoDao().queryBuilder().where(BranchInfoDao.Properties.BranchCode.like("%" + info.toUpperCase() + "%")).build().list();
            setQueryDatas(infos);
        } else {
            //根据BranchName查找
            List<BranchInfo> infos = getBranchInfoDao().queryBuilder().where(BranchInfoDao.Properties.BranchName.like("%" + info + "%")).build().list();
            setQueryDatas(infos);
        }

    }

    private void setQueryDatas(List<BranchInfo> infos) {
        mShowItems.clear();
        if (infos != null && !infos.isEmpty()) {
            Logger.e("查找到数据了");
            for (int i = 0; i < infos.size(); i++) {
                BranchInfo info1 = infos.get(i);
                CompanyBranchInfo cbInfo = new CompanyBranchInfo();
                cbInfo.setInitial(info1.getBranchLetter());
                Logger.e(cbInfo.getInitial());
                cbInfo.setBranchCode(info1.getBranchCode());
                cbInfo.setBranchName(info1.getBranchName());
                mShowItems.add(cbInfo);
            }
        }
        mAdapter.addDatas(mShowItems);
    }


    @Override
    public void onSuccess(int type, BaseBean bean) {
        if (type == HttpRequestType.GET_BRANCH) {
            mShowItems = (List<CompanyBranchInfo>) bean.getReturnData();
            //获取每个字符的首字母设置给Initial
            if (mShowItems != null && mShowItems.size() > 0) {
                for (int i = 0; i < mShowItems.size(); i++) {
                    CompanyBranchInfo companyBranchInfo = mShowItems.get(i);
                    StringBuilder builder = new StringBuilder();
                    for (int j = 0; j < companyBranchInfo.getBranchName().length(); j++) {
                        String charAt = String.valueOf(companyBranchInfo.getBranchName().charAt(j));
                        builder.append(PinYinUtil.getInstance().getPinYin(charAt).substring(0, 1));
                    }
                    companyBranchInfo.setInitial(builder.toString());
                }
            } else {
                mRecyclerView.setVisibility(View.GONE);
                mSlideBar.setVisibility(View.GONE);
                mEmpty.setVisibility(View.VISIBLE);
            }
            //根据字母进行排序
            Collections.sort(mShowItems, PinYinUtil.getInstance());
            mAdapter.addDatas(mShowItems);
            saveBranchByList(mShowItems);
        }
    }


    /**
     * 将数据插入到数据库中
     * @param showItems
     */
    private void saveBranchByList(final List<CompanyBranchInfo> showItems) {
        //判断数据库是否有数据,不为空清空数据库
        if (!getBranchInfoDao().loadAll().isEmpty()) {
            getBranchInfoDao().deleteAll();
        }

        if (mShowItems == null && mShowItems.isEmpty()) {
            return;
        }
        getBranchInfoDao().getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < showItems.size(); i++) {
                    BranchInfo info = new BranchInfo(null, showItems.get(i).getBranchName(), showItems.get(i).getBranchCode(), showItems.get(i).getInitial());
                    getBranchInfoDao().insertOrReplace(info);
                }
            }
        });
    }

    private BranchInfoDao getBranchInfoDao() {
        return GreenDaoManager.getInstance().getDaoSession().getBranchInfoDao();
    }
}
