package servlets;

import dao.ClientDAO;
import dao.DAOFactoryHolder;
import model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();
        Client client = clientDAO.getClient(req.getParameter("login"), req.getParameter("password"));
        if (client != null) {
            HttpSession session = req.getSession();
            session.setAttribute("client", client);
            resp.sendRedirect("game");
        } else {
            String msg = "Wrong password and login";
            resp.sendRedirect("login.jsp?msg=" + msg);

        }
    }
}
