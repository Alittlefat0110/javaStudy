package com.javaBasic.study.intermediate.collection.anotherCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection
 * Collections
 */
public class CollectionX {
    /**
     * Collection是一个接口
     * Collection是 Set List Queue和 Deque的接口
     * Queue: 先进先出队列
     * Deque: 双向链表
     *
     * 注：Collection和Map之间没有关系，Collection是放一个一个对象的，Map 是放键值对的
     * 注：Deque 继承 Queue,间接的继承了 Collection
     */

    /**
     * 接口与接口不能实现，只能继承。类实现接口啊。可以做一个类比：接口相当于动作的集合（当然接口也可以有数据）
     * 而且这些动作必须是public的，而类可以作为具体的东东去真正去做这些动作，故只能实现
     */

    public static void main(String[] args){
        /**
         * Collections是一个类，容器的工具类,就如同Arrays是数组的工具类
         * reverse	反转
         * shuffle	混淆
         * sort	    排序
         * swap	    交换
         * rotate   滚动
         * synchronizedList	线程安全化
         */
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据: \n"+numbers);
        //反转
        Collections.reverse(numbers);
        System.out.println("翻转后集合中的数据: \n"+numbers);
        //混淆
        Collections.shuffle(numbers);
        //排序
        Collections.sort(numbers);
        //交换
        Collections.swap(numbers,0,5);
        //滚动，把集合向右滚动2个单位
        Collections.rotate(numbers,2);
        //把非线程安全的List转换为线程安全的List
        List<Integer> synchronizedNumbers = Collections.synchronizedList(numbers);


        /**
         * 练习：统计概率
         * 首先初始化一个List,长度是10，值是0-9。
         * 然后不断的shuffle，直到前3位出现
         * 3 1 4
         * shuffle 1000,000 次，统计出现的概率
         */
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        long start = System.currentTimeMillis();
        long end = 0;
        int count = 0;
        for (int i=1;i<1000000;i++) {
            Collections.shuffle(list);
            if(list.get(0)==3&&list.get(1)==1&&list.get(2)==4) {
                end = System.currentTimeMillis();
                count++;
                System.out.println("查找第"+i+"个总共消耗了"+(end-start)+"ms");
            }
        }
        System.out.println("314出现的概率为"+(count/1000000.0)+"%");
    }
}
