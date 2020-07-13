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
    String msg = (String) request.getParameter("msg");
%>
<html>
<head>
    <title>Вход</title>
</head>
<body>
Вход
<% if (msg != null) { %>
<font color="red"><br><%=msg%><br>
        <%}%>
<form name="form" action="./login" method="get">
    <input type="text" name="login" onFocus="if(this.value=='Логин')this.value=''" onblur="if(this.value=='')this.value='Логин'"
           value='Логин'  class='text_input' />
    <input type="text" name="password" onFocus="if(this.value=='Пароль')this.value=''" onblur="if(this.value=='')this.value='Пароль'"
           value='Пароль' class='text_input' />
    <input type="submit" value="Login" name="Action"/>
</form>
    <a href="signin.jsp">Регистрация</a>
</body>
</html>
