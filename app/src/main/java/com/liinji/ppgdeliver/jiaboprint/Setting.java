package com.liinji.ppgdeliver.jiaboprint;

/**
 * Created by 朱志强 on 2017/9/26.
 */

public interface Setting {
    int SINGLE_PAGE_HEIGHT = 119;
    int PAGE_WIDTH = 79;
    int PAGE_HEIGHT = SINGLE_PAGE_HEIGHT;



    int AVAILABLE_PAGE_WIDTH = 78;

    int AVAILABLE_PAGE_HEIGHT = PAGE_HEIGHT - 3;

    int SOURCE_POINT_X = 1;
    int SOURCE_POINT_Y = 0;

    int WHOLE_BOX_X_01 = 0;
    int WHOLE_BOX_Y_01 = 0;
    int WHOLE_BOX_X_02 = 78;


    int TITLE_TOP_MARGIN = 3;

    int FONT_2_HEIGHT = 6;
    int FONT_2_DIGIT_HEIGHT = 3;
    int FONT_1_HEIGHT = 3;
    int CONTENT_START_X = 2;
    int BARCODE_WIDTH = 56;
    int BARCODE_HEIGHT = 10;
    int BARCODE_NARROOW = 4;
    int LAST_VER_LIEN_X = 44;
}
