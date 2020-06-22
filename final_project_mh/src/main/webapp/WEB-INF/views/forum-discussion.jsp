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
			<h1>*Discussion Name* ${discussion}</h1>
			
		</section>
		<section class="card-body">
		
			<table  id="table2" class="table table-hover">
			  <thead>
			    <tr>
			      <th scope="col">Topic</th>
			      <th scope="col">Replies</th>
			      <th scope="col">Author</th>
			      <th scope="col">Latest Post</th>
			    </tr>
			  </thead>
			  
			  <tbody>
			  
			  	<!-- For Each loop here, connect to thread database -->
			    <tr>
			      <td>
			      	<a href="">*thread topic title*</a>
			      </td>
			      <td>*# of replies*</td>
			      <td>*number of posts*</td>
			      <td>
			      	*<a href="">thread/topic author</a>,<br>
			      	 <sup><a href="">post author</a>, datetime*</sup>
			      </td>
			    </tr>
			    
			    
			    <!-- for the sake of an example, take this out later -->
			    <tr>
			      <td>
			      	<a href="">*thread topic title*</a>
			      </td>
			      <td>*# of replies*</td>
			      <td>*number of posts*</td>
			      <td>
			      	*<a href="">thread/topic author</a>,<br>
			      	 <sup><a href="">post author</a>, datetime*</sup>
			      </td>
			    </tr>
			    <tr>
			      <td>
			      	<a href="">*thread topic title*</a>
			      </td>
			      <td>*# of replies*</td>
			      <td>*number of posts*</td>
			      <td>
			      	*<a href="">thread/topic author</a>,<br>
			      	 <sup><a href="">post author</a>, datetime*</sup>
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