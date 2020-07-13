package dao.mysql;

import dao.AppDataSource;
import dao.ClientDAO;
import dao.IdGenerator;
import dao.ResultSetHandler;
import model.Client;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ClientMysqlDAO implements ClientDAO {
    private static final String CREATE_CLIENT_QUERY =
            "insert into client(id, name, login, password, rating, games) values (?, ?, ?, ?, ?, ?)";
    private static final String GET_CLIENT_BY_LOGIN =
            "select id, name, login, rating, games from client where login = ?";
    private static final String GET_CLIENT_BY_LOGIN_AND_PASSWORD =
            "select id, name, login, rating, games from client where login = ? and password = ?";
    private static final String UPDATE_RATING = "update client set rating = ? where id = ?";
    private static final String UPDATE_CLIENT_QUERY =
            "update client set name = ?, login = ?, rating = ?, games = ? where id = ?";
    private static final String GET_CLIENTS_WITH_RATING = "select * from client where rating is not null";

    @Override
    public Client createClient(Client client) {
        int createdClientCount = AppDataSource.executeDMLStatement(
                CREATE_CLIENT_QUERY,
                new Object[][]{
                        {JDBCType.INTEGER, IdGenerator.getIdGenerator().getNewId()},
                        {JDBCType.VARCHAR, client.getName()},
                        {JDBCType.VARCHAR, client.getLogin()},
                        {JDBCType.VARCHAR, client.getPassword()},
                        {JDBCType.DECIMAL, null},
                        {JDBCType.INTEGER, 0},
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
    public Client getClient(String login, String password) {
        return (Client) AppDataSource.executePreparedStatement(
                GET_CLIENT_BY_LOGIN_AND_PASSWORD,
                new Object[][]{{JDBCType.VARCHAR, login}, {JDBCType.VARCHAR, password}},
                new ResultSetHandler() {
                    @Override
                    public Object handle(ResultSet rs) throws Exception {
                        if (!rs.next()) {
                            return null;
                        }
                        return new Client(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getDouble("rating"),
                                rs.getInt("games"));
                    }
                }
        );
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
                                rs.getDouble("rating"),
                                rs.getInt("games"));
                    }
                }
        );
    }

    @Override
    public void updateRating(Double newRating, Integer id) {
        System.out.println("new rating = " + newRating);
        AppDataSource.executeDMLStatement(
                UPDATE_RATING,
                new Object[][]{
                        {JDBCType.DECIMAL, newRating},
                        {JDBCType.INTEGER, id}
                }
        );
    }

    @Override
    public Client updateClient(Client client) {
        int updatedClientCount = AppDataSource.executeDMLStatement(
                UPDATE_CLIENT_QUERY,
                new Object[][]{
                        {JDBCType.VARCHAR, client.getName()},
                        {JDBCType.VARCHAR, client.getLogin()},
                        {JDBCType.DECIMAL, client.getRating()},
                        {JDBCType.INTEGER, client.getGames()},
                        {JDBCType.INTEGER, client.getId()}
                }
        );
        if (updatedClientCount == 0) {
            System.out.println("Some error during client upgrades: "+client.toString());
            return null;
        } else {
            return client;
        }
    }

    @Override
    public List<Client> getClientsWithRating() {
        return (List<Client>) AppDataSource.executePreparedStatement(
                GET_CLIENTS_WITH_RATING,
                null,
                new ResultSetHandler() {
                    @Override
                    public Object handle(ResultSet rs) throws Exception {
                        List<Client> clients = new ArrayList<>();
                        while (rs.next()) {
                            Client client = new Client(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("login"),
                                    rs.getDouble("rating"));
                            clients.add(client);
                        }
                        return clients;
                    }
                });
    }
}
