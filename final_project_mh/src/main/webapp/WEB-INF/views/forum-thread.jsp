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
		<a href="/forum/discussion">Back to *Discussion Title*</a>
		<article class="card" id="posts" style="width: 50rem;">
			<a href="/forum/discussion">Back to *Discussion Title*</a>
			<section class="card-body">
				
	
				<table  id="post-table" class="table">
					
					<tbody>
					
						<th>
							*Thread title*
						</th>
						<!-- For each loop here -->
						
						<!--there's the author column on the left, then the message
						column will have the main elements -->
						<tr>
							<td>
								*author info*<br>
								*profile pic later*<br>
								<a href="">Username</a>
							</td>
							
							<td>
								<sup>*Date and time of post*</sup>
								<br>
								*user's message post text goes here*
								<br>
								<!-- only if user is logged in 
								& login user matches post author -->
								<a href="">Edit </a>
								<a href="">Delete </a> 
								
							</td>
						</tr>
						<!-- end for each loop -->
						
						
						<!-- Other ideas for later: allow reply where it tags
						a user. Allow signature on bottom. -->
						
						
						<!-- repeat here is temporary, 
						only for setting up -->
						<tr>
							<td>
								*author info*<br>
								*profile pic later*<br>
								<a href="">Username</a>
							</td>
							
							<td>
								<sup>*Date and time of post*</sup>
								<br>
								*user's message post text goes here*
								<br>
								<!-- only if user is logged in 
								& login user matches post author -->
								<a href="">Edit </a>
								<a href="">Delete </a> 
								
							</td>
						</tr>
						<tr>
							<td>
								*author info*<br>
								*profile pic later*<br>
								<a href="">Username</a>
							</td>
							
							<td>
								<sup>*Date and time of post*</sup>
								<br>
								*user's message post text goes here*
								<br>
								<!-- only if user is logged in 
								& login user matches post author -->
								<a href="">Edit </a>
								<a href="">Delete </a> 
								
							</td>
						</tr>
						
					
					</tbody>
			
			
				</table>
				
			</section>
		
		
		</article>
	</div>
	
</main>

</body>
</html>