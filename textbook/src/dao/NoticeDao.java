package dao;

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


public class NoticeDao {



    public void insertBean(Notice bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into notice(title,notice,time1) values(?,?,?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getNotice());
            ps.setString(3, bean.getTime1());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void updateBean(Notice bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "update  notice set title=?,notice=?,time1=? where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getNotice());
            ps.setString(3, bean.getTime1());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void deleteBean(Notice bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from   notice  where id= "+bean.getId();
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
    public Map<String,List<Notice>> getList(int pagenum, int pagesize , String url, String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Notice> list = new ArrayList<Notice>();
        try{
            String sql = "SELECT * from notice "+where ;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                Notice bean = new Notice();
                bean.setId(rs.getInt("id"));
                bean.setTitle(rs.getString("title"));
                bean.setNotice(rs.getString("notice"));
                bean.setTime1(rs.getString("time1"));
                list.add(bean);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        int currentpage = pagenum;
        Fenye pm = new Fenye(list, pagesize);

        List<Notice> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String,List<Notice>> map = new HashMap<String,List<Notice>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "totals: " + total + "."), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Notice> list2 = map.get(pagerinfo);
        if(list2==null){
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }


    public List<Notice> getList(String where ){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Notice> list = new ArrayList<Notice>();
        try{
            String sql = "SELECT * from notice "+where ;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                Notice bean = new Notice();
                bean.setId(rs.getInt("id"));
                bean.setTitle(rs.getString("title"));
                bean.setNotice(rs.getString("notice"));
                bean.setTime1(rs.getString("time1"));
                list.add(bean);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }


        return list;

    }


    public Notice selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Notice bean =null;
        try{
            String sql = "SELECT * from notice "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Notice();
                bean.setId(rs.getInt("id"));
                bean.setTitle(rs.getString("title"));
                bean.setNotice(rs.getString("notice"));
                bean.setTime1(rs.getString("time1"));
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
            String sql = "SELECT count(*) from notice "+where;
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