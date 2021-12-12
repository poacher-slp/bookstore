<%--
  Created by IntelliJ IDEA.
  User: 13211
  Date: 2021/5/2
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
    <a href="pages/order">我的订单</a>
    <a href="logout">注销</a>&nbsp;&nbsp;
    <a href="index">返回</a>
</div>