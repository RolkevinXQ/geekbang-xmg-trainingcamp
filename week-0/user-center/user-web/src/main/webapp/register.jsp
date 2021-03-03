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

	<SCRIPT LANGUAGE=javascript>
		var baseUrl = "<%=request.getContextPath()%>";
	</SCRIPT>
</head>
<body>
	<div class="container">
		<form class="form-signin" method="post" action="<%=request.getContextPath()%>/user/doregister">
			<h1 class="h3 mb-3 font-weight-normal">注册</h1>
			<label for="inputName" class="sr-only">用户名</label>
			<input type="name" id="inputName" name="userName" class="form-control"
					placeholder="请输入用户名" required autofocus>
			<br/>
			<label for="inputEmail" class="sr-only">邮箱</label>
			<input
				type="email" id="inputEmail" name="userEmail" class="form-control"
				placeholder="请输入电子邮箱" required autofocus>
			<br/>
			<label
				for="inputPassword" class="sr-only">密码</label>
			<input
				type="password" id="inputPassword" name="userPassword" class="form-control"
				placeholder="请输入密码" required>
			<br/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		</form>
	</div>
</body>