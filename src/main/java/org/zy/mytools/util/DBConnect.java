package org.zy.mytools.util;

import java.sql.*;

/**
 * Created by yuezhang on 18/9/22.
 */
public class DBConnect {

    // 支付中心生产环境
//    private static final String DB_URL = "jdbc:mysql://10.200.70.209:3305/pptv_pay";
//    private static final String USER = "pp_pay";
//    private static final String PASS = "roL9HV48P7gMmN0a";

    // 本地开发环境
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/pptv_pay";
    private static final String USER = "root";
    private static final String PASS = "root";

    static {
        // 注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Statement getStatement(Connection conn) {
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getResultSet(Statement stat , String sql){
        try {
            return stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeAll(Connection conn,Statement stat,ResultSet rs){
        closeConnection(conn);
        closeStatement(stat);
        closeResultSet(rs);
    }

    private static void closeConnection(Connection conn) {
        if (conn == null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeStatement(Statement stat){
        if (stat == null) return;
        try {
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeResultSet(ResultSet rs) {
        if (rs == null) return;
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
