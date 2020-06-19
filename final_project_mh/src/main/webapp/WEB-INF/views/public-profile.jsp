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

<script>
function addPoints() {
  alert("You just earned points.");
}
</script>

<script>
function deletePoints() {
  alert("You just lost points.");
}
</script>

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
						(<a  onclick="deletePoints()" href="/delete/friend?user=${user.id}&friend=${profileuser.id}">Remove</a>)
					</c:when>
					<c:when test="${acceptrequest == true}">
						<a onclick="addPoints()" href="/accept/request?user=${user.id}&friend=${profileuser.id}">Accept Request</a>
					</c:when>
					<c:when test="${isrequested == true}">
						<a onclick="deletePoints()" href="/cancel/request?user=${user.id}&friend=${profileuser.id}">Cancel Request</a>
					</c:when>
					<c:otherwise>
						<a onclick="addPoints()" href="/add/friend?user=${user.id}&friend=${profileuser.id}">Add Friend</a>
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
	
	<!--Comment Section -->

	<section id="comment">
	<c:if test="${canComment}">
	<form action="comment" method="post">
	<textarea name="comment" rows="5" cols="50" maxlength="500" 
	placeholder="Say something nice!" required></textarea>
	
	<br>
	<input type="hidden" name="profileId" value="${profileuser.id}"/>
	<button class="btn btn-info" type="submit">Add</button>
	</form>
	<br>
	<h2>Comments</h2>
	<c:forEach var="comment" items="${comments}">
		
		
		${comment.comment}
		<br>
		<i>${comment.datetime}</i> <a href="/delete/comment?id=${comment.id}&profileuserId=${profileuser.id}">
		Delete</a>
		
		<br>
	</c:forEach>
	</c:if>
</section>
	



</main>

</body>
</html>