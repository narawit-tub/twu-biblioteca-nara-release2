package com.twu.biblioteca.bibliotecharelease2.domain;

import org.apache.tomcat.jni.User;

public class GuestUser extends UserApp {
    public GuestUser(String firstname, String lastname, String email, String phoneNumber, String role, String password) {
        super(firstname, lastname, email, phoneNumber, role, password);
    }
}
