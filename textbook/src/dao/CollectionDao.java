package dao;

import util.DBConn;
import bean.Collection;
import util.Fenye;
import util.Pager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CollectionDao {

    public void deleteBean(Collection bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from collection where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public Collection selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Collection bean =null;
        try{
            String sql = "SELECT * from collection "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Collection();
                bean.setId(rs.getLong("id"));
                bean.setStudentid(rs.getString("studentid"));
                bean.setBookid(rs.getLong("bookid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return bean;
    }

    public void insertBean(Collection bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into collection(id,studentid,bookid) values(?,?,?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, bean.getId());
            ps.setString(2, bean.getStudentid());
            ps.setLong(3, bean.getBookid());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, List<Collection>> getList(int pagenum, int pagesize , String url, String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Collection> list = new ArrayList<Collection>();
        try{
            String sql = "SELECT * from collection "+where ;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs!=null &&rs.next()){
                Collection bean = new Collection();
                bean.setId(rs.getLong("id"));
                bean.setStudentid(rs.getString("studentid"));
                bean.setBookid(rs.getLong("bookid"));
                list.add(bean);
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        int currentpage = pagenum;
        Fenye pm = new Fenye(list, pagesize);
        List<Collection> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String,List<Collection>> map = new HashMap<String,List<Collection>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "totals:" + total + "."), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Collection> list2 = map.get(pagerinfo);
        if(list2==null){
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }
}