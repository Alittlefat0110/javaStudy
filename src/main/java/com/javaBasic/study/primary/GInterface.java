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
    public String name;
    protected float hp;

    //类方法，静态方法
    //通过类就可以直接调用
    public static void battleWin(){
        System.out.println("hero battle win");
    }
}

/**
 * 子类隐藏父类的类方法（静态方法）
 */
class ChildHide extends Hide implements AD{

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    //隐藏父类的battleWin方法
    public static void battleWin(){
        System.out.println("ad hero battle win");
    }

    public static void main(String[] args) {
        Hide.battleWin();
        ChildHide.battleWin();
    }
}
/**------------------------------------------------------------------------------*/

/**
 * super关键字
 */
class Super{
    /**
     * 准备显式提供无参构造方法的父类Super
     * 在实例化Super对象的时候，其构造方法会打印
     * “Super的构造方法 "
     */
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public void useItem(Polymorphism i){
        System.out.println("Super use Polymorphism");
        i.effect();
    }

    public Super(){
        System.out.println("Super的构造方法 ");
    }

    public static void main(String[] args) {
        new Super();
    }
}

/**
 * 实例化子类，父类的构造方法一定会被调用
 */
class ChildSuper extends Super implements AD{

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    public ChildSuper(){

        System.out.println("AD Hero的构造方法");
    }

    public static void main(String[] args) {
        /**
         * 实例化一个ChildSuper(), 其构造方法会被调用
         * 其父类的构造方法也会被调用
         * 并且是父类构造方法先调用
         * 子类构造方法会默认调用父类的 无参的构造方法
         */
        new ChildSuper();

    }
}

/**
 * 父类显式提供两个构造方法顶折纠问
 * 分别是无参的构造方法和带一个参数的构造方法
 */
class Hero {

    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public void useItem(Polymorphism i){
        System.out.println("hero use item");
        i.effect();
    }

    /**
     * 当父类没有无参构造方法的时候( 提供了有参构造方法，并且不显示提供无参构造方法)，
     * 子类就会抛出异常，因为它尝试去调用父类的无参构造方法
     * 此时子类可以通过super调用调用父类的带参构造方法（如ChildHero子类中所示）
     */
    public Hero(){
        System.out.println("Hero的无参的构造方法 ");
    }

    public Hero(String name){
        System.out.println("Hero的有一个参数的构造方法 ");
        this.name = name;
    }

    public static void main(String[] args) {
        new Hero();
    }
}

/**
 * 子类显式调用父类带参构造方法
 * 使用关键字super 显式调用父类带参的构造方法
 */
class ChildHero extends Hero implements AD{

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }
    public ChildHero(String name){
        super(name);//若没有这句，创建子类实例时就会默认调用父类的无参构造方法
        System.out.println("AD Hero的构造方法");
    }

    public static void main(String[] args) {
        new ChildHero("德莱文");
    }
}

/**
 * 调用父类属性
 * 通过super调用父类的moveSpeed属性
 * ChildHero1也提供了属性moveSpeed
 */
class ChildHero1 extends Hero implements AD{
    int moveSpeed=400; //移动速度

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    public int getMoveSpeed(){
        //子类自身定义的moveSpeed属性
        return this.moveSpeed;
    }

    public int getMoveSpeed2(){
        //super调用父类moveSpeed属性
        return super.moveSpeed;
    }

    public static void main(String[] args) {
        //创建实例时必调用父类无参构造方法
        ChildHero1 h= new ChildHero1();

        System.out.println(h.getMoveSpeed());
        System.out.println(h.getMoveSpeed2());

    }
}

/**
 * 调用父类方法
 */
class ChildHero2 extends Hero implements AD{
    int moveSpeed = 400; // 移动速度

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    public int getMoveSpeed2() {
        return super.moveSpeed;
    }

    // 重写useItem，并在其中调用父类的userItem方法
    public void useItem(Polymorphism i) {
        System.out.println("adHero use item");
        super.useItem(i);
    }

    public static void main(String[] args) {
        ADHero h = new ADHero();
        LifePotion lp = new LifePotion();

    }
}
/**---------------------------------------------------------------------------*/

/**
 * Object类
 * Object类是所有类的父类
 * 声明一个类的时候，默认是继承了Object
 */
