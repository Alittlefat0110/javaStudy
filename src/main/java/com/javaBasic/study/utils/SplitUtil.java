package com.javaBasic.study.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼接工具
 */
public class SplitUtil {
    /**
     * 将两个list列表中的字符串拼接成一个String字符串以英文逗号“,”间隔开
     *
     * @Param
     */
    //需改进
    public static String splitUtil(List<String> list1, List<String> list2) {
        java.lang.StringBuilder str1 = new java.lang.StringBuilder();
        java.lang.StringBuilder str2 = new java.lang.StringBuilder();
        if (list1 != null && list1.size() > 0) {
            for (int i = 0; i < list1.size(); i++) {
                str1.append(list1.get(i));
                if (i < list1.size() - 1) {
                    str1.append(",");
                }
            }
        }
        System.out.println(str1);
        if (list2 != null && list2.size() > 0) {
            for (int i = 0; i < list2.size(); i++) {
                str2.append(list2.get(i));
                if (i < list2.size() - 1) {
                    str2.append(",");
                }
            }
        }
        System.out.println(str2);
        String Str3 = "";
        String[] arr = {str1.toString(), str2.toString()};
        for (int i = 0; i < arr.length; i++) {
            Str3 += arr[i];
            if (i != arr.length - 1) {
                Str3 += ",";
            }
        }
        return Str3;
    }

    /**
     * 将两个String字符串拼接成一个字符串以英文逗号“,”间隔开
     *
     * @Param
     */
    public static String splitString(String str1, String str2) {
        String str = "";
        if (CheckUtil.isEmpty(str1) && !CheckUtil.isEmpty(str2)) {
            str = str2;
        } else if (CheckUtil.isEmpty(str2) && !CheckUtil.isEmpty(str1)) {
            str = str1;
        } else {
            String[] arr = {str1, str2};
            for (int i = 0; i < arr.length; i++) {
                str += arr[i];
                if (i != arr.length - 1) {
                    str += ",";
                }
            }
        }
        return str;
    }

    /**
     * 用某个符号拼接字符
     * @param str1
     * @param str2
     * @return
     */
    public static String splitWord(String str1 , String str2 , String str3){
        //第一二个字符以 , 间隔 ， 第三四个字符以 | 间隔
        String result=String.format("%s,%s|%s",str1,str2,str3);
        return result;
    }
    public static String splitList(List<String> list){
        //循环拼接list中的字符
        String result =list.get(0);
        for (int i = 1; i<list.size();i++){
            String str = list.get(i);
            String str1=String.format("%s,%s",result,str);
            result = str1;

        }
        return result;
    }
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("一");
        list.add("二");
        list.add("三");
        list1.add("四");
        list1.add("五");
        list1.add("六");
        String str=splitUtil(list,list1);
        System.out.println(str);
    }
}
