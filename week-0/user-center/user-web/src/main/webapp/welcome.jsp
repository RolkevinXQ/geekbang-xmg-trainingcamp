<%@ page import="org.rolkevin.user.domain.User" %>
<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<% String baseUrl = request.getContextPath();
		User user = (User) request.getAttribute("user");
	%>
<title>User Center</title>
	<script>
		var url="<%=baseUrl%>";
	</script>
</head>
<body>
	<div class="container-lg">
		Hello,World 2021

		<div>

		</div>
	</div>
</body>