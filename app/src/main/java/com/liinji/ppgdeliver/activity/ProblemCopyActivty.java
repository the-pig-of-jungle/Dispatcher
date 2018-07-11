package com.liinji.ppgdeliver.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.target.SquaringDrawable;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.PicAdapter;
import com.liinji.ppgdeliver.adapter.PosAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.CompanyBranchInfo;
import com.liinji.ppgdeliver.bean.ProblemOrder;
import com.liinji.ppgdeliver.bean.ProblemOrderState;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.tools.BitmapTools;
import com.liinji.ppgdeliver.tools.IntentUtils;
import com.liinji.ppgdeliver.utils.JsonUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;

public class ProblemCopyActivty extends BaseDarkStatusBarActivity {

    private static final int REQ_CODE_GET_BRANCH = 333;
    private static final int REQ_CODE_SCAN = 4;
    private static final int REQ_CODE_PIC = 0x32;
    @BindView(R.id.order_scan_mark)
    ImageView mOrderScanMark;
    @BindView(R.id.order_edt)
    EditText mOrderEdt;
    @BindView(R.id.order_state)
    TextView mOrderState;
    @BindView(R.id.drop_down_mark)
    ImageView mDropDownMark;
    @BindView(R.id.state_edt)
    EditText mStateEdt;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static final int REQUEST_CODE = 123;

    @BindView(R.id.submit_info_btn)
    Button mSubmitInfoBtn;
    @BindView(R.id.problem_desc)
    EditText mProblemDesc;
    @BindView(R.id.problem_record)
    TextView mProblemRecord;
    @BindView(R.id.position)
    TextView mPosition;
    @BindView(R.id.desc_label)
    TextView mDescLabel;
    @BindView(R.id.pic_list)
    RecyclerView mPicList;


    private ImgSelConfig mConfig;

    @Override
    protected int returnContentView() {

        return R.layout.activity_problem_copy_activty;
    }

    @Override
    protected void initToolbarStyle() {
        super.initToolbarStyle();
        toolbar_title.setText("问题件登记");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private ProblemOrderState mProblemState;

    private SpannableString mSpannableString;

    @Override
    protected void initView(Bundle savedInstanceState) {

        mPicList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });

