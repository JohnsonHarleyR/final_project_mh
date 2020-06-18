<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Daily User Questionaire</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>



<main class = "container">

<!-- Card Body -->
<div class = "card" style="max-width:30rem;">
 <div class="card-header">
 <h2>Daily Check-in</h2>
 </div>
<div class="card-body">
<form method="post">
<input name= "userId" type="hidden" value="${user.id}" >
 <div class="form-group">
    <label for="FormControlSelect1">How are you feeling today?</label>
    <select name= "feelings" class="form-control" id="FormControlSelect1">
      <option>Happy</option>
      <option>Sad</option>
      <option>Un-Motivated</option>
      <option>Anxious</option>
      <option>Tired</option>
    </select>
</div>

<%--Question ties into the body page for display of workouts --%>
<div class="form-group" id="excerciseFocusOptions">
<label for="excerciseFocusOptions" >What part of your body would you like to work on today?</label>
<div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios1" value="9" >
  <label class="form-check-label" for="radios1">
   Legs
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios2" value="14">
  <label class="form-check-label" for="radios2">
   Calves
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios3" value="11" >
  <label class="form-check-label" for="radios3">
   Chest
  </label>
</div>

<div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios4" value="12" >
  <label class="form-check-label" for="radios4">
   Back
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios5" value="13" >
  <label class="form-check-label" for="radios5">
   Shoulders
  </label>
  </div>
  <div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios5" value="10" >
  <label class="form-check-label" for="radios5">
   Abs
  </label>
  </div>
  <div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios6" value="8" >
  <label class="form-check-label" for="radios6">Arms
  </label>
</div>
  <div class="form-check">
  <input class="form-check-input" type="radio" name="workoutFocus" id="radios7" value="0" >
  <label class="form-check-label" for="radios7">
   Not Working Out Today
  </label>
</div>
</div>

<%--Question ties into the mind page for display of an article --%>
<div class="form-group">
    <label for="FormControlSelect2">What topics are you interested in today?</label>
    <select name= "interests" class="form-control" id="FormControlSelect2">
      <option value = "0">Top Headlines</option>
      <option value = "1">Spirituality</option>
      <option value = "2">Overcoming Struggle</option>
      <option value = "3">Something Funny</option>
      <option value = "3">Mental Health</option>
      <option value = "4">Meditation</option>
    </select>
</div>

<button type="submit" class="btn btn-info">Submit</button>
</form>

</div>
</div>
</main>
</body>
</html>