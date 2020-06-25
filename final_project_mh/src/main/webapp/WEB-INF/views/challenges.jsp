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
<title>Challenge Page</title>
</head>
<body>

	<!-- Header -->
	<section class="header">
		<%@ include file="partials/header.jsp"%>
	</section>

	<!-- MainBody -->
	<main class="container">
		<br>
		<h1>Challenges</h1>
		<a href="/user">Go back</a>
		<p>Here you are able to accept positive challenges from your
			friends and the larger community.</p>
		<p>By accepting challenges you earn points that you can use to
			enrich the lives of others.</p>

		<h2>Active Challenges</h2>
        <p>This is a list of challenges you have accepted and plan on completing. After completing the challenge you earn a reward. 
           If things become to cluttered feel free to delete the challenges you no longer want to keep. 
           </p>
		<section>
			<c:forEach items="${userChallengeList}" var="userchallenge">
				<c:choose>
					<c:when test="${userchallenge.complete}">
						<div class="card" style="width: 100%;">
							<ul class="list-group list-group-flush">
								<li class="list-group-item"><a
									href="/challenge-complete?id=${userchallenge.challengeId}&complete=false">Completed</a><b>
										${userchallenge.name}</b> : ${userchallenge.description} <small>${userchallenge.date}</small>
									<b> Reward :</b> <img src=${userchallenge.prizeUrl} alt="Prize Icon" width="50px" height="50px" class="img-thumbnail">
								</li>
							</ul>
					</c:when>
					<c:otherwise>
						<div class="card" style="width: 100%;">
							<ul class="list-group list-group-flush">
								<li class="list-group-item"><a
									href="/challenge-complete?id=${userchallenge.challengeId}&complete=true">Change
										to Completed</a><b> ${userchallenge.name}</b> :
									${userchallenge.description} <small>${userchallenge.date}</small>
									<a href = "/delete/acceptedchallenge?id=${userchallenge.challengeId}">Delete</a>
								</li>
							</ul>
					</c:otherwise>
				</c:choose>
				</ul>
				</div>
			</c:forEach>
		</section>


		<h2>Community Challenges</h2>
		<p> Community challenges are a wonderful way to create a positive uplifting community. 
		    Save the challenges for future completion and connect with others through the forum.  
		<section>

			<c:forEach items="${challengeList}" var="challenge">

				<div class="card" style="width: 100%;">
					<ul class="list-group list-group-flush">
						<li class="list-group-item"><a
							href="/select-challenge?id=${challenge.challengeListId}">Save
								Challenge</a><b> ${challenge.name}</b> : ${challenge.description} <small>${challenge.datetime}</small>
						</li>
					</ul>
				</div>

			</c:forEach>
		</section>








	</main>
</body>
</html>