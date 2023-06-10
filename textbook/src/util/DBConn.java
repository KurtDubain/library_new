package util;

/**
 * 数据库连接关闭类
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
    private static String url = "jdbc:mysql://localhost:3306/textbooksystem?serverTimezone=UTC&characterEncoding=utf-8";
    private static String username = "root";
    private static String password = "zxyzxy111";
    private static String jdbc = "com.mysql.cj.jdbc.Driver";

    //获得数据库的连接
    /**
     *获取数据库的连接
     *@return
     */
    public static Connection getConn(){
        Connection conn = null;
        try{
            Class.forName(jdbc);
            conn = DriverManager.getConnection(url,username,password);
        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }

    //关闭数据库的连接
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if (rs!=null){
            try{
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (ps!=null){
            try{
                ps.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (conn!=null){
            try{
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
