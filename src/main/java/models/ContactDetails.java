package models;

import java.util.List;

public class ContactDetails extends Contact{
    private List<PhoneNumber> phones;

    public ContactDetails(int contactId, String firstName, String lastName, List<PhoneNumber> phones) {
        super(contactId, firstName, lastName);
        this.phones = phones;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public boolean getHasPhones() {
        return !phones.isEmpty();
    }
}
