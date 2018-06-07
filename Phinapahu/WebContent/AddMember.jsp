<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>What's the E-Mail of your new Member?</p>
<form action = "${pageContext.request.contextPath}/AddMemberServlet.java" method = "post">
    <input name ="MemberEmail" type ="text" value="${param.memberEmail}"/>
    <input type="submit" value="Add Member"/>
</form>
</body>
</html>