package com.liinji.ppgdeliver.print;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.liinji.ppgdeliver.bean.CommonOutput;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.utils.SharePrefUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.liinji.ppgdeliver.print.ForwardIndicator.generateNewValueNotSaved;

/**
 * Created by 朱志强 on 2017/8/14.
 */

public class PrintService extends IntentService {

    public static final String EXTRA_PRINTER_ADDRESS = "device_address";
    public static final String EXTRA_SINGLE_DATA = "single_data";
    public static final String EXTRA_MULTIPLE_DATA = "multiple_data";


    public PrintService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("onCreate");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected synchronized void onHandleIntent(@Nullable Intent intent) {
        if (SharePrefUtils.getCancelConnect()) {
            return;
        }
        String address = intent.getStringExtra(EXTRA_PRINTER_ADDRESS);
        PrintInfo printInfo = intent.getParcelableExtra(EXTRA_SINGLE_DATA);
        List<PrintInfo> printInfoList = intent.getParcelableArrayListExtra(EXTRA_MULTIPLE_DATA);

        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
        boolean connSuccessful = ZicoxUtil.getPrinter().connect(device.getAddress());


        if (!connSuccessful) {
            SharePrefUtils.setCancelConnect(true);
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.FAIL_BLUETOOTH_CLOSE));
                return;
            }

            if (BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address).getBondState() != BluetoothDevice.BOND_BONDED) {
                EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.FAIL_DEVICE_NOT_BONDED));
                return;
            }

            EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.FAIL_REASON_UNKNOWN));
            return;
        }

        EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.CONNECT_SUCCESSFUL));


        if (printInfo != null) {

            printSingle(printInfo);
            ZicoxUtil.getPrinter().disconnect();
            if (printInfo.isNeedPrintSequence() == 0) {
                SystemClock.sleep(6500);
                EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.PRINT_COMPLETE));
            }

        }

        if (printInfoList != null && !printInfoList.isEmpty()) {

            for (int i = 0; i < printInfoList.size(); i++) {
                printSingle(printInfoList.get(i));

                SystemClock.sleep(5000);
            }
            ZicoxUtil.getPrinter().disconnect();
        }

    }


    public void printSingle(PrintInfo printInfo) {

        int realHeight = ZicoxSetting.PAGE_HEIGHT;

        if (!TextUtils.isEmpty(printInfo.getPaperNumber()) && !TextUtils.isEmpty(printInfo.getWaybillCargoNo())) {
            realHeight += 84;
        }


        if (printInfo.getSender().length() > 14) {
            realHeight += 150;
        }


        if (printInfo.getReceiver().length() > 14) {
            realHeight += 150;
        }


        if (printInfo.getReceiverAddress().length() > 20) {
            realHeight += 70;
        }


        if (printInfo.getRemark().length() > 20 && printInfo.getRemark().length() <= 43) {
            realHeight += 70;
        }

        if (printInfo.getRemark().length() > 43) {
            realHeight += 140;
        }

        int lines = printInfo.getAdWords().length() / 23;
        int remain = printInfo.getAdWords().length() % 23;

        realHeight += lines * 70 + ((lines == 0 && remain != 0) ? 70 : 0);


        ZicoxUtil.getPrinter().pageSetup(ZicoxSetting.PAGE_WIDTH, realHeight);


        printHelper(0, printInfo);

        ZicoxUtil.getPrinter().print(0, 0);


    }


    private void printHelper(int index, PrintInfo printInfo) {

        //打印上联


        CommonOutput topCommonOutput = commonPrintMost(printInfo, ZicoxSetting.CONTENT_START_Y, null, null);

        if (!TextUtils.isEmpty(printInfo.getSequence())) {
            ZicoxUtil.drawText("序号：" + printInfo.getSequence(), ForwardIndicator.generateNewValue(), 2, false);
        }

        commonPrintLittle(printInfo, topCommonOutput, ZicoxSetting.CONTENT_START_Y);

        ForwardIndicator.setCurrentValueByAdd(24);

        ZicoxUtil.drawText("-------------------------------------------- ", ForwardIndicator.generateNewValue(), 2, false);

        ForwardIndicator.setCurrentValueByAdd(40);

//        打印下联


        int bottomPageStartY = ForwardIndicator.generateNewValue();


        CommonOutput bottomCommonOutput = commonPrintMost(printInfo, bottomPageStartY, topCommonOutput.getBarcodeBmp(), topCommonOutput.getQRcodeBmp());


        ZicoxUtil.drawText("客服电话(代收查询):" + printInfo.getCustomerService(), ForwardIndicator.generateNewValue(), 2, false);
        if (printInfo.getDispatchBranchAddress().length() <= 11) {
            ZicoxUtil.drawText("网点地址：" + printInfo.getDispatchBranchAddress(), ForwardIndicator.generateNewValue(), 2, false);
        } else {
            ZicoxUtil.drawText("网点地址：" + printInfo.getDispatchBranchAddress().substring(0, 11), ForwardIndicator.generateNewValue(), 2, false);
            ZicoxUtil.drawText(printInfo.getDispatchBranchAddress().substring(11), ForwardIndicator.generateNewValue(), 2, false);
        }

        commonPrintLittle(printInfo, bottomCommonOutput, bottomPageStartY);

    }


    private void commonPrintLittle(PrintInfo printInfo, CommonOutput topCommonOutput, int startY) {
        ZicoxUtil.drawText("发货日期：" + printInfo.getGoodsSentTime(), ForwardIndicator.generateNewValue(), 2, false);
        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2);

        int signVLineY = ForwardIndicator.generateNewValueNotSaved();

        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        //打印广告

        int times = printInfo.getAdWords().length() / 23;
        int remain = printInfo.getAdWords().length() % 23;

        for (int i = 0;i < times;i++){
            if (i == times - 1 && remain < 5){
                ZicoxUtil.drawText(printInfo.getAdWords().substring(23 * i,printInfo.getAdWords().length()),ForwardIndicator.generateNewValue(),2,false);
                break;
            }
            ZicoxUtil.drawText(printInfo.getAdWords().substring(23 * i,23 + i * 23),ForwardIndicator.generateNewValue(),2,false);
        }

        if (remain > 5){
            ZicoxUtil.drawText(printInfo.getAdWords().substring(printInfo.getAdWords().length() - remain,printInfo.getAdWords().length()),ForwardIndicator.generateNewValue(),2,false);
        }
        int secondVLineEndY = ForwardIndicator.generateNewValueNotSaved();
        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        ZicoxUtil.drawVLine(ZicoxUtil.endShift(-6 * ZicoxUtil.getFontWordSize(2) - 16), topCommonOutput.getSecondVLineStartY(), signVLineY);

        ZicoxUtil.drawVLine(0, startY, secondVLineEndY);



        ZicoxUtil.drawVLine(ZicoxSetting.PAGE_H_END, startY, secondVLineEndY);
    }

    public static CommonOutput commonPrintMost(PrintInfo printInfo, int startY, Bitmap barcodeBmp, Bitmap qrbmp) {

        CommonOutput commonOutput = new CommonOutput();
        commonOutput.setBarcodeBmp(barcodeBmp);
        commonOutput.setQRcodeBmp(qrbmp);

        ZicoxUtil.drawHLine(startY);
        ForwardIndicator.init(startY, ZicoxSetting.LINE_SPACE);


        if (!TextUtils.isEmpty(printInfo.getCompanyUrl()) && false) {
            ZicoxUtil.drawImgInCenter(MyApplication.sCompanyLogo, ForwardIndicator.generateNewValue());
            ForwardIndicator.setCurrentValueByAdd(ZicoxSetting.IMG_SPACE);
        } else {
            ZicoxUtil.drawTextInCenter(printInfo.getCompanyName(), ForwardIndicator.generateNewValue(), 4, true);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(4));
        }


        if (!TextUtils.isEmpty(printInfo.getPaperNumber())) {
            ZicoxUtil.drawText("纸质单号：" + printInfo.getPaperNumber(), generateNewValueNotSaved(), 2, false);
            ZicoxUtil.drawTextAtRight(printInfo.getClerkName(), ForwardIndicator.generateNewValue(), 2, false);
            if (!TextUtils.isEmpty(printInfo.getWaybillCargoNo())) {
                ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(2));
                ZicoxUtil.drawText("货号：" + printInfo.getWaybillCargoNo(), ForwardIndicator.generateNewValue(), 2, false);
            }

        } else if (!TextUtils.isEmpty(printInfo.getWaybillCargoNo())) {
            ZicoxUtil.drawText("货号：" + printInfo.getWaybillCargoNo(), generateNewValueNotSaved(), 2, false);
            ZicoxUtil.drawTextAtRight(printInfo.getClerkName(), ForwardIndicator.generateNewValue(), 2, false);
        } else {
            ZicoxUtil.drawTextAtRight(printInfo.getClerkName(), ForwardIndicator.generateNewValue(), 2, false);
        }
        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(2));
        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        ForwardIndicator.setIncreaseAmount(12);

        ZicoxUtil.drawBarCode(printInfo.getOrderNumber(), ForwardIndicator.generateNewValueNotSaved());

        ZicoxUtil.drawQRCode(384, ForwardIndicator.generateNewValue(), printInfo.getOrderNumber(), 2);

        ForwardIndicator.setIncreaseAmount(95);
        ZicoxUtil.drawTextAtX(printInfo.getSpaceOrder(), ZicoxSetting.CONTENT_START_X + 40, ForwardIndicator.generateNewValue(), 3, true);
        ;
        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 12);

        commonOutput.setFirstVLineStart(generateNewValueNotSaved());

        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());
        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE);

        ZicoxUtil.drawText("寄件点：" + printInfo.getSBranchName(), generateNewValueNotSaved(), 2, false);

        ZicoxUtil.drawTextAtX("派件点：" + printInfo.getPBranchName(), ZicoxSetting.CONTENT_HALF_START_X, ForwardIndicator.generateNewValue(), 2, false);
        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 8);
        ZicoxUtil.drawText("电话：" + printInfo.getSendBranchContactTel(), generateNewValueNotSaved(), 2, false);

        ZicoxUtil.drawTextAtX("电话：" + printInfo.getDispatchBranchContactTel(), ZicoxSetting.CONTENT_HALF_START_X, ForwardIndicator.generateNewValue(), 2, false);

        commonOutput.setFirstVLineYEnd(generateNewValueNotSaved());
        ;

        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        ZicoxUtil.drawVLineInCenter(commonOutput.getFirstVLineStart(), commonOutput.getFirstVLineYEnd());


        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE);
        ZicoxUtil.drawText("发货人：", generateNewValueNotSaved() + 7, 2, false);
        if (printInfo.getSender().length() <= 9) {
            ZicoxUtil.drawTextAtX(printInfo.getSender(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), generateNewValueNotSaved(), 3, true);
            ZicoxUtil.drawTextAtX(printInfo.getSenderTel(), (ZicoxSetting.PAGE_H_END / 2 + 130), ForwardIndicator.generateNewValue(), 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 12);
        } else if (printInfo.getSender().length() <= 14) {
            ZicoxUtil.drawTextAtX(printInfo.getSender(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue(), 3, true);
            ZicoxUtil.drawTextAtX(printInfo.getSenderTel(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue() + 25, 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 24);
        } else {
            ZicoxUtil.drawTextAtX(printInfo.getSender().substring(0, 14), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue(), 3, true);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(3));
            ZicoxUtil.drawTextAtX(printInfo.getSender().substring(14), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue() + 25, 3, true);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(3));
            ZicoxUtil.drawTextAtX(printInfo.getSenderTel(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue() + 56, 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 56);
        }

        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE);
        ZicoxUtil.drawText("收货人：", generateNewValueNotSaved() + 7, 2, false);

        if (printInfo.getReceiver().length() <= 9) {
            ZicoxUtil.drawTextAtX(printInfo.getReceiver(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), generateNewValueNotSaved(), 3, true);
            ZicoxUtil.drawTextAtX(printInfo.getReceiverTel(), (ZicoxSetting.PAGE_H_END / 2 + 130), ForwardIndicator.generateNewValue(), 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 12);
        } else if (printInfo.getReceiver().length() <= 14) {
            ZicoxUtil.drawTextAtX(printInfo.getReceiver(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue(), 3, true);
            ZicoxUtil.drawTextAtX(printInfo.getReceiverTel(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue() + 25, 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 24);
        } else {
            ZicoxUtil.drawTextAtX(printInfo.getReceiver().substring(0, 14), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue(), 3, true);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(3));
            ZicoxUtil.drawTextAtX(printInfo.getReceiver().substring(14), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue() + 25, 3, true);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + ZicoxUtil.getFontWordSize(3));
            ZicoxUtil.drawTextAtX(printInfo.getReceiverTel(), ZicoxSetting.CONTENT_START_X + 4 * ZicoxUtil.getFontWordSize(2), ForwardIndicator.generateNewValue() + 56, 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2 + 56);
        }


        if (printInfo.getReceiverAddress().length() <= 20) {
            ZicoxUtil.drawText("地址：" + printInfo.getReceiverAddress(), ForwardIndicator.generateNewValue(), 2, false);
        } else {
            ZicoxUtil.drawText("地址：" + printInfo.getReceiverAddress().substring(0, 20), ForwardIndicator.generateNewValue(), 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + 14);
            ZicoxUtil.drawText(printInfo.getReceiverAddress().substring(20), ForwardIndicator.generateNewValue(), 2, false);
        }

        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2);

        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE);

        ZicoxUtil.drawText("品名：" + printInfo.getGoodsName(), ForwardIndicator.generateNewValueNotSaved() + 7, 2, false);

        ZicoxUtil.drawTextAtX("运费：", ZicoxUtil.halfShift(-5), ForwardIndicator.generateNewValueNotSaved() + 7, 2, false);
        if (printInfo.getPayModeSuffix().length() < 8) {
            ZicoxUtil.drawTextAtX(printInfo.getFreight() + "/" + printInfo.getSettlementWay() + printInfo.getPayModeSuffix(), ZicoxUtil.halfShift(72), ForwardIndicator.generateNewValue(), 3, true);
        } else {
            ZicoxUtil.drawTextAtX(printInfo.getFreight() + "/" + printInfo.getSettlementWay(), ZicoxUtil.halfShift(72), ForwardIndicator.generateNewValue(), 3, true);
            ZicoxUtil.drawTextAtX(printInfo.getPayModeSuffix(), ZicoxUtil.halfShift(100), ForwardIndicator.generateNewValue() + 16, 3, true);
        }

        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 3);

        ZicoxUtil.drawText("件数：", generateNewValueNotSaved() + 7, 2, false);

        ZicoxUtil.drawTextAtX(printInfo.getGoodsCount(), ZicoxUtil.startShift(72), generateNewValueNotSaved(), 3, true);


        ZicoxUtil.drawTextAtX(" 件", ZicoxUtil.startShift(72 + ZicoxUtil.getFontWordSize(2) * printInfo.getGoodsCount().length()), generateNewValueNotSaved() + 7, 2, false);


        ZicoxUtil.drawTextAtX("", ZicoxUtil.halfShift(-5), generateNewValueNotSaved() + 7, 2, false);

        ZicoxUtil.drawTextAtX("含送货费 " + printInfo.getDeliveryFee(), ZicoxUtil.halfShift(72), ForwardIndicator.generateNewValue(), 2, false);
        ;
        ZicoxUtil.drawText("代收款：", generateNewValueNotSaved() + 7, 2, false);

        ZicoxUtil.drawTextAtX(printInfo.getCollectionFee(), ZicoxUtil.startShift(4 * ZicoxUtil.getFontWordSize(2)), ForwardIndicator.generateNewValueNotSaved(), 3, true);

        ZicoxUtil.drawTextAtX("垫付款:", ZicoxUtil.halfShift(-5), generateNewValueNotSaved() + 7, 2, false);

        ZicoxUtil.drawTextAtX(printInfo.getAdvanceTransitFee(), ZicoxUtil.halfShift(88), ForwardIndicator.generateNewValue(), 3, true);


        if (printInfo.getRemark().length() <= 20) {
            ZicoxUtil.drawText("备注：" + printInfo.getRemark(), ForwardIndicator.generateNewValue(), 2, false);
        } else if (printInfo.getRemark().length() <= 43) {
            ZicoxUtil.drawText("备注：" + printInfo.getRemark().substring(0, 20), ForwardIndicator.generateNewValue(), 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2);
            ZicoxUtil.drawText(printInfo.getRemark().substring(20), ForwardIndicator.generateNewValue(), 2, false);
        } else {
            ZicoxUtil.drawText("备注：" + printInfo.getRemark().substring(0, 20), ForwardIndicator.generateNewValue(), 2, false);
            ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2);
            ZicoxUtil.drawText(printInfo.getRemark().substring(20, 43), ForwardIndicator.generateNewValue(), 2, false);
            if (printInfo.getRemark().length() > 66) {
                ZicoxUtil.drawText(printInfo.getRemark().substring(43, 66), ForwardIndicator.generateNewValue(), 2, false);
            } else {
                ZicoxUtil.drawText(printInfo.getRemark().substring(43), ForwardIndicator.generateNewValue(), 2, false);
            }

        }


        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE * 2);

        commonOutput.setSecondVLineStartY(generateNewValueNotSaved());

        ZicoxUtil.drawHLine(ForwardIndicator.generateNewValue());

        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE);

        ZicoxUtil.drawText("注：1.此票有效期一个月;", generateNewValueNotSaved(), 2, false);

        ZicoxUtil.drawTextAtX("收货人签字：", ZicoxUtil.endShift(-6 * ZicoxUtil.getFontWordSize(2)), ForwardIndicator.generateNewValue(), 2, false);
        ForwardIndicator.setIncreaseAmount(ZicoxSetting.LINE_SPACE + 12);
        ZicoxUtil.drawText("2.货物一旦签收，收货人即认可此票", ForwardIndicator.generateNewValue(), 2, false);
        ZicoxUtil.drawText("货物无异议", ForwardIndicator.generateNewValue(), 2, false);

        return commonOutput;
    }


    public static void startService(PrintInfo printInfo) {
        Intent intent = new Intent(MyApplication.sContext, PrintService.class);
        intent.putExtra(EXTRA_PRINTER_ADDRESS, SharePrefUtils.getSavedDeviceAddress())
                .putExtra(EXTRA_SINGLE_DATA, printInfo);
        MyApplication.sContext.startService(intent);
    }

    public static void startService(ArrayList<PrintInfo> printInfos) {

        Intent intent = new Intent(MyApplication.sContext, PrintService.class);
        intent.putExtra(EXTRA_PRINTER_ADDRESS, SharePrefUtils.getSavedDeviceAddress())
                .putParcelableArrayListExtra(EXTRA_MULTIPLE_DATA, printInfos);
        MyApplication.sContext.startService(intent);
    }


    public static String processSentTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }

        int index = time.indexOf(" ");

        if (index == -1) {
            return time;
        }

        return time.substring(0, index);

    }


}