<%@page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<head>
<jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<title>用户注册</title>
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

	<script type="text/javascript">
		function isValid(form){
			if(form.userName.value==""){
				alert("用户名不能为空！");
				return false;
			}
			if(form.userEmail.value==""){
				alert("两次输入的密码不同！");
				return false;
			}
			if(form.userPassword.value==""){
				alert("密码不能为空！");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<div class="container">
			<form method="post" action="<%=path%>/user/doregister"
				  onSubmit="return isValid(this);">
				<table>
					<caption>用户注册</caption>
					<tr>
						<td>用户名：</td>
						<td><input type="text" name="userName" size="21" class="form-control" placeholder="请输入用户名" required autofocus></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input type="email" name="userEmail" size="21" class="form-control" placeholder="请输入电子邮箱" required autofocus></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input type="password" name="userPassword" size="21" class="form-control" placeholder="请输入密码" required autofocus></td>
					</tr>
					<tr>
						<td>手机号</td>
						<td><input type="number" name="userMobile" size="21" class="form-control" placeholder="请输入手机号" required></td>
					</tr>
					<tr>
						<td><input type="submit" value="注册" /></td>
						<td><input type="reset" value="重置"></td>
					</tr>
				</table>
			</form>
	</div>
</body>