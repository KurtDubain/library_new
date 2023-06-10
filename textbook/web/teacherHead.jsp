<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="header">
    <div class="title">
    <span style="font-weight: bold;font-size: 35px;">管理后台</span>
    </div>
    <div class="header_right">
        欢迎 ${teacher.name },
        <a href="teacherPasswordupdate.jsp" class="settings">修改密码</a>
        <a href="teacherServlet/loginout" class="logout">退出系统</a>
    </div>
</div>
