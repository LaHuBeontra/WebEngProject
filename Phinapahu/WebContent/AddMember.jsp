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

                <h3>So, someone is joining your Household?</h3>
                <h5 class="text text-info">Just enter their E-Mail address and we will send them your Household Password!</h5>

				<h4 style="color: #9a79d2">${emailError}</h4>

				<form action="AddMemberServlet.java" method="post">

					<p>
						<input class="form-control" name="MemberEmail" type="email"
							value="${param.MemberEmail}" />
					</p>

					<br />
					<div class="form-group">
						<label for="invitationText">Give your new Household member a warm welcome:</label>
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