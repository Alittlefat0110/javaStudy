package com.javaBasic.study.knowledge.intermediate.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 关闭流
 * 所有的流，无论是输入流还是输出流，使用完毕之后，都应该关闭。
 * 如果不关闭，会产生对资源占用的浪费。 当量比较大的时候，会影响到业务的正常开展。
 */
public class CloseFlow {
    /**
     * 在try中关
     * 在try的作用域里关闭文件输入流，在前面的示例中都是使用这种方式，这样做有一个弊端；
     * 如果文件不存在，或者读取的时候出现问题而抛出异常，那么就不会执行这一行关闭流的代码，
     * 存在巨大的资源占用隐患。 不推荐使用
     * @param args
     */
    public static void main(String[] args) {
        try {
            File f = new File("d:/lol.txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
            // 在try 里关闭流
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 在finally中关闭:
 * 这是标准的关闭流的方式
 * 1. 首先把流的引用声明在try的外面，如果声明在try里面，其作用域无法抵达finally.
 * 2. 在finally关闭之前，要先判断该引用是否为空
 * 3. 关闭的时候，需要再一次进行try catch处理
 */
class CloseFlow1{
    public static void main(String[] args) {
        File f = new File("d:/lol.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 在finally 里关闭流
            if (null != fis)
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}

/**
 * 使用try()的方式顶折纠问
 * 把流定义在try()里,try,catch或者finally结束的时候，会自动关闭
 * 这种编写代码的方式叫做 try-with-resources， 这是从JDK7开始支持的技术
 * 所有的流，都实现了一个接口叫做 AutoCloseable，任何类实现了这个接口，都可以在try()中进行实例化。
 * 并且在try, catch, finally结束的时候自动关闭，回收相关资源
 */
class CloseFlow2{
    public static void main(String[] args) {
        File f = new File("d:/lol.txt");

        //把流定义在try()里,try,catch或者finally结束的时候，会自动关闭
        try (FileInputStream fis = new FileInputStream(f)) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
