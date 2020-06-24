<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
<meta charset="UTF-8">
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
	If they are, let them create post.
	Otherwise, ask them to sign in.-->
	<c:choose>
		<c:when test="${loggedin}">
	
			<form action="/post/add/submit" method="post">
			<br>
			<br>
			<label>What do you want to say?</label><br>
			<textarea name="comment" rows="7" cols="52" maxlength="1000" 
			placeholder="Write post here." required></textarea>
			<br>
			<input type="hidden" name="threadId" value="${threadId }">
			<button class="btn btn-info" type="submit">Post</button>
			<a href="/forum/thread?id=${threadId}" 
			style="color: #ffffff;" class="btn btn-info">Cancel</a>
			</form>
		
		</c:when>
		<c:otherwise>
			Sorry, you must be logged in to reply on a thread.
			<br>
			<a href="/login">Sign In</a>
		</c:otherwise>
	</c:choose>
	

</main>

</body>
</html>