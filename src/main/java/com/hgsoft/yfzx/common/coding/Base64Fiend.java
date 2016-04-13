package com.hgsoft.yfzx.common.coding;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 功能描述：对base64编码的封装,为了提高维护性,避免处处类用标注的api.对字符串进行Base64编码和解码；
 *
 * @author ices.x
 */
public class Base64Fiend {

    /**
     * 功能描述：将byte类型的数据经过base64编码转为string.
     *
     * @param fileData byte类型的数据 .
     * @return 转码后的数据, 发生异常或者filedate为null时返回null.
     */
    public static String encode(byte[] fileData) {
        if (fileData == null) {
            return null;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(fileData).replaceAll("\\s*", "");
    }

    /**
     * 功能描述：将string类型的数据转码为byte类型.
     *
     * @param fileData String 类型的数据.
     * @return 转码后的数据byte类型, 发生异常或者filedate为null时返回null.
     */
    public static byte[] decode(String fileData) throws IOException {
        if (fileData == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(fileData);
    }

    /**
     * 功能描述：将InputStream类型的数据转码为String.
     *
     * @param fileData InputStream类型的数据.
     * @return 转码后的数据String类型, 发生异常或者filedate为null时返回null.
     */
    public static String encode(InputStream fileData) throws IOException {
        if (fileData == null) {
            return null;
        }
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] _fileData;
        _fileData = new byte[fileData.available()];
        fileData.read(_fileData);
        fileData.close();
        return encoder.encode(_fileData).replaceAll("\\s*", "");

    }

    /**
     * 功能描述：对String进行base64编码.
     *
     * @param string 需要编码的字符串
     * @return 编码后的字符串
     */
    public static String encodeToString(String string) {
        if (string == null) {
            return null;
        } else {
            return encode(string.getBytes());
        }
    }

    /**
     * 功能描述：对String解码.
     *
     * @param string 需要解码的字符串
     * @return 解码后的字符串
     */
    public static String decodeToString(String string) throws IOException {
        if (string == null) {
            return null;
        } else {
            byte[] _byte = decode(string);
            return new String(_byte);
        }
    }

    /**
     * 功能描述：将对象编码为base64的String.     *
     *
     * @param object 要进行编码的对象.
     * @return 编码后的对象对应的bease64String.
     */
    public static String encodeObject(Object object) throws IOException {
        String objectString = null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                arrayOutputStream);
        objectOutputStream.writeObject(object);
        objectString = encode(arrayOutputStream
                .toByteArray());
        return objectString;
    }

    /**
     * 功能描述：将bease64的String解编码为对象.
     *
     * @param objectString 可以解编码为Object的Base64String,如果不能解编码为Object抛出异常.
     * @return 解编码成功后的对象.
     */
    public static Object decodeObject(String objectString) throws IOException, ClassNotFoundException {
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                decode(objectString));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        object = objectInputStream.readObject();
        return object;
    }

    /**
     * Base64编码.
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * 功能描述：测试
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String s = "sadfihowiqjDLAFKJASDLFKLASD阿拉卡积分拉丝机v3u5925u";
        String s1 = Base64Fiend.encode(s.getBytes());
        System.out.println(s1);
        String s3 = "c2FkZmlob3dpcWpETEFGS0pBU0RMRktMQVNE6Zi/5ouJ5Y2h56ev5YiG5ouJ5Lid5py6djN1NTkyNXU=";
        String s2 = new String(Base64Fiend.decode(s3));
        System.out.println(s2);
    }
}