class object extends Object{
    public String name;
    protected float hp;

    //重写Object类中的toString方法
    public String toString(){
        return name;
    }

    public static void main(String[] args) {
/**
 * Object类提供一个toString方法，所以所有的类都有toString方法
 * toString()的意思是返回当前对象的字符串表达
 * 通过 System.out.println 打印对象就是打印该对象的toString()返回值
 */
        object h = new object();
        h.name = "盖伦";
        //此处toString已经被重写
        System.out.println(h.toString());
        //直接打印对象就是打印该对象的toString()返回值
        System.out.println(h);

        /**
         * finalize()
         * 当一个对象没有任何引用指向的时候，它就满足垃圾回收的条件
         * 当它被垃圾回收的时候，它的finalize() 方法就会被调用。
         * finalize() 不是开发人员主动调用的方法，而是由虚拟机JVM调用的。
         */
        //只有一引用
        Hero h1;
        for (int i = 0; i < 100000; i++) {
            //不断生成新的对象
            //每创建一个对象，前一个对象，就没有引用指向了
            //那些对象，就满足垃圾回收的条件
            //当，垃圾堆积的比较多的时候，就会触发垃圾回收
            //一旦这个对象被回收，它的finalize()方法就会被调用
            h1 = new Hero();//创建一万次对象，调用一万次无参构造方法，且每个对象创建时的引用都是h1
        }
    }
    /**
     * Object提供的equals()比较两个值是否相等
     * 以下在后续中学习：
     * hashCode方法返回一个对象的哈希值
     * getClass()会返回一个对象的类对象
     * Object还提供线程同步相关方法：
     * wait()
     * notify()
     * notifyAll()
     */
}

/**---------------------------------------------------------------------------*/

/**
 * final关键字
 *
 */
final class WordFinal{
    /**
     * final修饰类
     * 当WordFinal被修饰成final的时候，表示WordFinal不能够被继承
     * 其子类会出现编译错误
     */
    String name; //姓名
    float hp; //血量

    /**
     * final修饰方法
     * WordFinal的useItem方法被修饰成final,那么该方法在子类中，不能够被重写
     */
    public final void useItem(){
        System.out.println("hero use item");
    }

    /**
     * final修饰基本类型变量顶折纠问
     * final修饰基本类型变量，表示该变量只有一次赋值机会
     */
    final int hp2=3;
    //hp2 = 5;不能再赋值

    public static void main(String[] args) {
        /**
         * final修饰引用
         * h引用被修饰成final，表示该引用只有1次指向对象的机会
         * 但是，依然通过h引用修改对象的属性值hp，因为hp并没有final修饰
         */
        final Hero h;
        h  =new Hero();
        //h  =new Hero();h这个引用不能再指向一个新的对象
        h.hp = 5;
    }
    /**
     * 常量
     * 常量指的是可以公开，直接访问，不会变化的值
     */
    public static final int ITEM = 6;
}

/**-----------------------------------------------------------------------*/

/**
 * 抽象类
 * 在类中声明一个方法，这个方法没有实现体，是一个“空”方法
 * 这样的方法就叫抽象方法，使用修饰符“abstract"
 * 当一个类有抽象方法的时候，该类必须被声明为抽象类
 */
abstract class AbstractClass{
    String name;

    float hp;

    float armor;

    int moveSpeed;

    public static void main(String[] args) {

    }

    // 抽象方法attack
    // Hero的子类会被要求实现attack方法
    public abstract void attack();
}

/**
 * 抽象类可以没有抽象方法
 */
abstract class Hero1 {
    String name;

    float hp;

    float armor;

    int moveSpeed;

    public static void main(String[] args) {
        //虽然没有抽象方法，但是一旦被声明为了抽象类，就不能够直接被实例化
        Hero h = new Hero();
    }
}

