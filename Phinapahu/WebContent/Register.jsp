<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>
<body>
<h3 style="color:red">${registrationError}</h3>
<form action="RegisterServlet" method ="post">
	<p>
		<label for ="UserName">Name</label> <input name ="UserName" type ="text" value="${param.UserName}"/>
	</p>
	<p>
		<label for="Email">Email</label> <input name="Email" type="text"
				value="${param.Email}" />
	</p>
	<p>
		<label for="Password">Password</label> <input name="Password" type="password"
				value="${param.Password}" />
	</p>

	<p>
		<input type="radio" name="Household" value ="Create" checked = "checked">Create Household
	</p>
	<p>
		<input type="radio" name="Household" value ="Join">Join Household
	</p>
	<p>
		<button name= "continue" type = "submit">Continue</button>
	</p>
</form>
</body>
</html>