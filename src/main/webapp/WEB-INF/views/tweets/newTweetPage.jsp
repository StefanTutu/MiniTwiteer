<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: New Tweet</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script>
    function functionTweet(){
    	var content = document.getElementById("tweet").value;
    	 var user = "<%=session.getAttribute("username")%>";
    	 var data = {"user":  user, "tweet" :  content};
    	 $.ajax({
    	  url: "http://localhost:8080/twitter/addTweet",
    	  type:"post",
    	         dataType : "json",
    	         async : true,
    	         data: JSON.stringify(data),
    	         contentType: "application/json; charset=utf-8",
    	  success: function(data) 
    	   {
    	   location.reload();

    	   },
    	   failure: function(data){
    	   }
    	  }); 
    }
  </script>
</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />
	<form:form method="POST" commandName="tweets" acction="tweetsPage">
		<div style="margin-left: 30px; margin-top: 30px;">
			<h1 style="color: grey;">Compose New Tweet</h1>
			<br />
			<table>
				<tr>
					<td><b>New Tweet:</b></td>
					<td><textarea id="tweet" name="tweet" maxlength="140" rows="3"
							cols="50" /></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><span style="font-size: 10pt; color: grey;">*
							Maximum 140 characters</span></td>
				</tr>
				<tr>
					<td><br />
					<input type="button" value="Tweet" style="margin-left: 10px;"
						onClick="functionTweet()"></td>
				</tr>
			</table>
		</div>

		<c:forEach var="tweetsList" items="${tweetByUser}">
			<div class="list_items">
				<p>
					<b>${tweetsList.user_username}:</b> ${tweetsList.tweet}
			</div>
		</c:forEach>


	</form:form>
</body>
</html>