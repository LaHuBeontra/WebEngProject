<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="container content">
		<div class="row">

			<div class="jumbotron col-xs-9 col-sm-7 col-md-5 col-lg-4 col-xl-4">
<p>What's the E-Mail of your new Member?</p>
<form action = "${pageContext.request.contextPath}/AddMemberServlet.java" method = "post">
    <input name ="MemberEmail" type ="text" value="${param.memberEmail}"/>
    <input class = "btn btn-primary" type="submit" value="Add Member"/>
</form>
</div>
</div>
</div>
</body>
</html>