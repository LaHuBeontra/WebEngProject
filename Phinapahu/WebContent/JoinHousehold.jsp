<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Join Household</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>
</head>
<body>


	<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">

			<div
				class="jumbotron col-xs-9 col-sm-7 col-md-5 col-lg-4 col-xl-4 margin">
				<h1 class="text-center">Register</h1>
				<br>

				<h3 style="color: red">${JoinHouseholdError}</h3>
				<div class="text text-info">Please enter the Password you
					received!</div>
				<br>
				<form action="JoinHouseholdServlet" method="post">
					<div class=form-group>
						<label for="HouseholdPassword">Password of Household</label> <br>

						<input class="form-control" name="HouseholdPassword"
							type="password" value="${param.HouseholdPassword}" />

					</div>
					<p>
						<button class="btn btn-primary" name="login" type="submit">Register</button>
					</p>

				</form>
			</div>
		</div>
	</div>
<jsp:include page ="Footer.jsp"/>
</body>
</html>