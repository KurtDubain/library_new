package dao;

import bean.Student;
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


public class StudentDao {

    public void insertBean(Student bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into student(name,password,idcard,phone,majorid) values(?,?,?,?,?,?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getIdcard());
            ps.setString(4, bean.getPhone());
            ps.setLong(5, bean.getMajorid());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void updateBean(Student bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "update  student set password=?,name=?,phone=?,majorid=?,idcard=? where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getPassword());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getPhone());
            ps.setLong(4, bean.getMajorid());
            ps.setString(5, bean.getIdcard());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }


    public void deleteBean(Student bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from  student  where id="+bean.getId();
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
    public Map<String,List<Student>> getList(int pagenum, int pagesize , String url, String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<Student>();
        try {
            String sql = "SELECT * from student " + where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Student bean = new Student();
                bean.setId(rs.getInt("id"));
                bean.setPhone(rs.getString("phone"));
                bean.setPassword(rs.getString("password"));
                bean.setName(rs.getString("name"));
                bean.setIdcard(rs.getString("idcard"));
                bean.setMajorid(rs.getLong("majorid"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(conn, ps, null);
        }
        int currentpage = pagenum;
        Fenye pm = new Fenye(list, pagesize);

        List<Student> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String, List<Student>> map = new HashMap<String, List<Student>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "共有" + total + "条记录"), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Student> list2 = map.get(pagerinfo);
        if (list2 == null) {
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }


    public static Student selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student bean =null;
        try{
            String sql = "SELECT * from student "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Student();
                bean.setId(rs.getInt("id"));
                bean.setIdcard(rs.getString("idcard"));
                bean.setPhone(rs.getString("phone"));
                bean.setPassword(rs.getString("password"));
                bean.setName(rs.getString("name"));
                bean.setMajorid(rs.getLong("majorid"));
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
            String sql = "SELECT count(*) from student "+where;
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
