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
<title>List of Records</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">


<section id="list">
	<h1>List of Records</h1>
	<a href="/user">Go back</a>
	
	<h2>Add a Happy Reminder</h2>
	One way to stay positive is to record what makes you feel happy. This could be an event, 
	a compliment from a stranger, or an uplifting saying.<br> 
	Whatever it may be, record it here for when you need a 
	pick-me-up!
	
	<form action="/record" method="post">
	<textarea name="text" rows="5" cols="50" placeholder="What made you smile?" required></textarea>
	
	<br>
	<input type="hidden" name="list" value="yes"/>
	<button type="submit">Add</button>
	</form>
	
	<c:forEach var="item" items="${list}">
		
		<h2></h2>
		${item.text}
		<br>
		<i>${item.datetime}</i>
		<c:choose>
			<c:when test="${item.onProfile == 0}">
				<a href="/post?type=record&id=${item.id}&url=/list/records">Post</a> / 
				<a href="/delete/record?id=${item.id}&url=/list/records">
				Delete</a>
				<br>
			</c:when>
			<c:otherwise>
				<a href="/post/remove?type=record&id=${item.id}&url=/list/records">Remove</a>
				<br>
			</c:otherwise>
		</c:choose>
		
		<br>
	</c:forEach>


</section>


</main>

</body>
</html>