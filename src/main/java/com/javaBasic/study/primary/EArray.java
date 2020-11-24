package com.javaBasic.study.primary;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Arrays;

/**
 * 数组
 * 数组是一个固定长度的，包含了相同类型数据的 容器
 */
public class EArray {
    /**
     * 数组的创建
     */
    public static void main(String[] args) {
        /**
         * 声明一个数组
         * 创建一个数组
         */
        //声明一个引用
        int[] a;
        //创建一个长度是5的数组，并且使用引用a指向该数组
        a = new int[5];
        //声明的同时，指向一个数组
        int[] b = new int[5];

        /**
         * 访问数组
         */
        a[0]= 1;  //下标0，代表数组里的第一个数
        a[1]= 2;
        a[2]= 3;
        a[3]= 4;
        a[4]= 5;

        /**
         * 数组长度
         * .length属性用于访问一个数组的长度
         * 数组访问下标范围是0到长度-1
         */
        System.out.println(a.length); //打印数组的长度
        a[4]=100; //下标4，实质上是“第5个”，即最后一个
        //practice();
    }

    /**
     * 练习
     */
    public static void practice(){
        /**
         * 首先创建一个长度是5的数组
         * 然后给数组的每一位赋予随机整数
         * 通过for循环，遍历数组，找出最小的一个值出来
         *
         * 0-100的 随机整数的获取办法有多种，下面是参考办法之一:
         * (int) (Math.random() * 100)
         * Math.random() 会得到一个0-1之间的随机浮点数，然后乘以100，并强转为整型即可
         */
        int[] b=new int[5];
        //Math.random()产生一个double类型的0-1之间的随机小数
        b[0]=(int)(Math.random()*100);
        b[1]=(int)(Math.random()*100);
        b[2]=(int)(Math.random()*100);
        b[3]=(int)(Math.random()*100);
        b[4]=(int)(Math.random()*100);
        System.out.println(b[0]);
        System.out.println(b[1]);
        System.out.println(b[2]);
        System.out.println(b[3]);
        System.out.println(b[4]);
        //找出数组中的最小值
        int temp=b[0];//存放最小值
        System.out.println(temp);
        for (int i=0;i<b.length;i++){
            if (b[i]<temp) {
                temp=b[i];
            }
        }
        System.out.println(temp);

    }
}

/**
 * 初始化数组
 */
class Array1{
    public static void main(String[] args){
        /**
         * 分配空间与赋值分步进行
         */
        int[] a = new int[5]; //分配了长度是5的数组，但是没有赋值
        //没有赋值，那么就会使用默认值
        //作为int类型的数组，默认值是0
       // System.out.println(a[0]);
        practice1();
        //进行赋值
        a[0] = 100;
        a[1] = 101;
        a[2] = 103;
        a[3] = 120;
        a[4] = 140;

        /**
         * 分布空间与赋值同步进行
         */
        //写法一： 分配空间同时赋值
        int[] x = new int[]{100,102,444,836,3236};

        //写法二： 省略了new int[],效果一样
        int[] b = {100,102,444,836,3236};

        //写法三：同时分配空间，和指定内容
        //在这个例子里，长度是3，内容是5个，产生矛盾了
        //所以如果指定了数组的内容，就不能同时设置数组的长度
        //int[] c = new int[3]{100,102,444,836,3236};
    }

    /**
     * 练习
     */
    public static void practice1(){
        /**
         * 首先创建一个长度是5的数组,并填充随机数。
         * 使用for循环或者while循环，对这个数组实现反转效果
         */
        int[] p=new int[6];
        p[0]=(int)(Math.random()*100);
        p[1]=(int)(Math.random()*100);
        p[2]=(int)(Math.random()*100);
        p[3]=(int)(Math.random()*100);
        p[4]=(int)(Math.random()*100);
        p[5]=(int)(Math.random()*100);
        System.out.println(p[0]+" "+p[1]+" "+p[2]+" "+p[3]+" "+p[4]+" "+p[5]);
        int i=0;
        int temp=0;
        //对位交换法,倒转数组
        while (i<p.length/2){
            temp=p[i];
            p[i]=p[p.length-1-i];
            p[p.length-1-i]=temp;
            i++;
        }
        //整体输出数组
        System.out.println(Arrays.toString(p));
    }
}

/**
 * 数组·排序
 */
