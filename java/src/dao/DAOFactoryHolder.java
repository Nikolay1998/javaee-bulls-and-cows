package dao;

import dao.mysql.MysqlDAOFactory;

/**
 * This class encapsulates logic to choose implementation of DAOFactory.
 */
public class DAOFactoryHolder {
    public static final DAOFactory factory = new MysqlDAOFactory();

    public static DAOFactory getDAOFactory(){
        return factory;
    }
}
