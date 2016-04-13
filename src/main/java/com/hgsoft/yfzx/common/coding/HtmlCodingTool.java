package com.hgsoft.yfzx.common.coding;

import com.hgsoft.yfzx.common.exception.Exceptions;
import com.hgsoft.yfzx.common.string.StringUtil;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 功能描述：此类中收集Java编程中WEB开发常用到的一些工具。
 * 为避免生成此类的实例，构造方法被申明为private类型的。
 * 此类主要用于一些html中特殊字符的替换，url的编码转换等
 *
 * @author
 */
public class HtmlCodingTool {
    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private HtmlCodingTool() {
    }

    /**
     * 功能描述：用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     *
     * @param strSrc 要进行替换操作的字符串
     * @return 替换特殊字符后的字符串
     * @since 1.0
     */
    public static String htmlEncode(String strSrc) {
        if (strSrc == null)
            return "";

        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;

        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];

            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"')
                buf.append("&quot;");
            else if (ch == '\'')
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * 功能描述：用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     *
     * @param strSrc 要进行替换操作的字符串
     * @param quotes 为0时单引号和双引号都替换，为1时不替换单引号，为2时不替换双引号，为3时单引号和双引号都不替换
     * @return 替换特殊字符后的字符串
     * @since 1.0
     */
    public static String htmlEncode(String strSrc, int quotes) {

        if (strSrc == null)
            return "";
        if (quotes == 0) {
            return htmlEncode(strSrc);
        }

        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;

        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"' && quotes == 1)
                buf.append("&quot;");
            else if (ch == '\'' && quotes == 2)
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * 功能描述：和htmlEncode正好相反
     *
     * @param strSrc 要进行转换的字符串
     * @return 转换后的字符串
     * @since 1.0
     */
    public static String htmlDecode(String strSrc) {
        if (strSrc == null)
            return "";
        strSrc = strSrc.replaceAll("&lt;", "<");
        strSrc = strSrc.replaceAll("&gt;", ">");
        strSrc = strSrc.replaceAll("&quot;", "\"");
        strSrc = strSrc.replaceAll("&#039;", "'");
        strSrc = strSrc.replaceAll("&amp;", "&");
        return strSrc;
    }

    /**
     * 功能描述：Utf8URL编码
     *
     * @return
     */
    public static final String utf8URLencode(String text) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);
            if (c >= 0 && c <= 255) {
                result.append(c);
            } else {

                byte[] b = new byte[0];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (Exception ex) {
                }

                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    result.append("%" + Integer.toHexString(k).toUpperCase());
                }

            }
        }

        return result.toString();
    }

    /**
     * 功能描述：Utf8URL解码
     *
     * @param text
     * @return
     */
    public static final String utf8URLdecode(String text) {
        String result = "";
        int p = 0;

        if (text != null && text.length() > 0) {
            text = text.toLowerCase();
            p = text.indexOf("%e");
            if (p == -1) return text;

            while (p != -1) {
                result += text.substring(0, p);
                text = text.substring(p, text.length());
                if (text == "" || text.length() < 9) return result;

                result += codeToWord(text.substring(0, 9));
                text = text.substring(9, text.length());
                p = text.indexOf("%e");
            }

        }

        return result + text;
    }

    /**
     * 使用URLEncoder进行URL 编码, Encode默认为UTF-8.
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 使用URLEncoder进行URL 解码, Encode默认为UTF-8.
     */
    public static String urlDecode(String part) {
        try {
            return URLDecoder.decode(part, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 功能描述：utf8URL编码转字符
     *
     * @param text
     * @return
     */
    private static final String codeToWord(String text) {
        String result;

        if (utf8CodeCheck(text)) {
            byte[] code = new byte[3];
            code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
            code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
            code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
            try {
                result = new String(code, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result = null;
            }
        } else {
            result = text;
        }

        return result;
    }

    /**
     * 功能描述：编码是否有效
     *
     * @param text
     * @return
     */
    private static final boolean utf8CodeCheck(String text) {
        String sign = "";
        if (text.startsWith("%e"))
            for (int i = 0, p = 0; p != -1; i++) {
                p = text.indexOf("%", p);
                if (p != -1)
                    p++;
                sign += p;
            }
        return sign.equals("147-1");
    }

    /**
     * 功能描述：判断是否Utf8Url编码
     *
     * @param text
     * @return
     */
    public static final boolean isUtf8Url(String text) {
        text = text.toLowerCase();
        int p = text.indexOf("%");
        if (p != -1 && text.length() - p > 9) {
            text = text.substring(p, p + 9);
        }
        return utf8CodeCheck(text);
    }

    /**
     * 功能描述：显示大文本块处理(将字符集转成ISO)
     *
     * @param str 要进行转换的字符串
     * @return 转换成html可以正常显示的字符串
     * @deprecated
     */
    public static String toISOHtml(String str) {
        return StrCodingConversion.gBKToISO(htmlDecode(StringUtil.null2Blank((str))));
    }

    /**
     * 功能描述：把null值和""值转换成&nbsp;
     * 主要应用于页面表格格的显示
     *
     * @param str 要进行处理的字符串
     * @return 转换后的字符串
     */
    public static String nullToNbsp(String str) {
        if (str == null)
            return "&nbsp;";
        else if (str.equals(""))
            return "&nbsp;";
        else
            return str;
    }



    /**
     * 功能描述：字符串从GBK编码转换为NCR，NCR类似这种形式：&#x4e2d;&#x56fd;
     *
     * @param text
     * @return
     * @param text   GBK编码的字符串
     * @return       NCR编码的字符串
     */
    public static String gbkStrToNCR(String text) {
        String result = "";
        int input;
        StringReader isr;
        try {
            isr = new StringReader(new String(text.getBytes(), "GBK"));
        } catch (UnsupportedEncodingException e) {
            return "-1";
        }
        try {
            while ((input = isr.read()) != -1) {
                result = result + "&#x" + Integer.toHexString(input) + ";";

            }
        } catch (IOException e) {
            return "-2";
        }
        isr.close();
        return result;
    }

    /**
     * 功能描述：NCR：形如：&#x901f;&#x5ea6;  此方法就是转换字符串为这种形式 *
     *
     * @param inStr 要转换的字符串
     * @return
     * @since 1.0
     */
    public static String strToNCR(String inStr) {
        char temChr;
        int ascInt;
        int i;
        String result = new String("");
        if (inStr == null) {
            inStr = "";
        }
        for (i = 0; i < inStr.length(); i++) {
            temChr = inStr.charAt(i);
            ascInt = temChr + 0;
            //System.out.println("1=="+ascInt);
            //System.out.println("1=="+Integer.toBinaryString(ascInt));
            if (Integer.toHexString(ascInt).length() > 2) {
                result = result + "&#x" + Integer.toHexString(ascInt) + ";";
            } else {
                result = result + temChr;
            }

        }
        return result;
    }

    /**
     * <pre>
     * 例：
     * String strVal="This is a dog";
     * String strResult=HtmlCodingTool.replace(strVal,"dog","cat");
     * 结果：
     * strResult equals "This is cat"
     *
     * @param strSrc 要进行替换操作的字符串
     * @param strOld 要查找的字符串
     * @param strNew 要替换的字符串
     * @return 替换后的字符串
     * <pre>
     */
    private static final String replace(String strSrc, String strOld,
                                        String strNew) {
        if (strSrc == null || strOld == null || strNew == null)
            return "";

        int i = 0;

        if (strOld.equals(strNew)) //避免新旧字符一样产生死循环
            return strSrc;

        if ((i = strSrc.indexOf(strOld, i)) >= 0) {
            char[] arr_cSrc = strSrc.toCharArray();
            char[] arr_cNew = strNew.toCharArray();

            int intOldLen = strOld.length();
            StringBuffer buf = new StringBuffer(arr_cSrc.length);
            buf.append(arr_cSrc, 0, i).append(arr_cNew);

            i += intOldLen;
            int j = i;

            while ((i = strSrc.indexOf(strOld, i)) > 0) {
                buf.append(arr_cSrc, j, i - j).append(arr_cNew);
                i += intOldLen;
                j = i;
            }

            buf.append(arr_cSrc, j, arr_cSrc.length - j);

            return buf.toString();
        }

        return strSrc;
    }

    public static  void main(String args[]){
        String url;

        System.out.print(codeToWord("dslfj受到了房间"));
        System.out.print(gbkStrToNCR("速度时代发生地方福建师范"));

        url = "http://www.google.com/search?hl=zh-CN&newwindow=1&q=%E4%B8%AD%E5%9B%BD%E5%A4%A7%E7%99%BE%E7%A7%91%E5%9C%A8%E7%BA%BF%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2&btnG=%E6%90%9C%E7%B4%A2&lr=";
        if (isUtf8Url(url)) {
            System.out.println(utf8URLdecode(url));
        } else {
            //System.out.println(URLDecoder.decode(url));
        }

        url = "http://www.baidu.com/baidu?word=%D6%D0%B9%FA%B4%F3%B0%D9%BF%C6%D4%DA%CF%DF%C8%AB%CE%C4%BC%EC%CB%F7&tn=myie2dg";
        if (isUtf8Url(url)) {
            System.out.println(utf8URLdecode(url));
        } else {
            //System.out.println(URLDecoder.decode(url));
        }
    }

}
