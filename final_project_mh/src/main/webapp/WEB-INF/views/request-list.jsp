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
<title>Friend Request List</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

<h1>Friend Requests</h1>

<c:choose>
<c:when test="${requests == true}">
These users want to be your friend!
<br><br>
<ul>
	<li>
		<c:forEach var="ruser" items="${list}">
		${ruser.username} - <a href="/profile?id=${ruser.id}">See profile</a>
		<br>
		<a href="/accept/request?user=${user.id}&friend=${ruser.id}">Accept</a> /
		<a href="/cancel/request?user=${user.id}&friend=${ruser.id}">Deny</a>
		<br>
		</c:forEach>
	</li>
</ul>
</c:when>
<c:otherwise>
You do not have any friend requests.
</c:otherwise>
</c:choose>

</main>

</body>
</html>