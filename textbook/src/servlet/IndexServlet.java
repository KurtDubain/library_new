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


//后台servlet，用于处理前台的所有请求
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}


	public void destroy() {

		super.destroy();
	}


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
		response.setContentType("text/html; charset=utf-8");
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

		BookmarketDao bookmarketDao = new BookmarketDao();
		BookDao bookDao = new BookDao();
		EvaluateDao evaluateDao = new EvaluateDao();
		CollectionDao collectionDao = new CollectionDao();
		NoticeDao noticeDao = new NoticeDao();
		StudentDao studentDao = new StudentDao();
		TeachingplanDao teachingplanDao = new TeachingplanDao();
		MD5 md5 = new MD5();

		//商品页面
		if ("productupdate".equals(method)) {
			//通过ID获取对象
			String id = request.getParameter("id");
			Bookmarket bean = bookmarketDao.selectBean(" where id= " + id);
			//把对象传给jsp页面
			bean.setHits(bean.getHits() + 1);
			bookmarketDao.updateBean(bean);
			request.setAttribute("bean", bean);

			Evaluate bean2 = evaluateDao.selectBean(" where bookid= " + id);
			request.setAttribute("bean2", bean2);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
			dispatcher.forward(request, response);
		}

		//统一订购
		else if ("orderlist".equals(method)) {
			//查到book书单
			HttpSession session = request.getSession();
			Student user = (Student)session.getAttribute("user");
			List<Teachingplan> teachingplanList = teachingplanDao.getList(" where majorid= " + user.getMajorid());
			List<Book> bookList = new ArrayList<Book>();
			for(Teachingplan i :teachingplanList){
				Book bean = bookDao.selectBean(" where courseid= " + i.getCourseid());
				bookList.add(bean);
			}

			request.setAttribute("bookList", bookList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/orderlist.jsp");
			dispatcher.forward(request, response);
		}

		//商品页面
		else if ("productDelete".equals(method)) {
			//通过ID获取对象
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			Student user = (Student)session.getAttribute("user");
			Collection bean = collectionDao.selectBean(" where bookid='"+id+"' and studentid ='"+ user.getIdcard() +"'");
			collectionDao.deleteBean(bean);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/gouwuchelist.jsp");
			dispatcher.forward(request, response);
		}

		//跳转到查看公告页面
		else if("gonggaoupdate".equals(method)){

			//通过ID获取对象
			String id = request.getParameter("id");
			Notice bean = noticeDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gonggao.jsp");
			dispatcher.forward(request, response);
		}

		//用户登录
		else if("login".equals(method)){
			//从jsp页面获取用户名和密码
			String idcard =  request.getParameter("idcard");
			String password =  request.getParameter("password");

			//MD5加密进行验证
			password = md5.string2MD5(password);

			//查询用户名和密码是否匹配
			Student bean = studentDao.selectBean(" where idcard='"+idcard+"' and password ='"+password+"'");
			if(bean!=null){
				HttpSession session = request.getSession();
				session.setAttribute("user", bean);
				writer.print("<script language='javascript'>alert('登录成功');window.location.href='"+basePath+".'; </script>");
			}else{
				writer.print("<script  language='javascript'>alert('用户名或者密码错误');window.location.href='"+basePath+"login.jsp';</script>");
			}
		}

		//退出操作
		else if("loginout".equals(method)){
			HttpSession session  =request.getSession();
			session.removeAttribute("user");
			writer.print("<script  language='javascript'>alert('退出成功');window.location.href='"+basePath+".';</script>");
		}

		//修改密码操作
		else if("passwordupdate2".equals(method)){
			//从JSP获取信息
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String password3 = request.getParameter("password3");
			HttpSession session = request.getSession();
			Student user = (Student)session.getAttribute("user");

			System.out.print(password1+"\n");
			password1 = md5.string2MD5(password1);

			System.out.print(user.getName()+"\n");
			System.out.print(password1+"\n");
			Student u = StudentDao.selectBean(" where name='"+user.getName()+"' and password='"+password1+"'  ");
			if(u==null)
			{
				writer.print("<script  language='javascript'>alert('操作失败，原密码错误！');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
			}
			else if(password3.equals(password2))
			{
				//md5加密
				password2 = md5.string2MD5(password2);
				u.setPassword(password2);
				studentDao.updateBean(u);
				writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
			}
			else{
				writer.print("<script  language='javascript'>alert('两次密码输入不一致，请重新输入！');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
			}
		}

		//首页收藏操作
		else if("gouwucheadd2".equals(method)){

			HttpSession session = request.getSession();
			Student user = (Student) session.getAttribute("user");
			if (user == null) {
				writer.print("<script  language='javascript'>alert('请先登录');window.location.href='"+basePath+"login.jsp';</script>");
				return  ;
			}

			Bookmarket pro = bookmarketDao.selectBean(" where id= "+request.getParameter("pid"));

			System.out.print(pro.getId()+"\n");

			Collection bean = collectionDao.selectBean(" where idcard="+user.getIdcard()+" and pid="+pro.getId()+" ");

			if(bean!=null){
				writer.print("<script  language='javascript'>alert('该商品已经添加到购物车，请勿重复添加');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
				return  ;
			}

			bean = new Collection();
			bean.setStudentid(user.getIdcard());
			bean.setBookid(pro.getId());


			System.out.print(user.getIdcard()+"\n");
			collectionDao.insertBean(bean);
			writer.print("<script  language='javascript'>alert('添加成功');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
		}


		//我的购物车列表
		else if("gouwuchelist".equals(method)){
			//定义跳转的地址
			url = "indexServlet/gouwuchelist";
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			HttpSession session = request.getSession();
			Student user = (Student) session.getAttribute("user");
			sb.append(" studentid="+user.getIdcard());
			String where = sb.toString();



			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String, List<Collection>> map = collectionDao.getList(1,999,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Collection> list = map.get(pagerinfo);
			List<Bookmarket> listBook=new ArrayList<Bookmarket> ();;

			for(Collection  i:list){
				StringBuffer sql = new StringBuffer();
				sql.append(" where  ");
				sql.append(" id="+i.getBookid());
				String whereSql = sql.toString();
				Bookmarket bean = bookmarketDao.selectBean(whereSql);
				listBook.add(bean);
			}

			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", listBook);


			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gouwuchelist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}

		//跳转到修改个人信息页面
		else if("userupdate".equals(method)){

			//通过ID获取对象
			HttpSession session = request.getSession();
			Student user = (Student)session.getAttribute("user");
			Student bean = studentDao.selectBean(" where idcard= "+user.getIdcard());
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("url", "indexServlet/userupdate2?id="+bean.getIdcard());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userupdate.jsp");
			dispatcher.forward(request, response);
		}

		//修改个人信息操作
		else if("userupdate2".equals(method)){

			//从JSP获取信息
			String name =  request.getParameter("name");
			String phone =  request.getParameter("phone");
			String idcard =  request.getParameter("idcard");
			//通过ID获取对象
			String id = request.getParameter("id");
			Student bean = studentDao.selectBean(" where idcard= "+idcard);
			//更新对象属性
			bean.setName(name);
			bean.setPhone(phone);
			bean.setIdcard(idcard);
			//更新操作
			studentDao.updateBean(bean);
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"indexServlet/userupdate'; </script>");
		}

	}
}
		
	
			
		
