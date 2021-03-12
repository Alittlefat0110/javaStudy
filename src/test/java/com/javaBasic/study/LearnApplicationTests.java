package com.javaBasic.study;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.javaBasic.study.bean.TestVO;
import com.javaBasic.study.utils.FindDuplicatWord;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootTest()
class LearnApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 数据精度调整
     */
	@Test
    public void test() {
        BigDecimal a = new BigDecimal(12);
        BigDecimal b = a.divide(new BigDecimal("10"), 2, RoundingMode.HALF_UP);
        System.out.println(b);
    }

    /**
     * 字符串简单无缝拼接
     */
    @Test
    public void strSplit() {
        String R001 = "R001";
        String add = "D" + R001;
        System.out.println(add);
    }

    /**
     * 查看map的取值方式
     */
    @Test
    public void testMap1() {
        String A = "A";
        String B = "B";
        List<String> bondIdArray = new ArrayList<>();
        bondIdArray.add(A);
        bondIdArray.add(B);
        String appId = "appId";
        Map<String, Object> map = new HashMap<>();
        map.put("bondIDs", bondIdArray);
        map.put("appID", appId);
        //System.out.println(map.entrySet());
        //System.out.println(map.get("bondIDs"));
        //遍历map，e代表一个key-value映射项
        for (Map.Entry<String,Object> e : map.entrySet()){
            System.out.println(e);
        }
    }


    /**
     * 正则表达式m
     */
    @Test
    public void testStr() {
        String str = "STK 3C3 Pro <={}$100K";
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        String str1 = "1.123%";
        //0-9之间的数字必须匹配3个
        String regEx1 = "[0-9]{3,}";
        //只允许3位整数和4位小数范围内,并指定结束字符为%，？表示只重复0次或一次，^指定起始字符 \转义字符 \d表示数字0-9 {0，2}表示匹配0个到2个
        String regEx2 = "(^[1-9]\\d{0,2}(\\.\\d{1,4})?(%)?$)";
        boolean s = Pattern.matches(regEx2, str1);
        System.out.println(s);
        //将str中的<={}等符号替换成空
        Matcher m = Pattern.compile(regEx).matcher(str);
        System.out.println(m.replaceAll(""));
        //匹配小数点
        String x = "(\\.)?";
        String y = ".";

        boolean n = Pattern.matches(x, y);
        System.out.println(n);
    }

    /**
     * ?: 的用法  ?前的判断为真则取前面的值，若为假，则取后面的值
     */
    @Test
    public void test0() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        String A = "1";
        String B = "0";
        Boolean C = list.isEmpty();
        //若list为空则str=A，若不为空则str=B
        String str = C ? A : B;
        System.out.println(str);

    }

    /**
     * 字符串拼接
     */
    @Test
    public void m() {
        String l = "小明";
        String l1 = "gugrcyur7";
        String bondKey = String.format("%s,%s", l, l1);
        System.out.println(bondKey);//输出 小明,gugrcyur7
    }

    /**
     * 集合内部排序
     */
    @Test
    public void list() {
        //TestVO testVO=new TestVO();   在这创建对象时循环执行list.add会覆盖前面的数据
        List<TestVO> list = new ArrayList<>();
        //产生随机数
        Random r = new Random(1);
        for (int i = 1; i < 10; i++) {
            TestVO testVO = new TestVO();
            int ran1 = r.nextInt(100);
            testVO.setNum(ran1);
            testVO.setStr("qiqi" + i);
            list.add(testVO);
        }
        System.out.println(list);
        System.out.println(list.get(0).getStr());
        System.out.println("-------------------------------------------------");
        //按num从小到大排序
        Collections.sort(list, Comparator.comparing(TestVO::getNum));
        System.out.println(list);

    }

    /**
     * list.stream().collect(Collectors.groupingBy(TestVO::getStr))
     * list按TestVO::getStr分组
     * 并构成map<Str , List<TestVO>>
     */
    @Test
    public void testMap() {
        List<TestVO> list = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 1; i < 10; i++) {
            TestVO testVO = new TestVO();
            int ran1 = r.nextInt(100);
            testVO.setNum(ran1);
            if (i % 2 == 0) {
                testVO.setStr("qiqi" + 1);
            } else {
                testVO.setStr("qiqi" + 2);
            }
            list.add(testVO);
        }
        //将list按照TestVO::getStr分组，构建以str为key，以具有相同str（当前key）的TestVO的list集合作为value的Map
        Map<String, List<TestVO>> map = list.stream().collect(Collectors.groupingBy(TestVO::getStr));
        System.out.println(map);
        //获取特定key映射的value值
        System.out.println("通过key：qiqi1获取value" + map.get("qiqi1"));
        System.out.println("-------------------------------");
        //遍历map   entrySet()：一个key-value映射项
        System.out.println(map.get("qiqi1"));
        for (Map.Entry<String, List<TestVO>> e : map.entrySet()) {
            System.out.println(e);
        }
    }

    /**
     * Multimap
     */
    @Test
    public void testMultimap() {
        Multimap multimap = HashMultimap.create();
        List<TestVO> list = new ArrayList<>();
        //用于产生随机数
        Random r = new Random(1);
        for (int i = 1; i < 10; i++) {
            TestVO testVO = new TestVO();
            int ran1 = r.nextInt(100);
            testVO.setNum(ran1);
            if (i % 2 == 0) {
                testVO.setStr("qiqi" + 1);
            } else {
                testVO.setStr("qiqi" + 2);
            }
            list.add(testVO);
        }
        //将list按照TestVO::getStr分组，构建以str为key，以具有相同str（当前key）的TestVO的list集合作为value的Map
        Map<String, List<TestVO>> map = list.stream().collect(Collectors.groupingBy(TestVO::getStr));
        for (Map.Entry<String, List<TestVO>> e : map.entrySet()) {
            System.out.println("遍历------>" + e);
            List<TestVO> list1 = e.getValue();
            System.out.println("list1长度------------->" + list1.size());
            Integer num = list1.get(0).getNum();
            for (int i = 0; i < list1.size(); i++) {
                multimap.put(num, list1.get(i).getNum());
                System.out.println("循环内-----)" + multimap);
            }
        }
        Map<Integer, Set<Integer>> map1 = multimap.asMap();
        System.out.println("循环外---------->" + multimap.asMap());
        System.out.println("----------" + map1);
    }

    /**
     * Switch 。。。 Case。。。
     * switch的值去匹配case的值，匹配之后执行case后的语句
     */
    @Test
    public void SwitchCase() {
        int i = 3;
        switch (i) {
            case 1:
            case 2:
            case 3:
                System.out.println("春季");
                //若没有break则当前case匹配时，除了执行当前case后的语句，还会执行下一个（或多个，只要前一case后没有break）case后的语句
                //结论：最终执行的方法体是什么呢？匹配上的case的冒号开始，一直到break为止，之间的case条件忽略，语句会执行
                //break;
            case 4:
            case 5:
            case 6:
                System.out.println("夏季");
                //break;
            case 7:
                System.out.println("秋季");
                break;
            default:
                System.out.println("默认值");
                break;
        }
    }

    /**
     * list.stream().map(TestVO::getStr).collect(Collectors.toList())
     * 通过stream流，从一个类对象集合中获取每个对象中的某个元素组中新的集合
     */
    @Test
    public void test7(){
        List<TestVO> list = new ArrayList<>();
        for (int i = 1; i<=10 ;i++){
            TestVO testVO = new TestVO();
            testVO.setNum(i);
            testVO.setStr("i"+i);
            list.add(testVO);
        }
        System.out.println("全量list-----"+list);
        for (TestVO e: list){
            System.out.println("遍历list------"+e);
        }
        //通过stream流，将list中的str一次放入list1中
        List<String> list1 = list.stream().map(TestVO::getStr).collect(Collectors.toList());
        System.out.println("list1----"+list1);
    }

    /**
     * 查找第一个重复元素
     */
    @Test
    public void test77(){
        List<TestVO> list = new ArrayList<>();
        for (int i = 1; i<=10 ;i++){
            TestVO testVO = new TestVO();
            testVO.setNum(i);
            testVO.setStr("i"+i);
            list.add(testVO);
        }
        TestVO testVO = new TestVO();
        testVO.setNum(11);
        //设置重复元素
        testVO.setStr("i"+5);
        list.add(testVO);
        //通过stream流，将list中的str一次放入list1中
        List<String> list1 = list.stream().map(TestVO::getStr).collect(Collectors.toList());
        System.out.println(list1);
        //查找第一个重复元素
        String str= FindDuplicatWord.findDuplicatWord(list1);
        System.out.println(str);
    }

    /**
     * isNumeric()
     * 校验字符串是否只由数字组成
     */
    @Test
    public void test11(){
        String str = "389120b";
        boolean t = StringUtils.isNumeric(str);
        System.out.println(t);
    }


    /**
     * parseLong
     * 将字符串转换成数字
     */
    @Test
    public void test00(){
        String str = "123";
        //radix:源数据解析进制，转换后都以十进制输出
        Long num = Long.parseLong(str,10);
        Long num1 = Long.parseLong("1011101",2);
        System.out.println(num);
        System.out.println(num1);
    }

}
