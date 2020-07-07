<%--
  Created by IntelliJ IDEA.
  User: Николай
  Date: 06.07.2020
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if ("Регистрация".equals(request.getParameter("Action"))) {
        response.sendRedirect("signin.jsp");
    }
%>
<html>
<head>
    <title>Вход</title>
</head>
<body>
Вход
<form name="form" action="login.jsp">
    <input type="submit" value="Регистрация" name="Action"/>
</form>
</body>
</html>
