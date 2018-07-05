<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous"><script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page ="Header.jsp"/>

	<div class="container content">
		<div class="row">

			<div class="jumbotron col-xs-9 col-sm-7 col-md-5 col-lg-4 col-xl-4">
				<h1 class="text-center">Login</h1>

				<br>
				<h5 style="color: red">${registrationError}</h5>

				<form action="LoginServlet" method="post">

					<div class="form-group">
						<label for="UserName">UserName</label><br />
						<input class="form-control" name="UserName" type="text" value="${param.UserName}" />
					</div>


					<div class="form-group">
						<label class="control-label" for="Password">Password</label><br />
						<input class="form-control" name="Password" type="password" value="${param.Password}" />
					</div>
					<div class="form-group">
						<button class="btn btn-primary" name="login" type="submit">
						 <i class = "fa fa-sign-in-alt"></i> Sign In</button>
					</div>
					<div>
						<a class="text-info" href="Register.jsp">Sign Up</a>
					</div>
				</form>
			</div>
			<div class="well margin" style="width: 10px"></div>


			<div class="jumbotron text-center col-md-6 margin bg-info text-white">
				<div class="text-center">
					<h3>Easily manage your household!</h3>

					<br /> Sign up now and create a new household. Send emails to the
					people you want to invite to your household. Propose meals for your
					next collective dinner and vote for the meal you're going to cook!
				</div>
			</div>
		</div>
		</div>
	
</body>
</html>