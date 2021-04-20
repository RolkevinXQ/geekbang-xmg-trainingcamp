<%@ page import="org.rolkevin.user.domain.User" %>
<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<%
		String baseUrl = request.getContextPath();
		HttpSession session1 = request.getSession();
		User user = (User) session1.getAttribute("user");
		//String username = "";
		if (null != user){
			String username = user.getName();
		}
	%>
	<title>用户中心</title>
	<link rel="stylesheet" href="/static/css/bootstrap.css"/>
	<link rel="stylesheet" href="/static/css/bootstrap-theme.css"/>
	<script src="/static/js/bootstrap.js" type="application/javascript">
		console.log(<%=session1%>);
		console.log(<%=baseUrl%>);
	</script>
</head>
<%=session1%>
<%=baseUrl%>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">用户中心</span>
			</button>
			<a class="navbar-brand" href="#">用户中心</a>
		</div>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<form class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="搜索问题">
				</div>
				<button type="submit" class="btn btn-default">搜索(无效)</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li th:if="<%=session1.getAttribute("user")%> eq null"><a href="">登录</a></li>
				<li class="dropdown" th:if="<%=session1.getAttribute("user")%>  not null">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" th:text=""><div></div> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">消息中心</a></li>
						<li><a href="#">个人资料</a></li>
						<li><a href="#">我的帖子</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">退出登录</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
</body>