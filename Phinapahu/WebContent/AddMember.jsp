<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add User</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous"><script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src = "jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
	//Function adds html-Tag <br/> whenever the user entered a linebreak in the textarea
	function textareaReplaceLineBreaks() {
		document.getElementById("invitationText").value = document
				.getElementById("invitationText").value.replace(/(\r\n|\n)/g,
				"<br/>");
	}
</script>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">
			<div
				class="jumbotron col-xs-6 col-sm-6 col-md-6 col-lg-7 col-xl-6 margin">

				<p>What's the E-Mail of your new Member?</p>


				<h3 style="color: red">${emailError}</h3>

				<form action="AddMemberServlet.java" method="post">

					<p>
						<input class="form-control" name="MemberEmail" type="email"
							value="${param.MemberEmail}" />
					</p>

					<br />
					<div class="form-group">
						<label for="invitationText">Please enter a text that will
							be sent to the person you want to invite:</label>
						<textarea onfocus="this.value=''" class="form-control"
							id="invitationText" name="invitationText">Enter invitation mail...</textarea>
					</div>
					<br />



					<button class="btn btn-primary" type="submit"
						onclick="textareaReplaceLineBreaks();">
						<i class="fa fa-user-plus"></i> Add Member
					</button>


				</form>
			</div>
		</div>
	</div>
	<jsp:include page ="Footer.jsp"/>
</body>
</html>