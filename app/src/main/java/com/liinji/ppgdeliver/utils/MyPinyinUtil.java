package com.liinji.ppgdeliver.utils;

import com.liinji.ppgdeliver.bean.NetBuddy;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Comparator;

/**
 * Created by 朱志强 on 2017/7/25.
 */

public class MyPinyinUtil implements Comparator<NetBuddy> {
    private static MyPinyinUtil sPinYinUtil;

    private MyPinyinUtil() {

    }

    public static MyPinyinUtil getInstance() {
        if (sPinYinUtil == null) {
            sPinYinUtil = new MyPinyinUtil();
        }
        return sPinYinUtil;
    }

    @Override
    public int compare(NetBuddy info1, NetBuddy info2) {
        return info1.getInitial().compareTo(info2.getInitial());
    }

    public String getPinYin(String str) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = str.trim().toCharArray();
        String output = "";

        try {
            for (char curchar : input) {
                if (Character.toString(curchar).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curchar, format);
                    output += temp[0];
                } else
                    output += Character.toString(curchar);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output.toUpperCase();
    }


}
