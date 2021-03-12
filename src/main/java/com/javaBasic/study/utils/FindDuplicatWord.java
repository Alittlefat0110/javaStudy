package com.javaBasic.study.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 查找首个重复元素
 */
public class FindDuplicatWord {
    public static String findDuplicatWord(List<String> list){
        for (int i = 0; i<list.size(); i++){
            String str = list.get(i);
            for (int j = i+1; j<list.size(); j++){
                String str1 = list.get(j);
                if (StringUtils.equals(str,str1)){
                    return str;
                }
            }
        }
        return null;
    }
}
