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
	<main class="container">

		<h1>${profileuser.username}'sProfile</h1>


		<section id="add-friend"">
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

		<section id="info" style="padding-top: 15px">


			<!-- User info -->
			<b>Name: </b> ${profileuser.name} <br> <b>Points: </b>
			${profileuser.points} <br>
		</section>

		<section>

		<c:forEach items = "${achieve}" var = "achievement">

			<div class="card" style="width: 36rem;">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b>${achievement.name}</b> : ${achievement.description} <small>${achievement.date}</small> </li>
				</ul>
			</div>
		</c:forEach>
		</section>

		<!--Comment Section -->
		<section id="comment">
			<c:if test="${canComment}">
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
					</p>
				</c:forEach>
				<h2>Leave a comment</h2>
				<form action="comment" method="post">
					<textarea name="comment" rows="5" cols="50" maxlength="500"
						placeholder="Say something nice!" required></textarea>

					<br> <input type="hidden" name="profileId"
						value="${profileuser.id}" />
					<button class="btn btn-info" type="submit">Add Comment</button>
				</form>
				<br>
			</c:if>
		</section>




	</main>

</body>
</html>