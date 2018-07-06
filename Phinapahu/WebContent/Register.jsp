<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">

			<div
				class="jumbotron col-xs-9 col-sm-7 col-md-5 col-lg-4 col-xl-4 margin">
				<h1 class="text-center">Register</h1>
				<br>
				<h5 style="color: red">${registrationError}</h5>
				<form action="RegisterServlet" method="post">
					<div class="form-group">

						<label for="UserName">Name</label><br />
						<input class="form-control" name="UserName" type="text"
							value="${param.UserName}" />
					</div>
					<div class="form-group">

						<label for="Email">Email</label><br />
						<input class="form-control" name="Email" type="email"
							value="${param.Email}" />
					</div>
					<div class="form-group">

						<label for="Password">Password</label><br />
						<input class="form-control" name="Password" type="password"
							value="${param.Password}" />
					</div>

					<div class="form-check radio-cyan-gap">
						<input id="create" class="form-check-input with-gap" type="radio"
							name="Household" value="Create" checked="checked"> <label
							class="form-check-label" for="create"> Create Household</label>
					</div>
					<div class="form-check radio-cyan-gap">
						<input id="join" class="form-check-input with-gap" type="radio"
							name="Household" value="Join"> <label
							class="form-check-label" for="join"> Join Household</label>
					</div>

					<br />
					<div class="form-group">
						<button class="btn btn-primary" name="continue" type="submit">Continue</button>
					</div>




				</form>
			</div>
		</div>
	</div>
</body>
</html>