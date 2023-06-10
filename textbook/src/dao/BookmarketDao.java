package dao;

import bean.Bookmarket;
import bean.Collection;
import bean.Notice;
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


public class BookmarketDao {


    @SuppressWarnings("unchecked")
    public static Map<String, List<Bookmarket>> getList(int pagenum, int pagesize, String url, String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Bookmarket> list = new ArrayList<Bookmarket>();

        try {
            String sql = "SELECT * from bookmarket"+where;
            conn = DBConn.getConn();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Bookmarket bean = new Bookmarket();
                bean.setId(rs.getLong("id"));
                bean.setCategoryid(rs.getString("categoryid"));
                bean.setBookname(rs.getString("bookname"));
                bean.setHits(rs.getLong("hits"));
                bean.setCover(rs.getString("cover"));
                bean.setPrice(rs.getDouble("price"));
                list.add(bean);
                System.out.print(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(conn, ps, null);
        }
        int currentpage = pagenum;
        Fenye pm = new Fenye(list, pagesize);

        List<Bookmarket> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String, List<Bookmarket>> map = new HashMap<String, List<Bookmarket>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "Total:" + total + "  books"), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Bookmarket> list2 = map.get(pagerinfo);
        if (list2 == null) {
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }

    public Bookmarket selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Bookmarket bean =null;
        try{
            String sql = "SELECT * from bookmarket "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Bookmarket();
                bean.setId(rs.getLong("id"));
                bean.setCategoryid(rs.getString("categoryid"));
                bean.setBookname(rs.getString("bookname"));
                bean.setHits(rs.getLong("hits"));
                bean.setCover(rs.getString("cover"));
                bean.setPrice(rs.getDouble("price"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return bean;
    }

    public void updateBean(Bookmarket bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "update  bookmarket set categoryid=?,cover=?,bookname=?,hits=?,price=? where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getCategoryid());
            ps.setString(2, bean.getCover());
            ps.setString(3, bean.getBookname());
            ps.setLong(4, bean.getHits());
            ps.setDouble(5, bean.getPrice());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void deleteBean(Bookmarket bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from bookmarket  where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }
}