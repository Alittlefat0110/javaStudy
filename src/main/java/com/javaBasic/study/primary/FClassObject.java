package com.javaBasic.study.primary;

/**
 * 类与对象
 */
public class FClassObject  {
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度
}

/**
 * 引用
 * 如果一个变量的类型是 类类型（由类定义，如实体类定义一个对象），而非基本类型，那么该变量又叫做引用
 */
class quote{
    public static void main(String[] args){
        /**
         * 引用和指向:
         *
         * new Hero();
         *
         * 代表创建了一个Hero对象
         * 但是也仅仅是创建了一个对象，没有办法访问它
         * 为了访问这个对象，会使用引用来代表这个对象
         *
         * Hero h = new Hero();
         *
         * h这个变量是Hero类型，又叫做引用
         * =的意思指的h这个引用代表右侧创建的对象
         * “代表” 在面向对象里，又叫做“指向”
         */
        //创建一个对象
        new FClassObject();
        //使用一个引用来指向这个对象,h称为一个“引用”
        FClassObject h = new FClassObject();

        /**
         * 多个引用，一个对象
         * 引用有多个，但是对象只有一个。
         * 在这个例子里，所有引用都指向了同一个对象。
         * 对象就像 "房产"， 引用就像"房产证"
         * 房产证的复印件可以有多张，但是真正的"房产" 只有这么一处
         */
        //使用一个引用来指向这个对象
        //h1,h2,h3,h4,h5 五个引用，都指向同一个对象
        FClassObject h1 = new FClassObject();
        FClassObject h2 = h1;  //h2指向h1所指向的对象
        FClassObject h3 = h1;
        FClassObject h4 = h1;
        FClassObject h5 = h4;

        /**
         * 一个引用，多个对象顶折纠问
         * 第65行，引用garen指向新创建的对象（对象1）
         * 第67行，同一个引用garen指向新创建的对象（对象2）
         * 这个时候，对象1，就没有任何引用指向了
         * 换句话说，就没有任何手段控制和访问该对象，那么该对象就变得没有意义。
         */
        //引用garen指向新创建的对象（对象1）
        FClassObject garen =  new FClassObject();
        //同一个引用garen指向新创建的对象（对象2）
        garen =  new FClassObject();
    }
}

/**
 * 继承
 */
class Inherit extends FClassObject{
    int test;
    public static void main(String[] args){
        Inherit inherit=new Inherit();
        //由当前类新定义
        inherit.test=1;
        //由FClassObject类中继承而来
        inherit.name="继承";
        inherit.moveSpeed=50;//移动速度
        inherit.hp=50.3f;//血量
        inherit.armor=100.0f;//护甲
    }
}

/**
 * 方法重载
 * 方法名一样，但是参数类型不一样
 */
class  FunctionOverride extends FClassObject{
    String name1;
    /**
     * attack方法的重载:
     * 有一种英雄，叫做物理攻击英雄 FunctionOverride
     * FunctionOverride 提供三种方法
     *
     * public void attack()
     * public void attack(FClassObject h1)
     * public void attack(FClassObject h1, FClassObject h2)
     *
     * 方法名是一样的，但是参数类型不一样
     * 在调用方法attack的时候，会根据传递的参数类型以及数量，自动调用对应的方法
     */
    public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }

    public void attack(FClassObject h1) {
        System.out.println(name + "对" + h1.name + "进行了一次攻击 ");
    }

    public void attack(FClassObject h1, FClassObject h2) {
        System.out.println(name1 + "同时对" + h1.name + "和" + h2.name + "进行了攻击 ");
    }

    public static void main(String[] args) {
        FunctionOverride bh = new FunctionOverride();
        FunctionOverride bh1 = new FunctionOverride();
        bh.name = "赏金猎人";
        bh1.name1= "德玛西亚";

        FClassObject h1 = new FClassObject();
        h1.name = "盖伦";
        FClassObject h2 = new FClassObject();
        h2.name = "提莫";
        FClassObject h3 = new FClassObject();
        h3.name = "锐雯";

        bh.attack(h1);
        bh.attack(h2);
        bh1.attack(h1,h2);
        bh.attack(h1,h2,h3);
    }

    /**
     * 可变数量的参数
     */
    // 可变数量的参数
    public void attack(FClassObject... heros) {
        for (int i = 0; i < heros.length; i++) {
            System.out.println(name + " 攻击了 " + heros[i].name);

        }
    }
}

