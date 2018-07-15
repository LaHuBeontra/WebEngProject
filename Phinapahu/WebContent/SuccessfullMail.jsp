<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email success</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>

</head>

<body>
	<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">
			<div
				class="jumbotron col-xs-6 col-sm-6 col-md-6 col-lg-7 col-xl-6 margin">
				<h5 style="color: #9a79d2">Email successfully sent to ${email}!</h5>
				<p>Your new member will surely join soon!</p>
				<p>

					<a class="btn btn-primary" href="ManagementServlet.java"> <i
						class="fa fa-arrow-right"> </i> Return to Management Page
					</a>

				</p>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>