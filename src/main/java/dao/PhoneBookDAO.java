package dao;

import exceptions.DAOException;
import models.Contact;

import java.sql.*;
import java.util.ArrayList;


public class PhoneBookDAO {
    public final String hostName = "jdbc:mysql://127.0.0.1/phonebook";
    public final String userName = "yarostbaklajana";
    public final String password = "udusen81";

    public PhoneBookDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addContact(Contact contact) throws DAOException {
        final String insertStatement = "INSERT INTO `contacts` (`firstName`, `lastName`) VALUES (?, ?)";
        try {
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(insertStatement);
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Unable to save contact");
        }
    }

    public ArrayList<Contact> getAllContacts() throws DAOException {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        final String selectAllQuery = "SELECT id, firstName, lastName FROM `contacts` ORDER BY firstName;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectAllQuery);

            while (result.next()) {

                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                int id = result.getInt("id");
                Contact contact = new Contact(id, firstName, lastName);
                contacts.add(contact);
            }
            connection.close();
            return contacts;
        } catch (SQLException e) {
            throw new DAOException("Unable to load list of contacts");
        }
    }

    public void deleteContact(int id) throws DAOException {
        final String deleteStatement = "DELETE FROM `contacts` WHERE `id`=?;";

        try {
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(deleteStatement);
            statement.setInt(1, id);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Unable to delete contact");
        }
    }

    public Contact getContact(int id) throws DAOException {
        final String selectContact = "SELECT firstName, lastName FROM `contacts` WHERE id=" + id;
        Contact contact = null;
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectContact);
            if (result.next()) {
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                contact = new Contact(id, firstName, lastName);
            } else {
                throw new DAOException("Unable to get contact details. The record doesn't exist or was removed in another session.");
            }
            connection.close();
            return contact;
        } catch (SQLException e) {
            throw new DAOException("Connection is not available.");
        }
    }

    public void updateContact(Contact contact) throws DAOException {
        final String updateContact = "UPDATE contacts SET firstName=?, lastName=? WHERE id=?";

        try {
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(updateContact);
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setInt(3, contact.getId());
            statement.execute();
            connection.close();

        } catch (SQLException e) {
            throw new DAOException("Connection is not available.");
        }
    }

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(hostName, userName, password);
    }
}
