package exceptions;

public class ContactNotFoundException extends DAOException {
    public ContactNotFoundException() {
        super("Contact not found.");
    }
}
