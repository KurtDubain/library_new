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
<link rel="stylesheet" type="text/css" href="style.css" />

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
    
    <%@ include file="head.jsp" %>         
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
    <h2>信息详情</h2> 
                    

    <div id="tab1" class="tabcontent">
        <form action="indexServlet/gouwucheadd2?pid=${bean.id }" method="post" >
        <div class="form">
            <div class="form_row">
            <label>商品名:</label>
           ${bean.bookname }
            </div>-
            <div class="form_row">
                <label>商品图片:</label>
                <img src="<%=basePath %>uploadfile/${bean.cover }" width="300" height="300" />
            </div>
            <div class="form_row">
             <label>分类名:</label>
                ${bean.categoryid }
            </div>
            <div class="form_row">
                <label>商品价格:</label>
                ￥ ${bean.price }
            </div>
            <div class="form_row">
                <label>商品点击数:</label>
                ${bean.hits }
            </div>
            <div class="form_row">
                <label>评论详情:</label>
                <br/>
                <br/>
                <table id="rounded-corner">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>评论</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr class="odd">
                            <td align="center">${bean2.studentid}</td>
                            <td align="center">${bean2.evaluation}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="form_row">
                <input type="submit" class="form_submit" onclick="return confirm('确定要加入收藏吗?'); " value="加入收藏" />
            </div>
            <div class="form_row">
                <input type="button" class="form_submit" value="返回"  onclick="javascript:history.go(-1);"/>
            </div>
            <div class="clear"></div>
        </div>
        </form>
        
    </div>
      
     </div>
     </div><!-- end of right content-->
                     
                    
    <%@ include file="left.jsp" %>

    <div class="clear"></div>
    </div> <!--end of center_content-->
    
    <div class="footer">
        <a href="manage/login.jsp">管理后台</a>
        <a href="teacherLogin.jsp">老师登录</a>
    </div>

</div>
</body>
</html>
