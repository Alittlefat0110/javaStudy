package com.javaBasic.study.intermediate.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock对象
 * 与synchronized类似的，lock也能够达到同步的效果
 */
public class LockObject {
    /**
     * 首先回忆一下 synchronized 同步对象的方式  （间线程同步问题threadConcurrency）
     * 当一个线程占用 synchronized 同步对象，其他线程就不能占用了，直到释放这个同步对象为止
     */
    /**
     *使用Lock对象实现同步效果
     * Lock是一个接口，为了使用一个Lock对象，需要用到
     *
     * Lock lock = new ReentrantLock();
     *
     * 与 synchronized (someObject) 类似的，lock()方法，表示当前线程占用lock对象，一旦占用，其他线程就不能占用了。
     * 与 synchronized 不同的是，一旦synchronized 块结束，就会自动释放对someObject的占用。
     * lock却必须调用unlock方法进行手动释放，为了保证释放的执行，往往会把unlock() 放在finally中进行。
     */
    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now() , Thread.currentThread().getName() , msg);
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("主动释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t1.setName("t1");
        t1.start();
        try {
            //先让t1飞2秒
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Thread t2 = new Thread() {

            public void run() {
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t2.setName("t2");
        t2.start();
    }

}


/**
 * tryLock方法
 * synchronized 是不占用到手不罢休的，会一直试图占用下去。
 * 与 synchronized 的钻牛角尖不一样，Lock接口还提供了一个trylock方法。
 * trylock会在指定时间范围内试图占用，占成功了，就啪啪啪。 如果时间到了，还占用不成功，扭头就走~
 *
 * 注意： 因为使用trylock有可能成功，有可能失败，所以后面unlock释放锁的时候，需要判断是否占用成功了，
 * 如果没占用成功也unlock,就会抛出异常
 */
class TestThreadR {

    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now() , Thread.currentThread().getName() , msg);
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread() {
            public void run() {
                boolean locked = false;
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    locked = lock.tryLock(1, TimeUnit.SECONDS);
                    if(locked){
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                    }
                    else{
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if(locked){
                        log("释放对象：lock");
                        lock.unlock();
                    }
                }
                log("线程结束");
            }
        };
        t1.setName("t1");
        t1.start();
        try {
            //先让t1飞2秒
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Thread t2 = new Thread() {

            public void run() {
                boolean locked = false;
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    locked = lock.tryLock(1,TimeUnit.SECONDS);
                    if(locked){
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                    }
                    else{
                        log("经过1秒钟的努力，还没有占有对象，放弃占有");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if(locked){
                        log("释放对象：lock");
                        lock.unlock();
                    }
                }
                log("线程结束");
            }
        };
        t2.setName("t2");
        t2.start();
    }

}

/**
 * 线程交互
 * 使用synchronized方式进行线程交互，用到的是同步对象的wait,notify和notifyAll方法
 *
 * Lock也提供了类似的解决办法，首先通过lock对象得到一个Condition对象，
 * 然后分别调用这个Condition对象的：await, signal,signalAll 方法
 *
 * 注意： 不是Condition对象的wait,nofity,notifyAll方法,是await,signal,signalAll
 */
class TestThreadX {

    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now() , Thread.currentThread().getName() , msg);
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);
                    log("临时释放对象 lock， 并等待");
                    condition.await();
                    log("重新占有对象 lock，并进行5秒的业务操作");
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t1.setName("t1");
        t1.start();
        try {
            //先让t1飞2秒
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Thread t2 = new Thread() {

            public void run() {
                try {
                    log("线程启动");
                    log("试图占有对象：lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);
                    log("唤醒等待中的线程");
                    condition.signal();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t2.setName("t2");
        t2.start();
    }

}
/**总结Lock和synchronized的区别
 1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现，Lock是代码层面的实现。

 2. Lock可以选择性的获取锁，如果一段时间获取不到，可以放弃。synchronized不行，会一根筋一直获取下去。
    借助Lock的这个特性，就能够规避死锁，synchronized必须通过谨慎和良好的设计，才能减少死锁的发生。

 3. synchronized在发生异常和同步块结束的时候，会自动释放锁。而Lock必须手动释放， 所以如果忘记了释放锁，一样会造成死锁。
 */