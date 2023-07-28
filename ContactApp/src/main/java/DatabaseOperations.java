import Entity.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    private final Connection conn;

    DatabaseOperations() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionPoolImpl.getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS CONTACT("
                + "   ID INT PRIMARY KEY AUTO_INCREMENT,"
                + "   name     VARCHAR(255),"
                + "   gsm VARCHAR(255))";

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);

    }

    public void createContact(Contact contact) {
        String sql = "INSERT INTO CONTACT (NAME,GSM) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getGsm());
            preparedStatement.executeUpdate();
            System.out.println("Executed :" + sql);
        } catch (SQLException sqlException) {
            System.out.println("Error occured while executing :" + sql);
        }
    }

    public void updateContact(Contact contact) {
        String sql = "UPDATE CONTACT SET NAME =? , GSM =? WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getGsm());
            preparedStatement.setInt(3, contact.getId());
            preparedStatement.executeUpdate();
            System.out.println("Executed :" + sql);
        } catch (SQLException sqlException) {
            System.out.println("Error occured while executing :" + sql);
        }
    }

    public List<Contact> searchContact(String name) {
        String sql = "SELECT * FROM CONTACT WHERE NAME = ?";
        List<Contact> contacts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer oid = rs.getInt(1);
                String oname = rs.getString(2);
                String ogsm = rs.getString(3);
                Contact c = new Contact(oname, ogsm);
                c.setId(oid);
                contacts.add(c);

            }
            System.out.println("Executed :" + sql);
            return contacts;
        } catch (SQLException sqlException) {
            System.out.println("Error occured while executing :" + sql);
        }
        return null;
    }

    public List<Contact> getAllContact() {
        String sql = "SELECT * FROM CONTACT";
        List<Contact> contacts = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Integer oid = rs.getInt(1);
                String oname = rs.getString(2);
                String ogsm = rs.getString(3);
                Contact c = new Contact(oname, ogsm);
                c.setId(oid);
                contacts.add(c);
            }
            System.out.println("Executed :" + sql);
            return contacts;
        } catch (SQLException sqlException) {
            System.out.println("Error occured while executing :" + sql);
        }
        return null;
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM CONTACT WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,     id);
            preparedStatement.executeUpdate();
            System.out.println("Executed : " + sql);
        } catch (SQLException sqlException) {
            System.out.println("Error occured while executing :" + sql);
        }
    }

}
