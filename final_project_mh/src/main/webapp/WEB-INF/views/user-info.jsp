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
<title>User Information</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main  id="card" class="container">


<article class="card text-left" style="width: 27rem;">
<table>
<tr>
<th>

	<section class="card-header">
		<h1>User Information</h1>		
	</section>
</th>
</tr>
<tr>
<td>
<section class="card-body">
<p id="editmsg">
<i>${message}</i>
</p>

<p>
<img width="100px" style="border:1px solid black;" src="${user.avatar}">
</p>
<p>
<b>Username: </b> ${user.username}
</p>
<p>
<b>Email: </b> ${user.email}
</p>
<p>
<b>Password: </b> ${password}
</p>
<p>
<b>First name: </b> ${user.name}
</p>
<p>
<b>Current Weight: </b> ${userPreferences.userWeight}
</p>
<p>
<b>Music Preferences: </b> ${userPreferences.musicGenrePreferences}
</p>


<p id="sub">
<a href="/user/edit" id="btnedit"><button type="button" id="btnsub"
class="btn btn-info btn-lg">Edit Info</button></a>
</p>

</section>

</td>
</tr>

</table>
</article>



</main>

</body>
</html>