/**
 * 抽象类和接口的区别
 * 区别1：
 * 子类只能继承一个抽象类，不能继承多个
 * 子类可以实现多个接口
 * 区别2：
 * 抽象类可以定义
 * public,protected,package,private
 * 静态和非静态属性
 * final和非final属性
 * 但是接口中声明的属性，只能是
 * public
 * 静态
 * final的
 * 即便没有显式的声明
 *
 * 注: 抽象类和接口都可以有实体方法。 接口中的实体方法，叫做默认方法
 */
 interface AP1 {

    public static final int resistPhysic = 100;

    //resistMagic即便没有显式的声明为 public static final
    //但依然默认为public static final
    int resistMagic = 0;

    public void magicAttack();
}
/**-------------------------------------------------------------------------*/

/**
 * 内部类
 */
class InnerClass{
    private String name; // 姓名

    float hp; // 血量

    float armor; // 护甲

    int moveSpeed; // 移动速度

    /**
     * 非静态内部类 BattleScore “战斗成绩”
     * 非静态内部类可以直接在一个类里面定义
     * 比如：
     * 战斗成绩只有在一个英雄对象存在的时候才有意义
     * 所以实例化BattleScore 的时候，必须建立在一个存在的英雄的基础上
     * 语法: new 外部类()  .new 内部类()
     * 作为InnerClass的非静态内部类，是可以直接访问外部类的private实例属性name的
     */
    // 非静态内部类，只有一个外部类对象存在的时候，才有意义
    // 战斗成绩只有在一个英雄对象存在的时候才有意义
    class BattleScore {
        int kill;
        int die;
        int assit;

        public void legendary() {
            if (kill >= 8)
                System.out.println(name + "超神！");
            else
                System.out.println(name + "尚未超神！");
        }
    }

    public static void main(String[] args) {
        InnerClass garen = new InnerClass();
        garen.name = "盖伦";
        // 实例化内部类
        // BattleScore对象只有在一个英雄对象存在的时候才有意义
        // 所以其实例化必须建立在一个外部类对象的基础之上
        BattleScore score = garen.new BattleScore();
        score.kill = 9;
        score.legendary();
    }
}

/**
 * 静态内部类顶折纠问
 * 在一个类里面声明一个静态内部类
 * 比如敌方水晶，当敌方水晶没有血的时候，己方所有英雄都取得胜利，而不只是某一个具体的英雄取得胜利。
 * 与非静态内部类不同，静态内部类水晶类的实例化 不需要一个外部类的实例为基础，可以直接实例化
 * 语法：new 外部类.静态内部类();
 * 因为没有一个外部类的实例，所以在静态内部类里面不可以访问外部类的实例属性和方法
 * 除了可以访问外部类的私有静态成员外，静态内部类和普通类没什么大的区别
 */
class InnerClass1{
    public String name;
    protected float hp;

    private static void battleWin(){
        System.out.println("battle win");
    }

    //敌方的水晶
    static class EnemyCrystal{
        int hp=5000;

        //如果水晶的血量为0，则宣布胜利
        public void checkIfVictory(){
            if(hp==0){
                InnerClass1.battleWin();

                //静态内部类不能直接访问外部类的对象属性,即name属性无法访问
                //System.out.println(name + " win this game");
            }
        }
    }

    public static void main(String[] args) {
        //实例化静态内部类
        InnerClass1.EnemyCrystal crystal = new InnerClass1.EnemyCrystal();
        crystal.checkIfVictory();
    }
}

/**
 * 匿名类顶折纠问
 * 匿名类指的是在声明一个类的同时实例化它，使代码更加简洁精练
 * 通常情况下，要使用一个接口或者抽象类，都必须创建一个子类
 *
 * 有的时候，为了快速使用，直接实例化一个抽象类，并“当场”实现其抽象方法。
 * 既然实现了抽象方法，那么就是一个新的类，只是这个类，没有命名。
 * 这样的类，叫做匿名类
 */
abstract class InnerClass2{
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public abstract void attack();

    public static void main(String[] args) {

        ADHero adh=new ADHero();
        //通过打印adh，可以看到adh这个对象属于ADHero类
        adh.physicAttack();
        System.out.println(adh);

        InnerClass2 h = new InnerClass2(){
            //当场实现attack方法
            public void attack() {
                System.out.println("新的进攻手段");
            }
        };
        h.attack();
        //通过打印h，可以看到h这个对象属于Hero$1这么一个系统自动分配的类名

        System.out.println(h);
    }
}

