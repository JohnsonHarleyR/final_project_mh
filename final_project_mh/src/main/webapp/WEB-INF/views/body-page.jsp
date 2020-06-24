<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Body Page</title>
<style>
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

</head>

<body>

	<section class="header">
		<%@ include file="partials/header.jsp"%>
	</section>
	
	<script>
	function addPoints() {
	  alert("You just earned points.");
	}
	</script>

<main class="container">
<h1>Body</h1>


		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
			integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
			integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
			crossorigin="anonymous"></script>

		<script>
			$(document)
					.ready(
							function() {
								$('a[data-toggle="tab"]').on(
										'show.bs.tab',
										function(e) {
											localStorage.setItem('activeTab',
													$(e.target).attr('href'));
										});
								var activeTab = localStorage
										.getItem('activeTab');
								if (activeTab) {
									$('#myTab a[href="' + activeTab + '"]')
											.tab('show');
								}
							});
		</script>
		
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
		<p> Total Body Points: <c:out value = "${total}"/></p>
		<p> Points till the Next Rank: <c:out value = "${nextRank}"/></p>
		</div>
		</c:if>
<br>
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item"><a class="nav-link" id="exercise-tab"
				data-toggle="tab" href="#exercise" role="tab"
				aria-controls="exercise" aria-selected="true">Exercise</a></li>
			<li class="nav-item"><a class="nav-link" id="food-tab"
				data-toggle="tab" href="#food" role="tab" aria-controls="food"
				aria-selected="false">Nutrition</a></li>
			<li class="nav-item"><a class="nav-link" id="workout-tab"
				data-toggle="tab" href="#workout" role="tab" aria-controls="workout"
				aria-selected="false">Daily Workouts</a></li>
		</ul>

		<!-- Tab panes -->
	<div class="tab-content">
	  <div class="tab-pane active" id="exercise" role="tabpanel" aria-labelledby="exercise-tab">
	  
	  <h3>Enter an exercise such as "ran 3 miles in 30 mins" to calculate calories burned: </h3>
		<form action = "/body" name = "exerciseForm" method = "post">
		
		          <textarea name = "userInput"
		          placeholder="Your text here" rows = "3" cols = "80"></textarea>
						<br>
						<input type="submit"  value="Submit" class = "btn btn-info btn-sm">
		<!-- onclick="addPoints()" Don't think we want this in input above -->
		</form>

			<c:forEach var= "exercise" items="${exercises}">
			<div class="card w-100">
			 	<div class="card-body">
				<h5 class = "card-title"> ${exercise.name} </h5>
				<p class = "card-text" >You burned ${exercise.nf_calories} calories in ${exercise.duration_min} minutes </p>
				
				<form action="/save/exercises" method="post">	
				<input type="hidden" name ="nf_calories" value="${exercise.nf_calories}"/>
				<input type="hidden" name ="duration_min" value="${exercise.duration_min}"/>
				<input type="hidden" name ="name" value="${exercise.name}"/>
				<br>
				<button type="submit" onclick="addPoints()" class="btn btn-info btn-sm">I did this!</button>
				</form>
			</div>
		</div>
		</c:forEach>
		</div>


  
