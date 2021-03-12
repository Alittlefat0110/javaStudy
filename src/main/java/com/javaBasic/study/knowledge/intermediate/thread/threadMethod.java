package com.javaBasic.study.knowledge.intermediate.thread;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 常用方法
 *关键字	           简介
 * sleep	       当前线程暂停
 * join	           加入到当前线程中
 * setPriority	   线程优先级
 * yield	       临时暂停
 * setDaemon	   守护线程
 */
public class threadMethod {
    public static void main(String[] args){
        /**
         * 当前线程暂停顶折纠问
         * Thread.sleep(1000); 表示当前线程暂停1000毫秒 ，其他线程不受影响
         * Thread.sleep(1000); 会抛出InterruptedException 中断异常，因为当前线程sleep的时候，
         * 有可能被停止，这时就会抛出 InterruptedException
         */
        Thread t1= new Thread(){
            public void run(){
                int seconds =0;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);
                }
            }
        };
//        t1.start();


        /**
         * 加入到当前线程中
         * 首先解释一下主线程的概念
         * 所有进程，至少会有一个线程即主线程，即main方法开始执行，就会有一个看不见的主线程存在。
         * 在42行执行t.join，即表明在主线程中加入该线程。
         * 主线程会等待该线程结束完毕， 才会往下运行。
         */
        final HeroTX gareen = new HeroTX();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        final HeroTX teemo = new HeroTX();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        final HeroTX bh = new HeroTX();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        final HeroTX leesin = new HeroTX();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        Thread t2= new Thread(){
            public void run(){
                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };

        t2.start();

        //代码执行到这里，一直是main线程在运行
        try {
            //t12程加入到main线程中来，只有t2线程运行结束，才会继续往下走
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Thread t3= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    bh.attackHero(leesin);
                }
            }
        };
        //会观察到盖伦把提莫杀掉后，才运行t2线程
        t3.start();



    }
}

/**
 * 线程优先级  setPriority
 * 运行时把把暂停时间去掉，多条线程各自会尽力去占有CPU资源
 */
class setPriorityX{
    public static void main(String[] args){
        final HeroTX gareen = new HeroTX();
        gareen.name = "盖伦";
        gareen.hp = 6160;
        gareen.damage = 1;

        final HeroTX teemo = new HeroTX();
        teemo.name = "提莫";
        teemo.hp = 3000;
        teemo.damage = 1;

        final HeroTX bh = new HeroTX();
        bh.name = "赏金猎人";
        bh.hp = 5000;
        bh.damage = 1;

        final HeroTX leesin = new HeroTX();
        leesin.name = "盲僧";
        leesin.hp = 4505;
        leesin.damage = 1;

        Thread t1= new Thread(){
            public void run(){

                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };

        Thread t2= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    bh.attackHero(leesin);
                }
            }
        };

        /**
         * 线程优先级
         * 当线程处于竞争关系的时候，优先级高的线程会有更大的几率获得CPU资源
         * 为了演示该效果，要把暂停时间去掉，多条线程各自会尽力去占有CPU资源
         * 同时把英雄的血量增加100倍，攻击减低到1，才有足够的时间观察到优先级的演示
         * 如图可见，线程1的优先级是MAX_PRIORITY，所以它争取到了更多的CPU资源执行代码
         */
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();

    }
}

/**
 * 临时暂停  yield
 * 当前线程，临时暂停，使得其他线程可以有更多的机会占用CPU资源
 */
class yieldX{
    public static void main(String[] args){
        final HeroTX gareen = new HeroTX();
        gareen.name = "盖伦";
        gareen.hp = 61600;
        gareen.damage = 1;

        final HeroTX teemo = new HeroTX();
        teemo.name = "提莫";
        teemo.hp = 30000;
        teemo.damage = 1;

        final HeroTX bh = new HeroTX();
        bh.name = "赏金猎人";
        bh.hp = 50000;
        bh.damage = 1;

        final HeroTX leesin = new HeroTX();
        leesin.name = "盲僧";
        leesin.hp = 45050;
        leesin.damage = 1;

        Thread t1= new Thread(){
            public void run(){

                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };

        Thread t2= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    //临时暂停，使得t1可以占用CPU资源
                    Thread.yield();

                    bh.attackHero(leesin);
                }
            }
        };

        //t1,t2线程优先级一样，但是t2设置了临时暂停，使得t1可以占用CPU资源，所以t1实际上先运行完t2才开始运行
        t1.setPriority(5);
        t2.setPriority(5);
        t1.start();
        t2.start();

    }
}

