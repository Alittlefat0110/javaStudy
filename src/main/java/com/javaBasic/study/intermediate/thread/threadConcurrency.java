package com.javaBasic.study.intermediate.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程同步问题
 * 多线程的同步问题指的是多个线程同时修改一个数据的时候，可能导致的问题
 * 多线程的问题，又叫Concurrency 问题
 */
public class threadConcurrency {
    public static void main(String[] args) {

        final HeroTC gareen = new HeroTC();
        gareen.name = "盖伦";
        gareen.hp = 10000;

        System.out.printf("盖伦的初始血量是 %.0f%n", gareen.hp);

        //多线程同步问题指的是多个线程同时修改一个数据的时候，导致的问题

        //假设盖伦有10000滴血，并且在基地里，同时又被对方多个英雄攻击

        //用JAVA代码来表示，就是有多个线程在减少盖伦的hp
        //同时又有多个线程在恢复盖伦的hp

        //n个线程增加盖伦的hp

        int n = 10000;

        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];

        //创建n个线程，每个线程执行一次recover方法
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                    gareen.recover();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;

        }

        //n个线程减少盖伦的hp
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                    gareen.hurt();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }

        //等待所有增加线程结束（将所有线程加入主线程中，保证在主线程结束时，所有子线程都执行）
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //等待所有减少线程结束
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //代码执行到这里，所有增加和减少线程都结束了

        //增加和减少线程的数量是一样的，每次都增加，减少1.
        //那么所有线程都结束后，盖伦的hp应该还是初始值

        //但是事实上观察到的是：

        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量变成了 %.0f%n", n,n,gareen.hp);

    }
    /**
     * 分析同步问题产生的原因
     * 1. 假设增加线程先进入，得到的hp是10000
     * 2. 进行增加运算
     * 3. 正在做增加运算的时候，还没有来得及修改hp的值，减少线程来了
     * 4. 减少线程得到的hp的值也是10000
     * 5. 减少线程进行减少运算
     * 6. 增加线程运算结束，得到值10001，并把这个值赋予hp
     * 7. 减少线程也运算结束，得到值9999，并把这个值赋予hp
     * hp，最后的值就是9999
     * 虽然经历了两个线程各自增减了一次，本来期望还是原值10000，但是却得到了一个9999
     * 这个时候的值9999是一个错误的值，在业务上又叫做脏数据
     */

    /**
     * 解决思路
     * 总体解决思路是： 在增加线程访问hp期间，其他线程不可以访问hp
     * 1. 增加线程获取到hp的值，并进行运算
     * 2. 在运算期间，减少线程试图来获取hp的值，但是不被允许
     * 3. 增加线程运算结束，并成功修改hp的值为10001
     * 4. 减少线程，在增加线程做完后，才能访问hp的值，即10001
     * 5. 减少线程运算，并得到新的值10000
     */
}

/**
 * synchronized 同步对象概念
 */
