package com.javaBasic.study.intermediate.collection.anotherCollection;

import java.util.*;

/**
 * 比较器
 */
public class ComparatorX {
    /**
     * 假设Hero有三个属性 name,hp,damage
     * 一个集合中放存放10个Hero,通过Collections.sort对这10个进行排序
     * 那么到底是hp小的放前面？还是damage小的放前面？Collections.sort也无法确定
     * 所以要指定到底按照哪种属性进行排序
     * 这里就需要提供一个Comparator给定如何进行两个对象之间的大小比较
     * @param args
     */
    public static void main(String[] args){
        Random r =new Random();
        List<HeroC> heros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //通过随机值实例化hero的hp和damage
            heros.add(new HeroC("hero "+ i, r.nextInt(100), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);

        //直接调用sort会出现编译错误，因为Hero有各种属性
        //到底按照哪种属性进行比较，Collections也不知道，不确定，所以没法排
        //Collections.sort(heros);

        //引入Comparator，指定比较的算法
//        Comparator<HeroC> c = new Comparator<HeroC>() {
//            @Override
//            public int compare(HeroC h1, HeroC h2) {
//                //按照hp进行排序
//                if(h1.hp>=h2.hp)
//                    return 1;  //正数表示h1比h2要大
//                else
//                    return -1;
//            }
//        };
       // Collections.sort(heros,c);
        System.out.println("按照血量排序后的集合：");
        //lambda方法
        heros.sort((h1, h2) -> h1.hp >= h2.hp ? 1 : -1);
        System.out.println(heros);
    }
}
class HeroC {
    public String name;
    public float hp;

    public int damage;

    public HeroC() {

    }

    public HeroC(String name) {
        this.name = name;
    }

    public String toString() {
        return "Hero [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }

    public HeroC(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

}

/**
 * 使Hero类实现Comparable接口
 * 在类里面提供比较算法
 * Collections.sort就有足够的信息进行排序了，也无需额外提供比较器Comparator
 * 注： 如果返回-1, 就表示当前的更小，否则就是更大
 */
class HeroCX implements Comparable<HeroCX>{
    public String name;
    public float hp;

    public int damage;

    public HeroCX(){

    }

    public HeroCX(String name) {
        this.name =name;

    }

    //初始化name,hp,damage的构造方法
    public HeroCX(String name,float hp, int damage) {
        this.name =name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public int compareTo(HeroCX anotherHero) {
        if(damage<anotherHero.damage)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "HeroCX [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }

}