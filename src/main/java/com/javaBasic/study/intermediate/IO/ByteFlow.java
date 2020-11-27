package com.javaBasic.study.intermediate.IO;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节流
 * InputStream字节输入流
 * OutputStream字节输出流
 * 用于以字节的形式读取和写入数据
 */
@Slf4j
public class ByteFlow {
    /**
     * 以字节流的形式读取文件内容：
     * InputStream是字节输入流，同时也是抽象类，只提供方法声明，不提供方法的具体实现。
     * FileInputStream 是InputStream子类，以FileInputStream 为例进行文件读取
     */
    public static void main(String[] args) {
        try {
            //准备文件test2.txt其中的内容是AB，对应的ASCII分别是65 66
            File f =new File("D:\\myprivate\\test2.txt");
            //创建基于文件的输入流
            FileInputStream fis =new FileInputStream(f);
            //创建字节数组，其长度就是文件的长度
            byte[] all =new byte[(int) f.length()];
            //以字节流的形式读取文件所有内容
            fis.read(all);
            for (byte b : all) {
                //打印出来是65 66
                System.out.println(b);
            }

            //每次使用完流，都应该进行关闭
            fis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //output();
        //practice();
        practice1();
    }
    private static void output(){
        /**
         * 以字节流的形式向文件写入数据顶折纠问
         * OutputStream是字节输出流，同时也是抽象类，只提供方法声明，不提供方法的具体实现。
         * FileOutputStream 是OutputStream子类，以FileOutputStream 为例向文件写出数据
         *
         * 注: 如果文件d:/lol2.txt不存在，写出操作会自动创建该文件。
         * 但是如果是文件 d:/xyz/lol2.txt，而目录xyz又不存在，会抛出异常
         */
        try {
            // 准备文件lol2.txt其中的内容是空的
            File f = new File("D:\\myprivate\\text.txt");
            // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y
            byte[] data = { 65, 66 };

            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 练习-写入数据到文件
     * 以字节流的形式向文件写入数据 中的例子，当lol2.txt不存在的时候，是会自动创建lol2.txt文件的。
     * 但是，如果是写入数据到d:/xyz/lol2.txt，而目录xyz又不存在的话，就会抛出异常。
     * 那么怎么自动创建xyz目录？
     * 如果是多层目录 d:/xyz/abc/def/lol2.txt 呢？(与创建一层一样）
     */
    private static void practice(){
        try {
            File file=new File("D:\\HHHH\\XX\\new.txt");
            byte[] bytes=new byte[]{88,89,90};
            // 以字符串形式返回获取所在文件夹
            File file1=new File(file.getParent());
            //判断HHHH\XX文件夹是否存在
            if(!file1.exists())
            {
                // 创建文件夹，如果父文件夹不存在，就会创建父文件夹
                boolean flag= file1.mkdirs();
                if(flag)
                {
                    System.out.println("创建文件夹成功");
                    FileOutputStream fs=new FileOutputStream(file);
                    fs.write(bytes);
                    fs.close();
                    System.out.println("写入成功");
                }
            }
            else
            {
                FileOutputStream fs=new FileOutputStream(file);
                fs.write(bytes);
                fs.close();
                System.out.println("写入成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 练习-拆分文件
     * 找到一个大于100k(1k=1024字节)的文件，按照100k为单位，拆分成多个子文件，并且以编号作为文件名结束。
     * 比如文件 eclipse.exe，大小是309k。
     * 拆分之后，成为
     * eclipse.exe-0
     * eclipse.exe-1
     * eclipse.exe-2
     * eclipse.exe-3
     */
    private static void practice1() {
        FileInputStream fis=null;
        FileOutputStream fs=null;
        try {
            File file = new File("D:\\HHHH");
            File[] files = file.listFiles();
            int I=100 * 1024;
            for (File f : files) {
                //找到一个大于100k的文件
                if (f.length() > I) {
                    // 准备长度是文件长度的字节数组，存放文件内容
                    byte[] bytes = new byte[(int) f.length()];
                    //创建字节输入流将文件以字节存入数组
                    fis =new FileInputStream(f);
                    fis.read(bytes);
                    //准备长度是100k的字节数组，存放拆分文件内容
                    byte[] bytes1 = new byte[I];
                    //循环将文件拆分内容放入不同文件中
                    int length=(int) f.length();
                    for (int i = 0; i <=(int) f.length() / I; i++) {
                        //生成文件名
                        String str = String.valueOf(f)+i;
                        //以上述字符串为文件名生成文件
                        File file1 = new File(str);
                        //创建字节输出流
                        fs = new FileOutputStream(file1);
                        if (length>I) {
                            //复制文件内容到新的文件
                            System.arraycopy(bytes, i * I, bytes1, 0, I);
                            //将拆分内容写入新的文件
                            fs.write(bytes1);
                        }else {
                            byte[] bytes2 = new byte[length];
                            //复制文件内容到新的文件
                            System.arraycopy(bytes, i * I, bytes2, 0, length);
                            //将拆分内容写入新的文件
                            fs.write(bytes2);
                        }
                        length=length-I;
                    }
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fs!=null){
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 练习-合并文件
     * 把上述拆分出来的文件，合并成一个原文件。
     * 以是否能正常运行，验证合并是否正确
     */
    private static void practice2(){

    }
}

/**
 * ASCII码 概念：
 * 所有的数据存放在计算机中都是以数字的形式存放的。 所以字母就需要转换为数字才能够存放。
 * 比如A就对应的数字65，a对应的数字97. 不同的字母和符号对应不同的数字，就是一张码表。
 * ASCII是这样的一种码表。 只包含简单的英文字母，符号，数字等等。 不包含中文，德文，俄语等复杂的。
 *
 * 示例中列出了可见的ASCII码以及对应的十进制和十六进制数字，不可见的暂未列出
 */

/**
 * 字符	十进制数字	十六进制数字
 * !	33	21
 * "	34	22
 * #	35	23
 * $	36	24
 * %	37	25
 * &	38	26
 * '	39	27
 * (	40	28
 * )	41	29
 * *	42	2A
 * +	43	2B
 * ,	44	2C
 * -	45	2D
 * .	46	2E
 * /	47	2F
 * 0	48	30
 * 1	49	31
 * 2	50	32
 * 3	51	33
 * 4	52	34
 * 5	53	35
 * 6	54	36
 * 7	55	37
 * 8	56	38
 * 9	57	39
 * :	58	3A
 * ;	59	3B
 * <	60	3C
 * =	61	3D
 * >	62	3E
 * @	64	40
 * A	65	41
 * B	66	42
 * C	67	43
 * D	68	44
 * E	69	45
 * F	70	46
 * G	71	47
 * H	72	48
 * I	73	49
 * J	74	4A
 * K	75	4B
 * L	76	4C
 * M	77	4D
 * N	78	4E
 * O	79	4F
 * P	80	50
 * Q	81	51
 * R	82	52
 * S	83	53
 * T	84	54
 * U	85	55
 * V	86	56
 * W	87	57
 * X	88	58
 * Y	89	59
 * Z	90	5A
 * [	91	5B
 * \	92	5C
 * ]	93	5D
 * ^	94	5E
 * _	95	5F
 * `	96	60
 * a	97	61
 * b	98	62
 * c	99	63
 * d	100	64
 * e	101	65
 * f	102	66
 * g	103	67
 * h	104	68
 * i	105	69
 * j	106	6A
 * k	107	6B
 * l	108	6C
 * m	109	6D
 * n	110	6E
 * o	111	6F
 * p	112	70
 * q	113	71
 * r	114	72
 * s	115	73
 * t	116	74
 * u	117	75
 * v	118	76
 * w	119	77
 * x	120	78
 * y	121	79
 * z	122	7A
 * {	123	7B
 * |	124	7C
 * }	125	7D
 * ~	126	7E
 */
