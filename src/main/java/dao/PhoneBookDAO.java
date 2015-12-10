package dao;

import exceptions.ContactNotFoundException;
import exceptions.DAOException;
import models.Contact;
import models.ContactDetails;
import models.PhoneNumber;
import models.PhoneType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PhoneBookDAO {
    public PhoneBookDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addContact(Contact contact) throws DAOException {
        final String insertStatement = "INSERT INTO `contacts` (`firstName`, `lastName`) VALUES (?, ?)";
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(insertStatement);
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Unable to save contact");
        }
    }

    public ArrayList<Contact> getAllContacts() throws DAOException {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        final String selectAllQuery = "SELECT id, firstName, lastName FROM `contacts` ORDER BY firstName;";

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectAllQuery);
            while (result.next()) {
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                int id = result.getInt("id");
                Contact contact = new Contact(id, firstName, lastName);
                contacts.add(contact);
            }
            return contacts;
        } catch (SQLException e) {
            throw new DAOException("Unable to load list of contacts");
        }
    }

    public void deleteContact(int id) throws DAOException {
        final String deleteStatement = "DELETE FROM `contacts` WHERE `id`=?;";

        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(deleteStatement);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Unable to delete contact");
        }
    }

    public Contact getContact(int id) throws DAOException {
        final String selectContact = "SELECT firstName, lastName FROM `contacts` WHERE id=" + id;

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectContact);
            if (result.next()) {
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                Contact contact = new Contact(id, firstName, lastName);
                return contact;
            } else {
                throw new ContactNotFoundException();
            }
        } catch (SQLException e) {
            throw new DAOException("Connection is not available.");
        }
    }

    public void updateContact(Contact contact) throws DAOException {
        final String updateContact = "UPDATE contacts SET firstName=?, lastName=? WHERE id=?";

        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(updateContact);
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setInt(3, contact.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Connection is not available.");
        }
    }

    public void addPhoneNumber(PhoneNumber phoneNumber, int contactId) throws DAOException {
        final String insertPhoneNumber = "INSERT INTO phone (phone_number, type_id, contact_id) VALUES (?, ?, ?)";
        try (Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(insertPhoneNumber);
            statement.setInt(1, phoneNumber.getPhoneNumber());
            statement.setString(2, phoneNumber.getType());
            statement.setInt(3, contactId);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Unable to add Phone Number.");
        }
    }

    public List<PhoneType> getListOfPhoneTypes() throws DAOException {
        String selectTypesQuery = "SELECT `type_id`, `p_type` FROM `phone_type`;";
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectTypesQuery);
            List<PhoneType> types = new ArrayList<>();
            while (result.next()) {
                int typeId = result.getInt("type_id");
                String phoneType = result.getString("p_type");
                types.add(new PhoneType(typeId, phoneType));
            }
            return types;
        } catch (SQLException e) {
            throw new DAOException("Unable to get list of Phone Types.");
        }

    }

    public ContactDetails getContactDetails(int id) throws DAOException {
        final String getDetailsQuery = "Select `phone`.`phone_number`, `phone_type`.`p_type`, `contacts`.`firstName`, `contacts`.`lastName`" +
                "FROM `phone`" +
                "RIGHT JOIN `contacts`" +
                "ON `phone`.`contact_id`=`contacts`.`id`" +
                "LEFT JOIN `phone_type`" +
                "ON `phone`.`type_id`=`phone_type`.`type_id`" +
                "WHERE `contacts`.`id`=" + id + ";";

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getDetailsQuery);
            List<PhoneNumber> phones = new ArrayList<>();
            ContactDetails details = null;

            while (result.next()) {
                String phoneType = result.getString("phone_type.p_type");
                int phoneNumber = result.getInt("phone.phone_number");
                String firstName = result.getString("contacts.firstName");
                String lastName = result.getString("contacts.lastName");
                PhoneNumber phone = new PhoneNumber(phoneNumber, phoneType);
                phones.add(phone);
                details = new ContactDetails(id, firstName, lastName, phones);
            }
            return details;

        } catch (SQLException e) {
            throw new DAOException("Unable to get contact details");
        }
    }

    public Connection connect() throws SQLException {
        final String hostName = "jdbc:mysql://127.0.0.1/phonebook";
        final String userName = "yarostbaklajana";
        final String password = "udusen81";
        return DriverManager.getConnection(hostName, userName, password);
    }
}
