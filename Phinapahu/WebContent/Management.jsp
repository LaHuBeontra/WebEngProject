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
<script type="text/javascript" src = "jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">
			<div
				class="jumbotron col-xs-9 col-sm-9 col-md-9 col-lg-9 col-xl-9 margin">
				<h2>Hi ${userName}, this is your Household, ${householdName}!</h2>
				<p>We hope you're having a great day! In case you're wondering, this is where you can manage your household.</p>
				<ul style="list-style-type: circle">
				    <li>"Toggle Status" will give a normal member administrator rights, or remove them.</li>
				    <li>"Delete Member" will delete a member from your household. We hope you won't have to use this too often!</li>
				    <li>"Add Member" will let you add a new member to your household. Yay!</li>
			    </ul>
				
				<h4 style="color: #9a79d2">${managementError}</h4>
				<h4 style="color: #9a79d2">${managementMessage}</h4>
				
				<div class="table-striped">
					<table class="table">
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
											<c:when test="${fileService.isAdmin(user)}">

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

											<button class = "btn btn-secondary" type="submit" onclick = "return confirm('Are you sure you want to remove ${user} from your Household?')">
											<i class ="fa fa-trash"></i> Remove Member
											</button>

										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<form action="AddMember.jsp">
					<button class ="btn btn-primary" type ="submit" onclick = "return confirm('Do you want to add a new Member to your Household?')">
					<i class="fa fa-user-plus"></i> Add Member
					</button>
				</form>
				<form action="${pageContext.request.contextPath}/GoToMealsServlet.java" method="post">
					<button class ="btn btn-primary" type ="submit" style="margin-top: 1%; font-size: 160%;">
					    <i class="fas fa-utensils" style="margin-right: 2%"></i> Go To Meals!
					</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page ="Footer.jsp"/>
</body>
</html>