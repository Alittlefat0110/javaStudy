package com.javaBasic.study.intermediate.collection;

import java.util.ArrayList;

/**
 * 常用方法
 */
public class CommonMethods {
    /**
     * 关键字
     * add	     增加
     * contains	 判断是否存在
     * get	     获取指定位置的对象
     * indexOf	 获取对象所处的位置
     * remove	 删除
     * set	     替换
     * size	     获取大小
     * toArray	 转换为数组
     * addAll	 把另一个容器所有对象都加进来
     * clear	 清空
     * @param args
     */
    public static void main(String[] args){
        ArrayList heros = new ArrayList();
        /**
         * 添加元素
         */
        // 把5个对象加入到ArrayList中
        Hero a;
        for (int i = 0; i < 5; i++) {
            a=new Hero("hero " + i);
            String b="hi "+i;
            //添加自定义类对象
            heros.add(a);
            //添加普通字符串对象
            heros.add(b);

        }
        //System.out.println(heros);
         //在指定位置增加对象
        Hero specialHero = new Hero("special hero");
        heros.add(3, specialHero);
        //System.out.println(heros.toString());
        /**
         * 判断是否存在
         */
//        通过方法contains 判断一个对象是否在容器中
//        判断标准： 是否是同一个对象，而不是name是否相同
        //System.out.print("虽然一个新的对象名字也叫 hero 1，但是contains的返回是:");
        //返回false，new之后已经不是原来那个对象（hero 1）了
        heros.contains(new Hero("hero 1"));
        //返回true
        //System.out.print("而对specialHero的判断，contains的返回是:");
        heros.contains(specialHero);
        //普通元素判断是否包含
       heros.contains("hi 1");

        ArrayList arrayList=new ArrayList();
        arrayList.add("香蕉");
        arrayList.add("苹果");
        arrayList.contains("苹果");

        /**
         * 获取指定位置的对象：
         * 通过get获取指定位置的对象，如果输入的下标越界，一样会报错
         */
        //获取指定位置的对象
        heros.get(5);

        /**
         * 获取对象所处的位置：
         * indexOf用于判断一个对象在ArrayList中所处的位置
         * 与contains一样，判断标准是对象是否相同，而非对象的name值是否相等
         */
        heros.indexOf(specialHero);
        heros.indexOf(new Hero("hero 1"));

        /**
         * 删除：
         * remove用于把对象从ArrayList中删除
         * remove可以根据下标删除ArrayList的元素
         */
        //删除下标是2的对象
        //heros.remove(2);
        //删除special hero
        //heros.remove(specialHero);
        /**
         * 替换：
         * set用于替换指定位置的元素
         */
        //把下标是5的元素，替换为"hero 5";
        heros.set(5, new Hero("hero 5"));

        /**
         * 获取大小
         * size 用于获取ArrayList的大小
         */
        heros.size();

        /**
         * 转换为数组
         * toArray可以把一个ArrayList对象转换为数组。
         * 需要注意的是，如果要转换为一个Hero数组，那么需要传递一个Hero数组类型的对象给toArray()，
         * 这样toArray方法才知道，你希望转换为哪种类型的数组，否则只能转换为Object数组
         */
        Hero hs[] = (Hero[])heros.toArray(new Hero[]{});

        /**
         * 把另一个容器所有对象都加进来
         * addAll 把另一个容器所有对象都加进来
         */
        ArrayList anotherHeros = new ArrayList();
        anotherHeros.add(new Hero("hero a"));
        anotherHeros.add(new Hero("hero b"));
        anotherHeros.add(new Hero("hero c"));
        heros.addAll(anotherHeros);

        /**
         * 清空
         * clear 清空一个ArrayList
         */
        heros.clear();
    }
}