class Array2{
    public static void main(String[] args){
       // chose();
        //bubble();
        practice3();
    }
    /**
     * 选择法排序的思路：
     * 把第一位和其他所有的进行比较，只要比第一位小的，就换到第一个位置来
     * 比较完后，第一位就是最小的
     * 然后再从第二位和剩余的其他所有进行比较，只要比第二位小，就换到第二个位置来
     * 比较完后，第二位就是第二小的
     */
    public static void chose(){
        int[] a=new int[]{18,62,68,82,65,9};

        for (int i=0;i<a.length;i++){
            for (int j=i+1;j<a.length;j++){
                if(a[j]<a[i]){
                    int temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
    /**
     * 冒泡排序
     * 冒泡法排序的思路：
     * 第一步：从第一位开始，把相邻两位进行比较
     * 如果发现前面的比后面的大，就把大的数据交换在后面，循环比较完毕后，最后一位就是最大的
     * 第二步： 再来一次，只不过不用比较最后一位
     */
    public static void bubble(){
        int[] a=new int[]{18,62,68,82,65,9};
        for(int j=0;j<a.length;j++) {
            //每一趟就有一个数到最终位置，所以可以收缩边界
            for (int i = 0; i < a.length - 1-j; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
    /**
     * 练习
     * 首先创建一个长度是5的数组,并填充随机数。 (向数组填充随机数的办法，参考这里)
     * 首先用选择法正排序，然后再对其使用冒泡法倒排序
     * 注 所谓的正排序就是从小到大排序，倒排序就是从大到小排序
     *  步骤 4 : 答案-排序
     */
    public static void practice3(){
       int[] a=new int[5];
       a[0]=(int)(Math.random()*100);
       a[1]=(int)(Math.random()*100);
       a[2]=(int)(Math.random()*100);
       a[3]=(int)(Math.random()*100);
       a[4]=(int)(Math.random()*100);
        /**
         * 选择法正排序(从小到大)
         */
       System.out.println("原始数组-->"+Arrays.toString(a));
        for(int i=0;i<a.length;i++){
            for (int j=i+1;j<a.length;j++){
                if(a[j]<a[i]){
                    int temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }
        System.out.println("选则法正序排列-->"+Arrays.toString(a));
        /**
         * 冒泡法逆序排列
         */
        for(int i=0;i<a.length;i++){
            for (int j=0;j<a.length-1-i;j++){
                if(a[j]<a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        System.out.println("冒泡法逆序排列-->"+Arrays.toString(a));
    }
}

/**
 * 增强型for循环
 */
class Array3{
    /**
     * 增强型for循环在遍历一个数组的时候会更加快捷
     * 注：增强型for循环只能用来取值，却不能用来修改数组里的值
     * @param args
     */
    public static void main(String[] args){
        int values [] = new int[]{18,62,68,82,65,9};
        //常规遍历
        for (int i = 0; i < values.length; i++) {
            int each = values[i];
            //System.out.println(each);
        }

        //增强型for循环遍历
        for (int each : values) {
           // System.out.println(each);
        }
        practice4();
    }

    /**
     * 练习
     * 用增强型for循环找出最大的那个数
     */
    public static void practice4(){
        int[] a = new int[]{18,62,68,82,65,9};
        int max=a[0];
        for (int v1:a){
           if(v1>max){
               max=v1;
           }
        }
        System.out.println(max);
    }
}

/**
 *复制数组
 * 将两个数组复制到第三个数组，相当于合并这两个数组
 */
class Array4{
    /**
     * 数组的长度是不可变的，一旦分配好空间，是多长，就多长，不能增加也不能减少
     * @param args
     */
    public static void main(String[] args){
        /**
         * 把一个数组的值，复制到另一个数组中
         * System.arraycopy(src, srcPos, dest, destPos, length)
         * src: 源数组
         * srcPos: 从源数组复制数据的起始位置
         * dest: 目标数组
         * destPos: 复制到目标数组的起始位置
         * length: 复制的长度
         */
        int[] a=new int[]{18,62,68,82,65,9};//长度为6，目标数组长度为5
        int[] b=new int[5];
        System.arraycopy(a, 0, b, 0, 5);
        //System.out.println(Arrays.toString(b));
        practice5();
    }
    /**
     * 练习
     * 首先准备两个数组，他俩的长度是5-10之间的随机数，并使用随机数初始化这两个数组
     * 然后准备第三个数组，第三个数组的长度是前两个的和
     * 通过System.arraycopy 把前两个数组合并到第三个数组中
     */
    public static void practice5(){
        int[] a=new int[]{18,62,68,82};//长度为4，目标数组长度为5
        int[] b=new int[]{27,19,4};//长度为3
        int[] c=new int[a.length+b.length];
        System.arraycopy(a,0,c,0,a.length);
        System.arraycopy(b,0,c,a.length,b.length);
        System.out.println(Arrays.toString(c));
    }
}

/**
 * 二维数组
 */
class Array5{
    /**
     * 这是一个一维数组, 里面的每一个元素，都是一个基本类型int
     *
     * int a[] =new int[]{1,2,3,4,5};
     *
     *
     * 这是一个二维数组，里面的每一个元素，都是一个一维数组
     * 所以二维数组又叫数组的数组
     *
     * int b[][] = new int[][]{
     *    {1,2,3},
     *    {4,5,6},
     *    {7,8,9}
     * };
     */
    public static void main(String[] args) {
        //初始化二维数组，
        int[][] a = new int[2][3]; //有两个一维数组，每个一维数组的长度是3
        a[1][2] = 5;  //可以直接访问一维数组，因为已经分配了空间

        //只分配了二维数组
        int[][] b = new int[2][]; //有两个一维数组，每个一维数组的长度暂未分配
        b[0]  =new int[3]; //必须事先分配长度，才可以访问，给第一个一维数组分配长度为3的空间
        b[0][2] = 5;

        //指定内容的同时，分配空间
        int[][] c = new int[][]{
                {1,2,4},
                {4,5},
                {6,7,8,9}
        };
    }
}

/**
 * Arrays
 * Arrays是针对数组的工具类，可以进行 排序，查找，复制填充等功能。 大大提高了开发人员的工作效率。
 */
class Array6{
    /**
     * copyOfRange	数组复制
     * toString()	转换为字符串
     * sort	        排序
     * binarySearch	搜索
     * equals	    判断是否相同
     * fill	        填充
     */
    public static void main(String[] args){
        /**
         * 数组复制:
         * 与使用System.arraycopy进行数组复制类似的， Arrays提供了一个copyOfRange方法进行数组复制。
         * 不同的是System.arraycopy，需要事先准备好目标数组，并分配长度。 copyOfRange 只需要源数组就就可以了，通过返回值，就能够得到目标数组了。
         * 除此之外，需要注意的是 copyOfRange 的第3个参数，表示源数组的结束位置，是取不到的。
         */
        int a[] = new int[] { 18, 62, 68, 82, 65, 9 };

        // copyOfRange(int[] original, int from, int to)
        // 第一个参数表示源数组
        // 第二个参数表示开始位置(取得到)
        // 第三个参数表示结束位置(取不到)
        int[] b = Arrays.copyOfRange(a, 0, 3);//只复制了三个数
        //转换为字符串输出
        System.out.println(Arrays.toString(b));

        /**
         * 转换为字符串
         */
        int c[] = new int[] { 18, 62, 68, 82, 65, 9 };
        String content = Arrays.toString(c);
        System.out.println(content);

        /**
         * sort排序
         */
        int d[] = new int[] { 18, 62, 68, 82, 65, 9 };
        System.out.println("排序之前 :");
        System.out.println(Arrays.toString(d));
        Arrays.sort(d);
        System.out.println("排序之后:");
        System.out.println(Arrays.toString(d));

        /**
         * binarySearch
         * 搜索
         * 查询元素出现的位置
         * 需要注意的是，使用binarySearch进行查找之前，必须使用sort进行排序
         * 如果数组中有多个相同的元素，查找结果是不确定的
         */
        int e[] = new int[] { 18, 62, 68, 82, 65, 9 };
        Arrays.sort(a);
        System.out.println(Arrays.toString(e));
        //使用binarySearch之前，必须先使用sort进行排序
        System.out.println("数字 62出现的位置:"+Arrays.binarySearch(e ,62));

        /**
         * 判断是否相同
         */
        int a1[] = new int[] { 18, 62, 68, 82, 65, 9 };
        int b1[] = new int[] { 18, 62, 68, 82, 65, 8 };
        System.out.println(Arrays.equals(a1, b1));

        /**
         * 填充
         * 使用同一个值，填充整个数组
         */
        int x[] = new int[10];
        Arrays.fill(x, 5);//只能一维
        System.out.println(Arrays.toString(x));
        practice6();
    }

    /**
     * 练习·二维数组排序
     * 首先定义一个5X8的二维数组，然后使用随机数填充满。
     * 借助Arrays的方法对二维数组进行排序。
     * 参考思路：
     * 先把二维数组使用System.arraycopy进行数组复制到一个一维数组
     * 然后使用sort进行排序
     * 最后再复制回到二维数组。
     */
    public static void practice6(){
        int a[][] = new int[5][8];
        //以随机数填充二维数组,a.length=5(即二维数组中一维数组的个数）
        for (int i = 0; i < a.length; i++) {
            //a[i].length即每个一维数组的长度,不能用fill填充
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = (int) (Math.random() * 100);
            }
        }

        System.out.println("打印二维数组");
        for (int[] i : a) {
            System.out.println(Arrays.toString(i));
        }

        // 把二维数组复制到一维数组（数组长度为二维数组元素个数）
        int b[] = new int[a.length * a[0].length];
        for (int i = 0; i < a.length; i++) {
            //
            System.arraycopy(a[i], 0, b, i * a[i].length, a[i].length);
        }
        // 对一维数组进行排序
        Arrays.sort(b);
        // 把一维数组复制到二维数组
        for (int i = 0; i < a.length; i++) {
            System.arraycopy(b, a[i].length * i, a[i], 0, a[i].length);
        }
        System.out.println("打印排序后的二维数组");
        for (int[] i : a) {
            System.out.println(Arrays.toString(i));
        }
    }
}
