<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 9/24/2020
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
login goes here
<form  method="post" action="${pageContext.request.contextPath}/library/login" >
    <div class="input-group">
        <input type="text"   name="login">
    </div>
    <div class="input-group">
        <input type="password"   name="password">
    </div>
<button  type="submit">login</button>
</form>
</body>
</html>
