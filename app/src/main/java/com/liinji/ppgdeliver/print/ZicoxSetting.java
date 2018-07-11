package com.liinji.ppgdeliver.print;

/**
 * Created by pig on 2017/12/29.
 */

public interface ZicoxSetting {
    int PAGE_WIDTH = 800;
    int PAGE_HEIGHT = 2080;
    int PAGE_H_END = 570;
    int LINE_WIDTH = 3;
    int LINE_SPACE = 16;
    int CONTENT_START_X = 16;
    int IMG_SPACE = 80;
    int CONTENT_START_Y = 0;
    int BARCODE_TYPE = 128;
    int BARCODE_PER_WIDTH = 3;
    int BARCODE_HEIGHT = 80;
    int QR_WIDTH = 3;
    int CONTENT_HALF_START_X = PAGE_H_END / 2 + 32;
}
