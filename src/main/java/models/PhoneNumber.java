package models;

public class PhoneNumber {
    private int id;
    private int phoneNumber;
    private String type;

    public PhoneNumber(int phoneNumber, String type) {
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public PhoneNumber(int id, int phoneNumber, String type) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
