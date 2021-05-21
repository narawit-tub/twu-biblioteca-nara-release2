package com.twu.biblioteca.bibliotecharelease2.domain;

public class LibarianUser extends UserApp{
    private String libaryNumber;

    public String getLibaryNumber() {
        return libaryNumber;
    }

    public LibarianUser(String firstname, String lastname, String email, String phoneNumber, String role, String password, String libaryNumber) {
        super(firstname, lastname, email, phoneNumber, role, password);

        this.libaryNumber = libaryNumber;
    }
}