/**
 * 本地类顶折纠问
 * 本地类可以理解为有名字的匿名类
 * 内部类与匿名类不一样的是，内部类必须声明在成员的位置，即与属性和方法平等的位置。
 * 本地类和匿名类一样，直接声明在代码块里面，可以是主方法，for循环里等等地方
 */
abstract class InnerClass3{
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public abstract void attack();

    public static void main(String[] args) {

        //与匿名类的区别在于，本地类有了自定义的类名
        class SomeHero extends InnerClass3{
            public void attack() {
                System.out.println( name+ " 新的进攻手段");
            }
        }

        SomeHero h  =new SomeHero();
        h.name ="地卜师";
        h.attack();

        //在匿名类中使用外部的局部变量，外部的局部变量必须修饰为final
        final int damage = 5;

        InnerClass3 h1 = new InnerClass3(){
            public void attack() {
                System.out.printf("新的进攻手段，造成%d点伤害",damage );
            }
        };
    }

}
/**----------------------------------------------------------------------------------*/

/**
 * 默认方法：
 * 默认方法是JDK8新特性，指的是接口也可以提供具体方法了，而不像以前，只能提供抽象方法
 * DefaultMethod 这个接口，增加了一个默认方法 revive，这个方法有实现体，并且被声明为了default
 */
interface DefaultMethod{
     void die();

    default  void revive() {
        System.out.println("本英雄复活了");
    }
    /**
     * 为什么会有默认方法顶折纠问
     * 假设没有默认方法这种机制，那么如果要为DefaultMethod增加一个新的方法revive,那么所有实现了Mortal接口的类，都需要做改动。
     *
     * 但是引入了默认方法后，原来的类，不需要做任何改动，并且还能得到这个默认方法
     *
     * 通过这种手段，就能够很好的扩展新的类，并且做到不影响原来的类
     */
}
/**---------------------------------------------------------------------------------------------*/

/**
 * 数字和字符串
 */
class NumberString{
    /**
     * 装箱/拆箱
     * @param args
     */
    public static void main(String[] args){
        /**
         * 封装类
         * 所有的基本类型，都有对应的类类型
         * 比如int对应的类是Integer
         * 这种类就叫做封装类
         */
            int i = 5;
            //把一个基本类型的变量,转换为Integer对象
            Integer it = new Integer(i);
            //把一个Integer对象，转换为一个基本类型的int
            int i2 = it.intValue();
        /**
         * Number类顶折纠问
         * 数字封装类有
         * Byte,Short,Integer,Long,Float,Double
         * 这些类都是抽象类Number的子类
         */
        //Integer是Number的子类，所以打印true
        System.out.println(it instanceof Number);

        //基本类型转换成封装类型
        Integer it1 = new Integer(i);
        //封装类型转换成基本类型
        int i3 = it.intValue();

        /**
         * 自动装箱:
         * 不需要调用构造方法，通过=符号自动把 基本类型 转换为 类类型 就叫装箱
         */
        //基本类型转换成封装类型,自动转换就叫装箱
        Integer it2 = i;

        /**
         * 自动拆箱:
         * 不需要调用Integer的intValue方法，通过=就自动转换成int类型，就叫拆箱
         */
        //封装类型转换成基本类型,自动转换就叫拆箱
        int i4 = it;

    }
}

/**
 * 字符串转换
 */
class change{
    public static void main(String[] args){
        /**
         * 数字转字符串顶折纠问
         * 方法1： 使用String类的静态方法valueOf
         * 方法2： 先把基本类型装箱为对象，然后调用对象的toString
         */
        int i = 5;
        //方法1
        String str = String.valueOf(i);
        //方法2
        Integer it = i;
        String str2 = it.toString();

        /**
         * 字符串转数字
         * 调用Integer的静态方法parseInt
         */
        String str1 = "999";
        int i1= Integer.parseInt(str1);
        System.out.println(i);
    }
}

/**
 * 数学方法MATH
 * java.lang.Math提供了一些常用的数学运算方法，并且都是以静态方法的形式存在
 */
