<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.login.User"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LoginSuccess</title>
</head>
<body>
<h3> Login successful!</h3>
<%
	User user = (User)request.getSession().getAttribute("user");
%>
Hello <%=user.getUserName() %> 
</body>
</html>