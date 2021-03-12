package com.javaBasic.study.knowledge.primary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期
 */
public class HDate {
    /**
     * Date类
     * 注意：是java.util.Date;
     * 而非 java.sql.Date，此类是给数据库访问的时候使用的
     */
    /**
     * 时间原点1970年1月1日 8点0分0秒
     * 所有的日期，都是以为这个0点为基准，每过一毫秒，就+1。
     */
    public static void main(String[] args){
        /**
         * 创建日期对象
         */
        // 当前时间
        Date d1 = new Date();
        System.out.println("当前时间:");
        System.out.println(d1);
        System.out.println();
        // 从1970年1月1日 早上8点0分0秒 开始经历的毫秒数
        Date d2 = new Date(5000);
        System.out.println("从1970年1月1日 早上8点0分0秒 开始经历了5秒的时间");
        System.out.println(d2);

        /**
         * getTime
         * getTime() 得到一个long型的整数
         * 这个整数代表 从1970.1.1 08:00:00:000 开始 每经历一毫秒，增加1
         */
        System.out.println("getTime()返回的值是："+d2.getTime());
        /**
         * System.currentTimeMillis()
         * 当前日期的毫秒数
         * new Date().getTime() 和 System.currentTimeMillis() 是一样的
         */

    }
}

/**
 * SimpleDateFormat 日期格式化类
 */
class simpleDateFormat{
    /**
     * format	日期转字符串
     * parse	字符串转日期
     * @param args
     */
    public static void main(String[] args){
        /**
         * 日期转字符串:
         *
         * y 代表年
         * M 代表月
         * d 代表日
         * H 代表24进制的小时
         * h 代表12进制的小时
         * m 代表分钟
         * s 代表秒
         * S 代表毫秒
         */
        //指定日期格式
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
        //当前时间
        Date d= new Date();
        //日期转字符串
        String str = sdf.format(d);
        System.out.println("当前时间通过 yyyy-MM-dd HH:mm:ss SSS 格式化后的输出: "+str);

        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
        Date d1= new Date();
        String str1 = sdf1.format(d1);
        System.out.println("当前时间通过 yyyy-MM-dd 格式化后的输出: "+str1);

        /**
         * 字符串转日期:
         * 模式（yyyy/MM/dd HH:mm:ss）需要和字符串格式保持一致，如果不一样就会抛出解析异常ParseException
         */
        SimpleDateFormat sdf2 =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );

        String str2 = "2016/1/5 12:12:12";

        try {
            Date d3 = sdf2.parse(str2);
            System.out.printf("字符串 %s 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: %s",str2,d3.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 练习
     * 准备一个长度是9的日期数组
     * 使用1970年-2000年之间的随机日期初始化该数组
     * 按照这些日期的时间进行升序排序
     * 比如 1988-1-21 12:33:22 就会排在 1978-4-21 19:07:23 前面，因为它的时间更小，虽然日期更大
     */
    public static void practiceDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        long ls = System.currentTimeMillis();
        Date[] de = new Date[9];
        for(int i=0;i<9;i++){
            if(i%3==0)
                System.out.println(" ");
            de[i] = MygetTime(2000);
            System.out.print(sdf.format(de[i])+" ");
        }
        System.out.println(" ");

        for(int m=0;m<9;m++)
            for(int n=0;n<8-m;n++)
                if(Long.parseLong(sdf2.format(de[n])) > Long.parseLong(sdf2.format(de[n+1]))){
                    Date f1 = de[n];
                    Date f2 = de[n+1];
                    de[n] = f2;
                    de[n+1] = f1;
                }

        for(int i=0;i<9;i++){
            if(i%3==0)
                System.out.println(" ");
            System.out.print(sdf.format(de[i])+" ");
        }
        System.out.println("\n"+(System.currentTimeMillis()-ls));//5
    }

    public static Date MygetTime(int t,int t2) {
        long ri = 1000*60*60*24;
        double dae = Math.random()*365*ri*(t2-t);
        dae += (t-1970)*365*ri;
        return new Date((long)dae);
    }

    public static Date MygetTime(int t) {
        return MygetTime(1970,t);
    }
}

/**
 * Calendar类即日历类，常用于进行“翻日历”，比如下个月的今天是多久
 */
class calendar{
    /**
     * Calendar与Date进行转换顶折纠问
     * 采用单例模式获取日历对象Calendar.getInstance();
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
//        //采用单例模式获取日历对象Calendar.getInstance();
//        Calendar c = Calendar.getInstance();
//        //通过日历对象得到日期对象
//        Date d = c.getTime();
//        Date d2 = new Date(0);
//        c.setTime(d2); //把这个日历，调成日期 : 1970.1.1 08:00:00
//
//        /**
//         * 翻日历:
//         * add方法，在原日期上增加年/月/日
//         * set方法，直接设置年/月/日
//         */
//            Calendar c1 = Calendar.getInstance();
//            Date now = c1.getTime();
//            // 当前日期
//            System.out.println("当前日期：\t" + format(c1.getTime()));
//
//            // 下个月的今天
//            c1.setTime(now);
//            c1.add(Calendar.MONTH, 1);
//            System.out.println("下个月的今天:\t" +format(c1.getTime()));
//
//            // 去年的今天
//            c1.setTime(now);
//            c1.add(Calendar.YEAR, -1);
//            System.out.println("去年的今天:\t" +format(c1.getTime()));
//
//            // 上个月的第三天
//            c1.setTime(now);
//            c1.add(Calendar.MONTH, -1);
//            c1.set(Calendar.DATE, 3);
//            System.out.println("上个月的第三天:\t" +format(c1.getTime()));

        practice();

    }

    /**
     * 按预设格式返回日期
     * @param time
     * @return
     */
    private static String format(Date time) {
        return sdf.format(time);
    }

    /**
     * 练习
     * 找出下个月的倒数第3天是哪天
     * @return
     */
    private static void practice() {
       Calendar a=Calendar.getInstance();
        Date now=new Date();
        a.setTime(now);
        //日期+一个月，即下个月的今天
        a.add(Calendar.MONTH,1);
        System.out.println(format(a.getTime()));
        //下个月的倒数第三天
        a.set(Calendar.DATE,-3);
        System.out.println( format(a.getTime()));
        //下个月的第一天
        a.set(Calendar.DATE,1);
        System.out.println( format(a.getTime()));
        //日期+1
        a.add(Calendar.DAY_OF_MONTH,1);
        System.out.println( format(a.getTime()));
    }

}
