package com.javaBasic.study.intermediate.annotation;

import java.lang.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 自定义注解
 */
public class CustomizedAnnotation {

}

/**
 * 非注解方式DBUtil顶折纠问
 * 通常来讲，在一个基于JDBC开发的项目里，都会有一个DBUtil这么一个类，
 * 在这个类里统一提供连接数据库的IP地址，端口，数据库名称， 账号，密码，编码方式等信息。
 *
 * 大家可以运行试试，运行结果是获取一个连接数据库test的连接Connection实例。
 */
class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "test";
    static String loginName = "root";
    static String password = "giyoyo9420";
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC&allowMultiQueries=true", ip, port, database);
        return DriverManager.getConnection(url, loginName, password);
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}


/**
 * 自定义注解@JDBCConfig顶折纠问
 * 接下来，就要把DBUtil这个类改造成为支持自定义注解的方式。 首先创建一个注解JDBCConfig
 *
 * 1. 创建注解类型的时候即不使用class也不使用interface,而是使用@interface
 *
 * public @interface JDBCConfig
 *
 *
 * 2. 元注解
 * @Target({METHOD,TYPE}) 表示这个注解可以用用在类/接口上，还可以用在方法上
 * @Retention(RetentionPolicy.RUNTIME) 表示这是一个运行时注解，即运行起来之后，才获取注解中的相关信息，
 * 而不像基本注解如@Override 那种不用运行，在编译时eclipse就可以进行相关工作的编译时注解。
 * @Inherited 表示这个注解可以被子类继承
 * @Documented 表示当执行javadoc的时候，本注解会生成相关文档
 * 请在学习完本知识点最后一个步骤解析注解之后，再查看 元注解，做更详尽的学习。
 *
 * 3. 注解元素，这些注解元素就用于存放注解信息，在解析的时候获取出来
 *
 *  String ip();
 *  int port() default 3306;
 *  String database();
 *  String encoding();
 *  String loginName();
 *  String password();
 */
@Target({METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface JDBCConfig {
    String ip();
    int port() default 3306;
    String database();
    String encoding();
    String loginName();
    String password();
}


/**
 * 注解方式DBUtil
 * 有了自定义注解@JDBCConfig之后，我们就把非注解方式DBUtil改造成为注解方式DBUtil。
 * 如例所示，数据库相关配置信息本来是以属性的方式存放的，现在改为了以注解的方式，提供这些信息了。
 *
 * 注： 目前只是以注解的方式提供这些信息，但是还没有解析，接下来进行解析
 */
@JDBCConfig(ip = "127.0.0.1", database = "test", encoding = "UTF-8", loginName = "root", password = "admin")
class DBUtilA {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析注解顶折纠问
     * 接下来就通过反射，获取这个DBUtil这个类上的注解对象
     *
     * JDBCConfig config = DBUtil.class.getAnnotation(JDBCConfig.class);
     *
     * 拿到注解对象之后，通过其方法，获取各个注解元素的值：
     *
     * String ip = config.ip();
     * int port = config.port();
     * String database = config.database();
     * String encoding = config.encoding();
     * String loginName = config.loginName();
     * String password = config.password();
     *
     * 后续就一样了，根据这些配置信息得到一个数据库连接Connection实例。
     * @return
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static Connection getConnection() throws SQLException, NoSuchMethodException, SecurityException {
        JDBCConfig config = DBUtil.class.getAnnotation(JDBCConfig.class);

        String ip = config.ip();
        int port = config.port();
        String database = config.database();
        String encoding = config.encoding();
        String loginName = config.loginName();
        String password = config.password();

        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, SQLException {
        Connection c = getConnection();
        System.out.println(c);
    }

}
