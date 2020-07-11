package dao;

import dao.mysql.MysqlDAOFactory;

public class DAOFactoryHolder {
    public static final DAOFactory factory = new MysqlDAOFactory();

    public static DAOFactory getDAOFactory(){
        return factory;
    }
}
