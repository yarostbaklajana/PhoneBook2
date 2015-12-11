package models;

public class ContactInfo extends Contact{
    private int phonesCount;
    public ContactInfo(int id, String firstName, String lastName, int phonesCount) {
        super(id, firstName, lastName);
        this.phonesCount = phonesCount;
    }

    public int getPhonesCount() {
        return phonesCount;
    }
}
