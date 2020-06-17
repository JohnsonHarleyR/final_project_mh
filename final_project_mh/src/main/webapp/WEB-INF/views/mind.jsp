<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mind Page</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<main class="container">
<h1>Mind</h1>

<section id="game">
	<div style="width:55%;height:5%;float:right;padding:10px;">
		<script src="https://cdn.htmlgames.com/embed.js?game=DailyWordSearch&amp;&amp;bgcolor=white"></script>
	</div>
</section>


<section id="important">
	<h2><c:out value="${important.title }"></c:out></h2>
<c:out value="${important.description }"></c:out>
<br>
<a href="${important.url}">Read Article</a>

<c:if test="${loggedin}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${important.title}"/>
			<input type="hidden" name="description" 
			value="${important.description}"/>
			<input type="hidden" name="url" 
			value="${important.url}"/>
			<button type="submit">Save</button>
		</form>
		</c:if>

</section>

<section id="mindfulness">
	<h2><c:out value="${mindfulness.title }"></c:out></h2>
<c:out value="${mindfulness.description }"></c:out>
<br>
<a href="${mindfulness.url}">Read Article</a>

<c:if test="${loggedin}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${mindfulness.title}"/>
			<input type="hidden" name="description" 
			value="${mindfulness.description}"/>
			<input type="hidden" name="url" 
			value="${mindfulness.url}"/>
			<button type="submit">Save</button>
		</form>
		</c:if>

</section>


<section id="randomized1">

<h2><c:out value="${article1.title }"></c:out></h2>
<c:out value="${article1.description }"></c:out>
<br>
<a href="${article1.url}">Read Article</a>

<c:if test="${loggedin}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${article1.title}"/>
			<input type="hidden" name="description" 
			value="${article1.description}"/>
			<input type="hidden" name="url" 
			value="${article1.url}"/>
			<button type="submit">Save</button>
		</form>
		</c:if>
</section>

<section id="randomized2">

<h2><c:out value="${article2.title }"></c:out></h2>
<c:out value="${article2.description }"></c:out>
<br>
<a href="${article2.url}">Read Article</a>

<c:if test="${loggedin}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${article2.title}"/>
			<input type="hidden" name="description" 
			value="${article2.description}"/>
			<input type="hidden" name="url" 
			value="${article2.url}"/>
			<button type="submit">Save</button>
		</form>
		</c:if>
</section>


</main>

</body>
</html>