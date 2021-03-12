package com.javaBasic.study.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 拆分一个String类型的，以英文半角逗号”,“间隔的字符串
 *
 * @Param str
 * @return
 */

public class TokenUtil {
    public static List<String> tokenString(String str) {
        StringTokenizer token = new StringTokenizer(str, ",");
        //String[] result = new String[token.countTokens()];
        //token.countTokens()可不加
        List<String> list = new ArrayList<>(token.countTokens());
        int i = 0;
        if (CheckUtil.isEmpty(str)) {
            //result = new String[]{""};
            //可不要词句，效果相同
            list = new ArrayList<>();
        } else {
            while (token.hasMoreTokens()) {
                //返回list集合
                list.add(token.nextToken());
                //返回数组
                //result[i++] = token.nextToken();
            }
        }
        //return result;
        return list;
    }

    public static void main(String[] args){
        String str = "a,b,c,d";
        String str1 = "";

        for (String s : tokenString(str1) ){
            System.out.println(s);
        }
        System.out.println(tokenString(str1));
    }
}

