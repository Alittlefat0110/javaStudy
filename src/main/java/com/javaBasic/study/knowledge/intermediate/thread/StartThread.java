package com.javaBasic.study.knowledge.intermediate.thread;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 启动一个线程
 */
public class StartThread {
    /**
     *多线程即在同一时间，可以做多件事情。
     *
     * 创建多线程有3种方式，分别是继承线程类,实现Runnable接口,匿名类
     */
    public static void main(String[] args){
        /**
         * 首先要理解进程(Processor)和线程(Thread)的区别
         * 进程：启动一个LOL.exe就叫一个进程。 接着又启动一个DOTA.exe，这叫两个进程。
         * 线程：线程是在进程内部同时做的事情，比如在LOL里，有很多事情要同时做，比如"盖伦” 击杀“提莫”，
         * 同时“赏金猎人”又在击杀“盲僧”，这就是由多线程来实现的。
         *
         * 此处代码演示的是不使用多线程的情况：
         * 只有在盖伦杀掉提莫后，赏金猎人才开始杀盲僧
         */
        HeroT gareen = new HeroT();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        HeroT teemo = new HeroT();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        HeroT bh = new HeroT();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        HeroT leesin = new HeroT();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        /**
         * 不加多线程
         */
        //盖伦攻击提莫
//        while(!teemo.isDead()){
//            gareen.attackHero(teemo);
//        }
//
//        //赏金猎人攻击盲僧
//        while(!leesin.isDead()){
//            bh.attackHero(leesin);
//        }

        /**
         * 继承线程类
         */
        KillThread killThread1 = new KillThread(gareen,teemo);
        killThread1.start();
        KillThread killThread2 = new KillThread(bh,leesin);
        killThread2.start();

        /**
         * 实现Runnable接口
         */
        Battle battle1 = new Battle(gareen,teemo);
        new Thread(battle1).start();

        Battle battle2 = new Battle(bh,leesin);
        new Thread(battle2).start();

        /**
         * 匿名类方式
         */
        //匿名类
        Thread t1= new Thread(){
            public void run(){
                //匿名类中用到外部的局部变量teemo，必须把teemo声明为final
                //但是在JDK7以后，就不是必须加final的了
                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };

        t1.start();

        Thread t2= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    bh.attackHero(leesin);
                }
            }
        };
        t2.start();

    }
}

/**
 * 创建多线程-继承线程类
 * 使用多线程，就可以做到盖伦在攻击提莫的同时，赏金猎人也在攻击盲僧
 * 设计一个类KillThread 继承Thread，并且重写run方法（Thread类实现了Runnable接口）
 * 启动线程办法： 实例化一个KillThread对象，并且调用其start方法
 * 就可以观察到 赏金猎人攻击盲僧的同时，盖伦也在攻击提莫
 */
class KillThread extends Thread{

    private HeroT h1;
    private HeroT h2;

    public KillThread(HeroT h1, HeroT h2){
        this.h1 = h1;
        this.h2 = h2;
    }

    //重写run方法，将需要异步执行的方法放入其中
    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}

/**
 * 创建多线程-实现Runnable接口
 * 创建类Battle，实现Runnable接口
 * 启动的时候，首先创建一个Battle对象，然后再根据该battle对象创建一个线程对象，并启动
 *
 * Battle battle1 = new Battle(gareen,teemo);
 * new Thread(battle1).start();
 *
 * battle1 对象实现了Runnable接口，所以有run方法，但是直接调用run方法，并不会启动一个新的线程。
 * 必须，借助一个线程对象的start()方法，才会启动一个新的线程。
 * 所以，在创建Thread对象的时候，把battle1作为构造方法的参数传递进去，这个线程启动的时候，就会去执行battle1.run()方法了。
 */
class Battle implements Runnable {

    private HeroT h1;
    private HeroT h2;

    public Battle(HeroT h1, HeroT h2) {
        this.h1 = h1;
        this.h2 = h2;
    }

    public void run() {
        while (!h2.isDead()) {
            h1.attackHero(h2);
        }
    }
}




class HeroT{
    public String name;
    public float hp;
    public int damage;

    public void attackHero(HeroT h) {
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

/**
 * 练习-同步查找文件内容
 * 练习-同步查找文件内容
 * 把 练习-查找文件内容 改为多线程查找文件内容
 * 原练习的思路是遍历所有文件，当遍历到文件是 .java的时候，查找这个文件的内容，查找完毕之后，再遍历下一个文件
 *
 * 现在通过多线程调整这个思路：
 * 遍历所有文件，当遍历到文件是.java的时候，创建一个线程去查找这个文件的内容，不必等待这个线程结束，继续遍历下一个文件
 */
class practiceM {
    /**
     * 首先准备一个SearchFileThread,继承Thread类，并重写run方法。 在run方法中，读取文件内容并查找
     *
     * 然后在遍历文件的时候，如果是以.java结尾，则启动一个SearchFileThread线程，进行查找工作
     */
    public static void main(String[] args) {
        File folder =new File("C:\\Users\\Sharpg\\IdeaProjects");
        search(folder,"java");
    }
    public static void search(File file, String search) {
        if (file.isFile()) {
            if(file.getName().toLowerCase().endsWith(".java")){
                //当找到.java文件的时候，就启动一个线程，进行专门的查找
                new SearchFileThread(file,search).start();
            }
        }
        if (file.isDirectory()) {
            File[] fs = file.listFiles();
            for (File f : fs) {
                search(f, search);
            }
        }
    }
}
class SearchFileThread extends Thread{

    private File file;
    private String search;
    public SearchFileThread(File file,String search) {
        this.file = file;
        this.search= search;
    }

    public void run(){
        String fileContent = readFileConent(file);
        if(fileContent.contains(search)){
            System.out.printf("找到子目标字符串%s,在文件:%s%n",search,file);
        }
    }

    /**
     * 查找文件内容
     * @param file
     * @return
     */
    public String readFileConent(File file){
        try (FileReader fr = new FileReader(file)) {
            char[] all = new char[(int) file.length()];
            fr.read(all);
            return new String(all);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}


