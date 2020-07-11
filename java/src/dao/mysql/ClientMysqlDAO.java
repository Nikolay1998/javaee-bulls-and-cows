package dao.mysql;

import dao.AppDataSource;
import dao.ClientDAO;
import dao.IdGenerator;
import dao.ResultSetHandler;
import model.Client;

import java.sql.JDBCType;
import java.sql.ResultSet;

public class ClientMysqlDAO implements ClientDAO {
    private static final String CREATE_CLIENT_QUERY =
            "insert into client(id, name, login, password, rating) values (?, ?, ?, ?, ?)";
    private static final String GET_CLIENT_BY_LOGIN =
            "select id, name, login, rating from Client where login = ?";

    @Override
    public Client createClient(Client client) {
        int createdClientCount = AppDataSource.executeDMLStatement(
                CREATE_CLIENT_QUERY,
                new Object[][]{
                        {JDBCType.INTEGER, IdGenerator.getIdGenerator().getNewId()},
                        {JDBCType.VARCHAR, client.getName()},
                        {JDBCType.VARCHAR, client.getLogin()},
                        {JDBCType.VARCHAR, client.getPassword()},
                        {JDBCType.DECIMAL, null}
                }
        );
        if (createdClientCount == 0) {
            System.out.println("Some error during client registration: "+client.toString());
            return null;
        } else {
            return client;
        }
    }

    @Override
    public Client getClientByLogin(String login) {
        return (Client) AppDataSource.executePreparedStatement(
                GET_CLIENT_BY_LOGIN,
                new Object[][]{{JDBCType.VARCHAR, login}},
                new ResultSetHandler() {
                    @Override
                    public Object handle(ResultSet rs) throws Exception {
                        if (!rs.next()) {
                            return null;
                        }
                        return new Client(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getDouble("rating"));
                    }
                }
        );
    }
}
