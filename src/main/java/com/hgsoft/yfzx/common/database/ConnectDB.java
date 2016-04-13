package com.hgsoft.yfzx.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 功能描述：连接数据库-mysql/oracle/sqlserver
 *
 * @author :FangHewei
 * @version :1.0
 * @Date :Jul 18, 2008
 * @Time :3:28:59 PM
 * @alter :PanNaiZhao
 */
public class ConnectDB {
    /**
     * mysql
     */
    private static final String MYSQL = "jdbc:mysql://";

    /**
     * oracle
     */
    private static final String ORACLE = "jdbc:oracle:thin:@";

    /**
     * sqlserver
     */
    private static final String SQLSERVER = "jdbc:microsoft:sqlserver://";

    private ConnectDB() {
    }

    /**
     * 功能描述：获取数据库的连接
     *
     * @param DBType，数据库类型
     * @param url          连接url
     * @param user         用户名
     * @param password     密码
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String DBType, String url,
                                           String user, String password) throws SQLException {
        if ("mysql".equalsIgnoreCase(DBType))
            return getMySqlConn(url, user, password);
        if ("oracle".equalsIgnoreCase(DBType))
            return getOracleConn(url, user, password);
        if ("sqlserver".equals(DBType)) {
            return getSqlServerConn(url, user, password);
        }
        return null;
    }

    /**
     * 功能描述：关闭数据库连接
     *
     * @param conn
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能描述：获取mysql的数据库连接
     *
     * @param url
     * @param user
     * @param password
     * @return
     * @throws SQLException
     */
    private static Connection getMySqlConn(String url, String user,
                                           String password) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(MYSQL + url, user, password);

        return conn;
    }

    /**
     * 功能描述：获取oracle的数据库连接
     *
     * @param url
     * @param user
     * @param password
     * @return
     * @throws SQLException
     */
    private static Connection getOracleConn(String url, String user,
                                            String password) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//		conn = DriverManager.getConnection(ORACLE + url, "scott", "tiger");
        conn = DriverManager.getConnection(ORACLE + url, user, password);

        return conn;
    }

    /**
     * 功能描述：获取sqlserver的数据库连接
     *
     * @param url
     * @param user
     * @param password
     * @return
     * @throws SQLException
     */
    private static Connection getSqlServerConn(String url, String user,
                                               String password) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");// 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//		conn = DriverManager.getConnection(SQLSERVER + url, "root", "root");
        conn = DriverManager.getConnection(SQLSERVER + url, user, password);

        return conn;
    }

    /**
     * Test 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConnection("MySQL", "10.173.232.140", "root",
                    "pnz");
            if (conn == null) {
                System.out.println("Connection the database is failled !");
            } else {
                System.out.println(conn.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
