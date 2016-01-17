package dao;

import exceptions.DAOException;
import models.*;
import java.util.ArrayList;
import java.util.List;

public interface PhoneBookDAO {
    void addContact(Contact contact) throws DAOException;

    ArrayList<ContactInfo> getAllContacts() throws DAOException;

    void deleteContact(int id) throws DAOException;

    Contact getContact(int id) throws DAOException;

    void updateContact(Contact contact) throws DAOException;

    void addPhoneNumber(PhoneNumber phoneNumber, int contactId) throws DAOException;

    List<PhoneType> getListOfPhoneTypes() throws DAOException;

    ContactDetails getContactDetails(int id) throws DAOException;

    void deletePhoneNumber(int phoneId) throws DAOException;
}
