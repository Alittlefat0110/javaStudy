package com.javaBasic.study.intermediate.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁
 * 当业务比较复杂，多线程应用里有可能会发生死锁
 */
public class Lock {
    /**
     * 演示死锁
     * 1. 线程1 首先占有对象1，接着试图占有对象2
     * 2. 线程2 首先占有对象2，接着试图占有对象1
     * 3. 线程1 等待线程2释放对象2
     * 4. 与此同时，线程2等待线程1释放对象1
     * 就会。。。一直等待下去，直到天荒地老，海枯石烂，山无棱 ，天地合。。。
     * @param args
     */
    public static void main(String[] args) {
        //对象1
        final HeroTY ahri = new HeroTY();
        ahri.name = "九尾妖狐";
        //对象2
        final HeroTY annie = new HeroTY();
        annie.name = "安妮";

        Thread t1 = new Thread(){
            public void run(){
                //占有九尾妖狐
                synchronized (ahri) {
                    System.out.println("t1 已占有九尾妖狐");
                    try {
                        //停顿1000毫秒，另一个线程有足够的时间占有安妮
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("t1 试图占有安妮");
                    System.out.println("t1 等待中 。。。。");
                    synchronized (annie) {
                        System.out.println("do something1");
                    }
                }

            }
        };
        t1.start();
        Thread t2 = new Thread(){
            public void run(){
                //占有安妮
                synchronized (annie) {
                    System.out.println("t2 已占有安妮");
                    try {

                        //停顿1000毫秒，另一个线程有足够的时间占有暂用九尾妖狐
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 试图占有九尾妖狐");
                    System.out.println("t2 等待中 。。。。");
                    synchronized (ahri) {
                        System.out.println("do something2");
                    }
                }

            }
        };
        t2.start();
    }


}


/**
 * 练习
 * 3个同步对象a, b, c
 * 3个线程 t1,t2,t3
 *
 * 故意设计场景，使这3个线程彼此死锁
 */
@Slf4j
class practiceO{
    public static void main(String[] args){
        //对象1
        final HeroTY h1 = new HeroTY();
        h1.name = "九尾妖狐";
        //对象2
        final HeroTY h2 = new HeroTY();
        h2.name = "安妮";
        //对象3
        final HeroTY h3 = new HeroTY();
        h3.name = "七七";

        Thread t1=new Thread(){
            public void run(){
                synchronized(h1){
                    log.info("t1 已占有 h1");
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    log.info("t1 试图占有 h2");
                    log.info("t1 进入等待......");
                    synchronized (h2){
                        log.info("t1 占有 h2");
                    }
                }
            }
        };
        t1.start();

        Thread t2=new Thread(){
            public void run(){
                synchronized (h2){
                    log.info("t2 占有 h2");
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    log.info("t2 试图占有 h3");
                    log.info("t2 进入等待......");
                    synchronized (h3){
                        log.info("t2 占有 h3");
                    }
                }
            }
        };
        t2.start();

        Thread t3=new Thread(){
            public void run(){
                synchronized (h3){
                    log.info("t3 占有 h3");
                    synchronized (h3){
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        log.info("t3 试图占有 h1");
                        log.info("t3 进入等待......");
                        synchronized (h1){
                            log.info("t3 占有 h1");
                        }
                    }
                }
            }
        };
        t3.start();
    }


}
class HeroTY{
    public String name;
    public float hp;
    public int damage;

}
