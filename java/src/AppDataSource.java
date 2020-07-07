import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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

}
