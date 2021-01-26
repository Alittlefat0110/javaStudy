package com.javaBasic.study.intermediate.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 查找文件，并打印所在位置
 */
public class practiceS {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        File folder = new File("C:\\Users\\Sharpg\\IdeaProjects");
        if(!folder.exists()){
            System.out.println("未找到文件夹");
            return;
        }
        search(folder, "File");
        long end = System.currentTimeMillis();
        System.out.println("费时（毫秒）" + (end - begin));
    }

    public static void search(File folder, String search) {
        File[] files = folder.listFiles();
        for (File file : files){
            // 遍历文件夹
            if (file.isDirectory()){
                search(file, search);
            }
            // 是.java文件就开找
            else if (file.isFile() && file.getName().endsWith(".java")){
                char[] content = new char[(int) file.length()];
                // 拿到文件内容
                try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                    reader.read(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 判断是否包含该字符串
                if (String.valueOf(content).contains(search)){
                    System.out.println("找到字串在：" + file.getAbsolutePath());
                }
            }
        }
    }
}
