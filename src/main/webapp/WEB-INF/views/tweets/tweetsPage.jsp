<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: Tweets</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">

		<c:choose>
			<c:when test="${not empty username}">
				<h1 style="color: grey;">${username}Tweets</h1>
			</c:when>

			<c:otherwise>
				<h1 style="color: grey;">All Tweets</h1>
			</c:otherwise>
		</c:choose>

		<h3>Search tweets by user and keyword:</h3>

		<form id="search_form">
			<b>User:</b> <input type="text" id="username" value="${username}"
				placeholder="Insert username" /> <b style="margin-left: 25px;">Keyword:</b>
			<input type="text" id="search" value="${search}"
				placeholder="Insert keyword" /> <input type="submit" value="Search"
				style="margin-left: 25px;" />
		</form>


		<c:forEach var="tweetsList" items="${tweets}">
			<div class="list_items">
				<p>
					<b>${tweetsList.user_username}:</b> ${tweetsList.tweet}
			</div>
		</c:forEach>


		<script type="text/javascript">
    $('#search_form').submit(function (e) {
      // Stop from submitting
      e.preventDefault();

      var username = $('#username').val();
      var search = $('#search').val();

      if(username == ""){
          window.location = "${pageContext.request.contextPath}/tweets/formatted?search=" + search;
      }
      else {
          window.location = "${pageContext.request.contextPath}/tweets/" + username + "/formatted?search=" + search;
      }
    });
  </script>
</body>
</html>