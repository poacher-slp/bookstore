<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>
	<%@ include file="/WEB-INF/pages/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$(".addItems").click(function () {
				var bookId = $(this).attr("bookId")
				location.href = "${pageScope.basePath}" + "cart/addItems?id=" + bookId;
			})
		})

	</script>
</head>
<body>

<div id="header">
	<span class="wel_word">网上书城</span>
	<div>

		<c:if test="${empty sessionScope.user}">
			<a href="pages/login">登录</a> |
			<a href="pages/regist">注册</a> &nbsp;&nbsp;
		</c:if>
		<c:if test="${not empty sessionScope.user}">
			<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
			<a href="pages/order">我的订单</a>
			<a href="logout">注销</a>&nbsp;&nbsp;
			<a href="index">返回</a>
		</c:if>
		<a href="#">购物车</a>
		<a href="pages/manager">后台管理</a>
	</div>
</div>

<div id="main">

	<%--显示图书信息--%>
	<div id="book">

		<div class="book_cond">
			<form action="client/pageByPrice" method="get">
				价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
				<input id="max" type="text" name="max" value="${param.max}"> 元
				<input type="submit" value="查询" />
			</form>
		</div>

		<div style="text-align: center">
			<span>您的购物车中有
				${empty sessionScope.cart.totalCount ? 0 : sessionScope.cart.totalCount }
				件商品</span>
			<div>
				您刚刚将<span style="color: red">${sessionScope.lastItem}</span>加入到了购物车中
			</div>
		</div>

		<c:forEach items="${requestScope.page.list}" var="book">
			<div class="b_list">
			<div class="img_div">
				<img class="book_img" alt="" src="${book.imgPath}" />
			</div>
			<div class="book_info">
				<div class="book_name">
					<span class="sp1">书名:</span>
					<span class="sp2">${book.name}</span>
				</div>
				<div class="book_author">
					<span class="sp1">作者:</span>
					<span class="sp2">${book.author}</span>
				</div>
				<div class="book_price">
					<span class="sp1">价格:</span>
					<span class="sp2">${book.price}</span>
				</div>
				<div class="book_sales">
					<span class="sp1">销量:</span>
					<span class="sp2">${book.sales}</span>
				</div>
				<div class="book_amount">
					<span class="sp1">库存:</span>
					<span class="sp2">${book.stock}</span>
				</div>
				<div class="book_add">
					<button class="addItems" bookId="${book.id}">加入购物车</button>
				</div>
			</div>
		</div>
		</c:forEach>

	</div>

	<%@ include file="/WEB-INF/pages/page_nav.jsp"%>

</div>

<div id="bottom">
		<span>
			书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>