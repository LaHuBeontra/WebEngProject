<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<HTML>
<HEAD>

<TITLE>Create Household</TITLE>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/Management.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous"><script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src = "jquery/jquery-3.3.1.min.js"></script>



<script type="text/javascript">
	var counter = 0;
	function add(type) {
		//Create an input type dynamically.
		var element = document.createElement("input");
		//Assign different attributes to the element.
		element.setAttribute("type", "email");
		element.setAttribute("placeholder", "Enter Email");
		element.setAttribute("class", "form-control");
		element.setAttribute("name", "Email" + counter++);
		element.setAttribute("class", "bottom2")
		var foo = document.getElementById("fooBar");
		linebreak = document.createElement("br");
		foo.appendChild(linebreak);
		//Append the element in page (in span).
		foo.appendChild(element);
	}
	
	//Function adds html-Tag <br/> whenever the user entered a linebreak in the textarea
	function textareaReplaceLineBreaks() {
		document.getElementById("invitationText").value = document
				.getElementById("invitationText").value.replace(/(\r\n|\n)/g,
				"<br/>");
	}
</script>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<div class="container content">
		<div class="row">
			<div class="jumbotron col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 margin">
				<h1 class="text-center">Create Household</h1>
				<br>
        <h3 style="color:red">${createHouseholdError}</h3>
				<FORM action="CreateHouseholdServlet" method="post"
					accept-charset=utf-8>

					<div class="form-group">

						<label for="HouseholdName">Name</label> <br> <input
							name="HouseholdName" class= "form-control" onfocus="this.value=''" type="text" placeholder="Name of Household"
							value="${param.HouseholdName}" />
					</div>
					<div class="text text-info">
						Click here to add Email Addresses of future household
						members <br />
					</div>
					<h5 style="color: red">${emailMissing}</h5>

					<button type="button" class="btn btn-secondary" onclick ="add(document.forms[0].text)">
						<i class ="fa fa-plus"></i> Add Email
					</button>

						 <br> <span
						id="fooBar">&nbsp;</span>
					
					<br/>
					<br/>
					
				<div class="form-group">
						<label for="invitationText">Please enter a text that will
							be sent to the person you want to invite:</label>
						<textarea onfocus="this.value=''" class="form-control"
							id="invitationText" name="invitationText">Enter invitation mail...</textarea>
					</div>
<br/>
					<div class="form-group">
						<button class = "btn btn-primary" name="login" onclick="textareaReplaceLineBreaks();"
							type="submit">Create Household</button>
					</div>

				</FORM>
			</div>
		</div>
	</div>
	<jsp:include page ="Footer.jsp"/>
</BODY>
</HTML>