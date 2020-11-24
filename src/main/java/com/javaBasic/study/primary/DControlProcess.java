package com.javaBasic.study.primary;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.util.*;

/**
 * 控制流程
 */
public class DControlProcess {
    /**
     * if 条件判断
     */
    public static void main(String[] args){
        /**
         * if
         * if(表达式1){
         *   表达式2；
         * }
         * 如果表达式1的值是true,
         * 就执行表达式2
         */
        boolean a = true;
        //如果成立就打印yes
        if(a){
            System.out.println("yes");
        }
        /**
         * if else
         * else代表if不成立的情况
         */
        boolean b = false;
        //最好都用{}号括一下执行体
        if (b)
            System.out.println("yes");
        else
            System.out.println("no");
        /**
         * else if
         * else if 是多条件判断
         */
        //如果只使用 if,会执行4次判断
        int i = 2;
        if (i==1)
            System.out.println(1);
        if (i==2)
            System.out.println(2);
        if (i==3)
            System.out.println(3);
        if (i==4)
            System.out.println(4);
        //如果使用else if, 一旦在18行，判断成立， 20行和22行的判断就不会执行了，节约了运算资源
        if (i==1)
            System.out.println(1);
        else if (i==2)
            System.out.println(2);
        else if (i==3)
            System.out.println(3);
        else if (i==4)
            System.out.println(4);
    }
}

/**
 * switch
 */
class ControlProcess1{
    /**
     * switch 语句相当于 if else的另一种表达方式
     * switch可以使用byte,short,int,char,String,enum  (case后的值）
     * 注: 每个表达式结束，都应该有一个break;
     * 注: String在Java1.7之前是不支持的, Java从1.7开始支持switch用String的，编译后是把String转化为hash值，其实还是整数
     * 注: enum是枚举类型
     */
    public static void main(String[] args){
        //如果使用if else
        int day = 5;
        if (day==1)
            System.out.println("星期一");
        else if (day==2)
            System.out.println("星期二");
        else if (day==3)
            System.out.println("星期三");
        else if (day==4)
            System.out.println("星期四");
        else if (day==5)
            System.out.println("星期五");
        else if (day==6)
            System.out.println("星期六");
        else if (day==7)
            System.out.println("星期天");
        else
            System.out.println("这个是什么鬼？");

        //如果使用switch
        switch(day){
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期天");
                break;
                //输入了不合法数据
            default:
                System.out.println("这个是什么鬼？");
        }
        /**
         * 练习
         * 输入月份判断是什么季节
         */
        Scanner in=new Scanner(System.in);
        System.out.println("请输入月份：");
        int month=in.nextInt();
        getSeason1(month);
        getSeason2(month);
    }
    static void getSeason1(int season) {
        int temp;
        if(0<season&&season<=12) {
            season-=1;
            temp=season/3;
            switch(temp) {
                case 0:
                    if(season==0)
                        System.out.println("冬天");
                    else
                        System.out.println("春天");
                    break;
                case 1:
                    System.out.println("夏天");
                    break;
                case 2:
                    System.out.println("秋天");
                    break;
                case 3:
                    System.out.println("冬天");
                    break;
            }
        }
        else
            System.out.println("输入格式错误！");
    }
    static void getSeason2(int month) {
        switch(month){
            case 3:
            case 4:
            case 5:
                System.out.println("春天");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("夏天");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("秋天");
                break;
            case 12:
            case 1:
            case 2:
                System.out.println("冬天");
                break;
            default:
                System.out.println("输入错误!!");
        }
    }
}


/**
 * while
 */
class ControlProcess2{
    /**
     * while	条件为true时 重复执行
     * do while	条件为true时 重复执行，至少会执行一次
     */
    public static void main(String[] args){
        /**
         * while
         * 只要while中的表达式成立，就会不断地循环执行
         */
        //打印0到4
        int i = 0;
        while(i<5){
            System.out.println(i);
            i++;
        }

        /**
         * 条件为true时 重复执行，至少会执行一次顶折纠问
         * do{
         * } while 循环
         * 与while的区别是，无论是否成立，先执行一次，再进行判断
         */
        //打印0到4
        //与while的区别是，无论是否成立，先执行一次，再进行判断
        int j = 0;
        do{
            System.out.println(j);
            j++;
        } while(j<5);

        /**
         * 练习
         * 通过Scanner 获取一个整数，然后使用while计算这个整数的阶乘
         */
        Scanner scanner=new Scanner(System.in);
        int k=scanner.nextInt();
        int factorial=k;
        while (k>1){
            factorial*=(k-1);
            k--;
        }
        System.out.println(factorial);
    }
}

/**
 * for
 */
class ControlProcess3{
    /**
     * for循环，和while一样，只是表达方式不一样
     */
    public static void main(String[] args){
        //使用while打印0到4
        int i = 0;
        while(i<5){
            System.out.println("while循环输出的"+i);
            i++;
        }

        //使用for打印0到4
        for (int j = 0; j < 5; j++) {
            System.out.println("for  循环输出的"+j);
        }
    }
}

/**
 * continue
 */
