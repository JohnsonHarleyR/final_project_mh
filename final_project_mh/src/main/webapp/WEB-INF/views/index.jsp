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
<title>Home Page</title>
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

<c:if test="${points}">
<script>

addPoints();

</script>
</c:if>

<!-- MainBody -->
<main class="container">

<h1>Welcome,<br>you are not alone!</h1>

<p>
We all need help sometimes, there is no shame in that. No matter what life may bring, 
you deserve to be happy and healthy. That is why we hope to provide you with more of 
the love and support you need.
</p>

<p>
Here, you will find resources, articles, and affirmations to help you stay healthy. You'll 
also find social support from others who understand what you are going through. This is 
a safe space for you to grow and we are glad you're here!</b>
</p>

<c:if test="${loggedin}">
<p>
<a href="/users">See Users</a><br>
<a href="/messages">See Messages</a><br>
</p>
</c:if>

<br>

<section>

<h2 style="color: #FF0000">Need Help?</h2>
<a href="/emergency">These services are available 24/7 and free!</a>

</section>

</main>

</body>
</html>