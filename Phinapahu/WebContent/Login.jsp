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

<script type="text/javascript" src = "jquery/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

<jsp:include page ="Header.jsp"/>

	<div class="container content">
		<div class="row">

			<div class="jumbotron col-xs-9 col-sm-7 col-md-5 col-lg-4 col-xl-4">
				<h1 class="text-center">Login</h1>

				<br>
				<h5 style="color: #9a79d2">${registrationError}</h5>

				<form action="LoginServlet" method="post">

					<div class="form-group">
						<label for="UserName">Username</label><br />
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
					<h3>Easily manage cooking in your Household!</h3>

					<br /> Sign up and create your own Household! Send invites to the people
					that want to join! Everyone can make meal suggestions for your next
					cookout and vote for their favourite. Let's see who wins!
				</div>
			</div>
		</div>
		</div>
	<jsp:include page ="Footer.jsp"/>
	
</body>
</html>