package com.liinji.ppgdeliver.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.OrderDetail;
import com.liinji.ppgdeliver.bean.PayEvent;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.utils.Utils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/3/23.
 */
public class OnlinePayActivity extends BaseDarkStatusBarActivity {

    @BindView(R.id.view_flipper)
    ViewFlipper mViewFlipper;

    @Override
    protected int returnContentView() {

        return R.layout.activity_online_pay;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar_title.setText("线上支付");
        toolbar_title.setTextColor(Color.parseColor("#333333"));
        toolbar.setNavigationIcon(R.drawable.yx_bk);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewFlipper.getDisplayedChild() == 1){
                    HttpTools.updateSignRemark(OnlinePayActivity.this,OnlinePayActivity.this,mOrderId,mSignRemarks);
                }else {
                    HttpTools.orderDetail(OnlinePayActivity.this, mOrder, OnlinePayActivity.this);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#888888"));
        }
    }

    private String mOrder;

    private String mOrderId;

    private Button mCompleteBtn;
    private TextView mPayAmount;

    private TextView mOrderNumber;
    private TextView mTotalFeeTxt;
    private String mTotalFee;

    private String mSignRemarks;

    @Override
    protected void initData(Bundle savedInstanceState) {

        mOrder = getIntent().getStringExtra("order");
        mOrderId = getIntent().getStringExtra("order_id");
        mTotalFee = getIntent().getStringExtra("total_fee");

        mSignRemarks = getIntent().getStringExtra("sign_remarks");




        Bitmap bitmap = CodeUtils.createImage(mOrder, (int) Utils.dp2px(this, 150), (int) Utils.dp2px(this, 150), null);
        ViewGroup viewGroup1 = (ViewGroup) getLayoutInflater().inflate(R.layout.scan_pay_item,mViewFlipper,false);
        ImageView imgv = (ImageView) viewGroup1.findViewById(R.id.pay_qr);
        imgv.setImageBitmap(bitmap);
        mViewFlipper.addView(viewGroup1);

        ViewGroup viewGroup2 = (ViewGroup) getLayoutInflater().inflate(R.layout.scan_pay_result_item,mViewFlipper,false);
        mCompleteBtn = (Button) viewGroup2.findViewById(R.id.complete_btn);
        mCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpTools.updateSignRemark(OnlinePayActivity.this,OnlinePayActivity.this,mOrderId,mSignRemarks);
            }
        });

        mPayAmount = (TextView) viewGroup2.findViewById(R.id.amount);
        mOrderNumber = (TextView) viewGroup2.findViewById(R.id.order_number);
        mTotalFeeTxt = (TextView) viewGroup2.findViewById(R.id.amount);
        mViewFlipper.addView(viewGroup2);

        mTotalFeeTxt.setText(mTotalFee);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    public void startBack(){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mViewFlipper.getDisplayedChild() == 1){
                HttpTools.updateSignRemark(this,this,mOrderId,mSignRemarks);
            }else {
                HttpTools.orderDetail(this, mOrder, this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    private OrderDetail.WaybillInfoBean mWaybillInfoBean;

    private int mRequestTimes = 0;
    @Override
    public void onSuccess(int type, BaseBean bean) {

        switch (type){

            case HttpRequestType.ORDER_DETAIL:

                mWaybillInfoBean = ((OrderDetail) bean.getReturnData()).getWaybillInfo();
                switch (mWaybillInfoBean.getCorWaybillStatus()) {
                    case 1:
                        finish();
                        break;
                    case 2:
                    case 3:
                        HttpTools.updateSignRemark(this,this,mOrderId,mSignRemarks);
                        break;
                }
                break;
            case HttpRequestType.UPDATE_SIGN_REMARKS:
                if (mRequestTimes != 0){
                    EventBus.getDefault().postSticky(new PayEvent(PayEvent.PAY_SUCCESSFUL));
                    NavUtils.navigateUpTo(this,new Intent(this,WaitToSentActivity.class));
                }

                mRequestTimes++;
                break;
        }

    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type){
            case HttpRequestType.ORDER_DETAIL:
                if (bean.getReturnCode().equals("-1") || bean.getReturnMsg().equals("配送员还未与此运单发生联系。")) {
                    NavUtils.navigateUpTo(this,new Intent(this,WaitToSentActivity.class));
                }
                break;
            case HttpRequestType.UPDATE_SIGN_REMARKS:

                break;
        }

    }


    public ViewFlipper getViewFlipper(){
        return mViewFlipper;
    }

    public TextView getPayAmount(){
        return mPayAmount;
    }

    public TextView getOrderNumber(){
        return mOrderNumber;
    }

    public String getOrderStr(){
        return mOrder;
    }


    public String getOrder() {
        return mOrder;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public String getSignRemarks() {
        return mSignRemarks;
    }
}