class synchronizedX{
    /**
     * synchronized 同步对象概念
     * 解决上述问题之前，先理解
     * synchronized关键字的意义
     * 如下代码：
     *
     * Object someObject =new Object();
     * synchronized (someObject){
     *   //此处的代码只有占有了someObject后才可以执行
     * }
     *
     * synchronized表示当前线程，独占 对象 someObject
     * 当前线程独占 了对象someObject，如果有其他线程试图占有对象someObject，就会等待，直到当前线程释放对someObject的占用。
     * someObject 又叫同步对象，所有的对象，都可以作为同步对象
     * 为了达到同步的效果，必须使用同一个同步对象
     *
     * 释放同步对象的方式： synchronized 块自然结束，或者有异常抛出
     */
    public static String now(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void main(String[] args) {
        final Object someObject = new Object();

        Thread t1 = new Thread(){
            public void run(){
                try {
                    System.out.println( now()+" t1 线程已经运行");
                    System.out.println( now()+this.getName()+ " 试图占有对象：someObject");
                    synchronized (someObject) {

                        System.out.println( now()+this.getName()+ " 占有对象：someObject");
                        Thread.sleep(5000);
                        //synchronized块自然结束，释放对象
                        System.out.println( now()+this.getName()+ " 释放对象：someObject");
                    }
                    System.out.println(now()+" t1 线程结束");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t1.setName(" t1");
        t1.start();
        Thread t2 = new Thread(){

            public void run(){
                try {
                    System.out.println( now()+" t2 线程已经运行");
                    System.out.println( now()+this.getName()+ " 试图占有对象：someObject");
                    synchronized (someObject) {
                        System.out.println( now()+this.getName()+ " 占有对象：someObject");
                        Thread.sleep(5000);
                        System.out.println( now()+this.getName()+ " 释放对象：someObject");
                    }
                    System.out.println(now()+" t2 线程结束");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t2.setName(" t2");
        t2.start();
    }
}

/**
 * 使用synchronized 解决同步问题
 * 所有需要修改hp的地方，有要建立在占有someObject的基础上。
 * 而对象 someObject在同一时间，只能被一个线程占有。 间接地，导致同一时间，hp只能被一个线程修改。
 */
class synchronizedA{
    public static void main(String[] args) {

        final Object someObject = new Object();

        final HeroTC gareen = new HeroTC();
        gareen.name = "盖伦";
        gareen.hp = 10000;

        int n = 10000;

        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){

                    //任何线程要修改hp的值，必须先占用someObject
                    synchronized (someObject) {
                        gareen.recover();
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;

        }

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                    //任何线程要修改hp的值，必须先占用someObject
                    synchronized (someObject) {
                        gareen.hurt();
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }

        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量是 %.0f%n", n,n,gareen.hp);

    }
}

/**
 * 使用hero对象作为同步对象
 * 既然任意对象都可以用来作为同步对象，而所有的线程访问的都是同一个hero对象，索性就使用gareen来作为同步对象
 * 进一步的，对于Hero的hurt方法，加上：
 * synchronized (this) {
 * }
 * 表示当前对象为同步对象，即也是gareen为同步对象
 */
class synchronizedB{
    public static void main(String[] args) {

        final HeroTB gareen = new HeroTB();
        gareen.name = "盖伦";
        gareen.hp = 10000;

        int n = 10000;

        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){

                    //使用gareen作为synchronized
                    synchronized (gareen) {
                        gareen.recover();
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;

        }

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                    //使用gareen作为synchronized
                    //在方法hurt中有synchronized(this)
                    gareen.hurt();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }

        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量是 %.0f%n", n,n,gareen.hp);

    }
}


class HeroTC{
        public String name;
        public float hp;

        public int damage;

        //回血
        public void recover(){
            hp=hp+1;
        }

        //掉血
        public void hurt(){
            hp=hp-1;
        }

        public void attackHero(HeroTC h) {
            h.hp-=damage;
            System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
            if(h.isDead())
                System.out.println(h.name +"死了！");
        }

        public boolean isDead() {
            return 0>=hp?true:false;
        }
}

/**
 * 使用this作为同步对象
 */
class HeroTB{
    public String name;
    public float hp;

    public int damage;

    //回血
    public void recover(){
        hp=hp+1;
    }

    //掉血
    public void hurt(){
        //使用this作为同步对象
        synchronized (this) {
            hp=hp-1;
        }
    }

    public void attackHero(HeroTB h) {
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        if(h.isDead())
            System.out.println(h.name +"死了！");
    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }
}

/**
 * 在方法前加synchronized关键字，创建线程时方法自带synchronized
 */
class HeroHTD{
    public String name;
    public float hp;

    public int damage;

    //回血
    //直接在方法前加上修饰符synchronized
    //其所对应的同步对象，就是this
    //和hurt方法达到的效果一样
    public synchronized void recover(){
        hp=hp+1;
    }

    //掉血
    public void hurt(){
        //使用this作为同步对象
        synchronized (this) {
            hp=hp-1;
        }
    }

    public void attackHero(HeroHTD h) {
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        if(h.isDead())
            System.out.println(h.name +"死了！");
    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }
    /**
     * 线程安全的类
     * 如果一个类，其方法都是有synchronized修饰的，那么该类就叫做线程安全的类
     *
     * 同一时间，只有一个线程能够进入 这种类的一个实例 的去修改数据，
     * 进而保证了这个实例中的数据的安全(不会同时被多线程修改而变成脏数据)
     *
     * 比如StringBuffer和StringBuilder的区别
     * StringBuffer的方法都是有synchronized修饰的，StringBuffer就叫做线程安全的类
     * 而StringBuilder就不是线程安全的类
     */

}
// TODO
/**
 * 练习
 * 练习-在类方法前面加修饰符synchronized
 * 在对象方法前，加上修饰符synchronized ，同步对象是当前实例。
 * 那么如果在类方法前，加上修饰符 synchronized，同步对象是什么呢？
 *
 * 提示：要完成本练习，需要反射reflection的知识，如果未学习反射，可以暂时不做
 */
class practiceC{

    /**
     * //见collection集合linkedList篇
     * 练习-线程安全的MyStack
     * 把答案-使用LinkedList实现Stack栈 中的MyStack类，改造为线程安全的类。
     */
    private static void t(){

    }

}

