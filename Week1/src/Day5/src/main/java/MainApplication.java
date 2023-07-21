import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName ("org.h2.Driver");

        String jdbcURL = "jdbc:h2:~/test";
        String username = "sa";
        String password = "";

        Connection conn = DriverManager.getConnection(jdbcURL, username, password);
        System.out.println("Connected to H2");
        DatabaseController databaseController = new DatabaseController(conn);


        databaseController.executeCrud("DROP TABLE PERSON");
        String sqlCreateTable =  "CREATE TABLE   PERSON " +
                "(id INTEGER not NULL, " +
                " firstname VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " phonenumber INTEGER, " +
                " email VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        databaseController.executeCrud(sqlCreateTable);
        databaseController.executeCrud("INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME,PHONENUMBER,EMAIL) VALUES (1,'numan','kafadar',123,'numan@gmail.com')");
        databaseController.executeCrud("INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME,PHONENUMBER,EMAIL) VALUES (2,'Itadori','Yuuji',456,'yuuji@gmail.com')");
        databaseController.select("SELECT * FROM PERSON");
        databaseController.executeCrud("INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME,PHONENUMBER,EMAIL) VALUES (3,'Kgusaki','Nobara',789,'nobara@gmail.com')");
        databaseController.executeCrud("INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME,PHONENUMBER,EMAIL) VALUES (4,'Fushiguro','Megumi',421,'megumi@gmail.com')");
        databaseController.select("SELECT FIRSTNAME,LASTNAME FROM PERSON");
        databaseController.executeCrud("UPDATE PERSON SET FIRSTNAME='Gojo', LASTNAME='Satoru', email='satoru@gmail.com' WHERE ID = 1");
        databaseController.select("SELECT * FROM PERSON");
        databaseController.executeCrud("DELETE FROM PERSON WHERE ID = 1");
        databaseController.select("SELECT * FROM PERSON");

        databaseController.close();
    }
}
