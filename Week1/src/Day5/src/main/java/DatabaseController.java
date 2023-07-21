import java.sql.*;

public class DatabaseController {
    private Connection connection;
    private Statement stmt;

    DatabaseController(Connection connection) throws SQLException {
        this.connection = connection;
        this.stmt = connection.createStatement();
    }

    public void executeCrud(String sql){
        try {
            stmt.executeUpdate(sql);
            System.out.println("Executed :"+sql);
        }catch (SQLException sqlException){
            System.out.println("Error occured while executing :"+sql);
        }
    }

    public void select(String sql){
        try {
            ResultSet result = stmt.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            int colCount = resultSetMetaData.getColumnCount();
            while (result.next()){
                for (int i = 1; i <=colCount ; i++) {
                    String name = resultSetMetaData.getColumnName(i);
                    Object o  = result.getObject(name);
                    System.out.print(name+" : ");
                    System.out.print(o);
                    System.out.println();
                }
                System.out.println("--------");

            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Error occured while executing :"+sql);
        }
    }

    public void close(){
        try {
            stmt.close();
            connection.close();
        }catch (SQLException sqlException){
            System.out.println("Error occured while closing");
        }
    }
}
