<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management Page</title>
</head>
<body>
<h1>Hi ${name}, this is your Household!</h1>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user.getUserName()}</td>
        <td>${user.isAdmin()}</td>
    </tr>
</c:forEach>
</body>
</html>