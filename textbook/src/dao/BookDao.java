package dao;

import bean.Book;
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


public class BookDao {

    public void insertBean(Book bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "insert into book(id,bookname,image,price,publisher,author,courseid) values(?,?,?,?,?,?,?)";
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, bean.getId());
            ps.setString(2, bean.getBookname());
            ps.setString(3, bean.getImage());
            ps.setDouble(4, bean.getPrice());
            ps.setString(5, bean.getPublisher());
            ps.setString(6, bean.getAuthor());
            ps.setLong(7, bean.getCourseid());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }

    public void deleteBean(Book bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "delete from  book  where id="+bean.getId();
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
    public static Map<String, List<Book>> getList(int pagenum, int pagesize, String url, String where) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> list = new ArrayList<Book>();

        try {
            String sql = "SELECT * from book"+where;
            conn = DBConn.getConn();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs != null && rs.next()) {
                Book bean = new Book();
                bean.setId(rs.getLong("id"));
                bean.setBookname(rs.getString("bookname"));
                bean.setImage(rs.getString("image"));
                bean.setPrice(rs.getDouble("price"));
                bean.setPublisher(rs.getString("publisher"));
                bean.setAuthor(rs.getString("author"));
                bean.setCourseid(rs.getLong("courseid"));
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

        List<Book> fenyelist = pm.getObjects(currentpage);
        int total = list.size();
        Map<String, List<Book>> map = new HashMap<String, List<Book>>();
        map.put(Pager.getPagerNormal(total, pagesize,
                currentpage, url, "Total:" + total + "  books"), fenyelist);
        String pagerinfo = map.keySet().iterator().next();
        List<Book> list2 = map.get(pagerinfo);
        if (list2 == null) {
            map.remove(pagerinfo);
            map.put(pagerinfo, list);
        }
        return map;
    }

    public Book selectBean(String where){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book bean =null;
        try{
            String sql = "SELECT * from book "+where;
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs!=null &&rs.next()){
                bean = new Book();
                bean.setId(rs.getLong("id"));
                bean.setBookname(rs.getString("bookname"));
                bean.setImage(rs.getString("image"));
                bean.setPrice(rs.getDouble("price"));
                bean.setPublisher(rs.getString("publisher"));
                bean.setAuthor(rs.getString("author"));
                bean.setCourseid(rs.getLong("courseid"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
        return bean;
    }

    public void updateBean(Book bean){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "update  bookmarket set bookname=?,image=?,price=?,publisher=?,author=? where id= "+bean.getId();
            conn = DBConn.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bean.getBookname());
            ps.setString(2, bean.getImage());
            ps.setDouble(3, bean.getPrice());
            ps.setString(4, bean.getPublisher());
            ps.setString(5, bean.getAuthor());
            ps.setLong(6, bean.getCourseid());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBConn.close(conn, ps, null);
        }
    }
}