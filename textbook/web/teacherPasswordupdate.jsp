<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
    <script type="text/javascript" language="javascript">
        function checkform(){
            if(document.getElementById("password1id").value==""){
                alert('原密码不能为空');
                return false;
            }
            if(document.getElementById("password2id").value==""){
                alert('新密码不能为空');
                return false;
            }
            if(document.getElementById("password3id").value!=document.getElementById("password2id").value){
                alert('确认新密码和新密码不一致');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div id="panelwrap">
    <%@ include file="teacherHead.jsp" %>
    <div class="center_content">
        <div id="right_wrap">
            <div id="right_content">
                <h2>修改密码</h2>
                <div id="tab1" class="tabcontent">
                    <form action="teacherServlet/passwordupdate" method="post" onsubmit="return checkform()">
                        <div class="form">
                            <div class="form_row">
                                <label>原密码:</label>
                                <input type="password" class="form_input" name="password1" id="password1" />
                            </div>
                            <div class="form_row">
                                <label>新密码:</label>
                                <input type="password" class="form_input" name="password2"  id="password2"/>
                            </div>
                            <div class="form_row">
                                <label>确认新密码:</label>
                                <input type="password" class="form_input" name="password3"  id="password3"/>
                            </div>
                            <div class="form_row">
                                <input type="submit" class="form_submit" value="提交" />
                            </div>
                            <div class="clear"></div>
                        </div>
                    </form>

                </div>
            </div>
        </div><!-- end of right content-->

        <div class="clear"></div>
    </div> <!--end of center_content-->
</div>
</body>
</html>
