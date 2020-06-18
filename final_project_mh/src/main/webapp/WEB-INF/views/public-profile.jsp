<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

<meta charset="ISO-8859-1">
<title>${user.username}'s Profile</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

	<h1>${profileuser.username}'s Profile</h1>
	<section id="add-friend"">
	<!-- Check if user is friends with them - if user is logged in -->
	<c:if test="${loggedin}">
		<c:choose>
			<c:when test="${profileuser.id == user.id}">
			This is your public profile.
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${isfriend == true}">
						You are friends! 
						(<a href="/delete/friend?user=${user.id}&friend=${profileuser.id}">Remove</a>)
					</c:when>
					<c:when test="${isrequested == true}">
						<a href="/cancel/request?user=${user.id}&friend=${profileuser.id}">Cancel Request</a>
					</c:when>
					<c:otherwise>
						<a href="/add/friend?user=${user.id}&friend=${profileuser.id}">Add Friend</a>
					</c:otherwise>
				</c:choose>
				
			</c:otherwise>
		</c:choose>
		
	</c:if>
	<br>
	
	</section>

	<section id="info" style="padding-top:15px">
	
	
	<!-- User info -->
	<b>Name: </b> ${profileuser.name}
	<br>
	<b>Points: </b> ${profileuser.points}
	<br>
	</section>
	
	



</main>

</body>
</html>