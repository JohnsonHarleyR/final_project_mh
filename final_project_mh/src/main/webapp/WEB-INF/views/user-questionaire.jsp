
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

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<script>
function addPoints() {
  alert("You just earned points.");
}
</script>

<main id="card" class = "container"> 
  
<!-- Card Body -->
<div class = "card" style="max-width:30rem;">
<div class="card-header">
<h2>Lets Get to Know You</h2>
</div>
<div class="card-body">
<form method="post">
<input name= "userId" type="hidden" value="${user.id}" >

<%-- Mental Health Question --%>

  <div class="form-group" id = "mentalHealthQuestion">
  <label for="mentalHealthQuestion">Would you like to address any mental health concerns?</label>
<div class="form-check">
  <input class="form-check-input" name= "mentalHealth[]" type="checkbox" value="depression" id="check1" >
  <label class="form-check-label" for="check1">
    Depression
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name= "mentalHealth[]" type="checkbox" value="anxiety" id="check2" >
  <label class="form-check-label" for="check2">
    Anxiety
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name= "mentalHealth[]" type="checkbox" value="ocd" id="check3" >
  <label class="form-check-label" for="check3">
    OCD
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name= "mentalHealth[]" type="checkbox" value="null" id="check4" >
  <label class="form-check-label" for="check4">
    Prefer Not To Answer
  </label>
</div>
</div>

<%-- Music Preference Question --%>

<div class="form-group" id = "musicQuestion">
  <label for="musicQuestion">What kind of music do you like?</label>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Rap/Hip-Hop" id="check5" >
  <label class="form-check-label" for="check5">
    Rap/Hip-Hop
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Classical" id="check6" >
  <label class="form-check-label" for="check6">
    Classical
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Jazz" id="check7" >
  <label class="form-check-label" for="check7">
    Jazz
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Rock" id="check8" >
  <label class="form-check-label" for="check8">
    Rock 
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Pop" id="check9" >
  <label class="form-check-label" for="check8">
    Pop 
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Country" id="check10" >
  <label class="form-check-label" for="check8">
    Country 
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" name="musicPreferences[]" type="checkbox" value="Melodic-Techno" id="check11" >
  <label class="form-check-label" for="check8">
    Melodic-Techno
  </label>
</div>



</div>



  <%-- User Current Body Weight --%>
<div class="form-group">
    <label for="FormControlInput2" >How much do you weigh currently?</label>
    <input type="text" name="userWeight" class="form-control" id="FormControlInput2">
  </div>
  
 
 <%-- User Current Body Weight Goal --%>
  <div class="form-group">
    <label for="FormControlInput3" >What is your goal weight?</label>
    <input type="text" name="userGoalWeight" class="form-control" id="FormControlInput3">
  </div>
  
  <input type="hidden" name="plus" value="ten"/>
 
 <button type="submit" class="btn btn-info">Submit</button>


</form>
</div>
</div>

</main>
</body>
</html>