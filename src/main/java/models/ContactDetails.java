package models;

import java.util.List;

public class ContactDetails {
    private int contactId;
    private String firstName;
    private String lastName;
    private List<PhoneNumber> phones;

    public ContactDetails(String firstName, String lastName, List<PhoneNumber> phones) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
    }
    public ContactDetails(int contactId, String firstName, String lastName, List<PhoneNumber> phones) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
    }
    public int getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }
}
