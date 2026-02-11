package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass){
        Object value = params.getOrDefault(key, null);
        if (value == null || value.toString().isEmpty()) return null;
        if (tClass == Long.class) value = Long.valueOf(value.toString());
        else value = value.toString();
        return tClass.cast(value);
    }
}
