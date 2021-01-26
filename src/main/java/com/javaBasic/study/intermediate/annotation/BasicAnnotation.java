package com.javaBasic.study.intermediate.annotation;

/**
 * 基本内置注解
 */
public class BasicAnnotation {


}

/**
 * //@Override
 * @Override 用在方法上，表示这个方法重写了父类的方法，如toString()。
 * 如果父类没有这个方法，那么就无法编译通过，如例所示，在fromString()方法上加上@Override 注解，
 * 就会失败，因为Hero类的父类Object，并没有fromString方法
 */
class HeroA {
    String name;
    @Override
    public String toString(){
        return name;
    }
//    @Override
//    public String fromString(){
//        return name;
//    }
}