class math{
    public static void main(String[] args) {
//        float f1 = 5.4f;
//        float f2 = 5.5f;
//        //5.4四舍五入即5
//        System.out.println(Math.round(f1));
//        //5.5四舍五入即6
//        System.out.println(Math.round(f2));
//
//        //得到一个0-1之间的随机浮点数（取不到1）
//        System.out.println(Math.random());
//
//        //得到一个0-10之间的随机整数 （取不到10）
//        System.out.println((int)( Math.random()*10));
//        //开方
//        System.out.println(Math.sqrt(9));
//        //次方（2的4次方）
//        System.out.println(Math.pow(2,4));
//
//        //π
//        System.out.println(Math.PI);
//
//        //自然常数
//        System.out.println(Math.E);

        //System.out.println(practice());
//        int max = 10000*1000;
//        int count = 0 ;
//        for (int i = 1; i <max; i+=2) {
//            if(isPrime(i)){
//                count++;
//            }
//        }
//        //664579
//        System.out.println("一千万以内的质数一共有 : " + count);
        System.out.println(method3());
    }

    /**
     * 统计一千万以内有多少个质数
     * 质数：因子只有1和自身
     */
    public static int practice(){
        int count=0;
        for (int i=1;i<10000000;i+=2){
            Boolean B=false;
            for (int j=2;j<=Math.sqrt(i);j++) {
                if (i % j == 0) {
                    B=false;
                    break;
                }else {
                    B = true;
                }

            }
            if (B==true){
                count++;
                System.out.println(i);
            }
        }
        //664577
        return count;
    }
    private static boolean isPrime(int i) {
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if(0==i%j)
                return false;
        }
        return true;
    }

    private static int method3(){
        int max_value = (int)Math.pow(10, 7);
        System.out.println(max_value);
        int sum = 0; //统计质数个数
         /*boolean 数组初始化全是false，而且只占一个bit，占用空间很小
          所以用筛选法将合数全部置true，剩下的false就是质数，最后统计false数量即可*/

        boolean judge[] = new boolean[max_value];
        judge[1] = true;   //0这个位置不要，1的位置代表数字1，2的位置代表数字2，以此类推
        judge[2] = true;
        judge[3] = true;
        for (int i = 2; i < max_value; i++)
        {
            if (!judge[i])   // 如果是质数，就把他的倍数都置true
            {
                sum +=1;     //当场统计
                for (int j = 2; j*i <max_value; j++)
                    judge[i*j] = true;//i*j最小为4所以前面先设置1，2，3为true
            }

        }
        return sum;

    }
}

/**
 * 操纵字符串
 */
class ControlString{
    /**
     * 关键字	          简介
     * charAt	          获取字符
     * toCharArray	      获取对应的字符数组
     * subString	      截取子字符串
     * split	          分隔
     * trim	              去掉首尾空格
     * toLowerCase        大写变小写
     * toUpperCase	      小写变大写
     * indexOf            定位
     * lastIndexOf        定位
     * contains	          定位
     * replaceAll         替换
     * replaceFirst	      替换
     */
    public static void main(String[] args) {

        String sentence = "盖伦,在进行了连续8次击杀后,获得了 超神 的称号";
        //获取字符
        char c = sentence.charAt(0);
        //获取对应的字符数组
        char[] cs = sentence.toCharArray();
        //截取从第3个开始的字符串 （基0）
        //到5-1的位置的字符串
        //左闭右开
        String subString2 = sentence.substring(3,5);
        //根据,进行分割，得到3个子字符串
        String subSentences[] = sentence.split(",");
        //去掉首尾空格
        sentence.trim();
        String sentence1 = "Garen";
        //全部变成小写
        System.out.println(sentence1.toLowerCase());
        //全部变成大写
        System.out.println(sentence1.toUpperCase());
        //字符第一次出现的位置
        System.out.println(sentence.indexOf('8'));
        //字符串第一次出现的位置
        System.out.println(sentence.indexOf("超神"));
        //字符串最后出现的位置
        System.out.println(sentence.lastIndexOf("了"));
        //从位置5开始，出现的第一次,的位置
        System.out.println(sentence.indexOf(',',5));
        //是否包含字符串"击杀"
        System.out.println(sentence.contains("击杀"));
        //替换所有的
        String temp = sentence.replaceAll("击杀", "被击杀");
        temp = temp.replaceAll("超神", "超鬼");
        //只替换第一个
        temp = sentence.replaceFirst(",","");
    }

}

