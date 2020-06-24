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
<title>Pick an Avatar</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

<h1>Pick a Profile Avatar</h1>

<c:choose>
<c:when test="${loggedin}">
<p>As you gain points, more avatars will be unlocked!<br>
Keep checking back!</p>





<section id="avatars">
Click one to choose it!<br>
<c:set var="counter" value="0"/>
<c:forEach var="av" items="${avatars}">
<c:if test="${counter == 5 || counter == 10 }">
<br>
</c:if>
<a href="/avatar/choose?av=${av}"><img width="100px" alt="avatar" src="${av}"/></a>
<c:set var="counter" value="${counter + 1}"/>
</c:forEach>
</section>

<p>

<h2>Avatar Milestones</h2>
You can unlock an avatar at these total point milestones.
<ul>
<li>25 pts</li>
<li>50 pts</li>
<li>100 pts</li>
<li>200 pts</li>
<li>400 pts</li>
<li>800 pts</li>
<li>1,000 pts</li>
<li>1,500 pts</li>
<li>2,000 pts</li>
</ul>

</p>

</c:when>
<c:otherwise>
Please sign in or sign up to choose an avatar.
</c:otherwise>
</c:choose>

</main>

</body>
</html>