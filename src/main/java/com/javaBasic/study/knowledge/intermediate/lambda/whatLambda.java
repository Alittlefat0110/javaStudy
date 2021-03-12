package com.javaBasic.study.knowledge.intermediate.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda表达式入门
 */
public class whatLambda {
    /**
     * 假设一个情景： 找出满足条件的Hero
     * 本教程将从使用普通方法，匿名类，以及Lambda这几种方式，逐渐的引入Lambda的概念
     * @param args
     */
    public static void main(String[] args){
//        Random r = new Random();
//        List<HeroD> heros = new ArrayList<HeroD>();
//        for (int i = 0; i < 10; i++) {
//            heros.add(new HeroD("hero " + i, r.nextInt(1000), r.nextInt(100)));
//        }
        //HeroD heroD=new HeroD();
        List<HeroD> list=new ArrayList<>();
        for (int i=0;i<=4;i++){
            HeroD heroD=new HeroD();
            heroD.name="hero "+i;
            float a=(float)(Math.random()*100+50);
            heroD.hp=(float)(Math.round(a*10))/10;
            heroD.damage=(int)(Math.random()*100);
            list.add(heroD);
        }
        System.out.println("初始化的集合：\n"+list);
        //普通方法
        System.out.println("普通方法筛选后的集合：\n"+forCyclic(list));
        //匿名类方法
        HeroChecker checker = new HeroChecker() {
            @Override
            public boolean test(HeroD h) {
                return (h.hp>100 && h.damage<50);
            }
        };
        System.out.println("匿名类方法筛选后的集合：\n"+filter(list,checker));

        //lambda表达式
        filter(list,h -> h.hp>100 && h.damage<50);
        /**
         * Lambda表达式可以看成是匿名类一点点演变过来
         * 1. 匿名类的正常写法
         * HeroChecker c1 = new HeroChecker() {
         *     public boolean test(Hero h) {
         *         return (h.hp>100 && h.damage<50);
         *     }
         * };
         *
         * 2. 把外面的壳子去掉
         * 只保留方法参数和方法体
         * 参数和方法体之间加上符号 ->
         * HeroChecker c2 = (Hero h) ->{
         * 	return h.hp>100 && h.damage<50;
         * };
         *
         * 3. 把return和{}去掉
         * HeroChecker c3 = (Hero h) ->h.hp>100 && h.damage<50;
         *
         * 4. 把 参数类型和圆括号去掉(只有一个参数的时候，才可以去掉圆括号)
         * HeroChecker c4 = h ->h.hp>100 && h.damage<50;
         *
         * 5. 把c4作为参数传递进去
         * filter(heros,c4);
         *
         * 6. 直接把表达式传递进去
         * filter(heros, h -> h.hp > 100 && h.damage < 50);
         *
         *
         * 与匿名类 概念相比较:
         * Lambda 其实就是匿名方法，这是一种把方法作为参数进行传递的编程思想。
         * 虽然代码是这么写
         * filter(heros, h -> h.hp > 100 && h.damage < 50);
         * 但是，Java会在背后，悄悄的，把这些都还原成匿名类方式。
         * 引入Lambda表达式，会使得代码更加紧凑，而不是各种接口和匿名类到处飞。
         */

    }

    /**
     * 普通方法 for循环
     * @param list
     * @return
     */
    private static List<HeroD> forCyclic(List<HeroD> list){
//        filter(List<Hero> heros) {
//            for (Hero hero : heros) {
//                if(hero.hp>100 && hero.damage<50)
//                    System.out.print(hero);
//            }
//        }
        List<HeroD> list1=new ArrayList<>();
        for (int j=0;j<list.size();j++){
            if (list.get(j).hp>100&&list.get(j).damage<50){
                list1.add(list.get(j));
            }
        }
        return list1;
    }

    /**
     * 配合匿名类方法，lambda表达式使用
     * @param list
     * @param checker
     * @return
     */
    private static List<HeroD> filter(List<HeroD> list,HeroChecker checker){
        List<HeroD> list1=new ArrayList<>();
        for (HeroD hero : list) {
            if(checker.test(hero))
               list1.add(hero);
        }
        return  list1;
    }
}

interface HeroChecker{
    boolean test(HeroD heroD);
}
class HeroD implements Comparable<HeroD> {
    public String name;
    public float hp;
    public int damage;
    public HeroD() {

    }

    public HeroD(String name) {
        this.name = name;

    }

    //初始化name,hp,damage的构造方法
    public HeroD(String name, float hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public int compareTo(HeroD anotherHero) {
        if (damage < anotherHero.damage)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }
}
