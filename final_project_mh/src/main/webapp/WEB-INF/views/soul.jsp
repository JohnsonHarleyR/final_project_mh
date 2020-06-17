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
<title>Soul Page</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>



<!-- MainBody -->
<main class="container">

<!-- Let's try kittens - kitten api -->
<img style="float: right;" src="http://placekitten.com/${wi}/${he}" alt="Place Kitten" />
<br>

<!-- If user is logged in, show option to save -->
<c:choose>
	<c:when test="${!loggedin}">
		${affirmation}
	</c:when>
	<c:when test="${exists}">
		${affirmation}
	</c:when>
	<c:otherwise>
		<form action="/save/affirmation" method="post">
			${affirmation}
			<br>
			<input type="hidden" name="affirmation" 
			value="${affirmation}"/>
			<button type="submit">Save</button>
		</form>
	</c:otherwise>
</c:choose>

<br>
<br>

<!-- Do it with the quote nows -->
<!-- If user is logged in, show option to save -->
<c:choose>
	<c:when test="${loggedin == false}">
		${quote.quote}
		${quote.author}
	</c:when>
	<c:when test="${qexists == true}">
		${quote.quote}
		${quote.author}
	</c:when>
	<c:otherwise>
		<form action="/save/affirmation" method="post">
			${quote.quote}
			${quote.author}
			<br>
			<input type="hidden" name="affirmation" 
			value="${quotestring}"/>
			<button type="submit">Save</button>
		</form>
	</c:otherwise>
</c:choose>


</main>

</body>
</html>