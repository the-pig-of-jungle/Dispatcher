package com.liinji.ppgdeliver.print;

/**
 * Created by 朱志强 on 2017/8/15.
 */

public interface PrintSetting {
    int PAGE_WIDTH = 700 / 8;
    int SINGLE_PAGE_HEIGHT = 265;
    int LINE_SIZE = 3;
    int PAGE_H_START = 0;
    float PAGE_H_EDN = 573;
    int PAGE_V_START = 0;
    int COMPANY_NAME_ABOVE_SPACE = 24;
    float CONTENT_START_SPACE = 2.5f;
    float CONTENT_HALF_START_X = PAGE_H_EDN / 16 + 5;
    int BAR_ABOVE_SPACE = 12;
    int BAR_PER_SIZE = 3;
    int BAR_HEIGHT = 92;
    int CONTENT_LIEN_ABOVE_SPACE = 58;
    float LINE_SPACE = 1.25f;
    int CONTENT_SPACE = 48;
    int BOTTOM_PAGE_ADDED = 12;
    int TOP_BOTTOM_PAGE_SPACE = 628 + 150 + 40 + 200;
    int FONT2_SPACE = 40;
    int ORDER_NUMBER_ABOVE_SPACE = 88;

    int TDP_PAGE_ADDED = 90;


    float CONTENT_HALF_SHIFT = 5f;
    double HALF_X = PAGE_H_EDN / 16;
}