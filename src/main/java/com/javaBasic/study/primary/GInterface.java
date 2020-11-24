package com.javaBasic.study.primary;

/**
 * 接口与继承
 */
public class GInterface {
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度
}

/**
 * 创建一个接口 File->New->Interface
 * AD ，声明一个方法 physicAttack 物理攻击，但是没有方法体，是一个“空”方法
 */
interface AD {
    /**
     * 在设计LOL的时候，进攻类英雄有两种，一种是进行物理系攻击，一种是进行魔法系攻击
     * 这时候，就可以使用接口来实现这个效果。
     * 接口就像是一种约定，我们约定某些英雄是物理系英雄，那么他们就一定能够进行物理攻击
     */
    //物理伤害
     void physicAttack();
}

/**
 * 魔法攻击接口
 */
interface AP {
    //魔法伤害
     void magicAttack();
}

/**
 *设计一类英雄，能够使用物理攻击，这类英雄在LOL中被叫做AD
 * 类：ADHero
 * 继承了GInterface 类，所以继承了name,hp,armor等属性
 *
 * 实现某个接口，就相当于承诺了某种约定
 *
 * 所以，实现了AD这个接口，就必须提供AD接口中声明的方法physicAttack()
 * 实现在语法上使用关键字 implements
 */
class ADHero extends GInterface implements AD{

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }
}

/**
 * 魔法攻击类英雄
 */
class APHero extends GInterface implements AP{
    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }

}

/**
 * ADAPHero类英雄同时具有物理/魔法攻击
 */
class ADAPHero extends GInterface implements AD,AP{

    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }
    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }
}
/**------------------------------------------------------------------------*/

/**
 * 对象转型
 */
class ObjectChange{
    /**
     * 明确引用类型与对象类型的概念顶折纠问
     * 首先，明确引用类型与对象类型的概念
     * 在这个例子里，有一个对象 new ADHero(), 同时也有一个引用ad
     * 对象是有类型的， 是ADHero
     * 引用也是有类型的，是ADHero
     * 通常情况下，引用类型和对象类型是一样的
     * 接下来要讨论的类型转换的问题，指的是引用类型和对象类型不一致的情况下的转换问题
     */

    /**
     * 子类转父类(向上转型)：
     * 所谓的转型，是指当引用类型和对象类型不一致的时候，才需要进行类型转换
     * 类型转换有时候会成功，有时候会失败(参考基本类型的类型转换)
     * 所有的子类转换为父类，都是说得通的。比如你身边的例子
     * 苹果手机 继承了 手机，把苹果手机当做普通手机使用
     * 怡宝纯净水 继承了 饮品， 把怡宝纯净水 当做饮品来使用
     */

    /**
     * 父类转子类(向下转型)：
     * 父类转子类，有的时候行，有的时候不行，所以必须进行强制转换。
     * 强制转换的意思就是 转换有风险，风险自担
     * 无法转换时抛出ClassCastException类型转换异常
     */

    /**
     * 没有直接继承关系的两个类，互相转换：
     * 没有继承关系的两个类，互相转换，一定会失败
     */

    /**
     * 实现类转换成接口(向上转型)：
     * 引用ad指向的对象是ADHero类型，这个类型实现了AD接口
     * 把一个ADHero类型转换为AD接口
     * 从语义上来讲，把一个ADHero当做AD来使用，而AD接口只有一个physicAttack方法，
     * 这就意味着转换后就有可能要调用physicAttack方法，而ADHero一定是有physicAttack方法的，
     * 转换是能成功的。
     */

    /**
     *接口转换成实现类
     * 转换失败
     */

