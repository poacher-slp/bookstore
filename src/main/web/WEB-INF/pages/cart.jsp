<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	base标签，css引入、jq引入--%>
	<%@include file="/WEB-INF/pages/head.jsp"%>
	<script type="text/javascript">

		$(function () {
			$(".deleteItems").click(function () {
				return confirm("你确定要删除"+ $(this).parent().parent().find("td：first").text()+"吗？");
			})
			$(".clearItems").click(function () {
				return confirm("你确定要清空购物车吗？");
			})
		})
	</script>
</head>
<body>
	<div id="header">
			<span class="wel_word">购物车</span>
			<%@include file="/WEB-INF/pages/login_success_head.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>		
			<%--如果购物车为空，友情提示--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="${pageScope.basePath}">亲，您的购物车还没有任何东西呢。</a> </td>
				</tr>
			</c:if>
			<%--如果购物车不为空，则显示购物车信息--%>
			<c:if test="${not empty sessionScope.cart}">
				<c:forEach items="${sessionScope.cart.items}" var="items">
					<tr>
						<td>${items.value.name}</td>
						<td>${items.value.count}</td>
						<td>${items.value.price}</td>
						<td>${items.value.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItems&id=${items.value.id}" class="deleteItems">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<div class="cart_info">
			<c:if test="${not empty sessionScope.cart.items}">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clearItems" class="clearItems">清空购物车</a></span>
				<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
			</c:if>
		</div>
	
	</div>

	<%@include file="/WEB-INF/pages/footer.jsp"%>
</body>
</html>