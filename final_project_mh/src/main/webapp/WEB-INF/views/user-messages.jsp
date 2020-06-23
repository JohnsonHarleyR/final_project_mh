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
<title>${user.username}'s Messages</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container" style="text-align:center;margin:auto;">

<h1>${user.username}'s Messages</h1>

<c:choose>
<c:when test="${length == 0}">
<i style="font-size:larger;">You have no messages yet.</i>
</c:when>

<c:otherwise>
<table style="text-align:left;" class="table table-striped">
  <thead>
    <tr>
      <th width="10%" scope="col"></th>
      <th width="45%" scope="col">Latest Message</th>
      <th width="20%" scope="col">Username</th>
      <th width="25%" scope="col">Date</th>
    </tr>
  </thead>
  <tbody>
  
  <c:forEach var="convo" items="${conversations}">
    <tr>
      <td>
      	<c:if test="${!convo.isRead}">
      		<b style="color:red;">new</b>
      	</c:if>
      </td>
      <td><a href="/message?id=${convo.friend.id}">${convo.abridgedMsg}</a></td>
      <td><a href="/profile?id={convo.friend.id}">${convo.friend.username}</a></td>
      <td>${convo.cleanDatetime}</td>
    </tr>
    </c:forEach>
    
    </tbody>
  </table>
</c:otherwise>
</c:choose>

</main>

</body>
</html>