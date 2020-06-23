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
<title>Create a Thread</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

	
	<h1>Create a New Thread</h1>
	<!-- Check if user is logged in. 
	If they are, let them create thread.
	Otherwise, ask them to sign in.-->
	<c:choose>
		<c:when test="${loggedin}">
	
			<form action="/thread/add/submit" method="post">
			Discussion: <c:out value="${discussion.topic}"/>
			<br>
			<br>
			<label>What should the thread be called?</label><br>
<<<<<<< HEAD
			<input type="text" size="50" placeholder="Topic Title" name="threadTitle"/><br>
=======
			<input type="text" class="postwidth" placeholder="Topic Title" name="topic"/><br>
>>>>>>> 8deb68cfe2a8f6f03205e28cdf114a3fc07b75b5
			<br>
			<label>What should the first post say?</label><br>
			<textarea name="comment" rows="7" class="postwidth" maxlength="1000" 
			placeholder="Write post here." required></textarea>
			<br>
			<input type="hidden" name="discussionId" value="${discussion.id }">
			<button class="btn btn-info" type="submit">Create Thread</button>
			<a href="/forum/discussion?id=${discussion.id}" 
			style="color: #ffffff;" class="btn btn-info">Cancel</a>
			</form>

		</c:when>
		<c:otherwise>
			Sorry, you must be logged in to create a thread.
			<br>
			<a href="/login">Sign In</a>
		</c:otherwise>
	</c:choose>
	

</main>

</body>
</html>