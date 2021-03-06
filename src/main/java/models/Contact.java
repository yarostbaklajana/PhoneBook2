package models;

import validation.constraints.NotEmpty;

public class Contact {
    private int id;
    private String firstName;
    private String lastName;

    public Contact(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    @NotEmpty(message = "First Name is empty")
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty(message = "Last Name is empty")
    public String getLastName() {
        return lastName;
    }
}
