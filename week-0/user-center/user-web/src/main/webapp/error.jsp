<%@ page language="java" import="org.rolkevin.user.domain.User " pageEncoding="UTF-8" %>
<%@ page import="org.rolkevin.user.response.ResponseResult" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<head>
<jsp:directive.include
	file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
	<% String baseUrl = request.getContextPath();
		ResponseResult result = (ResponseResult) request.getAttribute("result");
		String title = result.getTitle();
		String content = (String) result.getContent();
	%>
<title>用户中心-错误详情页</title>
<script>
	var url="<%=baseUrl%>";
	var title = <%=title%>;
	var content = <%=content%>;
</script>
</head>
<body>
	<div class="container-lg">
		<p><%=title%> </p>
		<p><%=content%> </p>
		<div>

		</div>
	</div>
</body>