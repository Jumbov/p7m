<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Home Page</title>
	
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<h2>Hello <security:authentication property="principal.username" /> </h2>
	<hr>
	
	
	
	<p>
		Press the check button for seeing which of your files are validated.
	</p>
	

	<hr align=”left” size=”1” width=”300” noshade/>
	
	
	<security:authorize access="hasRole('SUBSCRIBER')">
	
		<!-- FILEs CHECK -->
		<button id="${pageContext.request.contextPath}" class="myFiles">Check</button>

	</security:authorize>	
	
	
	<div id="validationResult">
        <hr align=”left” size=”1” width=”300” noshade/>
        
        <ul id="fileList"></ul>                    <!-- filled with js-->
        
     </div>
	
	<br>
	<hr align=”left” size=”1” width=”300” noshade/>
	
	<!-- logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
	<script src="js/restClientManager.js"></script>
	
</body>

</html>
