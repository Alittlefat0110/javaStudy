package com.javaBasic.study.knowledge.intermediate.IO;

import java.io.*;

/**
 * 缓存流
 */
public class CacheFlow {
    /**
     * 使用缓存流读取数据：
     * 缓存字符输入流 BufferedReader 可以一次读取一行数据
     * @param args
     */
    public static void main(String[] args) {
        // 准备文件lol.txt其中的内容是
        // garen kill teemo
        // teemo revive after 1 minutes
        // teemo try to garen, but killed again
        File f = new File("D:\\HHHH\\test.txt");
        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (FileReader fr = new FileReader(f);//字符流
                BufferedReader br = new BufferedReader(fr);//缓存流
        )
        {

//            char[] chars=new char[(int)f.length()];
//            fr.read(chars);
//            for (char c:chars){
//                System.out.print(c);
//            }
//            System.out.println();
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        cacheInput();
    }

    /**
     * 使用缓存流写出数据
     * PrintWriter 缓存字符输出流， 可以一次写出一行数据
     */
    private static void cacheInput(){
// 向文件lol2.txt中写入三行语句
        File f = new File("D:\\HHHH\\test.txt");

        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f,true);
                // 缓存流必须建立在一个存在的流的基础上
                //加上true代表不覆盖原有数据
                PrintWriter pw = new PrintWriter(fw,true);
        ) {
            pw.print("one3 \n");
            pw.print("two1 \n");
            pw.print("three1 \n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * flush
     * 有的时候，需要立即把数据写入到硬盘，而不是等缓存满了才写出去。 这时候就需要用到flush
     */
    private static void cacheFlash(){
//向文件lol2.txt中写入三行语句
        File f =new File("d:/lol2.txt");
        //创建文件字符流
        //缓存流必须建立在一个存在的流的基础上
        try(FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
            pw.println("garen kill teemo");
            //强制把缓存中的数据写入硬盘，无论缓存是否已满
            pw.flush();
            pw.println("teemo revive after 1 minutes");
            pw.flush();
            pw.println("teemo try to garen, but killed again");
            pw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
