<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
	<%--	base标签，css引入、jq引入--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>
</head>
<body>
		<div id="login_header">
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">
									${empty requestScope.msg ? "" : requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
									value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" id="code" name="code"/>
									<img alt="" id="code_img" src="kaptcha.jpg" style="float: right; margin-right: 40px" width="110px" height="30px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
<script>
	$(function(){
		$("#sub_btn").click(function () {
			//验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
			var username = $("#username").val();
			nameRegex = /^\w{5,12}$/;
			if(!nameRegex.test(username)) {
				$("span.errorMsg").text("用户名不合法");
				return false;
			}
			//验证密码：必须由字母，数字下划线组成，并且长度为5到12位
			var password = $("#password").val();
			pswRegex = /^\w{5,12}$/;
			if(!pswRegex.test(password)) {
				$("span.errorMsg").text("密码不合法");
				return false;
			}
			//验证确认密码：和密码相同
			var repsw = $("#repwd").val();
			if(repsw != password) {
				$("span.errorMsg").text("确认密码和密码不一致");
				return false;
			}
			//验证邮箱
			var email = $("#email").val();
			emailRegex = /^[a-z\d]+(\.\[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			if(!emailRegex.test(email)) {
				$("span.errorMsg").text("邮箱不合法");
				return false;
			}
			//验证：目前只要不是空值即可
			var code = $("#code").val();
			var trim = code.trim();
			if(trim == null || trim == "") {
				$("span.errorMsg").text("验证码不合法");
				return false;
			}
			//验证成功取消errorMsg显示
			$("span.errorMsg").text("");
		})

		$("#code_img").click(function () {
			//在事件响应的function函数中有一个this对象，这个this对象，是当前正在响应事件的dom对象
			this.src = "${basePaht}/kaptcha.jpg";
		})

		$("#username").blur(function () {
			var value = this.value;
			$.getJSON("${basePath}userServlet", "action=ajaxExistUsername&username=" + value, function (data) {
				// console.log(data);
				if(data.existUsername) {
					$("span.errorMsg").text("用户名已存在！");
				}
				else
					$("span.errorMsg").text("");
			});
		})
	})
</script>