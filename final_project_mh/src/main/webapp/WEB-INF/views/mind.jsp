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


<script>
function addPoints() {
  alert("You just earned a point.");
}
</script>


<main class="container">
<h1>Mind</h1>


<!-- test for users -->
   <c:if test = "${loggedin == true}">

<div>
<div>
        <h3>Your Rank is: <c:out value = "${names}"/></h3>
        </div>
		<div class="progress">
			<div class="progress-bar" role="progressbar" style="width:<c:out value ="${percent}"/>%;" aria-valuenow="${total}"
				aria-valuemin="${min}" aria-valuemax="${max}"></div>
		</div>
		<p> Total Mind Points: <c:out value = "${total}"/></p>
		<p> Points till the Next Rank: <c:out value = "${nextRank}"/></p>
</div>
  </c:if>

<section id="game">
	<div style="width:55%;height:55%;float:right;padding:10px;">
		<script src="https://cdn.htmlgames.com/embed.js?game=DailyWordSearch&amp;&amp;bgcolor=white"></script>
	</div>
</section>


<section id="important">

	
	<h2><c:out value="${important.title }"></c:out></h2>
<c:out value="${important.description }"></c:out>
<br>
<a href="${important.url}">Read Article</a>

<c:if test="${loggedin && !exists1}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${important.title}"/>
			<input type="hidden" name="description" 
			value="${important.description}"/>
			<input type="hidden" name="url" 
			value="${important.url}"/>
			<button onclick="addPoints()" class="btn btn-info btn-sm" type="submit">Save</button>
		</form>
		</c:if>

</section>

<section id="mindfulness">
	<h2><c:out value="${mindfulness.title }"></c:out></h2>
<c:out value="${mindfulness.description }"></c:out>
<br>
<a href="${mindfulness.url}">Read Article</a>

<c:if test="${loggedin && !exists2}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${mindfulness.title}"/>
			<input type="hidden" name="description" 
			value="${mindfulness.description}"/>
			<input type="hidden" name="url" 
			value="${mindfulness.url}"/>
			<button class="btn btn-info btn-sm" onclick="addPoints()" type="submit">Save</button>
		</form>
		</c:if>

</section>


<section id="randomized1">

<h2><c:out value="${article1.title }"></c:out></h2>
<c:out value="${article1.description }"></c:out>
<br>
<a href="${article1.url}">Read Article</a>

<c:if test="${loggedin && !exists3}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${article1.title}"/>
			<input type="hidden" name="description" 
			value="${article1.description}"/>
			<input type="hidden" name="url" 
			value="${article1.url}"/>
			<button class="btn btn-info btn-sm" onclick="addPoints()" type="submit">Save</button>
		</form>
		</c:if>
</section>

<section id="randomized2">

<h2><c:out value="${article2.title }"></c:out></h2>
<c:out value="${article2.description }"></c:out>
<br>
<a href="${article2.url}">Read Article</a>

<c:if test="${loggedin && !exists4}">
<form action="/save/article" method="post">
			<input type="hidden" name="title" 
			value="${article2.title}"/>
			<input type="hidden" name="description" 
			value="${article2.description}"/>
			<input type="hidden" name="url" 
			value="${article2.url}"/>
			<button class="btn btn-info btn-sm" onclick="addPoints()" type="submit">Save</button>
		</form>
		</c:if>
</section>


</main>

</body>
</html>