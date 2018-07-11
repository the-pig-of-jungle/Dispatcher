package com.liinji.ppgdeliver.jiaboprint;

import com.gprinter.command.LabelCommand;
import com.liinji.ppgdeliver.print.PrintInfo;

/**
 * Created by 朱志强 on 2017/9/26.
 */

public class TSCUtil {

    public static void addBox(LabelCommand tsc, int x1, int y1, int x2, int y2, int widht) {
        tsc.addBox(getRealCoordinator(x1), getRealCoordinator(y1), getRealCoordinator(x2), getRealCoordinator(y2), widht);
    }

    public static void addText(LabelCommand tsc, int x, int y, int fontSize, String content) {
        tsc.addText(getRealCoordinator(x), getRealCoordinator(y), LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE, LabelCommand.ROTATION.ROTATION_0, getFont(fontSize), getFont(fontSize), content);
    }

    public static void addText(LabelCommand tsc, int x, int y, int xFontSize, int yFontSize, String content) {
        tsc.addText(getRealCoordinator(x), getRealCoordinator(y), LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE, LabelCommand.ROTATION.ROTATION_0, getFont(xFontSize), getFont(yFontSize), content);
    }

    public static void add1DBar(LabelCommand tsc, int x, int y, int width, int height, int narrow, String content) {
        tsc.add1DBarcode(getRealCoordinator(x), getRealCoordinator(y), LabelCommand.BARCODETYPE.CODE128, getRealCoordinator(height), LabelCommand.READABEL.DISABLE, LabelCommand.ROTATION.ROTATION_0, narrow, getRealCoordinator(width), content);
    }


    private static LabelCommand.FONTMUL getFont(int fontSize) {
        switch (fontSize) {
            case 1:
                return LabelCommand.FONTMUL.MUL_1;
            case 2:
                return LabelCommand.FONTMUL.MUL_2;
            case 3:
                return LabelCommand.FONTMUL.MUL_3;
        }

        return LabelCommand.FONTMUL.MUL_1;
    }


    public static int getRealCoordinator(int source) {
        return source * 8;
    }

    public static int getTextWidth(int fontSize, String content) {
        if (content == null){
            return 0;
        }
        switch (fontSize) {
            case 1:
                return content.length() * 3;
            case 2:
                return content.length() * 6;
        }

        return content.length() * 3;
    }


    public static int getTextCenterX(int fontSize, String content) {
        return (Setting.AVAILABLE_PAGE_WIDTH - getTextWidth(2, content)) / 2;
    }

    public static int getDigitWidth(int fontSize, String digits) {
        switch (fontSize) {
            case 1:
                return 2 * digits.length();
            case 2:
                return 3 * digits.length();
        }

        return 3;
    }

    public static int getDigitsCenterX(int fontSize, String digits) {
        return (Setting.AVAILABLE_PAGE_WIDTH - getDigitWidth(2, digits)) / 2;
    }

    public static int getPageHeight(PrintInfo printInfo) {
        switch (printInfo.getTime()) {
            case 1:
                if (printInfo.getReceiverAddress().length() <= 23) {
                    return Setting.PAGE_HEIGHT - 1 + 7;
                } else {
                    return Setting.PAGE_HEIGHT + 5 + 9;
                }

            case 2:
                if (printInfo.getReceiverAddress().length() <= 23) {
                    return Setting.PAGE_HEIGHT - 1 + 5;
                } else {
                    return Setting.PAGE_HEIGHT+ 5 + 5 ;
                }


        }


        return 0;
    }
}