/**
 * 构造方法
 * 通过一个类创建一个对象，这个过程叫做实例化
 * 实例化是通过调用构造方法(又叫做构造器)实现的
 */
class ConstructorMethod{
    /**
     * 什么是构造方法:
     * 方法名和类名一样（包括大小写）
     * 没有返回类型
     * 实例化一个对象的时候，必然调用构造方法
     */
    String name;

    float hp;

    float armor;

    int moveSpeed;

    // 方法名和类名一样（包括大小写）
    // 没有返回类型
    //这个无参的构造方法，如果不写，
    //就会默认提供一个无参的构造方法
    public ConstructorMethod() {
        System.out.println("实例化一个对象的时候，必然调用构造方法");
    }

    /**
     * 如果提供了一个有参的构造方法顶折纠问
     * 一旦提供了一个有参的构造方法
     * 同时又没有显式的提供一个无参的构造方法
     * 那么默认的无参的构造方法，就“木有了“
     */
    //即说明，构造方法也可以重载
    public ConstructorMethod(String heroname){
        name = heroname;
    }

    public void attack(FClassObject h1, FClassObject h2) {
        System.out.println(name + "同时对" + h1.name + "和" + h2.name + "进行了攻击 ");
    }
    public static void main(String[] args) {
        //实例化一个对象的时候，必然调用构造方法
        ConstructorMethod h = new ConstructorMethod();
        ConstructorMethod h1=new ConstructorMethod("七七");
        FClassObject a=new FClassObject();
        a.name="剑圣";
        FClassObject b=new FClassObject();
        b.name="穗穗";
        h1.attack(a,b);
    }
}

/**
 * this关键字
 * 相当于普通话里的“我”
 */
class This{
    /**
     * 小明说 “我吃了” 这个时候，“我” 代表小明
     * 小红说 “我吃了” 这个时候，“我” 代表小红
     * "我"代表当前人物
     * this这个关键字，相当于普通话里的“我”
     * this即代表当前对象
     */
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    //打印内存中的虚拟地址
    public void showAddressInMemory(){
        System.out.println("打印this看到的虚拟地址："+this);
    }

    /**
     * 通过this访问属性
     * @param name
     */
    //参数名和属性名一样
    //在方法体中，只能访问到参数name
    public void setName1(String name){
        name = name;
    }
    //为了避免setName1中的问题，参数名不得不使用其他变量名
    public void setName2(String heroName){
        name = heroName;
    }
    //通过this访问属性
    public void setName3(String name){
        //name代表的是参数name
        //this.name代表的是属性name
        this.name = name;
    }

    /**
     * 通过this调用其他的构造方法
     */
    //不带参数
    public This(){

    }
    //带一个参数的构造方法
    public This(String name){
        System.out.println("一个参数的构造方法");
        this.name = name;
    }

    //带两个参数的构造方法
    public This(String name,float hp){
        this(name);
        System.out.println("两个参数的构造方法");
        this.hp = hp;
    }

    public static void main(String[] args) {
        This garen =  new This();
        garen.name = "盖伦";
        //直接打印对象，会显示该对象在内存中的虚拟地址
        //格式：Hero@c17164 c17164即虚拟地址，每次执行，得到的地址不一定一样

        System.out.println("打印对象看到的虚拟地址："+garen);
        //调用showAddressInMemory，打印该对象的this，显示相同的虚拟地址
        garen.showAddressInMemory();

        This teemo =  new This();
        teemo.name = "提莫";
        System.out.println("打印对象看到的虚拟地址："+teemo);
        teemo.showAddressInMemory();

        /**
         * 测试this访问属性
         */
        This h =  new This();
        //无法访问“提莫”这个属性
        h.setName1("提莫");
        System.out.println(h.name);
        h.setName2("盖伦");
        System.out.println(h.name);
        h.setName3("死歌");
        System.out.println(h.name);

        /**
         * 测试this调用其他构造方法
         */
        This teeMo =  new This("提莫",383);

        System.out.println(teeMo.name);
    }
}

/**
 * 传参
 */
class PassParameter{
    /**
     * 变量有两种类型 基本类型 和类类型
     * 参数也是变量，所以传参分为
     * 基本类型传参
     * 类类型传参
     */
    String name; //姓名

