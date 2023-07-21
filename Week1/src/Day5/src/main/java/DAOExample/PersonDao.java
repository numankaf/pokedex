package DAOExample;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    List<Person> getAllPersons() throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePerson(Person person) throws SQLException;
    void addPerson(Person person) throws SQLException;
}
