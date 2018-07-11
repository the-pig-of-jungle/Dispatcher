package com.liinji.ppgdeliver.jiaboprint;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;

import com.gprinter.command.EscCommand;
import com.gprinter.command.GpCom;
import com.gprinter.command.GpUtils;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.configure.MyApplication;
import com.liinji.ppgdeliver.print.PrintEvent;
import com.liinji.ppgdeliver.print.PrintInfo;
import com.liinji.ppgdeliver.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.Vector;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 朱志强 on 2017/9/27.
 */

public class PrintUtil {


    //ESC指令打印
    public static void sendESCPrintInfo(PrintInfo printInfo) {

        EscCommand escCommand = ESCUtil.init();
        escCommand.addSelectInternationalCharacterSet(EscCommand.CHARACTER_SET.CHINA);
        escCommand.addQueryPrinterStatus();


        commonPrint(printInfo, escCommand);

        escCommand.addText("注：1.此票有效期一个月；");
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.drawTextAtPos(escCommand, PrintInfo.SIGN_TIP, 340);
        ESCUtil.lineFeed(escCommand);
        escCommand.addText("2.货物一旦签收，收货人即认");
        ESCUtil.drawVLineAtPos(escCommand, 310);
        escCommand.addText("\n可此票货物无异议");
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.lineFeed(escCommand);
        String sequence = TextUtils.isEmpty(printInfo.getSequence()) ? "" : "序号：" + printInfo.getSequence();
        escCommand.addText(sequence);
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.lineFeed(escCommand);
        escCommand.addText("发货日期：" + printInfo.getGoodsSentTime());
        ESCUtil.drawVLineAtPos(escCommand, 310);
        ESCUtil.lineFeed(escCommand);
        ESCUtil.drawHLine(escCommand);
        escCommand.addText("\n");

        if (!printInfo.getAdWords().trim().isEmpty()) {
            escCommand.addText("    " + printInfo.getAdWords() + "\n");
            ESCUtil.drawHLine(escCommand);
        }

        ESCUtil.lineFeed(escCommand, 2);
        ESCUtil.drawDashHLine(escCommand);

        ESCUtil.lineFeed(escCommand, 2);


        //打印下联


        commonPrint(printInfo, escCommand);

        escCommand.addText("注：1.此票有效期一个月；");
        ESCUtil.drawVLineAtPos(escCommand, 370);
        ESCUtil.drawTextAtPos(escCommand, PrintInfo.SIGN_TIP, 390);
        ESCUtil.lineFeed(escCommand);
        escCommand.addText("2.货物一旦签收，收货人即认可此");
        ESCUtil.drawVLineAtPos(escCommand, 370);
        escCommand.addText("\n票货物无异议");
        ESCUtil.drawVLineAtPos(escCommand, 370);

        escCommand.addText("\n客服电话(代收查询):" + printInfo.getCustomerService());
        ESCUtil.drawVLineAtPos(escCommand, 370);
        if (printInfo.getDispatchBranchAddress().length() > 10) {
            escCommand.addText("\n网点地址：" + printInfo.getDispatchBranchAddress().substring(0, 10));
            ESCUtil.drawVLineAtPos(escCommand, 370);
            escCommand.addText("\n" + printInfo.getDispatchBranchAddress().substring(10, printInfo.getDispatchBranchAddress().length()));
            ESCUtil.drawVLineAtPos(escCommand, 370);
        } else {
            escCommand.addText("\n网点地址：" + printInfo.getDispatchBranchAddress());
            ESCUtil.drawVLineAtPos(escCommand, 370);
        }


        escCommand.addText("\n发货日期：" + printInfo.getGoodsSentTime());
        ESCUtil.drawVLineAtPos(escCommand, 370);
        ESCUtil.lineFeed(escCommand);
        ESCUtil.drawHLine(escCommand);
        escCommand.addText("\n");

        if (!printInfo.getAdWords().trim().isEmpty()) {
            escCommand.addText("    " + printInfo.getAdWords() + "\n");
            ESCUtil.drawHLine(escCommand);
        }

        ESCUtil.lineFeed(escCommand);
        ESCUtil.lineFeed(escCommand);


        ESCUtil.cutPaper(escCommand);

        Vector<Byte> data = escCommand.getCommand();
        byte[] bytes = GpUtils.ByteTo_byte(data);
        String resultStr = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rs;
        try {
            rs = MyApplication.sMyApplication.mGpService.sendEscCommand(2, resultStr);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rs];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                EventBus.getDefault().postSticky(new PrintEvent(PrintEvent.FAIL_REASON_UNKNOWN));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private static void commonPrint(PrintInfo printInfo, EscCommand escCommand) {
        ESCUtil.plainText(escCommand);
        ESCUtil.leftContent(escCommand);
        ESCUtil.drawHLine(escCommand);
        ESCUtil.lineFeed(escCommand, 1);
        ESCUtil.centerContent(escCommand);

        if (TextUtils.isEmpty(printInfo.getCompanyUrl().trim()) || MyApplication.sCompanyLogo == null) {
            ESCUtil.bigText(escCommand);
            escCommand.addText((printInfo.getCompanyName() == null) ? "" : printInfo.getCompanyName());
            ESCUtil.lineFeed(escCommand, TextUtils.isEmpty(printInfo.getPaperNumber()) && TextUtils.isEmpty(printInfo.getWaybillCargoNo()) ? 1 : 2);
        } else {
            escCommand.addRastBitImage(MyApplication.sCompanyLogo, MyApplication.sCompanyLogo.getWidth(), 0);
        }

        ESCUtil.leftContent(escCommand);
        ESCUtil.plainText(escCommand);

        if (!TextUtils.isEmpty(printInfo.getPaperNumber())) {
            escCommand.addText("纸质单号：" + printInfo.getPaperNumber());
            ESCUtil.posHorizontal(escCommand, 480);
            escCommand.addText(printInfo.getClerkName());

            if (!TextUtils.isEmpty(printInfo.getWaybillCargoNo())) {
                ESCUtil.lineFeed(escCommand);
                escCommand.addText("货号：");
                escCommand.addText(printInfo.getWaybillCargoNo());
            }
        } else if (!TextUtils.isEmpty(printInfo.getWaybillCargoNo())) {
            escCommand.addText("货号：");
            escCommand.addText(printInfo.getWaybillCargoNo());
            ESCUtil.posHorizontal(escCommand, 480);
            escCommand.addText(printInfo.getClerkName());

        } else {
            ESCUtil.posHorizontal(escCommand, 480);
            escCommand.addText(printInfo.getClerkName());
        }


        ESCUtil.lineFeed(escCommand);


        ESCUtil.drawHLine(escCommand);
        ESCUtil.lineFeed(escCommand);

        escCommand.addRastBitImage(Utils.createPrintImg(printInfo), 600, 2);

        ESCUtil.setLineSpace(escCommand, 1);
        ESCUtil.lineFeed(escCommand);
        ESCUtil.setDefaultLineSpace(escCommand);


        ESCUtil.setDefaultLineSpace(escCommand);
        ESCUtil.leftContent(escCommand);
        ESCUtil.plainText(escCommand);
        ESCUtil.drawHLine(escCommand);
        ESCUtil.lineFeed(escCommand, 1);

        escCommand.addText("寄件点：" + printInfo.getSBranchName());
        ESCUtil.drawVLineAtPos(escCommand, 270);
        ESCUtil.drawTextAtPos(escCommand, "派件点：" + printInfo.getPBranchName(), 310);
        ESCUtil.lineFeed(escCommand);
        ESCUtil.drawVLineAtPos(escCommand, 270);
        ESCUtil.lineFeed(escCommand);
        escCommand.addText("电话：" + printInfo.getSendBranchContactTel());
        ESCUtil.drawVLineAtPos(escCommand, 270);
        ESCUtil.drawTextAtPos(escCommand, "电话：" + printInfo.getDispatchBranchContactTel(), 310);

        ESCUtil.lineFeed(escCommand);

        ESCUtil.drawHLine(escCommand);

        ESCUtil.lineFeed(escCommand, 1);

        escCommand.addText("发货人:");

        ESCUtil.boldText(escCommand);

        if (printInfo.getSender().length() <= 7) {
            escCommand.addText(printInfo.getSender());
            ESCUtil.plainText(escCommand);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getSenderTel(), 390);
            ESCUtil.lineFeed(escCommand);
        } else if (printInfo.getSender().length() <= 10) {
            escCommand.addText(printInfo.getSender());
            ESCUtil.lineFeed(escCommand);
            ESCUtil.plainText(escCommand);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getSenderTel(), 420);
            ESCUtil.lineFeed(escCommand);
        } else {
            escCommand.addText(printInfo.getSender().substring(0, 10));
            ESCUtil.lineFeed(escCommand, 1);
            escCommand.addText(printInfo.getSender().substring(10));
            ESCUtil.lineFeed(escCommand);
            ESCUtil.plainText(escCommand);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getSenderTel(), 80);
            ESCUtil.lineFeed(escCommand);
        }

        ESCUtil.plainText(escCommand);
        ESCUtil.drawHLine(escCommand);
        ESCUtil.lineFeed(escCommand, 1);
        escCommand.addText("收货人:");
        ESCUtil.boldText(escCommand);
        if (printInfo.getReceiver().length() <= 7) {
            escCommand.addText(printInfo.getReceiver());
            ESCUtil.plainText(escCommand);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getReceiverTel(), 390);
            ESCUtil.lineFeed(escCommand);
        } else if (printInfo.getReceiver().length() <= 10) {
            escCommand.addText(printInfo.getReceiver());
            ESCUtil.lineFeed(escCommand);
            ESCUtil.plainText(escCommand);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getReceiverTel(), 80);
            ESCUtil.lineFeed(escCommand);
        } else {
            escCommand.addText(printInfo.getReceiver().substring(0, 10));
            ESCUtil.lineFeed(escCommand, 1);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getReceiver().substring(10), 80);
            ESCUtil.lineFeed(escCommand, 1);
            ESCUtil.plainText(escCommand);
            ESCUtil.drawTextAtPos(escCommand, printInfo.getReceiverTel(), 80);
            ESCUtil.lineFeed(escCommand);
        }

        escCommand.addText("地址：" + printInfo.getReceiverAddress());
        ESCUtil.lineFeed(escCommand);
        ESCUtil.drawHLine(escCommand);
        ESCUtil.lineFeed(escCommand, 1);
        escCommand.addText("品名：" + printInfo.getGoodsName());
        ESCUtil.drawTextAtPos(escCommand, "运费：", 230);
        ESCUtil.boldText(escCommand);
        if (printInfo.getFreight().length() <= 6) {
            if (printInfo.getPayModeSuffix().length() < 6) {
                escCommand.addText(printInfo.getFreight() + "/" + printInfo.getSettlementWay() + printInfo.getPayModeSuffix());
            } else {
                escCommand.addText(printInfo.getFreight() + "/" + printInfo.getSettlementWay());
                ESCUtil.lineFeed(escCommand);
                ESCUtil.drawTextAtPos(escCommand, printInfo.getPayModeSuffix(), 335);
            }


        } else {

            escCommand.addText(printInfo.getFreight());

            ESCUtil.lineFeed(escCommand);

            ESCUtil.drawTextAtPos(escCommand, printInfo.getSettlementWay() + printInfo.getPayModeSuffix(), 360);
        }

        ESCUtil.plainText(escCommand);
        ESCUtil.lineFeed(escCommand);
        ESCUtil.drawTextAtPos(escCommand, "含送货费 " + printInfo.getDeliveryFee(), 301);
        escCommand.addText("\n件数：");

        ESCUtil.boldText(escCommand);
        escCommand.addText(printInfo.getGoodsCount() + " ");
        ESCUtil.plainText(escCommand);
        escCommand.addText("件");


        ESCUtil.plainText(escCommand);
        ESCUtil.lineFeed(escCommand);
        escCommand.addText("\n代收款：");
        ESCUtil.boldText(escCommand);
        escCommand.addText(printInfo.getCollectionFee());
        ESCUtil.plainText(escCommand);
        ESCUtil.drawTextAtPos(escCommand, "垫付款：", 230);
        ESCUtil.boldText(escCommand);
        escCommand.addText(printInfo.getAdvanceTransitFee());


        ESCUtil.plainText(escCommand);
        ESCUtil.lineFeed(escCommand);
        escCommand.addText("\n备注：");

        escCommand.addText(printInfo.getRemark());

        ESCUtil.lineFeed(escCommand);

        ESCUtil.drawHLine(escCommand);

        ESCUtil.lineFeed(escCommand, 1);
    }


    public static void showSwitchModeTip(Context context) {
        final SweetAlertDialog dialog = new SweetAlertDialog(context);
        dialog.setTitleText("打印失败")
                .setContentText("\n请将打印机切换为票据模式！\n")
                .setConfirmText("好的")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismiss();
                    }
                });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((Dialog) dialog).findViewById(R.id.confirm_button).setBackgroundResource(R.drawable.round_rect__green);
            }
        });


        dialog.show();

    }
}
