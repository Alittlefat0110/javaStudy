package com.javaBasic.study.knowledge.intermediate.collection.anotherCollection;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

/**
 * HashSetX
 */
public class HashSetX {
    /**
     * Set中的元素，不能重复
     * @param args
     */
    public static void main(String[] args){
        HashSet<String> names = new HashSet();

        names.add("gareen");

        System.out.println(names);

        //第二次插入同样的数据，是插不进去的，容器中只会保留一个
        names.add("gareen");
        System.out.println(names);


        /**
         * Set中的元素，没有顺序。
         * 严格的说，是没有按照元素的插入顺序排列
         *
         * HashSet的具体顺序，既不是按照插入顺序，也不是按照hashcode的顺序
         * 不保证Set的迭代顺序; 确切的说，在不同条件下，元素的顺序都有可能不一样
         * 换句话说，同样是插入0-9到HashSet中， 在JVM的不同版本中，看到的顺序都是不一样的。
         * 所以在开发的时候，不能依赖于某种臆测的顺序，这个顺序本身是不稳定的
         */
        HashSet<Integer> numbers = new HashSet();

        numbers.add(9);
        numbers.add(5);
        numbers.add(1);

        // Set中的元素排列，不是按照插入顺序
        System.out.println(numbers);


        /**
         * 遍历
         * Set不提供get()来获取指定位置的元素
         * 所以遍历需要用到迭代器，或者增强型for循环
         */
        //Set不提供get方法来获取指定位置的元素
        //numbers.get(0)
        //遍历Set可以采用迭代器iterator
        for (Iterator<Integer> iterator = numbers.iterator(); iterator.hasNext();) {
            Integer i =  iterator.next();
            System.out.println(i);
        }

        //或者采用增强型for循环
        for (Integer i : numbers) {
            System.out.println(i);
        }
    }
}


/**
 * HashSet和HashMap的关系
 * 通过观察HashSet的源代码（如何查看源代码）
 * 可以发现HashSet自身并没有独立的实现，而是在里面封装了一个Map.
 * HashSet是作为Map的key而存在的
 * 而value是一个命名为PRESENT的static的Object对象，因为是一个类属性，所以只会有一个。
 *
 * private static final Object PRESENT = new Object();
 * @param <E>
 */
class HashSetXY<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {
    //HashSet里封装了一个HashMap
    private HashMap<E, Object> map;

    private static final Object PRESENT = new Object();

    //HashSet的构造方法初始化这个HashMap
    public HashSetXY() {
        map = new HashMap<E, Object>();
    }

    //向HashSet中增加元素，其实就是把该元素作为key，增加到Map中
    //value是PRESENT，静态，final的对象，所有的HashSet都使用这么同一个对象
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    //HashSet的size就是map的size
    public int size() {
        return map.size();
    }

    //清空Set就是清空Map
    public void clear() {
        map.clear();
    }

    //迭代Set,就是把Map的键拿出来迭代
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
}

/**
 * 练习-HashSet
 * 在比较字符串章节，有一个同样的练习
 * 创建一个长度是100的字符串数组
 * 使用长度是2的随机字符填充该字符串数组
 * 统计这个字符串数组里重复的字符串有多少种
 * 使用HashSet来解决这个问题
 */
class practiceX{
    public static void main(String[] args){
        HashSet<String> hashSet= new HashSet<>();
        HashSet<String> hashSet1=new HashSet<>();
        List<String> list=new ArrayList<>();
        String[] s=new String[300];
        for (int i=0;i<s.length;i++){
            String filename= RandomStringUtils.randomAlphanumeric(2);
            s[i]=filename;
        }
        for (String str:s){
            System.out.print(str+" ");
            //重复的不会插入
           boolean b=hashSet.add(str);
           if (!b){
               list.add(str);
               //重复的有可能是三个或以上
               hashSet1.add(str);
           }
        }
        System.out.println("\n重复的个数为："+hashSet1.size());
        System.out.println("分别是：\n"+list);
        System.out.println(hashSet1);
        list.clear();

    }
}
