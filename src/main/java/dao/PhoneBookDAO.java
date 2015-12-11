package dao;

import exceptions.ContactNotFoundException;
import exceptions.DAOException;
import models.*;

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

    public ArrayList<ContactInfo> getAllContacts() throws DAOException {
        ArrayList<ContactInfo> contacts = new ArrayList<ContactInfo>();
        final String selectAllQuery = "SELECT id, firstName, lastName, COUNT(phone_number) AS phones_count " +
                "FROM `contacts` LEFT JOIN phone ON contacts.id = phone.contact_id GROUP BY contacts.id, contacts.firstName, contacts.lastName ORDER BY firstName;";

        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectAllQuery);
            while (result.next()) {
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                int id = result.getInt("id");
                int count = result.getInt("phones_count");
                ContactInfo contactInfo = new ContactInfo(id, firstName, lastName, count);
                contacts.add(contactInfo);
            }
            return contacts;
        } catch (SQLException e) {
            throw new DAOException("Unable to load list of contacts");
        }
    }

    public void deleteContact(int id) throws DAOException {
        final String deleteStatement = "DELETE FROM `contacts` WHERE `id`=?;";
        String errorMessage = "Unable to delete Contatct.";
        delete(deleteStatement, errorMessage, id);
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
            statement.setLong(1, Long.parseLong(phoneNumber.getPhoneNumber()));
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
        final String getDetailsQuery = "Select `phone`.`phone_number`, `phone_type`.`p_type`, `contacts`.`firstName`, `contacts`.`lastName`, `phone`.`phone_id`" +
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
                    String firstName = result.getString("contacts.firstName");
                    String lastName = result.getString("contacts.lastName");
                    int phoneId = result.getInt("phone.phone_id");
                    if(phoneId != 0) {
                        String phoneType = result.getString("phone_type.p_type");
                        String phoneNumber = String.valueOf(result.getLong("phone.phone_number"));
                        PhoneNumber phone = new PhoneNumber(phoneId, phoneNumber, phoneType);
                        phones.add(phone);
                    }
                    details = new ContactDetails(id, firstName, lastName, phones);
                }
            if(details == null) {
                throw new ContactNotFoundException();
            }
                return details;

        } catch (SQLException e) {
            throw new DAOException("Unable to get contact details");
        }
    }

    public void deletePhoneNumber(int phoneId) throws DAOException {
        final String deletePhoneQuery = "DELETE FROM `phone` WHERE `phone_id`=?;";
        String errorMessage = "Unable to delete Phone Number";
        delete(deletePhoneQuery, errorMessage, phoneId);
    }

    private void delete(String sql, String errorMessage, int objectId) throws DAOException {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, objectId);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(errorMessage);
        }
    }
    public Connection connect() throws SQLException {
        final String hostName = "jdbc:mysql://127.0.0.1/phonebook";
        final String userName = "yarostbaklajana";
        final String password = "udusen81";
        return DriverManager.getConnection(hostName, userName, password);
    }
}
