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
		<c:when test="${canAdd}">
	
			<form action="/discussion/create" method="post">
			<br>
			<br>
			<label>What should the discussion be called?</label><br>
			<input type="text" class="postwidth" placeholder="Discussion Title" name="topic"/><br>
			<br>
			<label>Give a brief description about what this discussion is about.</label><br>
			<textarea name="comment" rows="7" class="postwidth" maxlength="100" 
			placeholder="Write description here." required></textarea>
			<br>
			<label for="tag">Choose a tag...</label>
    		<select class="form-control" id="tag" name="tag">
      			<option value="welcome">Welcome</option>
      			<option value="general">General</option>
      			<option value="mental health">Mental Health</option>
    		</select>
			<br>
			<input type="hidden" name="username" value="${user.username}">
			<input type="hidden" name="post-type" value = "${user.status }">
			<button class="btn btn-info" type="submit">Create Discussion</button>
			<a href="/forum" 
			style="color: #ffffff;" class="btn btn-info">Cancel</a>
			</form>

		</c:when>
		<c:otherwise>
			Sorry, you must be logged in and a Admin to create a new discussion.
			<br>
			<a href="/login">Sign In</a>
		</c:otherwise>
	</c:choose>
	

</main>

</body>
</html>