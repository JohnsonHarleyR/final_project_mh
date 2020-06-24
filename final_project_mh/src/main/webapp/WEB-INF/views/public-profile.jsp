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
<title>${user.username}'sProfile</title>
</head>
<body>

	<!-- Header -->
	<section class="header">
		<%@ include file="partials/header.jsp"%>
	</section>

	<script>
		function addPoints() {
			alert("You just earned points.");
		}
	</script>

	<script>
		function deletePoints() {
			alert("You just lost points.");
		}
	</script>

	<!-- MainBody -->
	<main class="container" style="display:flex;flex-direction:column;
	text-align:center;margin:auto;justify-content:center;">
	
		<table class="table">
		<tr>
		<td width="100%" style="text-align:left;content:left;">

		<h1 >${profileuser.username}'sProfile</h1>


		<section id="add-friend">
			<!-- Check if user is friends with them - if user is logged in -->
			<c:if test="${loggedin}">
				<c:choose>
					<c:when test="${profileuser.id == user.id}">
			This is your public profile.
			<br>
						<a href="/friends?id=${profileuser.id}">See your friends</a>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${isfriend == true}">
						You are friends! 
						(<a onclick="deletePoints()"
									href="/delete/friend?user=${user.id}&friend=${profileuser.id}">Remove</a>)
						<br>
							<a href="/message?id=${profileuser.id}">Message</a> / 
								<a href="/friends?id=${profileuser.id}">Friend List</a>
							</c:when>
							<c:when test="${acceptrequest == true}">
								<a onclick="addPoints()"
									href="/accept/request?user=${user.id}&friend=${profileuser.id}">Accept
									Request</a>
							</c:when>
							<c:when test="${isrequested == true}">
								<a onclick="deletePoints()"
									href="/cancel/request?user=${user.id}&friend=${profileuser.id}">Cancel
									Request</a>
							</c:when>
							<c:otherwise>
								<a onclick="addPoints()"
									href="/add/friend?user=${user.id}&friend=${profileuser.id}">Add
									Friend</a>
							</c:otherwise>
						</c:choose>

					</c:otherwise>
				</c:choose>

			</c:if>
			<br>

		</section>
		</td>
		</tr>
		
		<tr>
		<td>
		<section id="info" style="padding-top:15px;text-align:left;content:left;">


			<!-- User info -->
			<b>Name: </b> ${profileuser.name} <br> <b>Points: </b>
			${profileuser.points} <br><br>
		</section>

		<section>

		<c:forEach items = "${achieve}" var = "achievement">

			<div class="card" style="width: 100%;">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b>${achievement.name}</b> : ${achievement.description} <small>${achievement.date}</small> </li>
				</ul>
			</div>
		</c:forEach>
		</section>
		</td>
		</tr>
		</table>


		<!-- Favorites Display Section -->
		<br>
		<table class="table">
		<section id="favorites" style="display:flex;">
			
			<tr>
			<td>
			
			<div id="records" style="text-align:left;content:left;">
				<h2>Happy Reminders</h2>
				<p>
				<c:choose>
				<c:when test="${arerecords}">
				<c:forEach var="record" items="${records}">
					
					
					${record.text}
					<br>
					<sup><i>${record.datetime}</i></sup>
					<br>
				</c:forEach>
				</c:when>
				<c:otherwise>
					There is nothing here yet.
				</c:otherwise>
				</c:choose>
				</p>
			</div>
			</td>
			
			
			<td>
			<div id="affirmations" style="text-align:left;content:left;">
				<h2>Favorite Affirmations</h2>
				<p>
				<c:choose>
				<c:when test="${areaffirmations}">
				<c:forEach var="affirmation" items="${affirmations}">
					
					
					${affirmation.affirmation}
					<br>
					<sup><i>${affirmation.datetime}</i></sup>
					<br>
				</c:forEach>
				</c:when>
				<c:otherwise>
					There is nothing here yet.
				</c:otherwise>
				</c:choose>
				</p>
			</div>
			</td>
			
			</tr>
			
			<tr>
			<td>
			<div id="exercises" style="text-align:left;content:left;">
				<h2>Completed Exercises</h2>
				<p>
				<c:choose>
				<c:when test="${areexercises}">
				<c:forEach var="item" items="${exercises}">
					<b>${item.name}</b>
					<br>
					Calories: ${item.nf_calories}
					<br>
					Duration: ${item.duration_min} minutes
					<br>
					<sup><i>${item.datetime}</i></sup>
					<br>
				</c:forEach>
				</c:when>
				<c:otherwise>
					There is nothing here yet.
					<br>
				</c:otherwise>
				</c:choose>
				</p>
			</div>
			</td>
			
			<td>
			<div id="articles" style="text-align:left;content:left;">
				<h2>Favorite Articles</h2>

				
				<p>
				<c:choose>
				<c:when test="${arearticles}">
				<c:forEach var="item" items="${articles}">
					<b>${item.title}</b>
					<br>
					${item.description}
					<br>
					<a href="${item.url}">Read Article</a>
					<br>
					<sup><i>${item.datetime}</i></sup>
					<br>
				</c:forEach>
				</c:when>
				<c:otherwise>
					There is nothing here yet.
					<br>
				</c:otherwise>
				</c:choose>
				</p>
			</div>
			</td>
			</tr>
			
		
		</section>
		

		
		<!--Comment Section -->
		
		<section id="comment" style="text-align:left;content:left;padding-top:15px;">
			<c:if test="${canComment}">
			<tr style="text-align:left;content:left;padding-top:15px;">
			
			<td width="50%">
			<br>
			<br>
		
			
				<h2>Comments</h2>
				<c:if test="${!arecomments}">
		There are no comments yet.
		<br>
					<br>
				</c:if>
				<c:forEach var="comment" items="${comments}">
					<p style="font-size: larger">

						${comment.comment} <br> <sup> <a
							href="/profile?id=${comment.commenterId}"><i>${comment.username}</i></a>
							on <i>${comment.datetime}</i> <!-- Only show delete button if it's the session user's profile or their own comment -->
							<c:if
								test="${comment.profileId == user.id || comment.commenterId == user.id }">
								<a
									href="/delete/comment?id=${comment.id}&profileuserId=${profileuser.id}">
									Delete</a>
							</c:if></sup>
							<br>
					</p>
				</c:forEach>
				</td>
			
			</tr>
			<tr>
			
			<td width="50%">
				<div  style="text-align:left;content:left;">
				
				<h2>Leave a comment</h2>
				<form action="comment" method="post">
					<textarea name="comment" rows="5" cols="50" maxlength="500"
						placeholder="Say something nice!" required></textarea>

					<br> <input type="hidden" name="profileId"
						value="${profileuser.id}" />
					<button class="btn btn-info" type="submit">Add Comment</button>
				</form>
				<br>
			</div>
			</td>
			
			
			
				

				
		</tr>
		</c:if>
		
		</section>
		</table>



	</main>

</body>
</html>