<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE>Dynamically add Textbox, Radio, Button in html Form using JavaScript</TITLE>
<SCRIPT language="javascript">
var counter = 0;
function add(type) {

	//Create an input type dynamically.
	var element = document.createElement("input");

	//Assign different attributes to the element.
	element.setAttribute("type", "text");
	element.setAttribute("value", "Enter Email");
	element.setAttribute("name", "Email" + counter++);


	var foo = document.getElementById("fooBar");
	
	linebreak = document.createElement("br");
	foo.appendChild(linebreak);

	//Append the element in page (in span).
	foo.appendChild(element);

}
</SCRIPT>
</HEAD>
<BODY>
<FORM action = "CreateHouseholdServlet" method = "post">
Please enter a name for the Household
	<p>
	
		<label for ="HouseholdName">Name</label> 
		<input name ="HouseholdName" type ="text" value="${param.HouseholdName}"/>
	</p>
Click on Add Email to add Email Addresses of future household members
<BR/>


<INPUT type="button" value="Add Email" onclick="add(document.forms[0].text)"/>

<span id="fooBar">&nbsp;</span>
	<p>
		<button name= "login" type = "submit">Create Household</button>
	</p>
</FORM>
</BODY>
</HTML>