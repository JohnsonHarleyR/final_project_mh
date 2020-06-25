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
<title>Invest-Points</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">
<!-- Form to pass achievement into database to be displayed on profile page, make popup that shows points, also delete or update achieve.-->
<c:if test = "${loggedIn == true}">
<c:if test = "${enoughPoints == true}">

<h3>Achievement List</h3>
	<a href="/user">Go back</a><br>
 Spend your earned points to post on your profile page an achievement you are proud of.
 <br>
	<section>
	<form action = "/submit/achievement">
	<br>
	<label for = "achivementName"> Achievement Name: </label>
	<br>
	<input type = "text" id = "achievementName" name = "achievementName" size ="50" maxlength="50" required>
	<br>
	<label for = "achievementDescription"> A Short Description: </label>
	<br>
	<input type = "text" id = "achievementDescription" name = "achievementDescription" 
	               size ="50" maxlength="120" required>
	<br>              
	<label for = "achivementDate"> Date: </label>
	<br>
	<input type = "date" id = "achievementDate" name = "achievementDate" required>
	<br>
	<br>
	<button class="btn btn-info" type="submit">Invest 10 pts</button>
</form>
</section>

<br>

<h3>Challenge Others</h3>
 <p>Create a positive challenge for others in the community. Reward a fair amount of points based upon the difficulty.
 Come up with challenges that promote happiness, growth, and connection. Reward those who complete your challenge with 
 a custom image. 
 <br>

<form action="/submit-challenge">
  <label for="category">Choose a Category:</label><br>
  <select name ="category" id ="category" required>
  <option value="mind">Mind</option>
  <option value="body">Body</option>
  <option value="soul">Soul</option>
  </select>
  <br>
  <label for="name">Challenge Title:</label>
  <br>
  <input type="text" id="name" name="name" size ="50" maxlength="50" required>
  <br>
  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description" size ="50" maxlength="90" required>
  <br>
  <label for="prize_url">Prize Url:</label><br>
  <input type="text" id="prize_url" name="prize_url" size ="50" required>
  <br>
  <label for="points-req"> Points Awarded by Difficulty:</label>
  <br>
  <input type="radio" id="easy" name="points_req" value="5">
  <label for="easy">Easy: 5 pts</label><br>
  <input type="radio" id="medium" name="points_req" value="10">
  <label for="medium">Medium: 10 pts</label><br>
  <input type="radio" id="hard" name="points_req" value="15">
  <label for="hard">Hard: 15 pts</label><br>
  <br>
  <button class="btn btn-info" type="submit">Invest 15 pts</button>
</form>
 </c:if>
 </c:if>
 
 <br>
 
 <!-- add an href -->
  <c:if test = "${loggedin != true}">
 <h1>Nothing to see here. Return to Homepage and Login </h1>
 </c:if> 
 <c:if test = "${enoughPoints != true}">
 <h1> You do not have enough points. Please Refresh. </h1>
 </c:if> 

</main>
</body>
</html>