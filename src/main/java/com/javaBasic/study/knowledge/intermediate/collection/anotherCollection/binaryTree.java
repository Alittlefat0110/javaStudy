package com.javaBasic.study.knowledge.intermediate.collection.anotherCollection;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 二叉树
 */
public class binaryTree {

}

/**
 * 二叉树排序-插入数据
 * 假设通过二叉树对如下10个随机数进行排序
 * 67,7,30,73,10,0,78,81,10,74
 * 排序的第一个步骤是把数据插入到该二叉树中
 * 插入基本逻辑是，小、相同的放左边，大的放右边
 * 1. 67 放在根节点
 * 2. 7 比 67小，放在67的左节点
 * 3. 30 比67 小，找到67的左节点7，30比7大，就放在7的右节点
 * 4. 73 比67大， 放在67的右节点
 * 5. 10 比 67小，找到67的左节点7，10比7大，找到7的右节点30，10比30小，放在30的左节点。
 * ...
 * ...
 * 9. 10比67小，找到67的左节点7，10比7大，找到7的右节点30，10比30小，找到30的左节点10，10和10一样大，放在左边
 */
@Slf4j
@Data
class Node {
    // 左子节点
    public Node leftNode;
    // 右子节点
    public Node rightNode;
    // 值
    public Object value;
    // 插入 数据
    public void add(Object v) {
        List<Integer> list=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == value)
            value = v;
            // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else{
                // 新增的值，比当前值小或者相同
                if ((Integer) v - ((Integer) value) <= 0) {
                    if (null == leftNode)
                        leftNode = new Node();
                    leftNode.add(v);
                    list.add((Integer)leftNode.value);
                }
                // 新增的值，比当前值大
                else {
                    if (null == rightNode)
                        rightNode = new Node();
                    rightNode.add(v);
                    list1.add((Integer)rightNode.value);
                }
            }
        list2.add((Integer)value);
        System.out.println("左节点值-->"+list);
        System.out.println("右节点值-->"+list1);
        System.out.println("根节点值-->"+list2);
        System.out.println("---------------------------------");
    }
    public static void main(String[] args) {
        int randoms[] = new int[] { 67, 7, 30, 73, 10, 0, 78, 81, 10, 74 };
        Node roots = new Node();
        for (int number : randoms) {
            roots.add(number);
        }
        System.out.println(roots.valuesList());
    }

    // 中序遍历所有的节点
    public List<Object> valuesList() {
        List<Object> values = new ArrayList<>();
        // 左节点的遍历结果
        if (null != leftNode)
            values.addAll(leftNode.valuesList());

        // 当前节点
        values.add(value);

        // 右节点的遍历结果
        if (null != rightNode)
            values.addAll(rightNode.valuesList());
        return values;
    }

}

/**
 * 二叉排序树插入流程：Node（leftNode, rightNode,value)
 * Node(leftNode=Node(
 * leftNode=Node(leftNode=null, rightNode=null, value=0), rightNode=Node(leftNode=Node(leftNode=Node(leftNode=null, rightNode=null, value=10), rightNode=null, value=10), rightNode=null, value=30), value=7),
 * rightNode=Node(leftNode=null, rightNode=Node(leftNode=null, rightNode=Node(leftNode=null, rightNode=null, value=81), value=78), value=73), value=67)
 *
 * 第一层               第二层               第三层                   第四层                 第五层
 * Node(
 * leftNode=Node(
 *                     leftNode=Node(
 *                                        leftNode=null,
 *                                        rightNode=null,
 *                                        value=0),
 *                     rightNode=Node(
 *                                        leftNode=Node(
 *                                                               leftNode=Node(
 *                                                                                       leftNode=null,
 *                                                                                       rightNode=null,
 *                                                                                       value=10),
 *                                                               rightNode=null,
 *                                                               value=10),
 *                                         rightNode=null,
 *                                         value=30),
 *                       value=7),
 *
 *  rightNode=Node(
 *                       leftNode=null,
 *                       rightNode=Node(
 *                                         leftNode=null,
 *                                         rightNode=Node(
 *                                                                leftNode=null,
 *                                                                rightNode=null,
 *                                                                value=81),
 *                                          value=78),
 *                      value=73),
 * value=67)
 */


/**
 * 根据上面的学习和理解，设计一个Hero二叉树，HeroNode.
 * 可以向这个英雄二叉树插入不同的Hero对象，并且按照Hero的血量倒排序。
 *
 * 随机生成10个Hero对象，每个Hero对象都有不同的血量值，插入这个HeroNode后，把排序结果打印出来。
 */
