<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<% String baseUrl = request.getContextPath();%>
<title>User Center</title>

	<script>
		var url="<%=baseUrl%>";
	</script>
</head>
<body>
	<div class="container-lg">
		Hello,World 2021

		<div>
			<input type="button" onclick="window.location=baseUrl/user/register"  value="注册" >
		</div>
	</div>
</body>