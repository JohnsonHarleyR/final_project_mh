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
<title>*thread title* ${thread}</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

	<div id="thread">
		<a href="/forum/discussion?id=${discussion.id}">Back to <c:out value="${discussion.topic}" /></a>
		<article class="card" id="posts" style="width: 50rem;">
			<a href="/forum/discussion/add-post?id=${thread.id}">Add To Thread</a>
			<section class="card-body">
				
	
				<table  id="post-table" class="table">
					
					<tbody>
					
						<th>
							<c:out value="${thread.threadTitle}" />
						</th>
						<!-- For each loop here -->
						<c:forEach var="post" items="${posts}">
						<!--there's the author column on the left, then the message
						column will have the main elements -->
						<tr>
							<td>
								*author info*<br>
								*profile pic later*<br>
								<a href=""><c:out value="${post.username}"/></a>
							</td>
							
							<td>
								<sup>${post.datetime}</sup>
								<br>
								<c:out value="${post.message}"/>
								<br>
								<!-- only if user is logged in 
								& login user matches post author -->
								<c:if test="${loggedin}">
									<c:if test="${user.username == post.username}">
								<a href="/post/delete?id=${post.id }">Delete </a> 
									</c:if>
								</c:if>
							</td>
						</tr>
						<!-- end for each loop -->
						</c:forEach>
						
						<!-- Other ideas for later: allow reply where it tags
						a user. Allow signature on bottom. -->
						
						
						<!-- repeat here is temporary, 
						only for setting up -->
						
					
					</tbody>
			
			
				</table>
				
			</section>
		
		
		</article>
	</div>
	
</main>

</body>
</html>