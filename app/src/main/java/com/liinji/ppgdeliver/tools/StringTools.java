package com.liinji.ppgdeliver.tools;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringTools {
    private static final char[] UNIT = {'亿', '拾', '佰', '仟', '万', '拾', '佰', '仟'};
    private static final char[] CHAINIESFIGURE2 = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};

    /**
     * 校验是否符合手机号格式
     *
     * @param num
     * @return
     */
    public static boolean checkIsPhoneNumber(String num) {
//		String pattern = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
        //改为只要是纯数字就通过
//        String pattern = "^[0-9]*$";
        String pattern = "^1[3|4|5|7|8]\\d{9}$";
        return checkPattern(num, pattern);
    }


    /**
     * 将String转Int
     *
     * @param num
     * @return
     */
    public static int stringToInteger(String num) {
        if (TextUtils.isEmpty(num)) {
            return 0;
        } else {
            return Integer.parseInt(num);
        }
    }

    public static String getStar(String info) {
        String replace = "";
        if (TextUtils.isEmpty(info)) {
            return "";
        } else {
            if (info.length() == 1) {
                replace = info;
            } else if (info.length() == 2) {
                replace = info.charAt(0) + "^_^";
            } else if (info.length() == 11 && checkIsNumber(info)) {
                replace = info.substring(0, 3) + "^_^" + info.substring(7, 11);
            } else {
                replace = info.charAt(0) + "^_^" + info.charAt(info.length() - 1);
            }
        }
        return replace;
    }

    /**
     * 获得处理后的运单号
     *
     * @param orderid
     * @return
     */
    public static String getRealOrderId(String orderid) {
        if (!TextUtils.isEmpty(orderid)) {
            if (orderid.indexOf("-") != -1) {
                return orderid.substring(0, orderid.indexOf("-"));
            } else {
                return orderid;
            }
        } else {
            return "";
        }
    }


    /**
     * 判断是否是两位数
     *
     * @param num
     * @return
     */
    public static String checkIsDouble(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return num + "";
        }

    }

    /**
     * 验证银行卡号的正确性
     *
     * @param num
     * @return
     */
    public static boolean checkIsBankCardNumber(String num) {
        String pattern1 = "^[0-9]{16}";
        String pattern2 = "[0-9]{19}";
        return (checkPattern(num, pattern1) || checkPattern(num, pattern2));
    }

    public static boolean checkIsCantain(String text) {
        String pattern = "\\w+";
        return checkPattern(text, pattern);
    }

    /**
     * 判断输入的是不是电话号码
     *
     * @param number
     * @return
     */

    public static boolean checkIsNumber(String number) {
        String pattern = "[0-9]*";
        return checkPattern(number, pattern);
    }

    public static boolean checkIsCode(String info) {
        String pattern = "^[a-zA-Z0-9]+$";
        //String pattern1 = "[a-zA-Z]";
        return checkPattern(info, pattern) /*&& checkPattern(info, pattern1)*/;
    }

    public static boolean checkIsLetter(String info) {
        String pattern = "[a-zA-Z]*";
        return checkPattern(info, pattern);
    }

    /**
     * 校验密码格式是否正确
     * <p/>
     * 格式为：
     * 6—20位
     * 只能是数字和字母
     *
     * @param pwd
     * @return
     */
    public static boolean checkIsPasswordFormatter(String pwd) {
        String pattern = "[\\da-zA-Z]{2,20}";
        return checkPattern(pwd, pattern);
    }

    /**
     * 校验是否为中国姓名格式
     *
     * @param name
     * @return
     */
    public static boolean checkIsChineseNameFormatter(String name) {
        String pattern = "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*";
        return checkPattern(name, pattern);
    }

    /**
     * 校验是否为身份证号码
     *
     * @param id
     * @return
     */
    public static boolean checkIsIDCardNum(String id) {
        String pattern = "^\\d{15}(\\d{2}[Xx0-9])?$";
        return checkPattern(id, pattern);
    }

    /**
     * 判断是否为订单号
     *
     * @param code
     * @return
     */
    public static boolean checkIsOrderCode(String code) {
        String pattern = "[\\da-zA-Z]{1,30}";
        return checkPattern(code, pattern);
    }

    /**
     * 判断是否符合正则表达式
     *
     * @param str
     * @param pattern
     * @return
     */
    private static boolean checkPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
        return matcher.matches();
    }

    /**
     * 检查密码强度
     * 1:弱
     * 2:一般
     * 3:强
     *
     * @param psw
     * @return
     */
    public static int checkPasswordStrength(String psw) {
        final int WEAK = 1;
        final int GENERAL = 2;
        final int STRONG = 3;

        long combinations = getCombinations(psw);
        if (combinations < Math.pow((10 + 26), 6)) {
            return WEAK;
        } else if (combinations < Math.pow((10 + 26 + 26), 8)) {
            return GENERAL;
        } else {
            return STRONG;
        }
    }

    /**
     * 返回密码复杂度(待修改)
     *
     * @param str
     * @return
     */
    private static long getCombinations(String str) {
        double b = 0;
        /**
         * �Ƿ�������
         */
        if (checkPattern(str, ".*\\d+.*")) {
            b += 10;
        }
        /**
         * �Ƿ���Сд��ĸ
         */
        if (checkPattern(str, ".*[a-z]+.*")) {
            b += 26;
        }
        if (checkPattern(str, ".*[A-Z]+.*")) {
            b += 26;
        }
        return (long) Math.pow(b, str.length());
    }

    /**
     * @param inStream
     * @return 字节数组
     * @throws Exception
     * @方法功能 InputStream 转为 byte
     */

    public static byte[] inputStream2Byte(InputStream inStream)
            throws Exception {
        // ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        // byte[] buffer = new byte[1024];
        // int len = -1;
        // while ((len = inStream.read(buffer)) != -1) {
        // outSteam.write(buffer, 0, len);
        // }
        // outSteam.close();
        // inStream.close();
        // return outSteam.toByteArray();
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    /**
     * 获取字符串数据
     *
     * @param et
     * @return 值或空字符串
     */
    public static String getEdittextString(EditText et) {
        if (et != null) {
            return et.getText().toString().trim() == null ? "" : et.getText().toString().trim();
        } else {
            return "";
        }
    }


//    public static boolean CheckUserIsLogin() {
//        return (0 == UserInfoManager.getUserInfoInstance().getUserId() && 0 == UserInfoManager.getUserInfoInstance().getIsCertification() && 0 == UserInfoManager.getUserInfoInstance().getIsSendAndLoad() && UserInfoManager.getUserInfoInstance().getUserPhoneNumber() == null);
//    }

    /**
     * 获取textview值
     *
     * @param tv
     * @return
     */
    public static String getTextviewString(TextView tv) {
        if (tv != null) {
            return tv.getText().toString() == null ? "" : tv.getText().toString();
        } else {
            return "";
        }
    }

    /**
     * 获取带*字符串
     *
     * @param count
     */
    public static String getStarString(int count) {
        String star = "";
        for (int i = 0; i < count; i++) {
            star = star + "*";
        }
        return star;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     * <p/>
     * <p>A negative start position can be used to start/end <code>n</code>
     * characters from the end of the String.</p>
     * <p/>
     * <p>The returned substring starts with the character in the <code>start</code>
     * position and ends before the <code>end</code> position. All position counting is
     * zero-based -- i.e., to start at the beginning of the string use
     * <code>start = 0</code>. Negative start and end positions can be used to
     * specify offsets relative to the end of the String.</p>
     * <p/>
     * <p>If <code>start</code> is not strictly to the left of <code>end</code>, ""
     * is returned.</p>
     * <p/>
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str   the String to get the substring from, may be null
     * @param start the position to start from, negative means
     *              count back from the end of the String by this many characters
     * @param end   the position to end at (exclusive), negative means
     *              count back from the end of the String by this many characters
     * @return substring from start position to end positon,
     * <code>null</code> if null String input
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 金额转换
     *
     * @param intString
     * @return
     * @throws NumberFormatException
     */
    public static String toChineseCharI(String intString) throws NumberFormatException {

        //用来存放转换后的大写数字,因为是StringBuffer类型,所以顺便把没有转换
        //的数字倒序排列一下,省一个变量.
        StringBuffer ChineseCharI = new StringBuffer(intString);
        //倒序的数字,便于同单位合并
        String rintString = ChineseCharI.reverse().toString();
        //清空一下
        ChineseCharI.delete(0, ChineseCharI.length());
        //单位索引
        int unitIndex = 0;
        //数字长度
        int intStringLen = intString.length();
        //一位由字符转换的数字
        int intStringnumber = 0;
        //补0
        boolean addZero = false;
        boolean flagZero = false;
        for (int i = 0; i < intStringLen; i++) {
            //按单位长度循环索引
            unitIndex = i % UNIT.length;

            //异常检查
            if (!Character.isDigit(rintString.charAt(i))) {
                throw new NumberFormatException("数字中含有非法字符");
            }
            intStringnumber = Character.digit(rintString.charAt(i), 10);

            //如果当前位是0,开启补0继续循环直到不是0的位
            if (intStringnumber == 0) {
                addZero = true;
                if (i != 0 && i % 4 == 0) {
                    if (addZero && ChineseCharI.length() != 0) {
                        ChineseCharI.append(CHAINIESFIGURE2[0]);
                        addZero = false;
                    }
                    flagZero = true;
                    ChineseCharI.append(UNIT[unitIndex]);
                }
            } else {
                //若当前位不是第一位且补0开启
                if (addZero && ChineseCharI.length() != 0 && !flagZero) {
                    ChineseCharI.append(CHAINIESFIGURE2[0]);
                }
                flagZero = false;
                //插入单位
                //个位数后面不需 要单位
                if (i > 0) {
                    System.out.println(i);
                    ChineseCharI.append(UNIT[unitIndex]);
                }

                //插入大写数字
                ChineseCharI.append(CHAINIESFIGURE2[intStringnumber]);
                //补0关闭
                addZero = false;
            }
        }

        //异常处理
        if (ChineseCharI.length() == 0) {
            return "零";
        }
        return ChineseCharI.reverse().toString() + "元整";

    }

    /**
     * 打印截取
     *
     * @param s
     * @return
     */
    public static String printSubString(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        if (s.length() <= 16) {
            return s;
        }
        if (s.length() > 16 && s.length() <= 32) {
            return s.substring(0, 16) + "\n" + s.substring(16);
        }
        return s.substring(0, 16) + "\n" + s.substring(16, 32) + "\n" + s.substring(32);
    }

    /**
     * 打印截取
     *
     * @param s
     * @return
     */
    public static String printSubStringMix(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        String s1 = s.substring(0, s.indexOf("-"));
        if (s1.length() <= 10) {
            return s;
        }
        return s1.substring(0, 9) + "…" + s.substring(s.indexOf("-"));
    }

    public static boolean checkPwd(String pwd){
        String pattern ="^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{6,15}$";
        return checkPattern(pwd,pattern);
    }

}
