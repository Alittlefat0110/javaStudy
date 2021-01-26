package com.javaBasic.study.intermediate.collection.anotherCollection;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * HashCode原理
 */
public class HashCodeX {
    public static void main(String[] args) {
        List<HeroH> heros = new ArrayList<>();
        HashMap<String,HeroH> heroMap = new HashMap<>();
    for (int j = 0; j < 2000000; j++) {
        HeroH h = new HeroH("Hero " + j);
        heros.add(h);
        heroMap.put(h.getName(), h);
    }
        getList(heros);
        getHashMap(heroMap);
  }

    /**
     * List查找的低效率
     * 假设在List中存放着无重复名称，没有顺序的2000000个Hero
     * 要把名字叫做“hero 1000000”的对象找出来
     * List的做法是对每一个进行挨个遍历，直到找到名字叫做“hero 1000000”的英雄。
     * 最差的情况下，需要遍历和比较2000000次，才能找到对应的英雄。
     * 测试逻辑：
     * 1. 初始化2000000个对象到ArrayList中
     * 2. 打乱容器中的数据顺序
     * 3. 进行10次查询，统计每一次消耗的时间
     * @param heros
     */
  private static void getList( List<HeroH> heros){
      // 进行10次查找，观察大体的平均值
      for (int i = 0; i < 10; i++) {
          // 打乱heros中元素的顺序
          Collections.shuffle(heros);

          long start = System.currentTimeMillis();

          String target = "Hero 1000000";

          for (HeroH hero : heros) {
              if (hero.getName().equals(target)) {
                  System.out.println("找到了 hero!");
                  break;
              }
          }
          long end = System.currentTimeMillis();
          long elapsed = end - start;
          System.out.println("一共花了：" + elapsed + " 毫秒");
      }
    }

    private static void getHashMap(HashMap<String,HeroH> heroMap){

        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();

            //查找名字是Hero 1000000的对象
            HeroH target = heroMap.get("Hero 1000000");
            System.out.println("找到了 hero!" + target.getName());

            long end = System.currentTimeMillis();
            long elapsed = end - start;
            System.out.println("一共花了：" + elapsed + " 毫秒");
        }
    }
    /**
     * 分析HashMap性能卓越的原因顶折纠问
     * -----hashcode概念-----
     * 所有的对象，都有一个对应的hashcode（散列值）
     * 比如字符串“gareen”对应的是1001 (实际上不是，这里是方便理解，假设的值)
     * 比如字符串“temoo”对应的是1004
     * 比如字符串“db”对应的是1008
     * 比如字符串“annie”对应的也是1008
     *
     * -----保存数据-----
     * 准备一个数组，其长度是2000，并且设定特殊的hashcode算法，使得所有字符串对应的hashcode，都会落在0-1999之间
     * 要存放名字是"gareen"的英雄，就把该英雄和名称组成一个键值对，存放在数组的1001这个位置上
     * 要存放名字是"temoo"的英雄，就把该英雄存放在数组的1004这个位置上
     * 要存放名字是"db"的英雄，就把该英雄存放在数组的1008这个位置上
     * 要存放名字是"annie"的英雄，然而 "annie"的hashcode 1008对应的位置已经有db英雄了，那么就在这里创建一个链表，
     * 接在db英雄后面存放annie
     *
     * -----查找数据-----
     * 比如要查找gareen，首先计算"gareen"的hashcode是1001，根据1001这个下标，到数组中进行定位，
     * （根据数组下标进行定位，是非常快速的） 发现1001这个位置就只有一个英雄，那么该英雄就是gareen.
     * 比如要查找annie，首先计算"annie"的hashcode是1008，根据1008这个下标，到数组中进行定位，
     * 发现1008这个位置有两个英雄，那么就对两个英雄的名字进行逐一比较(equals)，因为此时需要比较的量就已经少很多了，
     * 很快也就可以找出目标英雄
     *
     * 这就是使用hashmap进行查询，非常快原理。
     * 这是一种用空间换时间的思维方式
     */


    /**
     * HashSet判断是否重复顶折纠问
     * HashSet的数据是不能重复的，相同数据不能保存在一起，到底如何判断是否是重复的呢？
     * 根据HashSet和HashMap的关系，我们了解到因为HashSet没有自身的实现，而是里面封装了一个HashMap，所以本质上就是判断HashMap的key是否重复。
     *
     * 再通过上一步的学习，key是否重复，是由两个步骤判断的：
     * hashcode是否一样
     * 如果hashcode不一样，就是在不同的坑里，一定是不重复的
     * 如果hashcode一样，就是在同一个坑里，还需要进行equals比较
     * 如果equals一样，则是重复数据
     * 如果equals不一样，则是不同数据。
     */
}

/**
 * HashCode练习
 */
