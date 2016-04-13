package com.hgsoft.yfzx.common.string;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 功能描述：字符串空的判断等。
 *
 * @Author: 詹武槟
 * @Version: 1.0 add
 * @File: StringUtil.java
 * @Date: 2015/11/19
 * @Time: 16:29
 */
public class StringUtil {
    /**
     * 功能描述：判断字符串是否为空，如果为空，返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) ? true : false;
    }

    /**
     * 功能描述：判断字符串是否为空，如果不为空，返回true
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 功能描述：null 处理
     *
     * @param str 要进行转换的字符串
     * @return 如果str为null值，返回空串"",否则返回str
     */
    public static String null2Blank(String str) {
        if (str == null)
            return "";
        else
            return str;
    }

    /**
     * 功能描述：判断一个字符串是否存在一个字符串数组中
     *
     * @param str
     * @param strArr
     * @return
     */
    public static boolean existStrArr(String str, String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 功能描述：判断字符串是否为空，并删除首尾空格
     */
    public static String convertNullCode(String tempSql) {
        if (tempSql == null) tempSql = "";
        return tempSql.trim();
    }

    /**
     * 功能描述：字符串替换操作
     *
     * @param originString 原字符串
     * @param oldString    被替换字符串
     * @param newString    替换字符串
     * @return 替换操作后的字符串
     */
    public static String replace(String originString, String oldString, String newString) {
        String getstr = originString;
        while (getstr.indexOf(oldString) > -1) {
            getstr = getstr.substring(0, getstr.indexOf(oldString)) + newString + getstr.substring(getstr.indexOf(oldString) + oldString.length(), getstr.length());
        }
        return getstr;
    }

    /**
     * 功能描述：代码转换，GBK转换为ISO-8859-1
     *
     * @param tempSql 要转换的字符串
     * @return
     */
    public static String ISOCode(String tempSql) {

        String returnString = convertNullCode(tempSql);

        try {
            byte[] ascii = returnString.getBytes("GBK");
            returnString = new String(ascii, "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 功能描述：代码转换，ISO-8859-1转换为GBK
     *
     * @param tempSql 要转换的字符串
     * @return
     */
    public static String GBKCode(String tempSql) {
        String returnString = convertNullCode(tempSql);
        try {
            byte[] ascii = returnString.getBytes("ISO-8859-1");
            returnString = new String(ascii, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 功能描述：编码转换 从srcCode转换为destCode
     *
     * @param srcCode  原编码
     * @param destCode 目标编码
     * @param strTmp   要转换的字符串
     * @return
     */
    public static String convertCode(String srcCode, String destCode, String strTmp) {
        String returnString = convertNullCode(strTmp);
        try {
            byte[] ascii = returnString.getBytes(srcCode);
            returnString = new String(ascii, destCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 功能描述：编码转换，GBK转换为big5
     *
     * @param tempSql 要转换的字符串
     * @return
     */
    public static String GBK2BIG5Code(String tempSql) {
        String returnString = convertNullCode(tempSql);
        try {
            byte[] ascii = returnString.getBytes("GBK");
            returnString = new String(ascii, "big5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * 功能描述：替换非法字符
     */

    public static String convertHtml(String input) {
        StringBuffer returnString = new StringBuffer(input.length());

        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {

            ch = input.charAt(i);

            if (ch == '<') {
                returnString = returnString.append("&lt");
            } else if (ch == '>') {
                returnString = returnString.append("&gt");
            } else if (ch == ' ') {
                returnString = returnString.append("&nbsp");
            } else if (ch == '\\') {
                returnString = returnString.append("&acute");
            } else {
                returnString = returnString.append(ch);
            }
        }
        return returnString.toString();
    }

    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
    private static final char[] AMP_ENCODE = "&amp;".toCharArray();
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();

    /**
     * 功能描述：讲"<"很">"分别转化为“&lt"和”&gt"
     */
    public static final String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];

            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            } else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * 功能描述：数字的金额表达式
     *
     * @param num
     * @return
     */
    public static String convertNumToMoney(int num) {
        NumberFormat formatc = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String strcurr = formatc.format(num);
        System.out.println(strcurr);
        //num = NumberFormat.getInstance().setParseIntegerOnly(true));
        return strcurr;
    }
}
