import dao.AppDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/opencode?serverTimezone=Europe/Moscow&useSSL=false",
                "root", "password1998")){
            resp.getWriter().write("Connection success!");
            PreparedStatement statement = conn.prepareStatement("insert into client value (2, \"name\", \"logfin\", \"password\", 1)");
            statement.executeUpdate();
        } catch (SQLException throwables) {
            resp.getWriter().write("Error ..." + throwables);
        }

         */

        DataSource ds = AppDataSource.getDataSource();
        try {
            ds.getConnection();
            resp.getWriter().write("Cool");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("lkmdf");
        }
    }
}
