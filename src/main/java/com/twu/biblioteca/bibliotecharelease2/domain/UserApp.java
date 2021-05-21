package com.twu.biblioteca.bibliotecharelease2.domain;

public class UserApp {

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String role;

    public UserApp(String firstname, String lastname, String email, String phoneNumber, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%s %s, email: %s, phone: %s", getFirstname(), getLastname(), getEmail(), getPhoneNumber());
    }
}
