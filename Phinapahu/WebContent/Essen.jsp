<%@page import="org.Essen.EssenBean"%>
<%@page import="org.EssenBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Essensabstimmung</title>
</head>
<body>

	<%@ page import="java.util.*"%>

	<form action="Date" method="post">
		<label for="Date">Datum</label> <input name="Date" type="text"
			value="${param.Date}" />

		<button name="addDate" type="submit">Datum hinzufügen</button>

	</form>
	<p>
	<form action="EssenErstellen" method="post">
		<label for="EssenDate">Datum</label> <input name="EssenDate" type="text"
			value="${param.EssenDate}" /> <label for="Essen">Essen</label> <input
			name="Essen" type="text" value="${param.Essen}" />

		<button name="addEssen" type="submit">Essen hinzufügen</button>
	</form>

	<p>
	<h3>Daten</h3>

	<form action="GetDates" method="post">
	<button name="getDates" type="submit">Daten Anzeigen</button>
	<% //List<String> dateList =new ArrayList<String>();
	Set<String> dateSet = new TreeSet<String>();
	if((Set<String>) request.getAttribute("getDateSet") != null){
		dateSet = ((Set<String>) request.getAttribute("getDateSet")) ;
	// out.print("Array nicht leer");
	 %>
	 <P>
	 <%
	 for(String s :dateSet){
		out.println(s); 
		 %>
		 <P>
		 <%
	 }
	// out.print(" das war das array");
	}
	else{
		//out.print("Array leer");
	}
		
	%>
  </form>
  <p>
  
	<form action="GetEssen" method="post">
	<button name="getEssen" type="submit">Essen Anzeigen</button>
	<% //List<String> dateList =new ArrayList<String>();
	Set<EssenBean> essenSet = new TreeSet<EssenBean>();
	if((Set<EssenBean>) request.getAttribute("getEssenSet") != null){
		essenSet = ((Set<EssenBean>) request.getAttribute("getEssenSet")) ;
	// out.print("Array nicht leer");
	 %>
	 <P>
	 <%
	 for(EssenBean s :essenSet){
		out.println(s.getEssen() + " am " + s.getDate()); 
		 %>
		 <P>
		 <%
	 }
	// out.print(" das war das array");
	}
	else{
		//out.print("Array leer");
	}
		
	%>
  </form>

</body>
</html>