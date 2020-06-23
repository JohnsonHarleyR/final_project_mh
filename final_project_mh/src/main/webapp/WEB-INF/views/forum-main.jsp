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
<title>Forum</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">


<div id="forum-main">
<article class="card" id="welcome" style="width: 50rem;">
	<section class="card-header">
		<h1>Welcome to the Forum</h1>
		
	</section>
	<section class="card-body">
	
		<table  id="table2" class="table table-borderless">
		  <thead>
		    <tr>
		      <th scope="col">Discussion</th>
		      <th scope="col">Description</th>
		      <th scope="col">Topics</th>
		      <th scope="col">Latest Post</th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  <c:forEach var="discussion" items="${discussions}">
		  <c:if test="${discussion.tag == 'welcome' }">
		    <tr>
		      <td>
		      	<a href="/forum/discussion?id=${discussion.id}"><c:out value="${discussion.topic}"></c:out></a>
		      </td>
		      <td><c:out value="${discussion.description}"></c:out></td>
		      <td>*number of posts*</td>
		      <td>
		      	*<a href="">post title</a>,<br>
		      	 <sup><a href="">author</a>, datetime*</sup>
		      </td>
		    </tr>
		    </c:if>
		    </c:forEach>
		    
		  </tbody>
		</table>
	
		
	</section>
</article>
</div>



<article class="card" id="general" style="width: 50rem;">
	<section class="card-header">
		<h1>General</h1>
	</section>
	
	<section class="card-body">
		<table  id="table2" class="table table-borderless">
		  <thead>
		    <tr>
		      <th scope="col">Discussion</th>
		      <th scope="col">Description</th>
		      <th scope="col">Topics</th>
		      <th scope="col">Latest Post</th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  <c:forEach var="discussion" items="${discussions}">
		  <c:if test="${discussion.tag == 'general' }">
		    <tr>
		      <td>
		      	<a href="">*discussion name*</a>
		      </td>
		      <td>*describe here*</td>
		      <td>*# of posts*</td>
		      <td>
		      	*<a href="">post title</a>,<br>
		      	 <sup><a href="">author</a>, datetime*</sup>
		      </td>
		    </tr>
		   </c:if>
		   </c:forEach>
		  
		  </tbody>
		</table>
	</section>
</article>



<article class="card" id="mental-health" style="width: 50rem;">
	<section class="card-header">
		<h1>Mental Health</h1>
		
	</section>
	<section class="card-body">
	
		<table  id="table3" class="table table-borderless">
		  <thead>
		    <tr>
		      <th scope="col">Discussion</th>
		      <th scope="col">Description</th>
		      <th scope="col">Topics</th>
		      <th scope="col">Latest Post</th>
		    </tr>
		  </thead>
		  
		  <tbody>
		  <c:forEach var="discussion" items="${discussions}">
		  <c:if test="${discussion.tag == 'mental health' }">
		    <tr>
		      <td>
		      	<a href="">*discussion name*</a>
		      </td>
		      <td>*describe here*</td>
		      <td>*# of posts*</td>
		      <td>
		      	*<a href="">post title</a>,<br>
		      	 <a href="">author</a>, datetime*
		      </td>
		    </tr>
		    </c:if>
		   </c:forEach>
		  </tbody>
		</table>
	
	</section>
</article>
		
		



</main>

</body>
</html>