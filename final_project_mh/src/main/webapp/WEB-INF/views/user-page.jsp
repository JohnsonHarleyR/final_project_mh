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
<title>User Page</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">
<h1>${user.name}'s Page</h1>

<section id="user-options">
	<a href="/friends?id=${user.id}">See friends</a>
	<br>
	<a href="/profile?id=${user.id}">See your profile</a>
	<br>
	<br>
	<b>Points:</b> ${user.points}
	<br>
	<b>Username:</b> ${user.username}
	<br>
	<br>
</section>

<section id="record-events">
	<h2>Happy Reminders</h2>
	One way to stay positive is to record what makes you feel happy. This could be an event, 
	a compliment from a stranger, or an uplifting saying.<br> 
	Whatever it may be, record it here for when you need a 
	pick-me-up!
	
	<form  style="padding-top: 15px" action="record" method="post">
	<textarea name="text" rows="5" cols="50" maxlength="500" 
	placeholder="What made you smile?" required></textarea>
	
	<br>
	<input type="hidden" name="list" value="no"/>
	<button class="btn btn-info" type="submit">Add</button>
	</form>
	<br>
	<h3>More Positives</h3>
	<c:forEach var="record" items="${records}" end="2">
		
		
		${record.text}
		<br>
		<i>${record.datetime}</i> <a href="/delete/record?id=${record.id}&url=/user">
		Delete</a>
		
		<br>
	</c:forEach>
	
	<!-- Form to pass list into "display more" page -->
	<form action="/list/records" method="post">
	<button class="btn btn-info" type="submit">See More</button>
	</form>
	<br>
</section>
<!-- Form to pass achievement into database to be displayed on profile page, make popup that shows points, also delete or update achieve.-->
<section>
<form action = "/submit/achievement">
<label for = "achivementName"> Achievement Name: </label>
<input type = "text" id = "achievementName" name = "achievementName">
<label for = "achievementDescription"> Description: </label>
<input type = "text" id = "achievementDescription" name = "achievementDescription">
<label for = "achivementDate"> Date: </label>
<input type = "date" id = "achievementDate" name = "achievementDate">
<input type = "submit" value = "Submit">
</form>





</section>



<section id="affirmations">
	<h2>Favorite Affirmations</h2>
	<c:forEach var="affirmation" items="${affirmations}" end="2">
		
		
		${affirmation.affirmation}
		<br>
		<i>${affirmation.datetime}</i> <a href="/delete/affirmation?id=${affirmation.id}&url=/user">
		Delete</a>
		
		<br>
	</c:forEach>
	
	
	<!-- Form to pass list into "display more" page -->
	<form action="/list/affirmations" method="post">
	<button class="btn btn-info" type="submit">See More</button>
	</form>
	<br>
</section>

<section id="exercises">
	<h2>Completed Exercises</h2>
	<c:forEach var="item" items="${exercises}" end="2">
		<b>${item.name}</b>
		<br>
		Calories: ${item.nf_calories}
		<br>
		Duration: ${item.duration_min} minutes
		<br>
		<i>${item.datetime}</i> <a href="/delete/exercise?id=${item.id}&url=/user">
		Delete</a>
		
		<br>
	</c:forEach>
	
	
	<!-- Form to pass list into "display more" page -->
	<form action="/list/exercises" method="post">
	<button class="btn btn-info" type="submit">See More</button>
	</form>
		<br>
</section>

<section id="articles">
	<h2>Saved Articles</h2>
	<c:forEach var="item" items="${articles}" end="2">
		<b>${item.title}</b>
		<br>
		${item.description}
		<br>
		<a href="${item.url}">Read Article</a>
		<br>
		<i>${item.datetime}</i> <a href="/delete/article?id=${item.id}&url=/user">
		Delete</a>

		<br>
	</c:forEach>
	
	
	<!-- Form to pass list into "display more" page -->
	<form action="/list/articles" method="post">
	<button class="btn btn-info" type="submit">See More</button>
	</form>
		<br>
</section>



</main>

</body>
</html>