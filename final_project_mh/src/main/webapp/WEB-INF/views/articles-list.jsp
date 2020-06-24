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
<title>Favorite Affirmations</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">


<section id="list">
	<h1>Saved Articles</h1>
	<a href="/user">Go back</a>
	<br>
	<br>
	<c:forEach var="item" items="${list}">
		<b>${item.title}</b>
		<br>
		${item.description}
		<br>
		<a href="${item.url}">Read Article</a>
		<br>
		<i>${item.datetime}</i>
		<c:choose>
			<c:when test="${item.onProfile == 0}">
				<a href="/post?type=article&id=${item.id}&url=/list/records">Post</a> / 
				<a href="/delete/article?id=${item.id}&url=/list/records">
				Delete</a>
				<br>
			</c:when>
			<c:otherwise>
				<a href="/post/remove?type=article&id=${item.id}&url=/list/records">Remove</a>
				<br>
			</c:otherwise>
		</c:choose>
		<br>
		<br>
	</c:forEach>


</section>


</main>

</body>
</html>