    float hp; //血量

    float armor; //护甲

    int moveSpeed; //移动速度

    public PassParameter(){

    }

    /**
     * 基本类型传参
     * 在方法内，无法修改方法外的基本类型参数
     */
    //回血
    //在方法内，无法修改方法外的基本类型参数,即xp=0是不起作用的，xp只是传递值
    public void huixue(int xp){
        hp = hp + xp;
        //回血完毕后，血瓶=0
        xp=0;
    }
    public PassParameter(String name,float hp){
        this.name = name;
        this.hp = hp;
    }

    /**
     * 类类型传参顶折纠问
     * 类类型又叫引用
     * 第363行的引用 teemo与 第350行的引用hero，是不同的引用
     * 通过调用garen.attack(teemo, 100); 使得这两个引用都指向了同一个对象
     * 所以在第351行hero.hp = hero.hp - damage; 就使得该对象的hp值，发生了变化
     * 因此第364行，打印该对象的Hp值就是变化后的值
     */
    // 攻击一个英雄，并让他掉damage点血
    public void attack(PassParameter hero, int damage) {
        hero.hp = hero.hp - damage;
    }

    /**
     * 练习
     */
    //复活
    public void revive(PassParameter h){
        h = new PassParameter("提莫",383);
    }
    public static void main(String[] args) {
        /**
         * 基本类型引用测试
         */
        PassParameter teeMo =  new PassParameter("提莫",383);
        //血瓶，其值是100
        int xueping = 100;
        //提莫通过这个血瓶回血
        teeMo.huixue(xueping);
        System.out.println(xueping);

        /**
         * 类类型引用测试
         */
        PassParameter teemo = new PassParameter("提莫", 383);
        PassParameter garen = new PassParameter("盖伦", 616);
        garen.attack(teemo, 100);
        System.out.println(teemo.hp);

        /**
         * 练习测试
         */
        PassParameter teemo2 =  new PassParameter("提莫",383);
        //受到400伤害，挂了
        teemo2.hp = teemo2.hp - 400;
        //输出-17
        System.out.println(teemo2.hp);
        teemo2.revive(teemo2);
        //问题： System.out.println(teemo2.hp); 输出多少？ 怎么理解？
        //输出-17，说明revive方法没有起作用
        //调用revive时，由于参数是类类型，并不是进行变量值的传递，而是指向对象的传递，
        // 也就是让“引用h“指向了“引用teemo”指向的对象——死提莫。然后在方法中，又让h指向了
        // 新创建的对象——复活的另一个提莫，而teemo指向的还是原来的死提莫，并没有发生变化。
        System.out.println(teemo2.hp);
        /**
         * 这是因为在main函数里面，引用teemo指向一个Hero对象（提莫），该对象内存地址为1，里面存放了（提莫）的名字和血量
         * teemo.revive(teemo)，变量teemo是一个类类型，参考类类型引用，revive方法将h也指向了（提莫）对象，内存地址1。
         * 然后revive方法内 又引用h指向了一个新的对象（提莫1），内存地址为2，里面存放了（提莫1）的名字和血量
         * 所以revive方法内的h = new Hero（"提莫"，383）；只是令h指向了（提莫1），并不能改变teemo.hp的值。
         */

    }

    /**
     * 引用与=
     * 如果一个变量是基本类型
     * 比如 int hp = 50;
     * 我们就直接管hp叫变量
     * =表示赋值的意思。
     * 如果一个变量是类类型
     * 比如 Hero h = new Hero();
     * 我们就管h叫做引用。
     * =不再是赋值的意思
     * =表示指向的意思
     * 比如 Hero h = new Hero();
     * 这句话的意思是
     * 引用h，指向一个Hero对象
     */
}

/**
 * 包
 */
class Package{
    /**
     * 把比较接近的类，规划在同一个包下
     * 使用其他包下的类，必须import
     */
}

/**
 * 访问修饰符
 */
class AccessModifier{
    /**
     * 成员变量有四种修饰符
     * private 私有的
     * package/friendly/default 不写
     * protected 受保护的
     * public 公共的
     *
     * 比如public 表示公共的
     * public String name;
     *
     * 而maxHP 没有修饰符即代表package/friendly/default
     * float maxHP
     */

