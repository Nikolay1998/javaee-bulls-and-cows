package dao.mysql;

import dao.ClientDAO;
import dao.DAOFactory;
import dao.mysql.ClientMysqlDAO;

public class MysqlDAOFactory implements DAOFactory {
    public ClientMysqlDAO clientDAO = new ClientMysqlDAO();

    @Override
    public ClientDAO getClientDAO() {
        return clientDAO;
    }
}
