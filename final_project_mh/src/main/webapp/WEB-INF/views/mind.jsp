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


<h1>Mind</h1>

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


<section id="randomized">

<h2><c:out value="${article.title }"></c:out></h2>
<c:out value="${article.description }"></c:out>
<br>
<a href="${article.url}">Read Article</a>

<c:if test="${loggedin}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${article.title}"/>
			<input type="hidden" name="description" 
			value="${article.description}"/>
			<input type="hidden" name="url" 
			value="${article.url}"/>
			<button type="submit">Save</button>
		</form>
		</c:if>
</section>




</body>
</html>