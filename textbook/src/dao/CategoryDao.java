package dao;

import bean.Category;
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


public class CategoryDao {


    public void insertBean(Category bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into category(category) values(?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getCategory());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }


    public void updateBean(Category bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "update  category set category=? where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getCategory());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void deleteBean(Category bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from   category  where id= "+bean.getId();
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
    public Map<String,List<Category>> getList(int pagenum, int pagesize , String url, String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<Category>();
        try{
            String sql = "SELECT * from category "+where ;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                Category bean = new Category();
                bean.setId(rs.getLong("id"));
                bean.setCategory(rs.getString("category"));
                list.add(bean);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        int currentpage = pagenum;
        Fenye pm = new Fenye(list, pagesize);

        List<Category> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String,List<Category>> map = new HashMap<String,List<Category>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "totals:" + total + " thing"), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Category> list2 = map.get(pagerinfo);
        if(list2==null){
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }


    public List<Category> getList(String where ){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<Category>();
        try{
            String sql = "SELECT * from category "+where ;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                Category bean = new Category();
                bean.setId(rs.getLong("id"));
                bean.setCategory(rs.getString("category"));
                list.add(bean);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return list;
    }


    public Category selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category bean =null;
        try{
            String sql = "SELECT * from category "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Category();
                bean.setId(rs.getInt("id"));
                bean.setCategory(rs.getString("category"));
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
            String sql = "SELECT count(*) from t_Fenlei "+where;
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
