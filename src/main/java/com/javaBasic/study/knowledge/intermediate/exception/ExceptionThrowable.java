package com.javaBasic.study.knowledge.intermediate.exception;

import java.io.File;
import java.io.FileInputStream;

public class ExceptionThrowable {
    /**
     * Throwable是类，Exception和Error都继承了该类
     * 所以在捕捉的时候，也可以使用Throwable进行捕捉
     * 如图： 异常分Error和Exception
     * Exception里又分运行时异常和可查异常
     */
    public static void main(String[] args) {

        File f = new File("d:/LOL.exe");

        try {
            new FileInputStream(f);
            //使用Throwable进行异常捕捉
        } catch (Throwable t) {
            // TODO Auto-generated catch block
            t.printStackTrace();
        }

    }
}
