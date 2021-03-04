<%@ page language="java" import="org.rolkevin.user.domain.User " pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<% String baseUrl = request.getContextPath();
		User user = (User) request.getAttribute("user");
		String name = user.getName();
	%>
<title>User Center</title>
<script>
	var url="<%=baseUrl%>";
	var userName = <%=name%>;
</script>
</head>
<body>
	<div class="container-lg">
		<p><%=name%> --Welcome to UserCenter</p>
		<div>
 
			<table>
				<tr></tr>
			</table>
		</div>
	</div>
</body>