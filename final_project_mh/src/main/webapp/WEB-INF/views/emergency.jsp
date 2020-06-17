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
<title>Emergency Resources</title>
</head>
<body>

<!-- Header -->
<section class="header">
	<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

<p>
<h2 style="color: #FF0000">Need Help?</h2>
These services are available 24/7 and free!
</p>

<p>
<b>National Suicide Prevention Lifeline</b><br>
<b style="font-size: 25px">1-800-273-8255</b>
</p>

<p>
<b>National Domestic Violence Hotline</b><br>
<b style="font-size: 25px">1-800-799-SAFE (7233)</b>
</p>

<p>
<b>National Sexual Assault Hotline</b><br>
<b style="font-size: 25px">1-800-656-HOPE (4673)</b>
</p>


</main>

</body>
</html>