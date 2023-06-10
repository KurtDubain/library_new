<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<body>
<div id="panelwrap">
    <%@ include file="head.jsp" %>
    <div class="center_content">
        <div id="right_wrap">
            <div id="right_content">
                <h2>收藏</h2>
                <table id="rounded-corner">
                    <thead>
                    <tr>
                        <th>商品名</th>
                        <th>图片</th>
                        <th>分类名</th>
                        <th>商品价格</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <td colspan="12">${pagerinfo }</td>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach items="${list}" var="bean">
                        <tr class="odd">
                            <td align="center">${bean.bookname }</td>
                            <td align="center">
                                <img src="<%=basePath %>uploadfile/${bean.cover }" width="100" height="100" />
                            </td>
                            <td align="center">${bean.categoryid }</td>
                            <td align="center">￥${bean.price }</td>
                            <td align="center">
                                <a href="indexServlet/productupdate?id=${bean.id }">查看详情</a> &nbsp;
                                <a href="indexServlet/productDelete?id=${bean.id }">删除</a> &nbsp;
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div><!-- end of right content-->
        <%@ include file="left.jsp" %>
        <div class="clear"></div>
    </div> <!--end of center_content-->
    <div class="footer">
        <%--        <a href="manage/login.jsp">管理后台</a>--%>
    </div>
</div>
</body>

</html>
