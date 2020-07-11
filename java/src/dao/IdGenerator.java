package dao;

import java.sql.ResultSet;

public class IdGenerator {

    private static IdGenerator instance = new IdGenerator();
    private Integer lastGeneratedId;

    private IdGenerator(){
        System.out.println("IdGenerator creation");
        lastGeneratedId = (Integer) AppDataSource.executePreparedStatement(
                "select max(id) max from client",
                null,
                new ResultSetHandler() {
                    @Override
                    public Integer handle(ResultSet rs) throws Exception {
                        if (rs.next()) {
                            return rs.getInt("max");
                        } else {
                            throw new Exception("Can't find max id in client table");
                        }
                    }
                }
        );
    }

    public static IdGenerator getIdGenerator(){
        return instance;
    }


    public Integer getNewId(){
        return ++lastGeneratedId;
    }
}
