package dao;

import bean.Teacher;
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


public class TeacherDao {

    public void insertBean(Teacher bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into teacher(name,password,idcard,phone) values(?,?,?,?,?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getIdcard());
            ps.setString(4, bean.getPhone());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void updateBean(Teacher bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "update  teacher set password=?,name=?,phone=?,idcard=? where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getPassword());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getPhone());
            ps.setString(4, bean.getIdcard());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }


    public void deleteBean(Teacher bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from  teacher  where id="+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }


    @SuppressWarnings("unchecked")
    public Map<String,List<Teacher>> getList(int pagenum, int pagesize , String url, String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<Teacher>();
        try {
            String sql = "SELECT * from teacher " + where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Teacher bean = new Teacher();
                bean.setId(rs.getInt("id"));
                bean.setPhone(rs.getString("phone"));
                bean.setPassword(rs.getString("password"));
                bean.setName(rs.getString("name"));
                bean.setIdcard(rs.getString("idcard"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(conn, ps, null);
        }
        int currentpage = pagenum;
        Fenye pm = new Fenye(list, pagesize);

        List<Teacher> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String, List<Teacher>> map = new HashMap<String, List<Teacher>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "totals: " + total + "."), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Teacher> list2 = map.get(pagerinfo);
        if (list2 == null) {
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }


    public static Teacher selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher bean =null;
        try{
            String sql = "SELECT * from teacher "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Teacher();
                bean.setId(rs.getInt("id"));
                bean.setIdcard(rs.getString("idcard"));
                bean.setPhone(rs.getString("phone"));
                bean.setPassword(rs.getString("password"));
                bean.setName(rs.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return bean;
    }


    public int selectBeancount(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try{
            String sql = "SELECT count(*) from teacher "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs!=null){
                count = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return count;
    }

}