    /**
     * 类之间的关系顶折纠问
     * 类和类之间的关系有如下几种:
     * 以Hero为例
     * 自身：指的是Hero自己
     * 同包子类：ADHero这个类是Hero的子类，并且和Hero处于同一个包下
     * 不同包子类：Support这个类是Hero的子类，但是在另一个包下
     * 同包类： GiantDragon 这个类和Hero是同一个包，但是彼此没有继承关系
     * 其他类：Item这个类，在不同包，也没有继承关系的类
     */

    /**
     * private 私有的
     * 使用private修饰属性
     * 自身：是可以访问的
     * 同包子类：不能继承
     * 不同包子类：不能继承
     * 同包类：不能访问
     * 其他包类：不能访问
     */
    //属性id是private的，只有Hero自己可以访问
    //子类不能继承
    //其他类也不能访问
    private int id;

    /**
     * package/friendly/default
     * 没有修饰符即代表package/friendly/default
     * float maxHP; 血量上限
     */
    // 无修饰符的属性 hp
    // 自己可以访问
    // 同包子类可以继承
    // 不同包子类不能继承
    // 同包类可以访问
    // 不同包类不能访问
    float hp;

    /**
     * protected 受保护的
     * 受保护的修饰符
     * protected float hp; 血量
     */
    // protected饰符的属性 hp1
    // 自己可以访问
    // 同包子类可以继承
    // 不同包子类可以继承
    // 同包类可以访问
    // 不同包类不能访问
    protected float hp1;

    /**
     * public 公共的
     * 公共的修饰符
     * public String name; 姓名
     * 任何地方，都可以访问
     */
    // public的属性 name
    // 自己可以访问
    // 同包子类可以继承
    // 不同包子类可以继承
    // 同包类可以访问
    // 不同包类可以访问
    public String name;

    /**
     * 那么什么情况该用什么修饰符呢？顶折纠问

     * 1. 属性通常使用private封装起来
     * 2. 方法一般使用public用于被调用
     * 3. 会被子类继承的方法，通常使用protected
     * 4. package用的不多，一般新手会用package,因为还不知道有修饰符这个东西
     *
     * 再就是作用范围最小原则
     * 简单说，能用private就用private，不行就放大一级，用package,再不行就用protected，最后用public。
     * 这样就能把数据尽量的封装起来，没有必要露出来的，就不用露出来了
     */
}

/**
 * 类属性
 */
class  ClassAttribute{
    /**
     * 当一个属性被static修饰的时候，就叫做类属性，又叫做静态属性
     * 当一个属性被声明成类属性，那么所有的对象，都共享一个值
     * 与对象属性对比：
     * 不同对象的 对象属性 的值都可能不一样。
     * 比如盖伦的hp 和 提莫的hp 是不一样的。
     * 但是所有对象的类属性的值，都是一样的
     */
    public String name; //实例属性，对象属性，非静态属性
    protected float hp;
    static String copyright;//类属性,静态属性
    public static void main(String[] args) {
        ClassAttribute garen =  new ClassAttribute();
        garen.name = "盖伦";

        ClassAttribute.copyright = "版权由Riot Games公司所有";

        System.out.println(garen.name);
        System.out.println(garen.copyright);

        ClassAttribute teemo =  new ClassAttribute();
        teemo.name = "提莫";
        System.out.println(teemo.name);
        System.out.println(teemo.copyright);

    }
}

/**
 * 类方法
 */
class ClassMethod{
    /**
     * 类方法： 又叫做静态方法
     *
     * 对象方法： 又叫实例方法，非静态方法
     * 访问一个对象方法，必须建立在有一个对象的前提的基础上
     * 访问类方法，不需要对象的存在，直接就访问
     */
    public String name;
    protected float hp;

    //实例方法,对象方法，非静态方法
    //必须有对象才能够调用
    public void die(){
        hp = 0;
    }

    //类方法，静态方法
    //通过类就可以直接调用,不依赖任何对象
    public static void battleWin(){
        System.out.println("battle win");
    }

    public static void main(String[] args) {
        ClassMethod garen =  new ClassMethod();
        garen.name = "盖伦";
        //必须有一个对象才能调用
        garen.die();

        ClassMethod teemo =  new ClassMethod();
        teemo.name = "提莫";

        //无需对象，直接通过类调用
        ClassMethod.battleWin();

    }

