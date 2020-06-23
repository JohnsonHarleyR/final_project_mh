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
<section id="thread">
	<h2>Leave a comment</h2>
	<form action="comment" method="post">
	<textarea name="comment" rows="5" cols="50" maxlength="1000" 
	 required></textarea>
	
	<br>
	<input type="hidden" name="profileId" value="${profileuser.id}"/>
	<button class="btn btn-info" type="submit">Add Comment</button>
	</form>
	<br>
</section>


</body>
</html>