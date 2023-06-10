package dao;

import bean.Course;
import util.DBConn;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CourseDao {

    public static Course selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course bean =null;
        try{
            String sql = "SELECT * from course "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Course();
                bean.setId(rs.getInt("id"));
                bean.setCoursename(rs.getString("coursename"));
                bean.setTeacherid(rs.getLong("teacherid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return bean;
    }

    public List<Course> getList(String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<Course>();
        try {
            String sql = "SELECT * from course " + where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Course bean = new Course();
                bean.setId(rs.getInt("id"));
                bean.setCoursename(rs.getString("coursename"));
                bean.setTeacherid(rs.getLong("teacherid"));
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
