package com.pzq.changeoa.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtils {
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("resources.jdbc");
    private static String driver= resourceBundle.getString("driver");
    private static String url= resourceBundle.getString("url");
    private static String user= resourceBundle.getString("user");
    private static String password= resourceBundle.getString("password");

    /**
     * jdbc 六步
     * 注册驱动
     * 加载驱动
     * 获取数据库连接对象
     * 执行Sql语句
     * 获取结果集
     * 释放资源
     */


    /**
     * 代码加载块  驱动装一次就行
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放资源
     * @throws Exception
     */
    public static void close(Connection connection, Statement statement,ResultSet resultSet) {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
