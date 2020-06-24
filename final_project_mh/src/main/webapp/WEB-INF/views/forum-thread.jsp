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
		<article class="card" id="posts" style="width: 60rem;">
			<section class="card-header">
			</section>
			<section class="card-body">
				
	
				<table  id="post-table" class="table">
				
					<thead>
					<tr>
					<th width="20%">
					<a href="#post">
					<button class="btn btn-info">Add to Thread</button></a>
					</th>
					
					<th style="text-align:left;">
					<c:out value="${thread.threadTitle}" />
					</th>
					</tr>
					</thead>
					
					<tbody>
					
						
						<!-- For each loop here -->
						<c:set var="count" value="1"/>
						<c:forEach var="post" items="${posts}">
						<!--there's the author column on the left, then the message
						column will have the main elements -->
						<tr>
							<td>
								
								*profile pic later*<br>
								<a href="/profile?id=${post.userId}"><c:out value="${post.username}"/></a>
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
										<c:choose>
											<c:when test="${count == 1}">
												<br>
												<sub><a href="/thread/delete?id=${post.threadId}&d=${discussion.id}">Delete Thread</a>
												</sub>
											</c:when>
											<c:otherwise>
												<br>
												<sub>
												<a href="/post/delete?id=${post.id }">Delete</a> 
												</sub>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:if>
							</td>
						</tr>
						<c:set var="count" value="${count + 1}"/>
						<!-- end for each loop -->
						</c:forEach>
						
						<!-- Other ideas for later: allow reply where it tags
						a user. Allow signature on bottom. -->
						
						
						<tr>
						<td>
						<td>
						</td>
						<td>
						</td>
						</tr>
						
					</tbody>
				</table>
				
			</section>	
			<c:if test="${num > 4}">
		<a href="#thread" style="text-align:center;font-size:larger;">Back to Top</a>
		</c:if>
		</article>
		
		
		<article id="post" class="card-header" style="width: 60rem;">
		<section class="card-header" style="display:flex;justify-content:center;">
		
		
		<!-- Check if user is logged in. 
	If they are, let them create post.
	Otherwise, ask them to sign in.-->
	<div style='display:flex;justify-content:center;'>
	
			<c:choose>
			
				<c:when test="${loggedin}">
			
					<form action="/post/add/submit" method="post">
					<br>
					<br>
					<label style="color:black;font-size:larger;">What do you want to say?</label><br>
					<textarea name="comment" rows="7" cols="80" maxlength="1000" 
					placeholder="Write post here." required></textarea>
					<br>
					<input type="hidden" name="threadId" value="${thread.id }">
					<button class="btn btn-info" type="submit">Post</button>
					<a href="/forum/thread?id=${thread.id}" 
					style="color: #ffffff;" class="btn btn-info">Cancel</a>
					</form>
				
				</c:when>
				<c:otherwise>
					Sorry, you must be logged in to reply on a thread.
					<br>
					<a href="/login">Sign In</a>
				</c:otherwise>
			</c:choose>
			</div>
			</section>
		</article>
		
		
		
		
	</div>
	
	
	
</main>

</body>
</html>