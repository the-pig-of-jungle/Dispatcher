package com.liinji.ppgdeliver.jiaboprint;

import com.gprinter.command.EscCommand;
import com.gprinter.command.EscCommand.ENABLE;


/**
 * Created by 朱志强 on 2017/10/9.
 */

public class ESCUtil {

    public static final String H_LINE = "━━━━━━━━━━━━━━━━━━━━━━━━";
    public static final String H_LINE_DASHED = "————————————————————————";
    public static final String V_LINE = "|";

    public static void lineFeed(EscCommand escCommand){
        escCommand.addPrintAndLineFeed();
    }

    public static void lineFeed(EscCommand escCommand, int spaceLines){
        escCommand.addPrintAndFeedLines((byte) spaceLines);
    }

    public static void centerContent(EscCommand escCommand){
        escCommand.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
    }

    public static void leftContent(EscCommand escCommand){
        escCommand.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);

    }


    public static void boldText(EscCommand escCommand){
        escCommand.addSelectPrintModes(EscCommand.FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.ON, ENABLE.OFF);
    }

    public static void plainText(EscCommand escCommand){
        escCommand.addSelectPrintModes(EscCommand.FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF);
    }

    public static void drawHLine(EscCommand escCommand) {
        escCommand.addText(H_LINE);
    }

    public static void drawDashHLine(EscCommand escCommand) {
                escCommand.addText(H_LINE_DASHED);
    }

    public static EscCommand init() {
         EscCommand command = new EscCommand();
        command.addInitializePrinter();
        return command;
    }

    public static void bigText(EscCommand escCommand) {
        escCommand.addSelectPrintModes(EscCommand.FONT.FONTA, ENABLE.OFF, ENABLE.ON, ENABLE.ON, ENABLE.OFF);
    }

    public static void draw1DBar(EscCommand escCommand, String orderNumber) {
        draw1DBar(escCommand,orderNumber,2,80);
    }

    public static void draw1DBar(EscCommand escCommand, String orderNumber, int singleBarWidth, int height){
        escCommand.addSelectPrintingPositionForHRICharacters(EscCommand.HRI_POSITION.NO_PRINT);
        // 设置条码可识别字符位置在条码下方
        escCommand.addSetBarcodeHeight((byte) height);
        escCommand.addSetBarcodeWidth((byte) singleBarWidth);
        escCommand.addCODE128(escCommand.genCodeB(orderNumber));
    }

    public static void posHorizontal(EscCommand escCommand, int pos) {
        escCommand.addSetAbsolutePrintPosition((short) pos);
    }
    public static void RelativePosHorizontal(EscCommand escCommand, int pos) {
        escCommand.addSetRelativePrintPositon((short) pos);
    }

    public static void setLineSpace(EscCommand escCommand, int i) {
        escCommand.addSetLineSpacing((byte) i);
    }

    public static void setDefaultLineSpace(EscCommand escCommand) {
        escCommand.addSelectDefualtLineSpacing();
    }

    public static void drawVLineAtPos(EscCommand escCommand, int pos) {
        ESCUtil.posHorizontal(escCommand,pos);
        escCommand.addText(V_LINE);
    }

    public static void drawTextAtPos(EscCommand escCommand, String text, int pos) {

        ESCUtil.posHorizontal(escCommand,pos);
        escCommand.addText(text);
    }

    public static void drawQRAtPos(EscCommand escCommand, String text, int pos){
        ESCUtil.posHorizontal(escCommand,pos);
        escCommand.addStoreQRCodeData(text);
        escCommand.addPrintQRCode();
    }




    public static void drawTextRelativePos(EscCommand escCommand, String text, int relativePos){
        ESCUtil.RelativePosHorizontal(escCommand,relativePos);
        escCommand.addText(text);
    }

    public static void cutPaper(EscCommand escCommand) {
        escCommand.addCutAndFeedPaper((byte) 0);
    }

    public static void drawQR(EscCommand escCommand, String orderNumber, int i) {
        escCommand.addSelectSizeOfModuleForQRCode((byte) i);
        escCommand.addStoreQRCodeData(orderNumber);
        escCommand.addPrintQRCode();
    }
}
