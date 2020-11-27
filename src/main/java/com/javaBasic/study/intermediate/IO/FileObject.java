package com.javaBasic.study.intermediate.IO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 文件对象
 * 文件和文件夹都是用File代表
 */
public class FileObject {
    public static void main(String[] args) {
        /**
         * 创建一个文件对象
         * 使用绝对路径或者相对路径创建File对象
         */
        // 绝对路径
        File f1 = new File("D:\\myPrivate");
        System.out.println("f1的绝对路径：" + f1.getAbsolutePath());
        // 相对路径,相对于工作目录，如果在eclipse中，就是项目目录
        File f2 = new File("LOL.txt");
        System.out.println("f2的绝对路径：" + f2.getAbsolutePath());

        // 把f1作为父目录创建文件对象
        File f3 = new File(f1, "LOL.txt");

        System.out.println("f3的绝对路径：" + f3.getAbsolutePath());
    }
}

/**
 * 文件常用方法1
 */
class use1{
    /**
     * 注意1： 需要在D:\LOLFolder确实存在一个LOL.exe,才可以看到对应的文件长度、修改时间等信息
     *
     * 注意2： renameTo方法用于对物理文件名称进行修改，但是并不会修改File对象的name属性。
     */
    public static void main(String[] args){
        File f = new File("D:\\myprivate\\test1.txt");
        System.out.println("当前文件是：" +f);
        //文件是否存在
        System.out.println("判断是否存在："+f.exists());

        //是否是文件夹
        System.out.println("判断是否是文件夹："+f.isDirectory());

        //是否是文件（非文件夹）
        System.out.println("判断是否是文件："+f.isFile());

        //文件长度
        System.out.println("获取文件的长度："+f.length());
        //文件最后修改时间
        long time = f.lastModified();
        Date d = new Date(time);
        System.out.println("获取文件的最后修改时间："+d);
        //设置文件修改时间为1970.1.1 08:00:00
        Date b=new Date();
        System.out.println(b.getTime());
        f.setLastModified(b.getTime());

        //文件重命名
        File f2 =new File("D:\\myprivate\\test2.txt");
        f.renameTo(f2);
        System.out.println("把test.txt改名成了test1.txt");

        System.out.println("注意： 需要在D:\\myprivate确实存在一个test.txt,\r\n才可以看到对应的文件长度、修改时间等信息");
    }
}

/**
 * 文件常用方法2
 */
class use2{
    public static void main(String[] args) throws IOException {

        File f = new File("d:/LOLFolder/skin/garen.ski");

        // 以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
        f.list();

        // 以文件数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
        File[]fs= f.listFiles();

        // 以字符串形式返回获取所在文件夹
        f.getParent();

        // 以文件形式返回获取所在文件夹
        f.getParentFile();
        // 创建文件夹，如果父文件夹skin不存在，创建就无效
        f.mkdir();

        // 创建文件夹，如果父文件夹skin不存在，就会创建父文件夹
        f.mkdirs();

        // 创建一个空文件,如果父文件夹skin不存在，就会抛出异常
        f.createNewFile();
        // 所以创建一个空文件之前，通常都会创建父目录
        f.getParentFile().mkdirs();

        // 列出所有的盘符c: d: e: 等等
        f.listRoots();

        // 刪除文件
        f.delete();

        // JVM结束的时候，刪除文件，常用于临时文件的删除
        f.deleteOnExit();

    }
}

/**
 * 练习-遍历文件夹
 */
class practice{
    /**
     * 一般说来操作系统都会安装在C盘，所以会有一个 C:\WINDOWS目录。
     *
     * 遍历这个目录下所有的文件(不用遍历子目录)
     *
     * 找出这些文件里，最大的和最小(非0)的那个文件，打印出他们的文件名
     *
     * 注: 最小的文件不能是0长度
     */
    public static void main(String[] args) {
//        //创建文件对象/引用  pathname引号里首尾不要有空格
//        File f = new File("C:\\WINDOWS");
//        System.out.println(f.length());
//        File[] files=f.listFiles();
//        long MAX=files[0].length();
//        File max=files[0];
//        long MIN=Long.MAX_VALUE;
//        File min=files[0];
//        int count=0;
//        for (File file : files){
//            System.out.println(file);
//            count++;
//            if(file.length()>MAX){
//                MAX=file.length();
//                 max=file;
//            }
//            if (file.length()<MIN && file.length()!=0){
//                MIN=file.length();
//                min=file;
//            }
//        }
//        System.out.println("长度最大的文件是："+max+" 长度为："+MAX);
//        System.out.println("长度最小（非0）的文件是："+min+" 长度为："+MIN);
//        System.out.println(f+"文件夹下的文件总个数为："+count);
        practice1();
    }

    /**
     * 练习，要求遍历子文件夹
     * 要求同上
     */
    private static void practice1(){
        File f = new File("C:\\WINDOWS");
        File[] files=f.listFiles();
        //存储所有子文件长度的最大值
        long MAX1=0;
        //存储所有子文件长度的最小值（非0）
        long MIN1=Long.MAX_VALUE;
        //存放长度最大的（非0）子文件
        File max1= null;
        //存放长度最小（非0）的子文件
        File min1=null;
        int count=0;
        for (File file : files) {
            if (file.listFiles()==null){
                System.out.println("文件夹："+file+"下没有子文件");
                System.out.println();
                continue;
            }
            File[] files1 = file.listFiles();
            //存储文件长度的最大值
            long MAX=0;
            //文件长度最大的文件
            File max=null;
            //存储文件长度的最小值（非0）
            long MIN=Long.MAX_VALUE;
            //文件长度最小（非0）的文件
            File min=null;
            for (File file1 : files1) {
                count++;
                if (file1.length() > MAX) {
                    MAX = file1.length();
                    max = file1;
                }
                if (file1.length() < MIN && file1.length() != 0) {
                    MIN = file1.length();
                    min = file1;
                }
            }
            if(MAX>MAX1){
                MAX1=MAX;
                max1=max;
            }
            if (MIN<MIN1){
                MIN1=MIN;
                min1=min;
            }
            System.out.println(f+"文件夹下的文件总个数为："+count);
            System.out.println("长度最大的文件是："+max+" 长度为："+MAX);
            System.out.println("长度最小（非0）的文件是："+min+" 长度为："+MIN);
            System.out.println();
        }
        System.out.println("文件夹"+f+"的所有子文件中长度最大的为："+max1+"  其长度为："+MAX1);
        System.out.println("文件夹"+f+"的所有子文件中长度最小（非0）的为："+min1+"  其长度为："+MIN1);
    }
}
