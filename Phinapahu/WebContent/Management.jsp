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
				<h1>Hi ${userName}, this is your household ${householdName}!</h1>
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
									<td>${user}</td>

									<!-- Second column: Status of member (Admin or normal Member) -->
									<td><c:choose>
											<c:when test="${loginService.isAdmin(user)}">

                     Admin
                     </c:when>
											<c:otherwise>
                     Member
                     </c:otherwise>
										</c:choose></td>

									<!-- Third column: Actions -->
									<td>
										<form
											action="ToggleStatusServlet.java"
											method="post">
											<input type="hidden" name="toggleUser"
												value="${user}"> 

											<button class ="btn btn-secondary" type = "submit" onclick = "return confirm('Are you sure you want to change the Status of ${user}?')">
											<i class ="fa fa-sync"></i> Toggle Status
											</button>
										</form>
										</td>
										<td>
										<form
											action="DeleteMemberServlet.java"
											method="post">
											<input type="hidden" name="deleteUser"
												value="${user}"> 

											<button class = "btn btn-secondary" type="submit" onclick = "return confirm('Are you sure you want to remove ${user} from your Household? :(')">
											<i class ="fa fa-trash"></i> Delete Member
											</button>

										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


				<form action="AddMember.jsp">

					<button class ="btn btn-primary" type ="submit" onclick = "return confirm('Do you want to add a new Member to your Household? :D')">
					<i class ="fa fa-user-plus"></i> Add Member
					</button>
				</form>
				<a href="#" class ="btn btn-primary" style="margin-top: 1%; font-size: 170%;">
					<i class="fas fa-utensils" style="margin-right: 2%"></i> Go To Meals!
				</a>
			</div>
		</div>
	</div>
</body>
</html>