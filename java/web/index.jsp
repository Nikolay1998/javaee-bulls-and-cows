<%@ page import="Model.Client" %><%--
  Created by IntelliJ IDEA.
  User: Николай
  Date: 06.07.2020
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Client client = (Client) session.getAttribute("client");
    if (client == null){
        response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
    <title>Быки и Коровы</title>
</head>
<body>
$END$
</body>
</html>
