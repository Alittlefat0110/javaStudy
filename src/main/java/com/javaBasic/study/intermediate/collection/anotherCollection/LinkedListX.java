package com.javaBasic.study.intermediate.collection.anotherCollection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 与ArrayList一样，LinkedList也实现了List接口，
 * 诸如add,remove,contains等等方法
 */
public class LinkedListX {
    /**
     * 除了实现了List接口外，LinkedList还实现了双向链表结构Deque，可以很方便的在头尾插入删除数据
     * 什么是链表结构: 与数组结构相比较，数组结构，就好像是电影院，每个位置都有标示，每个位置之间的间隔都是一样的。
     * 而链表就相当于佛珠，每个珠子，只连接前一个和后一个，不用关心除此之外的其他佛珠在哪里
     * @param args
     */
    public static void main(String[] args){
        //LinkedList是一个双向链表结构的list
        LinkedList<HeroX> ll = new LinkedList<>();

        //所以可以很方便的在头部和尾部插入数据
        //在最后插入新的英雄
        ll.addLast(new HeroX("hero1"));
        ll.addLast(new HeroX("hero2"));
        ll.addLast(new HeroX("hero3"));
        System.out.println(ll);

        //在最前面插入新的英雄
        ll.addFirst(new HeroX("heroX"));
        System.out.println(ll);

        //查看最前面的英雄
        System.out.println(ll.getFirst());
        //查看最后面的英雄
        System.out.println(ll.getLast());

        //查看不会导致英雄被删除
        System.out.println(ll);
        //取出最前面的英雄
        System.out.println(ll.removeFirst());

        //取出最后面的英雄
        System.out.println(ll.removeLast());

        //取出会导致英雄被删除
        System.out.println(ll);

    }
}

/**
 * 队列 - Queue
 */
class LinkedListY{
    /**
     * LinkedList 除了实现了List和Deque外，还实现了Queue接口(队列)。
     * Queue是先进先出队列 FIFO，常用方法：
     * offer 在最后添加元素
     * poll  取出第一个元素
     * peek  查看第一个元素
     */
    public static void main(String[] args){
        //和ArrayList一样，LinkedList也实现了List接口
        List ll =new LinkedList<HeroX>();

        //所不同的是LinkedList还实现了Deque，进而又实现了Queue这个接口
        //Queue代表FIFO 先进先出的队列
        Queue<HeroX> q= new LinkedList<HeroX>();

        //加在队列的最后面
        System.out.print("初始化队列：\t");
        q.offer(new HeroX("Hero1"));
        q.offer(new HeroX("Hero2"));
        q.offer(new HeroX("Hero3"));
        q.offer(new HeroX("Hero4"));

        System.out.println(q);
        System.out.print("把第一个元素取poll()出来:\t");
        //取出第一个Hero，FIFO 先进先出
        HeroX h = q.poll();
        System.out.println(h);
        System.out.print("取出第一个元素之后的队列:\t");
        System.out.println(q);

        //把第一个拿出来看一看，但是不取出来
        h=q.peek();
        System.out.print("查看peek()第一个元素:\t");
        System.out.println(h);
        System.out.print("查看并不会导致第一个元素被取出来:\t");
        System.out.println(q);
    }
}

/**
 * LinkedList实现Stack栈
 */
class MyStack implements stack {
    LinkedList<HeroX> l = new LinkedList<>();
    //设为线程安全
    LinkedList<HeroX> ll=(LinkedList<HeroX>) Collections.synchronizedList(l);
    //把英雄推入到最后位置
    @Override
    public synchronized void push(HeroX h){
        ll.addLast(h);
    }
    //把最后一个英雄取出来LinkedList<Hero> ll=(LinkedList<Hero>) Collections.synchronizedList(l);
    @Override
    public synchronized HeroX pull(){
        return ll.removeLast();
    };
    //查看最后一个英雄
    @Override
    public  synchronized HeroX  peek(){
        return ll.getLast();
    };
    /**
     * 与FIFO(先入先出的)队列类似的一种数据结构是FILO先入后出栈Stack
     * 根据接口Stack ：
     * 实现类：MyStack
     * public class MyStack implements Stack
     * 并向这个栈中，压入5个英雄，接着弹出5个英雄
     * @param args
     */
    public static void main(String[] args){
        MyStack myStack = new MyStack();
        for (int i=0;i<=4;i++) {
            HeroX heroX = new HeroX("hero"+i);
            //压入栈底
            myStack.push(heroX);
        }
        //取出（从栈中删除）
        myStack.pull();
        //查看
        System.out.println(myStack.peek());
        myStack.pull();
        System.out.println(myStack.peek());

    }
}
interface stack {

    //把英雄推入到最后位置
     void push(HeroX h);
    //把最后一个英雄取出来
    HeroX pull();
    //查看最后一个英雄
     HeroX peek();
}

class HeroX {
    public String name;
    public float hp;
    public int damage;
    public HeroX() {

    }

    // 增加一个初始化name的构造方法
    public HeroX(String name) {

        this.name = name;
    }

    // 重写toString方法
//    public String toString() {
//        return name;
//    }


    @Override
    public String toString() {
        return "HeroX{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", damage=" + damage +
                '}';
    }
}
