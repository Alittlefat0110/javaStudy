package com.javaBasic.study.knowledge.intermediate.exception;

public class ExceptionOwner {
    public String name;
    protected float hp;

    /**
     * 抛出自定义异常EnemyHeroIsDeadException
     * @param h
     * @throws EnemyHeroIsDeadException
     */
    public void attackHero(ExceptionOwner h) throws EnemyHeroIsDeadException{
        if(h.hp == 0){
            //throw抛出确定出现的异常
            throw new EnemyHeroIsDeadException(h.name + " 已经挂了,不需要施放技能" );
        }
    }

    /**
     * 重写Object类(默认继承）中的toString方法
     * @return
     */
    public String toString(){
        return name;
    }

    /**
     * 自定义异常类EnemyHeroIsDeadException
     */
    class EnemyHeroIsDeadException extends Exception{

        //无参的构造方法
        public EnemyHeroIsDeadException(){

        }
        //带参的构造方法，并调用父类的对应的构造方法
        public EnemyHeroIsDeadException(String msg){
            super(msg);
        }
    }

    public static void main(String[] args) {

        ExceptionOwner garen =  new ExceptionOwner();
        garen.name = "盖伦";
        garen.hp = 616;

        ExceptionOwner teemo =  new ExceptionOwner();
        teemo.name = "提莫";
        teemo.hp = 0;

        try {
            garen.attackHero(teemo);

        } catch (EnemyHeroIsDeadException e) {
            // TODO Auto-generated catch block
            System.out.println("异常的具体原因:"+e.getMessage());
            e.printStackTrace();
        }

    }
}
