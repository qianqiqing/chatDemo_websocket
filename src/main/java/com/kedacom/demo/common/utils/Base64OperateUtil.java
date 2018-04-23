package com.kedacom.demo.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * base64工具类
 * @author 钱其清
 */
public class Base64OperateUtil {

    /**
     * 使用jdk的base64 加密字符串
     * */
    public static String jdkBase64Encoder(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] strByte = new byte[0];
        try {
            strByte = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodeStr = encoder.encode(strByte);
        return encodeStr;
    }

    /**
     * 使用jdk的base64 解密字符串
     * 返回为null表示解密失败
     * */
    public static String jdkBase64Decoder(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        String decode=null;
        try {
            byte[] strByte = decoder.decodeBuffer(str);
            decode = new String(strByte, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decode;
    }

    /**
     * 判断是否为BASE加密
     * */
    public static boolean ifBase(String val) {
        if (val.equals(jdkBase64Encoder(jdkBase64Decoder(val)))) {
            return true;
        } else {
            return false;
        }
    }
}
