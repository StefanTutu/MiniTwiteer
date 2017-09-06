<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Twitter :: Follow and/or Unfollow</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
</head>
<body style="margin: 0px;">
  <jsp:include page="../_menu.jsp" />

  <div style="margin-left:30px; margin-top: 30px;">

    <h1 style="color: grey;">Follow and/or Unfollow Users</h1>

    <c:if test="${not empty notice}">
      ${notice}
    </c:if>

    <br/>

    <c:forEach var="user" items="${userStatusMap}" varStatus="status">
      <p>
        <c:choose>
          <c:when test="${user.status == false}">
            <b>User: </b>${user.username}
            <input type="button" value="Follow" style="margin-left: 10px;" onClick="follow('${user.username}')">
          </c:when>

          <c:otherwise>
            <b>User: </b>${user.username}
            <input type="button" value="Unfollow" style="margin-left: 10px;" onClick="unfollow('${user.username}')">
          </c:otherwise>
        </c:choose>
        <br/>
      </p>
    </c:forEach>
  </div>

  <script>
    function follow(username){
      $.post("${pageContext.request.contextPath}/users/follow",{username:username,commandName:"followForm"}).always(function(data){
        alert(data.message);
        location.reload();
      });
    }

    function unfollow(username){
      $.post("${pageContext.request.contextPath}/users/unfollow",{username:username,commandName:"unfollowForm"}).always(function(data){
        alert(data.message);
        location.reload();
      });
    }
  </script>
</body>
</html>