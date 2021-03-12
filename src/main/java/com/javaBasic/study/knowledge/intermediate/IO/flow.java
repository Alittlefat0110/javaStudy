package com.javaBasic.study.knowledge.intermediate.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 什么是流(Stream)，流就是一系列的数据
 */
public class flow {
    /**
     * 当不同的介质之间有数据交互的时候，JAVA就使用流来实现。
     * 数据源可以是文件，还可以是数据库，网络甚至是其他的程序
     *
     * 比如读取文件的数据到程序中，站在程序的角度来看，就叫做输入流
     * 输入流： InputStream
     * 输出流：OutputStream
     */

    /**
     * 文件输入流顶折纠问
     * 如下代码，就建立了一个文件输入流，这个流可以用来把数据从硬盘的文件，读取到JVM(内存)。
     * 目前代码只是建立了流，还没有开始读取
     */
    public static void main(String[] args) {
        try {
            File f = new File("d:/lol.txt");
            // 创建基于文件的输入流
            FileInputStream fis = new FileInputStream(f);
            // 通过这个输入流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 练习
     * 参考文件输入流的建立，建立一个文件输出流
     */
    private static void practice(){
        try {
            File f = new File("d:/lol.txt");
            // 创建基于文件的输入流
            FileOutputStream fis = new FileOutputStream(f);
            // 通过这个输入流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
