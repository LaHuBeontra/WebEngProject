<%@page import="org.Essen.EssenBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vote Meals</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<div class="container content">
		<div class="row">
			<div
				class="jumbotron col-xs-8 col-sm-8 col-md-8 col-lg-8 col-xl-8 margin">
				
				<h3>Make dinner suggestions and vote for meals!</h3>

				<h3 style="color: red">${voteMessage}</h3>

				<%@ page import="java.util.*"%>

				<div class="text text-info">
						Add dates on which you want to have dinner with your household! <br />
					</div>
				<div class="bootstrap-iso">
					<div class="container-fluid">
						<div class="row">
							<form action="Date" method="post">
								<div class="form-group ">
<!-- 									<label for="date"> Date </label> -->
									<div class="input-group">
										<div class="input-group-prepend">
    <span class="input-group-text"><i class="fa fa-calendar"></i></span>
  </div>
										<input class="form-control" id="date" name="date"
											placeholder="DD.MM.YYYY" type="text" />
										<div class="input-group-addon">
											<button class="btn btn-primary " name="addDate" type="submit">
												<i class="fa fa-plus"> </i> Add Date
											</button>
										</div>

									</div>

								</div>

							</form>
						</div>
					</div>
				</div>

					<div class="text text-info">
						Select one of the dates you just added and suggest a meal. <br />
					</div>
				<form action="EssenErstellen" method="post">
					<table>
						<tbody>
							<tr>
								<td>Date</td>
								<td>Meal</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<div class="form-group">
										<input class="form-control" name="EssenDate" type="text"
											value="${param.EssenDate}" />
									</div>
									
								</td>
								<td>
									<div class="form-group">
										<input class="form-control" name="Essen" type="text"
											value="${param.Essen}" />
									</div>
								</td>
								<td>
									<div class="form-group">
										<button class="btn btn-primary" name="addEssen" type="submit">
											<i class="fa fa-plus"></i> Add Meal
										</button>
									</div>
								</td>
							</tr>
						</tbody>

					</table>
				</form>

				<br/>
				

				<form action="GetDates" method="get">
					<button class="btn btn-primary btn-block" name="getDates"
						type="submit">
						<i class="fa fa-angle-down icon-right"></i>Dates
					</button>
					<%
						Set<String> dateSet = new TreeSet<String>();
						if ((Set<String>) request.getAttribute("getDateSet") != null) {
							dateSet = ((Set<String>) request.getAttribute("getDateSet"));
					%>
					<P>
						<%
							for (String s : dateSet) {
									out.println(s);
						%>
					
					<P>
						<%
							}
								// out.print(" das war das array");
							} else {
								//out.print("Array leer");
							}
						%>
					
				</form>
				
				<form action="GetEssen" method="get">
					<button class="btn btn-primary btn-block" name="getEssen"
						type="submit">
						<i class="fa fa-angle-down icon-right"></i>Vote for Meals!
					</button>
					<%
						Set<EssenBean> essenSet = new TreeSet<EssenBean>();
						if ((Set<EssenBean>) request.getAttribute("getEssenSet") != null) {
							essenSet = ((Set<EssenBean>) request.getAttribute("getEssenSet"));
					%>
				</form>
<div class="text text-info">
						You have only one vote per day. <br />
					</div>
				

				<form action="Vote" method="post">
				<div class="table-striped">
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>Meal</th>
								<th>Date</th>
								<th>Votes</th>
							</tr>
						</thead>
						<tbody>

						<%
							for (EssenBean s : essenSet) {
						%>
						<tr>
							<td>
								<button class ="btn btn-secondary" name="vote" name="vote" id="vote"
									value=<%out.print(s.getDate() + ";" + s.getEssen() + ";" + s.getVotes());%>
									type="submit">
									<i class="fa fa-thumbs-up"></i> Vote
								</button>
							</td>
							<td>
								<%
									out.print(s.getEssen().replace("_", " "));
								%>
							</td>
							<td>
								<%
									out.print(s.getDate());
								%>
							</td>
							<td>
								<%
									out.print(s.getVotes());
								%>
							</td>
						</tr>
						<%
							}
							}
						%>
						</tbody>
					</table>
					
				</form>

				
				<form action="GetTagesessen" method="get">
					<button class="btn btn-primary btn-block" name="getTagesessen" type="submit"><i class="fa fa-angle-down icon-right"></i>And the winner is...</button>
					
					<%
						Set<EssenBean> tagesEssenSet = new TreeSet<EssenBean>();
						if ((Set<EssenBean>) request.getAttribute("getTagesEssen") != null) {
							tagesEssenSet = ((Set<EssenBean>) request.getAttribute("getTagesEssen"));
					%>
					<div class="table-striped">
					<table class="table">
						<thead>
							<tr>
								<th>Meal</th>
								<th>Date</th>
								<th>Votes</th>
							</tr>
						</thead>
						<tbody>
					
						<%
							for (EssenBean s : tagesEssenSet) {%>
							<tr>
							<td>
							<%		out.print(s.getEssen().replace("_", " "));%>
							</td> 
							<td>
							<% out.print(s.getDate());%>
							</td>
							<td>
							<% out.print(s.getVotes()); %>
							</td>
							
						
					</tr>
					
						<%
							}
							}
						%>
					</tbody>
					</table>
					</div>
				</form>
			</div >

			</div>
		</div>

	<!-- Extra JavaScript/CSS added manually in "Settings" tab -->
	<!-- Include jQuery -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

	<!-- Include Date Range Picker -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

	<script>
		$(document).ready(
				function() {
					var date_input = $('input[name="date"]'); //our date input has the name "date"
					var container = $('.bootstrap-iso form').length > 0 ? $(
							'.bootstrap-iso form').parent() : "body";
					date_input.datepicker({
						format : 'dd.mm.yyyy',
						container : container,
						todayHighlight : true,
						autoclose : true,
						
					})
				})
	</script>


</body>
</html>