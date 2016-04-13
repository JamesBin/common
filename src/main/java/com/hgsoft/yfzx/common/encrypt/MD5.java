package com.hgsoft.yfzx.common.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 功能描述：MD5加密算法；
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: MD5.java
 * @Date: 13-10-17
 * @Time: 下午3:12
 */
public class MD5 {

    /**
     * 功能描述：使用MD5算法对字符串进行加密
     *
     * @param plainText（明文）
     * @return 32位密文
     */
    public static String encryption(String plainText) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    /**
     * 功能描述：给字符串进行MD5加密返回大写的字符串
     *
     * @param plainText 需要加密的字符串
     * @return 加密后大写字符串
     */
    public static String encryptionUppercase(String plainText) {
        return encryption(plainText).toUpperCase();
    }
}
