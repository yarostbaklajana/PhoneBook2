package models;

import validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotEmpty(message = "Phone Type is empty!")
    public String getType() {
        return type;
    }

    @NotEmpty(message = "Phone Number is empty!")
    @Pattern(regexp = "^\\d{1,12}$", message = "Only numbers are allowed in Phone Number.")
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
