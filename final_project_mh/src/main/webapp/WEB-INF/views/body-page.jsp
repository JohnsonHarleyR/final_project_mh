<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="/style.css" rel="stylesheet" />
</head>
<body>

<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link" id="exercise-tab" data-toggle="tab" href="#exercise" role="tab" aria-controls="exercise" aria-selected="true">Exercise</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="food-tab" data-toggle="tab" href="#food" role="tab" aria-controls="food" aria-selected="false">Nutrition</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="workout-tab" data-toggle="tab" href="#workout" role="tab" aria-controls="workout" aria-selected="false">Curated Workouts</a>
  </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
  <div class="tab-pane active" id="exercise" role="tabpanel" aria-labelledby="exercise-tab">
  
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
  
  
  
  </div>
  
  
  <div class="tab-pane" id="food" role="tabpanel" aria-labelledby="food-tab">
  
  <form action = "/body" name = "foodForm" method = "post">
	
	          <textarea name = "userInput" rows = "3" cols = "80">Your text here</textarea>
					<input type="submit" value="Submit">
	
</form>
<c:forEach var= "foods" items="${food}">
<h2>Calories: ${foods.nf_calories}</h2>
</c:forEach>
  
 
  </div>
  <div class="tab-pane" id="workout" role="tabpanel" aria-labelledby="workout-tab">
  
  <c:forEach var= "result" items="${resultList}">
<h3>${result.name}</h3>
<p>${result.description}</p>

</c:forEach>
  
  </div>
</div>


</body>
</html>