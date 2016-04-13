package com.hgsoft.yfzx.common.string;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

/**
 * 功能描述：数字的格式化，如小数点后几位等的字符串；
 */
public class NumberUtil {

    /**
     * 功能描述：小数点向后移动，
     *
     * @param str   el:  1.1111 11111
     * @param count el:  8   so may original: 1.1111E2  111.11
     * @return
     */
    public static String moveAfter(String str, int count) {
        int start = str.indexOf('.');
        int end = str.length() - str.indexOf('.') - 1; //小数点后的位数

        if (count == end) {//如果相等，则直接返回去掉小数点的字符串
            return str.replace(".", "");
        } else if (count < end) {
            str = str.replace(".", "");
            str = str.substring(0, start + count).concat(".").concat(str.substring(start + count));
        } else {
            str = str.replace(".", "");
            str = str.concat(addZero(count - end));
        }
        return str;
    }

    /**
     * 功能描述：小数点向前移动，
     *
     * @param str   el:  11.1111
     * @param count el:  4   so may original: 1.1111E2  111.11
     * @return
     */
    public static String GoHead(String str, int count) {
        int end = str.indexOf('.'); //小数点前的位数
        String prefix = "0.";
        if (count == end) {//如果相等，则直接在字符串前加上前缀返回
            return prefix.concat(str.replace(".", ""));
        } else if (count < end) {
            str = str.replace(".", "");
            str = str.substring(0, end - count).concat(".").concat(str.substring(end - count));
        } else {
            str = str.replace(".", "");
            str = prefix.concat(addZero(count - end)).concat(str);
        }
        return str;
    }

    //辅助方法
    public static String addZero(int count) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * 功能描述：将数字的整数位每三位用逗号隔开
     *
     * @param number
     * @return
     */
    public static String numberFormat(String number) {
        if (number == null || number.trim().equals("")) {

            return "0.00";
        } else {
            try {
                if (number.equals("0.0") || number.equals("0") || number.equals("0.00")) {
                    return "0.00";
                } else {
                    Double myNumber = Double.parseDouble(number);
                    String myString = NumberFormat.getInstance().format(
                            myNumber);
                    if (myString.indexOf(".") != -1) {
                        int strLength = myString.length() - 1;
                        int temp = myString.indexOf(".");
                        if ((strLength - temp) == 1) {
                            myString += "0";
                        }
                    } else {
                        myString += ".00";
                    }

                    return myString;

                }
            } catch (Exception e) {
                return "0.00";
            }
        }
    }

    /**
     * 功能描述：将数字的整数位每三位用逗号隔开
     *
     * @param number
     * @return
     */
    public static String numberFormat(Double number) {
        if (number == null || number.equals("")) {
            return "0.00";
        } else {
            try {
                if (number == 0) {
                    return "0.00";
                } else {
                    String myString = NumberFormat.getInstance().format(
                            number);
                    if (myString.indexOf(".") != -1) {
                        int strLength = myString.length() - 1;
                        int temp = myString.indexOf(".");
                        if ((strLength - temp) == 1) {
                            myString += "0";
                        }
                    } else {
                        myString += ".00";
                    }

                    return myString;

                }
            } catch (Exception e) {
                return "0.00";
            }
        }
    }

    /**
     * 功能描述：String型变量转换成int型变量
     *
     * @param str 要进行转换的字符串
     * @return
     * @since 1.0
     */
    public static int str2Int(String str) throws NumberFormatException {
        int intVal;
        intVal = Integer.parseInt(str);
        return intVal;
    }

    /**
     * 功能描述：String型变量转换成double型变量
     *
     * @param str 要进行转换的字符串
     * @return
     * @since 1.0
     */
    public static double str2Double(String str) throws NumberFormatException {
        double dVal = 0;
        dVal = Double.parseDouble(str);
        return dVal;
    }

    /**
     * 功能描述：String型变量转换成long型变量
     *
     * @param str 要进行转换的字符串
     * @return
     * @since 1.0
     */
    public static long str2Long(String str) throws NumberFormatException {
        long longVal = 0;
        longVal = Long.parseLong(str);
        return longVal;
    }

    /**
     * 功能描述：String型变量转换成float型变量
     *
     * @param floatstr 要进行转换的字符串
     * @return
     * @since 1.0
     */
    public static float stringToFloat(String floatstr) throws NumberFormatException {
        Float floatee;
        floatee = Float.valueOf(floatstr);
        return floatee.floatValue();
    }

    /**
     * 功能描述：float转为字符串
     *
     * @param value
     * @return
     */
    public static String floatToString(float value) {
        Float floatee = new Float(value);
        return floatee.toString();
    }

    /**
     * 功能描述：int型变量转换成String型变量
     *
     * @param intVal 要进行转换的整数
     * @return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */
    public static String int2Str(int intVal) {
        String str;

        try {
            str = String.valueOf(intVal);
        } catch (Exception e) {
            str = "";
        }

        return str;
    }

    /**
     * 功能描述：long型变量转换成String型变量
     *
     * @param longVal 要进行转换的整数
     * @return str 如果longVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */
    public static String long2Str(long longVal) {
        String str;

        try {
            str = String.valueOf(longVal);
        } catch (Exception e) {
            str = "";
        }

        return str;
    }

    /**
     * 功能描述：null 处理，如果是null的字符串将会返回""
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
     * 功能描述：返回date的字符串表示，如果date为null 返回""
     *
     * @param d 要进行转换的日期对像
     * @return 如果d为null值，返回空串"",否则返回d.toString()
     */
    public static String null2Blank(Date d) {
        if (d == null)
            return "";
        else
            return d.toString();
    }

    /**
     * 功能描述：null 处理，如果str为null值，返回空串整数0,否则返回相应的整数
     *
     * @param str 要进行转换的字符串
     * @return 如果str为null值，返回空串整数0,否则返回相应的整数
     */
    public static int null2Zero(String str) {
        if (str == null)
            return 0;
        else
            return str2Int(str);
    }

    /**
     * 功能描述：把null转换为字符串"0"
     *
     * @param str
     * @return
     */
    public static String null2SZero(String str) {
        if (str == null)
            return "0";
        else
            return str;
    }

    /**
     * 功能描述：test
     *
     * @param agrs
     */
    public static void main(String[] agrs) {
        // 将科学计数法转换成正常数字
        double d = 121113456789123456.789D;
        System.out.println(moveAfter("1.21113456789123456", 3));
        System.out.println(GoHead("1.21113456789123456", 3));
        System.out.println(String.format("%.4f", d));
        // 或者
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
        double doubleValue = 3.25;
        String strValue = df.format(doubleValue);

        BigDecimal bd = new BigDecimal("1.238761976E-10");
        System.out.println(bd.toPlainString());
    }

}