<div class="tab-pane" id="food" role="tabpanel" aria-labelledby="food-tab">
 <h3>Enter food items such as "2 eggs and a slice of white toast" to calculate the nutrition facts: </h3>  
  <form action = "/bodyfood" name = "foodForm" method = "post">
	
	          <textarea name = "userInput" placeholder="Your text here"
	           rows = "3" cols = "80"></textarea>
	          <br>
					<input type="submit" value="Submit" onclick="addPoints()" class = "btn btn-info btn-sm">
	
  </form>
		<c:forEach var= "foods" items="${food}">
		
		<section class="performance-facts">
		  <header class="performance-facts__header">
		    <h1 class="performance-facts__title">Nutrition Facts</h1>
		    <p class = "pnutrition"> ${foods.serving_qty} ${foods.serving_unit} ${foods.food_name} </p> 
		  </header>
		 
		 <table class="performance-facts__table">
		    <thead class = "thnutrition">
		      <tr class = "trnutrition">
		        <th colspan="3" class="small-info">
		          Amount Per Serving
		        </th>
		      </tr>
		    </thead>
		    <tbody class = "bodynutrition">
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">
		          <b class = "bodynutrition">Calories</b>
		         
		        </th>
		        <td class = "tdnutrition">
		          <b class = "bodynutrition">${foods.nf_calories}</b>
		        </td>
		      </tr>
		      <tr class="thick-row">
		      
		        <td colspan="2" class="small-info">
		          <b class = "bodynutrition" ></b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">
		          <b class = "bodynutrition">Total Fat</b>
		        </th>
		        <td class = "tdnutrition">
		          <b class = "bodynutrition"> ${foods.nf_total_fat}</b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <td class="blank-cell">
		        </td>
		        <th class = "thnutrition">
		         <b class = "bodynutrition">Saturated Fat</b>
		        </th>
		        <td class = "tdnutrition">
		          <b>${foods.nf_saturated_fat}</b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <td class="blank-cell">
		        </td>
		        <th class = "thnutrition">
		        </th>
		        <td class = "tdnutrition">
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">
		          <b class = "bodynutrition">Cholesterol</b>
		        </th>
		        <td class = "tdnutrition">
		          <b class = "bodynutrition">${foods.nf_cholesterol}</b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">
		          <b class = "bodynutrition">Sodium</b>
		     
		        </th>
		        <td class = "tdnutrition">
		          <b class = "bodynutrition">${foods.nf_sodium}</b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">
		          <b class = "bodynutrition">Total Carbohydrate</b>
		         
		        </th>
		        <td class = "tdnutrition">
		          <b class = "bodynutrition">${foods.nf_total_carbohydrate} </b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <td class="blank-cell">
		        </td>
		        <th class = "thnutrition">
		        <b class = "bodynutrition">Dietary Fiber</b>
		        </th>
		        <td class = "tdnutrition">
		          <b class = "bodynutrition">${foods.nf_dietary_fiber}</b>
		        </td>
		      </tr>
		      <tr class = "trnutrition">
		        <td class="blank-cell">
		        </td>
		        <th class = "thnutrition">
		        <b class = "bodynutrition">Sugars</b>
		          
		        </th>
		        <td class = "tdnutrition">
		        <b class = "bodynutrition">${foods.nf_sugars}</b>
		        </td>
		      </tr>
		      <tr class="thick-end">
		        <th colspan="2" class = "thnutrition">
		          <b class = "bodynutrition">Protein</b>
		        </th>
		        <td class = "tdnutrition">
		        <b class = "bodynutrition">${foods.nf_protein}</b>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		  <p class="small-info">* Percent Daily Values are based on a 2,000 calorie diet. Your daily values may be higher or lower depending on your calorie needs:</p>
		  <table class="performance-facts__table--small small-info">
		    <thead>
		      <tr class = "trnutrition">
		        <td colspan="2" class = "tdnutrition"></td>
		        <th class = "thnutrition">Calories:</th>
		        <th class = "thnutrition">2,000</th>
		        <th class = "thnutrition">2,500</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">Total Fat</th>
		        <td class = "tdnutrition">Less than</td>
		        <td class = "tdnutrition">65g</td>
		        <td class = "tdnutrition">80g</td>
		      </tr>
		      <tr class = "trnutrition">
		        <td class="blank-cell"></td>
		        <th class = "tdnutrition">Saturated Fat</th>
		        <td class = "tdnutrition">Less than</td>
		        <td class = "tdnutrition">20g</td>
		        <td class = "tdnutrition">25g</td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">Cholesterol</th>
		        <td class = "tdnutrition">Less than</td>
		        <td class = "tdnutrition">300mg</td>
		        <td class = "tdnutrition">300 mg</td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="2" class = "thnutrition">Sodium</th>
		        <td class = "tdnutrition">Less than</td>
		        <td class = "tdnutrition">2,400mg</td>
		        <td class = "tdnutrition">2,400mg</td>
		      </tr>
		      <tr class = "trnutrition">
		        <th colspan="3" class = "thnutrition">Total Carbohydrate</th>
		        <td class = "tdnutrition">300g</td>
		        <td class = "tdnutrition">375g</td>
		      </tr>
		      <tr class = "trnutrition">
		        <td class="blank-cell"></td>
		        <th colspan="2" class = "thnutrition">Dietary Fiber</th>
		        <td class = "tdnutrition">25g</td>
		        <td class = "tdnutrition">30g</td>
		      </tr>
		    </tbody>
		  </table>
		
		  <p class="small-info text-center">
		    Fat ${foods.nf_total_fat}
		    &bull;
		    Carbohydrate ${foods.nf_total_carbohydrate}
		    &bull;
		    Protein ${foods.nf_total_fat}
		  </p>
		</section>
		</c:forEach>
  </div>
			<div class="tab-pane" id="workout" role="tabpanel"
				aria-labelledby="workout-tab">
				<c:forEach var="result" items="${resultList}">
					<div class="card w-100">
						<div class="card-body">
							<h3 class="card-title">${result.name}</h3>
							<p class="card-body">${result.description}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
</body>
</html>