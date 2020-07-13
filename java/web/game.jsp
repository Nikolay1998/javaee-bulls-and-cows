<%--
  Created by IntelliJ IDEA.
  User: Николай
  Date: 11.07.2020
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String hiddenNumbers = (String) session.getAttribute("hiddenNumbers");
    StringBuilder log = (StringBuilder) session.getAttribute("log");
%>
<html>
<head>
    <title>Game</title>
</head>
<body>
<%=hiddenNumbers%>

<form name="form" action="./game" method="get">
    <input type="text" name="answer" value='Ответ' class='text_input'/>
    <input type="submit" value="Ответить" name="Action"/>
</form>
<% if (log != null) { %>
<pre><br><%=log%><br></pre>
<%}%>
</body>
</html>
