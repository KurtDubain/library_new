package servlet;

import bean.*;
import dao.*;
import util.MD5;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置获取的参数的编码格式
        request.setCharacterEncoding("utf-8");

        //获取绝对地址
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        //设置响应输出的字符串格式
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        //获取输出对象
        PrintWriter writer = response.getWriter();

        //获取页面请求地址
        String uri = request.getRequestURI();
        String[] s = uri.split("/");
        String method = s[3];

        //初始化跳转的地址
        String url = "";

        int pagenum = 1;//当前页
        int pagesize = 15;//每页显示的数量


        //初始化调用的数据库操作对象

        TeacherDao teacherDao = new TeacherDao();
        CourseDao courseDao = new CourseDao();
        TeachingplanDao teachingplanDao = new TeachingplanDao();
        MajorlistDao majorlistDao = new MajorlistDao();
        BookDao bookDao = new BookDao();
        MD5 md5 = new MD5();

        if("login".equals(method)){
            //从jsp页面获取用户名和密码
            String idcard =  request.getParameter("idcard");
            String password =  request.getParameter("password");

            //MD5加密进行验证
            password = md5.string2MD5(password);

            //查询用户名和密码是否匹配
            Teacher bean = teacherDao.selectBean(" where idcard='"+idcard+"' and password ='"+password+"'");
            List<Teachingplan> TeachingplanList = new ArrayList<Teachingplan>();
            List<String> periodList = new ArrayList<String>();
            List<Course> course = new ArrayList<Course>();
            List<String> courseList = new ArrayList<String>();
            List<String> majorList = new ArrayList<String>();
            if(bean!=null){

                HttpSession session = request.getSession();
                session.setAttribute("teacher", bean);

                //初始化界面
                Long id = bean.getId();
                course = courseDao.getList(" where teacherid='"+id+"'");

                for(Course i:course){
                    courseList.add(i.getCoursename());
                    System.out.print("\n"+ "getCoursename"+i.getCoursename());
                }

                for(Course i:course){
                    Teachingplan teachingplan = teachingplanDao.selectBean(" where courseid='"+i.getId()+"'");
                    TeachingplanList.add(teachingplan);
                }


                //学期初始化
                for(Teachingplan i:TeachingplanList){
                    String period = i.getPeriod();
                    periodList.add(period);
                    System.out.print("\n"+ "dperiod"+period);
                }

                //专业初始化
                for(Teachingplan i:TeachingplanList){
                    Long major = i.getMajorid();
                    Majorlist major1 = majorlistDao.selectBean(" where id='"+major+"'");
                    majorList.add(major1.getMajor());
                    System.out.print("\n"+ "major1.getMajor());"+major1.getMajor());
                }


                List<List> all = new ArrayList<List>();

                for(int i=0;i<courseList.size();i++){
                    List<String> temp = new ArrayList<String>();
                    temp.add(courseList.get(i));
                    temp.add(periodList.get(i));
                    temp.add(majorList.get(i));
                    all.add(temp);
                }

                request.setAttribute("all", all);
//                request.setAttribute("periodList", periodList);
//                request.setAttribute("majorList", majorList);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/teacherIndex.jsp");
                dispatcher.forward(request, response);
            }else{
                writer.print("<script  language='javascript'>alert('Wrong user name or password!');window.location.href='"+basePath+"teacherLogin.jsp';</script>");
            }
        }

        //退出操作
        else if("loginout".equals(method)){
            HttpSession session  =request.getSession();
            session.removeAttribute("manage");
            writer.print("<script  language='javascript' type=\"text/javascript\" charset=\"utf-8\">alert('Exit successful!');window.location.href='"+basePath+"login.jsp';</script>");
        }

        //修改密码操作
        else if("passwordupdate".equals(method)){

            //从JSP获取信息
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            String password3 = request.getParameter("password3");
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher)session.getAttribute("teacher");

            System.out.print("\n"+"password1"+password1);

            password1 = md5.string2MD5(password1);

            System.out.print("\n"+"password1"+password1);

            Teacher u = TeacherDao.selectBean(" where name='"+teacher.getName()+"' and password='"+password1+"'  ");

            System.out.print("\n"+"teacher.getName()"+teacher.getName());
            System.out.print("\n"+"u"+u.getIdcard());

            if(u==null)
            {
                System.out.print("Operation failed. The original password is incorrect！");
                writer.print("<script  language='javascript' type=\"text/javascript\" charset=\"utf-8\">alert('Operation failed. The original password is incorrect！');window.location.href='"+basePath+"teacherPasswordupdate.jsp'; </script>");
            }
            else if(password3.equals(password2))
            {
                //md5加密
                password2 = md5.string2MD5(password2);
                u.setPassword(password2);
                teacherDao.updateBean(u);
                writer.print("<script  language='javascript' type=\"text/javascript\" charset=\"utf-8\">alert('Operation success!');window.location.href='"+basePath+"teacherPasswordupdate.jsp'; </script>");
            }
            else{
                writer.print("<script  language='javascript' type=\"text/javascript\" charset=\"utf-8\">alert('The two passwords are inconsistent, please re-enter！');window.location.href='"+basePath+"teacherPasswordupdate.jsp'; </script>");
            }
        }

        //图书设置操作
        else if("bookSet".equals(method)){
            //通过ID获取对象
            String coursename = request.getParameter("coursename");
            //把对象传给jsp页面
            request.setAttribute("coursename", coursename);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bookSet.jsp");
            dispatcher.forward(request, response);
        }

        //图书设置操作
        else if("bookSet2".equals(method)){
            //把获取到的数据写入数据库
            String bookname =  request.getParameter("bookname");
            double price = Double.parseDouble(request.getParameter("price"));
            String publisher =  request.getParameter("publisher");
            String author =  request.getParameter("author");
            String coursename =  request.getParameter("coursename");

            Course course = courseDao.selectBean(" where coursename='"+coursename+"'");

            System.out.print("\n"+"bookname"+bookname);

            Book bean = new Book();
            bean.setBookname(bookname);
            bean.setPrice(price);
            bean.setPublisher(publisher);
            bean.setAuthor(author);
            bean.setCourseid(course.getId());

            bookDao.insertBean(bean);
            writer.print("<script  language='javascript'>alert('Insert Susscceed!');window.location.href='"+basePath+"bookSet.jsp';</script>");
        }

    }
}

