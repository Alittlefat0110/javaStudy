package com.javaBasic.study.knowledge.intermediate.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 中文问题
 */
public class ChineseQuestion {
    /**
 * 常见编码:
 * 工作后经常接触的编码方式有如下几种：
 * ISO-8859-1 ASCII 数字和西欧字母
 * GBK GB2312 BIG5 中文
 * UNICODE (统一码，万国码)
 *
 * 其中
 * ISO-8859-1 包含 ASCII
 * GB2312 是简体中文，BIG5是繁体中文，GBK同时包含简体和繁体以及日文。
 * UNICODE 包括了所有的文字，无论中文，英文，藏文，法文，世界所有的文字都包含其中
 *  步骤 3 : UNICODE和UTF:
 * 根据前面的学习，我们了解到不同的编码方式对应不同的棋盘，而UNICODE因为要存放所有的数据，那么它的棋盘是最大的。
 * 不仅如此，棋盘里每个数字都是很长的(4个字节)，因为不仅要表示字母，还要表示汉字等。
 *
 * 如果完全按照UNICODE的方式来存储数据，就会有很大的浪费。
 * 比如在ISO-8859-1中，a 字符对应的数字是0x61
 * 而UNICODE中对应的数字是 0x00000061，倘若一篇文章大部分都是英文字母，那么按照UNICODE的方式进行数据保存就会消耗很多空间
 *
 * 在这种情况下，就出现了UNICODE的各种减肥子编码, 比如UTF-8对数字和字母就使用一个字节，而对汉字就使用3个字节，
 * 从而达到了减肥还能保证健康的效果
 *
 * UTF-8，UTF-16和UTF-32 针对不同类型的数据有不同的减肥效果，一般说来UTF-8是比较常用的方式
 */
    public static void main(String[] args) {
//        String str1 = "中";
//        showCode(str1);
//        showCode(str1,"UTF-8");
        File f = new File("D:\\HHHH\\chinese.txt");
        try (FileInputStream fis = new FileInputStream(f);) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);

            //文件中读出来的数据是
            System.out.println("文件中读出来的数据是：");
            for (byte b : all)
            {
                int i = b&0x000000ff;  //只取16进制的后两位
                System.out.println(Integer.toHexString(i));
            }
            System.out.println("把这个数字，放在GBK的棋盘上去：");
            //UTF-8对数字和字母就使用一个字节，而对汉字就使用3个字节，从而达到了减肥还能保证健康的效果
            String str = new String(all,"UTF-8");
            System.out.println(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void showCode(String str) {
        String[] encodes = { "BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32" };
        for (String encode : encodes) {
            showCode(str, encode);
        }

    }
    //方法重载，方法名一样，输入参数不一样
    private static void showCode(String str, String encode) {
        try {
            System.out.printf("字符: \"%s\" 的在编码方式%s下的十六进制值是%n", str, encode);
            byte[] bs = str.getBytes(encode);

            for (byte b : bs) {
                int i = b&0xff;
                //int  j= b&0x000000ff;
                System.out.print(Integer.toHexString(i) + "\t");
            }
            System.out.println();
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            System.out.printf("UnsupportedEncodingException: %s编码方式无法解析字符%s\n", encode, str);
        }
    }
}

/**
 * 用FileReader 字符流正确读取中文
 */
class fileReader{
    /**
     * FileReader得到的是字符，所以一定是已经把字节根据某种编码识别成了字符了
     * 而FileReader使用的编码方式是Charset.defaultCharset()的返回值，如果是中文的操作系统，就是GBK
     * FileReader是不能手动设置编码方式的，为了使用其他的编码方式，只能使用InputStreamReader来代替，像这样：
     *
     * new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8"));
     *
     * 在本例中，用记事本另存为UTF-8格式，然后用UTF-8就能识别对应的中文了。
     *
     * 解释： 为什么中字前面有一个?
     * 如果是使用记事本另存为UTF-8的格式，那么在第一个字节有一个标示符，叫做BOM用来标志这个文件是用UTF-8来编码的。
     * @param args
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        File f = new File("D:\\HHHH\\fileReader.txt");
        System.out.println("默认编码方式:"+ Charset.defaultCharset());
        //FileReader得到的是字符，所以一定是已经把字节根据某种编码识别成了字符了
        //而FileReader使用的编码方式是Charset.defaultCharset()的返回值，如果是中文的操作系统，就是GBK
        try (FileReader fr = new FileReader(f)) {
            char[] cs = new char[(int) f.length()];
            fr.read(cs);
            System.out.printf("FileReader会使用默认的编码方式%s,识别出来的字符是：%n",Charset.defaultCharset());
            System.out.println(new String(cs));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //FileReader是不能手动设置编码方式的，为了使用其他的编码方式，只能使用InputStreamReader来代替
        //并且使用new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8")); 这样的形式
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8"))) {
            char[] cs = new char[(int) f.length()];
            isr.read(cs);
            System.out.printf("InputStreamReader 指定编码方式UTF-8,识别出来的字符是：%n");
            System.out.println(new String(cs));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

/**
 * 找出 E5 B1 8C 这3个十六进制对应UTF-8编码的汉字
 */
class practice0{
    public static void main(String[] args){
        File f=new File("D:\\HHHH\\input.txt");
        try(FileOutputStream out = new FileOutputStream(f)) {
            byte[] all = new byte[] {
                    (byte) 0xE5,(byte) 0xB1,(byte) 0x8C
            };
            out.write(all);

        } catch ( IOException e) {

            e.printStackTrace();
        }
        try(InputStreamReader in = new InputStreamReader(new FileInputStream(f),Charset.forName("UTF-8"))){
            char[] all =new char[(int)f.length()];
            in.read(all);
            String str=new String(all);
            System.out.println(str);

        } catch (IOException e) {

            e.printStackTrace();
        }
        practice01();

    }


    /**
     * 练习-移除BOM
     * 如果用记事本根据UTF-8编码保存汉字就会在最前面生成一段标示符，这个标示符用于表示该文件是使用UTF-8编码的。
     * 找出这段标示符对应的十六进制，并且开发一个方法，自动去除这段标示符
     */
    private static void practice01(){
        File f = new File("D:\\HHHH\\chinese.txt");
        try (FileInputStream fis = new FileInputStream(f);) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
            System.out.println("首先确认按照UTF-8识别出来有？");
            String str = new String(all,"UTF-8");
            System.out.println(str);
            System.out.println("根据前面的所学，知道'中'字对应的UTF-8编码是：e4 b8 ad");
            System.out.println("打印出文件里所有的数据的16进制是：");
            for (byte b : all) {
                int i = b&0xff;
                System.out.print(Integer.toHexString(i)+ " ");
            }
            System.out.println();
            System.out.println("通过观察法得出 UTF-8的 BOM 是 ef bb bf");
            byte[] bom = new byte[3];
            bom[0] = (byte) 0xef;
            bom[1] = (byte) 0xbb;
            bom[2] = (byte) 0xbf;
            byte[] fileContentWithoutBOM= removeBom(all,bom);
            System.out.println("去掉了BOM之后的数据的16进制是：");
            for (byte b : fileContentWithoutBOM) {
                int i = b&0xff;
                System.out.print(Integer.toHexString(i)+ " ");
            }
            System.out.println();
            System.out.println("对应的字符串就没有问号了：");
            String strWithoutBOM=new String(fileContentWithoutBOM,"UTF-8");
            System.out.println(strWithoutBOM);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static byte[] removeBom(byte[] all, byte[] bom) {
        return Arrays.copyOfRange(all, bom.length, all.length);
    }
}

