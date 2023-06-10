<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h3>我的购物车</h3>
    <table border="1" style="width:60%">
        <tr>
            <td><input type="checkbox" id="checkall" name="checkall" onclick="setAll(this)"></td>
            <td>名称</td>
            <td>价格</td>
            <td>出版社</td>
            <td>作者</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${bookList}" var="map">
            <tr>
                <td><input type="checkbox" value="${map.id}" name="checkcart"
                           onclick="total()"></td>
                <td>${map.bookname}</td>
                <td>${map.price}</td>
                <td>${map.publisher}</td>
                <td>${map.author}</td>
                <td><a href="">提交</a></td>
            </tr>
        </c:forEach>
    </table>
    <span>总金额:</span>
    <span id="sumSpan"></span>
</center>
</body>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
    function setAll(obj) {
        //获取全选的状态，这里函数传入参数this
        var status = obj.checked;
        //根据name属性获取产品数组，该数组元素是多选框标签对象
        var itemsList = document.getElementsByName("checkcart");

        for (var i = 0; i < itemsList.length; i++) {
            //将全选的状态给每个多选框
            itemsList[i].checked = status;
        }
        total()
    }
    //每点击一次复选框就调用一下该方法
    function total() {
        //获取多选框对象数组
        var itemsList = document.getElementsByName("checkcart");
        //获取span对象，为了放置计算的总额
        var sumSpan = document.getElementById("sumSpan");

        var itemsum = document.getElementsByName("itemsum");
        var sum = 0;
        for (var i = 0; i < itemsList.length; i++) {
            //计算勾选的产品
            if (itemsList[i].checked == true) {
                //value属性值为String 所以要进行转换
                sum += parseInt(itemsum[i].value);
            }
        }
        //将总额放进span区域
        sumSpan.innerHTML = sum;
    }
</script>
</html>
