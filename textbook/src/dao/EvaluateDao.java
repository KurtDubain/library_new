package dao;

import bean.Evaluate;
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


public class EvaluateDao {


    public Evaluate selectBean(String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Evaluate bean = null;
        try {
            String sql = "SELECT * from evaluate " + where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                bean = new Evaluate();
                bean.setId(rs.getLong("id"));
                bean.setStudentid(rs.getString("studentid"));
                bean.setBookid(rs.getLong("bookid"));
                bean.setEvaluation(rs.getString("evaluation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(conn, ps, null);
        }
        return bean;
    }
}