    public static void main(String[] args) {
        /**
         * instanceof Hero 判断一个引用所指向的对象，是否是Hero类型，或者Hero的子类
         */
        ADHero ad = new ADHero();
        APHero ap = new APHero();

        GInterface h1= ad;
        GInterface h2= ap;

        //判断引用h1指向的对象，是否是ADHero类型
        System.out.println(h1 instanceof ADHero);

        //判断引用h2指向的对象，是否是APHero类型
        System.out.println(h2 instanceof APHero);

        //判断引用h1指向的对象，是否是GInterface的子类型
        System.out.println(h1 instanceof GInterface);

        AD ad1;
        ADHero adHero=new ADHero();
        //接口不能向下转换成实现类
        //adHero=ad1;
        //实现类可以向上转换成接口
        ad1=adHero;

    }
}

/**-------------------------------------------------------------------------------*/

/**
 * 重写
 */
class override{
    /**
     *子类可以继承父类的对象方法
     * 在继承后，重复提供该方法，就叫做方法的重写
     * 又叫覆盖 override
     */
    String name;
    int price;

    public void buy(){
        System.out.println("购买");
    }
    public void effect() {
        System.out.println("物品使用后，可以有效果");
    }
    public static void main(String[] args) {
        override i = new override();
        i.effect();

        //LifePotion中effect方法已经被重写，所以输出不同结果
        LifePotion lp =new LifePotion();
        lp.effect();
        /**
         * 如果没有重写这样的机制，也就是说LifePotion这个类，一旦继承了Item，所有方法都不能修改了。
         * 但是LifePotion又希望提供一点不同的功能，为了达到这个目的，只能放弃继承Item,重新编写所有的属性和方法，
         * 然后在编写effect的时候，做一点小改动.
         * 这样就增加了开发时间和维护成本
         */
    }
}

/**
 * 子类LifePotion继承父类override
 */
class LifePotion extends override{
    //子类LifePotion继承override,同时也提供了方法effect
    public void effect(){
        System.out.println("血瓶使用后，可以回血");
    }

}
/**----------------------------------------------------------------------------------------------*/

/**
 * 多态
 * 多态: 都是同一个类型，调用同一个方法，却能呈现不同的状态
 */
class Polymorphism{
    /**
     * 观察类的多态现象：
     * 1. i1和i2都是Item类型
     * 2. 都调用effect方法
     * 3. 输出不同的结果
     */
    String name;
    int price;

    public void buy(){
        System.out.println("购买");
    }
    public void effect() {
        System.out.println("物品使用后，可以有效果 ");
    }

    /**
     * 类的多态条件顶折纠问
     * 要实现类的多态，需要如下条件
     * 1. 父类（接口）引用指向子类对象
     * 2. 调用的方法有重写
     * @param args
     */
    public static void main(String[] args) {
        //多态: 都是同一个类型，调用同一个方法，却能呈现不同的状态
        //类的多态，父类引用指向子类对象
        Polymorphism i1= new LifePotion1();
        Polymorphism i2 = new MagicPotion();
        System.out.print("i1  是Polymorphism类型，执行effect打印:");
        i1.effect();
        System.out.print("i2也是Polymorphism类型，执行effect打印:");
        i2.effect();
        /**
         * 如果不使用多态，
         * 假设英雄要使用血瓶和魔瓶，就需要为Hero设计两个方法
         * useLifePotion
         * useMagicPotion
         *
         * 除了血瓶和魔瓶还有很多种物品，那么就需要设计很多很多个方法，比如
         * usePurityPotion 净化药水
         * useGuard 守卫
         * useInvisiblePotion 使用隐形药水
         * 等等等等
         */
    }
}

/**
 * 子类 LifePotion1 继承 Polymorphism 并重写effect方法
 */
class LifePotion1 extends Polymorphism {
    public void effect(){
        System.out.println("血瓶使用后，可以回血");
    }
}
/**
 * 子类MagicPotion 继承 Polymorphism 并重写effect方法
 */
class MagicPotion extends Polymorphism{

    public void effect(){
        System.out.println("蓝瓶使用后，可以回魔法");
    }
}

/**-------------------------------------------------------------------------------------*/

/**
 * 隐藏
 * 与重写类似，方法的重写是子类覆盖父类的对象方法
 * 隐藏，就是子类覆盖父类的类方法
 */
class Hide{

}