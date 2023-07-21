package DAOExample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private Connection conn;
    private Statement stmt;
    PersonDaoImpl(Connection conn) throws SQLException {
        this.conn = conn;
        this.stmt = conn.createStatement();
    }
    @Override
    public List<Person> getAllPersons() throws SQLException {
        ResultSet result = stmt.executeQuery("SELECT * FROM PERSON");
        List<Person> personList = new ArrayList<>();
        while(result.next()){
            Person newPerson = new Person();
            newPerson.setFirstname(result.getString("FIRSTNAME"));
            newPerson.setLastname(result.getString("LASTNAME"));
            newPerson.setEmail(result.getString("EMAIL"));
            newPerson.setPhonenumber(result.getInt("PHONENUMBER"));
            personList.add(newPerson);
        }
        return personList;
    }

    @Override
    public void updatePerson(Person person) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE PERSON SET FIRSTNAME=?, LASTNAME=?,PHONENUMBER =?, EMAIL=? WHERE ID = ?");
        preparedStatement.setString(1,person.getFirstname());
        preparedStatement.setString(2,person.getLastname());
        preparedStatement.setInt(3,person.getPhonenumber());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setInt(5,person.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM PERSON WHERE ID = ?");
        preparedStatement.setInt(1,person.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void addPerson(Person person) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME,PHONENUMBER,EMAIL) VALUES (?,?,?,?,?)");
        preparedStatement.setInt(1,person.getId());
        preparedStatement.setString(2,person.getFirstname());
        preparedStatement.setString(3,person.getLastname());
        preparedStatement.setInt(4,person.getPhonenumber());
        preparedStatement.setString(5, person.getEmail());
        preparedStatement.executeUpdate();

    }
}
