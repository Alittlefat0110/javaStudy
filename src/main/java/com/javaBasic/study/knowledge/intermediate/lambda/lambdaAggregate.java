package com.javaBasic.study.knowledge.intermediate.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 聚合操作
 */
public class lambdaAggregate {
    public static void main(String[] args) {
        Random r = new Random();
        List<HeroL> heros = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            heros.add(new HeroL("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);

        HeroCheckerL c = new HeroCheckerL() {
            @Override
            public boolean test(HeroL A) {
                return A.hp>100 && A.damage<50;
            }
        };

        System.out.println("使用匿名类过滤");
        filter(heros, c);
        System.out.println("使用Lambda表达式");
        filter(heros, h->h.hp>100 && h.damage<50);
        System.out.println("在Lambda表达式中使用静态方法");
        filter(heros, h -> lambdaAggregate.testHero(h) );
        System.out.println("直接引用静态方法");
        filter(heros, lambdaAggregate::testHero);
    }

    public static boolean testHero(HeroL A) {
        return A.hp>100 && A.damage<50;
    }

    private static void filter(List<HeroL> heros, HeroCheckerL checker) {
        for (HeroL hero : heros) {
            if (checker.test(hero))
                System.out.print(hero);
        }
    }

}
interface HeroCheckerL{
    boolean test(HeroL A);
}
class HeroL implements Comparable<HeroL> {
    public String name;
    public float hp;
    public int damage;
    public HeroL() {

    }

    public HeroL(String name) {
        this.name = name;

    }

    //初始化name,hp,damage的构造方法
    public HeroL(String name, float hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public int compareTo(HeroL anotherHero) {
        if (damage < anotherHero.damage)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "HeroL [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }
}