    /**
     * 什么时候设计对象方法，什么时候设计类方法顶折纠问
     * 如果在某一个方法里，调用了对象属性，比如
     *
     *     public String getName(){
     *     	return name;
     *     }
     *
     * name属性是对象属性，只有存在一个具体对象的时候，name才有意义。 如果方法里访问了对象属性，那么这个方法，就必须设计为对象方法
     *
     * 如果一个方法，没有调用任何对象属性，那么就可以考虑设计为类方法，比如
     *
     *     public static void printGameDuration(){
     *     	System.out.println("已经玩了10分50秒");
     *     }
     */
}

/**
 * 单例模式
 * 单例模式又叫做 Singleton模式，指的是一个类，在一个JVM里，只有一个实例存在。
 */
class  GiantDragon{
    /**
     * 饿汉式单例模式:
     * GiantDragon 应该只有一只，通过私有化其构造方法，使得外部无法通过new 得到新的实例。
     * GiantDragon 提供了一个public static的getInstance方法，外部调用者通过该方法获取12行定义的对象，
     * 而且每一次都是获取同一个对象。 从而达到单例的目的。
     * 这种单例模式又叫做饿汉式单例模式，无论如何都会创建一个实例
     */
    //1.私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){
    }
    //2.准备一个类属性，指向一个实例化对象。 因为是类属性，所以只有一个
    private static GiantDragon instance = new GiantDragon();
    //3.public static 方法，提供给调用者获取上面定义的对象
    public static GiantDragon getInstance(){
        return instance;
    }


    /**
     * 懒汉式单例模式顶折纠问
     * 懒汉式单例模式与饿汉式单例模式不同，只有在调用getInstance1的时候，才会创建实例
     */
    //第1步同上
    //2.准备一个类属性，用于指向一个实例化对象，但是暂时指向null
    private static GiantDragon instance1;

    //3.public static 方法，返回实例对象
    public static GiantDragon getInstance1(){
        //第一次访问的时候，发现instance没有指向任何对象，这时实例化一个对象
        if(null==instance1){
            instance1 = new GiantDragon();
        }
        //返回 instance指向的对象
        return instance1;
    }

    /**
     * 什么时候使用饿汉式，什么时候使用懒汉式顶折纠问
     * 饿汉式是立即加载的方式，无论是否会用到这个对象，都会加载。
     * 如果在构造方法里写了性能消耗较大，占时较久的代码，比如建立与数据库的连接，那么就会在启动的时候感觉稍微有些卡顿。
     *
     * 懒汉式，是延迟加载的方式，只有使用的时候才会加载。 并且有线程安全的考量(鉴于同学们学习的进度，暂时不对线程的章节做展开)。
     * 使用懒汉式，在启动的时候，会感觉到比饿汉式略快，因为并没有做对象的实例化。 但是在第一次调用的时候，会进行实例化操作，感觉上就略慢。
     *
     * 看业务需求，如果业务上允许有比较充分的启动和初始化时间，就使用饿汉式，否则就使用懒汉式
     */


    /**
     * 这个是面试的时候经常会考的点，面试题通常的问法是:
     *
     * 什么是单例模式？
     *
     * 回答的时候，要答到三元素
     * 1. 构造方法私有化
     * 2. 静态属性指向实例
     * 3. public static的 getInstance方法，返回第二步的静态属性
     */

    /**
     * 单例模式使用情景：
     * 举个例子，你用播放器听歌，它同时只能播放一首歌，这就是单例，不用单例的话，你放一首歌，再点开另一首它就会同时播放两首歌
     */
}

/**
 * 枚举类型
 */
class Enum{
    /**
     * 预先定义的常量顶折纠问
     * 枚举enum是一种特殊的类(还是类)，使用枚举可以很方便的定义常量
     * 比如设计一个枚举类型 季节，里面有4种常量
     */
     public enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }

    /**
     * 一个常用的场合就是switch语句中，使用枚举来进行判断
     * 注：因为是常量，所以一般都是全大写
     */
    public static void main(String[] args) {
        Season season = Season.SPRING;
        switch (season) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }

        /**
         * 遍历枚举顶折纠问
         * 借助增强型for循环，可以很方便的遍历一个枚举都有哪些常量
         */
        for (Season s : Season.values()) {
            System.out.println(s);
        }
    }

}