package com.javaBasic.study.knowledge.primary;

/**
 * 变量的数据类型：
 */
public class BVariable {
    /**
     * 整型   （4种）byte-8位 short-16位 int-32位 long-64位   取值范围：-2的n（位数）次方-至->2的n次方-1
     */
    byte b = 127;
    short s = 32767;
    int i = 2147483647;
    //在定义long类型时，bai如果数据类型超过int类型的du取值范围，数据后面要加l或L，不超过则不需要加
    long l = 9223372036854775807L;

    /**
     * 字符型（1种）char  16位
     */
    //char类型用于存放一个字符，值用单引号''表示 (双引号表示字符串)
    //char 只能存放一个字符，超过一个字符就会产生编译错误
    char c = '中';
    //char c2 = '中国'; //报错

    /**
     * 浮点型 （2种）double-64位  float-32位
     * 默认的小数值是double类型的
     * 所以 float f = 54.321会出现编译错误，因为54.321的默认类型是 double，其类型 长度为64，超过了float的长度32
     * 在数字后面加一个字母f，直接把该数字声明成float类型
     */
    //float:单精度类型,精度是8位有效数字，取值范围是10的-38次方到10的38次方???，float占用4个字节的存储空间
    //double:双精度类型，精度是17位有效数字，取值范围是10的-308次方到10的308次方???，double占用8个字节的存储空间
    double d=54.321;
    double d1=1.7e+308;
    float f = 54.321f;
    float f2 =2147483648f;//超过了float的长度32  2的32次方-1 加f
    float f1 =3.4e+38f;

    /**
     * 布尔型（1种）boolean
     */
    //其长度为1,布尔型真正存放的数据是0(false) 1(true),但是，不能直接使用0 1 进行赋值
    boolean b1 = true;//1
    boolean b2 = false;//0

    /**
     * String类型
     * String类型其实并不是基本类型，但是它是如此广泛的被使用，常常被误以为是一种基本类型。
     * String类型是Immutable的，一旦创建就不能够被改变
     */
    String str = "Hello Java";

}

/**
 * 类型转换
 */
class Change {
    //转换规则，精度（对应长度）从小到大自然转，从大到小强制转
    //高精度到低精度只能强制转换，强制转换的意思就是，转是可以转的，但是不对转换之后的值负责，在低精度对象不能装下高精度对象时还是会报错
    int i=5;
    long l=100;
    long l1=i;//低到高
    int i1=(int)l;//高到低

    //然short和char都是16位的，长度是一样的，但是彼此之间，依然需要进行强制转换
    char c = 'A';
    short s = 80;
    //char a1 =s;//报错
    char a = (char) s;
}

/**
 * 中文也可用于命名变量
 */
class 国家{
    String 中国="第二大经济体";
    public static void main(String[] args){
        国家 国家1=new 国家();
        String str=国家1.中国;
    }
}

/**
 * 命名规则
 */
class Name{
    /**
     * 变量命名只能使用字母 数字 $ _
     * 变量第一个字符 只能使用 字母 $ _
     * 变量第一个字符 不能使用数字
     * 使用完整的单词命名，而非缩写
     * 不能只使用关键字，但是可以包含关键字
     */
}


/**
 * 变量的作用域
 */
class ActionScope{
    /**
     * 变量处于不同的位置，有不同的名称
     * 分别是
     * 字段，属性
     * 参数
     * 局部变量
     * 不同名称的变量，其作用域是不一样的
     */

    /**
     * 字段 或者属性、成员变量、Field:
     * 当一个变量被声明在类下面
     * 变量就叫做字段 或者属性、成员变量、Field
     * 比如变量i,就是一个属性。
     * 那么从第2行这个变量声明的位置开始，整个类都可以访问得到
     * 所以其作用域就是从其声明的位置开始的整个类
     */
    int i = 1;
    int j = i;  //其他的属性可以访问i
    public void method1(){
        System.out.println(i); //方法1里可以访问i
    }
    public void method2(){
        System.out.println(i); //方法2里可以访问i
    }

    /**
     * 参数:
     * 如果一个变量，是声明在一个方法上（与方法内的区别）的，就叫做参数
     * 参数的作用域即为该方法内的所有代码
     * 其他方法不能访问该参数
     * 类里面也不能访问该参数
     */
    public void method3(int x){ //参数x的作用域即方法method3
        System.out.println(x);
    }
    public void method4(){

        //System.out.println(x); //method4 不能访问参数x
    }
   // int j = i;  //类里面也不能访问参数i


    /**
     * 局部变量
     * 声明在方法内(与方法上的区别）的变量，叫做局部变量
     * 其作用域在声明开始的位置，到其所处于的块结束位置
     */
    public void method5() {
        int y  = 5;  //其作用范围是从声明的第146行，到其所处于的块结束154行位置
        System.out.println(i);
        {            //子块
            System.out.println(y); //可以访问y
            int p = 6;
            System.out.println(p); //可以访问p
        }
        //System.out.println(p); //不能访问p,因为其作用域到第152行就结束了
    }

}

/**
 * final
 * 当一个变量被final修饰的时候，该变量只有一次赋值的机会
 */
class  Final{
    /**
     * 在声明的时候赋值
     */
    public void method1() {
        final int i = 5;
        //i = 10; //i在第4行已经被赋值过了，所以这里会出现编译错误
    }

    /**
     * 在声明的时候没有赋值
     */
    public void method2() {
        final int i;
        i = 10; //i在第176行，只是被声明，但是没有被赋值，所以在这里可以进行第一次赋值
       // i = 11; //i在第177行已经被赋值过了，所以这里会出现编译错误
    }

    /**
     * final修饰的是参数
     */
    public void method3(final int j) {
        //j = 5; //无法赋值，在调用method3的时候已经传入参数，即已经赋值
    }

    /**
     * final 修饰其他
     * final 除了修饰变量，还可以修饰类，修饰方法
     */
}

/**
 * 以;结尾的一段代码，即为一个表达式
 * 从{ 开始 到对应的} 结束，即一个块
 */
