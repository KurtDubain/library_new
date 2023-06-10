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


//��̨servlet�����ڴ���ǰ̨����������
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

		//���û�ȡ�Ĳ����ı����ʽ
		request.setCharacterEncoding("utf-8");

		//��ȡ���Ե�ַ
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		//������Ӧ������ַ�����ʽ
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//��ȡ�������
		PrintWriter writer = response.getWriter();
		//��ȡҳ�������ַ
		String uri = request.getRequestURI();
		String[] s = uri.split("/");
		String method = s[3];

		//��ʼ����ת�ĵ�ַ
		String url = "";

		int pagenum = 1;//��ǰҳ
		int pagesize = 15;//ÿҳ��ʾ������


		//��ʼ�����õ����ݿ��������

		BookmarketDao bookmarketDao = new BookmarketDao();
		BookDao bookDao = new BookDao();
		EvaluateDao evaluateDao = new EvaluateDao();
		CollectionDao collectionDao = new CollectionDao();
		NoticeDao noticeDao = new NoticeDao();
		StudentDao studentDao = new StudentDao();
		TeachingplanDao teachingplanDao = new TeachingplanDao();
		MD5 md5 = new MD5();

		//��Ʒҳ��
		if ("productupdate".equals(method)) {
			//ͨ��ID��ȡ����
			String id = request.getParameter("id");
			Bookmarket bean = bookmarketDao.selectBean(" where id= " + id);
			//�Ѷ��󴫸�jspҳ��
			bean.setHits(bean.getHits() + 1);
			bookmarketDao.updateBean(bean);
			request.setAttribute("bean", bean);

			Evaluate bean2 = evaluateDao.selectBean(" where bookid= " + id);
			request.setAttribute("bean2", bean2);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
			dispatcher.forward(request, response);
		}

		//ͳһ����
		else if ("orderlist".equals(method)) {
			//�鵽book�鵥
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

		//��Ʒҳ��
		else if ("productDelete".equals(method)) {
			//ͨ��ID��ȡ����
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			Student user = (Student)session.getAttribute("user");
			Collection bean = collectionDao.selectBean(" where bookid='"+id+"' and studentid ='"+ user.getIdcard() +"'");
			collectionDao.deleteBean(bean);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/gouwuchelist.jsp");
			dispatcher.forward(request, response);
		}

		//��ת���鿴����ҳ��
		else if("gonggaoupdate".equals(method)){

			//ͨ��ID��ȡ����
			String id = request.getParameter("id");
			Notice bean = noticeDao.selectBean(" where id= "+id);
			//�Ѷ��󴫸�jspҳ��
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gonggao.jsp");
			dispatcher.forward(request, response);
		}

		//�û���¼
		else if("login".equals(method)){
			//��jspҳ���ȡ�û���������
			String idcard =  request.getParameter("idcard");
			String password =  request.getParameter("password");

			//MD5���ܽ�����֤
			password = md5.string2MD5(password);

			//��ѯ�û����������Ƿ�ƥ��
			Student bean = studentDao.selectBean(" where idcard='"+idcard+"' and password ='"+password+"'");
			if(bean!=null){
				HttpSession session = request.getSession();
				session.setAttribute("user", bean);
				writer.print("<script language='javascript'>alert('��¼�ɹ�');window.location.href='"+basePath+".'; </script>");
			}else{
				writer.print("<script  language='javascript'>alert('�û��������������');window.location.href='"+basePath+"login.jsp';</script>");
			}
		}

		//�˳�����
		else if("loginout".equals(method)){
			HttpSession session  =request.getSession();
			session.removeAttribute("user");
			writer.print("<script  language='javascript'>alert('�˳��ɹ�');window.location.href='"+basePath+".';</script>");
		}

		//�޸��������
		else if("passwordupdate2".equals(method)){
			//��JSP��ȡ��Ϣ
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
				writer.print("<script  language='javascript'>alert('����ʧ�ܣ�ԭ�������');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
			}
			else if(password3.equals(password2))
			{
				//md5����
				password2 = md5.string2MD5(password2);
				u.setPassword(password2);
				studentDao.updateBean(u);
				writer.print("<script  language='javascript'>alert('�����ɹ�');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
			}
			else{
				writer.print("<script  language='javascript'>alert('�����������벻һ�£����������룡');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
			}
		}

		//��ҳ�ղز���
		else if("gouwucheadd2".equals(method)){

			HttpSession session = request.getSession();
			Student user = (Student) session.getAttribute("user");
			if (user == null) {
				writer.print("<script  language='javascript'>alert('���ȵ�¼');window.location.href='"+basePath+"login.jsp';</script>");
				return  ;
			}

			Bookmarket pro = bookmarketDao.selectBean(" where id= "+request.getParameter("pid"));

			System.out.print(pro.getId()+"\n");

			Collection bean = collectionDao.selectBean(" where idcard="+user.getIdcard()+" and pid="+pro.getId()+" ");

			if(bean!=null){
				writer.print("<script  language='javascript'>alert('����Ʒ�Ѿ���ӵ����ﳵ�������ظ����');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
				return  ;
			}

			bean = new Collection();
			bean.setStudentid(user.getIdcard());
			bean.setBookid(pro.getId());


			System.out.print(user.getIdcard()+"\n");
			collectionDao.insertBean(bean);
			writer.print("<script  language='javascript'>alert('��ӳɹ�');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
		}


		//�ҵĹ��ﳵ�б�
		else if("gouwuchelist".equals(method)){
			//������ת�ĵ�ַ
			url = "indexServlet/gouwuchelist";
			//��װ��ѯ��SQL���
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			HttpSession session = request.getSession();
			Student user = (Student) session.getAttribute("user");
			sb.append(" studentid="+user.getIdcard());
			String where = sb.toString();



			//��ȡ��ǰ��ҳ��
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//�����ݿ��ѯ�б���Ϣ������ҳ����
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

			//���ظ�jspҳ�����Ϣ
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", listBook);


			//������ת�ĵ�ַ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gouwuchelist.jsp");
			//��ת����
			dispatcher.forward(request, response);
		}

		//��ת���޸ĸ�����Ϣҳ��
		else if("userupdate".equals(method)){

			//ͨ��ID��ȡ����
			HttpSession session = request.getSession();
			Student user = (Student)session.getAttribute("user");
			Student bean = studentDao.selectBean(" where idcard= "+user.getIdcard());
			//�Ѷ��󴫸�jspҳ��
			request.setAttribute("bean", bean);
			request.setAttribute("url", "indexServlet/userupdate2?id="+bean.getIdcard());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userupdate.jsp");
			dispatcher.forward(request, response);
		}

		//�޸ĸ�����Ϣ����
		else if("userupdate2".equals(method)){

			//��JSP��ȡ��Ϣ
			String name =  request.getParameter("name");
			String phone =  request.getParameter("phone");
			String idcard =  request.getParameter("idcard");
			//ͨ��ID��ȡ����
			String id = request.getParameter("id");
			Student bean = studentDao.selectBean(" where idcard= "+idcard);
			//���¶�������
			bean.setName(name);
			bean.setPhone(phone);
			bean.setIdcard(idcard);
			//���²���
			studentDao.updateBean(bean);
			writer.print("<script  language='javascript'>alert('�����ɹ�');window.location.href='"+basePath+"indexServlet/userupdate'; </script>");
		}

	}
}
		
	
			
		
