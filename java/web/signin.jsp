<%@ page import="dao.ClientDAO" %>
<%@ page import="dao.DAOFactoryHolder" %>
<%@ page import="model.Client" %><%--
  Created by IntelliJ IDEA.
  User: Николай
  Date: 06.07.2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String msg = (String) request.getParameter("msg");
%>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
Регистрация
<% if (msg != null) { %>
<font color="red"><br><%=msg%><br>
        <%}%>
    <form name="form" action="./registration" method="post">
        <table>
            <tr>
                <td>Имя</td>
                <td><input type="text" name="name" value="" size="30"/></td>
            </tr>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" value="" size="30"/></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="text" name="password" value="" size="30"/></td>
            </tr>
        </table>
        <input type="submit" value="Регистрация" name="Action"/>
    </form>
</body>
</html>
