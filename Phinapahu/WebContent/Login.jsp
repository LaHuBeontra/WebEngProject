<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>

<form action="LoginServlet" method ="post">
	<p>
		<label for="UserName">UserName</label> <input name="UserName" type="text"
				value="${param.UserName}" />
				
	</p>
	<p>
		<label for="Password">Password</label> <input name="Password" type="password"
				value="${param.Password}" />
	</p>
	<p>
		<button name= "login" type = "submit">Sign In</button>
	</p>
</form>
<a href = "Register.jsp">Sign Up</a>

</body>
</html>