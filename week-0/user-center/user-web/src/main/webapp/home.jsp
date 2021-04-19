<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<% String baseUrl = request.getContextPath();%>
<title>User Center</title>

	<script type="text/javascript">
		function register(){
			window.location = "register1.jsp";
		}
	</script>
</head>
<body>
	<div class="container-lg">
		用户中心

		<div>
			<input type="button" onclick="register()"  value="注册" >
		</div>
	</div>
</body>