class HeroNode{
    public HeroNode leftHeroNode;
    public HeroNode rightHeroNode;
    public Object hp;
    String name;
    private  void addX(Object v){
        if (null== hp)
            hp = v;
            // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else{
            // 新增的值，比当前值小或者相同
            if ((Integer) v - ((Integer) hp) <= 0) {
                if (null == leftHeroNode)
                    leftHeroNode = new HeroNode();
                leftHeroNode.addX(v);
            }
            // 新增的值，比当前值大
            else {
                if (null == rightHeroNode)
                    rightHeroNode = new HeroNode();
                rightHeroNode.addX(v);
            }
        }
    }
    public static void main(String[] args){
        int[] f=new int[10];
        HeroNode heroNode=new HeroNode();
        String name="";
        Object[][] A=new Object[10][2];
        Map<String,Object> map=new HashMap<>();
        for (int i=0;i<=9;i++){
            f[i]=(int) (Math.random()*100);
            name="name:hero "+i;
            A[i][0]= name;
            A[i][1]="hp:"+f[i];
            heroNode.addX(f[i]);
        }
        for (Object[] o:A) {
            System.out.println(Arrays.toString(o));
        }
        System.out.println("\n 根据血量到排序：");
        for (int j=0;j<=9;j++) {
            Object x=heroNode.valuesListX().get(j);
            String str=x.toString();
                for (int p=0;p<=9;p++) {
                    String s=A[p][1].toString();
                    if (s.endsWith(str)) {
                        System.out.println(Arrays.toString(A[p]));
                    }
            }
        }

    }

    // 逆序输出
    public List<Object> valuesListX() {
        List<Object> values = new ArrayList<>();
        // 右节点的遍历结果
        if (null != rightHeroNode)
            values.addAll(rightHeroNode.valuesListX());
        // 当前节点
        values.add(hp);
        // 左节点的遍历结果
        if (null != leftHeroNode)
            values.addAll(leftHeroNode.valuesListX());
        return values;
    }
}

/**
 * 官方方法
 */
class HeroNodeX {
    public HeroNodeX leftHero;
    public HeroNodeX rightHero;
    // 声明为Hero类型
    public HeroS value;
    public void add(HeroS v) {
        if (null == value)
            value = v;
        else {
            // 如果新英雄血量，比本节点大，就放在左边
            if (v.hp > value.hp) {
                if (null == leftHero)
                    leftHero = new HeroNodeX();
                leftHero.add(v);
            }
            else {
                if (null == rightHero)
                    rightHero = new HeroNodeX();
                rightHero.add(v);
            }
        }
    }

    public List<Object> values() {
        List<Object> values = new ArrayList<>();
        if (null != leftHero)
            values.addAll(leftHero.values());
        values.add(value);
        if (null != rightHero)
            values.addAll(rightHero.values());
        return values;
    }

    public static void main(String[] args) {

        List<HeroS> hs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HeroS h = new HeroS();
            h.name = "hero " + i;
            h.hp = (float) (Math.random() * 900 + 100); // 100-1000的随机血量
            hs.add(h);
        }
        System.out.println("初始化10个Hero");
        System.out.println(hs);

        HeroNodeX heroTree = new HeroNodeX();
        for (HeroS hero : hs) {
            heroTree.add(hero);
        }
        System.out.println("根据血量倒排序后的Hero");
        List<Object> treeSortedHeros = heroTree.values();
        System.out.println(treeSortedHeros);

    }
}
class HeroS{
    public String name;
    public float hp;
    public int damage;
    public HeroS() {
    }
    public HeroS(String name) {
        this.name = name;
    }
    //重写toString方法
    public String toString() {
        return String.format("[name:%s hp:%.0f]%n", name,hp);
    }
}


/**
 * 练习-比较冒泡法，选择法以及二叉树排序的性能区别
 * 创建4万个随机数，然后用分别用冒泡法，选择法，二叉树3种排序算法进行排序，比较哪种更快
 */
class sort{
    public sort left;
    public sort right;
    public Object value;

    public  void addS(int v) {
        if (null == value) {
            value = v;
        } else {
            if (v <= (int) value) {
                if (null == left) {
                    left = new sort();
                }
                left.addS(v);
            }
            else {
                if (null == right) {
                    right = new sort();
                }
                    right.addS(v);
                }
            }

        }
    public List<Object> values() {
        List<Object> values = new ArrayList<>();
        if (null != left)
            values.addAll(left.values());
        values.add(value);
        if (null != right)
            values.addAll(right.values());
        return values;
    }
    public static void main(String[] args){
        int[] b=new int[40000];
        int a=0;
        sort s=new sort();
        for (int i=0;i<b.length;i++){
            a=(int)(Math.random()*10000);
            b[i]=(int)(Math.random()*10000);
            s.addS(a);
        }
        //MaoPao(b);
        //Chose(b);
        for (int k:b){
           // System.out.println(k);
        }

        System.out.println(s.values());

    }

    /**
     * 冒泡法
     * @param b
     * @return
     */
    private static int[] MaoPao(int[] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length-i-1; j++) {
                if (b[j]>b[j+1]) {
                    int temp =b[j];
                    b[j]=b[j+1];
                    b[j+1]=temp;
                }
            }
        }
        return b;
    }

    /**
     * 选择排序法
     * @param b
     * @return
     */
    private static int[] Chose(int[] b){
        int temp=0;
        for (int i=0;i<b.length;i++){
            for (int j=i+1;j<b.length;j++){
                if(b[j]<b[i]){
                    temp=b[i];
                    b[i]=b[j];
                    b[j]=temp;
                }
            }
        }
        return b;
    }
}