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
<title>${discussion}</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

<div id="forum-discussion">

	<a href="/forum">Back to Main Forum</a>
	
	<article class="card" id="threads" style="width: 50rem;">
		
		<section class="card-header">
			<h1>${discussion.topic}</h1>
			
		</section>
		<section class="card-body">
			
			${discussion.description}
			<br>
			<br>
			<!-- If logged in, let them create new thread -->
			<c:choose>
			<c:when test="${loggedin && discussion.postType == 'regular'}">
				<a href="/thread/add?id=${discussion.id}">Create a New Thread</a>
			</c:when>
			<c:when test="${loggedin && user.status == 'admin'}">
				<a href="/thread/add?id=${discussion.id}">Create a New Thread</a>
			</c:when>
			</c:choose>
		
			<table  id="table2" class="table table-hover">
			  <thead>
			    <tr>
			      <th scope="col">Topic</th>
			      <th scope="col">Posts</th>
			      <th scope="col">Author</th>
			      <th scope="col">Latest Post</th>
			    </tr>
			  </thead>
			  
			  <tbody>
			  
			  	<!-- For Each loop here, connect to thread database -->
			  	<c:forEach var= "thread" items="${threads }">
			  	<c:if test="${thread.discussionId == discussion.id }">
			    <tr>
			      <td width="35%">
			      	<c:choose>
			      	<c:when test="${discussion.postType == 'announcement'}">
			      	<a href="/thread?id=${thread.id}"><b>Announcement: </b>
			      	<c:out value="${thread.threadTitle }"></c:out></a>
			      	</c:when>
			      	<c:otherwise>
			      	<a href="/thread?id=${thread.id}"><c:out value="${thread.threadTitle }"></c:out></a>
			      	</c:otherwise>
			      	</c:choose>
			      </td>
			      <td>${thread.numberOfPosts}</td>
			      <td><a href="/profile?id=${thread.userId}"><c:out value="${thread.username }" /></a></td>
			      
			      <c:forEach var = "post" items="${ posts}">
			      <c:if test="${post.id == thread.lastPostId}">
			      <td>
			      	<a href="thread?id=${thread.id}&d=${discussion.id}"></a>${post.abridgedMsg}<br>
			      	 <sup><a href="/profile?id=${thread.userId}"><c:out value="${post.username }" />
			      	 </a></sup><br><sup>${post.datetime}</sup>
			      </td>
			      </c:if>
			      </c:forEach>
			    </tr>
			    </c:if>
			    </c:forEach>
			    
			    <!-- for the sake of an example, take this out later -->
			    
			  </tbody>
			</table>
		
			
		</section>
	</article>
</div>



</main>

</body>
</html>