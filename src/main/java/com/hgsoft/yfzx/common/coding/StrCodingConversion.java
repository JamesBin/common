package com.hgsoft.yfzx.common.coding;

import java.io.UnsupportedEncodingException;

/**
 * 功能描述：字符编码工具类，UTF-8、GBK、ISO-8859-1、GB2312等编码之间的互相转换
 *
 * @version 1.0
 * @author:
 */
public class StrCodingConversion {

    /**
     * 功能描述：转换编码 ISO-8859-1到GB2312
     *
     * @param text 需要转换编码的字符串
     * @return 转换编码后的字符串
     */
    public static final String iSOToGB(String text) {
        String result = "";
        try {
            result = new String(text.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException ex) {
            result = ex.toString();
        }
        return result;
    }

    /**
     * 功能描述：转换编码 GB2312到ISO-8859-1
     *
     * @param text
     * @return
     */
    public static final String gBToISO(String text) {
        String result = "";
        try {
            result = new String(text.getBytes("GB2312"), "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 功能描述：字符串编码转换,从“ISO8859_1”到“GBK”.
     *
     * @param strVal 要转换的字符串
     * @return 从“ISO8859_1”到“GBK”得到的字符串
     * @since 1.0
     */
    public static String iSOToGBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("ISO8859_1"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 功能描述：编码转换 从UTF-8到GBK
     * @param strVal      UTF-8编码的字符串
     * @return            GBK编码的字符串
     */
    public static String uTF8ToGBK(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = strVal.trim();
                strVal = new String(strVal.getBytes("UTF-8"), "GBK");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 功能描述：字符串编码转换,从“GBK”到“ISO8859_1”
     *
     * @param strVal 要转换的字符串
     * @return 从“GBK”到“ISO8859_1”得到的字符串
     * @since 1.0
     */
    public static String gBKToISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "ISO8859_1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 功能描述：字符串编码转换,从“GBK”到“UTF-8”
     *
     * @param strVal 要转换的字符串
     * @return 从“GBK”到“UTF-8”得到的字符串
     * @since 1.0
     */
    public static String gBKToUTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("GBK"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 功能描述：字符串编码转换,从“ISO”到“UTF-8”
     *
     * @param strVal 要转换的字符串
     * @return 从“ISO”到“UTF-8”得到的字符串
     * @since 1.0
     */
    public static String iSOToUTF8(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("ISO-8859-1"), "UTF-8");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 功能描述：字符串编码转换,从“UTF-8”到“ISO”
     *
     * @param strVal 要转换的字符串
     * @return 从“UTF-8”到“ISO”得到的字符串
     * @since 1.0
     */
    public static String uTF8ToISO(String strVal) {
        try {
            if (strVal == null) {
                return "";
            } else {
                strVal = new String(strVal.getBytes("UTF-8"), "ISO-8859-1");
                return strVal;
            }
        } catch (Exception exp) {
            return "";
        }
    }



    /**
     * 功能描述：测试
     *
     * @param args
     */
    public static void main(String[] args) {

        //CharTools charTools = new CharTools();



    }

}
