package dao;

import bean.Course;
import bean.Teachingplan;
import util.DBConn;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeachingplanDao {

    public static Teachingplan selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teachingplan bean =null;
        try{
            String sql = "SELECT * from teachingplan "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Teachingplan();
                bean.setPeriod(rs.getString("period"));
                bean.setCourseid(rs.getLong("courseid"));
                bean.setMajorid(rs.getLong("majorid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return bean;
    }

    public List<Teachingplan> getList(String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teachingplan> list = new ArrayList<Teachingplan>();
        try {
            String sql = "SELECT * from teachingplan " + where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Teachingplan bean = new Teachingplan();
                bean.setPeriod(rs.getString("period"));
                bean.setCourseid(rs.getLong("courseid"));
                bean.setMajorid(rs.getLong("majorid"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(conn, ps, null);
        }
        return list;
    }

}
