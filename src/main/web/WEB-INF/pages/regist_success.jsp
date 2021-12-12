<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
	<%--	base标签，css引入、jq引入--%>
	<%@include file="/WEB-INF/pages/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<span class="wel_word"></span>
			<%@include file="/WEB-INF/pages/login_success_head.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index">转到主页</a></h1>
	
		</div>

		<%@include file="/WEB-INF/pages/footer.jsp"%>
</body>
</html>