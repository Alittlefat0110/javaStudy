package com.javaBasic.study.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 获取map中value的最大值/key的最大值
 */
public class GetMaxKeyValueOfMap {
    public static Object getMaxValue(Map<Object, Integer> map) {
        if (map == null) return null;
        Collection<Integer> c = map.values();
        Object[] obj = c.toArray();
        //排序
        Arrays.sort(obj);
        return obj[obj.length - 1];
    }

    public static Object getMaxKey(Map<Object, Integer> map) {
        if (map == null) return null;
        Set<Object> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[obj.length - 1];
    }
}