/**
 * 比较字符串
 */
class CompereString{
    public static void main(String[] args) {
        /**
         * 是否是同一个对象
         * str1和str2的内容一定是一样的！
         * 但是，并不是同一个字符串对象
         */
        String str1 = "the light";
        String str2 = new String(str1);
        //==用于判断是否是同一个字符串对象
        System.out.println( str1  ==  str2);
        /**
         * 是否是同一个对象-特例
         * 一般说来，编译器每碰到一个字符串的字面值，就会创建一个新的对象
         * 所以在第1128行会创建了一个新的字符串"the light"
         * 但是在第1129行，编译器发现已经存在现成的"the light"，那么就直接拿来使用，而没有进行重复创建
         */
        String str3 = "the light";
        String str4 = "the light";
        System.out.println( str3  ==  str4);


        /**
         * 内容是否相同：
         * 使用equals进行字符串内容的比较，必须大小写一致
         * equalsIgnoreCase，忽略大小写判断内容是否一致
         */
        System.out.println(str1.equals(str3));//完全一样返回true，大小写不一样返回false
        System.out.println(str1.equalsIgnoreCase(str4));//忽略大小写的比较

        /**
         * 是否以子某字符串开始或者结束,返回boolean值
         */
        str1.startsWith("");
        str1.endsWith("");

    }
}

/**
 * StringBuffer是可变长的字符串
 */
class stringBuffer{
    /**
     * append delete insert reverse	追加 删除 插入 反转
     * length capacity	长度 容量
     */
    /**
     * 为什么StringBuffer可以变长？
     * 和String内部是一个字符数组一样，StringBuffer也维护了一个字符数组。 但是，这个字符数组，留有冗余长度
     * 比如说new StringBuffer("the")，其内部的字符数组的长度，是19，而不是3，这样调用插入和追加，在现成的数组的基础上就可以完成了。
     * 如果追加的长度超过了19，就会分配一个新的数组，长度比原来多一些，把原来的数据复制到新的数组中，看上去 数组长度就变长了 参考MyStringBuffer
     * length: “the”的长度 3
     * capacity: 分配的总空间 19
     *
     * 注： 19这个数量，不同的JDK数量是不一样的
     */
    public static void main(String[] args) {
        String str1 = "let there ";

        StringBuffer sb = new StringBuffer(str1); //根据str1创建一个StringBuffer对象
        sb.append("be light"); //在最后追加

        System.out.println(sb);

        sb.delete(4, 10);//删除4-10之间的字符

        System.out.println(sb);
        sb.insert(4, "there ");//在4这个位置插入 there
        System.out.println(sb);
        sb.reverse(); //反转
        System.out.println(sb);

        /**
         * 长度/容量
         */
        System.out.println(sb.length()); //内容长度
        System.out.println(sb.capacity());//总空间
    }

    /**
     * 生成长度为10的随机字符串
     * @return
     */
    public static String ranstr() {
        char[]a=new char[10];
        for(int i=0;i<a.length;i++) {
            while(true) {
                char randomnumber=(char)(Math.random()*(122-48+1)+48);
                if(Character.isLetterOrDigit(randomnumber));
                a[i]=randomnumber;
                break;
            }
        }
        return String.valueOf(a);
    }

    /**
     * 练习
     * String与StringBuffer的性能区别?
     * 生成10位长度的随机字符串
     * 然后,先使用String的+,连接10000个随机字符串,计算消耗的时间
     * 然后,再使用StringBuffer连接10000个随机字符串,计算消耗的时间
     */
    public static void practiceX() {
        String str= ranstr();
        String str1="";
        String str2="";
        //开始时间
        long startTime=System.currentTimeMillis();
        for(int i=0;i<10000000;i++) {
            str=str+str1;


        }
        System.out.println(str);
        //结束时间
        long endtime=System.currentTimeMillis();
        System.out.println(endtime-startTime+"毫秒");
        //开始时间1
        long start=System.currentTimeMillis();
        StringBuffer sb=new StringBuffer();
        for(int j=0;j<10000;j++) {
            sb.append(str2);



        }
        //结束时间1
        long end=System.currentTimeMillis();
        System.out.println(end-start+"毫秒");
    }
}