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
<html>

<%!
    public static final ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();
%>

<%

    boolean loginAlreadyIsInUse = false;
    if ("Регистрация".equals(request.getParameter("Action"))) {
       // if (clientDAO.getClientByLogin(request.getParameter("login")) != null) {
       //     loginAlreadyIsInUse = true;
       // } else {
            Client client = new Client(request.getParameter("name"), request.getParameter("login"), request.getParameter("password"), null);
            Client createdClient = clientDAO.createClient(client);
            if (createdClient != null) {
                response.sendRedirect("login.jsp?Action=Login&login="+request.getParameter("login")+"&password="+request.getParameter("password"));
            }
       // }
    }
%>
<head>
    <title>Регистрация</title>
</head>
<body>
регистрация
    <form name="form" action="signin.jsp">
        <table>
            <tr>
                <td>Имя</td>
                <td><input type="text" name="name" value="" size="30" /></td>
            </tr>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" value="" size="30" /></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="text" name="password" value="" size="30" /></td>
            </tr>
        </table>
        <input type="submit" value="Регистрация" name="Action" />
    </form>
</body>
</html>
