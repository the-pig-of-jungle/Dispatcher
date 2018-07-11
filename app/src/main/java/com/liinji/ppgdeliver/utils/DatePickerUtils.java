package com.liinji.ppgdeliver.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

/**
 * Created by 朱志强 on 2017/9/8.
 */

public class DatePickerUtils {
    public static void setDatePickerDividerColor(DatePicker datePicker) {
        // Divider changing:

        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();

            boolean hasProcessDevider = false;
            boolean hasProcessText = false;

            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.parseColor("#00cccccc")));//设置分割线颜色
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

                if (pf.getName().equals("mSelectorWheelPaint")){
                    pf.setAccessible(true);
                    try {
                        ((Paint) pf.get(picker)).setTextSize(Utils.dp2px(20));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                if (pf.getName().equals("mInputText")){
                    pf.setAccessible(true);
                    try {
                        ((EditText) pf.get(picker)).setTextSize(Utils.dp2px(6.7f));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }

}
