<%@page import="org.Essen.EssenBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Essensabstimmung</title>
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
				class="jumbotron col-xs-6 col-sm-6 col-md-6 col-lg-7 col-xl-6 margin">

				<h3 style="color: red">${voteMessage}</h3>

				<%@ page import="java.util.*"%>


				<div class="bootstrap-iso">
					<div class="container-fluid">
						<div class="row">
							<form action="Date" method="post">
								<div class="form-group ">
									<label for="date"> Date </label>
									<div class="input-group">
										<input class="form-control" id="date" name="date"
											placeholder="DD.MM.YYYY" type="text" />
										<div class="input-group-addon">
											<button class="btn btn-primary " name="addDate" type="submit">
												<i class="fa fa-calendar"> </i> Add Date
											</button>
										</div>

									</div>

								</div>

							</form>
						</div>
					</div>
				</div>

				<p>
				<form action="EssenErstellen" method="post">
				<table >
				<tbody>
				<tr>
				<td>
				Date
				</td>
				<td>
				Meal
				</td>
				<td>
				</td>
				</tr>
				<tr>
				<td>
					<div class="form-group">
						<input class="form-control" name="EssenDate"
							type="text" value="${param.EssenDate}" />
					</div>
					</td>
					<td>
					<div class="form-group">
						<input class ="form-control" name="Essen" type="text"
							value="${param.Essen}" />
					</div>
					</td>
					<td>
					<div class = "form-group">
					<button class="btn btn-primary" name="addEssen" type="submit">
					<i class ="fa fa-plus"></i>
					Add Meal</button>
						</div>
						</td>
						</tr>
						</tbody>
						
				</table>
				</form>

				<p>

					<h3>Daten</h3>

				
	<div class="container">

		<div id="accordion">
			<form action="GetDates" method="get">
				<div class="card">
					<div class="card-header">

						<button class="btn btn-primary" data-toggle="collapse"
							data-target="#collapseOne" name="getDates" type="submit">Dates</button>

					</div>
					<div id="collapseOne" class="collapse show"
						data-parent="#accordion">
						<div class="card-body">
							<%
								//List<String> dateList =new ArrayList<String>();
								Set<String> dateSet = new TreeSet<String>();
								if ((Set<String>) request.getAttribute("getDateSet") != null) {
									dateSet = ((Set<String>) request.getAttribute("getDateSet"));
									// out.print("Array nicht leer");
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
							
						</div>
					</div>

				</div>
			</form>

			<div class="card">
				<form action="GetEssen" method="get">
					<div class="card-header">

						<button class="btn btn-primary" data-toggle="collapse"
							data-target="#collapseTwo" name="getEssen" type="submit">Vote
							for Meals</button>
					</div>
				</form>
				<div id="collapseTwo" class="collapse show" data-parent="#accordion">
					<div class="card-body">
						<%
							Set<EssenBean> essenSet = new TreeSet<EssenBean>();
							if ((Set<EssenBean>) request.getAttribute("getEssenSet") != null) {
								essenSet = ((Set<EssenBean>) request.getAttribute("getEssenSet"));
						%>



						<P></P>

						<form action="Vote" method="post">
							<table class ="table">
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
											<button class="btn btn-secondary" name="vote2" id="vote2"
												value=<%out.print(s.getDate() + ";" + s.getEssen() + ";" + s.getVotes());%>
												type="submit">
												<i class="fa fa-thumbs-up"></i> Vote For
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


									
									<%
										}
									%>
									</tr>
									<%

										}
									%>
								
							</table>
						</form>

					</div>
				</div>


				<form action="GetTagesessen" method="get">
					<div class="card">
						<div class="card-header">

							<button class="btn btn-primary" data-toggle="collapse"
								data-target="#collapseThree" name="getTagesessen" type="submit">Show
								winner meal!</button>

						</div>
						<div id="collapseThree" class="collapse show"
							data-parent="#accordion">
							<div class="card-body">
								<%
									Set<EssenBean> tagesEssenSet = new TreeSet<EssenBean>();
									if ((Set<EssenBean>) request.getAttribute("getTagesEssen") != null) {
										tagesEssenSet = ((Set<EssenBean>) request.getAttribute("getTagesEssen"));
								%>
								<P>Tagesessen:
								<p>
									<%
										for (EssenBean s : tagesEssenSet) {
												out.println(s.getEssen().replace("_", " ") + " am " + s.getDate() + " mit " + s.getVotes()
														+ " stimmen");
									%>
								
								<P>
									<%
							}
							}
						%>
								
							</div>
						</div>

					</div>
				</form>



			</div>
		</div>
	</div>
			</div>
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