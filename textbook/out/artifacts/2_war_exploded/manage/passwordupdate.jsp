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
<title>网上书城管理后台</title>
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


<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	if (document.getElementById('password1id').value=="")
	{
		alert("原密码不能为空");
		return false;
	}
	if (document.getElementById('password2id').value=="")
	{
		alert("新密码不能为空");
		return false;
	}
	
	if (document.getElementById('password2id').value.length<6)
	{
		alert("新密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('password3id').value)
	{
		alert("新密码与新密码确认不一致");
		return false;
	}	 
	return true;
	
}


</script>

</head>
<body>
<div id="panelwrap">
  	
	
	<%@ include file="head.jsp" %>

    
    <div class="submenu">
    
    </div>          
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
    
    <h2>${biaoti }</h2> 
                    
                    


	
    
    
    <div id="tab1" class="tabcontent">
       
        <div class="form">
            
            <form action="${url }" method="post" onsubmit="return checkform()" >
            
            <TABLE cellSpacing=0 cellpadding="5" width="100%" align=center border=1>
            
            
            
            <tr>
            <td>
            原密码:
            </td>
            <td> 
            <input type="password" name="password1" id="password1id" />
            </td>
            </tr>

            
            <tr>
            <td>
            新密码:
            </td>
            <td> 
            <input type="password" name="password2" id="password2id" />
            </td>
            </tr>
            
            <tr>
            <td>
            确认新密码:
            </td>
            <td> 
            <input type="password" name="password3" id="password3id" />
            </td>
            </tr>
            
            
             <tr>
            <td>操作:</td><td> 
          
             <input type="submit" value="提交" style="width: 60px" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
            </td>
            </tr>
            
            </table>
            </form>
            
            
            <div class="clear"></div>
        </div>
    </div>
    

      
     </div>
     </div><!-- end of right content-->
                     
                    
   <%@ include file="left.jsp" %>        
    
    
    <div class="clear"></div>
    </div> 

</div>

    	
</body>
</html>
