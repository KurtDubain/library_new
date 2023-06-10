<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>高校教材订购教师端</title>
<link rel="stylesheet" type="text/css" href="style.css" />

<!-- jQuery file -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var $ = jQuery.noConflict();
$(function() {
$('#tabsmenu').tabify();
$(".toggle_container").hide(); 
$(".trigger").click(function(){
	$(this).toggleClass("active").next().slideToggle("slow");
	return false;
});
});
</script>
</head>
<body>
<div id="panelwrap">
    <%@ include file="teacherHead.jsp" %>
    <div class="center_content">
        <div id="right_wrap">
            <div id="right_content">
                <h2>课程列表</h2>
                <table id="rounded-corner">
                    <thead>
                    <tr>
                        <th align="center">课程名</th>
                        <th align="center">学期</th>
                        <th align="center">专业</th>
                        <th align="center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List list=(List)request.getAttribute("all");
                        for(int i=0;i<list.size();i++){
                            List e=(List)list.get(i);
                    %>
                        <tr class="odd">
                            <td align="center"><%=e.get(0) %></td>
                            <td align="center"><%=e.get(1) %></td>
                            <td align="center"><%=e.get(2) %></td>
                            <td align="center">
                                <a href="teacherServlet/bookSet?coursename=<%=e.get(0) %>">设置书籍</a> &nbsp;
                            </td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div><!-- end of right content-->
        <div class="clear"></div>
    </div> <!--end of center_content-->
</div>
</body>
</html>