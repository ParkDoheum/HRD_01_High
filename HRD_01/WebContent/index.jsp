<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = (String)request.getAttribute("title");
	String view = (String)request.getAttribute("view");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
	<div>
		
	</div>
	<div>
		<jsp:include page="<%=view %>"></jsp:include>
	</div>
</body>
</html>