class ControlProcess4{
    /**
     *  继续下一次循环
     */
    public static void main(String[] args){
        //打印单数
        for (int j = 0; j < 10; j++) {
            //如果是双数，后面的代码不执行，直接进行下一次循环（即不输出j，直接j++）
            if(0==j%2)
                continue;
            System.out.println(j);
        }
    }
}
/**
 * break
 */
class ControlProcess5{
    /**
     * 结束循环
     */
    public static void main(String[] args){
        //打印单数
        for (int j = 0; j < 10; j++) {
            //如果是双数，直接结束for循环
            if(0==j%2)
                break;
            System.out.println(j);
        }
    }
}

/**
 * 结束外部循环
 */
class ControlProcess6{
    public static void main(String[] args){
        /**
         * break只结束当前循环
         */
        //打印单数
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i+":"+j);
                if(0==j%2)
                    break; //如果是双数，结束当前循环
            }
        }
        /**
         * 借助boolean变量结束外部循环
         * 需要在内部循环中修改这个变量值
         * 每次内部循环结束后，都要在外部循环中判断，这个变量的值
         */
        boolean breakout = false; //是否终止外部循环的标记
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i + ":" + j);
                if (0 == j % 2) {
                    breakout = true; //终止外部循环的标记设置为true
                    break;
                }
            }
            if (breakout) //判断是否终止外部循环
                break;
        }

        /**
         * 使用标签结束外部循环顶折纠问
         * 在外部循环的前一行，加上标签
         * 在break的时候使用该标签
         * 即能达到结束外部循环的效果
         */
        //打印单数
        outLoop: //outLoop这个标示是可以自定义的比如outLoop1,ol2,out5
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i+":"+j);
                if(0==j%2)
                    break outLoop; //如果是双数，结束外部循环
            }
        }
    }
}
@Slf4j
class Practice {
    public static void main(String[] args) {
        //practice1();
        //List<Integer> list=practice2();
        //log.info(String.valueOf(list));
        practice3();

    }

    /**
     * 寻找某两个数相除，其结果 离黄金分割点 0.618最近
     * <p>
     * 分母和分子不能同时为偶数
     * 分母和分子 取值范围在[1-20]
     * @return
     */
    public static float practice1() {
        /**
         * 思路：
         * 循环获得每一个分子/分母结果，用该结果减目标值，取绝对值最小的即为所求
         */
        //注：数据类型定义为double时，不能得出正确结果（为什么？）
        float h = 0.618f;
        float min=100;
        int numerator=0;
        int denominator=0;
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if (i%2==0&&j%2==0){
                    continue;
                }
                float x = (float)i / j;
                //取绝对值
                float temp=Math.abs(x-h);
                if (min>temp){
                    min=temp;
                    numerator=i;
                    denominator=j;
                }
            }
        }
        System.out.println(min);
        System.out.println((float)numerator/denominator);
        return (float)numerator/denominator;
    }

    /**
     * 寻找所有的水仙花数
     * 水仙花数定义：
     * 1. 一定是3位数
     * 2. 每一位的立方，加起来恰好是这个数本身，比如153=1*1*1+5*5*5+3*3*3
     * @return
     */
    public static List<Integer> practice2(){
        int mcl=0;
        List<Integer> list=new LinkedList<>();
        for (int j=100;j<1000;j++) {
            //将整数转为字符串
            String s=String.valueOf(j);
            //将字符串转为char数组
            char[] s1=s.toCharArray();

            for (int i = 0; i < s.length(); i++) {
                //int i1 = s1[i]得到的是s1[i]的Ascii码值，此时强转不生效
                //得到j的每一位数
                 int i1 = s1[i]-'0';
                mcl = mcl+(int)Math.pow(i1,3);
            }
            //System.out.println(j+"---"+mcl);
            if (j==mcl){
                list.add(mcl);
            }
            //重置mcl，不然会累加到下一次循环
            mcl=0;
        }
        return list;
    }

    /**
     * 计算题
     * a+b=8，c-d=6，a+c=14，b+d=10
     * 求a,b,c,d的值
     */
    public static void practice3(){
        //方法一 控制变量
        for (int a = 0; a < 8; a++)
        {
            int b = 8-a;
            int c = 14-a;
            int d = 10-b;
            if(c == d+6)
                log.info("a="+a+" "+"b="+b+" "+"c="+c+" "+"d="+d);
//                System.out.format("%d  +  %d = 8\n"
//                    + "+     +\n"
//                    + "%d - %d = 6\n"
//                    + "||   ||\n"
//                    + "14   10", a, b, c, d );
        }
        //方法二，嵌套循环
        Start://与break配合使用
        for (int a=0;a<=8;a++){
           for (int b=0;b<=10;b++){
               for (int c=0;c<=14;c++){
                   for (int d=0;d<=10;d++){
                       if(a+b==8 &&c-d==6&&a+c==14&&b+d==10){
                           System.out.println("a+b=8，c+d=6，a+c=14，b+d=10，求a,b,c,d的值");
                           log.info("a="+a+" "+"b="+b+" "+"c="+c+" "+"d="+d);
                           break Start;
                       }
                   }
               }
           }
        }
    }

}