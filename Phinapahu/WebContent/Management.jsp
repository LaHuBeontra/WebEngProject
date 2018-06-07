<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=”stylesheet” href="${pageContext.request.contextPath}/Management.css">
<title>Management Page</title>
</head>
<body>
<h1>Hi ${name}, this is your Household!</h1>
<p id="test">How are you doing?</p>
<table>
    <thead>
        <tr>
            <th>Household Member</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
        <tr>
            <!-- First column: Name of member -->
            <td>${user.getUserName()}</td>
            
            <!-- Second column: Status of member (Admin or normal Member) -->
            <td>
                <c:choose>
                     <c:when test="${user.isAdmin()}">
                     Admin
                     </c:when>
                     <c:otherwise>
                     Member
                     </c:otherwise>
                </c:choose>
            </td>
            
            <!-- Third column: Actions -->
            <td>
                <form action="${pageContext.request.contextPath}/ToggleStatusServlet.java" method="post">
                    <input type="hidden" name="toggleUser" value="${user.getUserName()}">
                    <input type="submit" value="Toggle Status" onclick="return confirm('Are you sure you want to change the Status of ${user.getUserName()}?')">
                </form>
                <form action="${pageContext.request.contextPath}/DeleteMemberServlet.java" method="post">
                    <input type="hidden" name="deleteUser" value="${user.getUserName()}">
                    <input type="submit" value="Delete Member" onclick="return confirm('Are you sure you want to remove ${user.getUserName()} from your Household? :(')">
                </form>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<form action="${pageContext.request.contextPath}/AddMember.jsp">
    <input type="submit" value="Add Member" onclick="return confirm('Do you want to add a new Member to your Household? :D')">
</form>
</body>
</html>