<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action="/submit-challenge">
  <label for="category">Category</label><br>
  <input type="text" id="category" name="category">
  <br>
   <label for="name">Challenge Title:</label><br>
  <input type="text" id="name" name="name">
  <br>
   <label for="description">Description:</label><br>
  <input type="text" id="description" name="description">
  <br>
   <label for="points_req">Points Rewarded:</label><br>
  <input type="text" id="points_req" name="points_req">
  <br>
   <label for="prize_url">Prize file</label><br>
  <input type="text" id="prize_url" name="prize_url">
  <br>
  <input type="submit" value="Submit">
</form>
</body>
</html>