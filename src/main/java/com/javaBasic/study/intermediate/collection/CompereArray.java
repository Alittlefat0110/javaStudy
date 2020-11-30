package com.javaBasic.study.intermediate.collection;

import java.util.ArrayList;

/**
 * 集合与数组的区别
 */
public class CompereArray {
    /**
     * 使用数组的局限性：
     * 如果要存放多个对象，可以使用数组，但是数组有局限性
     * 比如 声明长度是10的数组
     * 不用的数组就浪费了
     * 超过10的个数，又放不下
     */
    public static void main(String[] args) {
        //数组的局限性
        Hero heros[] = new Hero[10];
        //声明长度是10的数组
        //不用的数组就浪费了
        //超过10的个数，又放不下
        heros[0] = new Hero("盖伦");
        //放不下要报错
        heros[20] = new Hero("提莫");

    }
}

/**
 * ArrayList
 */
class arrayList{
    /**
     * ArrayList存放对象:
     * 为了解决数组的局限性，引入容器类的概念。 最常见的容器类就是
     * ArrayList
     * 容器的容量"capacity"会随着对象的增加，自动增长
     * 只需要不断往容器里增加英雄即可，不用担心会出现数组的边界问题。
     * @param args
     */
    public static void main(String[] args){
        //容器类ArrayList，用于存放对象
        ArrayList heros = new ArrayList();
        heros.add( new Hero("盖伦"));
        System.out.println(heros.size());

        //容器的容量"capacity"会随着对象的增加，自动增长
        //只需要不断往容器里增加英雄即可，不用担心会出现数组的边界问题。
        heros.add( new Hero("提莫"));
        System.out.println(heros.size());
    }
}

/**
 * 创建Hero类
 */
class Hero {
    public String name;
    public float hp;

    public int damage;

    public Hero() {

    }

    // 增加一个初始化name的构造方法
    public Hero(String name) {

        this.name = name;
    }

    // 重写toString方法
    public String toString() {
        return name;
    }

}
