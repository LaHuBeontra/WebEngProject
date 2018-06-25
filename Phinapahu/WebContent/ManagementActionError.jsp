<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Error Page</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous"><script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">
			<div
				class="jumbotron col-xs-9 col-sm-9 col-md-9 col-lg-9 col-xl-9 margin">
    <h3>You can't use actions on yourself! :(</h3>
	<form action="${pageContext.request.contextPath}/ManagementServlet.java" method="get">
        <input class = "btn btn-primary"  type="submit" value="Go Back!" />
    </form>
    </div>
    </div>
    </div>
</body>
</html>