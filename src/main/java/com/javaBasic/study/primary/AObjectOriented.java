package com.javaBasic.study.primary;

import java.util.*;

/**
 * 类与对象，对象实例化
 * list set map的区别
 */
public class AObjectOriented {
    String name;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Practice1{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args){
        //实例化对象
        List<AObjectOriented> list2=new ArrayList<>();
        AObjectOriented bloodVial=new AObjectOriented();
        bloodVial.setName("血瓶");
        bloodVial.setPrice(50);
        //放入map中
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",bloodVial.name);
        map.put("price",bloodVial.price);
        //放入list中
        List<Object> list=new ArrayList<>();
        list.add(bloodVial.name);
        list.add(bloodVial.price);
        //放入LinkedList中   ArrayList是实现了基于动态数组的数据结构，LinkedList是基于链表结构。
        List<Object> list1=new LinkedList<>();list2.add(bloodVial);
        System.out.println("get，set方法-->"+list2);

        list1.add(bloodVial.name);
        list1.add(bloodVial.price);
        //放入set中
        Set<Object> set=new HashSet<>();
        set.add(bloodVial.name);
        set.add(bloodVial.price);
        //输出[50, 血瓶]
        System.out.println("set-->"+set);
        //输出ArrayList-->[血瓶, 50]
        System.out.println("ArrayList-->"+list);
        //输出LinkedList-->[血瓶, 50]
        System.out.println("LinkedList-->"+list1);
        //输出{price=50, name=血瓶}
        System.out.println("HashMap-->"+map);

    }
}

class Node{
    public static void main(String[] args){
    }
}
