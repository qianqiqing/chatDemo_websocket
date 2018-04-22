package com.kedacom.demo.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json转换工具类
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Object转换json字符串
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toJson(T t) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * json字符串转Object
     * @param clazz
     * @param str
     * @param <T>
     * @return
     */
    public static <T> T toObject (Class<T> clazz, String str) {
        T object = null;
        try {
            object = objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
