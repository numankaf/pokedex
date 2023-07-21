package DAOExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DaoDemo {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");

        String jdbcURL = "jdbc:h2:~/test";
        String username = "sa";
        String password = "";

        Connection conn = DriverManager.getConnection(jdbcURL, username, password);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DROP TABLE PERSON");
        String sqlCreateTable = "CREATE TABLE   PERSON " +
                "(id INTEGER not NULL, " +
                " firstname VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " phonenumber INTEGER, " +
                " email VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sqlCreateTable);

        PersonDao personDao = new PersonDaoImpl(conn);
        System.out.println("Connected to H2");

        Person p1 = new Person(1,"Naruto","Uzumaki",17,"naruto@gmail.com");
        Person p2 = new Person(2,"Sasuke","Uchiha",15,"sasueke@gmail.com");
        Person p3 = new Person(3,"Kakashi","Hatake",21,"kakashi@gmail.com");

        personDao.addPerson(p1);
        personDao.addPerson(p2);
        personDao.addPerson(p3);
        List<Person> personList = personDao.getAllPersons();
        for(Person p : personList){
            System.out.println(p);
        }
        System.out.println("---------------");
        personDao.deletePerson(p1);
        personList = personDao.getAllPersons();
        for(Person p : personList){
            System.out.println(p);
        }
        System.out.println("---------------");

        personDao.updatePerson(new Person(2,"Minato","Namikaze",15,"minato@gmail.com"));
        personList = personDao.getAllPersons();
        for(Person p : personList){
            System.out.println(p);
        }
        System.out.println("---------------");
    }
}