/**
 * 守护线程 setDaemon
 */
class setDaemonX{
    public static void main(String[] args) {

        /**
         * 守护线程的概念是： 当一个进程里，所有的线程都是守护线程的时候，结束当前进程。
         *
         * 就好像一个公司有销售部，生产部这些和业务挂钩的部门。
         * 除此之外，还有后勤，行政等这些支持部门。
         *
         * 如果一家公司销售部，生产部都解散了，那么只剩下后勤和行政，那么这家公司也可以解散了。
         *
         * 守护线程就相当于那些支持部门，如果一个进程只剩下守护线程，那么进程就会自动结束。
         *
         * 守护线程通常会被用来做日志，性能统计等工作。
         */
        Thread t1= new Thread(){
            public void run(){
                int seconds =0;

                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);

                }
            }
        };
        //将t1设为守护线程
        t1.setDaemon(true);
        t1.start();
    }
}

/**
 * 练习
 */
class threadPractice{
    public static void main(String[] args) throws InterruptedException {
       // p1();
        p2();
        //主线程运行时间要大于子线程
        Thread.sleep(100000000000000L);
    }

    /**
     * 练习-英雄充能
     * 英雄有可以放一个技能叫做: 波动拳-a du gen。
     * 每隔一秒钟，可以发一次，但是只能连续发3次。
     * 发完3次之后，需要充能5秒钟，充满，再继续发。
     */
    private static void p1(){
        Thread t1=new Thread(){
            public void run(){
                while (true) {
                    for (int i = 1; i <= 3; i++) {
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        System.out.format("波动拳第 %d 发%n", i);
                    }
                    try {
                        System.out.println("开始充能5秒");
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();
    }

    /**
     * 练习-破解密码
     * 1. 生成一个长度是3的随机字符串，把这个字符串当作 密码
     *
     * 2. 创建一个破解线程，使用穷举法，匹配这个密码
     *
     * 3. 创建一个日志线程，打印都用过哪些字符串去匹配，这个日志线程设计为守护线程
     *
     * 提示： 破解线程把穷举法生成的可能密码放在一个容器中，日志线程不断的从这个容器中拿出可能密码，并打印出来。
     * 如果发现容器是空的，就休息1秒，如果发现不是空的，就不停的取出，并打印。
     */
    private static void p2(){
        //随机生成长度为3的字符串，当作密码
        String str= RandomStringUtils.randomAlphabetic(3);
        Queue<String> q= new LinkedList<>();
        List<String> list=new ArrayList<>();
        Thread t2=new Thread(){
            String str1= "";
            public void run(){
                while (!str1.equals(str)){
                    System.out.println("未匹配！");
                    str1= RandomStringUtils.randomAlphabetic(3);
                    q.offer(str1);
                    if (str1.equals(str)){
                        System.out.println("匹配成功，密码为："+str1);
                    }
                }
            }
        };

        Thread t3=new Thread(){
          public void run() {
              while (true) {
                  if (q.size() == 0) {
                      try {
                          Thread.sleep(10000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  } else {
                      String s=q.poll();
                      System.out.println(s);
                  }
              }
          }
        };
        t2.start();
        t3.setDaemon(true);
        t3.start();
    }
}



class HeroTX{
    public String name;
    public float hp;
    public int damage;

    public void attackHero(HeroTX h) {
        try {
            //为了表示攻击需要时间，每次攻击暂停1000毫秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        h.hp-=damage;
        //name表示当前对象（调用方法的对象）的名字属性，h.name表示传入对象的姓名属性
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);

        if(h.isDead())
            System.out.println(h.name +"死了！");
    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }

}

