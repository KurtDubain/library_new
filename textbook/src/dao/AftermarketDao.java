package dao;

import bean.Aftermarket;
import util.DBConn;
import util.Fenye;
import util.Pager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AftermarketDao {
    //插入纪录
    public void insertBean(Aftermarket bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into aftermarket(studentid,bookid,remarks,pulltime) " +
                    "values(?,?,?,?,?,?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setLong(2, bean.getStudentid());
            ps.setLong(3, bean.getBookid());
            ps.setString(4, bean.getRemarks());
            ps.setTimestamp(5, bean.getPulltime());
            ps.setString(6, "NO");
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }
}
