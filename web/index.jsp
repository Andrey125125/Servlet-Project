<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 9/24/2020
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<html>
<head>
    <title>Library</title>
</head>
<body>
 <h1>Hello there!</h1>
 <br>
 <div align = "center">
     <a  href="${pageContext.request.contextPath}/login.jsp" >login</a>
     <a  href="${pageContext.request.contextPath}/register.jsp">register</a>
     <br><br>
 </div>


</body>
</html>
