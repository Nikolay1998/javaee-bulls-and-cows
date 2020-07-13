package servlets;

import dao.ClientDAO;
import dao.DAOFactoryHolder;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();
        if (clientDAO.getClientByLogin(req.getParameter("login")) != null) {
            String msg = "Login already in use";
            resp.sendRedirect("signin.jsp?msg=" + msg);
        } else {
            Client client = new Client(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), null);
            Client createdClient = clientDAO.createClient(client);
            if (createdClient != null) {
                resp.getWriter().write("Registration complete!");
                resp.sendRedirect("login?Action=Login&login=" + req.getParameter("login") + "&password=" + req.getParameter("password"));
            }
        }
    }
}