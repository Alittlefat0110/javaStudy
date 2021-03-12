package com.javaBasic.study.knowledge.intermediate.collection.anotherCollection;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * HashMap
 */
public class HashMapX {
    /**
     * HashMap储存数据的方式是—— 键值对
     */
    public static void main(String[] args) {
        HashMap<String,String> dictionary = new HashMap<>();
        dictionary.put("adc", "物理英雄");
        dictionary.put("apc", "魔法英雄");
        dictionary.put("t", "坦克");

        System.out.println(dictionary.get("t"));
        hashMap();
    }

    /**
     * 键不能重复，值可以重复:
     * 对于HashMap而言，key是唯一的，不可以重复的。
     * 所以，以相同的key 把不同的value插入到 Map中会导致旧元素被覆盖，只留下最后插入的元素。
     * 不过，同一个对象可以作为值插入到map中，只要对应的key不一样
     */
    private static void hashMap(){
        HashMap<String,HeroZ> heroMap = new HashMap();

        heroMap.put("gareen", new HeroZ("gareen1"));
        System.out.println(heroMap);
HeroZ heroZ=new HeroZ();
heroZ.setName("gareen1");
        //key为gareen已经有value了，再以gareen作为key放入数据，会导致原英雄，被覆盖
        //不会增加新的元素到Map中
        heroMap.put("gareen", new HeroZ("gareen2"));
        System.out.println(heroMap);

        //清空map
        heroMap.clear();
        HeroZ gareen = new HeroZ("gareen");

        //同一个对象可以作为值插入到map中，只要对应的key不一样
        heroMap.put("hero1", gareen);
        heroMap.put("hero2", gareen);

        System.out.println(heroMap);

    }

}

/**
 * 练习-查找内容性能比较
 *准备一个ArrayList其中存放3000000(三百万个)Hero对象，其名称是随机的,格式是hero-[4位随机数]
 * hero-3229
 * hero-6232
 * hero-9365
 * ...
 *
 * 因为总数很大，所以几乎每种都有重复，把名字叫做 hero-5555的所有对象找出来
 * 要求使用两种办法来寻找
 * 1. 不使用HashMap，直接使用for循环找出来，并统计花费的时间
 * 2. 借助HashMap，找出结果，并统计花费的时间
 */
class practice{
    public static void main(String[] args){
        //practice();
        List<HeroZ> hs = new ArrayList<>();
        System.out.println("初始化开始");
        for(int i =0;i<3000000;i++)
        {
            HeroZ h = new HeroZ("hero-"+((int)(Math.random()*9000)+1000));
            hs.add(h);
        }
        HashMap<String,List<HeroZ>> heroMap = new HashMap<>();
        for(HeroZ h : hs)
        {
            List<HeroZ> list = heroMap.get(h.getName());
            if(list==null)
            {
                list=new ArrayList<>();
                heroMap.put(h.getName(),list);
            }
            list.add(h);
        }
        System.out.println("初始化结束");
        System.out.println("开始查找");
        findByMap(heroMap);
        findByIteration(hs);

    }
    public static void practice(){
        List<HeroZ> list=new ArrayList<>();
        for (int i=0;i<3000000;i++){
            list.add(new HeroZ("hero-"+(int)(Math.random()*10000)));
        }
        long start=System.currentTimeMillis();
        for (HeroZ heroZ:list){
            //String类型的数据对比用equals而不能用==
            if (heroZ.getName().equals("hero-5555")){
                System.out.println(heroZ);
            }
        }
        long end =System.currentTimeMillis();
        float l=(end-start)/1000f;
        System.out.println(l);
    }

    private static List<HeroZ> findByMap(HashMap<String,List<HeroZ>> m)
    {
        long start =System.currentTimeMillis();
        List<HeroZ> result= m.get("hero-6666");
        long end = System.currentTimeMillis();
        System.out.printf("通过map查找，一共找到%d个英雄，耗时%d毫秒%n",result.size(),end-start);
        return result;
    }

    private  static  List<HeroZ> findByIteration(List<HeroZ> hs)
    {
        long start = System.currentTimeMillis();
        List<HeroZ> result= new ArrayList<>();
        for(HeroZ h : hs)
        {
            if(h.getName().equals("hero-6666"))
            {
                result.add(h);
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("通过for查找，一共找到%d个英雄，耗时%d毫秒%n",result.size(),end-start);
        return result;
    }

}
@Data
class HeroZ{
    private String name;
    private float hp;

    public HeroZ(){
    }

    public HeroZ(String name){
        this.name=name;
    }
    public HeroZ(String name,float hp){
        this.name=name;
        this.hp=hp;
    }
}
