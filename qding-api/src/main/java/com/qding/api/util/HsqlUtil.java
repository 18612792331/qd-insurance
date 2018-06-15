package com.qding.api.util;

/**
 * Created by qd on 2016/1/25.
 */

import com.qding.framework.common.util.QDStringUtil;
import org.hsqldb.Server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 方便单机程序使用HSQL的工具类，包括启动，关闭，连接。数据库默认不加密，用户为sa，密码空
 * @author
 */
public class HsqlUtil {

    public static final int PORT = 9002;
    public static final String DB_NAME = "qdAPI";       //数据库文件名，同时也是本类中的数据库名
    public static final String DB_PATH = "E:/IntelliJideaWorkSpce/platform2.5/qding-api/src/main/resources/db/";
    public static final String USER_NAME = "SA";
    public static final String PASSWORD = "";
    public static final int SERVER_MODE = 0;
    public static final int STAND_ALONE_MODE = 1;   //In-Process
    public static int mode = STAND_ALONE_MODE;          //记录当前用什么模式，开发时用Server，发布时用standalone

    /**
     * 启动数据库服务
     */
    public static boolean startHSQL() {
        if (mode == SERVER_MODE) {
            Server server = new Server();//它可是hsqldb.jar里面的类啊。
            server.setDatabaseName(0, DB_NAME);
            server.setDatabasePath(0, DB_PATH + DB_NAME);
            server.setPort(PORT);
            server.setSilent(true);
            server.start();         //自动多线程运行
            System.out.println("hsqldb started...");
        } else if (mode == STAND_ALONE_MODE) {
            //standalone模式，打开连接就同时启动数据库，所以这里可以什么都不做
        }

        try {
            Thread.sleep(800);        // 等待Server启动
        } catch (InterruptedException e) {
        }
        return true;
    }

    /**
     * 关闭数据库服务
     */
    public static boolean stopHSQL() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("SHUTDOWN;");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HsqlUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * 查询
     * @param
     * @param expression
     * @return
     * @throws SQLException
     */
    public static synchronized  ResultSet query(String expression) throws SQLException {
        Connection conn = getConnection();
        Statement st = null;
        ResultSet rs = null;
        st = conn.createStatement();         // statement objects can be reused with
        rs = st.executeQuery(expression);    // run the query
       // st.close();    // NOTE!! if you close a statement the associated ResultSet is
        return rs;
    }

    /**
     * 修改
     * @param
     * @param expression
     * @throws SQLException
     */
    public static synchronized int update(String expression) throws SQLException {

        Connection conn = getConnection();
        Statement st = null;
        st = conn.createStatement();    // statements
        int i = st.executeUpdate(expression);    // run the query
        st.close();
        conn.close();
        return i;
    }

    //查询条数
    public static synchronized int queryCount(String expression) throws SQLException {

        Connection conn = getConnection();
        Statement st = null;
        ResultSet resultSet = null;
        int count =0;
        try {
            st = conn.createStatement();    // statements
            resultSet = st.executeQuery(expression);    // run the query
            if (resultSet.getRow()>0){
                count = resultSet.getRow();
            }
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(QDStringUtil.isNotNull(resultSet)){
                resultSet.close();
            }
            if(QDStringUtil.isNotNull(st)){
                st.close();
            }
            if (QDStringUtil.isNotNull(conn)){
                conn.close();
            }
        }
        return count;
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            if (mode == SERVER_MODE) {
                conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:" + PORT + "/" + DB_NAME, USER_NAME, PASSWORD);
            } else if (mode == STAND_ALONE_MODE) {
                conn = DriverManager.getConnection("jdbc:hsqldb:file:" + DB_PATH + DB_NAME, USER_NAME, PASSWORD);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HsqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HsqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }



    /**
     * 测试
     */
    public static void main(String[] args) {
        HsqlUtil.mode = HsqlUtil.STAND_ALONE_MODE;
        HsqlUtil.startHSQL();
        Connection conn = HsqlUtil.getConnection();
        try {
            Statement statement = getConnection().createStatement();
//            statement.executeUpdate("drop table api_requet");
            ResultSet resultSet =statement.executeQuery("select * from api_request");
//            statement.executeUpdate("create table api_requet(id integer not null primary key,serviceAlias VARCHAR(100), serviceMethod varchar(300),version varchar(10),parameterStr varchar(2000))");
//
//            ResultSet resultSet = statement.executeQuery("select * from customer");

            Object o = null;
            for (; resultSet.next(); ) {
               String firstName =  resultSet.getString("serviceAlias");
                System.out.println(firstName);
            }
           /* for (int i = 10; i < 20; i++) {
                statement.executeUpdate("insert into customer values(" + i + ",'liu','zhaoyang')");
            }*/
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(HsqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        HsqlUtil.stopHSQL();
    }
}