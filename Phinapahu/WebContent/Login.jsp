<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<form action="login" method ="post">
	<p>
		<label for="Email">Email</label> <input name="Email" type="text"
				value="${param.Email}" />
				
	</p>
	<p>
		<label for="Password">Password</label> <input name="Password" type="password"
				value="${param.Password}" />
	</p>
	<p>
		<button name= "login" type = "submit">Login</button>
	</p>
</form>
<a href = "Register.jsp">Register Now</a>

</body>
</html>