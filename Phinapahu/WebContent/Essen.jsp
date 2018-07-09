<%@page import="org.Essen.EssenBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Essensabstimmung</title>
</head>
<body>
<h3 style="color:red">${voteMessage}</h3>
<h3 style="color:red">${essenMessage}</h3>

	<%@ page import="java.util.*"%>

	<form action="Date" method="post">
		<label for="Date">Datum</label> <input name="Date" type="text"
			value="${param.Date}" />

		<button name="addDate" type="submit">Datum hinzufügen</button>

	</form>
	<br>
	<h3>Daten</h3>

	<form action="GetDates" method="get">
	<button name="getDates" type="submit"> Neues Essen planen</button>
	<% //List<String> dateList =new ArrayList<String>();
	Set<String> dateSet = new TreeSet<String>();
	if((Set<String>) request.getAttribute("getDateSet") != null){
		dateSet = ((Set<String>) request.getAttribute("getDateSet")) ;
	// out.print("Array nicht leer");
	}
	 %>
	 
  </form>
  <br>
	
    <form action="EssenErstellen" method="post">
  <%if (dateSet.size() >0){ %>  
 
	 <label>Datum:</label>
	
	<select name="EssenDate" onchange="">
				<% for(String s :dateSet){
					%><option value=<%out.print(s); %>><%out.print(s); %></option><% 
				}
					
				%>	
			</select>
			<br>
			<br>
			 <label>Essen:</label> <input
	name="Essen" type="text" value="${param.Essen}" /> 
			<br>
			<br>
			<button name ="save" type="submit">Essen erstellen</button>
			<br>
			<br>
	<%} %>
	</form>
  
	<form action="GetEssen" method="get">
	<button name="getEssen" type="submit">Essen Anzeigen</button>
	<% 
	Set<EssenBean> essenSet = new TreeSet<EssenBean>();
	if((Set<EssenBean>) request.getAttribute("getEssenSet") != null){
		essenSet = ((Set<EssenBean>) request.getAttribute("getEssenSet")) ;
	 %>
	  </form>
	  
	 <br>
	   
	  <form action="Vote" method="post">
	
	 <%
	 for(EssenBean s :essenSet){
		out.println(s.getEssen().replace("_", " ") + " am " + s.getDate()+ " mit "+ s.getVotes() + " stimmen"); 
		 %>		
		  <button name="vote" name = "vote" id="vote" value=<% out.print( s.getDate()+";" + s.getEssen()+";"+s.getVotes());%> type="submit" >Für <% out.print(s.getEssen().replace("_", " ")); %> abstimmen</button>		
		  <br>
		 <%
	 }
	}
		
	%>
	  </form>
	  
	  <br>
	  <form action="GetTagesessen" method="get">
	<button name="getTagesessen" type="submit">Tagesessen Anzeigen</button>
	<% 
	Set<EssenBean> tagesEssenSet = new TreeSet<EssenBean>();
	if((Set<EssenBean>) request.getAttribute("getTagesEssen") != null){
		tagesEssenSet = ((Set<EssenBean>) request.getAttribute("getTagesEssen")) ;
	 %>
	 <br>
	 Tagesessen:
	 <br>
	 <%
	 for(EssenBean s :tagesEssenSet){
		 out.println(s.getEssen().replace("_", " ") + " am " + s.getDate()+ " mit "+ s.getVotes() + " stimmen"); 
		 %>
		 <br>
		 <%
	 }
	}
		
	%>
  </form>
</body>
</html>