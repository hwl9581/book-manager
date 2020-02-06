package com.han.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName DBUtil
 * @Description TODO
 * @Author HanWL
 * @Since 2020/1/28 0028 16:15
 */
public class DBUtil {
    private static String driver = "";
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private DBUtil(){
    }

    static {
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = pro.getProperty("driver");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (threadLocal.get() == null) {
            Connection connection = DriverManager.getConnection(url, username, password);
            threadLocal.set(connection);
        }
        return threadLocal.get();
    }

    public static void closeAll(Connection con, Statement stmt, ResultSet rs){
        if (con != null) {
            threadLocal.remove();
        }

        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
