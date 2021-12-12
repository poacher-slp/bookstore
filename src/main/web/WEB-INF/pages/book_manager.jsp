<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--	base标签，css引入、jq引入--%>
	<%@include file="/WEB-INF/pages/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">图书管理系统</span>
		<%@include file="/WEB-INF/pages/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.list}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/getBook?id=${book.id}&pageNum=${requestScope.page.pageNum}">修改</a></td>
					<td><a class="deletebtn" href="manager/delete?id=${book.id}&pageNum=${requestScope.page.pageNum}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/book_edit?pageNum=${requestScope.page.pages}">添加图书</a></td>
			</tr>	
		</table>

		<%@ include file="/WEB-INF/pages/page_nav.jsp"%>

	</div>

	<%@include file="/WEB-INF/pages/footer.jsp"%>
</body>
</html>
<script type="text/javascript">
	$(function(){
		$("a.deletebtn").click(function () {
			return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text()+"？");
		})
	})
</script>