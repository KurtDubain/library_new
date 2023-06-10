<%@ page language="java" import="java.util.*,dao.*,bean.*" pageEncoding="UTF-8"%>
<div class="sidebar" id="sidebar">
    <h2>搜索</h2>
    <form action="./" method="post">
        <br/>
        商品名：<input type="text" name="bookname" value="${bookname}" size=10/>
        <input type="submit" value="搜索" />
    </form>
    <h2>分类</h2>
    <ul>
        <%
        CategoryDao categoryDao = new CategoryDao();
        List<Category> list = categoryDao.getList("");
        for(Category fl:list){
            %>
                <li><a href="./?categoryid=<%=fl.getId() %>"><%=fl.getCategory() %></a></li>
            <%
        }
        %>
    </ul>

    <h2>公告</h2>
    <ul>
        <%
            NoticeDao gonggaoDao = new NoticeDao();
            Map<String,List<Notice>> map2 = gonggaoDao.getList(1,5,""," order by id desc ");
            String pagerinfo2 = map2.keySet().iterator().next();
            List<Notice> list3 = map2.get(pagerinfo2);
            for(Notice g:list3){
                %>
                    <li><a href="indexServlet/gonggaoupdate?id=<%=g.getId() %>" ><%=g.getTitle() %></a></li>
                <%
            }
        %>
    </ul>
</div>