        mPicList.setAdapter(new PicAdapter(mPicList));

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mRecyclerView.setAdapter(new PosAdapter());
        mSpannableString = new SpannableString("问题描述：(0/100)");
        mSpannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0dc25f")), 5, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mDescLabel.setText(mSpannableString);
        mProblemDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() <= 100) {
                    mSpannableString = new SpannableString("问题描述：(" + s.length() + "/100)");
                    mSpannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#0dc25f")), 5, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mDescLabel.setText(mSpannableString);
                    return;
                }

                if (s.length() > 100) {
                    mSpannableString = new SpannableString("问题描述：(" + (100 - s.length()) + ")");
                    mSpannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#cc0000")), 5, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mDescLabel.setText(mSpannableString);
                    return;
                }

            }
        });
        ViewGroup content = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.select_state, new LinearLayout(this), false);
        mPopContent = (ListView) content.findViewById(R.id.list_view);
        mPopContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mStateEdt.setText("");
                mStateEdt.append(((TextView) view).getText());
                if (TextUtils.isEmpty(mProblemDesc.getText().toString().trim())) {
                    mProblemDesc.setText(((TextView) view).getText());
                }
                mProblemState = mProblemOrderStates.get(position);
                mSelectState.dismiss();
            }
        });
        mSelectState = new PopupWindow(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        HttpTools.getState(this, this);

        mSpotsDialog = new SpotsDialog(this, R.style.CustomSpotsDialog);


    }


    @OnClick(R.id.order_scan_mark)
    public void onMOrderScanMarkClicked() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQ_CODE_SCAN);
    }

    PopupWindow mSelectState;
    ListView mPopContent;

    @OnClick(R.id.drop_down_mark)
    public void onMDropDownMarkClicked() {
        mSelectState.showAtLocation(mDropDownMark, Gravity.CENTER, 0, 0);
    }


    @OnClick(R.id.add)
    public void onAddViewClicked() {
        IntentUtils.startActivityForResult(this, ChooseDeliveryActivity.class, REQ_CODE_GET_BRANCH);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == PicAdapter.ViewHolder.REQ_SELECT && resultCode == RESULT_OK && data != null) {
            final List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            ((PicAdapter) mPicList.getAdapter()).add(pathList);
        }
        if (requestCode == PicAdapter.ViewHolder.REQ_CAMERA && resultCode == RESULT_OK) {
            Logger.d("我爱你" + mOutPutFileUri);
            ((PicAdapter) mPicList.getAdapter()).add(mOutPutFileUri.getEncodedPath());
        }





        if (requestCode == REQ_CODE_GET_BRANCH && resultCode == RESULT_OK && data != null) {
            String jsonStr = data.getStringExtra("branch");
            CompanyBranchInfo companyBranchInfo = JsonUtils.json2obj(jsonStr, CompanyBranchInfo.class);
            ((PosAdapter) mRecyclerView.getAdapter()).addData(companyBranchInfo);
        }


        if (resultCode == RESULT_OK && requestCode == REQ_CODE_SCAN && data != null) {
            Bundle bundle = data.getExtras();
            switch (bundle.getInt(CodeUtils.RESULT_TYPE)) {
                case CodeUtils.RESULT_SUCCESS:
                    String orderStr = Utils.trimOrder(bundle.getString(CodeUtils.RESULT_STRING));
                    mOrderEdt.setText(orderStr);
                    break;
                case CodeUtils.RESULT_FAILED:
                    Snackbar.make(getRootView(), "扫描失败", Snackbar.LENGTH_SHORT).show();
                    break;
            }
        }




    }


    @Override
    public void onBackPressed() {
        if (mSelectState != null && mSelectState.isShowing()) {
            mSelectState.dismiss();
        } else if (mSelectMethod != null && mSelectMethod.isShowing()) {
            mSelectMethod.dismiss();
        } else {
            super.onBackPressed();
        }

    }

    private List<ProblemOrderState> mProblemOrderStates = new ArrayList<>();


    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.GET_STATE:
                List<ProblemOrderState> data = (List<ProblemOrderState>) bean.getReturnData();
                mProblemOrderStates.clear();
                mProblemOrderStates.addAll(data);
                List<String> names = new ArrayList<>(data.size());

                for (int index = 0; index < data.size(); index++) {
                    names.add(data.get(index).getRemark());
                }

                mPopContent.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
                break;
            case HttpRequestType.UPLOAD_PROBLEM_IMG:
                mPicUrls.add(((String) bean.getReturnData()));
               mCount--;
                Logger.d("数量" + mCount);
                if (mCount <= 0) {

                    for (int i = 0;i < mPicUrls.size();i++){
                        if (i == 0){
                            mProblemOrder.setImageUrl1(mPicUrls.get(0));

                        }

                        if (i == 1){
                            mProblemOrder.setImageUrl2(mPicUrls.get(1));

                        }

                        if (i == 2){
                            mProblemOrder.setImageUrl3(mPicUrls.get(2));

                        }


                    }

                    Logger.d(JsonUtils.jsonStr(mProblemOrder));

                    HttpTools.submitProblemInfo(this, this, mProblemOrder);
                }
                break;
            case HttpRequestType.SUBMIT_PROBLEM_INFO:
                mSubmitInfoBtn.setEnabled(false);
                mSpotsDialog.dismiss();
                MyApplication.upTotal = false;
                MyApplication.upImg = false;
                SmartToast.show("提交成功！");
                mOrderEdt.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1200);

                break;

        }
    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type){
            case HttpRequestType.SUBMIT_PROBLEM_INFO:
                mSpotsDialog.dismiss();
                MyApplication.upTotal = false;
                MyApplication.upImg = false;
                break;
        }
    }

    private PopupWindow mSelectMethod;

    private ListView mMethodContent;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
    private Uri mOutPutFileUri;

    private int mCount;

    private ProblemOrder mProblemOrder = new ProblemOrder();

    private SpotsDialog mSpotsDialog;

    @OnClick(R.id.submit_info_btn)
    public void onBtnViewClicked() {

        if (TextUtils.isEmpty(mOrderEdt.getText().toString().trim())) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
            sweetAlertDialog.setTitleText("提示");
            sweetAlertDialog.setContentText("订单号不可为空!");
            sweetAlertDialog.setConfirmText("确定");

            Utils.processDialog(sweetAlertDialog);

            sweetAlertDialog.show();
            return;
        } else {
            mProblemOrder.setWaybillNo(mOrderEdt.getText().toString().trim());
        }

        if (TextUtils.isEmpty(mStateEdt.getText().toString().trim())) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
            sweetAlertDialog.setTitleText("提示");
            sweetAlertDialog.setContentText("请选择问题件状态!");
            sweetAlertDialog.setConfirmText("确定");
            sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                }
            });
            sweetAlertDialog.show();
            return;
        } else {
            mProblemOrder.setProblemStatus(mProblemState.getRemark());
            mProblemOrder.setProblemStatusCode(mProblemState.getCode());
        }
        if (mProblemDesc.getText().toString().length() > 100) {
            Toast.makeText(this, "提交的问题描述不可超过100字", Toast.LENGTH_SHORT).show();
            return;
        }

        if (((PosAdapter) mRecyclerView.getAdapter()).getItemCount() == 0) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
            sweetAlertDialog.setTitleText("提示");
            sweetAlertDialog.setContentText("请选择接收站点!");
            sweetAlertDialog.setConfirmText("确定");
            sweetAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
                }
            });
            sweetAlertDialog.show();
            return;
        } else {

            StringBuilder mStringBuilder = new StringBuilder();

            List<CompanyBranchInfo> mPos = ((PosAdapter) mRecyclerView.getAdapter()).getDataSet();

            for (int index = 0; index < mPos.size(); index++) {
                mStringBuilder.append(mPos.get(index).getBranchName());
                if (index != mPos.size() - 1) {
                    mStringBuilder.append(",");
                }
            }

            mProblemOrder.setReceiveBranchName(mStringBuilder.toString());
            mStringBuilder.delete(0, mStringBuilder.length());
            for (int j = 0; j < mPos.size(); j++) {
                mStringBuilder.append(mPos.get(j).getBranchCode());
                if (j != mPos.size() - 1) {
                    mStringBuilder.append(",");

                }
            }

            mProblemOrder.setReceiveBranchCode(mStringBuilder.toString());
        }

        mProblemOrder.setProblemDescribe(mProblemDesc.getText().toString().trim());



        if (mPicList.getAdapter().getItemCount() - 1 != 0) {
            mSpotsDialog.show();
            MyApplication.upImg = true;
            MyApplication.upTotal = true;

        }

        mCount = mPicList.getAdapter().getItemCount() - 1;
        switch (mPicList.getAdapter().getItemCount() - 1) {
            case 0:
                HttpTools.submitProblemInfo(this, this, mProblemOrder);
                break;
            case 3:
                loadBitmap(((PicAdapter.ViewHolder) mPicList.getChildViewHolder(mPicList.getChildAt(2))).mPic);

            case 2:

                loadBitmap(((PicAdapter.ViewHolder) mPicList.getChildViewHolder(mPicList.getChildAt(1))).mPic);
            case 1:

                loadBitmap(((PicAdapter.ViewHolder) mPicList.getChildViewHolder(mPicList.getChildAt(0))).mPic);
                break;
        }

    }

    private void loadBitmap(ImageView imgv) {
        Drawable drawable = imgv.getDrawable();
        Bitmap b = null;
        if(drawable.getClass()==GlideBitmapDrawable.class){
            GlideBitmapDrawable mdrawable=(GlideBitmapDrawable) drawable;
            b = mdrawable.getBitmap();
        }else if(drawable.getClass()==BitmapDrawable.class){
            b = ((BitmapDrawable) drawable).getBitmap();
        }else if(drawable.getClass()==SquaringDrawable.class){
            SquaringDrawable mdrawable=(SquaringDrawable) drawable;
            b = ((GlideBitmapDrawable) mdrawable.getCurrent()).getBitmap();
        }

        BitmapTools.bitmapToBase64(this, b, new BitmapTools.CallBack() {
            @Override
            public void onConvertFinish(String result) {
                HttpTools.uploadProblemImg(ProblemCopyActivty.this, result, mOrderEdt.getText().toString().trim(), ProblemCopyActivty.this);
            }
        });
    }


    private List<String> mPicUrls = Collections.synchronizedList(new ArrayList<String>());


    @OnClick(R.id.problem_record)
    public void onRecordViewClicked() {
        IntentUtils.startActivity(this, ProblemOrderListActivity.class);
    }


    public void setOutPutFileUri(Uri outPutFileUri) {
        mOutPutFileUri = outPutFileUri;
    }


    public void setSelectMethod(PopupWindow selectMethod) {
        mSelectMethod = selectMethod;
    }
}
