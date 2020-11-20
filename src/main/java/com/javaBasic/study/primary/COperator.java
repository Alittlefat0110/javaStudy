package com.javaBasic.study.primary;

/**
 * 操作符
 */
public class COperator {
    /**
     * 算数操作符：+  -  *  /  %  ++  --
     */
    public static void main(String[] args) {
        //如果有任何运算单元的长度超过int,那么运算结果就按照最长的长度计算，小于int按int计算
        int a = 5;
        long b = 6;
        int c = (int) (a+b); //a+b的运算结果是long型，所以要进行强制转换
        long d = a+b;

        //取余（取模）
        int i = 5;
        int j = 2;
        System.out.println(i%j); //输出为1

        //i++: 先取（赋）值，再运算
        //++i: 先运算，再取（赋）值
        int x = 5;
        System.out.println(x++); //输出5
        System.out.println(x);   //输出6
        int y = 5;
        System.out.println(++y); //输出6
        System.out.println(y);   //输出6
        int i1 = 1;
        //2+2+4+5+5
        int j1 = ++i1 + i1++ + ++i1 + ++i1 + i1++;
        System.out.println(j1);//输出18



    }
}

/**
 * 关系操作符
 */
class COperator1{
    /**
     * 关系操作符：比较两个变量之间的关系
     * > 大于
     * >= 大于或等于
     * < 小于
     * <= 小于或等于
     * == 是否相等
     * != 是否不等
     */
    public static void main(String[] args){
        System.out.println(1<2);
        System.out.println(5==8);
        System.out.println(6!=7);
    }
}

/**
 * 逻辑操作符
 */
class COperator2{
    /**
     *  &   长路与
     *  &&	长路与
     *  |   长路或
     *  ||	长路或
     *  !	取反
     *  ^	异或^
     */
    public static void main(String[] args){
        /**
         * 长路与和短路与：&  &&
         * 无论长路与还是短路与
         * 两边的运算单元都是布尔值
         * 都为真时，才为真
         * 任意为假，就为假
         * 区别:
         * 长路与 两侧，都会被运算
         * 短路与 只要第一个是false，第二个就不进行运算了
         */
        //长路与  无论第一个表达式的值是true或者false,第二个的值，都会被运算
        int i = 2;
        //无论如何i++都会被执行，所以i的值变成了3
        System.out.println( i== 1 & i++ ==2  );
        System.out.println(i);
        //短路与 只要第一个表达式的值是false的，第二个表达式的值，就不需要进行运算了
        int j = 2;
        //因为j==1返回false,所以右边的j++就没有执行了，所以j的值，还是2
        System.out.println( j== 1 && j++ ==2  );
        System.out.println(j);


        /**
         * 长路或和短路或  | ||
         * 无论长路或还是短路或
         * 两边的运算单元都是布尔值
         * 都为假时，才为假
         * 任意为真，就为真
         * 区别：
         * 长路或 两侧都会被运算
         * 短路或 只要第一个是true的，第二个就不进行运算了
         */
        //长路或  无论第一个表达式的值是true或者false,第二个的值，都会被运算
        int a = 2;
        //无论如何a++都会被执行，所以a的值变成了3
        System.out.println( a== 1 | a++ ==2  );
        System.out.println(a);

        //短路或 只要第一个表达式的值是true的，第二个表达式的值，就不需要进行运算了
        int b = 2;
        //因为b==2返回true,所以右边的b++就没有执行了，所以b的值，还是2
        System.out.println( b== 2 || b++ ==2  );
        System.out.println(b);

        /**
         * 取反：！
         * 真变假，假变真
         */
        boolean c = true;
        System.out.println(c); //输出true
        System.out.println(!c);//输出false

        /**
         * 异或: ^
         * 不同，返回真
         * 相同，返回假
         */
        boolean x = true;
        boolean y = false;
        System.out.println(x^y); //不同返回真
        System.out.println(x^!y); //相同返回假

        /**
         * 练习
         */
        int p = 1;
        //p++ == 3为false，!(p++ == 3)为true，p++ ==2为true，两者异或为false，
        // 此时p经过两次自增等于3，因为长路与 左侧为false，所以右侧不执行运算
        boolean q = !(p++ == 3) ^ (p++ ==2) && (p++==3);
        System.out.println(q);//输出false
        System.out.println(p);//输出3
    }
}

/**
 * 位操作符(针对二进制数操作）
 */
