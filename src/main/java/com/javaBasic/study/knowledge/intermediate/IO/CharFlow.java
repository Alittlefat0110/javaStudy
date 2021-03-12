package com.javaBasic.study.knowledge.intermediate.IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流
 * Reader字符输入流
 * Writer字符输出流
 * 专门用于字符的形式读取和写入数据
 */
public class CharFlow {
    /**
     * 使用字符流读取文件：
     * FileReader 是Reader子类，以FileReader 为例进行文件读取
     */
    public static void main(String[] args){
        File file=new File("D:\\HHHH\\new1.txt");
        try(FileReader fileReader=new FileReader(file);) {
            char[] chars=new char[(int)file.length()];
            fileReader.read(chars);
            for (char c:chars){
                System.out.print(c);
            }
            System.out.println();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

/**
 * 使用字符流把字符串写入到文件:
 * FileWriter 是Writer的子类，以FileWriter 为例把字符串写入到文件
 */
class CharFlow1{
    public static void main(String[] args){
        File file=new File("D:\\HHHH\\new1.txt");
        try(FileWriter fileWriter=new FileWriter(file)) {
            char[] chars=new char[22];
            chars[20]='测';
            chars[21]='试';
            fileWriter.write(chars);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

/**
 * 练习-文件加密
 */
class practice6{
    public static void main(String[] args){
        File encodingFile=new File("D:\\HHHH\\encodingFile.txt");
        File encodedFile=new File("D:\\HHHH\\encodedFile.txt");
        encodeFile(encodingFile,encodedFile);

    }

    /**
     * 在这个方法中把encodingFile的内容进行加密，然后保存到encodedFile文件中。
     * 加密算法：
     * 数字：
     * 如果不是9的数字，在原来的基础上加1，比如5变成6, 3变成4
     * 如果是9的数字，变成0
     * 字母字符：
     * 如果是非z字符，向右移动一个，比如d变成e, G变成H
     * 如果是z，z->a, Z-A。
     * 字符需要保留大小写
     * 非字母字符
     * 比如',&^ 保留不变，中文也保留不变
     * @param encodingFile
     * @param encodedFile
     */
    public static void encodeFile(File encodingFile, File encodedFile){
        //在try()中创建输入输出流
        try (FileReader fileReader=new FileReader(encodingFile);
             FileWriter fileWriter=new FileWriter(encodedFile)){
            //创建字符数组用于存放文件内容
            char[] chars=new char[(int)encodingFile.length()];
            //以字符读取文件中内容
            fileReader.read(chars);
            //添加辅助变量，用于更改字符的ASCII码值
            int ch=0;
            //加密
            for (int i=0;i<chars.length;i++){
                char c=chars[i];
                //若字符为9则改为0
                if(c=='9'){
                    c='0';
                    //若字符为数字且不为9则ASCII码+1（等同于数值+1）
                }else if (Character.isDigit(c)&&c!='9'){
                    //此时c以ASCII码值参与运算
                    ch=c+1;
                    c= (char)ch;
                    //若字符为字母且不为'z','Z',则ASCII码+1
                }else if ((Character.isLowerCase(c)&&c!='z')|| (Character.isUpperCase(c)&&c!='Z')){
                       ch=c+1;
                       c=(char)ch;
                       //若字符为'z','Z'则z变为a，Z变为A，即ASCII码-25（26个字母首尾相差25）
                }else if (c=='z'||c=='Z'){
                    ch=c-25;
                    c= (char) ch;
                }
                //加密后的c（字符）重新存入字符数组中
                chars[i]=c;
                System.out.print(c);
            }
            System.out.println();
            //将加密字符存入加密文件
            fileWriter.write(chars);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
