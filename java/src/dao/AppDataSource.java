package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class AppDataSource {
    private final static String jndiDataSourceName = "java:comp/env/jdbc/opencode";
    private final static AppDataSource instance = new AppDataSource();
    private DataSource dataSource;

    private AppDataSource(){
        dataSource = initDataSource();
    }

    private DataSource initDataSource() {
        Context initContext = null;
        try {
            initContext = new InitialContext();
            return (DataSource) initContext.lookup(jndiDataSourceName);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error during attempt to get DataSource from JNDI: " + e.getMessage());
        }
    }

    public static DataSource getDataSource() {
        return instance.dataSource;
    }

    public static Object executePreparedStatement(String query, Object[][] params, ResultSetHandler handler){
        try(Connection con = instance.dataSource.getConnection()) {
            PreparedStatement statement = createStatement(con, query, params);
            ResultSet rs = statement.executeQuery();
            return handler.handle(rs);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during attempt to execute database query: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException(ex);
        }
    }

    public static int executeDMLStatement(String sqlQuery, Object[][] params) {
        try (Connection con = instance.dataSource.getConnection();
             PreparedStatement statement = createStatement(con, sqlQuery, params)) {
            return statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during attempt to execute database query: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException(ex);
        }
    }

    public static PreparedStatement createStatement(Connection con, String query, Object[][] params) throws SQLException {
        PreparedStatement statement = con.prepareStatement(query);
        if(params != null){
            int i = 0;
            for (Object[] parameter : params) {
                if(JDBCType.INTEGER.equals(parameter[0]) ){
                    if (parameter[1] == null) {
                        statement.setNull(++i, Types.INTEGER);
                    } else {
                        statement.setInt(++i, (Integer) parameter[1]);
                    }
                } else if (JDBCType.VARCHAR.equals(parameter[0]) || JDBCType.DATE.equals(parameter[0])) {
                    statement.setString(++i, (String) parameter[1]);
                } else if (JDBCType.DECIMAL.equals(parameter[0])) {
                    statement.setNull(++i, Types.DECIMAL);
                }
            }
        }
    return statement;
    }

}
