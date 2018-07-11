package com.liinji.ppgdeliver.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.gprinter.io.PortParameters;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.adapter.PrintOrderAdapter;
import com.liinji.ppgdeliver.adapter.TripNoAdapter;
import com.liinji.ppgdeliver.bean.BaseBean;
import com.liinji.ppgdeliver.bean.PrintOrders;
import com.liinji.ppgdeliver.bean.TripNos;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.http.HttpRequestType;
import com.liinji.ppgdeliver.http.HttpTools;
import com.liinji.ppgdeliver.jiaboprint.JiaboPrintService;
import com.liinji.ppgdeliver.print.PrintEvent;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.print.PrintService;
import com.liinji.ppgdeliver.tools.DateTool;
import com.liinji.ppgdeliver.tools.StatusBox;
import com.liinji.ppgdeliver.utils.DatePickerUtils;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.liinji.ppgdeliver.utils.Utils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.liinji.ppgdeliver.jiaboprint.PrintUtil.showSwitchModeTip;

public class BatchPrintActivity extends BaseRecycleviewActivity {

    public static final String EXTRA_WAIT_SEND_DETAIL_JSON = "order";
    public static final String EXTRA_REDUCE_AMOUNT = "reduce_amount";
    public static final String OP_MODE = "op_mode";
    public static final String RETURN_REDUCE = "return_reduce";
    public static final String EXTRA_IS_SEND_AND_LOAD = "is_send_and_load";
    public static final int REQ_CODE_CHOOSE = 0X456;
    public static final int CHOOSE_DEVICE = 0X888;

    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.to_top_fb)
    FloatingActionButton mToTopFb;
    @BindView(R.id.filter_mark)
    ImageView mFilterMark;
    @BindView(R.id.dispatch)
    Button mDispatch;
    @BindView(R.id.home_et)
    EditText mHomeEt;
    @BindView(R.id.search_order)
    Button mSearchOrder;
    @BindView(R.id.clear)
    ImageView mClear;
    @BindView(R.id.select_all_mark)
    CheckBox mSelectAllMark;
    @BindView(R.id.select_all_label)
    TextView mSelectAllLabel;
    @BindView(R.id.selected_date)
    TextView mSelectedDate;
    @BindView(R.id.sequence_mark)
    TextView mSequenceMark;
    @BindView(R.id.send_date_label)
    TextView mSendDateLabel;
    private boolean mNotScroll;

    private String formatTripNos;


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mNotScroll) {
                mToTopFb.setVisibility(View.GONE);
            }
        }
    };


    private boolean mIsTotalSelect = false;

    private View mTripEmptyView;


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

        mSelectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(BatchPrintActivity.this)
                        .setTitleText("交接单")
                        .setConfirmText("确定")
                        .setContentText(formatTripNos);
                Utils.processDialog(sweetAlertDialog);
                sweetAlertDialog.show();
            }
        });




        mSelectAllMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                PrintOrders.ReturnDataBean.setDefaultChecked(isChecked);
                mIsTotalSelect = isChecked;
                PrintOrderAdapter adapter = (PrintOrderAdapter) recyclerView.getAdapter();
                List<PrintOrders.ReturnDataBean> data = adapter.getData();
                int size = data.size();
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
        toolbar_title.setText("打印列表");
        pagesize = 300;

        PrintOrders.ReturnDataBean.setDefaultChecked(false);
        mEndDate = new Date();

        DateTool.CALENDAR.setTime(new Date());
        DateTool.CALENDAR.add(Calendar.DAY_OF_MONTH, -2);
        mBeginDate = DateTool.CALENDAR.getTime();

        if (!mEndDate.equals(mBeginDate)) {
            mTripNo = "";
            formatTripNos = "";
            mSendDateLabel.setText("发货日期：");
            mSelectedDate.setText(DateTool.getDateStr(mBeginDate, "yyyy.MM.dd - ") + DateTool.getDateStr(mEndDate, "yyyy.MM.dd"));
        }
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_batch_print;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new PrintOrderAdapter(new ArrayList<PrintOrders.ReturnDataBean>());
    }


    private String mKeywords = "";

    @Override
    protected void getRecyclerViewNetData() {
        if (!TextUtils.isEmpty(mTripNo)) {
            HttpTools.getPrintOrderList(this, this, "", "", mTripNo, mKeywords, mSequence, pageIndex, pagesize);
        } else {
            HttpTools.getPrintOrderList(this, this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yyyy-MM-dd"), mTripNo, mKeywords, mSequence, pageIndex, pagesize);
        }
    }


    @Override
    protected int getRecyclerViewNetType() {
        return HttpRequestType.GET_PRINT_ORDER_LIST;
    }


    private List<String> mTripNos = new ArrayList<>();

    @Override
    public void onSuccess(int type, BaseBean bean) {
        super.onSuccess(type, bean);
        switch (type) {
            case HttpRequestType.GET_PRINT_ORDER_LIST:
                if (bean.getReturnTotalRecords().equals("0")) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                    String imgUrl = ((List<PrintOrders.ReturnDataBean>) bean.getReturnData()).get(0).getCompanyUrl();

                    MyApplication.sMyApplication.loadCompanyLogo(imgUrl);

                }
                mSequenceMark.setEnabled(true);

                break;
            case HttpRequestType.GET_TRIP_NO:
                ((BaseQuickAdapter) mTripNosRecyclerView.getAdapter()).getData().clear();
                List<TripNos.ReturnDataBean> orders = ((TripNos) bean.getReturnData()).getReturnData();

                if (!orders.isEmpty()) {
                    mTripEmptyView.setVisibility(View.GONE);
                } else {
                    mTripEmptyView.setVisibility(View.VISIBLE);
                }

                ((TripNoAdapter) mTripNosRecyclerView.getAdapter()).addData(orders);
                MyApplication.sCancelNetTip = false;
                break;
        }

    }

    @Override
    public void onFailed(int type, Exception e, BaseBean bean) {
        super.onFailed(type, e, bean);
        switch (type) {
            case HttpRequestType.WAIT_SENDED_LIST:
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

    private ArrayList<PrintInfo> mSelectedOrders = new ArrayList<>();

    @OnClick({R.id.dispatch, R.id.search_order, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dispatch:
                SharePrefUtils.setCancelConnect(false);
                List<PrintOrders.ReturnDataBean> orders = ((PrintOrderAdapter) recyclerView.getAdapter()).getData();
                mSelectedOrders.clear();
                for (int index = 0; index < orders.size(); index++) {
                    PrintOrders.ReturnDataBean order = orders.get(index);
                    if (order.isChecked()) {

                        PrintInfo printInfo = new PrintInfo(order, (index + 1) + "");
                        mSelectedOrders.add(printInfo);
                    }

                }

                if (TextUtils.isEmpty(SharePrefUtils.getSavedDeviceAddress()) || BluetoothAdapter.getDefaultAdapter().getRemoteDevice(SharePrefUtils.getSavedDeviceAddress()).getBondState() != BluetoothDevice.BOND_BONDED) {
                    ((TextView) getPrintPop().getContentView().findViewById(R.id.printer_name)).setText("");
                    SharePrefUtils.storeDeviceName("");
                    SharePrefUtils.storeDeviceAddress("");
                }
                getPrintPop().showAtLocation(toolbar, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.search_order:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
                String phone = mHomeEt.getText().toString().trim();
                mKeywords = phone;
                pageIndex = 1;
                if (!TextUtils.isEmpty(mTripNo)) {
                    HttpTools.getPrintOrderList(this, this, "", "", mTripNo, mKeywords, mSequence, pageIndex, pagesize);
                } else {
                    HttpTools.getPrintOrderList(this, this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yyyy-MM-dd"), mTripNo, mKeywords, mSequence, pageIndex, pagesize);
                }
                break;
            case R.id.clear:
                mHomeEt.setText("");
                break;
        }

    }

    private PopupWindow mPrintPop;

    private AlertDialog mSpecifyOrderPrintDialog;

    public PopupWindow getPrintPop() {
        if (mPrintPop == null) {
            final View content = LayoutInflater.from(this).inflate(R.layout.print_select, null);
            mPrintPop = new PopupWindow(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mPrintPop.setAnimationStyle(R.style.pay_pop_anim);
            content.findViewById(R.id.close_print_pop).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPrintPop.dismiss();
                }
            });

            content.findViewById(R.id.start_print).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharePrefUtils.setCancelConnect(false);
                    SharePrefUtils.setPrintType(SharePrefUtils.PRINT_START);
                    if (((TextView) content.findViewById(R.id.print_num)).getText().equals("0 票")) {
                        SmartToast.showInCenter("尚未选择任何运单！");
                        return;
                    }

                    if (TextUtils.isEmpty(((TextView) mPrintPop.getContentView().findViewById(R.id.printer_name)).getText())) {
                        SmartToast.showInCenter("尚未选择打印设备！");
                        return;
                    }
                    getStatusBox().Show("正在连接打印机...");
                    String deviceName = SharePrefUtils.getSavedDeviceName();
                    if (deviceName.startsWith("XT") || deviceName.startsWith("HDT")) {

                        PrintService.startService(mSelectedOrders);
                        return;
                    }

                    if (SharePrefUtils.getSavedDeviceName().startsWith("Printer_")) {
                        JiaboPrintService.startService(mSelectedOrders);
                    }


                }
            });

            content.findViewById(R.id.specify_order_to_print).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (recyclerView.getAdapter().getItemCount() == 0) {
                        SmartToast.showInCenter("无运单可选！");
                        return;
                    }
                    if (TextUtils.isEmpty(SharePrefUtils.getSavedDeviceAddress())) {
                        SmartToast.showInCenter("尚未选择任何打印机！");
                        return;
                    }

                    SharePrefUtils.setCancelConnect(false);
                    getSpecifyOrderPrintDialog().show();
                }
            });

            content.findViewById(R.id.select_printer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Set<BluetoothDevice> devices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
                    final String[] deviceNames = new String[devices.size() + 1];
                    deviceNames[0] = "选择其他打印设备";
                    int i = 1;
                    for (BluetoothDevice device : devices) {
                        deviceNames[i] = device.getName() + " " + device.getAddress();
                        i++;
                    }
                    AlertDialog dialog = new AlertDialog.Builder(BatchPrintActivity.this)
                            .setTitle("请选择")
                            .setItems(deviceNames, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            startActivityForResult(new Intent(BatchPrintActivity.this, BluetoothActivity.class), CHOOSE_DEVICE);
                                            break;
                                        default:
                                            int separator = deviceNames[which].indexOf(" ");
                                            String srcDevice = SharePrefUtils.getSavedDeviceName();
                                            SharePrefUtils.storeDeviceName(deviceNames[which].substring(0, separator));
                                            SharePrefUtils.storeDeviceAddress(deviceNames[which].substring(separator + 1, deviceNames[which].length()));
                                            ((TextView) mPrintPop.getContentView().findViewById(R.id.printer_name)).setText(SharePrefUtils.getSavedDeviceName());
                                            dialog.dismiss();
                                            try {
                                                if (!srcDevice.equals(SharePrefUtils.getSavedDeviceName()) && !TextUtils.isEmpty(srcDevice)) {
                                                    MyApplication.sMyApplication.mGpService.closePort(2);
                                                }
                                            } catch (RemoteException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                    }
                                }
                            }).create();
                    dialog.show();
                }
            });

        }
        int totalPieces = 0;
        for (int index = 0; index < mSelectedOrders.size(); index++) {
            totalPieces += Integer.parseInt(mSelectedOrders.get(index).getGoodsCount());
        }
        ((TextView) mPrintPop.getContentView().findViewById(R.id.print_num)).setText(String.valueOf(mSelectedOrders.size()) + " 票");
        ((TextView) mPrintPop.getContentView().findViewById(R.id.total_peicese)).setText(String.valueOf(totalPieces + " 件"));
        ((TextView) mPrintPop.getContentView().findViewById(R.id.printer_name)).setText(SharePrefUtils.getSavedDeviceName());
        return mPrintPop;
    }

    public AlertDialog getSpecifyOrderPrintDialog() {
        if (mSpecifyOrderPrintDialog == null) {
            View content = LayoutInflater.from(BatchPrintActivity.this).inflate(R.layout.specify_print, null);
            final EditText editText = (EditText) content.findViewById(R.id.order_start_sequence);
            final EditText editTextend = (EditText) content.findViewById(R.id.order_end_sequence);

            content.findViewById(R.id.continue_print).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String index = editText.getText().toString().trim();
                    if (TextUtils.isEmpty(index)) {
                        SmartToast.showInCenter("请输入打印的起始序号！");
                        return;
                    }
                    int total = recyclerView.getAdapter().getItemCount();
                    int intIndex = Integer.parseInt(index);
                    if (intIndex < 1 || intIndex > total) {
                        SmartToast.showInCenter("起始序号不存在！");
                        return;
                    }

                    List<PrintOrders.ReturnDataBean> original = ((PrintOrderAdapter) recyclerView.getAdapter()).getData();
                    String endIndex = editTextend.getText().toString().trim();
                    int intEndIndex = 0;
                    if (TextUtils.isEmpty(endIndex)) {
                        intEndIndex = total;
                    } else {
                        intEndIndex = Integer.parseInt(endIndex);
                        if (intEndIndex < intIndex) {
                            SmartToast.showInCenter("终止序号不能小于起始序号！");
                            return;
                        }
                        if (intEndIndex > original.size()) {
                            SmartToast.showInCenter("终止序号不存在！");
                            return;
                        }
                    }
                    SharePrefUtils.setPrintType(SharePrefUtils.PRINT_ORDER);
                    getStatusBox().Show("正在连接打印机...");
                    SharePrefUtils.setStartSequence(intIndex);
                    SharePrefUtils.setEndSequence(intEndIndex);
                    Logger.d("start" + intIndex + "end" + intEndIndex);
                    mSpecifyOrderPrintDialog.dismiss();
                    String deviceName = SharePrefUtils.getSavedDeviceName();

                    if (deviceName.startsWith("XT") || deviceName.startsWith("HDT")) {

                        ArrayList<PrintInfo> printInfoList = new ArrayList<PrintInfo>();

                        for (int i = intIndex - 1; i < intEndIndex; i++) {
                            PrintInfo printInfo = new PrintInfo(original.get(i), (i + 1) + "");
                            printInfoList.add(printInfo);
                        }


                        PrintService.startService(printInfoList);
                    } else if (deviceName.startsWith("Printer_")) {
                        ArrayList<PrintInfo> printInfos = new ArrayList<PrintInfo>();
                        for (int i = intIndex - 1; i < intEndIndex; i++) {

                            PrintInfo printInfo = new PrintInfo(original.get(i), (i + 1) + "");

                            printInfos.add(printInfo);
                        }

                        JiaboPrintService.startService(printInfos);
                    }

                }
            });

            mSpecifyOrderPrintDialog = new AlertDialog.Builder(this)
                    .setView(content)
                    .create();
            mSpecifyOrderPrintDialog.setCanceledOnTouchOutside(true);
        }

        return mSpecifyOrderPrintDialog;
    }

    @Override
    public void onBackPressed() {
        getStatusBox().Close();
        if (mPrintPop != null && mPrintPop.isShowing()) {
            mPrintPop.dismiss();
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_DEVICE && resultCode == RESULT_OK) {
            ((TextView) getPrintPop().getContentView().findViewById(R.id.printer_name)).setText(SharePrefUtils.getSavedDeviceName());
        }
    }

    private StatusBox mStatusBox;

    public StatusBox getStatusBox() {
        if (mStatusBox == null) {
            mStatusBox = new StatusBox(this, toolbar);
        }

        return mStatusBox;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivePrintEvent(PrintEvent printEvent) {
        switch (printEvent.getResult()) {
            case PrintEvent.CONNECT_SUCCESSFUL:
                getStatusBox().Show("连接成功，开始打印。");
                getStatusBox().Close();
                break;
            case PrintEvent.FAIL_BLUETOOTH_CLOSE:
                getStatusBox().Close();
                final Snackbar snackbar = Snackbar.make(toolbar, "蓝牙已关闭，请开启蓝牙后重试！", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("开启蓝牙", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                        BluetoothAdapter.getDefaultAdapter().enable();
                    }
                });
                break;
            case PrintEvent.FAIL_DEVICE_NOT_BONDED:
                getStatusBox().Close();
                final Snackbar snackbar2 = Snackbar.make(toolbar, "蓝牙已关闭，请开启蓝牙后重试！", Snackbar.LENGTH_INDEFINITE);
                snackbar2.setAction("去重新配对", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar2.dismiss();
                        startActivity(new Intent(BatchPrintActivity.this, BluetoothActivity.class));
                    }
                });
                break;
            case PrintEvent.FAIL_REASON_UNKNOWN:
                getStatusBox().Close();
                getPrintAlert().show();
                break;
            case PrintEvent.PRINT_COMPLETE:
                getStatusBox().Close();
                Snackbar.make(toolbar, "打印完成！", Snackbar.LENGTH_INDEFINITE);
                break;
            case PrintEvent.FAIL_OFFLINE:
                try {

                    MyApplication.sMyApplication.mGpService.openPort(2, PortParameters.BLUETOOTH, SharePrefUtils.getSavedDeviceAddress(), 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case PrintEvent.JIABO_FAIL:

                getStatusBox().Close();
                getPrintAlert().show();
                MyApplication.isConnect = false;
                break;
            case PrintEvent.PAPER_LACK:
                getStatusBox().Close();
                getPrintAlert().show();
                SmartToast.showLongInCenter("打印机缺纸，请续纸后重试！");
                break;
            case PrintEvent.PRINTER_ERROR:
                getStatusBox().Close();
                getPrintAlert().show();
                SmartToast.showLongInCenter("打印机内部错误，请重试！");
                break;
            case PrintEvent.MODE_ERROR:
                getStatusBox().Close();
                showSwitchModeTip(this);
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private AlertDialog mPrintAlert;

    public AlertDialog getPrintAlert() {
        if (mPrintAlert == null) {
            View content = LayoutInflater.from(this).inflate(R.layout.print_alert, null);
            content.findViewById(R.id.cancel_conn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPrintAlert.dismiss();
                }
            });
            content.findViewById(R.id.reconnect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPrintAlert.dismiss();
                    SharePrefUtils.setCancelConnect(false);
                    String deviceName = SharePrefUtils.getSavedDeviceName();
                    switch (SharePrefUtils.getPrintType()) {
                        case SharePrefUtils.PRINT_START:
                            getStatusBox().Show("正在重新连接打印机...");

                            if (deviceName.startsWith("XT") || deviceName.startsWith("HDT")) {
                                PrintService.startService(mSelectedOrders);
                            } else if (deviceName.startsWith("Printer_")) {
                                JiaboPrintService.startService(mSelectedOrders);
                            }

                            break;
                        case SharePrefUtils.PRINT_ORDER:
                            getStatusBox().Show("正在重新连接打印机...");

                            List<PrintOrders.ReturnDataBean> original = ((PrintOrderAdapter) recyclerView.getAdapter()).getData();
                            int intIndex = SharePrefUtils.getStartSequence();
                            int intEndIndex = SharePrefUtils.getEndSequence();

                            if (deviceName.startsWith("XT") || deviceName.startsWith("HDT")) {
                                ArrayList<PrintInfo> printInfoArrayList = new ArrayList<PrintInfo>();
                                for (int i = intIndex - 1; i < intEndIndex; i++) {
                                    PrintInfo printInfo = new PrintInfo(original.get(i), (i + 1) + "");
                                    printInfoArrayList.add(printInfo);
                                }

                                PrintService.startService(printInfoArrayList);

                            } else if (deviceName.startsWith("Printer_")) {
                                ArrayList<PrintInfo> printInfos = new ArrayList<PrintInfo>();
                                for (int i = intIndex - 1; i < intEndIndex; i++) {

                                    PrintInfo printInfo = new PrintInfo(original.get(i), (i + 1) + "");
                                    printInfos.add(printInfo);
                                }

                                JiaboPrintService.startService(printInfos);
                            }

                            break;
                    }
                }
            });
            mPrintAlert = new AlertDialog.Builder(this)
                    .setView(content)
                    .create();
        }

        return mPrintAlert;
    }

    @Override
    protected void onBaseItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

        switch (view.getId()) {
            case R.id.root:
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_mark);
                checkBox.setChecked(!checkBox.isChecked());
                ((PrintOrders.ReturnDataBean) baseQuickAdapter.getData().get(i)).setChecked(checkBox.isChecked());
                break;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.filter_mark)
    public void onViewClicked() {
        getDateSelector().show();
    }


    private AlertDialog mDateSelector;
    private ViewFlipper mViewFlipper;

    private Date mBeginDate;
    private Date mEndDate;

    private String mTripNo = "";

    private RecyclerView mTripNosRecyclerView;

    public AlertDialog getDateSelector() {

        mViewFlipper = new ViewFlipper(this);
        final View beginDate = LayoutInflater.from(this).inflate(R.layout.date_seletor, null);
        final DatePicker beginDatePicker = (DatePicker) beginDate.findViewById(R.id.date_picker);
        beginDatePicker.setMaxDate(new Date().getTime());
        DateTool.CALENDAR.setTime(new Date());
        DateTool.CALENDAR.add(Calendar.DAY_OF_MONTH, -365);
        beginDatePicker.setMinDate(DateTool.CALENDAR.getTime().getTime());
        View beginConfirm = beginDate.findViewById(R.id.confirm_btn);
        View beginCancel = beginDate.findViewById(R.id.cancel_btn);

        View endDate = LayoutInflater.from(this).inflate(R.layout.date_seletor, null);
        final DatePicker endDatePicker = (DatePicker) endDate.findViewById(R.id.date_picker);
        View endConfirm = endDate.findViewById(R.id.confirm_btn);
        View endCancel = endDate.findViewById(R.id.cancel_btn);
        endDatePicker.setMaxDate(new Date().getTime());
        ((TextView) endDate.findViewById(R.id.title)).setText("请选择结束时间");
        endConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateTool.CALENDAR.set(endDatePicker.getYear(), endDatePicker.getMonth(), endDatePicker.getDayOfMonth());
                mEndDate = DateTool.CALENDAR.getTime();
                if (!mEndDate.equals(mBeginDate)) {
                    mTripNo = "";
                    formatTripNos = "";
                    mDateSelector.dismiss();
                    mSendDateLabel.setText("发货日期：");
                    mSelectedDate.setText(DateTool.getDateStr(mBeginDate, "yyyy.MM.dd - ") + DateTool.getDateStr(mEndDate, "yyyy.MM.dd"));
                    onRefresh();
                } else {
                    Utils.showNext(mViewFlipper);
                }

            }
        });

        endCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDatePicker.setMinDate(1);
                Utils.showPrevious(mViewFlipper);
            }
        });
        beginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateTool.CALENDAR.set(beginDatePicker.getYear(), beginDatePicker.getMonth(), beginDatePicker.getDayOfMonth());
                mBeginDate = DateTool.CALENDAR.getTime();
                endDatePicker.setMinDate(mBeginDate.getTime());
                MyApplication.sCancelNetTip = true;
                mTripEmptyView.setVisibility(View.GONE);
                HttpTools.getTripNo(BatchPrintActivity.this, BatchPrintActivity.this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"));
                Utils.showNext(mViewFlipper);
            }
        });

        beginCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDateSelector.dismiss();
            }
        });


        View thirdPage = LayoutInflater.from(this).inflate(R.layout.tripno_list_item, null);
        mTripEmptyView = thirdPage.findViewById(R.id.empty_view);
        mTripNosRecyclerView = (RecyclerView) thirdPage.findViewById(R.id.tripno_list);
        mTripNosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTripNosRecyclerView.setAdapter(new TripNoAdapter(new ArrayList<TripNos.ReturnDataBean>()));
        mTripNosRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TripNos.ReturnDataBean tripNo = (TripNos.ReturnDataBean) baseQuickAdapter.getData().get(i);
                tripNo.setChecked(!tripNo.isChecked());
                baseQuickAdapter.notifyDataSetChanged();
            }
        });
        Button tripConfirmBtn = (Button) thirdPage.findViewById(R.id.confirm_btn);
        Button tripCancelBtn = (Button) thirdPage.findViewById(R.id.cancel_btn);
        tripCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showPrevious(mViewFlipper);
            }
        });
        tripConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TripNos.ReturnDataBean> s = ((TripNoAdapter) mTripNosRecyclerView.getAdapter()).getData();


                int i = 0;
                String tripNos = "";
                formatTripNos = "";
                for (i = 0; i < s.size(); i++) {
                    TripNos.ReturnDataBean tripNo = s.get(i);
                    if (tripNo.isChecked()) {
                        tripNos = tripNos + tripNo.getTripNo() + ",";
                        formatTripNos = formatTripNos + tripNo.getTripNo() + "\n\n";
                    }
                }

                if (!s.isEmpty() && TextUtils.isEmpty(tripNos)) {
                    SmartToast.showInCenter("必须选择交接单号！");
                    return;
                }

                if (s.isEmpty()) {
                    mTripNo = "";
                } else {
                    mTripNo = tripNos.substring(0, tripNos.length() - 1);
                }
                mDateSelector.dismiss();
                mSendDateLabel.setText("交接单：");

                if (!TextUtils.isEmpty(formatTripNos)){
                    mSelectedDate.setText(formatTripNos.substring(0,formatTripNos.length() - 1));
                }

                onRefresh();
            }
        });
        mViewFlipper.addView(beginDate);
        mViewFlipper.addView(endDate);
        mViewFlipper.addView(thirdPage);
        mViewFlipper.setInAnimation(this, R.anim.slide_in_right);
        mViewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        mDateSelector = new AlertDialog.Builder(this)
                .setView(mViewFlipper)
                .create();
        mDateSelector.setCanceledOnTouchOutside(false);
        NumberPicker numberPicker = new NumberPicker(this);
        DatePickerUtils.setDatePickerDividerColor(beginDatePicker);
        DatePickerUtils.setDatePickerDividerColor(endDatePicker);

        return mDateSelector;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    private String mSequence = "1";

    @OnClick(R.id.sequence_mark)
    public void onSequenceMarkClicked() {
        switch (mSequenceMark.getText().toString()) {
            case "按发货时间":
                mSequenceMark.setText("按收货人");
                mSequence = "2";
                break;
            case "按收货人":
                mSequenceMark.setText("按发货时间");
                mSequence = "1";
                break;
        }

        mSequenceMark.setEnabled(false);
        if (!TextUtils.isEmpty(mTripNo)) {
            HttpTools.getPrintOrderList(this, this, "", "", mTripNo, mKeywords, mSequence, pageIndex, pagesize);

        } else {
            HttpTools.getPrintOrderList(this, this, DateTool.getDateStr(mBeginDate, "yyyy-MM-dd"), DateTool.getDateStr(mEndDate, "yyyy-MM-dd"), mTripNo, mKeywords, mSequence, pageIndex, pagesize);
        }
    }

}
