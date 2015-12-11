package models;

public class PhoneNumber {
    private int id;
    private String phoneNumber;
    private String type;

    public PhoneNumber(String phoneNumber, String type) {
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public PhoneNumber(int id, String phoneNumber, String type) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
