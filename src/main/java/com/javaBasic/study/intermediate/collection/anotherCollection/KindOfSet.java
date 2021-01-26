package com.javaBasic.study.intermediate.collection.anotherCollection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * 不同的Set
 * HashSet LinkedHashSet TreeSet
 * HashSet： 无序
 * LinkedHashSet： 按照插入顺序
 * TreeSet： 从小到大排序
 */
public class KindOfSet {
    public static void main(String[] args) {
        HashSet<Integer> numberSet1 = new HashSet<>();
        //HashSet中的数据不是按照插入顺序存放
//        numberSet1.add(88);
//        numberSet1.add(8);
//        numberSet1.add(888);
//
//        System.out.println(numberSet1);

        LinkedHashSet<Integer> numberSet2 = new LinkedHashSet<>();
        //LinkedHashSet中的数据是按照插入顺序存放
//        numberSet2.add(88);
//        numberSet2.add(8);
//        numberSet2.add(888);
//
//        System.out.println(numberSet2);
        TreeSet<Integer> numberSet3 = new TreeSet<>();
        //TreeSet 中的数据是进行了排序的
//        numberSet3.add(88);
//        numberSet3.add(8);
//        numberSet3.add(888);
//
//        System.out.println(numberSet3);
        for (int i=0;i<10;i++){
            int a=(int)(Math.random()*100);
            System.out.print(+a+" ");
            numberSet1.add(a);
            numberSet2.add(a);
            numberSet3.add(a);
        }
        System.out.println("\n"+numberSet1);
        System.out.println(numberSet2);
        System.out.println(numberSet3);
    }
}
