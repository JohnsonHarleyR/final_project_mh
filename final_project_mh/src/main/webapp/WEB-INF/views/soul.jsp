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
<title>Soul Page</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>



<!-- MainBody -->
<main class="container">

<!-- Let's try kittens - kitten api -->
<img style="float: right;" src="http://placekitten.com/${wi}/${he}" alt="Place Kitten" />
<br>

<h1>Soul</h1>

<!-- If user is logged in, show option to save -->
<c:choose>
	<c:when test="${!loggedin}">
		${affirmation}
	</c:when>
	<c:when test="${exists}">
		${affirmation}
	</c:when>
	<c:otherwise>
		<form action="/save/affirmation" method="post">
			${affirmation}
			<br>
			<input type="hidden" name="affirmation" 
			value="${affirmation}"/>
			<button type="submit">Save</button>
		</form>
	</c:otherwise>
</c:choose>

<br>
<br>

<!-- Do it with the quote nows -->
<!-- If user is logged in, show option to save -->
<c:choose>
	<c:when test="${loggedin == false}">
		${quote.quote}
		${quote.author}
	</c:when>
	<c:when test="${qexists == true}">
		${quote.quote}
		${quote.author}
	</c:when>
	<c:otherwise>
		<form action="/save/affirmation" method="post">
			${quote.quote}
			${quote.author}
			<br>
			<input type="hidden" name="affirmation" 
			value="${quotestring}"/>
			<button type="submit">Save</button>
		</form>
	</c:otherwise>
</c:choose>

<div id="player"></div>

    <script>
      // 2. This code loads the IFrame Player API code asynchronously.
      var tag = document.createElement('script');

      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      // 3. This function creates an <iframe> (and YouTube player)
      //    after the API code downloads.
      var player;
      function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
          height: '390',
          width: '640',
          videoId: '${videoId}',
          events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
          }
        });
      }

      // 4. The API will call this function when the video player is ready.
      function onPlayerReady(event) {
        event.target.playVideo();
      }

      // 5. The API calls this function when the player's state changes.
      //    The function indicates that when playing a video (state=1),
      //    the player should play for six seconds and then stop.
      var done = false;
      function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
          setTimeout(stopVideo, 6000);
          done = true;
        }
      }
      function stopVideo() {
        player.stopVideo();
      }
    </script>

</main>

</body>
</html>