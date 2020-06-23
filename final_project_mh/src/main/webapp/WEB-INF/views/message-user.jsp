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
<title>Conversation with ${friend.username}</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main style="text-align:center;margin:auto;" class="container">
<h1>Conversation with ${friend.username}</h1>

<!-- Eventually only show last few messages, add next page -->

<!-- if there are messages, show them. Otherwise say there are no messages yet -->
<c:choose>
	<c:when test="${aremessages}">
	
			
		<c:forEach var="message" items="${convo}" begin="${begin}" end="${end}">
			
			
				
			<c:choose>
				<c:when test="${message.senderId == user.id }">
				<table style="margin:auto;" class="table" id="messagewidth">
				<tr>
					<td style="vertical-align: middle;text-align:right;">
						${user.username}<br>
						<sup><a href="/profile?id=${user.id}">See profile</a></sup>
					</td>
					<td width="75%">
									<div class="modal-content">
							<div class="modal-body" style="font-size:large;">
								<sup><i>${message.cleanDatetime}</i></sup>
								<br>
								${message.message}
								<br>
							</div>
						</div>
					</td>
				</tr>
				</table>
				</c:when>
				<c:otherwise>
				<table class="table" style="margin:auto;" id="messagewidth">
				<tr>
					<td width="75%">
						<div class="modal-content">
							<div class="modal-body" style="font-size:large;">
								<sup><i>${message.cleanDatetime}</i></sup>
								<br>
								${message.message}
							</div>
						</div>
					</td>
					<td style="text-align:left;vertical-align: middle;">
						${friend.username}<br>
					<sup><a href="/profile?id=${friend.id}">See profile</a></sup>
					</td>
				</tr>
				</table>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${length > 5 && begin != 0 }">
			<a style="text-align:left;" href="/message?id=${friend.id}&begin=${begin - 5}">
			See more messages</a>
			<br>
		</c:if>
	</c:when>
	<c:otherwise>
		<p>
		<i style="font-size:larger;">There are no messages yet.</i>
		</p>
	</c:otherwise>
</c:choose>


<!-- if they are friends, allow to send message -->
<c:if test="${arefriends}">
	<br>
	<h2>Send Message</h2>
	<form action="/message/send" method="post">
	<textarea name="message" rows="5" class="messagewidth" maxlength="1000" 
	placeholder="What do you want to say?" required></textarea>
	
	<br>
	<input type="hidden" name="user" value="${user.id}"/>
	<input type="hidden" name="friend" value="${friend.id}"/>
	<button style="width:20%;margin:left;" class="btn btn-info btn-lg" type="submit">
	Send Message</button>
	<br>
	<br>
	</form>
	
</c:if>

</main>

</body>
</html>