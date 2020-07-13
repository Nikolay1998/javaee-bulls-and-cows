package servlets;

import dao.ClientDAO;
import dao.DAOFactoryHolder;
import model.Client;
import service.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "GameServlet", urlPatterns = "/game")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String reqNum = (String) session.getAttribute("hiddenNumbers");
        if (reqNum == null) {
            reqNum = GameService.createReqNum();
            session.setAttribute("hiddenNumbers", reqNum);
            session.setAttribute("attempts", 0);
            StringBuilder log = new StringBuilder("Game started");
            session.setAttribute("log", log);

            resp.sendRedirect("game.jsp?");
        } else {
            Client client = (Client) session.getAttribute("client");
            Integer attempts = (Integer) session.getAttribute("attempts");
            attempts++;
            String answer = req.getParameter("answer");
            Integer[] result = GameService.compare(reqNum, answer);
            if (result[0] == 4) {
                session.removeAttribute("hiddenNumbers");
                session.removeAttribute("log");
                ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();
                Double newRating = GameService.calcNewRating(client.getRating(), client.getGames(), attempts);
                client.setGames(client.getGames() + 1);
                client.setRating(newRating);
                Client updatedClient = clientDAO.updateClient(client);
                if (updatedClient != null) {
                    resp.getWriter().write("You win! Your rating now = " + client.getRating());
                    resp.sendRedirect("index.jsp");
                }
            } else {
                session.setAttribute("attempts", attempts);
                StringBuilder log = (StringBuilder) session.getAttribute("log");
                log.append(System.getProperty("line.separator") + client.getName() + ": " + answer + System.getProperty("line.separator") + "Server:" + result[0] + "B" + result[1] + "C");
                session.setAttribute("log", log);
                resp.sendRedirect("game.jsp");
            }
        }
    }
}
