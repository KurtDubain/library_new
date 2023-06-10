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
</head>


<body>
<div id="panelwrap">
    <%@ include file="teacherHead.jsp" %>
    <div class="center_content">

        <div id="right_wrap">
            <div id="right_content">
                <h2>课本信息</h2>
                <div id="tab1" class="tabcontent">
                    <form action="teacherServlet/bookSet2" method="post">
                        <div class="form">
                            <c:if test="${coursename!=null}">
                                <div class="form_row">
                                    <label>课程:</label>
                                    <input type="text" class="form_input" name="coursename" id="coursename" value=${coursename} name="coursename" id="coursename"/>
                                </div>
                            </c:if>
                            <div class="form_row">
                                <label>书名:</label>
                                <input type="text" class="form_input" name="bookname" id="bookname" />
                            </div>
                            <div class="form_row">
                                <label>价格:</label>
                                <input type="text" class="form_input" name="price"  id="price"/>
                            </div>
                            <div class="form_row">
                                <label>出版商:</label>
                                <input type="text" class="form_input" name="publisher"  id="publisher"/>
                            </div>
                            <div class="form_row">
                                <label>作者:</label>
                                <input type="text" class="form_input" name="author"  id="author"/>
                            </div>
<%--                            <div class="form_row">--%>
<%--                                <label>封面:</label>--%>
<%--                                <input type="text" class="form_input" name="password3"  id="password3"/>--%>
<%--                            </div>--%>
                            <div class="form_row">
                                <input type="submit" class="form_submit" value="提交" />
                            </div>
                            <div class="clear"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div><!-- end of right content-->
        <input type="button" class="form_submit" value="返回"  onclick="javascript:history.go(-2);"/>
        <div class="clear"></div>
    </div> <!--end of center_content-->

</div>
</body>
</html>

