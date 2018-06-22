<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Management Page</title>
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
				<h1>Hi ${name}, this is your Household!</h1>
				<p id="test">How are you doing?</p>
				
				<div class="table-striped">
					<table class = "table">
						<thead>
							<tr>
								<th>Household Member</th>
								<th>Status</th>
								<th>Actions</th>
								<th> </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<!-- First column: Name of member -->
									<td>${user.getUserName()}</td>

									<!-- Second column: Status of member (Admin or normal Member) -->
									<td><c:choose>
											<c:when test="${user.isAdmin()}">
                     Admin
                     </c:when>
											<c:otherwise>
                     Member
                     </c:otherwise>
										</c:choose></td>

									<!-- Third column: Actions -->
									<td>
										<form
											action="${pageContext.request.contextPath}/ToggleStatusServlet.java"
											method="post">
											<input type="hidden" name="toggleUser"
												value="${user.getUserName()}"> 
<!-- 											<input -->
<!-- 												class = "btn btn-primary" -->
<!-- 												type="submit" -->
<!-- 												value="Toggle Status" -->
<%-- 												onclick="return confirm('Are you sure you want to change the Status of ${user.getUserName()}?')"> --%>
											<button class ="btn btn-secondary" type = "submit" onclick = "return confirm('Are you sure you want to change the Status of ${user.getUserName()}?')">
											<i class ="fa fa-sync"></i> Toggle Status
											</button>
										</form>
										</td>
										<td>
										<form
											action="${pageContext.request.contextPath}/DeleteMemberServlet.java"
											method="post">
											<input type="hidden" name="deleteUser"
												value="${user.getUserName()}"> 
<!-- 												<input -->
<!-- 												class="btn btn-primary" type="submit" -->
<!-- 												value="Delete Member" -->
<%-- 												onclick="return confirm('Are you sure you want to remove ${user.getUserName()} from your Household? :(')"> --%>
											<button class = "btn btn-secondary" type="submit" onclick = "return confirm('Are you sure you want to remove ${user.getUserName()} from your Household? :(')">
											<i class ="fa fa-trash"></i> Delete Member
											</button>

										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<form action="${pageContext.request.contextPath}/AddMember.jsp">
<!-- 					<input class="btn btn-primary" type="submit" value="Add Member" -->
<!-- 						onclick="return confirm('Do you want to add a new Member to your Household? :D')"> -->
					<button class ="btn btn-primary" type ="submit" onclick = "return confirm('Do you want to add a new Member to your Household? :D')">
					<i class ="fa fa-plus"></i> Add Member
					</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>