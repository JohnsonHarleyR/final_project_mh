<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
</head>
<body>

<section class="header">
<%@ include file="partials/header.jsp" %>
</section>
<h1>Enter and exercise: </h1>
	<form action = "/body" name = "exerciseForm" method = "post">
	
	          <textarea name = "userInput" rows = "3" cols = "80">Your text here</textarea>
					<input type="submit" value="Submit">
	
	</form>

<c:forEach var= "exercise" items="${exercises}">
<h2>Total Calories burned: ${exercise.nf_calories}</h2>
<h2>Time in minutes: ${exercise.duration_min}</h2>
<h2>Exercise name : ${exercise.name}</h2>
<form action="/save/exercises" method="post">	
	<input type="hidden" name ="nf_calories" value="${exercise.nf_calories}"/>
	<input type="hidden" name ="duration_min" value="${exercise.duration_min}"/>
	<input type="hidden" name ="name" value="${exercise.name}"/>
	<button type="submit">Save</button>
</form>
<form action="/complete/workout" method="post">	
	<button type="submit">I did this</button>
</form>
</c:forEach>

<form action = "/bodyfood" name = "foodForm" method = "post">
	
	          <textarea name = "userInput" rows = "3" cols = "80">Your text here</textarea>
					<input type="submit" value="Submit">
	
</form>
<c:forEach var= "foods" items="${food}">
<h2>Calories: ${foods.nf_calories}</h2>
</c:forEach>


<c:forEach var= "result" items="${resultList}">
<h3>${result.name}</h3>
<p>${result.description}</p>

</c:forEach>


</body>
</html>