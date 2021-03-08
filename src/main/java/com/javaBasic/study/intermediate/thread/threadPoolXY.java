package com.javaBasic.study.intermediate.thread;


import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class threadPoolXY {
    /**
     * 线程池设计思路
     * 线程池的思路和生产者消费者模型是很接近的。
     * 1. 准备一个任务容器
     * 2. 一次性启动10个 消费者线程
     * 3. 刚开始任务容器是空的，所以线程都wait在上面。
     * 4. 直到一个外部线程往这个任务容器中扔了一个“任务”，就会有一个消费者线程被唤醒notify
     * 5. 这个消费者线程取出“任务”，并且执行这个任务，执行完毕后，继续等待下一次任务的到来。
     * 6. 如果短时间内，有较多的任务加入，那么就会有多个线程被唤醒，去执行这些任务。
     * <p>
     * 在整个过程中，都不需要创建新的线程，而是循环使用这些已经存在的线程
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务" + finalI);
                    //任务可能是打印一句话
                    //可能是访问文件
                    //可能是做排序
                }
            };

            pool.add(task);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

}

/**
 * 测试线程池
 * 创造一个情景，每个任务执行的时间都是1秒
 * 刚开始是间隔1秒钟向线程池中添加任务
 * <p>
 * 然后间隔时间越来越短，执行任务的线程还没有来得及结束，新的任务又来了。
 * 就会观察到线程池里的其他线程被唤醒来执行这些任务
 */
class TestThread {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        int sleep = 1000;
        while (true) {
            pool.add(new Runnable() {
                @Override
                public void run() {
                    //System.out.println("执行任务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(sleep);
                sleep = sleep > 100 ? sleep - 100 : sleep;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}

/**
 * 开发一个自定义线程池
 * 这是一个自定义的线程池，虽然不够完善和健壮，但是已经足以说明线程池的工作原理
 * <p>
 * 缓慢的给这个线程池添加任务，会看到有多条线程来执行这些任务。
 * 线程7执行完毕任务后，又回到池子里，下一次任务来的时候，线程7又来执行新的任务。
 */
class ThreadPool {

    // 线程池大小
    int threadPoolSize;

    // 任务容器
    LinkedList<Runnable> tasks = new LinkedList<>();

    // 试图消费任务的线程

    public ThreadPool() {
        threadPoolSize = 10;

        // 启动10个任务消费者线程
        synchronized (tasks) {
            for (int i = 0; i < threadPoolSize; i++) {
                new TaskConsumeThread("任务消费者线程 " + i).start();
            }
        }
    }

    public void add(Runnable r) {
        synchronized (tasks) {
            tasks.add(r);
            // 唤醒等待的任务消费者线程
            tasks.notifyAll();
        }
    }

    class TaskConsumeThread extends Thread {
        public TaskConsumeThread(String name) {
            super(name);
        }

        Runnable task;

        public void run() {
            System.out.println("启动： " + this.getName());
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeLast();
                    // 允许添加任务的线程可以继续添加任务
                    tasks.notifyAll();

                }
                System.out.println(this.getName() + " 获取到任务，并执行");
                task.run();
            }
        }
    }
}


/**
 * 使用java自带线程池
 */
class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 第一个参数10 表示这个线程池初始化了10个线程在里面工作
         * 第二个参数15 表示如果10个线程不够用了，就会自动增加到最多15个线程
         * 第三个参数60 结合第四个参数TimeUnit.SECONDS，表示经过60秒，
         *            多出来的线程还没有接到活儿，就会回收，最后保持池子里就10个
         * 第四个参数TimeUnit.SECONDS 如上
         * 第五个参数 new LinkedBlockingQueue() 用来放任务的集合
         *
         * execute方法用于添加新的任务
         */
//        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务1");
            }
        });
        threadPool.execute(() -> System.out.println("任务2"));
//        threadPoolTaskExecutor.initialize();
//        threadPoolTaskExecutor.execute(new Runnable(){
//            @Override
//            public void run(){
//                System.out.println("任务1");
//            }
//        });

    }

}

/**
 * 练习
 * 在 练习-同步查找文件内容 ，如果文件特别多，就会创建很多的线程。 改写这个练习，使用线程池的方式来完成。
 * 初始化一个大小是10的线程池
 * 遍历所有文件，当遍历到文件是.java的时候，创建一个查找文件的任务，把这个任务扔进线程池去执行，继续遍历下一个文件
 */
class practiceG {
    public void search(File files, String search) throws InterruptedException, IOException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        File[] f = files.listFiles();
        for (File file : f) {
            if (file.isFile()) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            BufferedReader in = null;
                            try {
                                in = new BufferedReader(new FileReader(file));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            String line;
                            try {
                                while ((line = in.readLine()) != null) {
                                    if (line.contains(search)) {
                                        System.out.println(Thread.currentThread().getName() + "查找到文件：" + file.getName()
                                                + " 路径: " + file.getAbsolutePath());
                                        break;
                                    }

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (file.isDirectory()) {
                search(file, search);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        practiceG t = new practiceG();
        File f = new File("C:\\Users\\Sharpg\\IdeaProjects");
        t.search(f, "java");
    }

}