class COperator3{
    /**
     * Integer.toBinaryString()	一个整数的二进制表达
     * |	位或 （两边放的是数值进行计算，长路与 | 两边放的是逻辑值返回真假判断
     * &	位与
     * ^	异或
     * ~	取非
     * <<   左移  左移一位相当于乘以2
     * >>	右移  右移一位相当于除以2
     */
    public static void main(String[] args){
        /**
         * 位操作都是对二进制而言的，但是我们平常使用的都是十进制比如5。
         * 而5的二进制是101。
         * 所以在开始学习之前，需要掌握一个整数的二进制表达是多少。
         * 通过Integer.toBinaryString() 方法，将一个十进制整数转换为一个二进制字符串
         */
        int i  =5;
        int j = 6;
        //5的二进制是101
        System.out.println(Integer.toBinaryString(i));
        //6的二进制是110
        System.out.println(Integer.toBinaryString(j));
        //位或 所以 5|6 对每一位进行或运算，得到 111 即 7
        System.out.println(i|j);
        // 位与 所以 5&6 对每一位进行与运算，得到 100 即 4
        System.out.println(i&j);
        //所以 5^6 对每一位进行异或（相同为假，不同为真）运算，得到 011->3
        System.out.println(i^j);
        //任何数和0 进行异或 都等于自己
        System.out.println(i^0);
        //任何数和自己进行异或 都等于 0
        System.out.println(i^i);
        //5的二进制是00000101,所以取非即为11111010 即为-6
        System.out.println(~i);

        byte b  =6;
        //6的二进制是110
        System.out.println(Integer.toBinaryString(b));
        //6向左移1位后，变成1100，对应的10进制是12
        System.out.println(i<<b);
        //6向右移1位后，变成11，对应的10进制是3
        System.out.println(b>>1);

        /**
         * 示例
         */
        //不用*计算2x16  利用<<左移一位相当于乘以2的性质，不需要把16转成2进制，直接左移一位
        int n = 16;
        int m=n<<1;
        System.out.println("不用*计算2x16-->"+m);

        //带符号右移与无符号右移
        /**
         * 带符号右移 >>
         * 对于正数， 带符号右移 >> 会把所有的位右移，并在最前面补0
         * 对于负数， 带符号右移 >> 会把所有的位右移，并在最前面补1
         * 无符号右移>>>
         * 如果是一个负数，那么对应的二进制的第一位是1
         * 无符号右移>>>会把第一位的1也向右移动，导致移动后，第一位变成0
         * 这样就会使得负数在无符号右移后，得到一个正数
         *
         * 简单的说：
         * 带符号右移 >> 移动后正的还是正的，负的还是负的,符号不变
         * 无符号右移>>>移动后，变正的了
         */
        int p  =-10;
        //-10的二进制是11111111111111111111111111110110
        //第一位是1，即符号位，代表这是一个负数
        System.out.println(Integer.toBinaryString(p));
        //对于正数， 带符号右移 >> 会把所有的位右移，并在最前面补0
        //对于负数， 带符号右移 >> 会把所有的位右移，并在最前面补1
        //-10带符号右移1位，移动后前面补齐1
        //得到11111111111111111111111111111011
        //因为第一位是1，所以依然是一个负数，对应的十进制是-5
        int q = p>>1;
        System.out.println(Integer.toBinaryString(q));
        System.out.println(q);
        //-10无符号向右移1位，符号位也会向右移，第一位就变成了0
        //得到01111111111111111111111111111011，对应的十进制是2147483643
        int k = p>>>1;
        System.out.println(Integer.toBinaryString(k));
        System.out.println(k);


        //练习
        int x = 3; // 二进制是11
        int y = 2; // 二进制是10
        int c = ((x | y) ^ (x & y)) << 2 >>> 1;
        System.out.println(c);


    }
}

/**
 * 赋值操作符
 */
class COperator4{
    /**
     * =	赋值操作
     * 对本身进行操作并赋值:
     * +=
     * -=
     * *=
     * /=
     * %=
     * &=
     * |=
     * ^=
     * <<=
     * >>=
     * >>>=
     */
    public static void main(String[] args){
        /**
         * +=
         */
        int i = 1;
        //即i=i + ++i
        i+=++i;
        //输出3
        System.out.println(i);

        /**
         * <<= ：左移等 、
         * >>= ：右移等、
         * >>>= ：无符号右移等
         */
        //计算：3 << 2
        //3 << 2，则是将数字3左移2位
        //1、首先把3转换为二进制数字0000 0000 0000 0000 0000 0000 0000 0011
        //2、然后把该数字高位（左侧）的两个零移出，其他的数字都朝左平移2位，最后在低位（右侧）的两个空位补零。
        //3、则得到的最终结果是0000 0000 0000 0000 0000 0000 0000 1100，则转换为十进制是12。
        int a = 5;
        a <<= 1;//等价于 a = a << 1
        System.out.println(a);
        a >>= 1;//等价于 a = a >> 1
        System.out.println(a);
        a >>>= 1;//等价于 a = a >> 1
        System.out.println(a);
    }
}

/**
 * 三元操作符
 * ?:
 */
class COperator5{
    public static void main(String[] args){
        /**
         * 表达式?值1:值2
         * 如果表达式为真 返回值1
         * 如果表达式为假 返回值2
         */
        int i = 5;
        int j = 6;
        int k = i < j ? 99 : 88;
        System.out.println(k);
        // 相当于
        if (i < j) {
            k = 99;
        } else {
            k = 88;
        }
        System.out.println(k);
    }
}

