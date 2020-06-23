<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

<title>Challenge Form</title>

</head>

<body>
<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<main class = "container">

<c:if test = "${loggedin == true}">

<br>

<form action="/submit-challenge">
  <label for="category">Choose a Category:</label><br>
  <select name ="category" id ="category" required>
  <option value="mind">Mind</option>
  <option value="body">Body</option>
  <option value="soul">Soul</option>
  </select>
  <br>
  <br>
   <label for="name">Challenge Title:</label>
  <br>
  <input type="text" id="name" name="name" required>
  <br>
   <label for="description">Description:</label><br>
  <input type="text" id="description" name="description" required>
  <br>
   <label for="points_req">Points Rewarded:</label><br>
  <input type="text" id="points_req" name="points_req" required>
  <br>
   <label for="prize_url">Prize file</label><br>
  <input type="text" id="prize_url" name="prize_url" required>
  <br>
  <br>
  <input type="submit" value="Submit">
  
</form>
 </c:if>
 <br>
 <c:if test = "${loggedin != true}">
 <h1>Nothing to see here.</h1>
 </c:if> 
 
 </main>

 
</body>
</html>