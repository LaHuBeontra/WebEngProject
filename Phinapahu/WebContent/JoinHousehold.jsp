<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Join Household</title>
</head>
<body>
<p>
	<h3 style="color:red">${JoinHouseholdError}</h3>
	Please enter the Password you received
</p>
<form action = "JoinHouseholdServlet" method="post">
	<p>
		<label for="HouseholdPassword">Password of Household</label> 
		<input name="HouseholdPassword" type="text"
				value="${param.HouseholdPassword}" />
	</p>
	<p>
		<button name= "login" type = "submit">Complete Registration</button>
	</p>			
	
</form>

</body>
</html>