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

<script language="javascript">
//Function adds html-Tag <br/> whenever the user entered a linebreak in the textarea
function textareaReplaceLineBreaks(){
	document.getElementById("invitationText").value = document.getElementById("invitationText").value.replace(/(\r\n|\n)/g, "<br/>");
}
</script>
</head>
<body>
<jsp:include page="Header.jsp"/>
<div class="container content">
		<div class="row">

			<div class="jumbotron col-xs-9 col-sm-7 col-md-5 col-lg-4 col-xl-4">
<p>What's the E-Mail of your new Member?</p>


<h3 style="color:red">${emailError}</h3>

<form action="${pageContext.request.contextPath}/AddMemberServlet.java" method="post">
    <p>
        <input name ="MemberEmail" type ="text" value="${param.MemberEmail}"/>
    </p>
    
    Please enter a text that will be sent to the person you want to invite:<br/>
	<textarea id = "invitationText" name = "invitationText" rows="5" cols="50" >Enter invitation mail...</textarea><br/>
    <p>
        <input class = "btn btn-primary" type="submit" onclick="textareaReplaceLineBreaks();" value="Add Member"/>
    </p>

</form>
</div>
</div>
</div>
</body>
</html>