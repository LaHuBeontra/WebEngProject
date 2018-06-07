<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE>Create Household</TITLE>
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
//Function adds html-Tag <br/> whenever the user entered a linebreak in the textarea
function textareaReplaceLineBreaks(){
	document.getElementById("invitationText").value = document.getElementById("invitationText").value.replace(/(\r\n|\n)/g, "<br/>");
}
</SCRIPT>
</HEAD>
<BODY>
<FORM action = "CreateHouseholdServlet" method = "post" accept-charset=utf-8>
Please enter a name for the Household<br>
	<p>
	
		<label for ="HouseholdName">Name</label> 
		<input name ="HouseholdName" type ="text" value="${param.HouseholdName}"/>
	</p>
Click on Add Email to add Email Addresses of future household members
<br/>
<h3 style="color:red">${emailMissing}</h3>


<INPUT type="button" value="Add Email" onclick="add(document.forms[0].text)"/>
<br>
<span id="fooBar">&nbsp;</span>
<p>
	Please enter a text that will be sent to the people you want to invite<br/>
	<textarea id = "invitationText" name = "invitationText" rows="5" cols="50" >Enter invitation mail...</textarea>
	
</p>
<p>
	<button name= "login" onclick = "textareaReplaceLineBreaks();" type = "submit">Create Household</button>
</p>
</FORM>
</BODY>
</HTML>