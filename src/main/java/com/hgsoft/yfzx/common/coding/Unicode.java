package com.hgsoft.yfzx.common.coding;

/**
 * 功能描述：字符串与Unicode之间的相互转换；
 * @Author: 潘乃照
 * @Package: com.hgsoft.yfzx.common.coding
 * @DATE: 2016/4/7
 * @TIME: 10:02
 */
public class Unicode {
    /**
     * 功能描述：此方法字符串编码为Unicode
     *
     * @param gbString
     * @return
     * @param gbString   String字符串
     * @return           unicode字符串
     */
    public static String encodeStrToUnicode(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    /**
     * 功能描述：将特定字符串进行Unicode解码
     *
     * @param dataStr
     * @return
     * @param dataStr      特定字符串
     * @return             解码后的字符串
     */
    public static StringBuffer decodeUnicodeToStr(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer;
    }

    public static void main(String args[]){
        System.out.println(decodeUnicodeToStr(encodeStrToUnicode("sdlfjsklf索科洛夫就算了sdkfjsfksdlfj")));
    }
}
