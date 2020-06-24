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
<article class="card" id="welcome" style="width: 65rem;">
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
		      <td style='width:18%;'>
		      	<a href="/forum/discussion?id=${discussion.id}"><c:out value="${discussion.topic}"></c:out></a>
		      </td>
		      <td style='width:50%;'><c:out value="${discussion.description}"></c:out></td>
		      <td style='width:13%;'>${discussion.numberOfTopics }</td>
		      <td>
		      	<c:choose>
		      	<c:when test="${discussion.numberOfTopics == 0}">
		      	No threads.
		      	</c:when>
		      	<c:otherwise>
		      	<c:forEach var="post" items="${posts}">
		      	<c:if test="${discussion.lastTopicPostId == post.id}">
		      	<a href="/thread?id=${post.threadId}">${post.abridgedMsg}</a><br>
		      	 <sup>${post.datetime}</sup>
		      	 <sup><a href="/profile?id=${post.userId}">${post.username}</a><br></sup>
		      	 </c:if>
		      	 
		      	 </c:forEach>
		      	 </c:otherwise>
		      	 </c:choose>
		      </td>
		    </tr>
		    </c:if>
		    </c:forEach>
		    
		    <!-- if user is an admin, allow them to create a new discussion -->
		    
		  </tbody>
		</table>
		<c:if test="${ loggedin && user.status == 'admin' }" >
		    	<a href=""><button class="btn btn-info">Create Discussion</button></a>
		</c:if>
	
		
	</section>
</article>
</div>



<article class="card" id="general" style="width: 65rem;">
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
		      <td style='width:18%;'>
		      	<a href="/forum/discussion?id=${discussion.id}"><c:out value="${discussion.topic}"></c:out></a>
		      </td>
		      <td style='width:50%;'><c:out value="${discussion.description}"></c:out></td>
		      <td style='width:13%;'>${discussion.numberOfTopics }</td>
		      <td>
		      	<c:choose>
		      	<c:when test="${discussion.numberOfTopics == 0}">
		      	No threads.
		      	</c:when>
		      	<c:otherwise>
		      	<c:forEach var="post" items="${posts}">
		      	<c:if test="${discussion.lastTopicPostId == post.id}">
		      	<a href="/thread?id=${post.threadId}">${post.abridgedMsg}</a><br>
		      	<sup>${post.datetime}</sup>
		      	<sup><a href="/profile?id=${post.userId}">${post.username}</a></sup><br>
		      	 
		      	 </c:if>
		      	 
		      	 </c:forEach>
		      	 </c:otherwise>
		      	 </c:choose>
		      </td>
		    </tr>
		    </c:if>
		   </c:forEach>
		  
		  </tbody>
		</table>
		
		<c:if test="${ loggedin && user.status == 'admin' }" >
		    	<a href=""><button class="btn btn-info">Create Discussion</button></a>
		</c:if>
	</section>
	
</article>



<article class="card" id="mental-health" style="width: 65rem;">
	<section class="card-header">
		<h1>Mental Health</h1>
		
	</section>
	<section class="card-body">
	
		<table  id="table3" class="table table-borderless">
		
		<!-- Format the width of the columns somehow. -->
   		
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
		      <td style='width:18%;'>
		      	<a href="/forum/discussion?id=${discussion.id}"><c:out value="${discussion.topic}"></c:out></a>
		      </td>
		      <td style='width:50%;'><c:out value="${discussion.description}"></c:out></td>
		      <td style='width:13%;'>${discussion.numberOfTopics }</td>
		      <td>
		      	<c:choose>
		      	<c:when test="${discussion.numberOfTopics == 0}">
		      	No threads.
		      	</c:when>
		      	<c:otherwise>
		      	<c:forEach var="post" items="${posts}">
		      	<c:if test="${discussion.lastTopicPostId == post.id}">
		      	<sup><a href="/thread?id=${post.threadId}">${post.abridgedMsg}</a></sup><br>
		      	<sup>${post.datetime}</sup>
		      	 <sup><a href="/profile?id=${post.userId}">${post.username}</a></sup><br>
		      	 </c:if>
		      	 
		      	 </c:forEach>
		      	 </c:otherwise>
		      	 </c:choose>
		      </td>
		    </tr>
		    </c:if>
		   </c:forEach>
		  </tbody>
		</table>
		<c:if test="${ loggedin && user.status == 'admin' }" >
		    	<a href=""><button class="btn btn-info">Create Discussion</button></a>
		</c:if>
	</section>
</article>
		
		



</main>

</body>
</html>