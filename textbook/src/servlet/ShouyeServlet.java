package servlet;

import bean.Bookmarket;
import dao.BookmarketDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//首页Servlet
public class ShouyeServlet extends HttpServlet {
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

		//分页页数定义
		int pagenum =1;//当前页
		int pagesize = 10;//每页显示的数量

		BookmarketDao bookmarketDao = new BookmarketDao();

		String bookname = request.getParameter("bookname");
		String categoryid = request.getParameter("categoryid");

		String url = "shouye";

		if(request.getParameter("pagenum")!=null){
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" where  ");

		if(bookname!=null&&!"".equals(bookname)){
			sb.append(" bookname like '%"+bookname+"%' ");
			sb.append(" and ");
			request.setAttribute("bookname", bookname);
		}

		if(categoryid!=null&&!"".equals(categoryid)){
			sb.append(" categoryid like '%"+categoryid+"%' ");
			sb.append(" and ");
			request.setAttribute("categoryid", categoryid);
		}

		sb.append(" 1=1  order by id desc ");
		String where = sb.toString();


		Map<String,List<Bookmarket>> map = BookmarketDao.getList(pagenum,pagesize,url,where);


		String pagerinfo = map.keySet().iterator().next();
		List<Bookmarket> list = map.get(pagerinfo);



		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("list", list);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