class practiceH{
    public static void main(String[] args){
        Map<String,Integer> map=new HashMap<>();
        for (int i=0;i<5;i++) {

            String str="";
            int count = (int) (Math.random()*8+2);
            for (int j=0;j<count;j++){
                str+=Arr();
            }
            //System.out.println(count);
            //String filename = RandomStringUtils.randomAlphanumeric(count);
            String name=RandomStringUtils.random(count,str);
           // System.out.println(name);
            int code = hashcode(name);
            map.put(name,code);
            //System.out.println("字符串：" + filename + " 的HashCode值为： " + code);
        }
        System.out.println(map);
    }
    /**
     * 练习-自定义字符串的hashcode
     * 如下是Java API提供的String的hashcode生成办法；
     *
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     *
     * s[0] 表示第一位字符
     * n表示字符串的长度
     * 本练习并不是要求去理解这个算法，而是自定义一个简单的hashcode算法，计算任意字符串的hashcode
     * 因为String类不能被重写，所以我们通过一个静态方法来返回一个String的hashcode
     *
     * public static int hashcode(String)
     *
     * 如果字符串长度是0，则返回0。
     * 否则： 获取每一位字符，转换成数字后，相加，最后乘以23
     *
     * (s[0]+ s[1] + s[2] + s[3]+ s[n-1])*23.
     *
     * 如果值超过了1999，则取2000的余数，保证落在0-1999之间。
     * 如果是负数，则取绝对值。
     *
     * 随机生成长度是2-10的不等的100个字符串，打印用本hashcode获取的值分别是多少
     */
    public static int hashcode(String str){
        int add=0;
        if (str.length()==0){
            return 0;
        }else {
            char[] c=str.toCharArray();
            for (int i=0;i<c.length;i++){
                byte b=(byte)c[i];
                 add+=b;
            }
        }
        int code=add*23;
        if (code<0){
            code= Math.abs(code);
            if (code>1999){
                code=code%1999;
            }
        }else if (code>1999){
            code= code%2000;
        }
       return code;
    }

    /**
     * 随机生成一个汉字
     * @return
     */
    public static String Arr()  {
        String str = null;
        try {
            int highPos, lowPos;
            Random random = new Random();
            highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
            random=new Random();
            lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列
            byte[] bArr = new byte[2];
            bArr[0] = (new Integer(highPos)).byteValue();
            bArr[1] = (new Integer(lowPos)).byteValue();
            str = new String(bArr, "GB2312");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return str;
    }
}

/**
 * 理解HashMap原理
 * hashcode的原理和自定义hashcode, 设计一个MyHashMap，实现接口IHashMap
 * MyHashMap内部由一个长度是2000的对象数组实现。
 */
class MyHashMap implements IHashMap{
    public static void main(String[] args){
        MyHashMap myHashMap=new MyHashMap();
        myHashMap.put("淇淇","宝贝");
        System.out.println(myHashMap.get("淇淇"));
    }
    LinkedList<Entry>[] entries=new LinkedList[2000];
    /**
     * 设计put(String key,Object value)方法
     * 首先通过上一个自定义字符串的hashcode练习获取到该字符串的hashcode,然后把这个hashcode作为下标，定位到数组的指定位置。
     * 如果该位置没有数据，则把字符串和对象组合成键值对Entry，再创建一个LinkedList，把键值对，放进LinkedList中，
     * 最后把LinkedList 保存在这个位置。
     * 如果该位置有数据，一定是一个LinkedList,则把字符串和对象组合成键值对Entry，插入到LinkedList后面。
     * @param key
     * @param object
     */
    public void put(String key,Object object){
        LinkedList<Entry> list=new LinkedList<>();
        int code=practiceH.hashcode(key);
        if (entries[code]==null){
            Entry entry=new Entry(key,object);
            list.add(entry);
            entries[code]=list;
        }else {
            boolean b=false;
            for (int i=0;i<entries[code].size();i++){
                if (key.equals(entries[code].get(i).key)){
                    //如果存在相同的key，则直接更新value
                    entries[code].get(i).value=object;
                    b=true;
                    break;
                }
            }
            if (!b) {
                //如果在当前位置的LinkedList中不存在相同key则将键值对插入链表中
                Entry entry=new Entry(key,object);
                entries[code].add(entry);
            }
        }
    }

    /**
     * 设计 Object get(String key) 方法
     * 首先通过上一个自定义字符串的hashcode练习获取到该字符串的hashcode,
     * 然后把这个hashcode作为下标，定位到数组的指定位置。
     * 如果这个位置没有数据，则返回空
     * 如果这个位置有数据，则挨个比较其中键值对的键-字符串，是否equals，
     * 找到匹配的，把键值对的值，返回出去。找不到匹配的，就返回空
     * @param key
     * @return
     */
    public Object get(String key){
        int code=practiceH.hashcode(key);
        Object a=null;
        if (entries[code]==null){
            return 0;
        }else {
            for (int i=0;i<entries[code].size();i++) {
                if (key.equals(entries[code].get(i).key)) {
                    a= entries[code].get(i);
                    break;
                }
            }
        }
        return a;
    }
}
interface IHashMap {
     void put(String key,Object object);
     Object get(String key);
}
class Entry {

    public Entry(Object key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }
    public Object key;
    public Object value;
    @Override
    public String toString() {
        return "[key=" + key + ", value=" + value + "]";
    }

}


@Data
class HeroH{
    private String name;
    private float hp;

    public HeroH(){
    }

    public HeroH(String name){
        this.name=name;
    }
    public HeroH(String name,float hp){
        this.name=name;
        this.hp=hp;
    }
}

