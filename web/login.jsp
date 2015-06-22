<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 6/22/2015
  Time: 3:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="j_spring_security_check" method="post">
    <input type="text" name="username">
    <input type="text" name="password">
    <input type="submit">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

</body>
</html>
