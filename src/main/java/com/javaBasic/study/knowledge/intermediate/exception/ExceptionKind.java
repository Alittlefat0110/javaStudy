package com.javaBasic.study.knowledge.intermediate.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 异常分类：
 * 可查异常，运行时异常和错误3种
 * 其中，运行时异常和错误又叫非可查异常
 */
public class ExceptionKind {
    public static void main(String[] args) {

        /**
         * 可查异常： CheckedException
         * 可查异常即必须进行处理的异常，要么try catch住,要么往外抛，谁调用，谁处理，比如 FileNotFoundException
         * 如果不处理，编译器，就不让你通过
         */
        File f= new File("d:/LOL.exe");

        try{
            System.out.println("试图打开 d:/LOL.exe");
            new FileInputStream(f);
            System.out.println("成功打开");
        }
        catch(FileNotFoundException e){
            System.out.println("d:/LOL.exe不存在");
            e.printStackTrace();
        }

        /**
         * 运行时异常RuntimeException指： 不是必须进行try catch的异常
         * 常见运行时异常:
         * 除数不能为0异常:ArithmeticException
         * 下标越界异常:ArrayIndexOutOfBoundsException
         * 空指针异常:NullPointerException
         * 在编写代码的时候，依然可以使用try catch throws进行处理，与可查异常不同之处在于，即便不进行try catch，也不会有编译错误
         * Java之所以会设计运行时异常的原因之一，是因为下标越界，空指针这些运行时异常太过于普遍，如果都需要进行捕捉，代码的可读性就会变得很糟糕。
         */
        //任何除数不能为0:ArithmeticException
        int k = 5/0;

        //下标越界异常：ArrayIndexOutOfBoundsException
        int j[] = new int[5];
        j[10] = 10;

        //空指针异常：NullPointerException
        String str = null;
        str.length();

        /**
         * 错误:
         * 错误Error，指的是系统级别的异常，通常是内存用光了
         * 在默认设置下，一般java程序启动的时候，最大可以使用16m的内存
         * 如例不停的给StringBuffer追加字符，很快就把内存使用光了。抛出OutOfMemoryError
         * 与运行时异常一样，错误也是不要求强制捕捉的
         */
        StringBuffer sb =new StringBuffer();
        // Integer.MAX_VALUE为int类型取值的最大值
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sb.append('a');
        }
    }
}
/**
 * ArithmeticException——由于除数为0引起的异常；
 * ArrayStoreException——由于数组存储空间不够引起的异常；
 * ClassCastException—一当把一个对象归为某个类，但实际上此对象并不是由这个类 创建的，也不是其子类创建的，则会引起异常；
 * IllegalMonitorStateException——监控器状态出错引起的异常；
 * NegativeArraySizeException—一数组长度是负数，则产生异常；
 * NullPointerException—一程序试图访问一个空的数组中的元素或访问空的对象中的 方法或变量时产生异常；
 * OutOfMemoryException——用new语句创建对象时，如系统无法为其分配内存空 间则产生异常；
 * SecurityException——由于访问了不应访问的指针，使安全性出问题而引起异常；
 * IndexOutOfBoundsException——由于数组下标越界或字符串访问越界引起异常；
 * IOException——由于文件未找到、未打开或者I/O操作不能进行而引起异常；
 * ClassNotFoundException——未找到指定名字的类或接口引起异常；
 * CloneNotSupportedException——一程序中的一个对象引用Object类的clone方法，但 此对象并没有连接Cloneable接口，从而引起异常；
 * InterruptedException—一当一个线程处于等待状态时，另一个线程中断此线程，从 而引起异常，有关线程的内容，将在下一章讲述；
 * NoSuchMethodException一所调用的方法未找到，引起异常；
 * IllegalAccessException—一试图访问一个非public方法；
 * StringIndexOutOfBoundsException——访问字符串序号越界，引起异常；
 * ArrayIndexOutOfBoundsException—一访问数组元素下标越界，引起异常；
 * NumberFormatException——字符的UTF代码数据格式有错引起异常；
 * IllegalThreadException—一线程调用某个方法而所处状态不适当，引起异常；
 * FileNotFoundException——未找到指定文件引起异常；
 * EOFException——未完成输入操作即遇文件结束引起异常。
 */


/**
 * 运行时异常：
 * 都是RuntimeException类及其子类异常，如NullPointerException(空指针异常)、
 * IndexOutOfBoundsException(下标越界异常)等，这些异常是不检查异常，程序中可以选择捕获处理，
 * 也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
 *        运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，即使没有
 *        用try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。
 * 非运行时异常 （编译异常）：
 * 是RuntimeException以外的异常，类型上都属于Exception类及其子类。
 * 从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如IOException、SQLException
 * 等以及用户自定义的Exception异常，一般情况下不自定义检查异常。
 */
